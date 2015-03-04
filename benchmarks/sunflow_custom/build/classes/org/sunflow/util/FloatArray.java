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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "kXQdDh0lIdFV4srcfwClh7tYjRUAAA==");
}
