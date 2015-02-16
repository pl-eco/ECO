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
          1414698450000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "EvE4/l/vrN9f/BcAAA==");
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
          1414698450000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Nm4nTckaAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698450000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "64+nP3/Dk/vP8i8w/wNY2jGtTiEAAA==");
}
