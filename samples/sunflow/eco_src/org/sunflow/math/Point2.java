package org.sunflow.math;
final public class Point2 {
    public float x;
    public float y;
    public Point2() { super(); }
    public Point2(float x, float y) { super();
                                      this.x = x;
                                      this.y = y; }
    public Point2(Point2 p) { super();
                              x = p.x;
                              y = p.y; }
    final public Point2 set(float x, float y) { this.x = x;
                                                this.y = y;
                                                return this; }
    final public Point2 set(Point2 p) { x = p.x;
                                        y = p.y;
                                        return this; }
    final public String toString() { return String.format("(%.2f, %.2f)",
                                                          x,
                                                          y); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1414698736000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wcxRmfe9iOH+BX4sTBiWPHIY2d3JaIoBJHAte1E5NN" +
                                                   "7NqJAw6pM96dO2+y\nt7vszp3PTqCg0iSAKI0KUlFLiNpIDgEKEq3SSpQG8S" +
                                                   "glrRQqlUqRSEGRoFJLpQoJgto/+GZm93Zv\n73wEYmnHczvfY775ft9j9rmP" +
                                                   "UYVjozbFSdBZiziJ/rERbDtE7dex4+yCV5PKGxXVI/PbDTOKIjKK\naipF9b" +
                                                   "LiSCqmWNJUaeg7vTkb9VimPpvSTZogOZo4oG9y5d0hbyoSuOfE2aYHTsXbo6" +
                                                   "hCRvXYMEyK\nqWYaAzpJOxQ1yAdwFksZqumSrDm0V0bXESOT7jcNh2KDOveg" +
                                                   "+1BMRpWWwmRS1CF7yiVQLlnYxmmJ\nq5dGuFqQ0GwTijWDqH15dcC5vpATtu" +
                                                   "3yjRZTg5BFbHEczOE7AKtX5a0W1haZasVOj99y+OQzMVQ/\ngeo1Y4wJU8AS" +
                                                   "CvomUF2apKeI7fSpKlEnUKNBiDpGbA3r2hzXOoGaHC1lYJqxiTNKHFPPMsIm" +
                                                   "J2MR\nm+v0XsqoTmE22RmFmnb+jJIa0VXvV0VSxykwu8U3W5g7yN6DgTUabM" +
                                                   "xOYoV4LPGDmgEebw9z5G3s\n2g4EwFqVJnTazKuKGxheoCbhSx0bKWmM2pqR" +
                                                   "AtIKMwNaKFq+oFB21hZWDuIUmaRoWZhuRCwBVTU/\nCMZC0ZIwGZcEXloe8l" +
                                                   "LAPz0tnx47/fNXbufYjqtE0dn+a4BpZYhplCSJTQyFCMYrmcTjQ3dl2qII\n" +
                                                   "AfGSELGg6Vtzdrf8zz+0C5obStAMTx0gCp1Udh5vHz201UQxto1FlulozPkF" +
                                                   "lvNwGHFXenMWRG1L\nXiJbTHiL50bfvOv+M+RfUVQzhCoVU8+kAUeNipm2NJ" +
                                                   "3YW4lBbEyJOoSqiaH28/UhVAVzGSAv3g4n\nkw6hQyiu81eVJv8NR5QEEeyI" +
                                                   "qmGuGUnTm1uYTvN5zkIIXQcPaoInhsQf/09Rd0JyMkZSN2ckx1Yk\n007lf6" +
                                                   "dBANgHANyYYJixKBqUps00kbCCDc0wpZQGUaqYG1SSZf+vWlKO7atpJhJhiS" +
                                                   "4csDpgfZup\nq8SeVOYvv314YPtDxwQYGIBdiyhaCgoSroIEU5AQClAkwuUu" +
                                                   "ZoqEG+AQD0I4QuKqWze27479xzrB\n+Jw1E2fHAKSdsHdX+4Bi9vsxO8TTmw" +
                                                   "LAWfaLvUcTV+ZvE8CRFk6tJblrLzx//uQndd1RFC2d95hV\nkHlrmJgRlizz" +
                                                   "+awrHCml5P/n4R0vvXv+vW/4MUNRV1EoF3OyUOwMn79tKkSF5OaLP9VaH9uD" +
                                                   "xo9H\nURziG3Ia3z+ki5VhHQUh2eulN2ZLlYxqk6adxjpb8nJSDZ22zRn/DQ" +
                                                   "dGA583g3NqGUYb4al0Qcv/\ns9UlFhtbBJCYt0NW8PR55QeV3/z7y7Vv8GPx" +
                                                   "Mm19oJaNESrittEHyy6bEHj/3k9HfvLEx0f3cqQI\nqEQoFLjMlK4pOWC50W" +
                                                   "eBgNUhaTBHdu020qaqJTU8pROGuP/Xr7npN//+UYNwjQ5vPM+u/3IB/vvW\n" +
                                                   "b6P7z3/vs5VcTERhBcM3wycT1jT7kvtsG8+yfeQe+OuKJ/+In4J8BjnE0eYI" +
                                                   "TwuIW4b4OUr83Lv5\nmAit3cSGTpC9fgHolyjPk8rhM6nOzD1/+h3fdS0O1v" +
                                                   "mgG3Zgq1d4ng2r2ekuDUfvNuxMA93N53be\n3aCf+x9InACJCpRFZ9iGXJEr" +
                                                   "cKJLXVF18dXXWva/E0PRQVSjm1gdxBz/qBqAR5xpSDM567bbObYa\nZhaxkZ" +
                                                   "uM+CEsdw+A/2grRmW1i8rqkqhkw42hI41yiVEwcFmwv7O1NNSJLI+ay0c6f/" +
                                                   "/W7qePikyz\nrkwTF+SaVL7/j/cPxh57dUrwhWtliPj4ylMfvnR5dLFApWgo" +
                                                   "VhfV9CCPaCq4ZfUW81BHOQ2c+vWe\njufuG73k7qipsDQOQPv40exrZO2WRz" +
                                                   "8okd0hWk3MQ6WP69xSBptb2XArX9rIhs3CYZu+tl+vd/16\n/VX7NSIShHMv" +
                                                   "G1FrwLc7TYOXd01h55LzkM3ar4RNkixuWcrNzX6w9uKqPzf0nxcRPk3RmkCj" +
                                                   "5lJK\nQ0bWVHgIbcOGCq2DCPi2kgr32NiCVuzC+x/ue6znozeZGyxuws4yhz" +
                                                   "nOhu3+YcpXe5i5wC8G8DLI\nHWQdsF9eJqfWn5bfHn6KW75gdSwB6pCcuVd2" +
                                                   "n7jyF3qJy/HLFOPuyBU3GnBr8Hm/9W62sfLFp9NR\nVDWBGhT3XjOO9QwrBh" +
                                                   "PQhjveZQfuPgXrhS216B9782W4LRxVAbXhAumHAMwZNZvXlaqJLfDEXZTG\n" +
                                                   "wyiNID7Zz4FKUYQL6BOVMu9X/GV+3fvVNRJP42wpjckyGsVSFx/XimobZUlA" +
                                                   "MzBv/9flCgPXRisW\nukHw1HP0zv/WHcGv74u6qO6nkPFNa4NOskQPiGLd34" +
                                                   "qC7m8HvzP5wHj4mWfP0nd6Nosk1r0wqMOM\n3ZtPzrVvfuGRr9HztYdsC4tu" +
                                                   "zN7w3di09laUX+sEzoqug4VMvYXoqoH9ZGxjVwHGVuU9zpIg2gBP\ns+vx5t" +
                                                   "J9V7HT2DzosaLSl0eGyOuHyqSie9mQpeAkIr4wDPNxzIfUzFfK9HahfWvgaX" +
                                                   "Xta712+7wS\nENgol/JQGQsfZcOD5Sz84bVY2AlPu2th+7VbGNz5k2XWfsaG" +
                                                   "xylaRE3xgYFTLaWowS99gQVu5xNX\nXWagCRcXPVb4lhV97RFfKBT54qG7P5" +
                                                   "X/9jm/suS/ItTCVT6Z0fVgng3MKy2osxq3oVZkXVEvT8HW\nw7dNiuLsH9/b" +
                                                   "LwXZPEW1ATKKqtxZkOgMeBuI2PRZq8TBiPpRmO6YpasLMg//oOZlh4z4pDap" +
                                                   "3Pn8\n3lW5R3b9mKecCkXHc3NMTI2MqsTVK59hOhaU5sm6gF58YfzlX93qZV" +
                                                   "Demi8OoKYAeBvFahkfQlYr\nfd8ZSFuU31Dmfrv011vmT1zifYr1BY+gcG0H" +
                                                   "FQAA");
}
