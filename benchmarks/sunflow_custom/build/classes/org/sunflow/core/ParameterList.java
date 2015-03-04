package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point2;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import java.util.Map;
import java.util.HashMap;
public class ParameterList {
    protected final Map<String,Parameter> list;
    private int numVerts;
    private int numFaces;
    private int numFaceVerts;
    private enum ParameterType {
        STRING, INT, BOOL, FLOAT, POINT, VECTOR, TEXCOORD, MATRIX, COLOR;
         
        private ParameterType() {
            
        }
        public static final String
          jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long
          jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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");
    }
    public enum InterpolationType {
        NONE, FACE, VERTEX, FACEVARYING; 
        private InterpolationType() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0YW2wU1/V6ba+f8RMIdcDGxiQyJLskVSJRp4DtrsF0sS3b" +
                                                        "WMVRsoxn73oHZmcmM3ftxYHwkCKnlUKrhiQkSv1RkUZJKURVUdqPSFRVE2hK" +
                                                        "JaKqLZVK2n6loUilatKoSdOec2Z357G7xvSjK83dO/fe87rnPWdusErLZJsM" +
                                                        "XT04o+oixDMitF99MCQOGtwK7Yo+OCqZFo8PqJJlTcBaTO56o/HjT7+VbAqw" +
                                                        "4BRrlTRNF5JQdM0a45auzvJ4lDU6qxGVpyzBmqL7pVkpnBaKGo4qluiNsjoX" +
                                                        "qGDd0RwLYWAhDCyEiYVwn3MKgO7gWjo1gBCSJqzH2ZOsLMqChozsCdbpRWJI" +
                                                        "ppTKohklCQBDNb5PglAEnDHZurzstswFAj+3KXzyhceafljOGqdYo6KNIzsy" +
                                                        "MCGAyBSrT/HUNDetvnicx6dYs8Z5fJybiqQq88T3FGuxlBlNEmmT5y8JF9MG" +
                                                        "N4mmc3P1MspmpmWhm3nxEgpX47m3yoQqzYCsqxxZbQkHcR0ErFWAMTMhyTwH" +
                                                        "UnFA0eKCdfgh8jJ2fxUOAGhViouknidVoUmwwFps3amSNhMeF6aizcDRSj0N" +
                                                        "VARrK4kU79qQ5APSDI8Jttp/btTeglM1dBEIIthK/zHCBFpq82nJpZ8bww+f" +
                                                        "eELbqQWI5ziXVeS/GoDafUBjPMFNrsncBqzfGH1eWvXW0wHG4PBK32H7zJuH" +
                                                        "bm6/t/3CRfvMXUXOjEzv57KIyaenG66sGejZUo5sVBu6paDyPZKT+Y9md3oz" +
                                                        "BnjeqjxG3AzlNi+Mvb336Ov8eoDVDrGgrKvpFNhRs6ynDEXl5g6ucVMSPD7E" +
                                                        "argWH6D9IVYF86iicXt1JJGwuBhiFSotBXV6hytKAAq8oiqYK1pCz80NSSRp" +
                                                        "njEYY3XwsEp41jL7dxcOgvFwUk/xsCRLmqLpYbBdLplyMsxlPWZyQw9HBkbC" +
                                                        "03DLyZRkHrDCVlpLqPpcTE5bQk+FLVMO6+ZMbjks6yYHYzDR1LiJsSGE5mb8" +
                                                        "vwhlUOKmubIyUMYafyhQwYt26mqcmzH5ZLo/cvNs7N1A3jWydyXYZqATytIJ" +
                                                        "IZ2Qh073EHok4KZwgDbDysqI4ArkwNY86O0ARAA4Xt8z/uiufU93lYPJGXMV" +
                                                        "cOl4tAvEzrIVkfUBJ0wMUTCUwVZXf/eRhdAnr26zbTVcOqYXhWYXTs0dmzyy" +
                                                        "OcAC3uCMYsJSLYKTXPnQ2e13ymJ4Gxc++Pjc84d1xz090T4bNQoh0eu7/Aox" +
                                                        "dZnHIY466Deuk87H3jrcHWAVEEogfAoJzB0iU7ufhsf7e3ORFGWpBIETupmS" +
                                                        "VNzKhb9akTT1OWeFLKUBhxbbaFCBPgYpCA/+5MKL51/atCXgjteNrgw4zoXt" +
                                                        "/c2O/idMzmH9D6dGn33uxsIjpHw4sb4YgW4cByAWgDbgxp66+PjV96+d/nXA" +
                                                        "MRjBqgxTmYUQkQEkdztkIFSoEK5Qr917tJQeVxKKNK1yNLzPGjfcf/6vJ5ps" +
                                                        "TamwklP0vbdG4Kx/oZ8dffexf7YTmjIZU5UjunPMvoFWB3OfaUoHkY/MsffW" +
                                                        "vviO9B2IpBC9LGWeU0BiJBqjuw+RSnpovM+3txmHdUbBXoZWVtNbBZDeUMJB" +
                                                        "IlBiOPbVsOfvl/d99vY/QJpdrEo344omqXAlPaXdaxDztcst/zWiTh//8yd0" +
                                                        "HwWOVSRN+eCnwmdebhvYep3gHQtH6I5MYdCC2saBfeD11EeBruDPA6xqijXJ" +
                                                        "2cJpUlLTaGxTUCxYuWoKiivPvjfx21muN+/Ba/ze5SLr9y0nWMIcT+O81nYn" +
                                                        "OtMMGqlGHdXDsyabbugfd1sNHFdkGKPJFgLppLEbh3tsjQqoA9PTqgIGF7So" +
                                                        "RBPABmpLsAosGzPwPzwyHCHfpcHIkmKE7It5XhpwrWUZvPSX4AWnX8ZhKw7b" +
                                                        "cNiO5Af7BoqQL/ORX4GLbcsgv+P2yAcnI2MTka8VMhDwMUD07lkGA9HbY6AO" +
                                                        "5Z/sG9s7NLyjkIvyTHFs5V5s291+zDC8rS1VG1Jde/r4ycX4yCv321mxxVtv" +
                                                        "oa//4Df//mXo1B8vFUnswWxt7xAMID1PKt5NNbPjct947ftviiubvmTT21g6" +
                                                        "TPgB3zn+YdvE1uS+20jAHT7J/Shf233m0o675W8HWHnecwvaAC9Qr9dfa00O" +
                                                        "fQsVLY7XtruT4HJ0ti3ji9dl2boG31dCe0hZAANNyO4wCLe0RJAnbh6FNDeL" +
                                                        "sWokYVsSKKejeKqKpAxByWX+x3f+6OFXF69RsiTUe28pjd+ewTCgnVNmuV8s" +
                                                        "N4faEnvkPAqgIe6tJZIR2AnlRbsbWfze+l8dWVz/JzDVKVatWBB0+8yZIu2R" +
                                                        "C+ZvZ96//t4da89SgVQxLVm2Iv19ZWHb6OkGie16100ZhmGLkQTHbi6ocHOa" +
                                                        "bV+6NAbJVxf033bPKJ9dbKy+c3HPb23Gc31dDTRXibSqunOKax40TJ5QiMUa" +
                                                        "O8MY9HcQjMzPCgRk/CNWM/axQxCkXMfAvrIz96EjgpXDIZweRZNbwsfH09OW" +
                                                        "cPWqzyiLl3/xUeMxOzh4awj6XJEF9cNd/V35A3Wi+5sUG/JKrIZEa+FJwdaV" +
                                                        "/vRBuHqJ8zo7xP8Hfgyez/FBo6QF6u5WOCkfY2OIvqwYRianzwbHU3EfVxfg" +
                                                        "CjpvcQUxeSgVGz9/deEhirKNswr0uTw+kf0W460RnVao1/N9puglxeQPzj1z" +
                                                        "sfPDyVZqvHP34S63d0tGQbm9U7KSsF5Z9fuf/mzVvivlLDDIalVdig9K1ISw" +
                                                        "Gqj+uZWE5i9jbNtOCap+DouUpmxDVspjszJRURyTD738+eW/HL52qZwFwcfQ" +
                                                        "iCUT2nZNsFCpL15uBN0TMPsKQEFYbrChITqSYWQNoCW/mm+OBLuvFG78oOfv" +
                                                        "ofCbEVg4N/v1tBZHtB2+HJA2DPeuHQv+Z1N6EvqJZdxdXvRckdZCPuMzQPcm" +
                                                        "JOzWgWjf+HhsYu9oJAaFxlBff5QKrgUDNssixPMxHJ4iIRZs+8Xx6zSeKFQ1" +
                                                        "Lj9LQ8Zbedhx0L/IPK+Gu+fA6RYc+ql4owIq4+nx/Ba+O21/t4zJ5xZ3DT9x" +
                                                        "86FXKABUwlXOz2cNoMruXPM1QWdJbDlcwZ09nza8UbMhkM1Gnp7WzT2F9/8C" +
                                                        "e2nI9iMWAAA=");
    }
    public ParameterList() { super();
                             list = new HashMap<String,Parameter>(
                                      );
                             numVerts = (numFaces =
                                           (numFaceVerts =
                                              0));
    }
    public void clear(boolean showUnused) {
        if (showUnused) {
            for (Map.Entry<String,Parameter> e
                  :
                  list.
                   entrySet(
                     )) {
                if (!e.
                       getValue(
                         ).
                       checked)
                    UI.
                      printWarning(
                        Module.
                          API,
                        "Unused parameter: %s - %s",
                        e.
                          getKey(
                            ),
                        e.
                          getValue(
                            ));
            }
        }
        list.
          clear(
            );
        numVerts =
          (numFaces =
             (numFaceVerts =
                0));
    }
    public void setFaceCount(int numFaces) {
        this.
          numFaces =
          numFaces;
    }
    public void setVertexCount(int numVerts) {
        this.
          numVerts =
          numVerts;
    }
    public void setFaceVertexCount(int numFaceVerts) {
        this.
          numFaceVerts =
          numFaceVerts;
    }
    public void addString(String name, String value) {
        add(
          name,
          new Parameter(
            value));
    }
    public void addInteger(String name, int value) {
        add(
          name,
          new Parameter(
            value));
    }
    public void addBoolean(String name, boolean value) {
        add(
          name,
          new Parameter(
            value));
    }
    public void addFloat(String name, float value) {
        add(
          name,
          new Parameter(
            value));
    }
    public void addColor(String name, Color value) {
        if (value ==
              null)
            throw new NullPointerException(
              );
        add(
          name,
          new Parameter(
            value));
    }
    public void addIntegerArray(String name,
                                int[] array) {
        if (array ==
              null)
            throw new NullPointerException(
              );
        add(
          name,
          new Parameter(
            array));
    }
    public void addStringArray(String name,
                               String[] array) {
        if (array ==
              null)
            throw new NullPointerException(
              );
        add(
          name,
          new Parameter(
            array));
    }
    public void addFloats(String name, InterpolationType interp,
                          float[] data) {
        if (data ==
              null) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create float parameter %s -- invalid data length",
                name);
            return;
        }
        add(
          name,
          new Parameter(
            ParameterType.
              FLOAT,
            interp,
            data));
    }
    public void addPoints(String name, InterpolationType interp,
                          float[] data) {
        if (data ==
              null ||
              data.
                length %
              3 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create point parameter %s -- invalid data length",
                name);
            return;
        }
        add(
          name,
          new Parameter(
            ParameterType.
              POINT,
            interp,
            data));
    }
    public void addVectors(String name, InterpolationType interp,
                           float[] data) {
        if (data ==
              null ||
              data.
                length %
              3 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create vector parameter %s -- invalid data length",
                name);
            return;
        }
        add(
          name,
          new Parameter(
            ParameterType.
              VECTOR,
            interp,
            data));
    }
    public void addTexCoords(String name,
                             InterpolationType interp,
                             float[] data) {
        if (data ==
              null ||
              data.
                length %
              2 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create texcoord parameter %s -- invalid data length",
                name);
            return;
        }
        add(
          name,
          new Parameter(
            ParameterType.
              TEXCOORD,
            interp,
            data));
    }
    public void addMatrices(String name, InterpolationType interp,
                            float[] data) {
        if (data ==
              null ||
              data.
                length %
              16 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create matrix parameter %s -- invalid data length",
                name);
            return;
        }
        add(
          name,
          new Parameter(
            ParameterType.
              MATRIX,
            interp,
            data));
    }
    private void add(String name, Parameter param) {
        if (name ==
              null)
            UI.
              printError(
                Module.
                  API,
                "Cannot declare parameter with null name");
        else
            if (list.
                  put(
                    name,
                    param) !=
                  null)
                UI.
                  printWarning(
                    Module.
                      API,
                    "Parameter %s was already defined -- overwriting",
                    name);
    }
    public String getString(String name, String defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                STRING,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getStringValue(
                );
        return defaultValue;
    }
    public String[] getStringArray(String name,
                                   String[] defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                STRING,
              InterpolationType.
                NONE,
              -1,
              p))
            return p.
              getStrings(
                );
        return defaultValue;
    }
    public int getInt(String name, int defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                INT,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getIntValue(
                );
        return defaultValue;
    }
    public int[] getIntArray(String name) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                INT,
              InterpolationType.
                NONE,
              -1,
              p))
            return p.
              getInts(
                );
        return null;
    }
    public boolean getBoolean(String name,
                              boolean defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                BOOL,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getBoolValue(
                );
        return defaultValue;
    }
    public float getFloat(String name, float defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                FLOAT,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getFloatValue(
                );
        return defaultValue;
    }
    public Color getColor(String name, Color defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                COLOR,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getColor(
                );
        return defaultValue;
    }
    public Point3 getPoint(String name, Point3 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                POINT,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getPoint(
                );
        return defaultValue;
    }
    public Vector3 getVector(String name,
                             Vector3 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                VECTOR,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getVector(
                );
        return defaultValue;
    }
    public Point2 getTexCoord(String name,
                              Point2 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                TEXCOORD,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getTexCoord(
                );
        return defaultValue;
    }
    public Matrix4 getMatrix(String name,
                             Matrix4 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (isValidParameter(
              name,
              ParameterType.
                MATRIX,
              InterpolationType.
                NONE,
              1,
              p))
            return p.
              getMatrix(
                );
        return defaultValue;
    }
    public FloatParameter getFloatArray(String name) {
        return getFloatParameter(
                 name,
                 ParameterType.
                   FLOAT,
                 list.
                   get(
                     name));
    }
    public FloatParameter getPointArray(String name) {
        return getFloatParameter(
                 name,
                 ParameterType.
                   POINT,
                 list.
                   get(
                     name));
    }
    public FloatParameter getVectorArray(String name) {
        return getFloatParameter(
                 name,
                 ParameterType.
                   VECTOR,
                 list.
                   get(
                     name));
    }
    public FloatParameter getTexCoordArray(String name) {
        return getFloatParameter(
                 name,
                 ParameterType.
                   TEXCOORD,
                 list.
                   get(
                     name));
    }
    public FloatParameter getMatrixArray(String name) {
        return getFloatParameter(
                 name,
                 ParameterType.
                   MATRIX,
                 list.
                   get(
                     name));
    }
    private boolean isValidParameter(String name,
                                     ParameterType type,
                                     InterpolationType interp,
                                     int requestedSize,
                                     Parameter p) {
        if (p ==
              null)
            return false;
        if (p.
              type !=
              type) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requested as a %s - declared as %s",
                name,
                type.
                  name(
                    ).
                  toLowerCase(
                    ),
                p.
                  type.
                  name(
                    ).
                  toLowerCase(
                    ));
            return false;
        }
        if (p.
              interp !=
              interp) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requested as a %s - declared as %s",
                name,
                interp.
                  name(
                    ).
                  toLowerCase(
                    ),
                p.
                  interp.
                  name(
                    ).
                  toLowerCase(
                    ));
            return false;
        }
        if (requestedSize >
              0 &&
              p.
              size(
                ) !=
              requestedSize) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requires %d %s - declared with %d",
                name,
                requestedSize,
                requestedSize ==
                  1
                  ? "value"
                  : "values",
                p.
                  size(
                    ));
            return false;
        }
        p.
          checked =
          true;
        return true;
    }
    private FloatParameter getFloatParameter(String name,
                                             ParameterType type,
                                             Parameter p) {
        if (p ==
              null)
            return null;
        switch (p.
                  interp) {
            case NONE:
                if (!isValidParameter(
                       name,
                       type,
                       p.
                         interp,
                       -1,
                       p))
                    return null;
                break;
            case VERTEX:
                if (!isValidParameter(
                       name,
                       type,
                       p.
                         interp,
                       numVerts,
                       p))
                    return null;
                break;
            case FACE:
                if (!isValidParameter(
                       name,
                       type,
                       p.
                         interp,
                       numFaces,
                       p))
                    return null;
                break;
            case FACEVARYING:
                if (!isValidParameter(
                       name,
                       type,
                       p.
                         interp,
                       numFaceVerts,
                       p))
                    return null;
                break;
            default:
                return null;
        }
        return p.
          getFloats(
            );
    }
    public static final class FloatParameter {
        public final InterpolationType interp;
        public final float[] data;
        public FloatParameter() { this(InterpolationType.
                                         NONE,
                                       null);
        }
        public FloatParameter(float f) { this(
                                           InterpolationType.
                                             NONE,
                                           new float[] { f });
        }
        private FloatParameter(InterpolationType interp,
                               float[] data) {
            super(
              );
            this.
              interp =
              interp;
            this.
              data =
              data;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    protected static final class Parameter {
        private ParameterType type;
        private InterpolationType interp;
        private Object obj;
        private boolean checked;
        private Parameter(String value) {
            super(
              );
            type =
              ParameterType.
                STRING;
            interp =
              InterpolationType.
                NONE;
            obj =
              (new String[] { value });
            checked =
              false;
        }
        private Parameter(int value) { super(
                                         );
                                       type =
                                         ParameterType.
                                           INT;
                                       interp =
                                         InterpolationType.
                                           NONE;
                                       obj =
                                         (new int[] { value });
                                       checked =
                                         false;
        }
        private Parameter(boolean value) {
            super(
              );
            type =
              ParameterType.
                BOOL;
            interp =
              InterpolationType.
                NONE;
            obj =
              value;
            checked =
              false;
        }
        private Parameter(float value) { super(
                                           );
                                         type =
                                           ParameterType.
                                             FLOAT;
                                         interp =
                                           InterpolationType.
                                             NONE;
                                         obj =
                                           (new float[] { value });
                                         checked =
                                           false;
        }
        private Parameter(int[] array) { super(
                                           );
                                         type =
                                           ParameterType.
                                             INT;
                                         interp =
                                           InterpolationType.
                                             NONE;
                                         obj =
                                           array;
                                         checked =
                                           false;
        }
        private Parameter(String[] array) {
            super(
              );
            type =
              ParameterType.
                STRING;
            interp =
              InterpolationType.
                NONE;
            obj =
              array;
            checked =
              false;
        }
        private Parameter(Color c) { super(
                                       );
                                     type =
                                       ParameterType.
                                         COLOR;
                                     interp =
                                       InterpolationType.
                                         NONE;
                                     obj =
                                       c;
                                     checked =
                                       false;
        }
        private Parameter(ParameterType type,
                          InterpolationType interp,
                          float[] data) {
            super(
              );
            this.
              type =
              type;
            this.
              interp =
              interp;
            obj =
              data;
            checked =
              false;
        }
        private int size() { switch (type) {
                                 case STRING:
                                     return ((String[])
                                               obj).
                                              length;
                                 case INT:
                                     return ((int[])
                                               obj).
                                              length;
                                 case BOOL:
                                     return 1;
                                 case FLOAT:
                                     return ((float[])
                                               obj).
                                              length;
                                 case POINT:
                                     return ((float[])
                                               obj).
                                              length /
                                       3;
                                 case VECTOR:
                                     return ((float[])
                                               obj).
                                              length /
                                       3;
                                 case TEXCOORD:
                                     return ((float[])
                                               obj).
                                              length /
                                       2;
                                 case MATRIX:
                                     return ((float[])
                                               obj).
                                              length /
                                       16;
                                 case COLOR:
                                     return 1;
                                 default:
                                     return -1;
                             } }
        protected void check() { checked =
                                   true; }
        public String toString() { return String.
                                     format(
                                       "%s%s[%d]",
                                       interp ==
                                         InterpolationType.
                                           NONE
                                         ? ""
                                         : interp.
                                         name(
                                           ).
                                         toLowerCase(
                                           ) +
                                       " ",
                                       type.
                                         name(
                                           ).
                                         toLowerCase(
                                           ),
                                       size(
                                         ));
        }
        private String getStringValue() {
            return ((String[])
                      obj)[0];
        }
        private boolean getBoolValue() { return (Boolean)
                                                  obj;
        }
        private int getIntValue() { return ((int[])
                                              obj)[0];
        }
        private int[] getInts() { return (int[])
                                           obj;
        }
        private String[] getStrings() { return (String[])
                                                 obj;
        }
        private float getFloatValue() { return ((float[])
                                                  obj)[0];
        }
        private FloatParameter getFloats() {
            return new FloatParameter(
              interp,
              (float[])
                obj);
        }
        private Point3 getPoint() { float[] floats =
                                      (float[])
                                        obj;
                                    return new Point3(
                                      floats[0],
                                      floats[1],
                                      floats[2]);
        }
        private Vector3 getVector() { float[] floats =
                                        (float[])
                                          obj;
                                      return new Vector3(
                                        floats[0],
                                        floats[1],
                                        floats[2]);
        }
        private Point2 getTexCoord() { float[] floats =
                                         (float[])
                                           obj;
                                       return new Point2(
                                         floats[0],
                                         floats[1]);
        }
        private Matrix4 getMatrix() { float[] floats =
                                        (float[])
                                          obj;
                                      return new Matrix4(
                                        floats,
                                        true);
        }
        private Color getColor() { return (Color)
                                            obj;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/eo8BEkld4A4bHh/+r8cCQAZJDAAAA==");
}
