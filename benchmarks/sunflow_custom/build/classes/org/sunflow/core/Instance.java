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
      1170616172000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
