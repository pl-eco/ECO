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
          1425482308000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "Ta7stVPwjGGXiMfx/wKZ+2AjDBgAAA==");
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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "haX3+U3XfwB0WArX2RoAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
