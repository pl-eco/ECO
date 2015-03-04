package org.sunflow.core;
import org.sunflow.image.Color;
public interface Display {
    void imageBegin(int w, int h, int bucketSize);
    void imagePrepare(int x, int y, int w, int h, int id);
    void imageUpdate(int x, int y, int w, int h, Color[] data);
    void imageFill(int x, int y, int w, int h, Color c);
    void imageEnd();
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170608712000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAALUYa2wUx3l8d3479WGDISRg7EBRYrgFK3aFHTUYbJMzC1xs" +
                               "DImBOuPdufPC3u5m\nd+58dhBNRAUkqC1WEyWVWppISASaNpHailZKU1CSNm" +
                               "3/NJGqSJGatkJqK7WRmj9pqvZHv29m725v\n/UgUGks3Ozvzvd/rlz4g1Z5L" +
                               "7tS8BJ91mJfYPZairsf03Sb1vINwNKm9WV2furzXsiOkSiURQ+ek\nWdU8Ra" +
                               "ecKoauJAf7Cy7pcmxzNmPaPMEKPHHc7PHpjag9Cwgevnit5YlLsfYIqVZJM7" +
                               "Usm1Nu2NaQ\nybIeJ3H1OM1TJccNU1ENj/er5DZm5bK7bcvj1OLeo+QUiaqk" +
                               "xtGQJicdapG5AswVh7o0qwj2Skqw\nBQqtLuPUsJg+UGIHmFsqMUFsH290IT" +
                               "QQqcPLQ6COkAC03lDSWmq7QFUn+uKh3pPPX4mS5gnSbFhj\nSEwDTTjwmyBN" +
                               "WZadYq43oOtMnyArLMb0MeYa1DTmBNcJ0uIZGYvynMu8UebZZh4BW7ycw1zB" +
                               "s3io\nkiYNdXJzGrfdko3SBjP14lt12qQZULutrLZUdxjPQcEGAwRz01RjRZ" +
                               "TYCcMCj7eHMUo6btwLAIBa\nm2V82i6xilkUDkiL9KVJrYwyxl3DygBotZ0D" +
                               "LpysXZIo2tqh2gmaYZOcrAnDpeQVQNULQyAKJ6vC\nYIISeGltyEsB/3S1fX" +
                               "Tuxe+8tlPEdkxnmonyNwDS+hDSKEszl1kak4gf5xJPJx/O3RkhBIBXhYAl\n" +
                               "zMCma+Pq337RLmHuWATmwNRxpvFJbf98++hje2wSRTHqHNsz0PkVmot0SPk3" +
                               "/QUHsratRBEvE8XL\n66O/fPjxq+zvEdKQJDWabeayEEcrNDvrGCZz9zCLuZ" +
                               "QzPUnqmaXvFvdJUgt7FUJenh5Ipz3GkyRm\niqMaW7yDidJAAk1UD3vDStvF" +
                               "vUP5tNgXHEJILfxIFfxGiPyrw4WTroTi5ay0ac8onqsptpspvWu2\ny5RBw3" +
                               "NMOpvAoHE42aNM21mmUI1ahmUrGQPSVLO36iyPz09PqoCStcxUVWGpC6esCd" +
                               "H+gG3qzJ3U\nLt/8zcmhvU+ek+GAIezrBK4ADgmfQwI5JHwOpKpKEF6JnKQn" +
                               "wI4nICOhdjXdPXZs5JFznVEIAWcm\nhlYoiBRZW3wBxJBEIhk/Pl2z7d1XG9" +
                               "8UkhTztjlQGccYl1Gwosz3oMsYnP/hudS3nvng7BHBVHKN\nclJHp6A6UI1z" +
                               "Ul9Kcw5VNDdlGtoCqdYtlTQi4c8+9GHTGfrGMRnaLZWBOATF+q+zr7PN9339" +
                               "z4tY\nsp7bzlaT5ZkZ4IldaB041ec2pNn7RD1JipqvQTY9deX71/jbXX2S5T" +
                               "1L95sw4j19z8+19718PkIi\ni/cBlAI6UQNSSGHzKNX39pARwqRX5O94MDpt" +
                               "vBURJQ/LxyKlshKpP2gOYAry5FwLDYsnTcC0Mxyh\nrq0xHRpAme+l25ujh8" +
                               "mh+QiJQQ2Eui90gpK6Psy8omz1F0MJWdWqpDFtu1lq4lWxbjfwadeeKZ+I\n" +
                               "1ImLfSu4SYTHdvi1+oktnni7ysG1TaaagF8n1g0yACO478RlI0bXF8tBCwXI" +
                               "hCKIjtg4bmVt3Ugb\ndMpkmD7/bd60/Sf/+EZcRpEJJ0XPbPlkAuXz23eRx3" +
                               "/7lX+tF2SqNGyA5UQqg8l8ai1THnBdOoty\nFJ54Z923f0W/C/UZaqJnzDFR" +
                               "5qIytwBpTXCAco0sFOK8MPnNM50/f2v8e2dl1N69zJQUxJrUvvrH\nP52Ifv" +
                               "PGlMQLN6MQ8Pz6S3/50c3RldJMsmPftaBpBnFk1xbOanbQIR3LcRDQb3R1vH" +
                               "Rq9H0hEeJt\n5iQKdQS394tFnPYJp/eIdQfGhl9R8H0Ql+0cpowsdO9dLGPI" +
                               "3Ot3ZPXZyUksbxs6iLNlCUMtMpdN\naievZjpzj/76Z0L9Rhoc8IIVcx91pM" +
                               "ZxXLpR69Xhmv0A9aYB7t7r+4/Gzev/AYoTQFGDecg74EKL\nKFTUWx+6uva9" +
                               "G6+3PfJ2lESGSYNpU32Y4hAGrRSyiXnT0F0Kzv07RcLEZ7AXxoVRSGXRxZeu" +
                               "ylRD\nE3b4qdbxWVKt0hvVstaWfRZaBMnxZVx4GJcUJ03ChSmXwbAtCtewAE" +
                               "/i8qDkqn4mhb8Mv25f4e7P\nVWHsvIGsFbmOwX7lwmDr6I4jp0W7qIdJnHr7" +
                               "y/Uavn9wVwWBsGnpZC4Rm9Q2H7v2zxuvsc0ilOoM\nDz4fBtzMIlNpAOdDep" +
                               "Xtezd9UZT32BT1ZG8Ij/MLp/WKIVyY6wsl82LVJr3LmVeAruZkdXDUEY7G\n" +
                               "Gmu7juPADNcgTNLd0927DUzYAibEL8GEoSdUW6NmcvCFG43vzOd6R2Tpui0A" +
                               "kBw8+cpIU90L58+I\nhunbsj4w8vrvtXnq7i/PDPgwOEn9n8bBvp5tW+7dsb" +
                               "UXSlEMRROGostEvRDgKCeNwhjjDiCFg/7Y\nrQT9l+C3y/fKrs836D/BxYLb" +
                               "3DKmOIVLDkdIRBo2TDNkiPytGAI3Sd8QyVs3RFDwc8vcPYXLaRiR\nhVJDlh" +
                               "7S6WufVqcCJ7V+pOEosWbBvwfkJ62mvvfY0Y/U3/9bJnjxs7MREiGdM83AeB" +
                               "gcFWscl6UN\nIXCj7GOOeMxzEg9/nEBk40MId0GCPQ3xGwADSf1dEOhZaOoA" +
                               "hNvnnGKwxEXHw2/4hPxgrfxYQE3v\nqqiF4j8wxZE5J/8HM6k99IMjGwrnD1" +
                               "4QhbVaM+ncHJJpgHSXc2hp7O5YklqR1u/IKy8fevWHO4rD\niGjpKwMhUhFl" +
                               "SXm7jBNh1F98oBzKOlyMgHM/Xf3j+y5ffD8iPqr+B/fAy8A4EwAA");
}
