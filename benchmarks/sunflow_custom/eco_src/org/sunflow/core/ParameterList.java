package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point2;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import java.util.Map;
import java.util.HashMap;
public class ParameterList {
    final protected java.util.Map<String,Parameter> list;
    private int numVerts;
    private int numFaces;
    private int numFaceVerts;
    private enum ParameterType {
        STRING, INT, BOOL, FLOAT, POINT, VECTOR, TEXCOORD, MATRIX, COLOR;
         
        private ParameterType() {
            
        }
        final public static String
          jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long
          jlc$SourceLastModified$jl =
          1414698290000L;
        final public static String
          jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1Ze2wUxxkf38NvsDHhTbBxIIRH7gBBCzhSYg4bH5x97tkY" +
           "YkOc8d6cvbC3u9md\nsw9D06REgUBLE/WhRiouTZF4pCS0SUUfEYGmoSW0Eq" +
           "nUREJN1KhSUylJ1ahSStX+0e+bvb3b27sj\nR5H83e7szPf9vud8M/zoE+I3" +
           "DbJQMgN8v87MQKivlxomi4cUapr9MDQsvemv6T29XdU8pCJCPHKc\nk4aIZA" +
           "bjlNOgHA+Gt7SlDbJS15T9o4rGAyzNA3uV9Rl+2yLrCxjunLrY9OQpX7OH+C" +
           "OkgaqqximX\nNbVDYUmTk8bIXjpOgykuK8GIbPK2CJnG1FQypKkmpyo3HyOP" +
           "E2+EVOoS8uRkccQWHgThQZ0aNBkU\n4oO9QixwmGkwTmWVxduz4mDlqvyVAD" +
           "uzLlY4G5hU48cBUEcgAK1bslpb2haoqnvPDHzh4MmzXtIw\nSBpktQ+ZSaAJ" +
           "B3mDpD7JkiPMMNvjcRYfJDNUxuJ9zJCpIk8KqYOkyZRHVcpTBjNjzNSUcZzY" +
           "ZKZ0\nZgiZ9mCE1Euok5GSuGZkbZSQmRK33/wJhY6C2rNzalvqduI4KFgrAz" +
           "AjQSVmL/Htk1XweLN7RVbH\nJdthAiytSjI+pmVF+VQKA6TJ8qVC1dFgHzdk" +
           "dRSm+rUUSOFkfkmmaGudSvvoKBvmZK57Xq/1CWbV\nCEPgEk5muacJTuCl+S" +
           "4vOfyzcvZnR85879JDIrZ9cSYpiL8WFi1yLYqxBDOYKjFr4a1U4Fvhh1ML\n" +
           "PYTA5Fmuydac9qUXd0T+9nqzNWdBkTnRkb1M4sNSz3PNsQNbNeJFGNW6Zsro" +
           "/DzNRTr0Zr60pXXI\n2tlZjvgxYH+8HLv68BPn2EceUhsmlZKmpJIQRzMkLa" +
           "nLCjO2MpUZlLN4mNQwNR4S38OkCp4jEPLW\naDSRMBkPE58ihio18Q4mSgAL" +
           "NFENPMtqQrOfdcrHxHNaJ4TUwx/xw988Yv2bg4STNYGgmVITijYR\nNA0pqB" +
           "mj2XdJMxg41sCwYQbmfQBDR+ekOzimJVmQSlSVVS04KkOyStr9cTaOv3fKMI" +
           "0omyYqKrDs\nudNXgcjv0pQ4M4al039562DH9meOWKGB4ZzRD8oGyAlk5ARQ" +
           "TiBPzpLsG/qYVFQIYXehdMtTYOd9\nkLEwtX55355tjx5p9UKI6BM+MBJObQ" +
           "W9MpA6JC2US+uwqIASxNbcF4YOB26dftCKrWDp6lt0dd2N\n89dP/rN+hYd4" +
           "ipdGVBWKcy2yEdpkS94SdzIV4//3o92vvHP9vftyacXJkoJsL1yJ2drqdoqh" +
           "SSwO\n9S/H/tS8Bu9OMvCch/igBEDZE/ihoixyy8jL2ja7AqIuVRFSl9CMJF" +
           "Xwk122avmYoU3kRkS0NCK5\nywocdKQLoCietw5Vrn73tbo3hcZ2nW1w7GR9" +
           "jFtZOyMXB/0GYzD+3nd7v/ntTw4PiSDIRAEnVboh\nj0OepmHNvbk1kK8K1A" +
           "x00pIdalKLywmZjigMo+m/DUvX/PTj442W2RUYsb226vMZ5MbnbSZPXH/k\n" +
           "X4sEmwoJ94ucHrlpljozc5zbDYPuRxzpJ/9w9/O/oSegnEEJMeVJJqoCEaoR" +
           "YciAsO9yQe93fVuN\npBV4ryoR1kV252Hp4LnR1tRj134uUNdR5zbv9EM31d" +
           "ssrwrZM0FoM8mQOc5qhV9n6UhnowvmuNO3\ni5pjwGzd5Z7djcrl/4DYQRAr" +
           "wdZpRg2oIOk8V2dm+6tuXnlj9qNve4mnk9QqGo13UpEApAYij5lj\nUHzS+o" +
           "MPCRiNE9VIhV2IQDs/Y6W0460GwC0tYagOaJtyabO269jWtqc+UiHrt5EqzY" +
           "jLKlVgv6Gq\nplpbaEvhTpvX0GB+Li9dazqx2cjJGx5ZdSbyVvSE8EjJKlNk" +
           "r3Xxmby0Y+rW7/n7gk8u3XH14nRh\nFYcGLbd2wzvjMyovfD/pIVWDpFHKtJ" +
           "ADVElh5g1Cx2PafSW0mXnf87sXa6tuy5azhW5TOcS6C01u\n94BnnI3P9a4o" +
           "RF+T6fA3NxOFc91RSIguZm9Cshlf8SEkxu4R9F6revg48WHHzAEG+phDr5wa" +
           "UWRI\n5UpTdKBpeOrrj4V7toqqJoiekWux3YJkm/llLEZknuN80KOpooWQJY" +
           "yItJ0ZaKSAwRJYHLBmp/d/\nsOxmy+8aQ9etMjLGyVKHOTMzg2F1XJNENHRR" +
           "NQ7tiVVVFhYVuNOgOrR7N/781z3fWPnhVdz7dBd0\ny0BZo4rkmVm2UWO2Uf" +
           "uKGBWfO5EIm3UhCYMdveGe/kIjVgg24sOA4LJd0KhrkgvuLJLpmcqDO2jD\n" +
           "HSofrm9zNBopxOsRfHYjeaQkXo8b7wIcbSkb74iNVyofr78zEm0vYmCvYBRH" +
           "kigJ2OsG3Iqjy8oG\nvNcGvO8OAPdGi0aETzAS5wu1JGCfG/ByHA2WDdiwAZ" +
           "vlA64c6Aj1R2OFiP2Ck+ghxksi9rsRr8HR\njWUjnrQRHygfcXV/x65QNBrb" +
           "Uoi5UvA6iOTxkpgr3ZjbcLS9bMxftTEfugMrd7dDvd1ViLhKcHoK\nydMlEV" +
           "e5EYdwtKtsxEdtxMfuIJBD0UixsKgWjL6G5HhJwNV6ulCSNyvpPktIXlMDZf" +
           "/uUid1cctw\neNen9U/TX++xzjxN+adfbHU+3P8GW/bA1z8ocmSrzNy05AR6" +
           "UF7eQatb3GDkeoejZ1+8yN9eucmS\nt6J03+NeuGLTycnmTS8f+z+OV80uC7" +
           "hZzxhf8CXvmPxbj7hksVqRgsuZ/EVt+Q1ILeBJGaLhwxGr\nDWlBYh9xivvN" +
           "GRy3ad5/cJtvP0RyApwxjv2ViWcpxz4vjg8I6+yzW2bGNg4dEuaroYpMzZ4c" +
           "fo8s\nin5FunTbCy7JMhuWlu25+I8rl9gy0aFXyyY0au3GaJF7IceaT+k51v" +
           "1uYkqcMH0j1LRs5b5QK7wv\ny7sGE0pPd5hW13XCSa1Qae3aL67boGOz01z8" +
           "cNaR1Lk4Tk3+bM6rD5yeel/0O4Lz8w6eSKZcRq/I\nXCXg+xxOGnMdmnUJJ1" +
           "a+dBtP/aS4GCTnkfwYyStwSBWejCYs7ZC+iORVZ12whqGgTMu7F7HBLbr9\n" +
           "ZQrYZ27BLat1MyhFbh7Y/Vnkj/+2vGTf3tXBkSaRUhRn0+14rtSh95SFUnVW" +
           "C66Ln1+CndxQoF3C\nHwH1F9a0S5zUOaaBCTJPzklXoC2ESfj4K/TwLEeYx+" +
           "iEKGvDkvmnE8dPPfMV/rlxvrjUOTjLas0L\n/d/ZcG37OqvyYbxysrLU5Xbh" +
           "nXzb7ZPJcWG6+sLVj3s2vz7lyQTKtOyOVPzOz7kj2R6fngtHrNo4\neo1nDL" +
           "Bx9cYNumtfyL3U4GMISR+SISQSkn1IxMQDYl8WWx3uKPfkaSX+a8CurKnejP" +
           "F2nR9qSR/r\nf1b4wS8pdHIS19dCNls3RNnqvLgkN5vXDXLh5YHXXtpo2yfv" +
           "7qhAqzXWV/G8vtipX6SO/j+wGiKq\nphkAAA==");
    }
    public enum InterpolationType {
        NONE, FACE, VERTEX, FACEVARYING; 
        private InterpolationType() {  }
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1414698290000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1YeWwU1xl/3l3b+Ag+uMFg40AoR3aBhjbGkRJjfCwstmsb" +
                                                       "AwbiPM+8tQdmZ4aZ\nt/ba0DYRabjaNFejpiqURkgcbRqktqKVIkKUhjahlU" +
                                                       "ilJhJqokaVmkpJqkaVUqr2j37fm5md2dld\n4taSv515x3d/v/e9+fEnpNQy" +
                                                       "SYNkRfmUwaxo+0AfNS0mt6vUsgZhaER6o7Si7/x2TQ+RkgQJKTIn\nNQnJis" +
                                                       "mU05gix+JbWzMmWWvo6tSYqvMoy/DoAXWTw29bYlMew11nrtQ/di7SGCKlCV" +
                                                       "JDNU3nlCu6\n1qGylMVJbeIAnaCxNFfUWEKxeGuC3MW0dKpd1yxONW4dIl8j" +
                                                       "4QQpMyTkycnyhCs8BsJjBjVpKibE\nx/qEWOAwx2ScKhqT27LiYOe63J2gtr" +
                                                       "OvP381MJmFk0NgjtAArG7KWm1bm2eqEb4w9KUjZy+GSc0w\nqVG0AWQmgSUc" +
                                                       "5A2T6hRLjTLTapNlJg+TOo0xeYCZClWVaSF1mNRbyphGedpkVj+zdHUCF9Zb" +
                                                       "aYOZ\nQqY7mCDVEtpkpiWum1kfJRWmyu5baVKlY2D2fM9s29xOHAcDKxVQzE" +
                                                       "xSiblbIgcVDSLeGNyRtXHF\ndlgAW8tTjI/rWVERjcIAqbdjqVJtLDbATUUb" +
                                                       "g6WlehqkcLK4KFP0tUGlg3SMjXCyMLiuz56CVRXC\nEbiFk3nBZYITRGlxIE" +
                                                       "q++Kyd/9nxC9+/+pDI7YjMJBX1r4RNywKb+lmSmUyTmL3xdjr6XHxPuiFE\n" +
                                                       "CCyeF1hsr2lbeWVn4q+vNtprlhRY0zt6gEl8ROp5urH/cJdOwqjGLEO3FAx+" +
                                                       "juWiHPqcmdaMAVU7\nP8sRJ6Pu5LX+63sevcQ+CpHKOCmTdDWdgjyqk/SUoa" +
                                                       "jM7GIaMylncpxUME1uF/NxUg7PCUh5e7Q3\nmbQYj5OIKobKdPEOLkoCC3RR" +
                                                       "BTwrWlJ3nw3Kx8VzxiCEVME/KYX/pcT+W4KEkw3RmJXWkqo+GbNM\nKaabY9" +
                                                       "l3STcZBNbEtGEm1n0UU8fgZEdsXE+xGJWopmh6bEyBYpX0e2U2gb//K8MMal" +
                                                       "k/WVKCsBcs\nXxUyv1tXZWaOSOf//NaRju0njtupgens2MfJepATdeREUU40" +
                                                       "R86KOFYR8BYljHEmJSVC4FzUwI4W\n+PogVC0sr149sH/bI8ebw5AmxmQEHI" +
                                                       "VLm8E2R60OSW/3SjsuUFCC/Fr44t5j0dvnH7TzK1YcgQvu\nrrr50o2z/6he" +
                                                       "EyKhwvCI5gJAVyIbYV8W9lYEC6oQ/7+d3PHTd2689wWvtDhZkVfx+TuxYpuD" +
                                                       "gTF1\nicmAgR77c4tqwrvI0NMhEgEYAOgT+gOqLAvKyKncVhcF0ZbyBKlK6m" +
                                                       "aKqjjlQlclHzf1SW9EZEwt\nkrl28mAgAwoKAL19tGz9u69UvSEsdrG2xnea" +
                                                       "DTBuV26dlweDJmMw/t53+579zifH9ookcLKAk3LD\nVCagVjOw5x5vD9SsCr" +
                                                       "iBQVqxU0vpspJU6KjKMJv+U7Nyw88/frLWdrsKI27U1n0+A2980Rby6I2H\n" +
                                                       "/7lMsCmR8Mzw7PCW2ebM8Ti3mSadQj0yj/1+6Qu/pqcB0gBGLGWaCWQgwjQi" +
                                                       "HBkV/l0t6L2BufVI\nmoH3uiJpXeCEHpGOXBprTh9685dC6yrqP+r9cdhBjV" +
                                                       "Y7qkL2HBDaTByyxI9YODvPQDofQ7AgWL7d\n1BoHZvdd69lXq177N4gdBrES" +
                                                       "HJ9WrwkokskJtbO6tPzWa6/Pf+TtMAl1kkpVp3InFQVAKiDzmDUO\nAJQxHn" +
                                                       "xIqFE7OQup8AsR2i52vJTxvUVAuZVFHNUBrZNXNhu7T3W1Pv6RBlW/jZTrpq" +
                                                       "xoVIUzh2o2\nUnHSlH/a5jQ1WJ+ri2NNJzYcnryR0XUXEm/1nhYRKYoyBc7b" +
                                                       "AJ/pqzvP3P4df1/w8coddy/P5CM5\nNGne3vvfmagru/yDVIiUD5NayWkjh6" +
                                                       "iaxsobhq7HcntLaDVz5nM7GPu4bs3CWUPQVT6xQaDxThB4\nxtX4XB3IQow1" +
                                                       "qYb/BicLG4JZSIghVm9GsgVf8aFdjN0t6D02ekQ4iWDXzEENjDGHfjk9qipQ" +
                                                       "ymWW\n6EIzsKKnt6dDYJoghiPVZroVyTbrqwhFZJHvhtCja6KJUCTMh4xbF+" +
                                                       "iiqMmSCA2I2JmpD1bdavpt\nbfsNG0TGOVnpc6azMhbXJnRJ5EI31WRoUGxM" +
                                                       "aSgocJdJDWj4bv7pL/u/vfbD63jyGQHVbfdkXTob\nB+tn7NJ+16UDBVyKz5" +
                                                       "1IupB0I4mjFzvb2gt4sUTwGUQyJNhsF7Q3sCig71wcXTxjfYddfffOXN+y\n" +
                                                       "oY7+wY7d+RqHBKd9SB4uqnEoqLFQcNWMNR51NZZmrnEVeniorX9PvKcrX+2w" +
                                                       "YCcjSRZVO2xk8uWF\ns/IcUTkIC1m4tNjVQVx7ju3+tPoJ+qv9dgNWn9uOI+" +
                                                       "5+OPU6W/XAtz4o0EOWOVc/T2AI5eV0fTvE\nlcoDspMXf3SFv712sy1vTXEQ" +
                                                       "Dm5cs/nsdOPml0/9H71eY8ADQdZ1E0u+Eh5XfhMStz4bF/Nui7mb\nWnPRsB" +
                                                       "L0SZvi9PEwscnfbxWOW1cwboU7iak7zB1GkoZgTCDYW9jY+WBH9DKo1sWnts" +
                                                       "7pb9l7VLiv\nAi7n1Orx9A8pIvVKMsXPYAhJltmItGr/lb+/dpWtEu3CLMWC" +
                                                       "U6PNHCtwUfXt+ZReYjveTZ4R7W5k\nlFq2r4I3/PwLfM69XBg92+dawzDgWl" +
                                                       "YpTNq48cubvmgg9jYW7hQ7UgYXvd30Lxb87IHzZ94X8Cs4\nH/LxRDIRcHqJ" +
                                                       "c6/B9wWc1HoHhv1VQOx84g6ROllYDJJvIDmB5BR0zCKSvUnbOqRHkXzTjwv2" +
                                                       "MMBK\nXd5FzVVw2Z1veOCjhXmffuzPFVLi1uF9nyX+8C87Uu4nhSrosZJpVf" +
                                                       "V3Ab7nMgOOQ0UYVmX3BIb4\neRZ8FVQFDhz8Eao+Yy97HiDStwzc4Dz5F73A" +
                                                       "SRgW4eP3MMrzfKneTycFtI1I1h9PP3nuxNf55+b6\n8mKNeZbVhhcHn7//ze" +
                                                       "332eiHOcvJ2mJf3PI/FLbeuaB8X3HWX77+cc+WV8+EnGS5K3s2Ff4Q4T+b\n" +
                                                       "3IjP9lISkRtHf8gdB7Ssb2kxAmeD9yIOrXYkA+IgFmcbHh535ygvPku6IJru" +
                                                       "c3y0+6W9TZlTg08J\nd5dKKp2exv2VULj2zTQLxMuLcnN53SSXXx565Sctrh" +
                                                       "ty7qx5ym+wZ8XzpkK3DVElxn8B4AV4gyIW\nAAA=");
    }
    public ParameterList() { super();
                             list = new java.util.HashMap<String,Parameter>(
                                      );
                             numVerts = (numFaces =
                                           (numFaceVerts =
                                              0));
    }
    public void clear(boolean showUnused) {
        if (showUnused) {
            for (java.util.Map.Entry<String,Parameter> e
                  :
                  list.
                   entrySet()) {
                if (!e.
                       getValue().
                       checked)
                    UI.
                      printWarning(
                        Module.
                          API,
                        "Unused parameter: %s - %s",
                        e.
                          getKey(),
                        e.
                          getValue());
            }
        }
        list.
          clear();
        numVerts =
          (numFaces =
             (numFaceVerts =
                0));
    }
    public void setFaceCount(int numFaces) {
        this.
          numFaces =
          numFaces;
    }
    public void setVertexCount(int numVerts) {
        this.
          numVerts =
          numVerts;
    }
    public void setFaceVertexCount(int numFaceVerts) {
        this.
          numFaceVerts =
          numFaceVerts;
    }
    public void addString(String name, String value) {
        this.
          add(
            name,
            new Parameter(
              value));
    }
    public void addInteger(String name, int value) {
        this.
          add(
            name,
            new Parameter(
              value));
    }
    public void addBoolean(String name, boolean value) {
        this.
          add(
            name,
            new Parameter(
              value));
    }
    public void addFloat(String name, float value) {
        this.
          add(
            name,
            new Parameter(
              value));
    }
    public void addColor(String name, Color value) {
        if (value ==
              null)
            throw new NullPointerException(
              );
        this.
          add(
            name,
            new Parameter(
              value));
    }
    public void addIntegerArray(String name,
                                int[] array) {
        if (array ==
              null)
            throw new NullPointerException(
              );
        this.
          add(
            name,
            new Parameter(
              array));
    }
    public void addStringArray(String name,
                               String[] array) {
        if (array ==
              null)
            throw new NullPointerException(
              );
        this.
          add(
            name,
            new Parameter(
              array));
    }
    public void addFloats(String name, InterpolationType interp,
                          float[] data) {
        if (data ==
              null) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create float parameter %s -- invalid data length",
                name);
            return;
        }
        this.
          add(
            name,
            new Parameter(
              ParameterType.
                FLOAT,
              interp,
              data));
    }
    public void addPoints(String name, InterpolationType interp,
                          float[] data) {
        if (data ==
              null ||
              data.
                length %
              3 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create point parameter %s -- invalid data length",
                name);
            return;
        }
        this.
          add(
            name,
            new Parameter(
              ParameterType.
                POINT,
              interp,
              data));
    }
    public void addVectors(String name, InterpolationType interp,
                           float[] data) {
        if (data ==
              null ||
              data.
                length %
              3 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create vector parameter %s -- invalid data length",
                name);
            return;
        }
        this.
          add(
            name,
            new Parameter(
              ParameterType.
                VECTOR,
              interp,
              data));
    }
    public void addTexCoords(String name,
                             InterpolationType interp,
                             float[] data) {
        if (data ==
              null ||
              data.
                length %
              2 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create texcoord parameter %s -- invalid data length",
                name);
            return;
        }
        this.
          add(
            name,
            new Parameter(
              ParameterType.
                TEXCOORD,
              interp,
              data));
    }
    public void addMatrices(String name, InterpolationType interp,
                            float[] data) {
        if (data ==
              null ||
              data.
                length %
              16 !=
              0) {
            UI.
              printError(
                Module.
                  API,
                "Cannot create matrix parameter %s -- invalid data length",
                name);
            return;
        }
        this.
          add(
            name,
            new Parameter(
              ParameterType.
                MATRIX,
              interp,
              data));
    }
    private void add(String name, Parameter param) {
        if (name ==
              null)
            UI.
              printError(
                Module.
                  API,
                "Cannot declare parameter with null name");
        else
            if (list.
                  put(
                    name,
                    param) !=
                  null)
                UI.
                  printWarning(
                    Module.
                      API,
                    "Parameter %s was already defined -- overwriting",
                    name);
    }
    public String getString(String name, String defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  STRING,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getStringValue();
        return defaultValue;
    }
    public String[] getStringArray(String name,
                                   String[] defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  STRING,
                InterpolationType.
                  NONE,
                -1,
                p))
            return p.
              getStrings();
        return defaultValue;
    }
    public int getInt(String name, int defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  INT,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getIntValue();
        return defaultValue;
    }
    public int[] getIntArray(String name) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  INT,
                InterpolationType.
                  NONE,
                -1,
                p))
            return p.
              getInts();
        return null;
    }
    public boolean getBoolean(String name,
                              boolean defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  BOOL,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getBoolValue();
        return defaultValue;
    }
    public float getFloat(String name, float defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  FLOAT,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getFloatValue();
        return defaultValue;
    }
    public Color getColor(String name, Color defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  COLOR,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getColor();
        return defaultValue;
    }
    public Point3 getPoint(String name, Point3 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  POINT,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getPoint();
        return defaultValue;
    }
    public Vector3 getVector(String name,
                             Vector3 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  VECTOR,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getVector();
        return defaultValue;
    }
    public Point2 getTexCoord(String name,
                              Point2 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  TEXCOORD,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getTexCoord();
        return defaultValue;
    }
    public Matrix4 getMatrix(String name,
                             Matrix4 defaultValue) {
        Parameter p =
          list.
          get(
            name);
        if (this.
              isValidParameter(
                name,
                ParameterType.
                  MATRIX,
                InterpolationType.
                  NONE,
                1,
                p))
            return p.
              getMatrix();
        return defaultValue;
    }
    public FloatParameter getFloatArray(String name) {
        return this.
          getFloatParameter(
            name,
            ParameterType.
              FLOAT,
            list.
              get(
                name));
    }
    public FloatParameter getPointArray(String name) {
        return this.
          getFloatParameter(
            name,
            ParameterType.
              POINT,
            list.
              get(
                name));
    }
    public FloatParameter getVectorArray(String name) {
        return this.
          getFloatParameter(
            name,
            ParameterType.
              VECTOR,
            list.
              get(
                name));
    }
    public FloatParameter getTexCoordArray(String name) {
        return this.
          getFloatParameter(
            name,
            ParameterType.
              TEXCOORD,
            list.
              get(
                name));
    }
    public FloatParameter getMatrixArray(String name) {
        return this.
          getFloatParameter(
            name,
            ParameterType.
              MATRIX,
            list.
              get(
                name));
    }
    private boolean isValidParameter(String name,
                                     ParameterType type,
                                     InterpolationType interp,
                                     int requestedSize,
                                     Parameter p) {
        if (p ==
              null)
            return false;
        if (p.
              type !=
              type) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requested as a %s - declared as %s",
                name,
                type.
                  name().
                  toLowerCase(),
                p.
                  type.
                  name().
                  toLowerCase());
            return false;
        }
        if (p.
              interp !=
              interp) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requested as a %s - declared as %s",
                name,
                interp.
                  name().
                  toLowerCase(),
                p.
                  interp.
                  name().
                  toLowerCase());
            return false;
        }
        if (requestedSize >
              0 &&
              p.
              size() !=
              requestedSize) {
            UI.
              printWarning(
                Module.
                  API,
                "Parameter %s requires %d %s - declared with %d",
                name,
                requestedSize,
                requestedSize ==
                  1
                  ? "value"
                  : "values",
                p.
                  size());
            return false;
        }
        p.
          checked =
          true;
        return true;
    }
    private FloatParameter getFloatParameter(String name,
                                             ParameterType type,
                                             Parameter p) {
        if (p ==
              null)
            return null;
        switch (p.
                  interp) {
            case NONE:
                if (!this.
                      isValidParameter(
                        name,
                        type,
                        p.
                          interp,
                        -1,
                        p))
                    return null;
                break;
            case VERTEX:
                if (!this.
                      isValidParameter(
                        name,
                        type,
                        p.
                          interp,
                        numVerts,
                        p))
                    return null;
                break;
            case FACE:
                if (!this.
                      isValidParameter(
                        name,
                        type,
                        p.
                          interp,
                        numFaces,
                        p))
                    return null;
                break;
            case FACEVARYING:
                if (!this.
                      isValidParameter(
                        name,
                        type,
                        p.
                          interp,
                        numFaceVerts,
                        p))
                    return null;
                break;
            default:
                return null;
        }
        return p.
          getFloats();
    }
    final public static class FloatParameter {
        final public InterpolationType interp;
        final public float[] data;
        public FloatParameter() { this(InterpolationType.
                                         NONE,
                                       null);
        }
        public FloatParameter(float f) { this(InterpolationType.
                                                NONE,
                                              new float[] { f });
        }
        private FloatParameter(InterpolationType interp,
                               float[] data) {
            super();
            this.
              interp =
              interp;
            this.
              data =
              data;
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698290000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK1YfWwUxxWfu/O3DWcMGOKAjY0JBcwdHzEfNlXi+AMOzmBs" +
           "vmKgl/Hu3Hlhb3ez\nO2cfDqKJIgENShOaRkqlhpAKCXChQSIVrZRSUJI2Da" +
           "qUIDWRIoW2QmortakaVUqp2j/6Zmb3dm/v\nbJq0lrw3O/Pmzbx5v/d7b/bS" +
           "Z6jUMtECyYrQIwaxIt1DA9i0iNytYsvaBV0J6d3SyoHz2zQ9iAJx\nFFRkis" +
           "JxyYrKmOKoIkdjPZ1ZE60wdPVIStVphGRp5JDabuvbGm8vULj3zLW6Z86VNA" +
           "VRaRyFsabp\nFFNF13pVkrYoqo0fwmM4mqGKGo0rFu2MoxlEy6S7dc2iWKPW" +
           "k+gYCsVRmSExnRQ1x53Fo7B41MAm\nTkf58tEBvixomG0SihWNyF255WBmW/" +
           "5M2LY9b7BQGpRUsME9YA7fAVi9KGe1sLbAVCN0Yc+6o2cv\nhlB4GIUVbYgp" +
           "k8ASCusNo5o0SY8Q0+qSZSIPo1kaIfIQMRWsKhN81WFUZykpDdOMSaxBYunq" +
           "GBOs\nszIGMfmaTmcc1UjMJjMjUd3MnVFSIarsvJUmVZwCs+tds4W5fawfDK" +
           "xSYGNmEkvEmVJyWNHA403+\nGTkbW7eBAEwtTxM6queWKtEwdKA64UsVa6no" +
           "EDUVLQWipXoGVqGoYUql7KwNLB3GKZKgaL5fbkAM\ngVQlPwg2haK5fjGuCb" +
           "zU4POSxz8r6r84eeH71x/l2C6RiaSy/VfBpEbfpEGSJCbRJCIm3stEvht7\n" +
           "PLMgiBAIz/UJC5muJdd2x//08yYh82ARmR0jh4hEE9L2002DT23WUYhto8LQ" +
           "LYU5P89yHg4D9khn\n1oCorc9pZIMRZ/DG4C8ef3qS/DmIqmKoTNLVTBpwNE" +
           "vS04aiEnMz0YiJKZFjqJJocjcfj6FyaMcB\n8qJ3RzJpERpDJSrvKtP5OxxR" +
           "ElSwI6qEtqIldadtYDrK21kDITQf/tFChIIzEf8TvxStjkStjJZU\n9fGoZU" +
           "pR3Uzl3iXdJOBYk8GGmCzuIww6BkX90VE9TaJYwpqi6dGUAsEq6StlMsZ+v6" +
           "zCLNtl3Xgg\nwGjPH74qIH+LrsrETEjn775/tHfbt04KaDA42/ZRtBLWidjr" +
           "RNg6kbx1WvtUHdNcFwoE+Gpz2PLC\nVXDQhyFkQbZm2dDBrU+cbAkBRozxEj" +
           "ilEIi2gGH2nnolvduN6xinQAnANf8H+09E7p1/RIArOjX9\nFp1d/cHlW2f/" +
           "XrM8iILFuZHZCuxcxdRwS3Kc1+qPpmL6//pc/9WPbn36NTeuKGotCPfCmSxc" +
           "W/xe\nMXWJyECArvpzD4RDe9Ge00FUAhwAvMf3D5TS6F8jL2w7HQpktpTHUX" +
           "VSN9NYZUMOb1XRUVMfd3s4\nXGp5ezY4J+wAu84GNv9lo3MN9qwX8GLe9lnB" +
           "Kfbes2WrPn6r+l1+LA4bhz35bohQEduzXLDsMgmB\n/k9fGXjp5c9O7OdIEV" +
           "AJUEiCmRFVkbIw5SF3CgS1CsTCHNm6W0vrspJU8IhKGOL+HV6y+sd/+Xat\n" +
           "cI0KPY5n2+6vwO1/4DH09K1v/KORqwlILKm4ZrhiwprZruYu08RH2D6yz9xe" +
           "+L1f4leB84BnLGWC\ncOpA3DLEzzHKz305f0Z8Y6vZowV0t00B/SIpPCEdnU" +
           "y1ZJ781U/5rquxtxbwuqEfG53C8+yxmJ3u\nPH/0bsHWKMg9fGP7gVr1xr9A" +
           "4zBolCB1WjtMYJBsnhNt6dLyT26+Xf/EhyEU7ENVQBNyH+b4R5UA\nPGKNAv" +
           "lkjUce5eCqHa9gT24y4ofQYB8Af1lQiMp6G5X1RVHJHg/5jjQggAQGzvfWgK" +
           "aShlwyxqPm\n7vGWn723+7UTgmmWTVPoeWclpG/+9neHQy/cHBHz/PnUJ3y6" +
           "8dwfrt4dnCNQKYqOxQV53ztHFB7c\nsrDBPNQ83Qpc+p0VzZeODd6xd1SXnz" +
           "57ocT845G3ydJNz/++COdDtAKp8+U2TQPLzeyxkQ+tYY8O\n4av2L+tSLtII" +
           "rmywXdpQnGjYs5U/l+Y4odwwlTHI8FnfPoNcIMjf51G06j5ZLMZqQTgjHiCc" +
           "EYHV\nPCDhkcz6L77YM3tw4/5neTKphNoVW9udo+M3BtYKwIkvmRo7OWUJae" +
           "nBa3+7eZ0s5RFVoVhQcHeZ\nqSJ1nGfO53iS9H+cPMMTQskItvjqNf4CuLC+" +
           "zStb+ZnONPhPl2EYULEIczauXrUBrK8D69m1J6LI\nkbguYTXW8/rN6tunM+" +
           "u2CkjN8AjEeo5e2VpT8fqp40G2kH0MlZ76zn4vH8Pmdhdq7GcfRXv/r1VP\n" +
           "R/vaDW3t61eu20ghHcMOuY07pwFygj22uUCO/7dAznregta0hNHHfONm9cRI" +
           "24X4+zte5dE3ZVFS\nhEt8eiau7z5z79f0DtfjVgdsdnO2sOoDfLlzN3w0Nq" +
           "vsymvpICofRrWSfeXcg9UMy8HDgCDLuYfC\ntTRvPP+2I0r7zlz1s8BPZp5l" +
           "/XWJyzzQZtIczL5SZC47bfBaMGwzRNjPEAHEG0ohSQQZnyka5red\nZVkoI3" +
           "hgGF+ZGlwy4mA5dD+wyPlmtMCWam0zaqcwwypiBmunHRs4rvnwLjuEC7IcX9" +
           "pwNE7cT2OZ\nSrQUHeUCXzeETY9RFILDYs2s3VWEW0XBw4AAt01dI6x2csbE" +
           "PUDRI7mbPgxmC/ZqooV5t4B+TlQu\nUp+7+MNr9MMVHYJ5lk8dZf6JyzvOTj" +
           "R1vHHqK9T+Tb7Q8aueNfbgztCo8h5nPBv4BZ8O8id15sO9\nCvaTMXOYEqBf" +
           "JPyZLVbAeDnr+WnGXmCPU4B7iflDuA/OuKl4zdubNiivUid+Mu/NTefP3GGn" +
           "bGS9\nCWG9LyEIEuq53TcymdQmZX4EVTz7dLEZtn2VvMeTHkO6YbGLvueLmq" +
           "2pdYdhifpmmEJlyZSsWb9h\nzeo1FB34H5KD4ZRF0Z0ZLPdDzSkyxNq169vW" +
           "rlr58FqKZuzaEhuKZMaEcWwHL9nhdcxE9YU2s50i\n+6+Ow3Gmi39WV3kHoZ" +
           "YqGezt6vFRBp2GMrKFgRpyAxXi1OKf1byZB7GKcOFUH354NXhi3+c1x/E7\n" +
           "B4M2QrpBkf09zquHopn593knjhunJ0lWUhd8HhSftKT4J08d+CL+m3+KcsX5" +
           "7FQNtUEyo6pe9ve0\nywyTJBW+1WqRC0RYXKao1r8VOGP2w7d6SYhdARB5xK" +
           "BMtFteoavAbiDEmm/mskGt60yR1fIPmoXR\n4jz64V9gHYrIiG+wCWnf5f2L" +
           "sqd2vch5B8IQT0wwNVVQAYl7eI5mmqfU5uj6AF15Y89bP9ro+I7f\n0+Z4au" +
           "K8snqNGJ0aYGzgO8Z/AN8fwvkNFwAA");
    }
    final protected static class Parameter {
        private ParameterType type;
        private InterpolationType interp;
        private Object obj;
        private boolean checked;
        private Parameter(String value) {
            super();
            type =
              ParameterType.
                STRING;
            interp =
              InterpolationType.
                NONE;
            obj =
              (new String[] { value });
            checked =
              false;
        }
        private Parameter(int value) { super();
                                       type =
                                         ParameterType.
                                           INT;
                                       interp =
                                         InterpolationType.
                                           NONE;
                                       obj =
                                         (new int[] { value });
                                       checked =
                                         false;
        }
        private Parameter(boolean value) {
            super();
            type =
              ParameterType.
                BOOL;
            interp =
              InterpolationType.
                NONE;
            obj =
              value;
            checked =
              false;
        }
        private Parameter(float value) { super();
                                         type =
                                           ParameterType.
                                             FLOAT;
                                         interp =
                                           InterpolationType.
                                             NONE;
                                         obj =
                                           (new float[] { value });
                                         checked =
                                           false;
        }
        private Parameter(int[] array) { super();
                                         type =
                                           ParameterType.
                                             INT;
                                         interp =
                                           InterpolationType.
                                             NONE;
                                         obj =
                                           array;
                                         checked =
                                           false;
        }
        private Parameter(String[] array) {
            super();
            type =
              ParameterType.
                STRING;
            interp =
              InterpolationType.
                NONE;
            obj =
              array;
            checked =
              false;
        }
        private Parameter(Color c) { super();
                                     type =
                                       ParameterType.
                                         COLOR;
                                     interp =
                                       InterpolationType.
                                         NONE;
                                     obj =
                                       c;
                                     checked =
                                       false;
        }
        private Parameter(ParameterType type,
                          InterpolationType interp,
                          float[] data) {
            super();
            this.
              type =
              type;
            this.
              interp =
              interp;
            obj =
              data;
            checked =
              false;
        }
        private int size() { switch (type) {
                                 case STRING:
                                     return ((String[])
                                               obj).
                                              length;
                                 case INT:
                                     return ((int[])
                                               obj).
                                              length;
                                 case BOOL:
                                     return 1;
                                 case FLOAT:
                                     return ((float[])
                                               obj).
                                              length;
                                 case POINT:
                                     return ((float[])
                                               obj).
                                              length /
                                       3;
                                 case VECTOR:
                                     return ((float[])
                                               obj).
                                              length /
                                       3;
                                 case TEXCOORD:
                                     return ((float[])
                                               obj).
                                              length /
                                       2;
                                 case MATRIX:
                                     return ((float[])
                                               obj).
                                              length /
                                       16;
                                 case COLOR:
                                     return 1;
                                 default:
                                     return -1;
                             } }
        protected void check() { checked =
                                   true; }
        public String toString() { return String.
                                     format(
                                       "%s%s[%d]",
                                       interp ==
                                         InterpolationType.
                                           NONE
                                         ? ""
                                         : interp.
                                         name().
                                         toLowerCase() +
                                       " ",
                                       type.
                                         name().
                                         toLowerCase(),
                                       this.
                                         size());
        }
        private String getStringValue() {
            return ((String[])
                      obj)[0];
        }
        private boolean getBoolValue() { return (Boolean)
                                                  obj;
        }
        private int getIntValue() { return ((int[])
                                              obj)[0];
        }
        private int[] getInts() { return (int[])
                                           obj;
        }
        private String[] getStrings() { return (String[])
                                                 obj;
        }
        private float getFloatValue() { return ((float[])
                                                  obj)[0];
        }
        private FloatParameter getFloats() {
            return new FloatParameter(
              interp,
              (float[])
                obj);
        }
        private Point3 getPoint() { float[] floats =
                                      (float[])
                                        obj;
                                    return new Point3(
                                      floats[0],
                                      floats[1],
                                      floats[2]);
        }
        private Vector3 getVector() { float[] floats =
                                        (float[])
                                          obj;
                                      return new Vector3(
                                        floats[0],
                                        floats[1],
                                        floats[2]);
        }
        private Point2 getTexCoord() { float[] floats =
                                         (float[])
                                           obj;
                                       return new Point2(
                                         floats[0],
                                         floats[1]);
        }
        private Matrix4 getMatrix() { float[] floats =
                                        (float[])
                                          obj;
                                      return new Matrix4(
                                        floats,
                                        true);
        }
        private Color getColor() { return (Color)
                                            obj;
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698290000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVaCWwc1Rl+u+vbDnac+/QR53Z248Q5nQKJk8CGdWLsxCEO" +
           "wYxn39qTzM4MM2+d\njbFoEJCEo21SQgsVBIIQIZCUSFClB01JgZZDlQAVUJ" +
           "EIraIeUgsqQqWpStX+79id3dndibObWvKb\n4733v//7r/f/b+fUZ6jYMtF0" +
           "2fKTfQa2/O3dnZJp4XC7KlnWVnjVJ79RXN554iZN9yJPCHmVMEHV\nIdkKhC" +
           "UiBZRwILi+LW6ihYau7htQdeLHceLfrS4T9DaFlmUQ3H7sbO3dzxTVeVFxCF" +
           "VLmqYTiSi6\ntkHFUYugmtBuaUgKxIiiBkKKRdpCaAzWYtF2XbOIpBHrDnQX" +
           "8oVQiSFTmgQ1hBKLB2DxgCGZUjTA\nlg90smWBwjgTE0nRcHhtcjmY2Zw+E9" +
           "gW87oyRwORMtrZA3AYB4C6Pomao82Aavie61k+8tRJH6ru\nRdWK1k2JyYCE" +
           "wHq9qCqKo/3YtNaGwzjci8ZqGIe7salIqjLMVu1FtZYyoEkkZmKrC1u6OkQH" +
           "1lox\nA5tszcTLEKqSKSYzJhPdTMooomA1nHgqjqjSAMCeaMPmcDfS9wCwQg" +
           "HGzIgk48SUoj2KBhqvc85I\nYmy6CQbA1NIoJoN6cqkiTYIXqJbrUpW0gUA3" +
           "MRVtAIYW6zFYhaCpOYlSWRuSvEcawH0ETXaO6+Rd\nMKqcCYJOIWiCcxijBF" +
           "qa6tBSin4WTvzq0HOPn7ue2XZRGMsq5b8CJs10TOrCEWxiTcZ84qWY/2hw\n" +
           "R2y6FyEYPMExmI9ZO/vsttBfflHHx0zLMmZL/24skz5585G6rjtv0JGPslFm" +
           "6JZClZ+GnLlDp+hp\nixvgtROTFGmnP9H5atevdux/Hv/ViyqCqETW1VgU7G" +
           "isrEcNRcXmDVjDpkRwOIjKsRZuZ/1BVAr3\nITB5/nZLJGJhEkRFKntVorNn" +
           "EFEESFARlcO9okX0xL0hkUF2HzcQQtPgH9Uj5K1H7I9fCWrxB6yY\nFlH1vQ" +
           "HLlAO6OZB8lnUTg2JNajbYpH7vp6ZjENQRGNSjOCDJkqZoemBAAWeV9UVhPE" +
           "SvV0owTrms\n3evx0LDndF8VLP9GXQ1js08+cfHtkQ033X+ImwY1Z4GPoHmw" +
           "jl+s46fr+NPWaUo+IY+HLTSersy1\nBDLeA94Kw6rmd+/adPuhRh+Yh7G3CA" +
           "RUBkMbAZNgZ4Ost9suHWTRTwa7mvz0zoP+Syeu43YVyB15\ns86ufPf0O099" +
           "WbXAi7zZwyKFCYG5gpJhSJLhrsnpSNnof/5Ax0sfvvPJPNulCGrK8PTMmdRT" +
           "G50K\nMXUZhyH22eSfmVLt2456jnhREbg/hDzGP0STmc410jy2LRH9KJbSEK" +
           "qM6GZUUmlXImRVkEFT32u/\nYZZSw+7HgXJqqAlPAVueI2yaXWnvBIO2E7ll" +
           "UW07ULDoeumeksUfvVL5BhNLIhBXp2x13Zhwtx5r\nG8tWE2N4/8mjnQ8/8t" +
           "nBncxSuKl4CCo1TGUIHDkOc+bYc8ChVQgqVJNN27SoHlYiitSvYmpyX1fP\n" +
           "bvnR375dw3WjwpuEapsvT8B+P2Ud2v/Obf+cych4ZLqh2DjsYRzOOJvyWtOU" +
           "9lE+4ne/P+OxX0tP\nQLyDGGMpw5iFDQ+HxkQ+CRIBNpPuHX6+dzAJB1j3At" +
           "b6qQrYJMT6ltKmERZtzuEUWfb1Pnnk+YHG\n2B1v/YTBqZRSE4RUBXVIRhu3" +
           "CdrMomKf5PTrGyVrEMa1vrr51hr11X8DxV6gKMN+am0xIazE09Qr\nRheXfn" +
           "z+tYm3v+dD3o2oQtWl8EaJeQYqB5PE1iBEpLhx3fXM7Gr2liWMMY6YEKYKAb" +
           "CH6Zn2ukjY\n66Ks9kqbOQ6RJvRgosmpiaGpRGGDGWL+dPFA48/f3PbkQR6D" +
           "5rtkf6mz+uRvfvr7Pb7vnO/n85yb\nrGPwkZnP/Omli13jubnyTGRWRjKQOo" +
           "dnIwxZtUE11OC2Ahv9+sKGU3d1XRAc1abvqRsg7/zzvtfw\n3DXf+kOWjcAH" +
           "+RJb7FoXowzSpo11tdJmDdfUirwV2ioU2nrFCqXP1xmc8HqIIP26rmJJYxM7" +
           "XSBs\no02HDWFzoRDaBIS2QiFAJNUlroNbXQBItNlhA+gtFMA6AWBdHk41Ps" +
           "WpWEiktnjy8PpxXat23sO2\n5XIoACRrc8LUWNlF7zxgobNz+1qSWJ88d9fZ" +
           "v58/h+eyCFSmWFC1rDUHsiTDKXO+kJ7HHR9FjrGt\ntahfstjqVc4qIrNISM" +
           "v9mQSuMdil3TAMSPs4nFUti1cB+lpAT2tHvxL2h3RZUoPrj5+vfP9IbPkm\n" +
           "7oJjUgYE14+c2VRVdvzBA166kBBDeUqSLJ5LhyRzs+2a9BIlaPtVTR1XL1ux" +
           "snnJ8kVLV4DdSVRu\nDOSgi92x/RXbdhcp1O6Cwu6CeTmOkiQ4lc6vdyOYcx" +
           "fmOq1gOl2yZGXrSjaSKWL/1Rf5ymVU5K2L\n00R+p4vI76NN3Bb5vkJF3ikk" +
           "1JmXyEGCk1LLBSUKlSvNsXSTEXjIBcph2txvQ3mgUCjbBZTto4bi\nYxR9CS" +
           "jNo618kmk0TFp8mUlBGk8gLLGkKzlR4SFkV3oIaVlsW9sTV9/aVq1qXrFk0c" +
           "pWApULhCDG\nwfddNPQ0bY7aGnpktBqKpzwVWa4Z1EYafO0CqK+/+bnQ21ue" +
           "YOlIzvotS3LloDN8btuxS78hFxgd\nu5CisxvimbUxbCD23JUfDo0tOfNk1I" +
           "tKe1GNLA7meiQ1RsuVXtgirMRpXQjieWp/+pkQPwBpSxaK\n053ZXcqyzhLO" +
           "TsXgno5mu1W2qm0WmHyDMP0Gp+l7ELs5xayfcEp5mTvzIdsUTl/OFE6kMzkf" +
           "mGsU\nTDbmYPIlwWQJ24KNvB3MwerLV8jqNGBxlmB1Vg5WfypY9en9u7NsJV" +
           "zxDj5+doV80P2rSfDRlIOP\n84KPUnkQy1Ch0cebHev+0mVd3tXE2rm89vbR" +
           "tFPRJLDXcsPUCQDBUJmUWOxwN57m5+DaM3IdP7Ly\n4+AtX1QdkF7f5RURZQ" +
           "MQEqfCNp1qSibtYKiDZVy2Rz5w8oWz5L2Fq3kKtSB3NHFOXLD6qeG61S8+\n" +
           "mMdxUJ0DmJP02KFpN/sGlTdZ6iYcPOMgOX1SW7pbVwA/MTNpsty569ONADIZ" +
           "723CCG4b9b6WGsQ/\ncun7HW1+S/ghBb1vt83mgyvajt9Nsl1LX84EduOC7X" +
           "hWtjMNz0Pv34q7sHvRpe+PtLkAtss8wVlI\nFQ3pStjG9mme2KoTfjkisI2M" +
           "FhvYvRHrVxMOlB3Dly59/6DNZwSVEZ0nqjkzWBvm53nCZEPmArz9\nAub+vC" +
           "zvv7n7PEzZXxN0zQAmnG22f44C1H8KATUHwNwrQN2bDyhPpUvfGNqUEVQFoN" +
           "bpusogsZCc\nZN9TXgj7sL17Dwn2D+XF/gSXvkm0qSWoEtgPioQmPSh4xhXC" +
           "fR1w/ZDg/qG8uK9z6WugzXTYCjn3\nVmqa7ajUW+yiztN01dPs5cuWNLdAHd" +
           "1C0BTByzy/3z9/rlVvB3xbpDMKEelsEOVhIdLDeYnU79K3\nmDYLoRpOOqmQ" +
           "ajoTE92YyOnRjkJ72VJbJ8v/DzpZznSyApJwG4ybWpoLVctRIZGjeanlepe+" +
           "dbT5\nBkFjAMlGekCY9NRdNv/XFsL/AuD7UcH/o3nxv8mlL0SbG8AdE/xbCS" +
           "NZdJlUn41OvrLB3lhoUH1c\ngH08L7A9Ln230KYb9m0A26kr4gMP57lJVCKD" +
           "fta91Ea1tRBUtCo8LlAdzwtVv0sfLTQ8fVyFPTj5\nTQbAmpwBi/en4Lq9EF" +
           "xQCXmfFbiezQuX5tJH6Xj28C1wK46367oZvozCltjA1EIV9oIA9kJewIZd\n" +
           "+kZos5crrEOC8BfPrTDe32rjiheCqx7wnBG4zuSF64BL3yHa3MPdix07ZtVW" +
           "yrGkjereUZ9mgdjS\ngw7Qn+kequivixmfT/FPfuTQx3fe+lXog3/xXyISn+" +
           "VUhlBZJKaqqec+KfclhokjChNYJT8FYqmN\n52HYWp2sQL1DL5RVz3f5sO+B" +
           "TacMgzRJ3KUOeoweaJgD9PYHyeOXjGON9CMA+iP4rLSCnH2hliia\nY/wbtT" +
           "75ltM76+MPbj3MKvFiWZWGhymZihAq5R8rMKq08G7ISS1B61105sWeV364Kn" +
           "GqwH6yHp9S\ne6XZYyvvdVE2FPvZPxDYEDUI+0l/+MeTXl5z4tgFL/tG4X/D" +
           "f5dOWCgAAA==");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698290000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAANVcDXQU13V+uyuhXxASP8LY5t9gQNqVZP0CqY0FCoIVyELI" +
       "jowjj2ZH0pjRznhm\nViw/MXZIDbETbLd1GnIKxj5OsTF2OHES3NRxwNj5wT" +
       "THuI1pcUPsQ5O6TeKWtCfgpsfpve+9nZmd\n3R0J7Tqn1jnzdnbmvfved+93" +
       "77vvzayO/obkGzq5TjSC5jZNMoKtGzsF3ZAirYpgGN1wqU98Pb+o\n8/C6qO" +
       "onvjDxyxGTlIVFIxQRTCEkR0Ltq5bHdbJUU5Vtg4pqBqW4GbxHaeDy1oYbUg" +
       "TefvB4xQNP\n5832k/wwKROiUdUUTFmNrlakYcMkk8P3CCNCKGbKSigsG+by" +
       "MJkoRWPDrWrUMIWoadxL7iOBMJmg\niSjTJHPDic5D0HlIE3RhOES7D3XSbk" +
       "HCFF0yBTkqRVZa3UHLquSWMGzeriu1NggpxJs9AIeOAFDP\nsVAztClQtcAz" +
       "PY07Dz0bIGW9pEyObkRhIiAxob9eUjosDfdLurEyEpEivaQ8KkmRjZIuC4q8" +
       "nfba\nSyoMeTAqmDFdMrokQ1VGsGKFEdMknfaZuBgmpSJi0mOiqeqWjgZkSY" +
       "kkvuUPKMIgwJ5uw2Zw2/A6\nACyWYWD6gCBKiSZ5W+QoWHy2u4WFccE6qABN" +
       "C4Ylc0i1usqLCnCBVDBbKkJ0MLTR1OXoIFTNV2PQ\ni0lmZhSKutYEcYswKP" +
       "WZZIa7Xie7BbWKqCKwiUmmuatRSWClmS4rOeyzdPrv9j7zV6/cQrmdF5FE\n" +
       "BcdfDI1muRp1SQOSLkVFiTW8Egv+RftnYtf5CYHK01yVWZ2VNxzfFH7/+7NZ" +
       "nWvT1NnQf48kmn3i\n+sdmd+34tEoCOIxCTTVkNH4ScuoOnfzO8rgGXjvdko" +
       "g3g4mbJ7p+8Jn7j0i/8pPidjJBVJXYMPCo\nXFSHNVmR9E9LUUkXTCnSToqk" +
       "aKSV3m8nBXAeBsqzqxsGBgzJbCd5Cr00QaXfQUUDIAJVVATncnRA\nTZxrgj" +
       "lEz+MaIaQADlIKxxTC/uinSWqDISMWHVDUrSFDF0OqPmh9F1VdAsPqSBtJR7" +
       "8PInU0k3SE\nhtRhKSSIQlSOqqFBGZxVVKsj0gh+Xq3AOI6yYqvPh2HP7b4K" +
       "MH+NqkQkvU88fPH0ztXrvriXUQPp\nzPGZZBb0E+T9BLGfYFI/xOej4qdif8" +
       "w2oNkt4KNws3TxxrvW3r13XgBIoW3NA7Vg1XmAhA9itai2\n2o7cTmOeCGya" +
       "8dSde4JXDt/M2BTKHG/Tti558/k3Dv1X6RI/8acPhggOwnExiqForCC3wO0+" +
       "6eR/\n8FDHi2+/8bMbbUcyyYIU/05tif45z20GXRWlCEQ8W/zT15QFbic9j/" +
       "lJHjg9BDo6foghs9x9JPnp\n8kTMQywFYVIyoOrDgoK3EoGq2BzS1a32FcqP" +
       "yfQcSVuCxJ0BxzzOZPqJd6dpWE5nfEJru1DQmHpl\n94Sacy+XvE7Vkgi/ZY" +
       "4JbqNkMmcut8nSrUsSXP/ZVzv//PHf7LmTMoVTxYRZL9avyGIcmiy0m4AX\n" +
       "KxBJ0JALNkWH1Yg8IAv9ioSM+9+yG2q//et9k5lpFLiSsGzV6ALs69fcSu5/" +
       "47OXZ1ExPhFnERuG\nXY2hmWJLXqnrwjYcR/yBt67f/0PhAAQ5CCyGvF2isY" +
       "JQZITqMUT1voSWQde9WizmgeyqDNRPM2f3\niTuPDM6L3fvjv6GjLhGck7/T" +
       "DB2CtpxZHov5qN1Kt/euEYwhqFd/Yv3mycqJ34PEXpAowlxpbNAh\nZMSTjM" +
       "hr5xecP3lq+t1nA8TfRooVVYi0CZT/pAiIJxlDEG3i2s23UG5N3lqIJYVMqB" +
       "JmcgXEHd/y\nYHCLM7t/G874tuf09Vc9Ez694QBVQEbHTzPhueRsf2XTwSt/" +
       "Z16gcmwPxNZz46mhFLIku23z2yPl\nE449MewnBb1kssjzuB5BiSHPeyHtMB" +
       "LJHeR6SfeTUwg2Xy63Isx1bu93dOv2fTuEwznWxvNSl7tP\nRW3XJE4Sn053" +
       "9xF6cjNtsoCWi5hz+k3oUI4KMK4iTVdNGKkUiZvM5cBm1zqS7I2xfsN05CEN" +
       "tzRV\nfXPN5lM0PBdB+icY6+3hQtKNZz7Q85LMhnfL1E91vy5dnPYVNmEkM4" +
       "bmyLypu91XFv9qZGnN5w/Q\nseT1CwYdRjGo08CaJpmTOd+mspgnTWRK/QP8" +
       "ETg+wgOVSS/QbGBKkv8FaTavaYzqlSaZaDsT3MaL\nHaDFCrcW+8TKzdqmHv" +
       "KlzQzo3FE01CfOPPPdt28/36NRKpeNyJA2SZFuntonxzV73l6elO6n1WGf\n" +
       "+C/z7rrwtbyf76d5HFMXjvsmiIn42cj9vNTycz8GGgciPgoaev/9J/unfkHf" +
       "q1I75FNOpPLhhgxo\nnYL6xH3v3/rR/eEti/zoCMXoO4IOCSBkmMFMKyCngA" +
       "XdcLYKWoFPTWKtIZOnlOHUqLCuWpO3Saoz\nycZFoXuOd7srxMqtkn6rGotS" +
       "rHOTHbg4pmnOu5Rxk8bLOA3/IG1bl6DeZEo9DDlBFnJMUkgNUF9f\np6WpxJ" +
       "Y29MYmLHotkT1jEFlvibzRO7FcYH1LmhAIBJliKquurqnlJu4kuD4PyhEex1" +
       "e91dZ/ZCB6\nJOKnSQ9dFq50UKqIXnHEnICqGbgAcaz0uaQFGzQDvWGio5P2" +
       "VTuPrS0tfPLhB6l8TtAix2KGfy8Y\nEfT1zly6hA68tqWhvr7GJLfnNNdfVt" +
       "9UV9VYW93YACG5e037xmAi+7mHh+X7YBWVqitEyOM/qaBB\nZZJtvdXR2LDz" +
       "JmDI61q9chXLA7Gsw+IWZqGGjFP7MmvSwYUSqYVjGhc7LcOksy110oGMsEDT" +
       "5REB\nNxRIIYytB9JiAzgww7mno8vDYIURmhRffHDe93606Yk96eaF5I0bZ6" +
       "s+cdfP390SeORkP2vnThdc\nlR+b9fQvX7zYNZUlnWwTYX7KOt7Zhm0kUEcu" +
       "0zABm+vVA6392tK5R+/rusBHVJG8HEY7/eu2U9Ki\nFV9+L80aLiBHTZfJtn" +
       "8MJnsIix3MMm2JfZXdro4f/hg6foR3XMo7prRI1/mjHp1TayxMCjVgmOsz\n" +
       "7adQo+y541Lpg8Jrd/l53v6n4HumqlUr0oikOETNR0lJy94OuoNkp40PPfvc" +
       "cfPs0mXMvB6Zj7vh\nkmWHts9e9o2Hx7HYne3C5hZdPnLtbYEh+Uc0zPEsNG" +
       "VzLLnRctfUBeOJ6dHupAx0TvKCczEcS7mB\nl7oNTO1nG8ZeLfn4dgJ+38Xz" +
       "qC9AfOhXVUUSorThIY8V1texOABZrAjVdbeYvBFVjti0OTgaZy3C\n4Jf9yf" +
       "iWwLGC41txtfgoiWmtYx5gvonF88B/QzKR/62QK1CHP2xjeCEbDFVwtHEMbe" +
       "PH8F0PDC9j\ncdyExEYy0X+leDoUL2WDoh6O2ziK28aP4jUPFD/A4iQs5Lgl" +
       "MiN5NVskmzmSzWNG4mdLN++cLt0N\nKvInHrD/AYs3IPgJkQhr4kJ7Jhu0dX" +
       "AMcrSDOUNrG/SfPZC9i8U/Yc4ZwTWENMi2ARzQzmcDrREO\nnUPTcwrtSdr8" +
       "fQ9ov8biFwzarSxsuqD9MlurfY5D+1xuOeqI1LCOUgWTyvtvD6y/x+ISLkMi" +
       "kTZs\n4UL622yR7uVI9+bcGyud6yR5WBiUcANT1VGyL5AZs68IC8Iw0xbJmH" +
       "2+bDC3wPE4x/x47jDrZKoj\nlafbqZg8PPvoqildLXfuHnXLKNMWASROlrA+" +
       "cdFdx//z5CvSIrqtWSgbsOJfqQ+meXrmaHNJOCJ1\nnBs4SHflrZ2iUvdjx9" +
       "SnikkPC9naXaMfu9MmM8s0ntX6rkldAfnxvBWLxbACmqBI0UH2KAoTXV8l\n" +
       "d4s0mp5ia7pVUaMS7nQk7rFHN7IatJ7Gws142sHtpyP3VaazrZN6N3jcW4TF" +
       "fJp5wUDYuMHus9Pv\nz68e1ky6o779pcpvrTh88AImyFqcmJwGLbU1Na5NgL" +
       "AqCkr7qidPlrz1WKxxbSI3H8auluZ82V1b\n31JV31Dd0AiIBKQLBVnuoQDc" +
       "3PdNMkmZPaVQnrncsywb91wOx1PcPZ/KaUjyTbV6qkoEv4w9eQe3\nFOqj+E" +
       "8l6L/Sm/5Y5TqL+itSqY+XZ2ExJwOXWW+09Wh8XuNxby0WbVjcyEaC5RJkaG" +
       "Kfqrmmnmqu\nCot1uWdgY11VfX11Q3MSA1s8htyJBRB2kpWupSNgUzYEXA/H" +
       "MU6LY2MmYIBKDIw6J9aMsneIbqVD\nLKcr4MTzTt9UFncva5rmjB+1tnE+m3" +
       "vjNDVWNTZUNzXBohIDFNX/HR62EbHYxFJpmqkYLrP0ZGuW\nE9wsJ/6fmWWU" +
       "6VC/mnigjSMesLlNGy0WbPO4twOLrVYs0OxYYLGtzmbbztyzraUulW1bPAb8" +
       "ABZD\njG2dqhxNYZucDds2wHGWs+3sJ4ttj14N2/aNm237RmPbX3rcozPY4x" +
       "bb9qVh2002276Wc7bV1bRU\nNTZWNzU72fZFjwE/gcWDbMXZI1nv7jnoticb" +
       "uuGeznucbu99suj2wtXQ7ei46XZ0NLp92+PecSxe\ntOh2NA3dHHnOS7mnW1" +
       "1DVWNzdXONk26HPQb8PSyeNkkp0K0bd+FUPeIm3NezIVwnHJc44S59sgh3\n" +
       "5moId3rchDs9GuH+3uPeT7E4axHudBrCNdiEezv3hKuvrWpsqm5qcRLudY8B" +
       "v4PFq/icNxLpEMCY\nfBfAwbdT4+QbfRaGa60POd8+zMi3Ha4RjnWjaewP5B" +
       "HIRQ89/BsW75okAHpw4X8vG3/7FLCrkMFn\nn2Pyt6y2vX2XPHBexuIDoOOg" +
       "ZI4iy9bAf2SjgTZAXsk1UJlTDTjW9fhOGO5MZ+4poyS2tLIWvrWN\nln/6C3" +
       "Pvn00tVfXN1Y3gn6URaUCIKew9OlSE35fZbv6JOKiPYP1r2Y2uf11qoAovz4" +
       "kamm01TPlY\n1FBbW90E8+KcZEA3BoPBxYuMOfZTWJuGf8jy+ZNvIdfKwpzR" +
       "kM431EDXexhvLhYzTTIBsLazx2q7\nLVj+a7OBtRjg1HNY9WOG5Zz2MoUR/2" +
       "IPRNVYLIRpgyFycJFvFLs2LBxOBTbfnAWbtMRrJqHbYkKk\nQzKGGKWam6vq" +
       "mqsbmpaAj2hK0DUupulF2Wga2vhauaZbc0og+tzLv8xD3X+CBayQiwGW47nX" +
       "kza0\n5mygLQNIXRxaV06hXabDb/OAhnuR/laTFAI06zHXZRvYqmyBCRyYkD" +
       "NgiRtej7n8XR6Y78BiA8NM\nW4wm0VZHZzbqWAFq0Lg6tI9XHcOCORSk2zM3" +
       "Ucj9HurAx0H+PqYOe0fHQ6KtjruzUcfNoIZdXB27\ncq6OGSmDZ/sHTB+6hz" +
       "5oobIczbHn4CXT1oiWrUb2cY3s+2MRpI7i/byHQvZisYvNNkmrYg+htkbu\n" +
       "z1YjB7hGDvwROELXYPF6CvvPPFTyVSweYRxhbUaVaWvE653CMSVSz3GNPDdm" +
       "jYwp43jKA/BfY/GE\nSSYm5gl7bgd51aMsBGkL65KtiUPZauI7XBPfya0mPF" +
       "6d838Li+eZJijXc6KJrF62awBAJ7kmTuZW\nE694aOJVLP6WLYpYIMyJKl7O" +
       "RhVNgOgMV8WZ3KrijIcq3sTix9DSESJzoozT2fLiHFfGudwq47yH\nMi5gcY" +
       "7xggW/nKjiH7PZCIMEw/cOV8U7GVXh3gjLpxLzR51Iqsa6EWatqMezW4vtdi" +
       "daX9Xem9/j\nrT7/b7H4BaDD15oUOWK1xMqONc543+2j+u8FvX/A9f/BmPU/" +
       "1o3vcen/6jT4P5k1GMC9bf9lk5Qn\nJsdk9mbB+Stj1XkcJqQkkfiG1IyUf8" +
       "DB/mmEGD6/Y/Pvwj/9kL2alvjHDiVhUjgQUxTnT0Ed5xM0\nXRqQqbFKaDmZ" +
       "bjYESsAwbngmycMPHGKgmFWbBAmko5pJCviZs1K5SQJQCU8r0v2UjP04LPnn" +
       "XYh0\nfsbfHnbE2H856RPveP7OOfGHux9lvxgUFWH7dhRTHCYF7IfvVGog5U" +
       "eSTmkJWW+SY9/oefmFlsSr\nYvSH0VMZkVN9oY7dzWzKe/ExhfZ/QFtetm9G" +
       "AAA=");
}
