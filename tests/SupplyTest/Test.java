class $UTILMODES{
    public static final int $MAX = 2;
    public static final int high = 2;
    public static final int low = 1;
    public static final int mid = 0;
}
public class Test {
    public static void main(String[] args) { ecor.CalibratorStack.push(new ecor.Calibrator() {
                                             double supplyRatio = 0;
                                             public int mode = $UTILMODES.$MAX;
                                             public int getMode(int max) {
                                             return mode;
                                             }
                                             public int supply() {
                                             return ecor.tsupply.TemperatureSupply.sharedSupply().getCurrentTemperature();}
                                             private double supplyLimit = 0;
                                             private int initialSupply = this.supply();
                                             public Object calibrate(Object input) {
                                             int currentTemperature = this.supply();
                                             supplyRatio = (supplyLimit - (this.supply() - initialSupply))/supplyLimit;
                                             return input;
                                             }
                                             public void adjust() {
                                             double demandRatio = (double)(0)/(0);
                                             System.out.println(supplyRatio + " " + demandRatio);
                                             if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                                             else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;
                                             }
                                             });
                                             {  }
                                             ecor.CalibratorStack.pop();
                                             ; }
    public Test() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1421855613000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwUVRS+u93+Wti2/IiVlv4sSCndARMwWCKWTYHiAg0t" +
                                                    "JJTIcjtzdzt0dmaYudtui5WfxJTwQIwWBKN9MCCi/MVI0BiSPgkEYwIxJj4I" +
                                                    "+iQRSeQFTVDx3Ht3d3an2xofbDK3M+eec+75+849e+4BKrQt1Gwa2lBMM2iQ" +
                                                    "JGlwj7YiSIdMYgc3hld0YssmSkjDtt0NtIjccMn/6PGbfRVeVNSDZmFdNyim" +
                                                    "qqHbW4ltaANECSO/Q23XSNymqCK8Bw9gKUFVTQqrNm0No6eyRCkKhNMmSGCC" +
                                                    "BCZI3ASpzeECoRlET8RDTALr1N6LXkeeMCoyZWYeRfW5Skxs4XhKTSf3ADSU" +
                                                    "sO/t4BQXTlqoLuO78HmSw8eapbF3dlV8WoD8Pciv6l3MHBmMoHBIDyqPk3gv" +
                                                    "sew2RSFKD6rUCVG6iKViTR3mdvegKluN6ZgmLJIJEiMmTGLxM53IlcvMNysh" +
                                                    "U8PKuBdViaakvwqjGo6Br3MdX4WH6xgdHCxTwTArimWSFvH1q7pC0QK3RMbH" +
                                                    "wCvAAKLFcUL7jMxRPh0DAVWJ3GlYj0ld1FL1GLAWGgk4haLqKZWyWJtY7scx" +
                                                    "EqFonpuvU2wBVykPBBOhaI6bjWuCLFW7spSVnwebVx/dp2/Qvdxmhcgas78E" +
                                                    "hGpdQltJlFhEl4kQLF8SPo7nXj3sRQiY57iYBc+V1x6+vLR24rrgeTYPz5be" +
                                                    "PUSmEflU78xb80NNqwqYGSWmYass+Tme8/LvTO20Jk1A3tyMRrYZTG9ObP1q" +
                                                    "x4GPyX0vKutARbKhJeJQR5WyETdVjVjriU4sTInSgUqJroT4fgcqhvewqhNB" +
                                                    "3RKN2oR2IJ/GSUUG/4YQRUEFC1ExvKt61Ei/m5j28fekiRAqhgeVw1OAxB//" +
                                                    "T9EOaZsN5S5hGeuqbkhQvARbcp+U9qTl+eDK4PIW25Il9uRAksiGRIlNbakr" +
                                                    "YZraUDe8S2wJwo75fypPMs8qBj0eCPp8N+Q1QMsGQ1OIFZHHEmvbH16I3PRm" +
                                                    "IJCKCUU+pgx5PFzJbIYKkTWIeT+gF/paeVPXqxt3H26AWCXNQR8EjLE2wPmp" +
                                                    "o9plI+RAvIM3MhnqbN4HO0eDf5xZI+pMmrof55VGEycGD27fv8yLvLmNlZkO" +
                                                    "pDIm3snaYabtBdyAyqfXP3rv0cXjI4YDrZxOnUL8ZEmG2AZ3kC1DJgr0QEf9" +
                                                    "kjp8OXJ1JOBFPmgD0PoohlKFrlLrPiMHua3pLsh8KQSHo4YVxxrbSreuMtpn" +
                                                    "GYMOhWd/JluqRCGwBLoM5A103RcTJy+/27zKm91r/Vm3VxehArmVTv67LUKA" +
                                                    "/sOJzrePPRjdyZMPHI35DgiwNQQ4hmxAxN64vvf7u3dOfet1CobChZbo1VQ5" +
                                                    "CToWOacAyjXoNCytgW163FDUqIp7NcLq7k//wuWXfz1aIRKlASWd56X/rsCh" +
                                                    "P7MWHbi56/darsYjs1vG8dxhEwGY5Whusyw8xOxIHrxdc/Iafh+aIDQeWx0m" +
                                                    "vJcg7hnioQ/yjDTxtcW1t4wtdeakvSSnzEt98Y96vgbY8pyIG3td7OK0UM1U" +
                                                    "Fwe/9E4dGhtXtpxeLmBXlduM22HWOP/dX18HT/x4I083KKWG2aKRAaJlncng" +
                                                    "XpMD9038TnWK/sjZT67QW80viiOXTI10t+C1Q79Ud7/Ut/s/gHyBy3m3yrOb" +
                                                    "zt1Yv0h+y4sKMvieNCbkCrVmhwEOtQjMNToLKKOU8TTWcgMqIRwVLA2L4fGl" +
                                                    "LhH+n+3OMtk6O4XGvBn18owCHGw+cSVdhePJBHzh1EHklSmu8vEPG7/ZP974" +
                                                    "E6SyB5WoNgyBbVYsz2yRJfPbubv3b8+oucA7lK8X28JL91A2eebKGaW4k+WZ" +
                                                    "oFSzGNRNFxTOOgfGZg4xNnkFxeRlmqZASvs0KNrEljVwXcWhnUJ4mqaZ7i01" +
                                                    "DgPHQGoikkaq7va/d++8qE33+ORiJofHjjwJHh3zZs2YjZPGvGwZMWdyG2eI" +
                                                    "aDyBPw88f7OHOcAIYs6oCqWGnbrMtGOaDNH105nFj1j388WRLz8aGfWmAvIC" +
                                                    "xGLAUJU8jSV1qZtmnpCLyS6JclqK6W4wjTnR5b850mhLiF8dEfni+MbN+x6u" +
                                                    "PM2hWwi/VoaH+YwKdSLurgxi66fUltZVtKHp8cxLpQvTzuXcai7bFuRv/u1x" +
                                                    "k/J2Pfz505+tPjN+h98+/wCcHmuoDA4AAA==");
}
