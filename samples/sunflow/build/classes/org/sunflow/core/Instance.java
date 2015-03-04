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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "kO0hAAA=");
}
