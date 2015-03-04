package org.sunflow.core;
final public class IntersectionState {
    final private static int MAX_STACK_SIZE = 64;
    float u;
    float v;
    Instance instance;
    int id;
    final private StackNode[] stack;
    final private float[] rstack;
    Instance current;
    final public static class StackNode {
        public int node;
        public float near;
        public float far;
        public StackNode() { super(); }
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1170611152000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH+baAm1BoLQUBIFbSMSoJYFaWjg4aG1p" +
                                                       "gQKW6e7cdene7rI7\n114LQYgJoMYHURNN5BFDUiSgJGjQBBEC+ICYoImYkI" +
                                                       "AaEjVRTIwJ1ugffjOz99q7K9EmnZ2b+Z7z\nfd9vvjl5FxXYFpom2346bBLb" +
                                                       "39zZji2bKM0atu31sNQrXykobh9doxselBdEHlWhyBeUbUnBFEuq\nIgVWNM" +
                                                       "YsNN80tOGwZlA/iVH/dm2JI291cEmGwA2Hz1buPZZf60EFQeTDum5QTFVDb9" +
                                                       "FIxKaoPLgd\nD2IpSlVNCqo2bQyiB4gejTQbuk2xTu0daDfyBlGhKTOZFNUF" +
                                                       "48olUC6Z2MIRiauX2rlakDDRIhSr\nOlGaEuqAc0E6J5jt8HVkUoOQCWyzG9" +
                                                       "zhFoDXMxNeC28zXDW9x7sf3XX0HS/y9SCfqncyYTJ4QkFf\nDyqLkEgfsewm" +
                                                       "RSFKD6rQCVE6iaViTR3hWntQpa2GdUyjFrE7iG1og4yw0o6axOI644tBVCYz" +
                                                       "n6yo\nTA0rcUYhlWhK/FdBSMNhcLsq6bZwt5Wtg4MlKhhmhbBM4iz5A6oOEa" +
                                                       "91cyR8bFgDBMBaFCG030io\nytcxLKBKEUsN62Gpk1qqHgbSAiMKWiiamlMo" +
                                                       "O2sTywM4THopqnHTtYstoCrmB8FYKJrsJuOSIEpT\nXVFKic/8qnsHjr91fj" +
                                                       "nP7XyFyBqzvwSYZriYOkiIWESXiWAci/pfC2yKTvMgBMSTXcSCpmn22a7g\n" +
                                                       "z5/UCpoHs9C09W0nMu2V1x2s7di50kBeZsYE07BVFvw0z3k5tDs7jTETqrYq" +
                                                       "IZFt+uObFzo+3bTn\nBPnFg0oCqFA2tGgE8qhCNiKmqhFrJdGJhSlRAqiY6E" +
                                                       "oz3w+gIpgHIeXFalsoZBMaQPkaXyo0+G84\nohCIYEdUDHNVDxnxuYlpP5/H" +
                                                       "TIRQDfyj6fA/GYk//qVoiV+yo3pIM4Yk25IlwwonfsuGRaQASz8b\njgTcYK" +
                                                       "VC/Cx9TIqekvqNCJGwjHVVN6SwCgUrGwsVMsi+/0dojFlbOZSXx+DPXcYaVM" +
                                                       "AqQ1OI1SuP\n3rm6q2XNcwdEirC0dvwE+ABdfkeXn+nyZ+hqgFEeWGcoBOXl" +
                                                       "cWWTmHYRMTjvAahcwLiyeZ1bV287\nUO+FVDGH8uGwGGk9+OaY1CIbzcnyDn" +
                                                       "AklCHHat7evN8/NrpM5JiUG4WzcpdeP3Xt6B9lD3uQJztE\nMlcBpEuYmHaG" +
                                                       "qwnoa3AXVTb5vz2/9syNa7fmJsuLooaMqs/kZFVb7w6KZchEARxMij82xefd" +
                                                       "gLoP\nelA+QAHAH7cfkGWGW0da9TbGkZD5UhREpSHDimCNbcXhq4T2W8ZQco" +
                                                       "VnSzkbJonEYYF0GchBdOzZ\nwkXfniu9wj2O460v5UbrJFRUb0UyD9ZbhMD6" +
                                                       "rTfaX3397v7NPAmcLKBwzUX7NFWOAcucJAuUrSbS\nzG7o0iOGooZU3KcRlk" +
                                                       "z/+GYv/uDXl8rFqWuwEg/agvsLSK5PeRLtufb0nzO4mDyZXRtJN5JkwpuJ\n" +
                                                       "SclNloWHmR2xvV9Pf/MzfAhQDZDEVkcIBwfEPUP8HP38eOfxcaFrbxEb6kH2" +
                                                       "ghxZneWS7pV3nQjX\nR3d88RG3uhSn3vapYViLzUYRVK57Iiidi5whDbTY7m" +
                                                       "STjVUsBNXu6l2F7X4Q9siFdVvKtQt/g9oe\nUCvDDWq3WQAgsbRIO9QFRTcv" +
                                                       "Xqra9pUXeVpRiWZgpRXz/EfFkHjE7gfsiZnLlnMzyocmsJGfC+LW\nTnVOKZ" +
                                                       "byywvGzctd/q2sD0hWTm/fguPBq22H+CnlLPws16BLzsj5rsNjX9LbXE6yAh" +
                                                       "l3XSwTWKF3\nSvI+dmOwovD0kYgHFfWgctnp7rqxFmXF0APNiB1v+aADTNtP" +
                                                       "byzELdqYQJhp7upPUeuu/SSgw5xR\ns3mZKzN8yLnTqpzMqHJnRh7ik2WcZR" +
                                                       "Yf5zgVzOZzYxQ0wSUAQapJ7bUtNQJ39iCHpTv76j/+vOvI\nfgHl48QyjatX" +
                                                       "fua77we8L1/sE3zugLmID8449uOZOx2TBDaI5m5WRn+VyiMaPH4iPpOVQN14" +
                                                       "Gjj1\n5fl1J3d33HYsqkxvU1qglf9p+BJ5aOmLP2S5U73QggqEZeNiNiwXeb" +
                                                       "4kZz08kR6pqfBf7USqOkek\n2tjQxKJCnH5+hSnUBCjLEAO7rWj/j1ZMifdB" +
                                                       "8W8WKzY4VnhDmNdMh0vnxnF0xjJzzctMV3XMm9i5\ncHXY/LkRSwMNiOD0XA" +
                                                       "0xj97+jb+X7cOXt3ocHF4Ngpx3SqociooTvQ3fqKao/v4NEauAjFeT6PTl\n" +
                                                       "4M2dW+4Fv/mL3+eJbrwUWuJQVNNSKzVlXmhaJKRyS0tF3Zr8o8JL0m0OxJt9" +
                                                       "uLn9ggzwojSFjKIi\nZ5ZKZECIgIhNTTPubDmHdYZAfoFA6efMbsVZaUXMH6" +
                                                       "aOz2uj4mnaK288tXlm7IX1r/AerACetCMj\n/A0CTyrRlyRarrqc0uKyrqPT" +
                                                       "73Wfe/fxeOjSOpa0JEik2KTx0tpCtdk7hpaISfkdP/Jh9ftLRw/f\n9vCe5V" +
                                                       "/NkPwHTxAAAA==");
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
    final public StackNode[] getStack() {
        return stack;
    }
    final public int getStackTop() { return current ==
                                       null
                                       ? 0
                                       : MAX_STACK_SIZE;
    }
    final public float[] getRobustStack() {
        return rstack;
    }
    final public boolean hit() { return instance !=
                                   null; }
    final public void setIntersection(int id,
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170611152000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZDYwU1R1/u3vfd3IfwPEhcHAcUo5jlzs4CpwGjuOAg+U4" +
       "7w7QQ3vOzb7dG5id\nGWfeHstJrcYErMQqFZs2qYiWhI9ipbENbWJRgrRW2k" +
       "Rtq42JSGtj27Q2mqZK0ybt///ezO7s7Aci\nuMm8eTPvvf/3//f+b/bUh6TY" +
       "MskM2QqyPQa1gl0DfZJp0UiXKlnWILwali8Ul/cd26TpfuILE78S\nYaQ6LF" +
       "uhiMSkkBIJ9aztSJpkoaGre2KqzoI0yYI71Xab3sZwexbB7YfP1D14tKjBT4" +
       "rDpFrSNJ1J\nTNG1bpXGLUZqwjulMSmUYIoaCisW6wiTm6iWiHfpmsUkjVn3" +
       "kvtJIExKDBlpMjIn7DAPAfOQIZlS\nPMTZh/o4W6Aw0aRMUjQa6Uyxg5UtmS" +
       "tBbHtdf/ZsIFKGg9tAHS4BaD07pbXQNktVI3B827K9R04E\nSPUQqVa0ASQm" +
       "gyYM+A2RqjiNj1DT6oxEaGSI1GqURgaoqUiqMs65DpE6S4lpEkuY1Oqnlq6O" +
       "4cQ6\nK2FQk/N0XoZJlYw6mQmZ6WbKRlGFqhHnqTiqSjFQuz6ttlB3Hb4HBS" +
       "sUEMyMSjJ1lhTtUjTweIN3\nRUrHpk0wAZaWxikb1VOsijQJXpA64UtV0mKh" +
       "AWYqWgymFusJ4MLI9LxE0daGJO+SYnSYkaneeX1i\nCGaVc0PgEkYme6dxSu" +
       "Cl6R4vufyzsP6Th49/9+xqHttFESqrKH8FLJrlWdRPo9SkmkzFwiuJ4KGe\n" +
       "OxMz/ITA5MmeyWJO57wzW8N/ealBzLk5x5wtIzupzIbl3oMN/fet10kAxSgz" +
       "dEtB52doztOhzx7p\nSBqQtfUpijgYdAZf7v/5nQ+cpH/zk4oeUiLraiIOcV" +
       "Qr63FDUam5nmrUlBiN9JByqkW6+HgPKYV+\nGEJevN0SjVqU9ZAilb8q0fkz" +
       "mCgKJNBE5dBXtKju9A2JjfJ+0iCE3AQXqYOrjIgfvzPSHgxZCS2q\n6rtDli" +
       "mHdDOWepZ1k4Z6MPwsMAmogalCgxg+BiO3h0b1OA1JsqQpmh6KKZCwsr4oQs" +
       "fw/nmIJlHa\nut0+H8KfN41VyIANuhqh5rB87P3X9nZv+vrDIkQwrG09GWkE" +
       "XkGbVxB5BbN4EZ+Ps5iEPIWfwMq7\nIF8B2aoWDNy98Z6HGwMQIMbuIjARTm" +
       "0EjWxBumW9K53UPRz/ZIisqc/u2B+8cmyViKxQfuzNubry\n9ecuHvlnVbOf" +
       "+HMDIyoI0FyBZPoQTVOA1+RNpVz0//HI5hfeuvjul9JJxUhTVq5nr8RcbfS6" +
       "wtRl\nGgH0S5M/Oq06sJ1sO+gnRQAAAHpcfsCTWV4eGTnb4eAf6lIaJpVR3Y" +
       "xLKg45oFXBRk19d/oNj5Ea\n3p8IzqnEIJ4B1yw7qvkdRycb2NaLmEJve7Tg" +
       "+HrloZLFb79YeYGbxYHiatdmN0CZSOzadLAMmpTC\n+3e/3ffEkx/u38EjxQ" +
       "4VBjtgYkRV5CQsuSW9BDJaFRFoNW3V4npEiSrSiEox4v5bPa/1x3//Ro1w\n" +
       "jQpvHM+2XJ1A+v20NeSBi1/5dBYn45NxR0mrkZ4mtJmYptxpmtIelCP54Jsz" +
       "v/ML6SkAPAAZSxmn\nHDcI14xwO4a43Zt5G/SMtWLTCLRb8oR+jv17WN57Mt" +
       "aYuPeXP+VSV0ruQsDths2S0SE8j81ctO4U\nb/ZukKxRmLf05d67atSX/wMU" +
       "h4CiDPumtcUE2EhmONGeXVz6zrnz9fe8ESD+daRC1aXIOonHPymH\nwKPWKC" +
       "BO0li1msdWzW7EzBquMuFGmG4bIOl6KgPhFuRP/3W4+6czZ3ik5Xj4tS1PcQ" +
       "PkTfwcm5+H\nzvjZrYev/Jpd4nTSGYir5ySz4RQqpvTa5W+N1ZacfjruJ6VD" +
       "pEa2a7ptkprAOB+CEsRyCj2o+zLG\nM8sJsXd2pBBmhjf7XWy9uZ+GcejjbO" +
       "xXedJ9Glq7Fa5yO93LvenOkV34GUXi6B+jZt0fjxz99MH9\ny/0Y3MVjKDpY" +
       "pSY9rzeBVd++U0/OrDx0+QCCOAb/aiS6irNv4u18kegBBsIrmgQ6lhqmMga7" +
       "CqS+\nxSvJJCMTNnfeMTww2Nm1aXigZ6gbJJrqruZNJQ5VwRiHwPf3Nf7s1a" +
       "1P7xfbRoG4yVg1LH/tvcu7\nAo+dGxHrvMHhmXxw1tEPXni/f5KAGFE+zs2q" +
       "4NxrRAnJrV9tYLrNKcSBz35l4ZxT9/dfsiWqyyyE\nuuGw8Oc95+n8Wx/9Q4" +
       "5dOwBFrgBqbNuwWS1yqj1v7q1MRQXPwWq4KuyoqMiKCsI7W7M9ScBdvgR/\n" +
       "s9EQLLegcwEKvBJt+wIkGsZmO4gwhp0hD8d7rpFjvbMjOvccHCM2xzLFTkRO" +
       "Ygoj03JUTmKGRyp6\njVKhJFW2VFV5pNplSwVnWez1eliqn50llrikidgFr3" +
       "PPwdLIDgY/9tdhsx7Cohi0l3dh6eBKXr5d\nYsyfeHztxP4VOx7iFVs5nA4l" +
       "qzeNYLYePsiEeflzOkVsWJ5/95mPzp2l8/m2VaZYgJSdZizHScm1\n5mPpJN" +
       "38dvQwr7qKRiRLYKb3iJl9gsw4GPIkn5Bpv5sL2c8JmJarl9pNA2jCXj1CUz" +
       "WDzy6qudew\n2eM45KuFHLIAHFKiUi0mzjQ8QJJ2wiJZv5jvyDYxjexdqq5R" +
       "rJWcMVH3K3owdayHwWSWgCaZmVH1\nb+Y2S++aj5z4/hn2xsKVAu+a83vZu7" +
       "B55ZHxhpXPH/gctX6DJxi8pGvHbr49MKq86ufnfbEJZ30n\nyFzUkbn1VoA8" +
       "CVMbzNiAZws/cZtjc0uBOvDRAmOPYXMAMktGnwgXgp0bcte53XGD8cp0/CdT" +
       "fnTr\nscOX+K6chDNrJU+31sWL25a3wfo6yE/89BVUInZZtPbNdSMno9rJCD" +
       "dEBU+HTlxja1nO37jyNaAb\nFp7tXR/RbEpNWwwLT0E3uZj0rN17emNV2TMH" +
       "9nH6drKXu74T2M+lY5LZ697obMlbly1ZvoKRoRt+\nhF65dElLa/uitsWMVA" +
       "xu6BkIcgxD9k84gHa/SeqzDYZq2gmPEAAKT0inEO7e7kFQpKi/u3OtB6Xv\n" +
       "vUaUngPXBJvshDwo/ezVULrEFDCNzwmDh9mQYRgQJQKUVyxesZQPHnJbf0X7" +
       "F2L9ZW3c+q3Ailvf\nTJn/eEpSfDrssdz3rnFLnUxEeUGcew7Lnba31FI5Yc" +
       "KJm13zPv/DAkIlC2N1xuEIkTTf5z5eOe6/\n4+OqfdIrd/ttmOgD3zHdWKTS" +
       "Maq6SBXz/v6UKWqR/Hy4mm1TNHtNkdaosKi54ep8gbEL2JyFMipG\n2UA6AD" +
       "Nlm15Its+1jYrIduFfuwf/wrosqT1rnzlX+ebBxLKNjkm/hc1FRnZeR8zjWV" +
       "oNrdETWkTR\nYlxIOEhtUKgpmfLoHht/2ttbli5btKytGY5BFs+MDAvxyHrp" +
       "auGeCp0cDm+Ea6lt1KU31uG/LzD2\nDja/Ads76gzqnGdvWq3fXo9aC+C6zV" +
       "brthur1p8KjH2AzXvgK1CrXx9JWO5ozg2n7XzwV9j8lZGR\n64goRi2LqhC5" +
       "UICF1tBxCKXN1BoVkdT25eUtSxYvasdImpSKJK+M3PCXr8fw+MVwvW349TfW" +
       "8P8q\nMPYpNh/B0XdUYd7jZ+mIrqtU0tIafnw9Gq6Ca9DWcPD6NQyITyA8+H" +
       "l88AYJ+YryK+wrwZf/Y6Qa\n/8VwIZxX+aIxXYmkNPeRz6o51AK1WciJ5eXU" +
       "rH8Exb9Ycvid++76JPy7f4vzk/NPUyWUcdGEqrq/\nR7n6JYZJowo3W6X4Os" +
       "WzxFfDSI0XyUEZvKGYvmoxbSIgiGsa+NruuSfVQ1jAJOxOMZx9wvWxSnxn\n" +
       "y9xlUdO5GYcQ/qerc1BIiL9dh+U7ntsxO3lg8HF++oBCXBofRzIVUKyKr++p" +
       "w8acvNQcWq+T089v\ne/EHK5xdhn+dneQKqYyobBOj+d2JA980/g/MS1rzAB" +
       "8AAA==");
}
