package org.sunflow.util;
public final class FloatArray {
    private float[] array;
    private int size;
    public FloatArray() { super();
                          array = (new float[10]);
                          size = 0; }
    public FloatArray(int capacity) { super();
                                      array = (new float[capacity]);
                                      size = 0; }
    public final void add(float f) { if (size == array.length) { float[] oldArray =
                                                                   array;
                                                                 array =
                                                                   (new float[size *
                                                                                3 /
                                                                                2 +
                                                                                1]);
                                                                 System.
                                                                   arraycopy(
                                                                     oldArray,
                                                                     0,
                                                                     array,
                                                                     0,
                                                                     size);
                                     }
                                     array[size] =
                                       f;
                                     size++;
    }
    public final void set(int index, float value) {
        array[index] =
          value;
    }
    public final float get(int index) { return array[index];
    }
    public final int getSize() { return size;
    }
    public final float[] trim() { if (size <
                                        array.
                                          length) {
                                      float[] oldArray =
                                        array;
                                      array =
                                        (new float[size]);
                                      System.
                                        arraycopy(
                                          oldArray,
                                          0,
                                          array,
                                          0,
                                          size);
                                  }
                                  return array;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "ZxUAAA==");
}
