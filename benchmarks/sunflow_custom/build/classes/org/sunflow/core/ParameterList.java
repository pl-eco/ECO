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
          1414698290000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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");
    }
    public enum InterpolationType {
        NONE, FACE, VERTEX, FACEVARYING; 
        private InterpolationType() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1414698290000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wcR3l8ts/P+pmkwU3s2Lm0ctLepUWtFFyS2OacOFxs" +
                                                        "y3Ys4iq9rPfm7E32dre7c/bFTZqHVLkgNSCalhQV/0ApVUtIKkRU+FEpCNEm" +
                                                        "lCClQkCQSIFfpSESQbRUtBS+79u728fdOQ4SJ+3c7Mx8r/nee/Ymq7RMtsnQ" +
                                                        "1UPTqi7CPCPCB9SHw+KQwa3wrtjDI5Jp8US/KlnWOKzF5a7XGz/65BszTQEW" +
                                                        "nGStkqbpQhKKrlmj3NLVWZ6IsUZnNarylCVYU+yANCtF0kJRIzHFEj0xVucC" +
                                                        "FSwUy7EQARYiwEKEWIj0OqcA6C6upVP9CCFpwnqCPcXKYixoyMieYJ1eJIZk" +
                                                        "SqksmhGSADBU4/sECEXAGZOty8tuy1wg8PObIqe+9XjTD8tZ4yRrVLQxZEcG" +
                                                        "JgQQmWT1KZ6a4qbVm0jwxCRr1jhPjHFTkVRlnvieZC2WMq1JIm3y/CXhYtrg" +
                                                        "JtF0bq5eRtnMtCx0My9eUuFqIvdWmVSlaZB1lSOrLeEAroOAtQowZiYlmedA" +
                                                        "Kg4qWkKwDj9EXsbQl+EAgFaluJjR86QqNAkWWIutO1XSpiNjwlS0aThaqaeB" +
                                                        "imBtJZHiXRuSfFCa5nHBVvvPjdhbcKqGLgJBBFvpP0aYQEttPi259HNz6NGT" +
                                                        "T2o7tQDxnOCyivxXA1C7D2iUJ7nJNZnbgPUbYy9Iq958JsAYHF7pO2yfeePw" +
                                                        "re33t1+8ZJ+5p8iZ4akDXBZx+cxUw9U1/d1bypGNakO3FFS+R3Iy/5HsTk/G" +
                                                        "AM9blceIm+Hc5sXRt/Yee43fCLDaQRaUdTWdAjtqlvWUoajc3ME1bkqCJwZZ" +
                                                        "DdcS/bQ/yKpgHlM0bq8OJ5MWF4OsQqWloE7vcEVJQIFXVAVzRUvqubkhiRma" +
                                                        "ZwzGWB08rBKetcz+3YODYPsieyww94gkS5qi6REwXi6Z8kyEy3p8Cm53JiWZ" +
                                                        "B62InLaEnopYaS2p6nMRy5Qjujmdf5d1k4MRmGhi3MSYEEYzM/7fBDIoYdNc" +
                                                        "WRlc/hq/66vgNTt1NcHNuHwq3Re9dS7+TiDvCtm7EWwz0Aln6YSRTthDJzSI" +
                                                        "Hgi4yf3RRlhZGRFcgRzYmgY9HQSPh+P13WP7du1/pqscTMyYq4BLxqNdIG2W" +
                                                        "rais9zthYZCCnwy2ufq7jy2EP35lm22bkdIxvCg0u3h67vjE0c0BFvAGYxQT" +
                                                        "lmoRnOTKh8qQ3wmL4W1ceP+j8y8c0R139ET3bJQohEQv7/IrxNRlnoC46aDf" +
                                                        "uE66EH/zSCjAKiB0QLgUEpg3RKJ2Pw2Pt/fkIifKUgkCJ3UzJam4lQt3tWLG" +
                                                        "1OecFbKUBhxabKNBBfoYpKA78JOLL1749qYtAXd8bnRlvDEubG9vdvQ/bnIO" +
                                                        "6384PfLc8zcXHiPlw4n1xQiEcOwH3wdtwI09femJa+9dP/PrgGMwglUZpjIL" +
                                                        "ISEDSO51yEBoUCE8oV5De7SUnlCSijSlcjS8Txs3PHjhryebbE2psJJT9P23" +
                                                        "R+Csf66PHXvn8X+2E5oyGVOTI7pzzL6BVgdzr2lKh5CPzPF31774tvQdiJwQ" +
                                                        "rSxlnlMAYiQao7sPk0q6aXzAt7cZh3VGwV6GVlbTWwWQ3lDCQaJQUjj21bDn" +
                                                        "71f2f/rWP0CaXaxKNxOKJqlwJd2l3WsA87PLLf81rE6d+PPHdB8FjlUkLfng" +
                                                        "JyNnX2rr33qD4B0LR+iOTGHQglrGgX3otdSHga7gzwOsapI1ydlCaUJS02hs" +
                                                        "k1AcWLnqCYopz7430dtZrSfvwWv83uUi6/ctJ1jCHE/jvNZ2JzrTDBqpRh3V" +
                                                        "w7Mmm17oH3dbDRxXZBijyRYC6aQxhMN9tkYF1H3pKVUBgwtaVJIJYAO1JVgF" +
                                                        "lokZ+B8aHoqS79JgZEkxQvb5PC8NuNayDF76SvCC0y/isBWHbThsR/IDvf1F" +
                                                        "yJf5yK/AxbZlkN9xZ+SDE9HR8ehXChkI+Bggevctg4HYnTFQh/JP9I7uHRza" +
                                                        "UchFeaY4tnIvtu1uP2YY3taWqgWpjj1z4tRiYvjlB+2s2OKtr9DXf/Cbf/8y" +
                                                        "fPqPl4sk9mC2lncIBpCeJxXvphrZcbmvvfr9N8TVTV+w6W0sHSb8gG+f+KBt" +
                                                        "fOvM/jtIwB0+yf0oX9199vKOe+VvBlh53nMLyn4vUI/XX2tNDn0KFS2O17a7" +
                                                        "k+BydLYt44vXZdm6Bt9XQjtIWQADTdjuKAi3tESQJ272QZqbxVg1nLQtCZTT" +
                                                        "UTxVRVOGoOQy/+O7f/ToK4vXKVkS6r23lcZvz2AY0L4ps9wvlptDbYk9ch4F" +
                                                        "0BD31hLJCOyE8qLdfSx+b/2vji6u/xOY6iSrViwIur3mdJF2yAXzt7Pv3Xj3" +
                                                        "rrXnqECqmJIsW5H+PrKwTfR0f8R2veumDMOwxZgBx24uqHBzmm1fujQGyVcX" +
                                                        "9Nt2jyifW2ysvntxz29txnN9XA00U8m0qrpzimseNEyeVIjFGjvDGPR3CIzM" +
                                                        "zwoEZPwjVjP2scMQpFzHwL6yM/eho4KVwyGcHkOTW8LHx9JTlnD1ps8qi1d+" +
                                                        "8WHjcTs4eGsI+jyRBfXDXftd+UN1IvR1ig15JVZDorXwpGDrSn/qIFw9xHmd" +
                                                        "HeL/Az8Gz2f4oFHSAnVzK5yUj7ExTF9SDCOT02eD46m4j6sLcAWdt7mCuDyY" +
                                                        "io9duLbwCEXZxlkF+lqeGM9+e/HWiE4r1OP5HlP0kuLy++efvdT5wUQrNdq5" +
                                                        "+3CX27slo6Dc3ilZM7BeWfX7n/5s1f6r5SwwwGpVXUoMSNSEsBqo/rk1A81f" +
                                                        "xti23c7Tc1ikBLINWSmPzcpERXFcPvzSZ1f+cuT65XIWBB9DI5ZMaNM1wcKl" +
                                                        "vnC5EYTGYfYlgIKw3GBDQ3Qkw8gaQEt+Nd8cCfZAKdz4Ac/fQ+E3IrBwbvbp" +
                                                        "aS2BaDt8OSBtGO5dOxb8z6b0FPQTy7i7vOi5Iq2FfMZngO5NSNit/bHesbH4" +
                                                        "+N6RaBwKjcHevhgVXAsGbJZFiefjODxNQizY9ovjV2k8WahqXH6Ohoy38rDj" +
                                                        "oH+ReV4Nd8+B0y049FHxRgVUxtPj+S18d9r+ThmXzy/uGnry1iMvUwCohKuc" +
                                                        "n88aQJXdueZrgs6S2HK4gju7P2l4vWZDIJuNPD2tm3sK7/8F443BBRMWAAA=");
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
          1414698290000L;
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
          1414698290000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAALVaDWwUxxWeOxv/xWBj/syf/0H83pEAVYlJCDgYTA242JDG" +
           "EZj13py9eG93s7sHx19CEBUWiihtTEJSajUtIQQI0B+UVFEqqqoNUapWQVGj" +
           "Si20VaWkJahFapO0SUvfm5nbu9u72ztOrSU/386befO9n3nvzZ7P3yJjLJPM" +
           "N3R194Cq2wEaswM71KUBe7dBrcC6zqVdkmnRUJsqWVYPjPXJTZeqPv7s2GC1" +
           "n5T0kgmSpum2ZCu6Zm2ilq7upKFOUpUYXa3SiGWT6s4d0k4pGLUVNdipWHZr" +
           "J7knaalNWjrjEIIAIQgQggxCcGViFiwaS7VopA1XSJptPU6eIL5OUmLICM8m" +
           "jalCDMmUIkJMF9MAJJTh8xZQii2OmaTB0Z3rnKbw8fnBkee2VX+/iFT1kipF" +
           "60Y4MoCwYZNeUhmhkX5qWitDIRrqJeM1SkPd1FQkVdnDcPeSGksZ0CQ7alLH" +
           "SDgYNajJ9kxYrlJG3cyobOumo15YoWoo/jQmrEoDoOvkhK5cw3YcBwUrFABm" +
           "hiWZxpcUDylayCb17hWOji1fggmwtDRC7UHd2apYk2CA1HDfqZI2EOy2TUUb" +
           "gKlj9CjsYpNpWYWirQ1JHpIGaJ9Nat3zujgLZpUzQ+ASm0xyT2OSwEvTXF5K" +
           "8s+tDcuP7tXWan6GOURlFfGXwaI616JNNExNqsmUL6yc1/msNPnNYT8hMHmS" +
           "azKf89q+2w8tqLtylc+ZnmHOxv4dVLb75FP9496d0TZ3WRHCKDN0S0Hnp2jO" +
           "wr9LcFpjBpy8yY5EZAbizCubfv7ogbP0pp9UdJASWVejEYij8bIeMRSVmmuo" +
           "Rk3JpqEOUk61UBvjd5BS+NypaJSPbgyHLWp3kGKVDZXo7BlMFAYRaKJS+Kxo" +
           "YT3+2ZDsQfY5ZhBCpsMvaSDE30DYD/9rk63BzRaEe1CSJU3R9CAEL5VMeTBI" +
           "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" +
           "vCcfh6QmqXutTeoJDvwXtbh+e4IiAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698290000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "HDa8P/1/haIzRBQwAAA=");
}
