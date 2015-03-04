package org.sunflow.util;
final public class IntArray {
    private int[] array;
    private int size;
    public IntArray() { super();
                        array = (new int[10]);
                        size = 0; }
    public IntArray(int capacity) { super();
                                    array = (new int[capacity]);
                                    size = 0; }
    final public void add(int i) { if (size == array.length) { int[] oldArray =
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
    final public void set(int index, int value) {
        array[index] =
          value;
    }
    final public int get(int index) { return array[index];
    }
    final public int getSize() { return size; }
    final public int[] trim() { if (size < array.
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170480004000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZe2wUxxkf353fJn4A5hHAYExcXnfYBQI2VXLYBg4OMDaP" +
       "xECd8e7ceWFvd7M7\ndxwOokmQgAY1CUqjtlIhJEIC3DyQ0pS2ohSaR2lQpQ" +
       "Q1SZU2tBVSW6lN1ahSStX+0W9mdm/39s4m\nDamlnZudme+b+X3vWb/4MSq1" +
       "TDRDssJ0v0GscFd/LzYtInep2LK2wtCg9GZpZe+ZDZoeQCVxFFBk\nimrjkh" +
       "WRMcURRY7EujuzJlpo6Or+pKrTMMnS8B51mc1vfXxZAcMdJy80PHY61BRApX" +
       "FUizVNp5gq\nutajkpRFUV18D87gSJoqaiSuWLQzjiYQLZ3q0jWLYo1aD6OD" +
       "KBhHZYbEeFI0J+5sHoHNIwY2cSrC\nt4/08m2Bw0STUKxoRI7mtgPKRfmUcG" +
       "ybrq9wNTCpYJPbAQ4/AaCenUMt0BZANYJnty8/cOpcENUO\noFpF62fMJEBC" +
       "Yb8BVJMiqSFiWlFZJvIAqtcIkfuJqWBVGeG7DqAGS0lqmKZNYvURS1czbGGD" +
       "lTaI\nyfd0BuOoRmKYzLREdTMno4RCVNl5K02oOAmwG13YAu4aNg4AqxQ4mJ" +
       "nAEnFIQnsVDTTe5KfIYWzZ\nAAuAtDxF6LCe2yqkYRhADUKXKtaSkX5qKloS" +
       "lpbqadiFouljMmWyNrC0FyfJIEVT/et6xRSsquSC\nYCQUTfYv45xAS9N9Wv" +
       "LoZ2Hjp0fPfvfS/dy2QzKRVHb+KiCa5SPqIwliEk0igvBWOvzN2IPpGQGE\n" +
       "YPFk32KxJjrvwrb4n3/aJNbcXWTN5qE9RKKD0qbjTX2PrNVRkB2jwtAthSk/" +
       "Dzl3h157pjNrgNc2\n5jiyybAzebnvrQcfHSV/CaCqGCqTdDWdAjuql/SUoa" +
       "jEXEs0YmJK5BiqJJrcxedjqBz6cTB5Mbo5\nkbAIjaGQyofKdP4OIkoACyai" +
       "SugrWkJ3+gamw7yfNRBCE+BBDfAEkfjjv+Bv4YiV1hKqvi9imVJE\nN5O5d4" +
       "4vptGoaeL9YWY1BkXrIsN6ikSwhDVF0yNJBfxU0hfLJMN+/wdeWXa2hn0lJS" +
       "zY+Z1WBXtf\np6syMQelMzffPtCz4etHhUEwI7ZRUTQNtgjbWwiJO1ugkhLO" +
       "eRLbSkyBKPeCU0L4qpnfv3v9Q0eb\nQQRZY18I5BCApc1wfnv/Hknvcj03xo" +
       "OcBOYz9YWdR8K3ztwnzCcydoAtSl39zkvXTv2jZkEABYpH\nP4YL4m8VY9PL" +
       "QmYuqrX4/aUY/789sfHV96999CXXcyhqKXDoQkrmkM1+DZi6RGQIcS7709Nq" +
       "gzvQ\n9uMBFAIvh8jGzw9BY5Z/jzzH7HSCHMNSHkfVCd1MYZVNOZGpig6b+j" +
       "53hJtGHe9PBOVUM0tlnXLb\ndPkvm51ssLZRmBLTtg8FD6K3DpUt+eBi9Ztc" +
       "LE68rfVktH5ChffWu8ay1SQExj/6du8zz358ZCe3\nFGEqJRTSXHpIVaQskN" +
       "zjkoDbqhA6mCJbtmkpXVYSCh5SCbO4/9TOa3vtr0/WCdWoMOJodtHtGbjj\n" +
       "01ajR6999Z+zOJsSiaUNF4a7TKCZ6HLmLsHOkX3s+szv/ByfgKgGkcRSRggP" +
       "DogjQ1yOES73BbwN\n++baWNMMvBeNYfpFkvSgdGA02Zx++Bc/4qeuxt5s71" +
       "XDRmx0Cs2zZi6T7hS/967D1jCsW3p50646\n9fK/geMAcJQgOVqbTYgW2Twl" +
       "2qtLyz+88nrjQ+8GUWANqlJ1LK/B3P5RJRgesYYh0GSN++7ntlW3\nr4K1HD" +
       "LiQphuC4C/zCi0yhrbKmuKWiVr7vGJtEQYEgCc6q3yTCUF2SLDvebm4eafXN" +
       "323BERaeaP\nU8p5qQalr/3u93uDT10ZEnT+jOlbfHzW6T++erNvkrBKUVbM" +
       "LcjsXhpRWnBktQbT0JzxduCr31g4\n58WDfTfsEzXkJ8geKCL/tP910rrqG3" +
       "8oEt+DUPzwzVaNY5RrWbOST7WzpkNoatltFZr1vLEEMI6U\n17CazQ2Fg0OL" +
       "zsbf3nyCH3nMSF5EAT4+I5e2nbz1S3qD83FDKqOeky1Mi1DnurQr3s/Ul51/" +
       "LhVA\n5QOoTrIr8e1YTbPANQCFo+WU51Ct583nF4Gi4unMpYwZfgvwbOsP5q" +
       "66oM9Ws36NL35zx5gKT8j2\nlJDfU0oQ72zhJC28bc1F23LDVDKYVeeoFLNA" +
       "xgK9x294cGPmdu7p7ol9K3ce4vm1Egp2bG1yjwfX\nJNYrAbnOG1vROWaDUu" +
       "vuC3+/com08iBToVgghqiZLFK8emg+waNk4weJkzxHhoawJQTir/oLi/q8\n" +
       "Wp3L7y6D/0QLogZ7jxuOzHYVyiwAgkooGuaF83wQW5lKtKQoBqOsGTCyOa4B" +
       "2/zZ+xRqJw1mF1CT\n6xph+ceZE7WUoodz9yGYzBaJajPzKqmNHJlruE+c+9" +
       "4F+u7CDhEQFoytCz/hgo5TI00drxz7HPVT\nk09lftb1mbu3BIeVqwF+URJ+" +
       "UHDByifqzLf+KjhP2tS25vnAbKHDgWJJwBvCUuPM8Xp+L6hUYvoQ\n6gMZNx" +
       "WvG3pSBuWZfuSHU76/6szJG0zKRhYKfeEQK9vblwN1A/gP+1oQVmQ7JnVfXz" +
       "M0mtBGZS6C\nKm6uUUZh46vkIx5/CuqGxa5Dnu8ONqeWzYbFasoJnk1i3QfO" +
       "r6+peP7YYc7fdsZKz9XKfi/PYHOT\nNwdU83O3td27YulSirZ8UdePjnuXLm" +
       "pbtrh9CUVVW9fF+sM8tLBdLSfOHIT7XKGcGDo7jrH7FOC8\ny/UZls+8k3D+" +
       "UF9PtFsUqLkU1Xe7FBXPD56T4Sm12ZaOETwfZ00/FVVdztU9mx4aZ9NskSjC" +
       "+oMi\nhOTXQeDgY13fecY/8sAnNYfxG7sDtgV3gfFR3ViskgxRPaxKeT+Zg1" +
       "rP2M+Gp9GG2li8zr/9UQtK\nLS4PTv7MOK72LGuehLIDyyISf8UOlKtBrBld" +
       "kV1hPvVZi4xiGCPwtNoYW+8cY8BdEHWBvjAO0NOs\nOQFALcIvIt9ygZ28E2" +
       "Bz4WmzgbX9H5T38jiYzrPmHGBKCkxRF9PonWCaBU+HjanjzjF5j/zjceYu\n" +
       "suY1qH4ATr/j0S6kH9wJpJnwdNuQur9YSG+NM3eVNVfAlShcEviKHXaNYxiG" +
       "N0Ot8GWouC5hNdb9\n/JXq68fTy9c7cSXDmmsUKZ8vJ0i6yYgkokZW62lNVr" +
       "RkjJVlGayuU4iJTWnYThVL21mq+PKSBRRN\noCRlsI8D4RwIro+ffeZLB0UV" +
       "TiJieXxqwfdq8Y1Vin/4yK5P4+/9S5SSznfQasiYibSqeutuT7/M\nMElC4d" +
       "KpFlW4kPB7FNX5v5WBItgPP92vxLJfQ7r1LAPrs3veRb8BH4NFrPtbw6kN69" +
       "wcKO4T+TmD\nIZ2bV+nxfwk41Vha/FNgUHrgpZ2zs8e2Ps1LPKh48MgIY1MF" +
       "dYH4bJSr6OaMyc3h9Q46/8r2iy+v\ndMyFf1aY5LHvPBdpF7Nja5FNmMZ/Aa" +
       "ivsJOeGQAA");
}
