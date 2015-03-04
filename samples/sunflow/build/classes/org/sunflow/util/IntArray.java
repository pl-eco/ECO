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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
