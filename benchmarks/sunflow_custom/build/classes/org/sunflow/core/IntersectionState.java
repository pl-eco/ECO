package org.sunflow.core;
public final class IntersectionState {
    private static final int MAX_STACK_SIZE = 64;
    float u;
    float v;
    Instance instance;
    int id;
    private final StackNode[] stack;
    private final float[] rstack;
    Instance current;
    public static final class StackNode {
        public int node;
        public float near;
        public float far;
        public StackNode() { super(); }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1170611152000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XXWwURXh6138K1xb6Q4XSlkMt4C1oMGLxp5wttB60oUDi" +
                                                        "IRzT3bl26d7usjtHj2IVSEyJD8TEgmCwDwaCIfzFSNQYkr6oGHzRGI0PgvFF" +
                                                        "EuSBB5GIit/M7N3e7V3hzUt2dm7m+//fs7dQiW2hZaah7R3SDBoiKRrapa0K" +
                                                        "0b0msUO9kVX92LKJEtawbW+Gs5jcdjFw597bw9U+VBpFc7GuGxRT1dDtTcQ2" +
                                                        "tD1EiaCAe9qlkYRNUXVkF96DpSRVNSmi2rQjgmZloVIUjKRFkEAECUSQuAhS" +
                                                        "pwsFSLOJnkyEGQbWqb0bvY6KIqjUlJl4FLXmEjGxhRMOmX6uAVAoZ/+3glIc" +
                                                        "OWWhlozuQuc8hY8skybf3VH9kR8Foiig6gNMHBmEoMAkiqoSJDFILLtTUYgS" +
                                                        "RTU6IcoAsVSsqWNc7iiqtdUhHdOkRTJGYodJk1icp2u5KpnpZiVlalgZ9eIq" +
                                                        "0ZT0v5K4hodA13pXV6FhNzsHBStVEMyKY5mkUYpHVF2haJEXI6Nj8GUAANSy" +
                                                        "BKHDRoZVsY7hANUK32lYH5IGqKXqQwBaYiSBC0VNMxJltjaxPIKHSIyiRi9c" +
                                                        "v7gCqApuCIZCUZ0XjFMCLzV5vJTln1sb1xzep6/XfVxmhcgak78ckJo9SJtI" +
                                                        "nFhEl4lArFoaOYrrLx/yIQTAdR5gAfPJa7dfXN48fUXAPFIApm9wF5FpTD45" +
                                                        "OOfbBeH21X4mRrlp2Cpzfo7mPPz7nZuOlAmZV5+hyC5D6cvpTV++sv8MuelD" +
                                                        "lT2oVDa0ZALiqEY2EqaqEWsd0YmFKVF6UAXRlTC/70FlsI+oOhGnffG4TWgP" +
                                                        "Ktb4UanB/4OJ4kCCmagM9qoeN9J7E9Nhvk+ZCKFGeNBCeOqQ+PE3RVjaYkO4" +
                                                        "S1jGuqobEgQvwZY8LBHZiA2CdYcT2BqxJTlpUyMh2Uk9rhmjkm3JkmENZf7L" +
                                                        "hkWkHhaqNpgPVGZpRUIs1Mz/g0mKaVo9WlQETljgLQEaZM96Q1OIFZMnk2u7" +
                                                        "bp+PXfVlUsKxEUXLgVfI4RVivEJ5vIKwyiMbDYWgoiLObB7jLrwNvhqBrId6" +
                                                        "WNU+sL1356E2P4SZOVoMhmagbaCtI1KXbITd0tDDC6AM8dn4wbaJ0N3TL4j4" +
                                                        "lGau4wWx0fSx0QNb31jhQ77cgsxUhKNKht7PymimXAa9iViIbmDixp0LR8cN" +
                                                        "NyVzKrxTKfIxWaa3eZ1hGTJRoHa65Je24Euxy+NBHyqG8gElk2IIcahGzV4e" +
                                                        "ORnfka6eTJcSUDhuWAmssat0yaukw5Yx6p7wKJnDlloRMMyBHgF54e3+bPr4" +
                                                        "pfeWrfZl1+hAVtcbIFRkfI3r/80WIXD+87H+d47cmtjGnQ8QiwsxCLI1DPkP" +
                                                        "3gCLvXll90/Xr5383ucGDIVGmBzUVDkFNB51uUB10ERE2sEtesJQ1LiKBzXC" +
                                                        "4u7vwJKVl34/XC0cpcFJ2s/LH07APZ+/Fu2/uuPPZk6mSGbdydXcBRMGmOtS" +
                                                        "7rQsvJfJkTrw3cLjX+H3oXhCwbLVMcJrEOKaIW76EPdIO1+f8NytYEuLmXeX" +
                                                        "4ieN/J8fWLfPnB/drMlm5dVffdrgwV/vco3yMqNAb/HgR6WzJ5rCz9/k+G6I" +
                                                        "MuxFqfyKAwOJi/vkmcQfvrbSL3yoLIqqZWfa2Yq1JIuWKHR4Oz0CwUSUc5/b" +
                                                        "rUVr6sik4AJvemSx9SaHW+lgz6DZvlLkA4epAZsGkNMo6p0ewd/sdq7J1nmp" +
                                                        "IsQ3z3CUVr4G2fKYiFm2fTxFgRtUyAc7qN9SE9Ab9zjNWxqvvT5y4sY5Ufi8" +
                                                        "3vAAk0OTb90PHZ70ZY1Di/MmkmwcMRJxdWcLde/Drwief9nD1GQHoiXWhp2+" +
                                                        "3JJpzKbJsrD1QWJxFt2/XRj//MPxCaFGbe400AXD7rkf/vkmdOyXrwu0Hz9M" +
                                                        "erwoicB/KtctTfA0OG5pmMEt69jyLDM/cSbi59jSKfImTFlIGPgBTOanB4X0" +
                                                        "uwCTDQ4Tfxzz+O8V5FKFQ8LPQwJqmc3HbCaCqmMtO5MRM+3CmQZCbtaTByen" +
                                                        "lL5TK31OgXgJCDpzejYdiioy/Zlf1FHU9vCmDuwb874axKQrn58KlDdMbfmR" +
                                                        "96bMNFoBI2E8qWnZSZW1LzUtEle5pBUixUz+2g5fTl5xwFvsxcV9VYDtpGhW" +
                                                        "FhhFZc4uGwhM6QcgtlXMtLLVvBazYhESxSKFcgxkes2+OCdB+ReWY4ENSfGN" +
                                                        "FZMvTPVu3Hf76VN8qiiBb7OxMT6RwweG6LiZYaJ1RmppWqXr2+/NuVixJO3I" +
                                                        "nF7skW1R4ZbVlTApbzJjnzZ8vOb01DXeM/8DDzvVF/oOAAA=");
    }
    public IntersectionState() { super();
                                 stack = (new StackNode[MAX_STACK_SIZE *
                                                          2]);
                                 for (int i =
                                        0;
                                      i <
                                        stack.
                                          length;
                                      i++)
                                     stack[i] =
                                       new StackNode(
                                         );
                                 rstack =
                                   (new float[53 *
                                                256]);
    }
    public final StackNode[] getStack() {
        return stack;
    }
    public final int getStackTop() { return current ==
                                       null
                                       ? 0
                                       : MAX_STACK_SIZE;
    }
    public final float[] getRobustStack() {
        return rstack;
    }
    public final boolean hit() { return instance !=
                                   null; }
    public final void setIntersection(int id,
                                      float u,
                                      float v) {
        instance =
          current;
        this.
          id =
          id;
        this.
          u =
          u;
        this.
          v =
          v;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170611152000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+Mn/AFaaT5dXGAAA");
}
