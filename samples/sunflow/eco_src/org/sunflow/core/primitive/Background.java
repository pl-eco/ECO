package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public class Background implements PrimitiveList {
    public Background() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public void prepareShadingState(ShadingState state) { if (state.getDepth() ==
                                                                0)
                                                              state.
                                                                setShader(
                                                                  state.
                                                                    getInstance().
                                                                    getShader(
                                                                      0));
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (r.
              getMax() ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1165455048000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRP8CPOI4T4jh2YlqccAsRARojwHadcOSS" +
                                                   "HHbigJPUjHfnzhvv\n7Sy7s/bZCS9VTVJooRG0aiVKUxQ1QIFUpSgg0TYIaG" +
                                                   "nzD1SCSkjQVpFaJB5SVQmC2j/4Zmb3dm/P\nvqTxSTu7N/M95ve99pt97lNU" +
                                                   "5dholeok2KxFnMTgSBrbDtEGDew4u2BqXH2zqjZ9cptJY6gihWK6\nxlBjSn" +
                                                   "UUDTOs6JqS/GZf3kbrLWrMZg3KEiTPEgeMTZ6821ObSgTuefJ0y4MnKjtjqC" +
                                                   "qFGrFpUoaZ\nTs0hg+QchppSB/A0VlymG0pKd1hfCl1GTDc3SE2HYZM596D7" +
                                                   "UDyFqi2Vy2SoK+UrV0C5YmEb5xSh\nXkkLtSBhqU0Y1k2i9RfUAeeGYk7Yts" +
                                                   "c3XEoNQpbwxVGAI3YAqNcUUEu0JVCt+NOj1x86/kwcNY6h\nRt0c4cJUQMJA" +
                                                   "3xhqyJHcBLGdfk0j2hhqNgnRRoitY0OfE1rHUIujZ03MXJs4w8ShxjQnbHFc" +
                                                   "i9hC\npz+ZQg0qx2S7KqN2wUYZnRia/68qY+AswG4LYEu4W/g8AKzTYWN2Bq" +
                                                   "vEZ6mc0k3weGeUo4Bx3TYg\nANaaHGGTtKCq0sQwgVqkLw1sZpURZutmFkir" +
                                                   "qAtaGFq5oFBuawurUzhLxhlqj9Kl5RJQ1QpDcBaG\nlkXJhCTw0sqIl0L+Wd" +
                                                   "/2+dGnn/jdrSK2KzWiGnz/dcC0OsI0TDLEJqZKJON5N/F48i53VQwhIF4W\n" +
                                                   "IZY0/T2nd6c++n2npLliHpqdEweIysbVHcc6hw9upSjOt7HEoo7OnV+EXKRD" +
                                                   "2lvpy1uQtW0FiXwx\n4S+eGf7DXQ88Sz6Oobokqlap4eYgjppVmrN0g9hbiU" +
                                                   "lszIiWRLXE1AbFehLVwHMKQl7O7sxkHMKS\nqNIQU9VU/AcTZUAEN1EtPOtm" +
                                                   "hvrPFmaT4jlvIYRq4ELXwNWA5E/cGboxoTiumTHojOLYqkLtbOG/\nSm2iWL" +
                                                   "aeAwzTRBkAF2dt6ppagkeQxdAuZZLmiIJVbOomVbI65KxKr9bINL9fotw833" +
                                                   "PLTEUFL4LR\nZDYgD26jhkbscfXkuT8fGtr23aMyUHhwe2gZ6gF1CU9dgqtL" +
                                                   "FNQlAnWookJoaeVqpcPA3FOQuFDi\nGq4a2X/73Ue74xAp1kwl2IqTdgMuby" +
                                                   "9DKh0MsjspCqEKIdb+1N4jifMnb5EhpixchOflrn/7+bPH\n/9PQG0Ox+Ssk" +
                                                   "xwg1uo6LSfOyWqh866I5NZ/8zx7a/uJ7Zz/4epBdDK0rSfpSTp603VFv2FQl" +
                                                   "GpTB\nQPyJFY3xPWj0WAxVQiWA6if2D4VldVRHUfL2+YWQY6lJofoMtXPY4E" +
                                                   "t+9apjkzadCWZEmDSJ56Xg\nnHoezcvguswLb3Hnq8ssPrbJsOLejqAQhfb8" +
                                                   "t6uv+eur9W8Ks/g1uTH01hshTGZ4cxAsu2xCYP6D\nH6cf++GnR/aKSPFChc" +
                                                   "Gr0J0wdDUPLFcGLJDaBpQX7sh1u80c1fSMjicMwiPuf4091770ySNN0jUG\n" +
                                                   "zPie3XBhAcH8igH0wNlvfbFaiKlQ+aslgBGQSTRLA8n9to1n+T7yD/6l4yd/" +
                                                   "xD+FygfVxtHniCgg\nSCBDwo6KsHuvGBORtWv50A2yNywQ+vO8yMfVQ89mu9" +
                                                   "17/vSK2HU9DncEYTdsx1af9Dwf1nLrLo9m\n723YmQS6687s2NdknPkvSBwD" +
                                                   "iSq8QJ2dNlSOfJETPeqqmvdfe73t7nfiKLYF1RkUa1uwiH9UC4FH\nnEkoOn" +
                                                   "nrlltFbDXNLOGjgIyEEVZ6BsgX/ePPV4ZmKsTzcsiGkvqU9uuT8ADA6ljoDS" +
                                                   "ne7kfu/HfD\nYfzGfllkWorfOkPQmf1r9nXytZu+/495imMto9bVBpkmRmhr" +
                                                   "NVxlUXHbLpqHILUfeuaXp9k76zdL\nlb0L17UoY+/m43Odm089fAklrTNihK" +
                                                   "jo5ukr7ohP6m/FRH8jq1lJX1TM1Bc2ByiF/bi2yQ3LZxqE\nz9YUl5U+39n+" +
                                                   "vaSsBI4OciIm7Bor43IOlUDbxV3uk7WFyUbkvT+dFGqSZbJuJx+2QNlxLTgF" +
                                                   "EPBm\ne/gA4UcXx3nucPdv39r9syPSkVeVOSWEucbV+//296n4o69NSL5oMx" +
                                                   "YhPrb6xD9fPDfcKuNPdqxr\nS5rGMI/sWgWYRotnQFc5DYL6jfVdz903/KHY" +
                                                   "Eee7maGaCUoNgmVQbeTDVpmTmy6Yu+LPQLHrb4Cr\n1XN960W7vqI42ztKXD" +
                                                   "8yiTVovPnRgwgx42Vcq/JhH0NLLZvAQYqEmQXDHZbc+yhDldNU1wLs+xeD\n" +
                                                   "vQuuFR72FReNPbxzs8yakDMFR8ssYTvcXMG5ThRSXPfOlwKRsRhEm+Dq8BB1" +
                                                   "/L+JzP/aYhCkB8tg\nu5cPeYaaAVsB2ABvOaPgoNWgOARvdjHwNnpO8513Sc" +
                                                   "HaHg7WHBwhEtsxnBLz1wkJR8ug/h4fvsPQ\n5YB6D7UNTSL2Ba8qESzWIZIH" +
                                                   "aD6wwOHFWGAHXD2eBXou2gJxITHub7S1JF2H8axwvE/RXUKR5Id0\nR3ZWQV" +
                                                   "b/qIy1nuDDY3Ac133WQqTwFRJY5PHFWISr7fUs0ntJSXyizNov+PBzKE3g8g" +
                                                   "EMVT4byeML\n9zoBzqcuFidkVl1wiuNtbHvJRx/5oUJNvX9w3+epd78U55HC" +
                                                   "x4R6ONFnXMMI9QHhnqAaCm1GFwDr\nZbNpidsphlYufLCExqrwLLb7guT6Nd" +
                                                   "S4KBcUan4Lk73EUH2IDN5k3lOY6GUoh0DEH1+xfAM3iWaW\nf89JyI8XxU0o" +
                                                   "t8/aope8+Brnd1Su/B43rt75/N41+Yd3/UC0aVWqgefmuJi6FKqRp7FCV9a1" +
                                                   "oDRf\n1tvoV6dGX33hG/6LWXTrrd4RrCRkN8rVMt6HTnD+I9BQzmLi0DL38v" +
                                                   "Lf3HTyyQ9j4hD2FRB3X6VE\nFQAA");
}
