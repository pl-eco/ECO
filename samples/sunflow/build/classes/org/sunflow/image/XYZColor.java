package org.sunflow.image;
public final class XYZColor {
    private float X;
    private float Y;
    private float Z;
    public XYZColor() { super(); }
    public XYZColor(float X, float Y, float Z) { super();
                                                 this.X = X;
                                                 this.Y = Y;
                                                 this.Z = Z; }
    public final float getX() { return X; }
    public final float getY() { return Y; }
    public final float getZ() { return Z; }
    public final XYZColor mul(float s) { X *= s;
                                         Y *= s;
                                         Z *= s;
                                         return this; }
    public final void normalize() { float XYZ = X + Y + Z;
                                    if (XYZ < 1.0E-6F) return;
                                    float s = 1 / XYZ;
                                    X *= s;
                                    Y *= s;
                                    Z *= s; }
    public final String toString() { return String.format("(%.3f, %.3f, %.3f)",
                                                          X,
                                                          Y,
                                                          Z); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWxUxxWeXf8bw/onYNcBY4yJaqB7S6ukSpzSwsoE001s" +
                                                    "YYJgo7IZ351dX3z/uHfWXkzdEqTIiAf65ySkon6oQPkjIaqK0qqKxEubROlL" +
                                                    "qqpVH5pUfWnUlAcemkZN2/ScmXv33r273gTJqqWZHc+cc+acOWe+c+Zeu0Wa" +
                                                    "XIfssi39dEG3eJKVePKkfm+Sn7aZmzyUvneSOi7LpXTqukdgLqsOvZr48OPv" +
                                                    "zXTGSXOG9FDTtDjlmmW6h5lr6XMslyaJYHZMZ4bLSWf6JJ2jSpFrupLWXD6a" +
                                                    "JutCrJwMp30VFFBBARUUoYKyL6ACpvXMLBop5KAmd0+Rb5NYmjTbKqrHybZK" +
                                                    "ITZ1qOGJmRQWgIRW/P8oGCWYSw4ZLNsuba4y+KldyvIzJzp/2kASGZLQzClU" +
                                                    "RwUlOGySIR0GM6aZ4+7L5VguQ7pMxnJTzNGori0IvTOk29UKJuVFh5UPCSeL" +
                                                    "NnPEnsHJdahom1NUueWUzctrTM/5/zXldVoAWzcFtkoLD+A8GNiugWJOnqrM" +
                                                    "Z2mc1cwcJ1ujHGUbh78BBMDaYjA+Y5W3ajQpTJBu6TudmgVlijuaWQDSJqsI" +
                                                    "u3DSv6pQPGubqrO0wLKc9EXpJuUSULWJg0AWTjZGyYQk8FJ/xEsh/9x65MGL" +
                                                    "Z8yDZlzonGOqjvq3AtNAhOkwyzOHmSqTjB0700/TTa+fjxMCxBsjxJLmtW/d" +
                                                    "/vrugZtvSpq7a9BMTJ9kKs+qV6Y3vLM5NXJ/A6rRaluuhs6vsFyE/6S3Mlqy" +
                                                    "4eZtKkvExaS/ePPwr4+ffZF9ECft46RZtfSiAXHUpVqGrenMeYiZzKGc5cZJ" +
                                                    "GzNzKbE+TlpgnNZMJmcn8nmX8XHSqIupZkv8D0eUBxF4RC0w1sy85Y9tymfE" +
                                                    "uGQTQtZDI93QGoj8E7+cUGXGMphCVWpqpqVA7DLqqDMKU62sw2xLGUtNKNNw" +
                                                    "yjMGdWZdxS2aed2az6pFl1uG4jqqYjkFf1rRDIgC5djxDBhhOUkMNfv/sUkJ" +
                                                    "Le2cj8XACZujEKDD7Tlo6TnmZNXl4v6x269k346Xr4R3RhD8sEfS2yMp9kj6" +
                                                    "e5BYTIi+C/eSvgXPzMIdB/TrGJn65qHHzw/BiZbs+UY41jiQDoFxngJjqpUK" +
                                                    "gGBcwJ0K0dj3k8eWkh899zUZjcrqqF2Tm9y8NP/E0e98MU7ilfCLBsFUO7JP" +
                                                    "ImiWwXE4eu1qyU0svf/h9acXreACVuC5hwvVnHivh6JH71gqywFSBuJ3DtIb" +
                                                    "2dcXh+OkEcACAJJTCGjAnoHoHhX3e9THSrSlCQzOW45BdVzyAa6dzzjWfDAj" +
                                                    "YmKDGHeBU9ZhwPdAa/ZugPjF1R4b+7tkDKGXI1YILD7wi5vP3vjRrvvjYdhO" +
                                                    "hBLhFOMSBLqCIDniMAbzf7o0+cOnbi09JiIEKLbX2mAY+xRAArgMjvXJN0/9" +
                                                    "8b13r/wuXo6qGIfcWJzWNbUEMu4JdoEQ1QG00PfDj5qGldPyGp3WGQbnvxM7" +
                                                    "9tz4+8VO6U0dZvxg2P3pAoL5z+0nZ98+8c8BISamYsIKLA/I5AH0BJL3OQ49" +
                                                    "jXqUnvjtlmffoD8GPAUMc7UFJmCJCMuIOHpFuGqn6JORtT3YDdpVa2Kiv9rH" +
                                                    "bZ6P22r6GLvhyG4NQmIDqD9Sp3xyNAMQfc5LOcpi93uzl99/WV7gaH6KELPz" +
                                                    "yxc+SV5cjoeS+PaqPBrmkYlcqLxemvgJ/MWg/RcbmoYTEsi7U142GSynE9vG" +
                                                    "QNlWTy2xxYG/Xl/85fOLS9KM7socNgYl2su//89vkpf+/FYN0ISbYFERUw+I" +
                                                    "Tmh7Xx1P7sfuy9WelK7s+yxeOIAVVAhG/zWhT5/7y0dCuyogrOGYCH9GuXa5" +
                                                    "P7X3A8EfIBJyby1VpxOoNgPeL71o/CM+1PyrOGnJkE7VK2WPUr2I9z4D5Zvr" +
                                                    "17dQ7lasV5Zisu4YLSPu5mhkhLaNYmHgERgjNY7bI/DXgafcD63RuxqN0asR" +
                                                    "I2JwSLAMiX4Hdp/30afFdrQ5inUyiR0ru7xT+nLszneaxC4Nwo6vgbApX1gm" +
                                                    "JKxU25Y4DkcweDWT6uHII3hjtqxWnYrbcuXc8kpu4uqeuBfQezlp45b9BZ3N" +
                                                    "MT0kqhklVRQBD4t6PAieCy+89Bp/Z9cD8t7tXD3go4xvnPtb/5G9M4/fQerf" +
                                                    "GrEpKvKFh6+99dA96g/ipKEcg1VPjEqm0crIa3cYvInMIxXxN1B2ZC+e7t3Q" +
                                                    "Ep4jE7XTbx2HYXesVAdbCnXWNOymOWksMC6Dd5VscqJa5R5P5Z61V/lUnTUx" +
                                                    "qUuVj9+hyr2eyr1rr/KZOmuL2M1JlTOfTWW83GSLp7av/hqoHJOwFWSlJ+vo" +
                                                    "vYTdWU4ajKIewqE6amMj26ANeWoPrf1Jf7fO2vexuwDYY4pCGAoqQfYV7Eal" +
                                                    "0K+CH+YsLfeptvTh5CC03Z4tu9felst11lawe4aTVm7JTxKCaiMnnaKSxASZ" +
                                                    "lAs16gZg859pWHz2VX0Akh8t1FdWEq29K4/+QTw8yh8W2uB1ny/qejiFhsbN" +
                                                    "tsPymlCyTSZUW/xc5aSr6rkICUX8Ch2vSMLnOVkXIoQk6o3CRC9B4AERDq/Z" +
                                                    "NWyXxUGJVCQqO5q2tlckEPG5zAf7ovxgllWvrxx65Mzt+66KzNGk6nRhAaW0" +
                                                    "pkmLfFCVE8a2VaX5spoPjny84dW2HX4i3IBddyhUQrptrf3YGDNsLp4HCz/v" +
                                                    "/dmDz628K147/wPYw76sxxQAAA==");
}
