package org.sunflow.core;
import org.sunflow.SunflowAPI;
import java.util.Map;
final public class Options extends ParameterList implements RenderObject {
    public boolean update(ParameterList pl, SunflowAPI api) { for (java.util.Map.Entry<String,Parameter> e
                                                                    :
                                                                    pl.
                                                                      list.
                                                                     entrySet()) {
                                                                  list.
                                                                    put(
                                                                      e.
                                                                        getKey(),
                                                                      e.
                                                                        getValue());
                                                                  e.
                                                                    getValue().
                                                                    check();
                                                              }
                                                              return true;
    }
    public Options() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698393000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xe2wURRif3l1b+iB9AKW8WloKAoVbSMRESgK1Fjg4aL2W" +
       "AgUs092567Z7u8vu\nXHstBCEmFiE+iJpookgMSQFBTNCgCSoEUJR/wERNSE" +
       "ANiZooJsYEMfqH38zc3mOvLcQmnd2b+Z7z\n/b7fzJ66i3JtC82UbT8dNInt" +
       "b2prxZZNlCYN23Y7THXJV3ILWkfW64YH5QSRR1UoKgnKtqRgiiVV\nkQJPNs" +
       "QtVG8a2mBEM6ifxKm/V1uWsLcuuCzL4OYj58r3H/NVe1BuEJVgXTcopqqhN2" +
       "skalNUGuzF\n/ViKUVWTgqpNG4JoItFj0SZDtynWqb0L7UXeIMozZWaTopqg" +
       "41wC55KJLRyVuHuplbsFC5MsQrGq\nE6Ux6Q40F2VqQtgJvVC2NBiZwBY7IB" +
       "0eAWQ9O5m1yDYrVdN7vOOxPUdPeFFJJypR9TZmTIZMKPjr\nRMVREu0mlt2o" +
       "KETpRGU6IUobsVSsqUPcaycqt9WIjmnMInaI2IbWzwTL7ZhJLO7TmQyiYpnl" +
       "ZMVk\naljJPQqrRFOcX7lhDUcg7YpU2iLd1WweEixUITArjGXiqPj6VB0qXu" +
       "3WSOZYtx4EQDU/SmiPkXTl\n0zFMoHJRSw3rEamNWqoeAdFcIwZeKJo+plG2" +
       "1yaW+3CEdFFU6ZZrFUsgVcA3gqlQNMUtxi1Blaa7\nqpRWn/qKeweOv/npKo" +
       "5tn0JkjcVfCEpVLqUQCROL6DIRivdj/lcDW2MzPQiB8BSXsJBpnHtuU/CX\n" +
       "z6qFzIxRZFq6e4lMu+SNh6tDu9cYyMvCmGAatsqKn5E5b4fWxEpD3ISurUha" +
       "ZIt+Z/FC6POt+06S\nXz2oMIDyZEOLRQFHZbIRNVWNWGuITixMiRJABURXmv" +
       "h6AOXDexAgL2ZbwmGb0ADyaXwqz+C/YYvC\nYIJtUQG8q3rYcN5NTHv4e9xE" +
       "CE2Ef7QW/guR+ONPiur9kh3Tw5oxINmWLBlWJPlbNiwitZi81fwM\nNCZFa6" +
       "QeI0okLGNd1Q0pokKbysZihfSz58ObirPIygdychjVuVtWA7SvNTSFWF3yyJ" +
       "2v9jSvf/6A\ngAODcCInKAV48Cc8+JkHf8IDysnhhiczT6ISsI990JHAXcUL" +
       "2nas23mg1gsQMAd8sAlMtBaiT7hv\nlo2mVNsGOMPJgJ3Kd7YN+++PrBTYkc" +
       "Zm11G1i66fvnb0z+KFHuQZnfpYWkC+hcxMK+PLJKXVuZtl\nNPu/H9xw9ttr" +
       "t+an2oaiuqxuztZk3VjrLoBlyEQBfkuZPzatxLsZdRz2IB+0ONAajx8Yo8rt" +
       "I6Mr\nGxyGY7nkB1FR2LCiWGNLDi0V0h7LGEjNcGSUsmGyAAkrpCtATo73n8" +
       "1b8t35ois8Y4dHS9JOqjZC\nRVeWpXDQbhEC87deb33ltbvD2zgIEiigcHzF" +
       "ujVVjoPKvJQKtKMGlMBqVLdJjxqKGlZxt0YYmP4t\nmbv0w99eLBW7rsGMU7" +
       "RFDzaQmp/2BNp37em/qriZHJkdB6k0UmIim0kpy42WhQdZHPH9X8964wv8\n" +
       "FrAVMIStDhHe9Ihnhvg++vn2LuDjYtfaEjbUgu1FY6B6lMO3S95zMlIb2/Xl" +
       "xzzqIpx+iqeXYQM2\nG0RRue9J4DSAEkMGGbHVKSYbK1gJprq7dy22e8DYox" +
       "c2bi/VLvwDbjvBrQwno91iAVnEMyqdkM7N\nv3nxUsXOG17kWY0KNQMrqzHH" +
       "PyoA4BG7B3gmbq5cxcMoHZjARr4viEc7PbFL8Yxf/MccPs4T6PFQ\ngKCqY3" +
       "5YzU+XzuHvUymalUVXIaB1YokTh2U8a6xTkZ/ow1v+KH4OX94h+Kc886Rpht" +
       "vYz4OXyCMr\nXvhxFKosoIa5WCP9RMuIDFxm8N4GfmFIdf3BE++eozfqlwuX" +
       "C8emPLfiwuVHh6qXnzn0P9iu2rUJ\nbtNl/TOe8vaoVz38TiOILusulKnUkL" +
       "4d4BTiiVk621g2U8zBOTsJziJW4gbnxXmmg1PQUhYGctJq\nn+ozj8CHg4Kq" +
       "LBTw9Alcv1gjO2IV6WJt4tnYGuCuN47Tye1sCACVxUz4GiBQ4cr0DwlLjcKF" +
       "pJ9z\n853naj+5uuntYVHcBeN8LaRrdcnPfP9Dn/eli91Cz30pcwkfrjr209" +
       "k7ockCk+LmOifr8piuI26v\nPJkSk3VFzXgeuPTl+ppTe0O3eURMr5Gi/G7D" +
       "0AgWQFvKhnWiMMse3OqgnbhIMLqtzPqgEJdgOXhz\n9/Z7wW/+5kdi8qJaBL" +
       "fFcEzT0vCWjr080yJhlUdZJEjR5A/g/FI3MijysQcPrluIhSkqShODSBNv\n" +
       "6UIqRV4QYq+95kPiLpPfWN5zMgDBv+CcjoyJb7guecvpbbPjh9pf5m2eC99+" +
       "Q0P8sg7fHuKgT3Z1\nzZjWHFvX0ftnOs6/97hTxIwrQBb5LhWr45QUmGT0I7" +
       "g5alJ+aA59NPWDFSNHbnv4JeA/7+bncngP\nAAA=");
}
