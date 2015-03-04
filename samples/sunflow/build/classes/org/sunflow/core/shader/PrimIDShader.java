package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class PrimIDShader implements Shader {
    private static final Color[] BORDERS = { Color.RED, Color.GREEN, Color.
                                                                       BLUE,
    Color.
      YELLOW,
    Color.
      CYAN,
    Color.
      MAGENTA };
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay(
                                                           ).
                                                         dot(
                                                           n));
                                                   return BORDERS[state.getPrimitiveID(
                                                                          ) %
                                                                    BORDERS.
                                                                      length].
                                                     copy(
                                                       ).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public PrimIDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYfWwcRxWfOzv+imNfnCZxQ+wkjlNw3N62oCKKq4B9tRsH" +
                                                    "p7763Ahcte7c3tzdJnu72905++Jg2kRUiYoUVdRt0y9LVOknaVIhooJQpfwD" +
                                                    "bVWK1AqB+IMG+IeKEIn8QakoUN6b2a/bO7tUwEk7Nzvz3sx78977vTd75jJZ" +
                                                    "49hk0DL1wwXd5ElW4cmD+o1JfthiTnLfxI1pajssl9Kp40zD2Kza90rnBx89" +
                                                    "VEzESdMM2UANw+SUa6bhTDHH1OdYboJ0BqOjOis5nCQmDtI5qpS5pisTmsOH" +
                                                    "JsjaECsn/ROeCAqIoIAIihBBGQ6ogGkdM8qlFHJQgzv3km+R2ARpslQUj5Md" +
                                                    "1YtY1KYld5m00ABWaMH3A6CUYK7YZLuvu9S5RuFHBpWlx+5O/KCBdM6QTs3I" +
                                                    "oDgqCMFhkxnSXmKlLLOd4VyO5WbIeoOxXIbZGtW1BSH3DOlytIJBedlm/iHh" +
                                                    "YNlittgzOLl2FXWzyyo3bV+9vMb0nPe2Jq/TAui6KdBVajiG46BgmwaC2Xmq" +
                                                    "Mo+l8ZBm5DjZFuXwdez/GhAAa3OJ8aLpb9VoUBggXdJ2OjUKSobbmlEA0jVm" +
                                                    "GXbhZMuKi+JZW1Q9RAtslpPuKF1aTgFVqzgIZOFkY5RMrARW2hKxUsg+l2+7" +
                                                    "+eQRY68RFzLnmKqj/C3A1BthmmJ5ZjNDZZKxfffEo3TTayfihADxxgixpHn1" +
                                                    "m1e+em3vhTckzWfq0ExmDzKVz6qnsx3vbE0N3NSAYrRYpqOh8as0F+6fdmeG" +
                                                    "KhZE3iZ/RZxMepMXpn72jftfYpfipG2cNKmmXi6BH61XzZKl6cy+lRnMppzl" +
                                                    "xkkrM3IpMT9OmqE/oRlMjk7m8w7j46RRF0NNpniHI8rDEnhEzdDXjLzp9S3K" +
                                                    "i6JfsQghzfCQQXjaiPyJf06+rhTNElOoSg3NMBXwXUZttagw1VQcWrJ0sJpT" +
                                                    "NvK6Oa84tqqYdsF/V02bKU6R5pitpG2tNH5LRrwk0cOs/+PaFdQrMR+LwZFv" +
                                                    "jQa8DrGy19SBdlZdKo+MXjk7+1bcDwD3RACiYLeku1sSd0vK3ZLh3UgsJja5" +
                                                    "CneVNgWLHILYBtRrH8jcte+eE30N4EzWfCMcJ5L2gXauKKOqmQoAYFzAnApe" +
                                                    "2P3MnceTHz7/FemFyspoXZebXDg1f/TAfdfHSbwadlE1GGpD9jSCpQ+K/dFw" +
                                                    "q7du5/H3Pzj36KIZBF4Vjrt4UMuJ8dwXNYJtqiwHCBksv3s7PT/72mJ/nDQC" +
                                                    "SAAwcgqODJjTG92jKq6HPIxEXdaAwnnTLlEdpzxga+NF25wPRoR3dGDTJR0F" +
                                                    "DRgRUMDr2I8vPH7+icGb4mEk7gzltgzjMq7XB/afthmD8d+eSj/8yOXjdwrj" +
                                                    "A8XOehv0Y5uCKAdrwIk98Ma9v7n43ulfxgOH4ZDuylldUyuwxjXBLoABOuAQ" +
                                                    "mrX/DqNk5rS8RrM6Q7/7R+euG87/+WRCGkqHEc/O137yAsH41SPk/rfu/luv" +
                                                    "WCamYg4KNA/I5AFsCFYetm16GOWoHH235/HX6dMAkQBLjrbABNIQoRkRR58U" +
                                                    "FhkQ7XWRueux2W7VzFXESLcfUAMrx8cYptJQXP19Us8e+8OHQqOayKiTQSL8" +
                                                    "M8qZp7ak9lwS/IGLIve2Si3SQNkR8H7+pdJf431NP42T5hmSUN2a5gDVy+gt" +
                                                    "M5DHHa/Qgbqnar46J8sENOSH4NZoeIS2jQZHgHDQR2rst8l4EDTr4UyvxlMe" +
                                                    "gafdzQRrscHZDRa2V1ViRHS+JFh2iLYfm88KmzRw0mzZ2hwkLPBeR5RPHOTQ" +
                                                    "DKpXYG5kcuqW0akMWG7XypYTTiRz8vJzO39x3/LO38Opz5AWzQH9hu1CnSIh" +
                                                    "xPOXMxcvvbuu56wAk8YsdaSm0eqqtniqqonEwbT7BzOK57APnrfdg3lbpsjb" +
                                                    "/4s05hx2OCsp4yUojtLUYLqXG//3i8q42cjJ5nBm05AI0cCtQDHSYm5s4fsX" +
                                                    "sEl5Jh+vb/I4dj+HzR6wcJPOjAIvrh6amEWh9plzizNlsevioafef1mmvGgc" +
                                                    "RojZiaUHP06eXIqHyt2dNRVnmEeWvMKe66Q9P4ZfDJ5/4YN2xAFpz66UW3dt" +
                                                    "9wsvy0L83bGaWGKLsT+eW/zJC4vHpRpd1dXeKFxmXv7VP3+ePPW7N+sUHA3g" +
                                                    "jPgyYlV8S8Tl8XqWkyiLMAAVsWkwBGxvTpYgmpn0byMwWamxqU16qgqQ/cLf" +
                                                    "A5x68MXvv8rfGfyy1GD3ygaMMr5+7E9bpvcU7/kUZce2yHlGl3xx/5k3b71G" +
                                                    "/W6cNPhwV3OtqWYaqga5NpvBPcyYroK6XunS4qzr+3NM+HNlleTEVpkrYEMB" +
                                                    "8lS0kTQpnPu2+sl3tGRxkS4XfrT5hzc/v/yeyP4VAbgJSyyYrcp49aJY1Key" +
                                                    "JEVH7Vnp+iSc9PSxpeXc5LM3xF15pzlp5aZ1nc7mmB7aqkH07/LxbwNxs0OH" +
                                                    "i38d0cQgZA4kHljJj3trZBeOwQCM8SQ8sk1hsoz8H06Pi23KqxjgCDZQOTaV" +
                                                    "rRxEr6DZj83t0qQZyEJZ09QZNWrLi4jS+JCt8HS5Snf9x0rHqg3WU9dgcL/G" +
                                                    "LwxMLPPtVZQ6gc1RTtYWGJ8CPvR2HBr7RBVE/t4DT7erQventRu+PiA2E6Qn" +
                                                    "VxHzIWy+wwFmVcrBnumiyV0QiJigcc7UcnXKO07aw1csDJ3umo828kODena5" +
                                                    "s2Xz8h2/lnne+xjQCjfyfFnXw9VOqN9k2SyvCWlbZe0jw+xUvaCSlz4sZERH" +
                                                    "iPuYpH+Sk0SUHtTCvzDZMtgsRAa+5/bCRN8D/Aci7D5jeR6TCOBeVn0VEgpP" +
                                                    "vFeE36ouGQjX4oOYB61l+UlsVj23vO+2I1e++KzAaYAourCAq7RA7SOvTj48" +
                                                    "71hxNW+tpr0DH3W80rrLQ5KqS1VYNuwf/Dfqy5qBfhQAAA==");
}
