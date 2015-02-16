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
      1170480004000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "YBUAAA==");
}
