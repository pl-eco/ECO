package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Instance implements RenderObject {
    private Matrix4 o2w;
    private Matrix4 w2o;
    private BoundingBox bounds;
    private Geometry geometry;
    private Shader[] shaders;
    private Modifier[] modifiers;
    public boolean update(ParameterList pl, SunflowAPI api) { String geometryName =
                                                                pl.
                                                                getString(
                                                                  "geometry",
                                                                  null);
                                                              if (geometry ==
                                                                    null ||
                                                                    geometryName !=
                                                                    null) {
                                                                  if (geometryName ==
                                                                        null) {
                                                                      UI.
                                                                        printError(
                                                                          Module.
                                                                            GEOM,
                                                                          "geometry parameter missing - unable to create instance");
                                                                      return false;
                                                                  }
                                                                  geometry =
                                                                    api.
                                                                      lookupGeometry(
                                                                        geometryName);
                                                                  if (geometry ==
                                                                        null) {
                                                                      UI.
                                                                        printError(
                                                                          Module.
                                                                            GEOM,
                                                                          "Geometry \"%s\" was not declared yet - instance is invalid",
                                                                          geometryName);
                                                                      return false;
                                                                  }
                                                              }
                                                              String[] shaderNames =
                                                                pl.
                                                                getStringArray(
                                                                  "shaders",
                                                                  null);
                                                              if (shaderNames !=
                                                                    null) {
                                                                  shaders =
                                                                    (new Shader[shaderNames.
                                                                                  length]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         shaders.
                                                                           length;
                                                                       i++) {
                                                                      shaders[i] =
                                                                        api.
                                                                          lookupShader(
                                                                            shaderNames[i]);
                                                                      if (shaders[i] ==
                                                                            null)
                                                                          UI.
                                                                            printWarning(
                                                                              Module.
                                                                                GEOM,
                                                                              "Shader \"%s\" was not declared yet - ignoring",
                                                                              shaderNames[i]);
                                                                  }
                                                              }
                                                              else {
                                                                  
                                                              }
                                                              String[] modifierNames =
                                                                pl.
                                                                getStringArray(
                                                                  "modifiers",
                                                                  null);
                                                              if (modifierNames !=
                                                                    null) {
                                                                  modifiers =
                                                                    (new Modifier[modifierNames.
                                                                                    length]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         modifiers.
                                                                           length;
                                                                       i++) {
                                                                      modifiers[i] =
                                                                        api.
                                                                          lookupModifier(
                                                                            modifierNames[i]);
                                                                      if (modifiers[i] ==
                                                                            null)
                                                                          UI.
                                                                            printWarning(
                                                                              Module.
                                                                                GEOM,
                                                                              "Modifier \"%s\" was not declared yet - ignoring",
                                                                              modifierNames[i]);
                                                                  }
                                                              }
                                                              Matrix4 transform =
                                                                pl.
                                                                getMatrix(
                                                                  "transform",
                                                                  o2w);
                                                              if (transform !=
                                                                    o2w) {
                                                                  o2w =
                                                                    transform;
                                                                  if (o2w !=
                                                                        null) {
                                                                      w2o =
                                                                        o2w.
                                                                          inverse(
                                                                            );
                                                                      if (w2o ==
                                                                            null) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                GEOM,
                                                                              "Unable to compute transform inverse - determinant is: %g",
                                                                              o2w.
                                                                                determinant(
                                                                                  ));
                                                                          return false;
                                                                      }
                                                                  }
                                                                  else
                                                                      o2w =
                                                                        (w2o =
                                                                           null);
                                                              }
                                                              return true;
    }
    public void updateBounds() { bounds =
                                   geometry.
                                     getWorldBounds(
                                       o2w);
    }
    public boolean hasGeometry(Geometry g) {
        return geometry ==
          g;
    }
    public void removeShader(Shader s) { if (shaders !=
                                               null) {
                                             for (int i =
                                                    0;
                                                  i <
                                                    shaders.
                                                      length;
                                                  i++)
                                                 if (shaders[i] ==
                                                       s)
                                                     shaders[i] =
                                                       null;
                                         }
    }
    public void removeModifier(Modifier m) {
        if (modifiers !=
              null) {
            for (int i =
                   0;
                 i <
                   modifiers.
                     length;
                 i++)
                if (modifiers[i] ==
                      m)
                    modifiers[i] =
                      null;
        }
    }
    public BoundingBox getBounds() { return bounds;
    }
    int getNumPrimitives() { return geometry.
                               getNumPrimitives(
                                 ); }
    void intersect(Ray r, IntersectionState state) {
        Ray localRay =
          r.
          transform(
            w2o);
        state.
          current =
          this;
        geometry.
          intersect(
            localRay,
            state);
        r.
          setMax(
            localRay.
              getMax(
                ));
    }
    public void prepareShadingState(ShadingState state) {
        geometry.
          prepareShadingState(
            state);
        if (state.
              getNormal(
                ) !=
              null &&
              state.
              getGeoNormal(
                ) !=
              null)
            state.
              correctShadingNormal(
                );
        if (state.
              getModifier(
                ) !=
              null)
            state.
              getModifier(
                ).
              modify(
                state);
    }
    public Shader getShader(int i) { if (shaders ==
                                           null ||
                                           i <
                                           0 ||
                                           i >=
                                           shaders.
                                             length)
                                         return null;
                                     return shaders[i];
    }
    public Modifier getModifier(int i) { if (modifiers ==
                                               null ||
                                               i <
                                               0 ||
                                               i >=
                                               modifiers.
                                                 length)
                                             return null;
                                         return modifiers[i];
    }
    public Point3 transformObjectToWorld(Point3 p) {
        return o2w ==
          null
          ? new Point3(
          p)
          : o2w.
          transformP(
            p);
    }
    public Point3 transformWorldToObject(Point3 p) {
        return o2w ==
          null
          ? new Point3(
          p)
          : w2o.
          transformP(
            p);
    }
    public Vector3 transformNormalObjectToWorld(Vector3 n) {
        return o2w ==
          null
          ? new Vector3(
          n)
          : w2o.
          transformTransposeV(
            n);
    }
    public Vector3 transformNormalWorldToObject(Vector3 n) {
        return o2w ==
          null
          ? new Vector3(
          n)
          : o2w.
          transformTransposeV(
            n);
    }
    public Vector3 transformVectorObjectToWorld(Vector3 v) {
        return o2w ==
          null
          ? new Vector3(
          v)
          : o2w.
          transformV(
            v);
    }
    public Vector3 transformVectorWorldToObject(Vector3 v) {
        return o2w ==
          null
          ? new Vector3(
          v)
          : w2o.
          transformV(
            v);
    }
    PrimitiveList getBakingPrimitives() {
        return geometry.
          getBakingPrimitives(
            );
    }
    Geometry getGeometry() { return geometry;
    }
    public Instance() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Vsz+YuyZypn2kTnn85UbGz5v/S/fobFZxyEAAA==");
}
