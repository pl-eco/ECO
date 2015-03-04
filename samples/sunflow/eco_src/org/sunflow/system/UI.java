package org.sunflow.system;
import org.sunflow.system.ui.ConsoleInterface;
import org.sunflow.system.ui.SilentInterface;
final public class UI {
    private static UserInterface ui = new ConsoleInterface();
    private static boolean canceled = false;
    private static int verbosity = 0;
    public enum Module {
        API, GEOM, HAIR, ACCEL, BCKT, IPR, LIGHT, GUI, SCENE, BENCH, TEX,
         IMG,
         DISP,
         QMC,
         SYS,
         USER,
         CAM;
         
        private Module() {
            
        }
        final public static String
          jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long
          jlc$SourceLastModified$jl =
          1415385948000L;
        final public static String
          jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1ZC2wUxxke353fBp9NeIZgQwwETO4AhQYwamKOwz58fsRn" +
           "G7Ahznpvzl7Y293s\nztlnQ9MkRIFAS5OSRm0TXERQeeWBSlraBhFSEtpAWp" +
           "FKTVRUUFGkphIhKk2V0qaqOv/s7T12b8ld\nkfi9Nzvz/9//nJl/X7mBCjUV" +
           "zeQ1DxlVsObxhTo4VcNhn8hpWhcd6uffLSztONwiyQ5UEEQOIUxQ\nZZDXvG" +
           "GOcF4h7A2saYirqF6RxdFBUSYeHCeeLeKyBL91wWUWhuvHT1U/cchV40CFQV" +
           "TJSZJMOCLI\nkl/EUY0gd3ALN8x5Y0QQvUFBIw1BNAFLsahPljTCSUR7FD2G" +
           "nEFUpPDAk6DZQUO4lwr3KpzKRb1M\nvLeDiaUcJqmYcIKEw41JcXTlosyVFH" +
           "ZiXad1NmVSAi97qDoMAdW6Nqm1rq1FVcV5pOdr2w8cdaLK\nXlQpSCFgxlNN" +
           "CJXXiyqiODqAVa0xHMbhXlQlYRwOYVXgRGGMSe1F1ZowKHEkpmKtE2uyOAwT" +
           "q7WY\nglUm0xgMogoedFJjPJHVpI0iAhbDxq/CiMgNUrWnpNTW1V0L41TBMo" +
           "ECUyMcj40lrq2CRD1eY16R\n1LGuhU6gS4ujmAzJSVEuiaMDqFr3pchJg94Q" +
           "UQVpkE4tlGNUCkEzbJmCrRWO38oN4n6Cppnndeiv\n6KxSZghYQtBk8zTGiX" +
           "pphslLaf6pn/LFriMvnXmQxbYrjHkR8JfRRbNMizpxBKtY4rG+8FbM83xg\n" +
           "Y2ymAyE6ebJpsj6nce6p7uBf36rR59yZZU77wBbMk36+7bmazm1NMnICjBJF" +
           "1gRwfobmLB06Em8a\n4grN2ilJjvDSY7w823l+4+PH8HUHKgugIl4WY1EaR1" +
           "W8HFUEEatNWMIqR3A4gEqxFPax9wFUTJ+D\nNOT10fZIRMMkgFwiGyqS2W9q" +
           "oghlASYqpc+CFJGNZ4UjQ+w5riCEyul/VEj/VyH9XyUQgu7xeLWY\nFBHlEa" +
           "+m8l5ZHUz9HtUIjnq7Ax6IGIWgNd4hOYq9HM9JgiR7BwWao7x8bxgPw98c+c" +
           "QBU/VIQQEU\nOXOyijTOm2UxjNV+/vDHF7b7W57ZpQcCBG9CG4JmUvaeBHuP" +
           "zt7THahrlcMxEaOCAsb8DpCm+4Fa\ncSvNR1q5KhaENq97ZNccJw0AZcRFTQ" +
           "BT51D4CQh+XvalkjbA6htPI2fawb6dnluHH9Ajx2tfW7Ou\nLr/06sUDn1cs" +
           "dCBH9sIHqtHSWwZsOqBaJgtanTlVsvH/bHfryQ8vXrknlTQE1Vly2boScnGO" +
           "2Qmq\nzOMwrW4p9oemVzrXo57nHMhFE5wWNYaf1otZZhkZOdlg1DfQpTiIyi" +
           "OyGuVEeGUUpTIypMojqREW\nHW4gd+iBAo40AWSl8daOosUfnS5/l2lsVNHK" +
           "tH0qhImek1WpOOhSMabjV77fse97N3b2sSBIRAFB\nxYoqDNMsjNM181JraD" +
           "aKtCKAk+q6pagcFiICNyBiiKb/VM5d8tNP97p1s4t0xPDaoq9mkBqfvho9\n" +
           "fvHhf85ibAp42A1SeqSm6epMSnFuVFVuFHDEn/j9XT/4NbefFitaIDRhDLOc" +
           "R0w1xAzpYfZdwOi9\npneLgcyhvBfZhHWWvbef335scE7s0fd+wVCXc+mbeL" +
           "ofWjmlQfcqkz2JCp2KEqQyvRbB28kK0Cng\ngqnm9G3mtCHK7L6zbZvc4tkv" +
           "qdheKpanG6PWrtKKEc9wdWJ2YfHlt89NeeQDJ3KsRWWizIXXciwB\nUCmNPK" +
           "wN0WITVx54kMFwj5QAZXZBDO2MhJXiab+qKLi5Noby00NRKm2WNu9panjquk" +
           "Szfh0qltWw\nIHEi3U04SZb0DbLWuo9mHFcgPxfY15q1cJRIyesfWHQkeKF9" +
           "P/OIbZXJspOa+Iyd6R6/9VtylfFJ\npTusnh23Vm16/EqtXf7hcFXRiR9FHa" +
           "i4F7n5xAGxhxNjkHm99DyjGadGeojMeJ95NtE34oZkOZtp\nNlWaWHOhSe0W" +
           "9Blmw3OFKQrB12xbdCei0G2OQoQUNnslkNXwEx58bOxuRufp1cNFkAvOw4TC" +
           "AB8T\nehKODYgCTeUijZ0v4wQ5GzsCrKQxoiSE6jzXAFmnfQMqEZqedvRvky" +
           "V2OhB4CIe4kRZgIY+KI1AZ\noGDHR6/Nv1z7vtt3Ua8hQwTNTbNlYqY3IA3L" +
           "PAuFZk4K05OHXlJmZhW4XuUUepK79Oe/bP5O/Sfn\nYeNTTNB16yQtOgElzh" +
           "e5WbTTsGgoi0XheS2QJiDNQALUiK4mf3ur1YoFjE8XkB7GpoXRdtMkE95J\n" +
           "Rh3KDW+vgbcvD7zNjYFOK14H47MJyMO2eB1mvNNhdFbOeAcMvHzueAsbfT5/" +
           "0ArYyRiFgURsATvN\ngGthtC5nwFsMwFvzMPBqX0uXFa+L8WFXB8kWr8uMdx" +
           "6MLsgZr2rg1XLH6wx0ZImHQsaGnR6GbeEW\nmuHWw+jinOGOGXC35REPwUBT" +
           "cxb7FjFG24E8Zgu4yAx4KYwuyxnwkwbgHXnYt6k7S5UtZmyeAvK0\nLdxiM9" +
           "z7YXRVznB3G3D35GHfkM/f5rcCLmGMvgVkry3gEjPgr8OoL2fA3zUA78sD8G" +
           "p/m6/ZCriU\nMXoeyAu2gEvNgP0w2pwz4B8agF/MIyC6/BuscMsYm5eAjNvC" +
           "LTPDXQejrTnDPWjAfTmf+tDaZIVb\nztgcAvJjW7jlZrjtMBrKGe4xA+7xPM" +
           "rvmkCow4q3gvF5BchrtngrzHi7YXRDznh/YuA9mYd5H2r1\nWeFOYGzeAPIz" +
           "W7gTzHB7YXRzznB/acB9Mw+4oY0hK9yJjM1pIGds4U40w+2HUT5nuL8y4J7L" +
           "Ixq6\nQ/4su1sl4/MOkPO2eCvNeDGMDuWM94KB92Ie5vU1ZjlMuhmb94H8zh" +
           "auW4lb5TiTchIiMi6R9KR9\nl13fk/Vsd264WfE0985mvcdUndlLhKvlJ6Pn" +
           "8PxV376WpSVWlOhbpwQ6QF5GY6uV9YNTd7XdR4+f\nIh/Ur9TlLbS/Z5oXLl" +
           "x5YKxm5et7/o92Vo3JAmbWVcN3PuQcEn7jYC1r/epnaXVnLmrIvPCVUTwx\n" +
           "lV2wYUS/9tUCMVpK2f3WZPZb9mbJtdu8+xjIFeqMYbjPatC7SrtasXYNwDr6" +
           "7JpJnSv6djDzlXKi\nwGltKfwOgR2zC+L2bQbqkiSzfn7+5lN/e/sMns86Ii" +
           "WCRi/Gjepgli572pqb3DHc+lFknHX0XAOc\nptvK/HnC+vUh46MCU3pimmkV" +
           "RUEkodKKpfcvVuB6WZO9F+aPKoR1r8Z+PvWNVYfHr7IbJmP8xzSW\nQK6abF" +
           "6Q6NzC76kEuVN3Yv2LBlv52W0c9ffsYoDcAHITyOcEFTNHtkd05YBeB/KP9L" +
           "KgD9NqUqT3\nnw1Uk7M2qak9plk+UemfVfjg5W2bvgj+4V+6U4xPH+VBVBKJ" +
           "iWJ6TyPtuUiht3uBKVGudzgU9udL\ngqqtCKAhwR4Yyn/rU/9LUHnaVKp24i" +
           "ltUkEBLZd0Ejw6wKuT0yK7kxthlayf1/60f++hZ75JvjK0\nZ9u1GpOslhzs" +
           "emH5ey336cUOQpSgeruvg9aPmg23z5+0L06LT5z/tG31W+OORHBMSG5B2T+a" +
           "pG9B\nhrMnpkIQCjVYqZSgEmaAJUuWK6adIPWjCh7ZkYRt9H1AeCBbgbCJ24" +
           "DsALIHyD4gLwJ5GchxICeB\nvAnkHNsCYa+5O0N59gnWqLmxjoSNN7zaVxvf" +
           "0/Usc1chL3JjY7C+jOa53qtP1u3ZttwMXpfQidd7\nTr+2wjBjRhffov0S/S" +
           "17Xpat/8qySvkfTqS3PA4fAAA=");
    }
    public enum PrintLevel {
        ERROR, WARN, INFO, DETAIL; 
        private PrintLevel() {  }
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1415385948000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Ya2wc1RW+3l3b8SP4kThvYsckhDzYTRBpSYwEjmPHG29s" +
                                                       "d/0I2AnmeubuepLZ\nmcnMXXvtpC0olLxayquoVI2bokh5tJRIbZVWQiGIkh" +
                                                       "bSSqFSQYoKKqpUKgFVUSWaqv3Rc+7s7MzO\n7ga3lvb4ztx7zzn3PL5z7vz4" +
                                                       "U1JumWSFZIX5tMGscMdAPzUtJneo1LIG4dWY9GZ5Vf/ZHk0PkLIY\nCSgyJ3" +
                                                       "UxyYrIlNOIIkeiO9oyJtlg6Op0UtV5mGV4eL+6JctvV2xLAcM9s5caHz8Tag" +
                                                       "6Q8hipo5qm\nc8oVXetUWcripD62n07SSJoraiSmWLwtRuYzLZ3q0DWLU41b" +
                                                       "B8nXSDBGKgwJeXKyKuYIj4DwiEFN\nmooI8ZF+IRY4LDAZp4rG5PacONi5MX" +
                                                       "8nqJ3dFy9cDUzm4eQwHEdoAKduyZ3aPm3BUY3gueEvHT59\nPkjqRkidog0g" +
                                                       "MwlOwkHeCKlNsdQ4M612WWbyCGnQGJMHmKlQVZkRUkdIo6UkNcrTJrPizNLV" +
                                                       "SVzY\naKUNZgqZzssYqZXwTGZa4rqZs1FCYarsPJUnVJqEYy9yj20ftwvfww" +
                                                       "GrFVDMTFCJOVtCBxQNPN7s\n35E74+oeWABbK1OMT+g5USGNwgvSaPtSpVoy" +
                                                       "MsBNRUvC0nI9DVI4WVaSKdraoNIBmmRjnCzxr+u3\np2BVlTAEbuGkyb9McA" +
                                                       "IvLfN5yeOfDYs+P3bu+5cfFLEdkpmkov7VsGmlb1OcJZjJNInZG2+mw89H\n" +
                                                       "H06vCBACi5t8i+017WsuDcX++lqzvWZ5kTV94/uZxMek3mea44d26iSIaswz" +
                                                       "dEtB5+edXKRDf3am\nLWNA1i7KccTJsDN5JX714ccusI8DpDpKKiRdTacgjh" +
                                                       "okPWUoKjN3Mo2ZlDM5SqqYJneI+SiphHEM\nQt5+25dIWIxHSUgVryp08Qwm" +
                                                       "SgALNFEVjBUtoTtjg/IJMc4YhJAa+JFy+DUR+28BEk7uCkestJZQ\n9amIZU" +
                                                       "oR3Uy6z9MWZ6nIUDSMEWNwsiMyoadYhEpUUzQ9klQgRyX9bplN4v858smgTo" +
                                                       "1TZWUIcv5k\nVSHOu3VVZuaYdPbPbx/u7Dl+zA4EDN7saThpAfbhLPuwzT48" +
                                                       "FF3dD9HMY2ySqaSsTAhYiBJtX4Al\nD0BOAnrVrhvYt+vRY61BCAJjKgRmwK" +
                                                       "WtcISsGp2S3uEmblRgnATRs+Sl0aPhm2cfsKMnUhpfi+6u\nuf7ytdP/qF0f" +
                                                       "IIHi4IfHA/itRjb9iJg5UFvtT5di/P92YvdP3732/l1u4nCyuiCfC3diPrb6" +
                                                       "HWHq\nEpMB4Vz2Z5bWBfeQ4WcCJARJDsAm9AfMWOmXkZeXbQ7G4VkqY6QmoZ" +
                                                       "spquKUA0zVfMLUp9w3IkLq\nkSy0gwUd6VNQwOPNIxWb3nu15k1xYgdJ6zy1" +
                                                       "aoBxOy8b3DgYNBmD9+9/t/+573x6dFQEQTYKOKk0\nTGUSMjEDe+5090BGqo" +
                                                       "AK6KTVQ1pKl5WEQsdVhtH0n7o1m3/+yVP1ttlVeON4beMXM3DfL91OHrv2\n" +
                                                       "yD9XCjZlElYE9xzuMvs4C1zO7aZJp1GPzOO/v/3FX9NTAFgAEpYyw0TeE3E0" +
                                                       "IgwZFvZdJ+jdvrlN\nSFqB98YSYV2k/o5Jhy8kW9MH3/ql0LqGegu51w+7qd" +
                                                       "Fme1XIRuhZTrJkgRePcLbJQLoIXbDYn77d\n1JoAZvde6d1br175N4gdAbES" +
                                                       "FEerzwTUyOS5Oru6vPLG628sevSdIAl0kWpVp3IXFQlAqiDymDUB\ngJMxHn" +
                                                       "hQqFE/NQ+psAsR2i7LWinjeQqBcmtKGKoTGiM3be7pPrmz7YmPNcj6XaRSN2" +
                                                       "VFoypUFKrp\nml0kWwpraV7Lgvm5rjTWdGE74cobG994LvZ23ynhkZIoU6Sa" +
                                                       "+vjMXB6avfk7/oHg46Y77l6VKURu\naMHcvfe9O9lQcfEHqQCpHCH1UrZJHK" +
                                                       "ZqGjNvBHoay+kcoZHMm8/vT+xi3JaDsxV+U3nE+oHGrRgw\nxtU4rvVFIfqa" +
                                                       "zIffwmwULvRHISGGWL0NyXZ8xEGHeHeHoHfa6BHiJIQ9MQc10MccuuH0uKpA" +
                                                       "KldY\nosfMwFRnPN4XF6AmiJEVa3PdgWSX9VXEIrLUcwHo1TXRIygSBkTGSQ" +
                                                       "y0UdhkCcQGhOzM9Idrb7T8\ntr7jmo0iE5ys8VgzuzIS1SZ1SQRDN9Vk6D9s" +
                                                       "UFlRVOAekxrQz13/01/2fXvDR1ex9Bk+1W375Gxa\n52Tz3Gwad2w6UMSmOO" +
                                                       "5CshNJN5IomDG0pz3eW2jFMsFnEMmwYNMjaJ9vkU9f0RAtnbO+I46+o/+D\n" +
                                                       "vtHerr5CfQOCz14kj5TUN+DXVyDnqjnrO+7oK81d34odnYPt0VihxkHBSUaS" +
                                                       "KKlx0MgUigrmRGWl\n5GErhN/tpa4E4jpz9KHPap+kv9pnt16N+W02Iu5H02" +
                                                       "+wtfd/68Mi3WJF9krnCgygvLx+b7e4KrkQ\nduL8jy7xdzZss+WtLw2//o3r" +
                                                       "t52ead72ysn/o8tr9lnAz7phcvlXghPKbwLiNmcjYsEtMH9TWz4O\nVoM+aV" +
                                                       "PUHRcNW7ydVnG/7fT7rXgPMX2LuUNI0uCMSYR5C1s6D96ILgbVOv/0jgXxra" +
                                                       "NHhPmq4NJN\nrV5X/4AiQq8sU7r6gktyzMaktfsu/f31y2ytaBTmKRbUi3Yz" +
                                                       "WeQC6tnzGb3Adr+XmBWNbmicWrat\n/Df3wot53n1bHPo2j2kNw4Drln2krf" +
                                                       "d8eYuBmNtcvEXsTBlcNHUzv1j8s/vPzn4gYFcwPuhhiWTS\nZ/Oy7IUGnxdz" +
                                                       "Uu8WCvuyL3Y+eQtHnSguBsk3kBxHchJaZeHIvoR9OKRHkHzTCwv2awCUavdq" +
                                                       "5mjW\nVPQOBzZZUvAFx/7qIMVuHNr7eewP/7Id43wZqIFmKpFWVW+594wrDC" +
                                                       "h7ijhIjV38DfHvOeg0CjXA\nWi0GQstn7aUvcFLjWQpHz468i17kJAiLcPg9" +
                                                       "9GyTJ7rjdEqg2Zhk/fHUU2eOf51/YXivKtWF51ht\nfmnwhfve6rnXBjwMU0" +
                                                       "42lPp4VvjNr+3WOeT5ILPp4tVPere/NhvIBsj8XCUq/k3BW4kcZ9/mhiGC\n" +
                                                       "Nb79IYcmGA2wefNWw1cN3AdRoTqQDIiaKwoZlos78nQXHxgd2Ez3Z0300Muj" +
                                                       "LZmTg08La5dLKp2Z\nwf3VkKr2LTQHvatKcnN4XScXXxl+9SdbHSvk3U8LlN" +
                                                       "9sz4rxlmI3C5EYxn8BqeP0h+wVAAA=");
    }
    private UI() { super(); }
    final public static void set(UserInterface ui) {
        if (ui ==
              null)
            ui =
              new SilentInterface(
                );
        UI.
          ui =
          ui;
    }
    final public static void verbosity(int verbosity) {
        UI.
          verbosity =
          verbosity;
    }
    final public static String formatOutput(Module m,
                                            PrintLevel level,
                                            String s) {
        return String.
          format(
            "%-5s  %-6s: %s",
            m.
              name(),
            level.
              name().
              toLowerCase(),
            s);
    }
    final public static synchronized void printDetailed(Module m,
                                                        String s,
                                                        java.lang.Object ... args) {
        if (verbosity >
              3)
            ui.
              print(
                m,
                PrintLevel.
                  DETAIL,
                String.
                  format(
                    s,
                    args));
    }
    final public static synchronized void printInfo(Module m,
                                                    String s,
                                                    java.lang.Object ... args) {
        if (verbosity >
              2)
            ui.
              print(
                m,
                PrintLevel.
                  INFO,
                String.
                  format(
                    s,
                    args));
    }
    final public static synchronized void printWarning(Module m,
                                                       String s,
                                                       java.lang.Object ... args) {
        if (verbosity >
              1)
            ui.
              print(
                m,
                PrintLevel.
                  WARN,
                String.
                  format(
                    s,
                    args));
    }
    final public static synchronized void printError(Module m,
                                                     String s,
                                                     java.lang.Object ... args) {
        if (verbosity >
              0)
            ui.
              print(
                m,
                PrintLevel.
                  ERROR,
                String.
                  format(
                    s,
                    args));
    }
    final public static synchronized void taskStart(String s,
                                                    int min,
                                                    int max) {
        ui.
          taskStart(
            s,
            min,
            max);
    }
    final public static synchronized void taskUpdate(int current) {
        ui.
          taskUpdate(
            current);
    }
    final public static synchronized void taskStop() {
        ui.
          taskStop();
        canceled =
          false;
    }
    final public static synchronized void taskCancel() {
        UI.
          printInfo(
            Module.
              GUI,
            "Abort requested by the user ...");
        canceled =
          true;
    }
    final public static synchronized boolean taskCanceled() {
        if (canceled)
            UI.
              printInfo(
                Module.
                  GUI,
                "Abort request noticed by the current task");
        return canceled;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1415385948000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAANVaC2wUxxmeu/PbJjbmGV4GYyCAueNl83AqMOZ15rAdG/Mw" +
       "AbPem7MX9naX3Tlz\nNigNiQQ0KGnoQ2qrhkBEy6OhQUoiWjWloIQ2DWqVpG" +
       "rSRoK2QkorpamCqqZUrar+/+ze7d7eAxcb\nqbU0s3M7M//8///9/z//zPrl" +
       "T0m+oZMpouFnAxo1/E0dbYJu0HCTLBjGFnjVLV7PL247u0lRvcQT\nIl4pzE" +
       "h5SDQCYYEJASkcCK5tiOtkvqbKA72yyvw0zvx75TqLXnOoLo3gtpOXKw+fya" +
       "vykvwQKRcU\nRWUCk1RlnUyjBiMVob1CvxCIMUkOhCSDNYTIKKrEok2qYjBB" +
       "YcZ+8gTxhUiBJiJNRmaEEosHYPGA\nJuhCNMCXD7TxZYHCGJ0yQVJouDG5HM" +
       "ysTZ0JbFvz2tNHA5Ei7NwK4nAOQOrpSalNadNE1XznttYf\nOnXeR8q7SLmk" +
       "dCAxESRhsF4XKYvSaA/VjcZwmIa7yGiF0nAH1SVBlgb5ql2k0pB6FYHFdGq0" +
       "U0OV\n+3FgpRHTqM7XTLwMkTIRZdJjIlP1pI4iEpXDiV/5EVnoBbHH22Kb4q" +
       "7H9yBgiQSM6RFBpIkpefsk\nBRCvcs9IylizCQbA1MIoZX1qcqk8RYAXpNLE" +
       "UhaU3kAH0yWlF4bmqzFYhZFJWYmirjVB3Cf00m5G\nJrrHtZldMKqYKwKnMD" +
       "LOPYxTApQmuVBy4DN//OfHzn37ympu23lhKsrIfwlMmuaa1E4jVKeKSM2J\n" +
       "d2P+rwV3xKZ4CYHB41yDzTGNsy53hv70kypzzOQMY1p79lKRdYstJ6raD25Q" +
       "iQ/ZKNJUQ0LwUyTn\n7tBm9TTENfDa8UmK2OlPdF5t/+mOJy/QT7ykJEgKRF" +
       "WORcGORotqVJNkqm+gCtUFRsNBUkyVcBPv\nD5JCaIfA5M23rZGIQVmQ5Mn8" +
       "VYHKf4OKIkACVVQMbUmJqIm2JrA+3o5rhJBRUEgllBJi/vEnI4/4\nA0ZMic" +
       "jqgYChiwFV77V/DxiMRgOdQT9ajMbI2kCfGqUBQRQUSVEDvRL4qKguCNN+fA" +
       "6RThx5qjzg\n8WCQczurDHa+UZXDVO8Wz95+59C6TV86ZhoCGq8lDdgVkPdb" +
       "5P0meX9nkHg8nOpYXMYEANS3DxwR\nQlbZ3I5dzXuOVfsAee1AHsiOQ6uBb2" +
       "vtdaLaZHtrkAc2EUxm4ks7j/rvnl1lmkwge1DNOLv03Ys3\nTv21bJ6XeDNH" +
       "PJQJYm4JkmnDMJmMZDVuH8lE/y/PbH71gxs3H7G9hZGaNCdOn4lOWO3Wvq6K" +
       "NAxh\nzSZ/5uFy3zay9YSX5IFnQzTj/EOgmOZeI8UZGxKBDWUpDJHSiKpHBR" +
       "m7EtGohPXp6gH7DTeLCt4e\nA+CUoXU+BGWCZa78ib3jNKzHm2aEaLuk4IHz" +
       "7tMFCz98o/Q6V0sixpY7drEOykyPHW0byxadUnh/\n8xttX/36p0d3ckuxTI" +
       "WRQk2X+sFH4zBntj0HfFWGeIFI1nQqUTUsRSShR6Zocv8qn7Xo9T8/V2Fi\n" +
       "I8ObBLS19yZgv394DXnyxu6/T+NkPCLuFbYc9jBTnDE25UZdFwaQj/jh96d+" +
       "82fCCxDKIHwY0iDl\nEYFw0QhXZIArfh6v/a6+RVhVA+3aLLafYWfuFg9d6K" +
       "2O7f/5DznXpYJzi3fisFnQGkzosZqJ2p3g\ndt+NgtEH45ZebXm8Qr76T6DY" +
       "BRRF2BGNVh1CRTwFRWt0fuFH194cv+c9H/GuJyWyKoTXC9wBSDFY\nHjX6IM" +
       "rEtVWruXFVHCjCmotMuBImWQqIO375gLm52f1/Pe7rtut099SeC73T+gJXQF" +
       "bPz7CtuegM\nXuk8efcX7BanY7sgzp4RTw+hkAvZc5d/0D+64NKLUS8p7CIV" +
       "opWtbRXkGBp6FyQXRiKFg4wupT81\nUTB3xYZkiJnidn/Hsm7nt0M3tHE0ts" +
       "tc/o6FfAFKqeXvpW5/9xDeWMWn1PB6jumdXmzPZaTA4Klc\nnBFvTOIDJjAy" +
       "PdNWYVDg1kqrzDiC9WKsVpuI12W1jJWpPNcmglXimYHnYDaesWoCfotEVJxM" +
       "w2Bh\nE52Jvy5FIYHo50H19pHqH7/d+eJRcyPKYYgps7rFL/7u9/t8X77WY8" +
       "5zW5tr8IlpZz5+9Xb7WDNm\nmZnmzLRkzznHzDY5nOUa+u+MXCvw0W/Nn/Hy" +
       "E+23LI4qU3OmdXCu+OPAm3TOo8/+IcPWX9ijqjIV\nFBdszf8lbLMSKVHimQ" +
       "G2HfeCrbif6j2Y4g3wMS2ayUQHIz7I2l0cduXgMJ5hJQZeJCkCOFuBFuuR\n" +
       "wa5TAhMoemq2JJor+ej2O2VHhLd2ea0wvgX4Zaq2QKb9VHaQKkNKKWnQZn5s" +
       "sKPIM+e/d5m9N3+l\nCde87Ibnnjhv5anBqpWvHL+P5KfKJZub9Oj+yY/5+q" +
       "S3vfxkYwaltBNR6qSG1FBUAvzEdGVLSkCa\nnrSSiajlxVCmW1YyPXMCkgac" +
       "D9ucZMS0E9fW6rGSz6HGJ75OLMf+PIAVeIYPjgNuO8zrV6WwbYP7\n7+UlSe" +
       "vCH3KqMuqgzLaUMXtklYE/d3MaR3IIegyrw063wxcHbfGeGo54j0GZZ4k3b4" +
       "TE81l5g4X1\nlIzHlprNajgm05wGEayBKAqbdNJ3YWAFz3lwe/ab53jO5Vdy" +
       "KPBbWD3LSBlPx1lrjGkxlpWcrdfn\n7lOvGGTJTih+S6/+Ieo136VXYNkYUE" +
       "Q4MSiQvcKWVNgP0ULvNUZC4emCw6HCsQnzPBrDxPnn145p\nX7HzaR7OigVZ" +
       "EowWO554pTC2PBC8ZmUPkUli3eKcXZc/u3aFzuH5bJFkQArVqPdmuBxxzLkj" +
       "XKCb\nP4yc5OexvB7BMGOX+1Yp/dIo5S6Iq/2hJEwhRKU9F0wZVGVmhJqmwd" +
       "mohOtiUd3iJQtBd5WgO7yJ\n9Ethf0gVBTm49vS10vdPxOqbzR1klGNAcO2h" +
       "S81lRaePH+GR3FJisePKxfqNiLfYiQA+XmNk80jc\nSKxcWl+7rH7BioUQL7" +
       "lRIfFzOfzoR1idZmSUhk65Fvc1yN1cweil4TjNNihLLDSW3KfTYPUdrL77\n" +
       "YLwEOy4m2d6IXLbkYnvIRrSEj3wdq1+OFMR1i2qXLV6wvN4J8fUcEP8Kq6uw" +
       "13CIg9a1mgPea8OB\ntwvKcktPy/8v4N2U2CKzsj1keOtteD8eMXjBg+sWLF" +
       "/hhPdmDng/weo3sK1weLcJOize60L4t8NB\neDsxj7Mk8fyfRziIXLbmYnvI" +
       "CK+wEf73SCFcDw68ZMHyZU6E/5YdYQ9PzT4DrjjC63Rd1V343hkO\nvqioNZ" +
       "ai1gwR37xM+N4D2jTU8P1uXnExR+VQwWisivDYJxj7Opigs1QNeIqHo4FGKO" +
       "stDawfSQ24\nzwSeyTlknIrVeIAZZezUIK2gLiEnDEfIpVCaLSGbR1JIpwxz" +
       "cvThXYOnmpEiE0NVc0k3czjS1UPp\ntKTrfFDSLcnRV4eV30Kvid+BueQLDE" +
       "e+Bih7LPn2PCj5VuXoa8RqJWwztnxmnthuS9gwVAnxXrMz\niJf8E9O+cZvf" +
       "ZcXQRwcf/zz063+Yx4PEt9NSyKYjMVl23sM62gWaTiMS10epeSurceY3MlKZ" +
       "vrHg\nNStvIG+eDebQTYyUOobC+cxqOQe1MOKDQdhs1bLuJam3XCjtzJTTFP" +
       "9XgsSlUMz8Z4JucfvFndPj\nx7c8z49m+aIsDA4imRI4N5ifnqyDj/N20k0t" +
       "QetdcumVrW98f0Xi4ox/mRjrsJcUk1ts9ubAUCdV\nmT/3rItqjH+gGfzBhN" +
       "cePXvylpd/cfoPzgMiMQEiAAA=");
}
