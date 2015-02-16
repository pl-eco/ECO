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
      1170479976000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "jpArc/8BjuyYwH0VAAA=");
}
