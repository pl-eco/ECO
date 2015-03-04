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
        public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXjurv8Uri30h8pPWw61gLegwYjFn3K20HrQhkIT" +
                                                        "D6FMd+fapXu7y+4cPYpVIDElPhATC4LBPhgIhvAXI1FjSPqiYvBFYzQ+CMYX" +
                                                        "SZAHHkQiKn4zs3d7t3eFJy/Z2bmZ7/9/z95CxbaFlpuGtndIM2iYpGh4l7Y6" +
                                                        "TPeaxA53R1f3YssmSkTDtr0FzgbklovBO/feHq7yo5IYmot13aCYqoZubya2" +
                                                        "oe0hShQF3dMOjSRsiqqiu/AeLCWpqklR1aZtUTQrC5WiUDQtggQiSCCCxEWQ" +
                                                        "2l0oQJpN9GQiwjCwTu3d6HXki6ISU2biUdScS8TEFk44ZHq5BkChjP3vB6U4" +
                                                        "cspCTRndhc55Ch9ZLk2+u6PqowAKxlBQ1fuYODIIQYFJDFUmSGKQWHa7ohAl" +
                                                        "hqp1QpQ+YqlYU8e43DFUY6tDOqZJi2SMxA6TJrE4T9dylTLTzUrK1LAy6sVV" +
                                                        "oinpf8VxDQ+BrnWurkLDTnYOClaoIJgVxzJJoxSNqLpC0WIvRkbH0MsAAKil" +
                                                        "CUKHjQyrIh3DAaoRvtOwPiT1UUvVhwC02EgCF4oaZyTKbG1ieQQPkQGKGrxw" +
                                                        "veIKoMq5IRgKRbVeME4JvNTo8VKWf25tWnt4n75B93OZFSJrTP4yQFrkQdpM" +
                                                        "4sQiukwEYuWy6FFcd/mQHyEArvUAC5hPXrv94opF01cEzCMFYHoGdxGZDsgn" +
                                                        "B+d8uyDSuibAxCgzDVtlzs/RnId/r3PTljIh8+oyFNllOH05vfnLV/afITf9" +
                                                        "qKILlciGlkxAHFXLRsJUNWKtJzqxMCVKFyonuhLh912oFPZRVSfitCcetwnt" +
                                                        "QkUaPyox+H8wURxIMBOVwl7V40Z6b2I6zPcpEyHUAA9aCE8tEj/+pqhfGjYS" +
                                                        "RMIy1lXdkCB2CbbkYYnIhmTjhKmB1+ykHteMUcm2ZMmwhjL/ZcMiUheLTxts" +
                                                        "BnqyXCJhFl/m/0Y5xXSqGvX5wNwLvMmuQZ5sMDSFWAPyZHJdx+3zA1f9meB3" +
                                                        "rEHRCuAVdniFGa9wHq8QrPLIJkMhyOfjzOYx7sKv4JURyG+ofJWtfdu7dx5q" +
                                                        "CUBAmaNFYFIG2gI6OiJ1yEbELQJdvNTJEIkNH2ybCN89/YKIRGnmil0QG00f" +
                                                        "Gz3Q/8ZKP/Lnll6mIhxVMPReVjAzhTHkTblCdIMTN+5cODpuuMmXU8udmpCP" +
                                                        "yXK6xesMy5CJAlXSJb+sCV8auDwe8qMiKBRQHCmGYIa6s8jLIye329J1kulS" +
                                                        "DArHDSuBNXaVLm4VdNgyRt0THiVz2FIjAoY50CMgL7Gdn00fv/Te8jX+7Goc" +
                                                        "zOpvfYSK3K52/b/FIgTOfz7W+86RWxPbuPMBYkkhBiG2RiDTwRtgsTev7P7p" +
                                                        "+rWT3/vdgKHQ8pKDmiqngMajLheoA5qISDu0VU8YihpX8aBGWNz9HVy66tLv" +
                                                        "h6uEozQ4Sft5xcMJuOfz16H9V3f8uYiT8cmsD7mau2DCAHNdyu2WhfcyOVIH" +
                                                        "vlt4/Cv8PpRJKE22OkZ4tUFcM8RNH+YeaeXrE567lWxpMvPuUvykgf8LAOvW" +
                                                        "mfOjk7XTrLz6q0cbPPjrXa5RXmYU6CIe/Jh09kRj5PmbHN8NUYa9OJVfcWD0" +
                                                        "cHGfPJP4w99S8oUflcZQlezMNf1YS7JoiUEvt9PDDsw+Ofe5fVk0obZMCi7w" +
                                                        "pkcWW29yuJUO9gya7StEPnCYarBpEDktoc7pBvzNbueabJ2X8iG+eYajNPM1" +
                                                        "xJbHRMyy7eMpCtygQj7YQb2WmoAuuMdp09J4zfWREzfOicLn9YYHmByafOt+" +
                                                        "+PCkP2vwWZI3e2TjiOGHqztbqHsffj54/mUPU5MdiOZXE3E6cFOmBZsmy8Lm" +
                                                        "B4nFWXT+dmH88w/HJ4QaNbl9vwPG2nM//PNN+NgvXxdoPwGY6XhREoH/VK5b" +
                                                        "GuGpd9xSP4Nb1rPlWWZ+4sy+z7GlXeRNhLKQMPADmMxPjwTpdwEmGx0mgTjm" +
                                                        "8d8tyKUKh0SAhwTUMpsP1EwEVcdadiYjZtqFM41+3KwnD05OKT2nVvmdAvES" +
                                                        "EHQm8mw6FJVn+jO/qKWo5eFNHdg35H0fiJlWPj8VLKuf2voj702ZubMchr94" +
                                                        "UtOykyprX2JaJK5ySctFipn8tR2+kbzigLfYi4v7qgDbSdGsLDCKSp1dNhCY" +
                                                        "MgBAbKuYaWWreC1mxSIsikUK5RjI9Jp9SU6C8m8pxwIbk+JrakC+MNW9ad/t" +
                                                        "p0/xqaIYvsLGxvjsDZ8SouNmhonmGamlaZVsaL0352L50rQjc3qxR7bFhVtW" +
                                                        "R8KkvMmMfVr/8drTU9d4z/wPGGE2ceQOAAA=");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "YDm0ljwBiiDbkXEBHmiN8NQ5LlCX5wKET4YLm42AbcpSfPVLOOwRMbsXTacZ" +
       "8v9IdAyHEaAwjZNIcWTr3BLg/hdAdreDrEZ1YoajWMvI+gItgoAoThCJ1DsE" +
       "64sQPOQQDKgxnA0WxIZ9Gul2uzb3vwA2ubABAjjlgbULTFEJfCtT4E49xaOD" +
       "Z23h7Ivf6/r1scWuP4HHREEvNiSUfitRoGnPOvO35UuX31rddo6X+IoJ2Rap" +
       "xX/byb/M5NxReLjU52rhplJacI21beV+rjuCShg2YjRTUcqczo3rHgfVVWuy" +
       "hFq3uGqt0qieEC02t+Kkmc6gDgh4l781XhIMaYZOsdK6e6LBVI1g5pYJm+k8" +
       "Ji3SltNe7ud68wrMg8/84AX25tYvikTQW9zS/oOvnPjLhtHbJ+/9L5rKTT5H" +
       "8KN8Zv/ya4M3Kw8FSHmmTuVdV3MP9eVWpzqLwv1aH82pUe3CRlzXOHSX6B7m" +
       "Suwdw+EoxIWCthCmA/1uKtwdDSRNxvuZ2Rdv/PGtZxff4YUrTYoH7mZ4GhyX" +
       "bSgSuA9cS+BWWSJy8T1uct4jpmkWJs3lW+vkUDeXFiL9LScDVSspC5ptno2/" +
       "IlCmV/b7rO6LYIlqK3aT5+Vp6cTCYuzA09sDjur3MVLLDPMzGp2mWhaqSj6f" +
       "yQjThuh74Ol1hOn1C8MZvkZ2C7vBmRJ7j+PwMJSFBGU8cThe4of1sY3qJx3w" +
       "7HDY3nH92X6qxN4SDk8yssple9TgdAdX5Jx3fZ+C5zaH89uuP+fLJfbO4XAW" +
       "Wjrg/KAxkbI9tZ+8NubxnjfoMD94/Zn/SYm9F3H4ITRUkyor1PVUTxiGRmV9" +
       "RUHwIXfAM+oIMnp9BCkX3TR3BZ5F+MCR/aKEXC/j8HO48+KHp6yCWkjGimlD" +
       "jRW4rTHSnFeLMdu25n2nFd8WlXOLjTU3Lt71e9FKuN//asOkJp7StOwbTNa8" +
       "yrRoXJSGWnGfEenyV4w0+XsD4BX/OKtvCLDfQMhkgYHFnFk20G/BwACE07dN" +
       "t3pnXW/EzSxNcjKk6c+XXTmVmX/TdqtoSnzVHlfOL+4dPnr180/zkgxVSp6d" +
       "RSw10C6JbyCZSry5KDYXV9WeLR83PF/b42bgBhxasvyoNcvgJ/4DEYpUy0EY" +
       "AAA=");
}
