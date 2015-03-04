package org.sunflow;
import java.util.ArrayList;
import org.sunflow.core.Camera;
import org.sunflow.core.Geometry;
import org.sunflow.core.Instance;
import org.sunflow.core.LightSource;
import org.sunflow.core.Modifier;
import org.sunflow.core.Options;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.RenderObject;
import org.sunflow.core.Scene;
import org.sunflow.core.Shader;
import org.sunflow.core.Tesselatable;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import java.util.Map;
import java.util.HashMap;
final class RenderObjectMap {
    private Map<String,RenderObjectHandle> renderObjects;
    private boolean rebuildInstanceList;
    private boolean rebuildLightList;
    private enum RenderObjectType {
        UNKNOWN, SHADER, MODIFIER, GEOMETRY, INSTANCE, LIGHT, CAMERA, OPTIONS;
         
        private RenderObjectType() {
            
        }
        public static final String
          jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long
          jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "FwAA");
    }
    RenderObjectMap() { super();
                        renderObjects = new HashMap<String,RenderObjectHandle>(
                                          );
                        rebuildInstanceList = (rebuildLightList = false);
    }
    final boolean has(String name) { return renderObjects.containsKey(name);
    }
    final void remove(String name) { RenderObjectHandle obj = renderObjects.
                                       get(
                                         name);
                                     if (obj == null) { UI.printWarning(Module.
                                                                          API,
                                                                        "Unable to remove \"%s\" - object was not defined yet");
                                                        return; }
                                     UI.printDetailed(Module.API, "Removing object \"%s\"",
                                                      name);
                                     renderObjects.remove(name);
                                     switch (obj.type) { case SHADER:
                                                             Shader s =
                                                               obj.
                                                               getShader(
                                                                 );
                                                             for (Map.Entry<String,RenderObjectHandle> e
                                                                   :
                                                                   renderObjects.
                                                                    entrySet(
                                                                      )) {
                                                                 Instance i =
                                                                   e.
                                                                   getValue(
                                                                     ).
                                                                   getInstance(
                                                                     );
                                                                 if (i !=
                                                                       null) {
                                                                     UI.
                                                                       printWarning(
                                                                         Module.
                                                                           API,
                                                                         "Removing shader \"%s\" from instance \"%s\"",
                                                                         name,
                                                                         e.
                                                                           getKey(
                                                                             ));
                                                                     i.
                                                                       removeShader(
                                                                         s);
                                                                 }
                                                             }
                                                             break;
                                                         case MODIFIER:
                                                             Modifier m =
                                                               obj.
                                                               getModifier(
                                                                 );
                                                             for (Map.Entry<String,RenderObjectHandle> e
                                                                   :
                                                                   renderObjects.
                                                                    entrySet(
                                                                      )) {
                                                                 Instance i =
                                                                   e.
                                                                   getValue(
                                                                     ).
                                                                   getInstance(
                                                                     );
                                                                 if (i !=
                                                                       null) {
                                                                     UI.
                                                                       printWarning(
                                                                         Module.
                                                                           API,
                                                                         "Removing modifier \"%s\" from instance \"%s\"",
                                                                         name,
                                                                         e.
                                                                           getKey(
                                                                             ));
                                                                     i.
                                                                       removeModifier(
                                                                         m);
                                                                 }
                                                             }
                                                             break;
                                                         case GEOMETRY:
                                                             {
                                                                 Geometry g =
                                                                   obj.
                                                                   getGeometry(
                                                                     );
                                                                 for (Map.Entry<String,RenderObjectHandle> e
                                                                       :
                                                                       renderObjects.
                                                                        entrySet(
                                                                          )) {
                                                                     Instance i =
                                                                       e.
                                                                       getValue(
                                                                         ).
                                                                       getInstance(
                                                                         );
                                                                     if (i !=
                                                                           null &&
                                                                           i.
                                                                           hasGeometry(
                                                                             g)) {
                                                                         UI.
                                                                           printWarning(
                                                                             Module.
                                                                               API,
                                                                             ("Removing instance \"%s\" because it referenced geometry \"%s" +
                                                                              "\""),
                                                                             e.
                                                                               getKey(
                                                                                 ),
                                                                             name);
                                                                         remove(
                                                                           e.
                                                                             getKey(
                                                                               ));
                                                                     }
                                                                 }
                                                                 break;
                                                             }
                                                         case INSTANCE:
                                                             rebuildInstanceList =
                                                               true;
                                                             break;
                                                         case LIGHT:
                                                             rebuildLightList =
                                                               true;
                                                             break;
                                                         default:
                                                             break;
                                     } }
    final boolean update(String name, ParameterList pl,
                         SunflowAPI api) {
        RenderObjectHandle obj =
          renderObjects.
          get(
            name);
        boolean success;
        if (obj ==
              null) {
            UI.
              printError(
                Module.
                  API,
                "Unable to update \"%s\" - object was not defined yet",
                name);
            success =
              false;
        }
        else {
            UI.
              printDetailed(
                Module.
                  API,
                "Updating %s object \"%s\"",
                obj.
                  typeName(
                    ),
                name);
            success =
              obj.
                update(
                  pl,
                  api);
            if (!success) {
                UI.
                  printError(
                    Module.
                      API,
                    "Unable to update \"%s\" - removing",
                    name);
                remove(
                  name);
            }
            else {
                switch (obj.
                          type) {
                    case GEOMETRY:
                    case INSTANCE:
                        rebuildInstanceList =
                          true;
                        break;
                    case LIGHT:
                        rebuildLightList =
                          true;
                        break;
                    default:
                        break;
                }
            }
        }
        return success;
    }
    final void updateScene(Scene scene) {
        if (rebuildInstanceList) {
            UI.
              printInfo(
                Module.
                  API,
                "Building scene instance list for rendering ...");
            int numInfinite =
              0;
            int numInstance =
              0;
            for (Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet(
                     )) {
                Instance i =
                  e.
                  getValue(
                    ).
                  getInstance(
                    );
                if (i !=
                      null) {
                    i.
                      updateBounds(
                        );
                    if (i.
                          getBounds(
                            ) ==
                          null)
                        numInfinite++;
                    else
                        numInstance++;
                }
            }
            Instance[] infinite =
              new Instance[numInfinite];
            Instance[] instance =
              new Instance[numInstance];
            numInfinite =
              (numInstance =
                 0);
            for (Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet(
                     )) {
                Instance i =
                  e.
                  getValue(
                    ).
                  getInstance(
                    );
                if (i !=
                      null) {
                    if (i.
                          getBounds(
                            ) ==
                          null) {
                        infinite[numInfinite] =
                          i;
                        numInfinite++;
                    }
                    else {
                        instance[numInstance] =
                          i;
                        numInstance++;
                    }
                }
            }
            scene.
              setInstanceLists(
                instance,
                infinite);
            rebuildInstanceList =
              false;
        }
        if (rebuildLightList) {
            UI.
              printInfo(
                Module.
                  API,
                "Building scene light list for rendering ...");
            ArrayList<LightSource> lightList =
              new ArrayList<LightSource>(
              );
            for (Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet(
                     )) {
                LightSource light =
                  e.
                  getValue(
                    ).
                  getLight(
                    );
                if (light !=
                      null)
                    lightList.
                      add(
                        light);
            }
            scene.
              setLightList(
                lightList.
                  toArray(
                    new LightSource[lightList.
                                      size(
                                        )]));
            rebuildLightList =
              false;
        }
    }
    final void put(String name, Shader shader) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              shader));
    }
    final void put(String name, Modifier modifier) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              modifier));
    }
    final void put(String name, PrimitiveList primitives) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              primitives));
    }
    final void put(String name, Tesselatable tesselatable) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              tesselatable));
    }
    final void put(String name, Instance instance) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              instance));
    }
    final void put(String name, LightSource light) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              light));
    }
    final void put(String name, Camera camera) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              camera));
    }
    final void put(String name, Options options) {
        renderObjects.
          put(
            name,
            new RenderObjectHandle(
              options));
    }
    final Geometry lookupGeometry(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getGeometry(
            );
    }
    final Instance lookupInstance(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getInstance(
            );
    }
    final Camera lookupCamera(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getCamera(
            );
    }
    final Options lookupOptions(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getOptions(
            );
    }
    final Shader lookupShader(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getShader(
            );
    }
    final Modifier lookupModifier(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getModifier(
            );
    }
    final LightSource lookupLight(String name) {
        if (name ==
              null)
            return null;
        RenderObjectHandle handle =
          renderObjects.
          get(
            name);
        return handle ==
          null
          ? null
          : handle.
          getLight(
            );
    }
    private static final class RenderObjectHandle {
        private final RenderObject obj;
        private final RenderObjectType type;
        private RenderObjectHandle(Shader shader) {
            super(
              );
            obj =
              shader;
            type =
              RenderObjectType.
                SHADER;
        }
        private RenderObjectHandle(Modifier modifier) {
            super(
              );
            obj =
              modifier;
            type =
              RenderObjectType.
                MODIFIER;
        }
        private RenderObjectHandle(Tesselatable tesselatable) {
            super(
              );
            obj =
              new Geometry(
                tesselatable);
            type =
              RenderObjectType.
                GEOMETRY;
        }
        private RenderObjectHandle(PrimitiveList prims) {
            super(
              );
            obj =
              new Geometry(
                prims);
            type =
              RenderObjectType.
                GEOMETRY;
        }
        private RenderObjectHandle(Instance instance) {
            super(
              );
            obj =
              instance;
            type =
              RenderObjectType.
                INSTANCE;
        }
        private RenderObjectHandle(LightSource light) {
            super(
              );
            obj =
              light;
            type =
              RenderObjectType.
                LIGHT;
        }
        private RenderObjectHandle(Camera camera) {
            super(
              );
            obj =
              camera;
            type =
              RenderObjectType.
                CAMERA;
        }
        private RenderObjectHandle(Options options) {
            super(
              );
            obj =
              options;
            type =
              RenderObjectType.
                OPTIONS;
        }
        private boolean update(ParameterList pl,
                               SunflowAPI api) {
            return obj.
              update(
                pl,
                api);
        }
        private String typeName() { return type.
                                      name(
                                        ).
                                      toLowerCase(
                                        );
        }
        private Shader getShader() { return type ==
                                       RenderObjectType.
                                         SHADER
                                       ? (Shader)
                                           obj
                                       : null;
        }
        private Modifier getModifier() { return type ==
                                           RenderObjectType.
                                             MODIFIER
                                           ? (Modifier)
                                               obj
                                           : null;
        }
        private Geometry getGeometry() { return type ==
                                           RenderObjectType.
                                             GEOMETRY
                                           ? (Geometry)
                                               obj
                                           : null;
        }
        private Instance getInstance() { return type ==
                                           RenderObjectType.
                                             INSTANCE
                                           ? (Instance)
                                               obj
                                           : null;
        }
        private LightSource getLight() { return type ==
                                           RenderObjectType.
                                             LIGHT
                                           ? (LightSource)
                                               obj
                                           : null;
        }
        private Camera getCamera() { return type ==
                                       RenderObjectType.
                                         CAMERA
                                       ? (Camera)
                                           obj
                                       : null;
        }
        private Options getOptions() { return type ==
                                         RenderObjectType.
                                           OPTIONS
                                         ? (Options)
                                             obj
                                         : null;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "cx6u85KDAsmzh39SOvtce3j7J5w/s0sjd3cqP+2CE1eG96svJf4d7K1+PUhq" +
           "JkmT4hznDshaEvfCSTjC2O4ZD458Oe25xxGRgQ2ms5AOb4aQJdabH2TSPShj" +
           "byzXe1KCVpzlDfB8IWwv3tm2DxBemOcsvZyuR/IVYRMs9jOQrOqylmKkwpg+" +
           "VDzoZueV3KmExZO5eDbBc9PBc7MInqN+eJA8AWC42i6aTaUmvBPOXDWZqcJC" +
           "Khylq21+UhbSshyVYHLSWexAxw+jZ44uLkVHn79PJL7NuYekIT2ZeOVPN/8Q" +
           "OvX3Nwtk7tXOgTwjsA7l5WTbe/lBN+ORT7/48qvs7YFvCHkbi68iL+MbRz9u" +
           "n9g+c/D/yLG7PZp7h3xx79k3h+9RngmSirRj553dc5kGc9253qIsaekTOU7d" +
           "lXYivjHAyS6wwQloG0oOaEEn0hTfhlFVyqiF27DbrTW727h47xwb4WJO+0S9" +
           "nyI5CRZNmlHIaf3jWzoBEAd3aaH5yuxzH70iLOoNZp7O9Pji07dCJxaDWdcl" +
           "6/JuLLJ5xJUJR3mnmNhb8AvA8198UAWswDeErLBzbu9JH9xNExfBWj9YXMSu" +
           "D88v/ObnC8eCzpScgBR/2jA0KutFNq5n0nZejZXdYN8Bx84DJds52wrnfNou" +
           "IHmZiVu1fWB51+RNPMHHIB0Sd0aloe0BlJKDVioL7Ws+bb9GcpGRujhl4tSA" +
           "FffeFhq/CukDSJsdaJvLgrbs0/ZbJJcYuQOguccErNpaOrj7HXD3lwXusk/b" +
           "75G8LsANUwPWt3WkeGLr9igd+aCDfLAs5O/4tL2L5I8CuRsssWrituA6XHA7" +
           "HHA7ygL3F5+2vyJ5DxYPgOMZNn4/UvpCCTvIwmUh+8Cn7R9IroiFIpJprIjd" +
           "FhrfWTBVHnagDZcF7WOftk+RfMhIPUBzsmesMQrkxhB48y/qXL+92yfdgcjc" +
           "lvevgLjJVs4tNdauXnrwPX47lb5trouQ2lhS07JzyqxytWnRmMrx14kM0+Sv" +
           "f4FnZuGA2O6UOMp/ik7XMWu04lj8zCwQXwXsFMnJsExvvrUuZ+fkf424+UdS" +
           "/DkypZxf2rPv8Wtfe54nM1WKJs/P4yi1EVIjLtPSOczaoqO5Y1Xv7r/RcKFu" +
           "vbtxNSBpdm7QPNi6C98zDSVMxm+G5l9b/cttLyy9z2+6/gd8HWmxsxoAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "X2D+D2eDFTQ4IQAA");
}
