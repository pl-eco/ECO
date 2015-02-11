class $UTILMODES{
    public static final int $MAX = 2;
    public static final int high = 2;
    public static final int low = 1;
    public static final int mid = 0;
}
public class Test {
    public static void main(String[] args) { final int[] x = new int[1];
                                             ecor.CalibratorStack.push(new ecor.Calibrator() {
                                             double demandRatio = (double)(100 -
                                               x[0])/(100);
                                             double supplyRatio = 0;
                                             public int mode = $UTILMODES.$MAX;
                                             public int getMode(int max) {
                                             return mode;
                                             }
                                             public int supply() {
                                             return ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity();}
                                             private double supplyLimit = (ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity()) *
                                               0.6;
                                             private int initialSupply = this.supply();
                                             public Object calibrate(Object input) {
                                             supplyRatio = (supplyLimit - (initialSupply - this.supply()))/supplyLimit;
                                             return input;
                                             }
                                             public void adjust() {
                                             System.out.println(supplyRatio + " " + demandRatio);
                                             if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                                             else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;
                                             }
                                             });
                                             {
                                                 for (ecor.CalibratorStack.calibrate(x[0] =
                                                        0);
                                                      x[0] <
                                                        100;
                                                      ecor.CalibratorStack.calibrate(x[0] +=
                                                        1)) {
                                                     System.
                                                       out.
                                                       println(
                                                         x[0]);
                                                 }
                                             }
                                             ecor.CalibratorStack.pop();
                                             ; }
    public Test() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1421788780000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "m5S366HPn/xs9enx2/z2+QdRkba7Eg4AAA==");
}
