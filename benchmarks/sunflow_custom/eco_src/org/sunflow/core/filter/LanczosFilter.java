package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class LanczosFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return this.sinc1d(x * 0.5F) * this.
                                           sinc1d(
                                             y *
                                               0.5F); }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-5F) return 1;
                                    if (x > 1.0F) return 0;
                                    x *= Math.PI;
                                    float sinc = (float) Math.sin(3 * x) /
                                      (3 *
                                         x);
                                    float lanczos = (float) Math.sin(x) /
                                      x;
                                    return sinc * lanczos; }
    public LanczosFilter() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRifu+u19AEtBVooj9JSECi9hUSMUhLAUqD0CrXH" +
                                                   "swXLdHfuunRvd9md\nu14LIsREEOKDqIkmisSQFBDEBA2aoEIARfkHTNSEBN" +
                                                   "SQqIliYkwQo3/4zcw9966FcMnO7c58z/m+\n7zffnLyDvLaFpsi2jw6YxPY1" +
                                                   "BdqxZROlScO2vQ6muuXL3sL2oVbdcCOXH7lVhaJSv2xLCqZYUhWp\nZXljzE" +
                                                   "L1pqENhDSD+kiM+rZrC+PyVvsXZgncePhs+d6jedVu5PWjUqzrBsVUNfRmjY" +
                                                   "Rtisr823EU\nSxGqapJftWmjH40meiTcZOg2xTq1d6DdyONH+abMZFJU408o" +
                                                   "l0C5ZGILhyWuXmrnakHCOItQrOpE\nWZZUB5zzMjnB7DhfRzY1CBnFFjeAO9" +
                                                   "wC8Hp60mvhbZarpufYhsd2HTnuQaWdqFTVA0yYDJ5Q0NeJ\nSsIk3EMse5mi" +
                                                   "EKUTjdUJUQLEUrGmDnKtnajcVkM6phGL2B3ENrQoIyy3IyaxuM7EpB+VyMwn" +
                                                   "KyJT\nw0ruUVAlmpL48gY1HAK3K1JuC3dXsHlwsEgFw6wglkmCJa9P1SHi1U" +
                                                   "6OpI91rUAArAVhQnuNpKo8\nHcMEKhex1LAekgLUUvUQkHqNCGihqGpYoWyv" +
                                                   "TSz34RDppmiik65dLAFVId8IxkLRBCcZlwRRqnJE\nKS0+9RV39x9767OlPL" +
                                                   "fzFCJrzP4iYJrmYOogQWIRXSaC8V7E91rL5sgUN0JAPMFBLGiWzTy73v/r\n" +
                                                   "59WCZnIOmrU924lMu+U1h6o7dq40kIeZMco0bJUFP8NzXg7t8ZXGmAlVW5GU" +
                                                   "yBZ9icXzHV9s3nOC\n/OZGRS0oXza0SBjyaKxshE1VI9ZKohMLU6K0oEKiK0" +
                                                   "18vQUVwLsfUl7Mrg0GbUJbUJ7Gp/IN/g1b\nFAQRbIsK4V3Vg0bi3cS0l7/H" +
                                                   "TIRQATxoHjxeJH78n6LHfZId0YOa0S/ZliwZVij5LRsWkUA6ZIbk\nx7o8aN" +
                                                   "gr+JePZZBJ0Tqp1wgTCctYV3VDCqlQs7LRoJAo+39IuTFmc3m/y8VA0FnMGt" +
                                                   "TBKkNTiNUt\nD93+eldz6wv7RaKw5I57S9FMUOeLq/MxdT6hzpehDrlcXMt4" +
                                                   "plYEDLa7DwoXIK5kTmDr6m37az2Q\nKWZ/HuwVI60Fv+K2NMtGU6q6WzgQyp" +
                                                   "BiE9/t2ue7N7REpJg0PAjn5C6+durqkb9K5rqROzdCMh8B\no4uYmHYGq0nk" +
                                                   "q3PWVC75fxxoO/Pd1ZuzU9VFUV1W0WdzsqKtdUbDMmSiAAymxB+dVOrZiDYc" +
                                                   "cqM8\nQAJAP24/AMs0p46M4m1MACHzpcCPioOGFcYaW0qgVxHttYz+1AxPkz" +
                                                   "I2jBcZwwLpMJBj6L3n8ud/\nf674Mvc4AbelaQdagFBRvGNTebDOIgTmb77R" +
                                                   "/urrd/Z18SSIZwGFUy7So6lyDFhmpVigajVADhaj\nuvV62FDUoIp7NMKS6b" +
                                                   "/SmQs++v2lMrHrGswkgjbv/gJS85OeRHuuPv33NC7GJbNTI+VGikx4My4l\n" +
                                                   "eZll4QFmR2zvN1Pf/BK/DaAGQGKrg4RjA+KeIb6PPr69c/jY4Fibz4ZakD1v" +
                                                   "mKzOcUZ3y7tOhGoj\nO776hFtdjNMP+/QwtGGzUQSV6x4HSrn+BidmsdUJJh" +
                                                   "srWAgqndW7Ctu9IOzR82u2lGnn/wW1naBW\nhgPUXmsBcsQyIh2n9hbcuHCx" +
                                                   "Ytt1D3KvQEWagZUVmOc/KoTEI3YvgE7MXLKUm1HWP4qNfF8Qt7Yq\nvkuxjC" +
                                                   "/+MYOPs+LZw95np1O5+HslRZVZmCVQivk4dbjjkh/1+zb9WfI8vrRVIE555h" +
                                                   "HUDG3aLwMX\nySOLX/wpB1IWUsNs0EiUaGk2eZjKDKRr451Eqs4PHH/vLL1e" +
                                                   "v0ionDs8yDkZ5y46Mli96PTBh8C3\nascmOEWPjU5+ytOrXnHzZkdAW1aTlM" +
                                                   "nUmL4doBTsiVg621g2U8LTcXoyHYtZUKvgyY+nY74zHTkQ\nsWHRCFXUOsJa" +
                                                   "GxtWUVQQIjQA5QmBmJh+EbDUMDQUUQ6at5+v/fTK+nf2iRjMGaHbT+fqlp/9" +
                                                   "4cc+\nz8sXegSfs6lyEB+advTnM7c7xovUEZ3njKzmL51HdJ/cm1KTJW/NSB" +
                                                   "o49aX6mpO7O25xixjfUsqA\n2sDiXrGADS2iZhbetwL5x/LMmM2Cpyges6IH" +
                                                   "jpmbS3SzzwAfOOnWEaLXzYZOijwQPc6Rsr/rIe0v\nYZM18IyJ2z8mp/05kA" +
                                                   "aSyLTUKLSVMYfJrhQUCZ+2j+AT774h6Pm2qssLFIdb5EHdilE0OqP5YkfU\n" +
                                                   "xKy7mrhfyP4bO7fc9X/7D28jkneAYmjEgxFNS6vY9OrNNy0SVLnRxeIgMfmf" +
                                                   "nQtbRT9I2W2MvXBb\nLUEfhfuuk56iPPaXTjZAUXEaGWx3/C2daBckAxCx12" +
                                                   "fMBNKX8dOHXcB84raReWqwnZmRUc38+pxA\nvYi4QHfLm051TY8dXPcKh1Iv" +
                                                   "XLwHB/lNCS5+on1KImfNsNISsq6hD05vOPf+E4kKzGisstJzgVgd\nIfSA1r" +
                                                   "kbm+awSXkrMvhx5YeLhw7fcvPW6n+WBL5w9RAAAA==");
}
