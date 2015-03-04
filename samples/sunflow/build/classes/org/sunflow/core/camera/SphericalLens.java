package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class SphericalLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { double theta = 2 * Math.PI *
                                       x /
                                       imageWidth +
                                       Math.
                                         PI /
                                       2;
                                     double phi = Math.PI * (imageHeight -
                                                               1 -
                                                               y) /
                                       imageHeight;
                                     return new Ray(0, 0, 0, (float)
                                                               (Math.
                                                                  cos(
                                                                    theta) *
                                                                  Math.
                                                                  sin(
                                                                    phi)),
                                                    (float)
                                                      Math.
                                                      cos(
                                                        phi),
                                                    (float)
                                                      (Math.
                                                         sin(
                                                           theta) *
                                                         Math.
                                                         sin(
                                                           phi)));
    }
    public SphericalLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXW2wUVRg+3d5Ly/ZCS7mVthS0FHYgBhMsXspaoHWBTQsk" +
                                                    "lEA5nTm7O+3szDBztt0Wq0BiSnggRguC0T4YCKLcYiRoDEmfBIIvGKPxQfBN" +
                                                    "ovLAC5qg4n/O2cvs7Lbqg5vM2TPn/Oe//9/558IDVGhbqM00tNGwZlAfiVPf" +
                                                    "oLbOR0dNYvu6A+uC2LKJ4tewbe+AtX65+Yr30eM3I5UeVNSHarCuGxRT1dDt" +
                                                    "HmIb2jBRAsibXu3USNSmqDIwiIexFKOqJgVUm7YH0BzHUYpaAkkVJFBBAhUk" +
                                                    "roLUkaaCQxVEj0X97ATWqX0AvYbyAqjIlJl6FDVlMjGxhaMJNkFuAXAoYe+7" +
                                                    "wCh+OG6hxpTtwuYsg0+0SZPv7Kv8JB95+5BX1XuZOjIoQUFIHyqPkugAsewO" +
                                                    "RSFKH6rSCVF6iaViTR3jevehalsN65jGLJJyEluMmcTiMtOeK5eZbVZMpoaV" +
                                                    "Mi+kEk1JvhWGNBwGW+vStgoLN7F1MLBMBcWsEJZJ8kjBkKorFC11n0jZ2PIK" +
                                                    "EMDR4iihESMlqkDHsICqRew0rIelXmqpehhIC40YSKFo4YxMma9NLA/hMOmn" +
                                                    "qN5NFxRbQFXKHcGOUFTrJuOcIEoLXVFyxOfBtg3HD+pbdA/XWSGyxvQvgUMN" +
                                                    "rkM9JEQsostEHCxfGTiJ664f9SAExLUuYkFz7dWHL61qmL4paBbloNk+MEhk" +
                                                    "2i+fGZh7Z7G/dX0+U6PENGyVBT/Dcp7+wcROe9yEyqtLcWSbvuTmdM+Xuw99" +
                                                    "RH7xoLIuVCQbWiwKeVQlG1FT1Yi1mejEwpQoXaiU6Iqf73ehYpgHVJ2I1e2h" +
                                                    "kE1oFyrQ+FKRwd/BRSFgwVxUDHNVDxnJuYlphM/jJkKoGB60Bp4SJH78n6Ld" +
                                                    "UsSIEgnLWFd1Q4LcJdiSIxKRDcnGUVODqNkxPaQZI5JtyZJhhVPvsmERSYas" +
                                                    "srDUa0agSmSsBYhu+1iKmf8n8zizrHIkLw+cvthd8hpUyxZDU4jVL0/GNnY+" +
                                                    "vNR/25MqgYRPKFoO4nwJcT4mzifE+TLEobw8LmUeEyvCCkEZgvIG4Ctv7d3b" +
                                                    "vf9ocz7kkzlSAB5lpM1gX0KXTtnwpzGgiyOdDIlY/8GeCd/v514UiSjNDNg5" +
                                                    "T6PpUyOHd72+xoM8mcjLbIOlMnY8yPAyhYst7orLxdc7cf/R5ZPjRrr2MqA8" +
                                                    "AQnZJ1lJN7ujYBkyUQAk0+xXNuKr/dfHWzyoAHACsJFiyGWAnQa3jIzSbk/C" +
                                                    "JLOlEAwOGVYUa2wriW1lNGIZI+kVnh5z2VAtMoUF0KUgR9hNn0+fvvpu23qP" +
                                                    "E4y9juutl1BR2lXp+O+wCIH1H04F3z7xYGIPDz5QLMsloIWNfih0iAZ47I2b" +
                                                    "B76/d/fMN550wlC48WIDmirHgceKtBSAAQ2giIW1ZaceNRQ1pOIBjbC8+8O7" +
                                                    "fO3VX49XikBpsJKM86p/ZpBeX7ARHbq977cGziZPZtdQ2vI0mXBATZpzh2Xh" +
                                                    "UaZH/PDXS07fwO8DSgIy2eoY4WCDuGWIu97HI9LKx9WuvTVsaDSz9uJ8pT7x" +
                                                    "xl+a+NjChqeE39j0aSdlHp/XUrQoq7D9vLBZOTMnL5np9uE355kjk1PK9rNr" +
                                                    "RWlWZyJ6JzQsF7/98yvfqR9v5YCUUmqYqzUyTDSHXh4mMgMStvKLOV0Yx85/" +
                                                    "fI3eaXtOiFw5Mxq4D9448vPCHS9E9v8HIFjqMt7N8vzWC7c2r5Df8qD8FAZk" +
                                                    "9RqZh9qdbgChFoHmSGcOZStlPNQNXIEqcEcNC+oCeEoTNxH/Z7s1JhvniYpl" +
                                                    "wzOu3PEk/JmIc0NWnLmpBFoZlpxJsjonWa/47wh2cTEvz5Kd3WzogPKMmQrc" +
                                                    "zhDF1lkaa0uNwl0/nGhGpPHqe0Pv3b8oIuruXFzE5OjksSe+45MeR3u3LKvD" +
                                                    "cp4RLR7XskI49gn88uD5iz3MBLYgrvhqf6LPaEw1GqbJ6qBpNrW4iE0/XR7/" +
                                                    "4sPxCU/CJespKh4wDI1gPbtq+cLzqTh72WItPBWJOFf86zgXc47F/D3AhqBg" +
                                                    "3kMZRhuYo11fru18aJfZdG+uzSLFAKDlSbmfD1yD3bOkgMKGXXAyTGgPHk1m" +
                                                    "1LysxIPNHDBGUUVGL8FgtD7rA0U01fKlKW/J/Kmd3/HbMdX4lkL3GYppmqO+" +
                                                    "nLVWZFokpHJdS8WlZ/K/IYrmz9DegDViwvUdFPTQ2Va66SkqYH9OsgMUzXGQ" +
                                                    "QTYkZk4iCmEAIjaNmUmXVfLbg311+ESLHUcZIG9mQr7zNmW1xj/+kogVE59/" +
                                                    "/fLlqe5tBx8+e5bDXyF8No6N8Y8F+PYRPUIK9Zpm5JbkVbSl9fHcK6XLk6me" +
                                                    "0T24dFua+5LtjJqUX4tjn83/dMO5qbv8lv8byZgBbpUPAAA=");
}
