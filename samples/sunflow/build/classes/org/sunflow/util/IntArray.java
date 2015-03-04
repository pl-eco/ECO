package org.sunflow.util;
public final class IntArray {
    private int[] array;
    private int size;
    public IntArray() { super();
                        array = (new int[10]);
                        size = 0; }
    public IntArray(int capacity) { super();
                                    array = (new int[capacity]);
                                    size = 0; }
    public final void add(int i) { if (size == array.length) { int[] oldArray =
                                                                 array;
                                                               array = (new int[size *
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
                                   array[size] = i;
                                   size++; }
    public final void set(int index, int value) {
        array[index] =
          value;
    }
    public final int get(int index) { return array[index];
    }
    public final int getSize() { return size; }
    public final int[] trim() { if (size < array.
                                             length) {
                                    int[] oldArray =
                                      array;
                                    array =
                                      (new int[size]);
                                    System.
                                      arraycopy(
                                        oldArray,
                                        0,
                                        array,
                                        0,
                                        size);
                                }
                                return array; }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "K2DIw/8BTrRTfHAVAAA=");
}
