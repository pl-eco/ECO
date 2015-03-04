package org.sunflow.core;
import org.sunflow.core.display.FrameDisplay;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class $UTILMODES{
    public static final int $MAX = 2;
    public static final int high = 2;
    public static final int low = 1;
    public static final int mid = 0;
}
public class Scene {
    private LightServer lightServer;
    private InstanceList instanceList;
    private InstanceList infiniteInstanceList;
    private Camera camera;
    private AccelerationStructure intAccel;
    private String acceltype;
    private boolean bakingViewDependent;
    private Instance bakingInstance;
    private PrimitiveList bakingPrimitives;
    private AccelerationStructure bakingAccel;
    private boolean rebuildAccel;
    private int[] imageWidth = new int[] { 480, 800, 1024, };
    private int[] imageHeight = new int[] { 320, 600, 728, };
    private int threads;
    private boolean lowPriority;
    public Scene() { super();
                     lightServer = new LightServer(this);
                     instanceList = new InstanceList();
                     infiniteInstanceList = new InstanceList();
                     acceltype = "auto";
                     bakingViewDependent = false;
                     bakingInstance = null;
                     bakingPrimitives = null;
                     bakingAccel = null;
                     camera = null;
                     threads = 0;
                     lowPriority = true;
                     rebuildAccel = true; }
    public int getThreads() { return threads <= 0 ? Runtime.getRuntime().
                                availableProcessors()
                                : threads; }
    public int getThreadPriority() { return lowPriority ? Thread.MIN_PRIORITY
                                       : Thread.
                                           NORM_PRIORITY; }
    public void setCamera(Camera camera) { this.camera = camera; }
    Camera getCamera() { return camera; }
    public void setInstanceLists(Instance[] instances, Instance[] infinite) {
        infiniteInstanceList =
          new InstanceList(
            infinite);
        instanceList =
          new InstanceList(
            instances);
        rebuildAccel =
          true;
    }
    public void setLightList(LightSource[] lights) { lightServer.setLights(
                                                                   lights);
    }
    public void setShaderOverride(Shader shader, boolean photonOverride) {
        lightServer.
          setShaderOverride(
            shader,
            photonOverride);
    }
    public void setBakingInstance(Instance instance) { bakingInstance = instance;
    }
    public ShadingState getRadiance(IntersectionState istate, float rx,
                                    float ry,
                                    double lensU,
                                    double lensV,
                                    double time,
                                    int instance) { if (bakingPrimitives ==
                                                          null) {
                                                        Ray r =
                                                          camera.
                                                          getRay(
                                                            rx,
                                                            ry,
                                                            imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
                                                            imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
                                                            lensU,
                                                            lensV,
                                                            time);
                                                        return r !=
                                                          null
                                                          ? lightServer.
                                                          getRadiance(
                                                            rx,
                                                            ry,
                                                            instance,
                                                            r,
                                                            istate)
                                                          : null;
                                                    }
                                                    else {
                                                        Ray r =
                                                          new Ray(
                                                          rx /
                                                            (imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]),
                                                          ry /
                                                            (imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]),
                                                          -1,
                                                          0,
                                                          0,
                                                          1);
                                                        this.
                                                          traceBake(
                                                            r,
                                                            istate);
                                                        if (!istate.
                                                              hit())
                                                            return null;
                                                        ShadingState state =
                                                          ShadingState.
                                                          createState(
                                                            istate,
                                                            rx,
                                                            ry,
                                                            r,
                                                            instance,
                                                            lightServer);
                                                        bakingPrimitives.
                                                          prepareShadingState(
                                                            state);
                                                        if (bakingViewDependent)
                                                            state.
                                                              setRay(
                                                                camera.
                                                                  getRay(
                                                                    state.
                                                                      getPoint()));
                                                        else {
                                                            Point3 p =
                                                              state.
                                                              getPoint();
                                                            Vector3 n =
                                                              state.
                                                              getNormal();
                                                            Ray incoming =
                                                              new Ray(
                                                              p.
                                                                x +
                                                                n.
                                                                  x,
                                                              p.
                                                                y +
                                                                n.
                                                                  y,
                                                              p.
                                                                z +
                                                                n.
                                                                  z,
                                                              -n.
                                                                 x,
                                                              -n.
                                                                 y,
                                                              -n.
                                                                 z);
                                                            incoming.
                                                              setMax(
                                                                1);
                                                            state.
                                                              setRay(
                                                                incoming);
                                                        }
                                                        lightServer.
                                                          shadeBakeResult(
                                                            state);
                                                        return state;
                                                    }
    }
    public BoundingBox getBounds() { return instanceList.
                                       getWorldBounds(
                                         null);
    }
    void trace(Ray r, IntersectionState state) {
        state.
          instance =
          null;
        state.
          current =
          null;
        for (int i =
               0;
             i <
               infiniteInstanceList.
               getNumPrimitives();
             i++)
            infiniteInstanceList.
              intersectPrimitive(
                r,
                i,
                state);
        state.
          current =
          null;
        intAccel.
          intersect(
            r,
            state);
    }
    Color traceShadow(Ray r, IntersectionState state) {
        this.
          trace(
            r,
            state);
        return state.
          hit()
          ? Color.
              WHITE
          : Color.
              BLACK;
    }
    void traceBake(Ray r, IntersectionState state) {
        state.
          current =
          bakingInstance;
        state.
          instance =
          null;
        bakingAccel.
          intersect(
            r,
            state);
    }
    public void render(Options options, ImageSampler sampler,
                       Display display) {
        if (display ==
              null)
            display =
              new FrameDisplay(
                );
        if (bakingInstance !=
              null) {
            UI.
              printDetailed(
                Module.
                  SCENE,
                "Creating primitives for lightmapping ...");
            bakingPrimitives =
              bakingInstance.
                getBakingPrimitives();
            if (bakingPrimitives ==
                  null) {
                UI.
                  printError(
                    Module.
                      SCENE,
                    "Lightmap baking is not supported for the given instance.");
                return;
            }
            int n =
              bakingPrimitives.
              getNumPrimitives();
            UI.
              printInfo(
                Module.
                  SCENE,
                ("Building acceleration structure for lightmapping (%d num pri" +
                 "mitives) ..."),
                n);
            bakingAccel =
              AccelerationStructureFactory.
                create(
                  "auto",
                  n,
                  true);
            bakingAccel.
              build(
                bakingPrimitives);
        }
        else {
            bakingPrimitives =
              null;
            bakingAccel =
              null;
        }
        bakingViewDependent =
          options.
            getBoolean(
              "baking.viewdep",
              bakingViewDependent);
        if (bakingInstance !=
              null &&
              bakingViewDependent &&
              camera ==
              null ||
              bakingInstance ==
              null &&
              camera ==
              null) {
            UI.
              printError(
                Module.
                  SCENE,
                "No camera found");
            return;
        }
        threads =
          options.
            getInt(
              "threads",
              0);
        lowPriority =
          options.
            getBoolean(
              "threads.lowPriority",
              true);
        int imageWidthTemp =
          MathUtils.
          clamp(
            imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
            1,
            1 <<
              14);
        int imageHeightTemp =
          MathUtils.
          clamp(
            imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
            1,
            1 <<
              14);
        long numPrimitives =
          0;
        for (int i =
               0;
             i <
               instanceList.
               getNumPrimitives();
             i++)
            numPrimitives +=
              instanceList.
                getNumPrimitives(
                  i);
        UI.
          printInfo(
            Module.
              SCENE,
            "Scene stats:");
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Infinite instances:  %d",
            infiniteInstanceList.
              getNumPrimitives());
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Instances:           %d",
            instanceList.
              getNumPrimitives());
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Primitives:          %d",
            numPrimitives);
        String accelName =
          options.
          getString(
            "accel",
            null);
        if (accelName !=
              null) {
            rebuildAccel =
              rebuildAccel ||
                !acceltype.
                equals(
                  accelName);
            acceltype =
              accelName;
        }
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Instance accel:      %s",
            acceltype);
        if (rebuildAccel) {
            intAccel =
              AccelerationStructureFactory.
                create(
                  acceltype,
                  instanceList.
                    getNumPrimitives(),
                  false);
            intAccel.
              build(
                instanceList);
            rebuildAccel =
              false;
        }
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene bounds:        %s",
            this.
              getBounds());
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene center:        %s",
            this.
              getBounds().
              getCenter());
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene diameter:      %.2f",
            this.
              getBounds().
              getExtents().
              length());
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Lightmap bake:       %s",
            bakingInstance !=
              null
              ? (bakingViewDependent
                   ? "view"
                   : "ortho")
              : "off");
        if (sampler ==
              null)
            return;
        if (!lightServer.
              build(
                options))
            return;
        UI.
          printInfo(
            Module.
              SCENE,
            "Rendering ...");
        sampler.
          prepare(
            options,
            this,
            imageWidthTemp,
            imageHeightTemp);
        sampler.
          render(
            display);
        lightServer.
          showStats();
        bakingPrimitives =
          null;
        bakingAccel =
          null;
        UI.
          printInfo(
            Module.
              SCENE,
            "Done.");
    }
    public boolean calculatePhotons(PhotonStore map,
                                    String type,
                                    int seed) {
        return lightServer.
          calculatePhotons(
            map,
            type,
            seed);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1415733436000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAMVaC2wcxRmeO78fYMdxnAeJnThOQl53sRMTEqMGx3GIk0ti" +
       "7MQBh2DGe3PnJXu3\ny+6ccw4pDUVKAghIVGiLgBBK1PAsSBSlVDQlCq+CqC" +
       "BqiURLKEIqVEBVREVTtZX6z2Nv7/ZuDz8i\nYWnGezsz/3z/c/6Z2We+REWW" +
       "iWYqVoCOGMQKdPR2Y9Mi4Q4NW9Y2eDWgvFZU1n1iU1z3I18I+dUw\nRVUhxQ" +
       "qGMcVBNRzsWteWNNFiQ9dGoppOAyRJAzdrrZLexlBrFsEdR0/W3H68sMGPik" +
       "KoCsfjOsVU\n1eOdGolZFFWHbsbDOJigqhYMqRZtC6FLSDwR69DjFsVxat2C" +
       "bkMFIVRsKIwmRXNC9uRBmDxoYBPH\ngnz6YDefFihMNgnFapyE21PTwcglmS" +
       "MBthzXk90biJSyxj5ghyMArmenuBbcZrFqFDzRd8W+Y08W\noKp+VKXGexkx" +
       "BTihMF8/qoyR2CAxrfZwmIT70aQ4IeFeYqpYU/fyWftRjaVG45gmTGL1EEvX" +
       "hlnH\nGithEJPPab8MoUqF8WQmFKqbKRlFVKKF7V9FEQ1Hge06h23B7nr2Hh" +
       "gsVwGYGcEKsYcU7lbjoPEG\n94gUj02boAMMLYkROqSnpiqMY3iBaoQuNRyP" +
       "Bnupqcaj0LVIT8AsFM3wJMpkbWBlN46SAYqmuft1\niyboVcYFwYZQNMXdjV" +
       "MCLc1waSlNP4vrvjn0xMOnrua2XRgmisbwl8OgetegHhIhJokrRAy8kAjc\n" +
       "33V9YqYfIeg8xdVZ9Gmfd3J76LPfNog+l+Xos3XwZqLQAWXLkYaeW6/RUQGD" +
       "UWrolsqUn8E5d4du\n2dKWNMBr61IUWWPAbnyl5/Xr9z9FPvej8i5UrOhaIg" +
       "Z2NEnRY4aqEfMaEicmpiTchcpIPNzB27tQ\nCTyHwOTF262RiEVoFyrU+Kti" +
       "nf8GEUWABBNRGTyr8YhuPxuYDvHnpIEQKoGCKqHUIPHH/1O0MBC0\nEvGIpu" +
       "8JWqYS1M1o6reimyTYqwC4ADMZg6LO4JAeI0Gs4Lga14NRFZxU0ZeGyTD7P1" +
       "pCSYaqZo/P\nx8Kc2101sPQNuhYm5oBy4pO39nVuuvOQMAVmvpIf8BegH5D0" +
       "A4x+gNNHPh8nW8vmEToACe4GX4So\nVbmwd9fGmw41FoDyjT2FwD7r2gjI5e" +
       "Sdit7hOGwXj20KWM20n+08GLhwYo2wmqB3XM05uuLdZ98+\n9nXlIj/y5w56" +
       "jCkIu+WMTDeLlKlg1uR2k1z0/37X5hfef/vDyx2Hoagpy4+zRzI/bHSL39QV" +
       "EobI\n5pA/Pr2qYAfqO+JHheDcENA4fogV9e45MvyxzY5tjJeSEKqI6GYMa6" +
       "zJDkjldMjU9zhvuF1U8+fJ\noJwKZqDVUK6QFsv/s9YpBqvrhB0xbbu44LHz" +
       "wh3Fy869XPEaF4sdZqvSFrJeQoXTTnKMZZtJCLz/\n8KfdP3rgy4M7uaVIU6" +
       "GwuiUGNVVJwpD5zhDwVg0iBlNk0/Z4TA+rERUPaoRZ3H+r5jW/+MW91UI1\n" +
       "GryxNbvk2wk476evRfvfvvFf9ZyMT2GrhcOG001wM9mh3G6aeIThSN5+dtaD" +
       "b+BHIJhBALHUvYTH\nBMQ5Q1yOQS73RbwOuNqaWdUItJd4mH6OtXlA2fdUtD" +
       "Fxy+9e4qgrcPoin66GzdhoE5pn1Vwm3alu\n792ArSHot+KVLTdUa6/8Byj2" +
       "A0UF1kRrqwmhIpmhRNm7qOSD02fqbnqvAPnXo3JNx+H1mNs/KgPD\nI9YQRJ" +
       "mkseZqblvVe0qltaEk4kKYIQWQTPtVBeAWerv/erayO54zMLjkidBbWx/hAv" +
       "B0/BwLm4vO\n3lPbj154h57ndBwPZKPnJLNDKGRDztgr3x+eVPz8ozE/KulH" +
       "1YrM1/qwlmB23g/phWUncZDTZbRn\npgpiXWxLRZiZbu9Pm9bt+07ohmfWmz" +
       "1XutydrU2oCUqtdPdat7v7EH9Yw4c08XpByjlLDFMdxiyH\nQxWaGh2ikLQN" +
       "E5F1TQW4WetFyOkk4girW1h1tVB5q6dprM4EPR/KFAl6igfoLla1U1SpShkx" +
       "t7TB\nzcoC15XWy4Vu4xjRMWeuk+jqPNB1S3S1kD+ocZWSromivHaMKGdAmS" +
       "pRTvVAuUOiLFbAnExs45qa\nhauDt7sQXTdGRIuhTJOIpnkgulEiKoUkvR3C" +
       "kWZjmp+FiTezLA8cv5evxLDKuiAOjBHiLCjTJcTp\nHhCJhFjGwqVG7cUWMF" +
       "bziMmcOyD2AS40kTGiuRzKTIlmpgcaTaKZPIhhFxPtU8medcSA7JbAJtJE\n" +
       "09I3vaYag+R5mGcTnxxo/M2b2x89KDKwPCE4Y9SA8oOP/rK74L7Tg2KcO866" +
       "Oh+pP/7XFz7pqRWr\ntdhlzc3a6KSPETstHsiqDLZyzck3A+/96uI5z9zWc1" +
       "4iqsncL3TCnvrTkTNkwVX3fJwj6S0Z1HWN\n4LhLUbExKqpJmo5tQrkUtVcq" +
       "6lKhKNvTbduZ7hkLXNhuHYfX1Uts9R7YbpfYqgW2lIwtG119FrpU\nnxyh6o" +
       "fjCKgNEmKDB8RDEmKFgHgRYsOdY0Q5Rxb7ORfK++xFySSDCVULcxzsneWa/P" +
       "DoJ+fbymIo\nC+Tkcz0mv19OXq7GcJTsUMN0CBxoirMb68aUEjPOPOfp9l3n" +
       "W+6vPyN8YhBbaRmEbghwlKLCYV0V\nxx1pLwsgNrvYeWAc7CyV7Cz0YOeYrX" +
       "HOzgbCsgtO5ScGh/kgqx5yAXlsjEpl69AyCWSZB5CfSyAl\nLMnFQhzueU+M" +
       "cd7ZUJrlvM0e8z5tCwDMGtxNN1U6ksuWnskzN5fU/Iz0G2xiltdhEQ+oB6/7" +
       "qvIA\nfnWXX25WErDWUd1YqpFh6XNO7j4rY6+/mR+PObnyXU8+fZK+t3i1CM" +
       "2LvBcZ98BFq4/tbVj93N3j\n2OE3uHhzk540fNm1BUPqm35+gidS76yTv8xB" +
       "bZkJdzngSQg3cpxmduYu+zIom6V+N7v1y/XnKCb3\nFvHlPG2nWPUSeHqU0G" +
       "1pRunYxK+/zR5T1sB+vJgJvhFKnwTfNy7wr+dpe5NVZyialAKfbtxpPLw6\n" +
       "ER5Y7jQgeRgYNQ8+eYglFxavRJgTeDcPj39g1TvgNxahHU5urcvI5fD4+3Hy" +
       "yF+yzYcieVRy8pi9\nsZOUcsP+KE/bx6z6AFiKZrCUR0oOl3+aiCY3QYlJLm" +
       "Oj1qSfU/Szs6y0FJif3zDHffLwusk9q3be\nwQNMGdZUbG1xPNyvhtmTD8LJ" +
       "PO+glSI2oCzYdfIfp0+RBfwcpVS1YOvebkZzHMunjfkKP0U2n4sc\n5ceAzh" +
       "Lsvs/Ivq7IuIXgErg0JTGemCzMJ7FvTzgNw0AQW7hQWpa3rlwFQqwBIbLLsI" +
       "AaDoR0BWtd\n6x47XXH2SOKKjSK4X5LWoWvdvuc3VpY+dvcBHmSlNMvSTv3l" +
       "75JhbG5x8nH272uKtl6UM/HVzctW\nLVm+fGnrcrBZ+4xAJLRfpMS1kklnzU" +
       "UV15XNfMg/WfW/i8pMa+vSlSv41lgcKXDVf+7tsj4/e/kZ\npPXsmsN9/vC+" +
       "455/m4h7tvBu4i85rkDrqGOWHbk9Cdrq8Dp90hNmtkaWpzTiq7p4GmlZtqRl" +
       "1dIV\nqygq5sdjFpd5eR591LKqBHYH7JqJDbH3To4ufKUT0cUGKAek6A6MOV" +
       "R6hvPeIRwWZ3qCx5l5eJzN\nqumwugOPYtzWYWKaapi4GJ0xEUZXQDkiGT0y" +
       "LqPL69Kck8vzcLmEVfMEl2sz9vMuLudPhMtDUB6X\nXD4+ai5LOMUSm8vGHF" +
       "zCgmKJGw52V0/cG7wi6IuZYfpWupuKw3pikN+O+lbbFXqIy2RFHnl9j1XN\n" +
       "sJWB5KEHh9X0k4/sU1BmNyBSBxsXZctEU9ozUpRnRi3KdBY25GnbyKpOkRut" +
       "1RPxcOrgJCNOxTAd\nCvB24G6tnnR4Wz+RNJCheUPy9oYnb3/O7/W1WVrowS" +
       "NjsCEuh215ZNTPqmvBuKiJs9ykZyL8t0I5\nK/k/+53yP5iHf/blgG8AXIDz" +
       "z0xc35Mz5PLjDnaHKb9w4QK6aSICYkv0OSmgc9+pgMw8AuKVzs4a\nmIAgqr" +
       "qNxJhIANgC5VMpg09HHQAKOMUCm81pWWxuNfhxhHcs62La7MUxQ3OuzrLJrF" +
       "MtQ8MjXAT7\n88iInYL6vg9h2GQn/KZLQLdNcJvlu1LIR/wfj4Cy07LuIZ0y" +
       "C4Bnu1POaxKxhhzOw/uPWXUPDIfd\nh5LQwKQEbd7ZcqRw72ilkIRgxBM6ds" +
       "k/LesrN/FllhL64NYbvgn98d9im2Z/PVUBm5lIQtPS72HT\nnosNk0RULrYK" +
       "cSvLd/6+RwC+W0QUFSpSOr6HRbdjECbSulFYzcVTeqfHKSqATuzxuJFDtuJ+" +
       "OZnB\nMuN0bsaOln9IaB+VJcSnhAPKdc/unJ28e9thvj0uUjS8l19flMOWTX" +
       "x1wqmy47Y5ntRsWu+i55/r\ne/kXq+zjRP5VQm3a+USGVbaI1jwqNFFD7k89" +
       "OmMG5R9n7P3V1F9edeLoeT//2OT/tzTa8f8pAAA=");
}
