package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class NormalShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal();
                                                   if (n == null) return Color.
                                                                           BLACK;
                                                   float r = (n.x + 1) * 0.5F;
                                                   float g = (n.y + 1) * 0.5F;
                                                   float b = (n.z + 1) * 0.5F;
                                                   return new Color(r, g,
                                                                    b); }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public NormalShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169281078000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRP9A5TuI8IHbihEfi5DaRmlaNEcQ1Djly" +
                                                   "SQ5fEoIDmPHu3N0m\nezvL7qx9MVGAVk0CCNoIkEDiEVWRApSXBCggQRsEtE" +
                                                   "D+gUptJSQeVaS2UkulqhIEtX/0m29u7/b2\nzg4Klm52d+Z7zPf6zTd+4SvS" +
                                                   "4rnkCt1LikMO85Ij2Qx1PWaMWNTzdsPUhP5+S3vm9Habx0hTmsRM\nQ5BEWv" +
                                                   "c0gwqqmYaWumGo5JJBh1uH8hYXSVYSyQPWprK8m9Kb6gTe8vSZnvtONffHSE" +
                                                   "uaJKhtc0GF\nye1RixU9QbrTB+gU1XxhWlra9MRQmlzGbL84wm1PUFt4d5Ej" +
                                                   "JJ4mrY4uZQqyMh0o10C55lCXFjVU\nr2VQLUhY4DJBTZsZwxV1wLmulhO2Xe" +
                                                   "Ybq6cGIfPk4l4wB3cAVq+oWK2srTPViT+794eHTz4XJ4lx\nkjDtrBSmgyUC" +
                                                   "9I2TriIrTjLXGzYMZoyT+TZjRpa5JrXMGdQ6Tno8M29T4bvMG2Met6YkYY/n" +
                                                   "O8xF\nncFkmnTp0ibX1wV3Kz7Kmcwygq+WnEXzYHZv1Wxl7lY5DwZ2mLAxN0" +
                                                   "d1FrA0HzRtiHh/lKNi4+rt\nQACsbUUmCryiqtmmMEF6VCwtaue1rHBNOw+k" +
                                                   "LdwHLYIsm1Wo9LVD9YM0zyYEWRKly6gloGpHR0gW\nQRZFyVASRGlZJEqh+A" +
                                                   "z2fn382Sd/swVzu9lguiX33wFMfRGmMZZjLrN1phgv+MlHU7f6V8QIAeJF\n" +
                                                   "EWJFM3zlmT3pv/+2X9Fc3oBm1+QBposJfeeJ/rG7b+QkLrcxz+GeKYNfYzmW" +
                                                   "Q6a8MlRyoGp7KxLl\nYjJYPDv2u1vvfZ79I0Y6UqRV55ZfhDyar/OiY1rMvZ" +
                                                   "HZzKWCGSnSzmxjBNdTpA3e05DyanZXLucx\nkSLNFk61cvwGF+VAhHRRO7yb" +
                                                   "do4H7w4VBXwvOYSQNviRQfh1EPWHT0F+lNQ8385ZfFrzXF3jbr7y\nrXOXaV" +
                                                   "6BGszVdnK3SK0sfiRlAjmCZLUCLzKN6tQ2ba7lTShZna832JR8XprYktxxz3" +
                                                   "RTk4TAaClb\nUAXbuAW0E/rp8x8dHt1+/3GVJjK1y7YKshq0JcvaklJbUmlL" +
                                                   "hrWRpiZUslBqVdECXx+EqgV861qT\nvf2mO48PxCFNnOlmcJQkHQCrylsZ1f" +
                                                   "lItbRTiII65NeSX+0/lrxw+nqVX9rsCNyQu/PjF8+d/E/X\n2hiJNYZHaSIA" +
                                                   "dIcUk5GYWoG91dGCaiT/Xw/sePVP5z67plpa4Ky6iq/nlBU7EA2Gy3VmAAZW" +
                                                   "xZ9a\nmojfQvaeiJFmgAGAPtw/oEpfVEdN5Q4FKChtaUuTzhyGSS4F0NUhCi" +
                                                   "6frs5glnTLYaFKGBnIyAYR\nQC/8rHXDn9/qfB8tDrA2ETrNskyoyp1fzYPd" +
                                                   "LmMw/9njmUce++rYfkyCchYIOOL8ScvUS8ByVZUF\nStYC2JAxWr3HLnLDzJ" +
                                                   "l00mIymf6XuHLj6/98uFt53YKZIGjrLi6gOr/0J+Tec3d804dimnR5ZFTN\n" +
                                                   "qJIpaxZUJQ+7Lj0k91G67w/Ln/g9fQoQDVDEM2cYAgNBywj6MYnuXYPj+sja" +
                                                   "BjkMgOx1s2R1gwN6\nQj/8fH7Av+vDN3HXnTR80ofDsIM6QyqoqHsBKF1Hyk" +
                                                   "MNYMnVRY4ce2UIFkerdxv1CiDsB2d33tZt\nnf0vqB0HtTqcnt4uF8q+VBPp" +
                                                   "MnVL26fvvNt75ydxEttKOixOja0U85+0Q+IxrwCYU3Ku34Lb6J6e\nJ0f0C8" +
                                                   "HdLit7qVTzhR+rcLyqnD3y/ZowVRO+LxZkcR1kKZSSNi6f7azEc/7Yvn93Ha" +
                                                   "Xv3a4Qp6f2\n/BmFHu1vh95lV1/70F8aAGW74M56i00xK7SnuFRZg3Q7sI2o" +
                                                   "1vkDz/36jPhkcLNSuXZ2kIsyrt18\ncqZ/88sPXgK+9UecEBU9f+rym+MF84" +
                                                   "MYdjoK2uo6pFqmobA7QCnsx3dt6Vg504XpuKKSjp0yqEPB\nS/AMpyMCkRw2" +
                                                   "R6oohn6NBbHuq4s1msqgAZNlGpD1hsmy6jmcSaGa7XPU6c1y2AZA5TtwH2AQ" +
                                                   "zSXh\nq4RrFqElmULkPX904O0P9jxzTAVyzRz3hTDXhH7PF18ejP/inUnFF2" +
                                                   "3LIsQn+k799dXzYwtV/qne\ndVVd+xjmUf0rGpNwZAWsnEsDUr83uPKFI2Of" +
                                                   "444k3xZB2iY5txhVSbVRDilVepsuWsj4cUNt6DfA\nL1EOfeI7h76ptsyXNy" +
                                                   "xzaMHlJYShGDpHaDEx7xCkM8/EGPDJLG6IIGYRenF5ppSvHWj+xPcx/zr4\n" +
                                                   "LS2bv/RSM39u8y9mCarhc7jHlwPU/WWeTgVUU6bARRlUso6yap8gzVPcNKpe" +
                                                   "KX5Xr5QE6Qo3kPKY\nXVJ32VQXJD396d23fZ3+47fYClUuMZ1wk8j5lhVCnT" +
                                                   "ACtTouy5loSqc6DB183NPofFAtLZS5esGt\nHlH0P4ULe5Qe7JaPMNnPIZFC" +
                                                   "ZFAx5bcw0XFB4kAkX+93ggh14wkqb5BJdV2qPfmkZ1bVgAne/wPk\n9tV/AC" +
                                                   "b0fS/uX1F6cPcv8Tho0S06M4NXPbi5qhawgv4rZ5UWyPqYvPLy3rde+nEAAD" +
                                                   "XNYV1yb1Sr\nc0QeTpzGzdlo0RHYTs28sfi1a08//XkM28P/A4KQQvy2EQAA");
}
