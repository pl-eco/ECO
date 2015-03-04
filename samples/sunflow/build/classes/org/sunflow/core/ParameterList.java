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
          1425485134000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "tW9WbfZkryjHM4tdLZ7z/wtgVdSrjBgAAA==");
    }
    public enum InterpolationType {
        NONE, FACE, VERTEX, FACEVARYING; 
        private InterpolationType() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wUx3l8Z5+f8RMIdcDG5khkSO5IqkSiTgHbtcH0sC3b" +
                                                        "WMVRcqz35uyFvd3N7px9OBAeUuS0UmjVkIREqX9UpFFSClFVlPZHJKqqCTSl" +
                                                        "ElHVlkolbX+loUilatKoSdN+37d3t4+7M6ZST9q52Zn5XvO998wNVmGZbJOh" +
                                                        "qwenVV1EeEZE9qsPRsRBg1uRXbEHRyTT4ok+VbKscViLy51vNHz86bdmGgMs" +
                                                        "NMlaJE3ThSQUXbNGuaWrszwRYw3Oar/KU5ZgjbH90qwUTQtFjcYUS3THWK0L" +
                                                        "VLBwLMdCFFiIAgtRYiHa45wCoDu4lk71IYSkCetx9iQri7GQISN7gnV4kRiS" +
                                                        "KaWyaEZIAsBQhe8TIBQBZ0y2Li+7LXOBwM9tip584bHGHwZZwyRrULQxZEcG" +
                                                        "JgQQmWR1KZ6a4qbVk0jwxCRr0jhPjHFTkVRlnvieZM2WMq1JIm3y/CXhYtrg" +
                                                        "JtF0bq5ORtnMtCx0My9eUuFqIvdWkVSlaZB1lSOrLeEAroOANQowZiYlmedA" +
                                                        "yg8oWkKwdj9EXsbwV+EAgFamuJjR86TKNQkWWLOtO1XSpqNjwlS0aThaoaeB" +
                                                        "imCtJZHiXRuSfECa5nHBVvvPjdhbcKqaLgJBBFvpP0aYQEutPi259HNj6OET" +
                                                        "T2g7tQDxnOCyivxXAVCbD2iUJ7nJNZnbgHUbY89Lq956OsAYHF7pO2yfefPQ" +
                                                        "ze33tl24aJ+5q8iZ4an9XBZx+fRU/ZU1fV1bgshGlaFbCirfIzmZ/0h2pztj" +
                                                        "gOetymPEzUhu88Lo23uPvs6vB1jNIAvJuppOgR01yXrKUFRu7uAaNyXBE4Os" +
                                                        "mmuJPtofZJUwjykat1eHk0mLi0FWrtJSSKd3uKIkoMArqoS5oiX13NyQxAzN" +
                                                        "MwZjrBYeVgHPWmb/7sJBsNHojJ7iUUmWNEXTo2C7XDLlmSiX9aglpQwVtGal" +
                                                        "taSqz0UtU47q5nT+XdZNDpo30a64iYEggrZl/F+wZlCWxrmyMrjmNX4nV8E/" +
                                                        "dupqgptx+WS6t//m2fi7gbzRZ29BsM1AJ5KlE0E6EQ+d8CD6GuAmR0drYGVl" +
                                                        "RHAFcmDrFDRyAHwbjtd1jT26a9/TnUEwJmOuHK4Tj3aCjFm2+mW9zwkAgxTm" +
                                                        "ZLDC1d99ZCHyyavbbCuMlo7WRaHZhVNzxyaObA6wgDfsopiwVIPgJFc+KIb9" +
                                                        "7lYMb8PCBx+fe/6w7jieJ45n40EhJPpzp18hpi7zBERIB/3GddL5+FuHwwFW" +
                                                        "DkECAqOQwJAh5rT5aXj8ujsXI1GWChA4qZspScWtXGCrETOmPueskKXU49Bs" +
                                                        "Gw0q0McghdeBn1x48fxLm7YE3JG4wZXbxriw/brJ0f+4yTms/+HUyLPP3Vh4" +
                                                        "hJQPJ9YXIxDGsQ+8HLQBN/bUxcevvn/t9K8DjsEIVmmYyiw4fwaQ3O2QgSCg" +
                                                        "QiBCvYb3aCk9oSQVaUrlaHifNWy4//xfTzTamlJhJafoe2+NwFn/Qi87+u5j" +
                                                        "/2wjNGUyJiFHdOeYfQMtDuYe05QOIh+ZY++tffEd6TsQIyEuWco8p1DDSDRG" +
                                                        "dx8hlXTReJ9vbzMO64yCvQytrKa3ciC9oYSD9EPx4NhX/Z6/X9732dv/AGl2" +
                                                        "sUrdTCiapMKVdJV2rwHMxC63/NewOnX8z5/QfRQ4VpEE5IOfjJ55ubVv63WC" +
                                                        "dywcodszhUELqhYH9oHXUx8FOkM/D7DKSdYoZ0uiCUlNo7FNQhlg5eokKJs8" +
                                                        "+96Ubuev7rwHr/F7l4us37ecYAlzPI3zGtud6EwTaKQKdVQHz5psIqF/3G0x" +
                                                        "cFyRYYwmWwikg8YwDvfYGhVQ4aWnVAUMLmRR8SWADdSWYOVYEGbgf2h4qJ98" +
                                                        "lwYjS4oRsi/meanHteZl8NJbghecfhmHrThsw2E7kh/o6StCvsxHfgUuti6D" +
                                                        "/I7bIx+a6B8d7/9aIQMBHwNE755lMBC7PQZqUf6JntG9g0M7CrkIZopjC3qx" +
                                                        "bXf7McPwtrZU1UcV6+njJxcTw6/cb2fFZm8lhb7+g9/8+5eRU3+8VCSxh7JV" +
                                                        "u0MwgPQ8qXg3VcOOy33jte+/Ka5s+pJNb2PpMOEHfOf4h63jW2f23UYCbvdJ" +
                                                        "7kf52u4zl3bcLX87wIJ5zy0o8L1A3V5/rTE5dCRUtDhe2+ZOgsvR2baML16X" +
                                                        "ZesafF8JjR9lAQw0Ebt3INzSEkGeuHkU0twsxqrhpG1JoJz24qmqP2UISi7z" +
                                                        "P77zRw+/uniNkiWh3ntLafz2DIYBjZoyy/1iuTnUltgj51EADXFvLZGMwE4o" +
                                                        "L9p9xuL31v/qyOL6P4GpTrIqxYKg22NOF2l8XDB/O/P+9ffuWHuWCqTyKcmy" +
                                                        "FenvGAsbQk+fR2zXuW7KMAxbjBlw7KaCCjen2balS2OQfHVBZ213g/LZxYaq" +
                                                        "Oxf3/NZmPNexVUPblEyrqjunuOYhw+RJhVistjOMQX8Hwcj8rEBAxj9iNWMf" +
                                                        "OwRBynUM7Cs7cx86IlgQDuH0KJrcEj4+lp6yhKsLfUZZvPyLjxqO2cHBW0PQ" +
                                                        "h4gsqB/u6u+CD9SK8DcpNuSVWAWJ1sKTgq0r/VGDcHUT57V2iP8P/Bg8n+OD" +
                                                        "RkkL1LetcFI+xsYIfTMxjExOn/WOp+I+ri7AFXTc4gri8mAqPnb+6sJDFGUb" +
                                                        "ZhXoYHliPPuVxVsjOq1Qt+fLS9FLissfnHvmYseHEy3UUufuw11u75aMgnJ7" +
                                                        "p2TNwHpF5e9/+rNV+64EWWCA1ai6lBiQqAlh1VD9c2sGmr+MsW07Jai6OSxS" +
                                                        "GrMNWSmPzcpERXFcPvTy55f/cvjapSALgY+hEUsmNOTQ8UdKfctyIwiPw+wr" +
                                                        "AAVhud6GhuhIhpE1gOb8ar45Euy+UrjxU52/h8KvQWDh3OzV01oC0bb7ckDa" +
                                                        "MNy7diz4n03pSegnlnF3edFzRVoz+YzPAN2bkLBb+mI9Y2Px8b0j/XEoNAZ7" +
                                                        "emNUcC0YsFnWTzwfw+EpEmLBtl8cv07jiUJV4/KzNGS8lYcdB/2LzPNquHsO" +
                                                        "nG7BoZeKNyqgMp4ez2/hu9P2F8m4fG5x19ATNx96hQJABVzl/HzWACrtzjVf" +
                                                        "E3SUxJbDFdrZ9Wn9G9UbAtls5Olp3dxTeP8vuKlrL/0VAAA=");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "K71ItvChNXQ3aA9VlPULWBcxHwsTAAA=");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAALVaC2wUxxmeOxu/YrAxL/PyG4R53JEAVYnJAxwMpgZc7JDG" +
           "EdjrvTl78d7uZm8PHw8nDqLCQhGljUlISq2mJYQAAfpASRWloqraEKVqFRQ1" +
           "qtRCW1VKWoJapDZJm7T0/2fm9u727vaOU2vJv2/nn/nn+x/z///s+dxNMils" +
           "kiWGru4ZUHXLR6OWb5e6ymftMWjYt6ljVadkhmmgVZXC4W4Y65UbLlZ8/NnR" +
           "wUovKeoh0yRN0y3JUnQtvI2GdXU3DXSQivjoepWGwhap7Ngl7Zb8EUtR/R1K" +
           "2GrpIHclLLVIU0cMgh8g+AGCn0Hwr43PgkWTqRYJteIKSbPCj5MniKeDFBky" +
           "wrNIfbIQQzKlkBDTyTQACSX4vB2UYoujJqmzdec6pyh8bIl//Lmdld8vIBU9" +
           "pELRuhCODCAs2KSHlIdoqJ+a4bWBAA30kKkapYEuaiqSquxluHtIVVgZ0CQr" +
           "YlLbSDgYMajJ9oxbrlxG3cyIbOmmrV5QoWog9jQpqEoDoOvMuK5cwzYcBwXL" +
           "FABmBiWZxpYUDilawCK1zhW2jk1fggmwtDhErUHd3qpQk2CAVHHfqZI24O+y" +
           "TEUbgKmT9AjsYpE5GYWirQ1JHpIGaK9Fqp3zOjkLZpUyQ+ASi8xwTmOSwEtz" +
           "HF5K8M/NLWuO7NM2al6GOUBlFfGXwKIax6JtNEhNqsmULyxf3PGsNPPNMS8h" +
           "MHmGYzKf89r+Ww8urbl8hc+Zm2bO1v5dVLZ65ZP9U96d19q8ugBhlBh6WEHn" +
           "J2nOwr9TcFqiBpy8mbZEZPpizMvbfv7o6Bl6w0vK2kmRrKuREMTRVFkPGYpK" +
           "zQ1Uo6Zk0UA7KaVaoJXx20kxfO5QNMpHtwaDYWq1k0KVDRXp7BlMFAQRaKJi" +
           "+KxoQT322ZCsQfY5ahBC5sIvqSPEW0fYD/9rkW3+QT1E/ZIsaYqm+yF2qWTK" +
           "g34q6/6wFDJU8Fo4ogVVfdgfNmW/bg7Yz7JuUvC8iXFFTUwEPowt4/8iNYq6" +
           "VA57PGDmec5DrsL52KirAWr2yuORdetvne99x2sHvbCCRRbBPj6xjw/38SXt" +
           "02Q/EY+HbTQdd+a+BE8MwZmGaeXNXTs29Y01FEAQGcOFYMYSmNoAugk462W9" +
           "NX7w21l6kyH6qr/z2CHfpy8/wKPPnzlLp11NLh8ffmr7k8u9xJucblE9GCrD" +
           "5UwDOxk2OY9ZOrkVhz78+MKzI3r8wCXlb5EHUlfiOW5wOsLUZRqAzBgXv7hO" +
           "utT75kiTlxRCcoCEaEkQwJBrapx7JJ3nllhuRF0mgcJB3QxJKrJiCa3MGjT1" +
           "4fgIi5Ap7PNUcEolBvhsiPSFIuLZX+ROM5BO5xGFXnZowXJv248uP3/phSWr" +
           "vYlpuiKh8HVRix/6qfEg6TYphfHfHe985tjNQ4+xCIEZjek2aELaCikAXAZm" +
           "/eqVx39z/drJ97x2VHksUmyYym7IDFEQsjC+DWQIFbIUOr/pYS2kB5SgIvWr" +
           "FKPz84oFd1/66Egld6cKI7FoWJpdQHx89joy+s7OT2qYGI+MFSquenwat8C0" +
           "uOS1pintQRzRp67Of/4t6VuQQCFphZW9lOUhD1eNeWkGdBJsJRYjHy9GzCl+" +
           "xl7MqA+9xhYRxluBpM5I4bGBOaneXya8vyyt95E0OXaLQTRJs0sjZSohyO27" +
           "RfHxj1RdHzrx4av8aDsrlWMyHRs/fNt3ZNybUM4bUypq4hpe0hnkyVzF2/Dj" +
           "gd//4C+qhgM8pVe1irpSZxcWw8AIqneDxbZo++DCyBunRw5xNaqSq9l6aNZe" +
           "/fW/f+E7/vu30yTXAuhUGMIvunjvISSrcvfeSuG9lXfsPXxejeQ+LvwBOE39" +
           "uq5SSWOL211QbkHSljvKFoGy5X+BEnKNLnFLdrlg/AqSztwxrhMY1+VxDhZk" +
           "PgfsvPOwnjjV+MsnJxr/ALHRQ0qUMLTla82BNN1ewpq/nbt+4+rk+edZdSjs" +
           "l8IsnsqcbXJqF5zU3DIdyg32Z82dneM23CahRP9rq9p/4I+fsvhOKbJpjrZj" +
           "fY//3Ik5rfffYOvj1Q5X10ZTGxcwUXztPWdC//A2FP3MS4p7SKUsrkXbJTWC" +
           "NaUHjBCO3ZXg6pTET27reQ/bYlfzec7ckrCts87GzzR8xtnMH7y0GlEPYUHD" +
           "ZzcwugDJImZqrwWXt0i/qsgYxYomqVEYUak2wDvRNUj6jKjtIS9fFisG0+LF" +
           "oFXVNYoVKcbjnZii++zLGDCjaXw9P6kP28yiJG7jw6+cfc16d8m9PMEtzhwX" +
           "zoVvHfjLnO77B/vuoPuqdTjbKfKVzefe3rBQ/oaXFNiuSrnVJS9qSXZQmUnh" +
           "Gqp1J7mphp+DvvQ+8uDHXVGXxLLPhTeCZBi8K6N/uDvB5rXpO4v1IcNivcDe" +
           "12f9cM3LE9dYbxPlona4bHMASU/uua1d5Lb2vPLvTm6w5WmZ/XbQH84Q9Myg" +
           "SIaYGNWO9LHUSMdHDYmRGrr4HOZQxpC4+ejrLrxnkHwNyRMcBdJRYfVDLguP" +
           "ITmYu9U7hdU787I6nOlZidcxJSQNUAwf3WQCXnBBOoHkudyRPiKQPpIz0gIm" +
           "sSCGdGmuF0f7NgKLlmdZ1I41DY47yyP2QhGN3Vmi8cydRuPpvKPxdLZo/J4L" +
           "7wdILtjReDopGr/rsvASkm+n+pgDqWZPhdwoyQ5vBM3qhcPrnQ4X1nud+dyK" +
           "V7iTLBKMdPKaQU6DkNeQQd4bQl4R61PY0KnMEueCpEYhsTGDxB8LiQV6/640" +
           "lyVe4jNvUQeim8QWTRm2+KnYolgepPIQDeDjJi4ymj66CixSapi6BVtTuLMU" +
           "hdm7Ux5tCW4heNmYn+kVH7tonDwwPhHY+tLdXuHtB0GaePMal1PBo9DWbDrK" +
           "rgaNdgrNduZ8nhMj66oL7z0kv7L4hZWdnQxZJo6qCgdrAE1UoIqmReVSjq+4" +
           "Ha7fuvCuIXkfyzF6MN1lonC3rgSyqjAzpsJ+ocL+jIZ1g/qBC+/PSP5kkRJL" +
           "5xd9fF6eFdksHFwAiEYFstG8XP5XF94tJB9ZZMoAtTg01lLnhq8aB+GAeQ8K" +
           "fAfzwveJC++fSP5ukXLAtw4usDa6TVnRsQNTC6jGBLqxvNDdzszzsAD+3CJ3" +
           "Abp2cRvJ7dywoJsPoJ4W4J7OB5yn2IVXiqQQMhwHx+b0ZQU2GwexihwVwI7m" +
           "BazChTcVSblFyuyYY9PGcjMaYhsX2MbzwjbLhTcbyXSLTAZsbfgqwvZpd1Z4" +
           "tTi4CGAdF/CO5wWv1oVXj2QeFKIYvHCsPC7L0m+x2fZQbqkHD88JocuJvHRp" +
           "duEtQbIQkiLo0qkrIkKdnXFIsgZ9jL0it3wEXY/3RQH6xbxAr3DhrUKynDtg" +
           "O7W/2gTU1SmoOT87bGZraK68pwTsU3nBvs+F9wCSe3mi6qbRVl03A1nMfU/u" +
           "5j4rcJ/NC/cGF147koe4uTdLkCuimc3N+Stzr/cXBeyLecH+sguvC8kWHtrs" +
           "UoeTvpmmnQfN7DMZ06zG/SRDd1md8jU//2paPj9RUTJr4uH3+QvF2NfHpR2k" +
           "JBhR1cSXWwmfiwyTBhWmdGnsVRfi74Ge2wkFeir8g1A9j/JpOyCqEqZBvRGf" +
           "Eif1YS9vYtvjkZiRfxIlST2z4eygG5PeTrF/f4i9SYrwf4DolS9MbNqy79YX" +
           "XmKvpSbJqrR3L0op6SDF/AszJhTfRtVnlBaTVbSx+bMpF0sXxHryKUiqErrX" +
           "6oSeYPS//pPRk2wiAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "LwAA");
}
