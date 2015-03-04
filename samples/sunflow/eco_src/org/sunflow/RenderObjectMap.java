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
    private java.util.Map<String,RenderObjectHandle> renderObjects;
    private boolean rebuildInstanceList;
    private boolean rebuildLightList;
    private enum RenderObjectType {
        UNKNOWN, SHADER, MODIFIER, GEOMETRY, INSTANCE, LIGHT, CAMERA, OPTIONS;
         
        private RenderObjectType() {
            
        }
        final public static String
          jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long
          jlc$SourceLastModified$jl =
          1414698450000L;
        final public static String
          jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1Ye2wcxRkf38tP8CPk7cSJkxASh7s4wjTEUcHx8+LznXt2" +
           "HGInmPHenL3J3u6y\nO2dfnJQCQSQQlRK1oKI2aUTT5gGUqLRNK6EQREkLaa" +
           "VQqSBFBRVVKpWAqqgSTdX+0e+bvb3H3l1w\nauk+787OfN/ve84389JnxGsa" +
           "pFEy/Xy/zkx/59AgNUwW61SoaQ7D0Lj0lrdy8HS/qrlIWYi45Bgn\ntSHJDM" +
           "QopwE5Fgh2tacM0qJryv5JReN+luL+vUpbmt/2UFsBw50nLjQ8esrT5CLeEK" +
           "mlqqpxymVN\n7VZYwuSkLrSXTtNAkstKICSbvD1EbmFqMtGpqSanKjcfIg8T" +
           "d4j4dAl5crIyZAsPgPCATg2aCAjx\ngUEhFjjMMxinsspiHRlxsHJD/kqAnV" +
           "4XLZwNTCrw4wioIxCA1isyWlvaFqiqu8+M3H3w5Fk3qR0l\ntbI6hMwk0ISD" +
           "vFFSk2CJCWaYHbEYi42SepWx2BAzZKrIs0LqKGkw5UmV8qTBzCgzNWUaJzaY" +
           "SZ0Z\nQqY9GCI1EupkJCWuGRkbxWWmxOw3b1yhk6D2gqzalro9OA4KVskAzI" +
           "hTidlLPPtkFTze5FyR0XF1\nP0yApeUJxqe0jCiPSmGANFi+VKg6GRjihqxO" +
           "wlSvlgQpnCwpyRRtrVNpH51k45wscs4btD7BrEph\nCFzCyXznNMEJvLTE4a" +
           "Uc/7Qs+OLIme9fvE/EtifGJAXxV8Gi5Y5FURZnBlMlZi28nvR/J7gr2egi\n" +
           "BCbPd0y25nSsubAj9LfXm6w5S4vMiUzsZRIfl8LHmqIHejXiRhgVumbK6Pw8" +
           "zUU6DKa/tKd0yNoF\nGY740W9/vBS9vOuRc+wTF6kKEp+kKckExFG9pCV0WW" +
           "FGL1OZQTmLBUklU2Od4nuQlMNzCELeGo3E\n4ybjQeJRxJBPE+9gojiwQBNV" +
           "wrOsxjX7Wad8SjyndEJIDfyIF37LifXXiIQTvz9gJtW4os0ETEMK\naMZk5j" +
           "0KUplhGWSA6n6MG52T7YEpLcECVKKqrGqBSRkyVdLujLFp/H9T3FKIr2GmrA" +
           "wLnjNxFYj5\nPk2BRePS6b+8c7C7/8kjVlBgIKc1g4IBQvxpIX6HkNW57+hd" +
           "UlYmhN2G0i0fgYX3Qa5CVatZN7Rn\n+4NHmt0QHPqMB8yDU5tBqTSkbknrzC" +
           "Z0UNQ+CaJq0Qtjh/3XT99rRVWgdN0turr66stXTv6zZr2L\nuIoXRVQVynIV" +
           "shnESpopdqudaVSM/9+fGnj1vSsf3JFNKE5WF+R54UrM02anUwxNYjGofFn2" +
           "pxbX\nuneSkWMu4oHkh4In8EMtWe6UkZev7XbtQ13KQ6Q6rhkJquAnu2BV8S" +
           "lDm8mOiGipQ3KbFTjoSAdA\nUTavH/JtfP+16reExnaFrc3Zw4YYt/K1PhsH" +
           "wwZjMP7Bdwe//exnh8dEEKSjgJNy3ZCnIUNTsOb2\n7BrIVAUiC520eoea0G" +
           "JyXKYTCsNo+m/tmtaff/p0nWV2BUZsr234cgbZ8cXbyCNXHvjXcsGmTMKd\n" +
           "IqtHdpqlzrws5w7DoPsRR+rRPyx7/jf0OBQyKB6mPMtEPSBCNSIM6Rf2XSfo" +
           "nY5vG5E0A+8NJcK6\nyL48Lh08N9mcfOjtXwnU1TR3g8/1A6Rou+VVIXseCG" +
           "0madKYW6fw63wd6QJ0wUJn+vZRcwqY3XUp\nvLtOufQfEDsKYiXYNM2IARUg" +
           "lefq9Gxv+bU33lzw4Ltu4uohVYpGYz1UJACphMhj5hQUn5R+730C\nRt1MBV" +
           "JhFyLQLklbKZXzVgHg1pQwVDc0TNm02dR3tLf98U9UyPrtpFwzYrJKFdhpqK" +
           "qp1ua5onCP\nzWtlMD/Xla41PdhmZOWNT2w4E3onclx4pGSVKbLLOvjMXtxx" +
           "4vrv+YeCTzbdcfXKVGEVh9Ysu3bz\ne9P1vvM/SLhI+Sipk9LN4whVkph5o9" +
           "DrmHZHCQ1m3vf8vsUq6O2ZctboNFWOWGehye4e8Iyz8bnG\nEYXoa1ILv2Xp" +
           "KFzmjEJCdDF7C5Jt+IoPnWJslaC3W9XDw4kHe2UOMNDHHLrk5IQiQyr7TNF7" +
           "pqC+\n7Aj3hyM7w6KsCaKnBVt8u5BsN7+O1YgszjkahDVVdA+yhCGRslMDre" +
           "Q3WByrAxbt1P6P1l5b8bu6\nzitWHZniZE2OPdMzA0F1WpNEOPRRNQadiVVW" +
           "GosK3GlQHTq9q3/+655vtXx8GTc/3QHdslDGqvU4\nuHDOVo3aVh0qYlV87k" +
           "HSi6QPSRAM6Rvq6+jqjhbasUxwGkYyIhj1CxpxTHIgXoyjK+eMeNRGPDZ3\n" +
           "xBUDka5gT7AYZpfgtRvJAyUxu5yYV+Foy5wxT9iYpZvA3NsdGegeju4qxOwW" +
           "vGJI4iUxu52Yxa7T\nNmfMe23M+24CczA8NNwR7uwuxOwRvMQ5Qy2J2ePE/B" +
           "Uc3TpnzIaN2Zw7Zm8o2Ns3XAjYKxiJhmK6\nJGCvE/BXcbRrzoBnbcAHbiL9" +
           "OjsGuqMdhYh9gtNBJA+XROxzIu7B0YE5I37MRnxo7ojLI4PDwUh4\nqBByuW" +
           "D1OJInSkIu11OFstwZWXdYYvL6BSioy0odf8XR/fD9n9c8QX+9xzpONOQfKb" +
           "GL+Hj/m2zt\n1m9+VOQ05EtfX2QFulBe3hlmQFwLZLflp86+eIG/27LFkre+" +
           "dEvhXLh+y8nZpi2vHP0/Ti5NDgs4\nWddPL/2ae0r+rUvcXFi7fMGNR/6i9v" +
           "y9vQrwJA3RS+GItcOvQGKfHor7LTc8btAXP3+Db99D8iw4\nYxpbFxOPKTk7" +
           "qOjMEdbZZ7rmRe8ZOyTMV0kVmZrhLH6XLIpoWap0RwkuyTAbl9buufCPNy6y" +
           "taL5\nrZBN6IE6jMkily05az6n59jA+/ET4vDmmaCmZSvnLVXhJVTe3ZJQ+t" +
           "Yc0+q6TjipEipt2rT57lYd\n24im4uee7oTOxUll9pcLf7b19IkPRSchOB/L" +
           "4YnkOYfRy9KndHxfyEldtvexbrbEyh/fwFPniotB\n8iMkZ5G8CFVCeDISt7" +
           "RD+kMkL+XWBWsYSkqd89bBxrf0BlcVYJ9FBVeX1nWbFLp2YPcXoT/+2/KS\n" +
           "fSVWDaeFeFJRcvvZnGefDl2dLJSqtrpbXfx7lZPqHBygW/pJoPypNekXnLhh" +
           "Ej5eQGjzc+I3SmdE\nvRqXzD8df/rUk9/gXxrAK0udHTOsWl8Yfm7z2/13WS" +
           "UNA5GTllJXwYU32O03zpKc68WN5y9/Gt72\n+glXOgJuyWw2xW/Icjcb24+3" +
           "ZuMMyzGOXrTDvbVtY1ur7qj42ZcKfOxEInacMSQSkn1IxMQDYgfD\nbWJVnk" +
           "biEt0ul8nBtOHuf3lsRero8DPCB15JobOzuL4KUtS6UcmU3JUludm8rpLzr4" +
           "y89pN7bNvk\n3bUUKNRqfRXPbcVOySIf9P8B1oTrMNAYAAA=");
    }
    RenderObjectMap() { super();
                        renderObjects = new java.util.HashMap<String,RenderObjectHandle>(
                                          );
                        rebuildInstanceList = (rebuildLightList =
                                                 false); }
    final boolean has(String name) { return renderObjects.containsKey(
                                                            name);
    }
    final void remove(String name) { RenderObjectHandle obj = renderObjects.
                                       get(
                                         name);
                                     if (obj == null) { UI.printWarning(
                                                             Module.
                                                               API,
                                                             "Unable to remove \"%s\" - object was not defined yet");
                                                        return; }
                                     UI.printDetailed(Module.API,
                                                      "Removing object \"%s\"",
                                                      name);
                                     renderObjects.remove(name);
                                     switch (obj.type) { case SHADER:
                                                             Shader s =
                                                               obj.
                                                               getShader();
                                                             for (java.util.Map.Entry<String,RenderObjectHandle> e
                                                                   :
                                                                   renderObjects.
                                                                    entrySet()) {
                                                                 Instance i =
                                                                   e.
                                                                   getValue().
                                                                   getInstance();
                                                                 if (i !=
                                                                       null) {
                                                                     UI.
                                                                       printWarning(
                                                                         Module.
                                                                           API,
                                                                         "Removing shader \"%s\" from instance \"%s\"",
                                                                         name,
                                                                         e.
                                                                           getKey());
                                                                     i.
                                                                       removeShader(
                                                                         s);
                                                                 }
                                                             }
                                                             break;
                                                         case MODIFIER:
                                                             Modifier m =
                                                               obj.
                                                               getModifier();
                                                             for (java.util.Map.Entry<String,RenderObjectHandle> e
                                                                   :
                                                                   renderObjects.
                                                                    entrySet()) {
                                                                 Instance i =
                                                                   e.
                                                                   getValue().
                                                                   getInstance();
                                                                 if (i !=
                                                                       null) {
                                                                     UI.
                                                                       printWarning(
                                                                         Module.
                                                                           API,
                                                                         "Removing modifier \"%s\" from instance \"%s\"",
                                                                         name,
                                                                         e.
                                                                           getKey());
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
                                                                   getGeometry();
                                                                 for (java.util.Map.Entry<String,RenderObjectHandle> e
                                                                       :
                                                                       renderObjects.
                                                                        entrySet()) {
                                                                     Instance i =
                                                                       e.
                                                                       getValue().
                                                                       getInstance();
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
                                                                               getKey(),
                                                                             name);
                                                                         this.
                                                                           remove(
                                                                             e.
                                                                               getKey());
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
                  typeName(),
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
                this.
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
            for (java.util.Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet()) {
                Instance i =
                  e.
                  getValue().
                  getInstance();
                if (i !=
                      null) {
                    i.
                      updateBounds();
                    if (i.
                          getBounds() ==
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
            for (java.util.Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet()) {
                Instance i =
                  e.
                  getValue().
                  getInstance();
                if (i !=
                      null) {
                    if (i.
                          getBounds() ==
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
            java.util.ArrayList<LightSource> lightList =
              new java.util.ArrayList<LightSource>(
              );
            for (java.util.Map.Entry<String,RenderObjectHandle> e
                  :
                  renderObjects.
                   entrySet()) {
                LightSource light =
                  e.
                  getValue().
                  getLight();
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
                                      size()]));
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
          getGeometry();
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
          getInstance();
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
          getCamera();
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
          getOptions();
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
          getShader();
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
          getModifier();
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
          getLight();
    }
    final private static class RenderObjectHandle {
        final private RenderObject obj;
        final private RenderObjectType type;
        private RenderObjectHandle(Shader shader) {
            super();
            obj =
              shader;
            type =
              RenderObjectType.
                SHADER;
        }
        private RenderObjectHandle(Modifier modifier) {
            super();
            obj =
              modifier;
            type =
              RenderObjectType.
                MODIFIER;
        }
        private RenderObjectHandle(Tesselatable tesselatable) {
            super();
            obj =
              new Geometry(
                tesselatable);
            type =
              RenderObjectType.
                GEOMETRY;
        }
        private RenderObjectHandle(PrimitiveList prims) {
            super();
            obj =
              new Geometry(
                prims);
            type =
              RenderObjectType.
                GEOMETRY;
        }
        private RenderObjectHandle(Instance instance) {
            super();
            obj =
              instance;
            type =
              RenderObjectType.
                INSTANCE;
        }
        private RenderObjectHandle(LightSource light) {
            super();
            obj =
              light;
            type =
              RenderObjectType.
                LIGHT;
        }
        private RenderObjectHandle(Camera camera) {
            super();
            obj =
              camera;
            type =
              RenderObjectType.
                CAMERA;
        }
        private RenderObjectHandle(Options options) {
            super();
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
                                      name().
                                      toLowerCase();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698450000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK1ZDWwT1x1/tvOdtPmCkEA+SEhJIZm9CTo2gkZpRkLAIWkS" +
           "Uhpg6eX87Fw4313v\nnhMnZYyqU6Gt1o52k1pppWhCg7K2dOomNqnrqArrB5" +
           "oEm9pJ1aCrkLZOHZOmbS1VW23/996dzz7b\nl9SeJb8733v/j9/7f9z///zc" +
           "dVRo6KhRNPxkTsOGv2dkSNANHOqRBcMYhUcT4oXC0qGTOxTVizxB\n5JVCBF" +
           "UGRSMQEogQkEKB/m92x3XUqanyXERWiR/HiX9avs3ktz14WxrDu46drbn/RE" +
           "GLFxUGUaWg\nKCoRiKQqW2UcNQiqCk4LM0IgRiQ5EJQM0h1EN2ElFu1RFYMI" +
           "CjHuRQeRL4iKNJHyJKg1aAkPgPCA\nJuhCNMDEB4aYWOBQq2MiSAoObUmIA8" +
           "quVEpQ26QbTl8NTEro5BjAYRoA6pUJ1BxtGlTNd2rsqweO\nP+tDleOoUlJG" +
           "KDMRkBCQN44qojg6iXVjSyiEQ+OoWsE4NIJ1SZCleSZ1HNUYUkQRSEzHxjA2" +
           "VHmG\nLqwxYhrWmUzrYRBViBSTHhOJqif2KCxhOWT9KgzLQgRg19mwOdxe+h" +
           "wAlkmgmB4WRGyRFOyXFLB4\ni5MigbF9BywA0uIoJlNqQlSBIsADVMNtKQtK" +
           "JDBCdEmJwNJCNQZSCFqelSnda00Q9wsRPEFQvXPd\nEJ+CVaVsIygJQUudyx" +
           "gnsNJyh5WS7NNZ99GRUz965Xbm2wUhLMpU/zIganYQDeMw1rEiYk54I+b/\n" +
           "Qf/dsUYvQrB4qWMxX7PllrO7gh/8poWvWZFhzeDkNBbJhLjzaMvwfX0q8lE1" +
           "SjTVkKjxU5CzcBgy\nZ7rjGkRtXYIjnfRbk+eGf3v3odP4Qy8q60dFoirHou" +
           "BH1aIa1SQZ631YwbpAcKgflWIl1MPm+1Ex\n3AfB5fnTwXDYwKQfFcjsUZHK" +
           "fsMWhYEF3aJSuJeUsGrdawKZYvdxDSHUAF/UDN9PEf+wK0F+f8CI\nKWFZnQ" +
           "0YuhhQ9Uji9zBIxTrfkAFB81O/0QjaHphSozggiIIiKWogIkGkiuqXQniGXr" +
           "8QtzjVr2bW\n46EJzxm4Mvj8NlUGognx5LW3Dmzd8dAR7hTUkU1kAACE+E0h" +
           "foeQ9uTf2wQlJGPk8TBxS6h8biXY\n4/0QrZDXKtaM7Nt+z5E2H7iHNlsAG1" +
           "QCS9sAlqnUVlHtsUO6n2U/Efyq/sd7DvtvnNzM/SqQPfNm\npC6/9PzF4/+q" +
           "WOtF3sxpkYKFxFxG2QzRXJpId+3OQMrE/x8PD7z0zsUrt9ohRVB7WqSnU9JI" +
           "bXOa\nRVdFHILcZ7M/0VDpuwuNHfWiAgh/SHlMf8gmzU4ZKRHbbWU/iqU4iM" +
           "rDqh4VZDplpawyMqWrs/YT\n5i9V7L4WjFNFXbgNvv81fZpd6exSjY513L+o" +
           "tR0oWHa98UDRl//4cvkFti1WIq5MetWNYMLDutp2\nllEdY3h+5cmhJ354/f" +
           "Ae5incVTwEFWu6NAOBHAea1TYNBLQMDkgt2b5LiaohKSwJkzKmLvdZ5S1f\n" +
           "+cXfH63itpHhiWXaroUZ2M8b7kCHLn7r42bGxiPSF4qNw17G4dTanLfoujBH" +
           "9Yjf//ump14XnoZ8\nBznGkOYxSxseDo1t+TKCliUHm6jq2D8yJUCEsY0OsF" +
           "Vr2einlmC0iM2to0MbyO7KEhsZXu8T4oHT\nkbbYvW/+iqEqF5LrhGQ7QaR3" +
           "c9egwyq6+8uc4b1NMKZg3fpzO/dWyec+BY7jwFGE16oxqFMEKVY2\nVxcWv/" +
           "vqa3X3XPYhby8qk1Uh1CuwAEGl4JnYmIL0FNc2386cr2q2xPLJOGKbsNzcAP" +
           "ajMc1tPQXc\na/k1zW3psNqxpQ5zNKSZY4D5hmmQb7gY5A46dLOp9XTYxLXc" +
           "kDOYUhNMaa5gmtLAjIJxsAxFLbg6\nY9PvAmiADr02oL58Ad1sAro5V0DNaY" +
           "CGdCkKBcEMi3zGZ9QF0W463GkjGs4XUa2JqPb/52/WW4Cx\nmHABI9Jhrw1m" +
           "X75g6k0w9bmCaUwDE5QiU2REjekmnmkXPAodIjaeqXzxNJl4mnLFk56be6BO" +
           "0nmR\nFXOBMkcH3YZi5AulzYTSliuU+jQogxrL+4zDIRcs36XDt20sBxeLJZ" +
           "70ywsvkDXZa7he2sDZ5c/E\nZNep4FuDT7OXVNbqLUP/4uAz/8quYzd+R64y" +
           "PnYZRalb4+n1MTS9Nu3X3pmpLnrxmagXFY+jKtFs\ny8cEOUaLlXHoIg2rV4" +
           "fWPWU+tSPkpXJ3okxsdJZwSWKdBZxdl8M9XU3vKxw1Wx3d7Q74fsadhF+T\n" +
           "ncSD2M2jjKSdjR28wvISECgpAusI18QJ8qmT09lfH8nFP/M82y0eW8gtHkpV" +
           "uAu+n5sKf55F4Scz\nKEzvH7e0ZXtiqdu12LZl1NzIJO2fctE+nq6Fz9aCoC" +
           "KDnXfEUwNZR03ZOnJ2mnB49z8rHhTO7+P9\nTU1ql7tViUX/Ovca7tj0vfcz" +
           "NGhF5omKLbCUyktpqgbYSYXtzw8/+9Oz5HLnRi5vbfZYdBKu3Xh8\nvmXjmU" +
           "dyaKVaHDvgZF09s+JO35T0hpcdpvDwSDuESSXqTg2KMtAnpiujKaGxMjV/gk" +
           "k9HWb+7Fh0\n/vSaect0rgyVB4WKCdZp5WEtq0teNsKvW4b6mZifuSTZs3R4" +
           "ASwb00LQ7YA165NPK60ih+K89mDb\nr9/Y9cxhbkiXpJpCNSF+570/7/c99u" +
           "okp3NmTsfio80n/vLSteEl3Pn48diqtBOqZBp+RMbAVGrU\n/VvdJLDV5ztb" +
           "nzs4fJVpROl+Au3epKrKWFDsyDzzhV6dp1NN3wom7zRN37lo0ycb5nWXuTfp" +
           "cJ7w\nE9Od4AyWF1Sxtodmfz8/D7ThXMgHDq0EAiacQE5w/uAy9zYdLhFUGs" +
           "GE96AL9ag2rMv5wFoNcNaZ\nsNblBOs9l7n36fAngsoBltXLLdzt2dCu5Att" +
           "gwltQ07QPnSZu06HDzi0PqxCNtLnskNLWcGg/S1f\naN0mtO6coH3sMvcJHf" +
           "7NoVm5f+GeyYb2n3yhbTahbc4FmsfnMldIBwRpA6Cx/mhR7VMCmseTbwrp\n" +
           "MaH15ASt0mWumg4VPIXwVmmhVsqGdVM+sNoBTp8Jqy8nWCtc5proUE9QGcAy" +
           "26YF+yobWMOiGyZo\nHNKP1y1JK1zKW1ovpP1Lx/9ZEoPv3rf3o+Dbn7Cz5M" +
           "S/P+VBVBKOyXJyg5F0X6TpOCyxnSvn7YbG\nduJWiMgkPeCFbd5RLT0dfFEn" +
           "bSP0CL3t0jK8E7naqfUyhbAqpZZh/3BahWOM/8c5Ie5+fs/K+COj\n32fVaK" +
           "EoC/PzlE1ZEBXzw+5E8dmalZvF6xJ68czYyy983ao/2FnnEvOEO83j1vNZF1" +
           "tCwZv5gHlr\nVCPsSHj+l8t+vunksatedsb9P5E0YfKYHgAA");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698450000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAL1aDXAU1R1/d5fvBBIChPAhITEUgXAHaVAgVol8Bg5IkxA0" +
       "Ys/N3rtkYW933X13\nOWIGsVqg2NpirZVOUbQ4KH5RtNIylqqotVgdcabUsR" +
       "XawbFWpTNtHaUfY/t/b9/d7u19JHo3vZn3\nbnff//3f+/2/3v+93ccuoEJD" +
       "R9NEw0u2adjwLuvqEHQDB5fJgmF0w6OA+FJhacehtYrqRi4/cktB\ngir9ou" +
       "ELCkTwSUFf+/LWmI7maqq8rV9WiRfHiHeLvJDzW+NfmMJw033Hqm89WFDnRo" +
       "V+VCkoikoE\nIqnKChmHDYKq/FuEqOCLEEn2+SWDtPrRGKxEwstUxSCCQoyb" +
       "0Hbk8aMiTaQ8Car3xwf3weA+TdCF\nsI8N7+tgwwKH8TomgqTgYFtiOOjZlN" +
       "wTps37daZSA5MS2tgDcNgMAPWMBGoTbQpUzfNwz+XDBx7x\noMpeVCkpXZSZ" +
       "CEgIjNeLKsI43Id1oy0YxMFeNE7BONiFdUmQpSE2ai+qNqR+RSARHRud2FDl" +
       "KCWs\nNiIa1tmY8Yd+VCFSTHpEJKqekFFIwnIwflcYkoV+gF1jwTbhrqTPAW" +
       "CZBBPTQ4KI410KtkoKaLzO\n2SOBsXEtEEDX4jAmA2piqAJFgAeo2tSlLCj9" +
       "vi6iS0o/kBaqERiFoCkZmVJZa4K4VejHAYJqnXQd\nZhNQlTJB0C4ETXSSMU" +
       "6gpSkOLdn0M7fmk90P/+jEUmbbBUEsynT+ZdBpuqNTJw5hHSsiNjtejHjv\n" +
       "br8uMs2NEBBPdBCbNG0zj230v//LOpNmahqaDX1bsEgC4vq9dZ03r1KRh06j" +
       "RFMNiSo/CTlzhw7e\n0hrTwGtrEhxpozfe+Fzny9ftOIw/dKOydlQkqnIkDH" +
       "Y0TlTDmiRjfRVWsC4QHGxHpVgJLmPt7agY\nrv1g8ubTDaGQgUk7KpDZoyKV" +
       "3YOIQsCCiqgUriUlpMavNYEMsOuYhhAqgoLKoUxC5o/9E+T1+oyI\nEpLVQZ" +
       "+hiz5V70/cd8KoWDcFsk7QvNRuNILW+AbUMPYJoqBIiurrl8BTRXVeEEfp/+" +
       "fiFqPzqx50\nuWjAczquDDa/WpWhU0A8dP7U8Iq139xtGgU1ZI6MoKkwiJcP" +
       "4nUMglwuxnsCHcxUCQh0K7gmBLGK\n2V03rLlxd4MHbEEbLABpUNIGwMBnsE" +
       "JUl1n+285CnQhGVPvg9bu8Fw9dbRqRL3OYTdu7/I3HXz3w\nj4o5buROHwMp" +
       "MojCZZRNBw2cidjW6PSadPz/umfdU2defecyy38Iakxx69Se1C0bnDrQVREH" +
       "IdBZ\n7A9OrvRsQj173agAfB3iG5s/hI7pzjGS3LM1HuoolmI/Kg+peliQaV" +
       "M8PpWRAV0dtJ4w46hi1+NB\nOVRB7GIGN2D2T1snarSuMY2JatuBgoXSi7cV" +
       "zf/ds+UvMbHEo26lbV3rwsT04XGWsXTrGMPzd+7t\n+N73L+y6nlmKaSooBp" +
       "RfsijBZ2WwOaq/xo1KWA1KIUnokzE1tP9Uzlzw04/urDI1IsOTuEKbRmZg\n" +
       "PZ98Ddrx6tc+nc7YuES6Zlizt8hMEOMtzm26Lmyj84jd+uYl+34l7IeQBmHE" +
       "kIYwiwyIA6KT8jJx\nz2b1PEfbfFo1AO+mDBafZoUOiMOH+xsiN/3652zW5Y" +
       "J9qbdLH5y11VQ4rS6l0p3kdNrVgjEAdC3P\nrd9cJT/3b+DYCxxFWBmNDTq4" +
       "fSxJd5y6sPjt50/W3Hjag9wrUZmsCsGVAjN7VAr2ho0BiDAx7eql\nzKSqBk" +
       "tozSAjJoQpXAAx250HJjc7s9evpOu75TCBvqaH/ac27GcCyOjvaZY3B5+hEx" +
       "vvu/gaOcv4\nWI5He9fHUsMn5ERW30VnouOKjtwfdqPiXlQl8qytR5Aj1Lx7" +
       "Ickw4qkcZHZJ7ckJgxlZWxOBZZrT\n6W3DOl3eCttwTanpdYXDyyuotJdAqe" +
       "VeXuv0chdiF1exLo2snsXDN0HFmi5FBZrJoTG6bS0wQGVT\nbRl1V6TPILak" +
       "Y+HSK5qOrt58kgXlUsj1BGO9NVvIsOmVC8Q8J7PenTz1k90v4fMT7zGXiWSD" +
       "YQkx\n7+rsd8/sD6Nz5399P5tLQZ9gsGmUgTQNSknQjMzJNeNlOtIYU6b/hR" +
       "+C8hktVJbsAVv6xye5n5el\n7ppmWvokEKDlS9BMH64FKVY7pRgQJ23WNvag" +
       "b202gdaPIKGAOOU3x89sertHY5ZcGZUgR8LBbp7H\nJ4c1a7VuTcrt08owIL" +
       "7bcMPZHxac28eSNlNcdN7NEBLp/0Lu5hUJN3fTOGNDxGfBIu8Hr++bcLu+\n" +
       "W2V6KGQ2kWoPMzOgtTMKiHe+f81nO/xbZ7mpH5RR1xF0yPYgnfRm2u7YGTR2" +
       "w9Vy6AUuNdbsDWk7\nMxluGtWJp4klm6B5mXjTHaBzZXd6K4TKQaxfo0YUhr" +
       "U+2X/LIppmb2UWN/aLWpxGfwS51sZNr4qZ\nHo04XtN/CSphCmhpadbSEJn7" +
       "GNbQRatrEyx7RsGyJcHSmyWXbLTfrxaUoIyTVgUEMaeMcWxuXrSo\nmbsK3Z" +
       "J7pSAP5svfXNl3OKQcDrpZwsN2gm02wyplT2yRx6NqBt1z2Db3nFPjBs2gPj" +
       "HGNkj78uEj\naypKHrhjJ+PPzbTUtn/h98VRQV9vT6LL2cQXXAG/xQR15y/D" +
       "X9LcckXTly+f1wJsq7tXt3d5k8Iy\nncCAM1Zvh31UqugoYL4moGoWacZaKl" +
       "2hRML2RoBU0LmibbmZEtJ6Aa2uNhXWknG5X5y8EF0GZTJn\nOznDQjRIq6WE" +
       "Lu19EclasWnQAiOotZ/j6FIY1BBlGfH5nQ2/eGXj/bvSLQ/JhzX2XgHxlnN/" +
       "3Or5\nzvN9Zj9n0uAg3jv94HtPne+cYKae5sHBpSl7d3sf8/CA+XOlRtOw+m" +
       "wjMOoX59Y/tr3zLJ9RdfIW\nmGrmz9tO4llXfvtPaXZvxX2qKmNBcSgq9jkV" +
       "NRPKFK6oKRkU9Q2uqCquKL/UP0D8PBvf7pjAziwT\niKVNPQpDkiLIyREBxH" +
       "dJppMOJrpd1/6tYqfw4g1unmPvIJCWqto8GUexbGM1nnJK2pmuY2c7Voq3\n" +
       "55FHj5HTc5eYSsiSpjg7zllyYKhuyZN3fIH9aJ0Dm5P1uOjUr3oGpFdYNOIZ" +
       "Y8qxVXKnVsc6A/OJ\n6Ep3UrY4I6H7MirlBq7/uB2k7glptcexs3HxHX+mxY" +
       "T13JdlO7SfVvcQ5Bkww/d2y3h+MJL1JuyD\n3tyVCmc2hzM7v3AeygLnEVo9" +
       "SFCRjsNq1NyCD/Fk8BaIplFVCloIf5wLwlVQAhxhYNQIPXzvlXX1\nh4bp9j" +
       "VcVHXsZUaLCdZZSOZkNXayLvO/raOdjf90Fjk9S6ufgJwiGixQ2KH5o7nIZR" +
       "aUYS6X4S+q\n+ZoU+F0iVjDr/2IWXK/Q6nnIBExcrBN99KgF7oVcwHmhHObg" +
       "Do8anJtxdI+o9EmpqAcEeiRA29/I\nAvsMrV4DL9YixAH39VzgNkN5gsN9Iu" +
       "9wJ6fAXccOjDjgs1kAv0ur36cF/IdcAC+GcpQDPpp3wGmc\nOp6GsJMtSvZR" +
       "FtR/p9Vf0qL+IBfU9KTiGY76mbyjviQFdTc2DCxDYgy7Qsb/X5lBu9hkPk0L" +
       "+mKu\ntn2cgz7+f7DteG7AQJVkATyWVoXpALuKcgV8ggM+kXfA01IAs8y0S4" +
       "3oHHNNFszTaDU+LeYJucbr\nFzjmF/KOOTVeL4MlWmcvhFyNWeDOoVV9WrgN" +
       "ucCdD+VlDvflvMOtTYG7QWO5NcPUnAXvIlr50uKd\nn6t6T3G8p0aNdzRZpm" +
       "tpFjh0Q+76CkFjZVXdGtFWYRUSM31bZt9PomCwr8oV9mkO+3R+Ya/PAruT\n" +
       "VmsTsOMBbeSQZ8H25wJ7DpS3OOy38gt7cxbYN9LqOoIqTNimi48UAizIvblA" +
       "boJyjkM+l1/IW7JA\npgcZrn6CxpiQuZuPGAcs0AO56vk9Dvq9/IKOZQE9TK" +
       "tIQs9m6p1Zz7Z2Bjmaq0df4JAv5BfyziyQ\n99DqtoRHx9PvzB6dRMFg354r" +
       "7I857I/zC/vuLLDvpdVe2CWasFmiMqo8xoJ912hhxwiqdBwp0zfb\ntSkfeJ" +
       "kfJYn+t2/e/In/t/9kXyYkPhwq96OSUESW7S8fbddFmo5DEpNYufkqUmNAHw" +
       "CMNjwEFfMr\nOkXXAZPoIKzLQEQvH0r3WsKcd/KRIIVwacb3WOsi5udxAfHa" +
       "x6+fEbuj+7vm2ydRFoaGKJsyPyo2\nP51gXD0pL9zs3OK83kBHnux59onF8V" +
       "NG9o59Av9eIsXSFpitWbSko7r0Hy6sCGuEfWow9LNJT195\n6L6zbvbFxP8A" +
       "oo9bfdMoAAA=");
}
