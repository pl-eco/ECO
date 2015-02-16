package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class IDShader implements Shader {
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
                                                   return new Color(state.
                                                                      getInstance(
                                                                        ).
                                                                      hashCode(
                                                                        )).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public IDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1160854852000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXju+l9+rj+0FITSQgFL4RZiMEEQLaVA8YBLCySU" +
                                                    "wDHdnbtburez7M61R7EKJAbCAzFaEIz2wUAQ5S9GgsaQ9Ekg+IIxGh8E3yQq" +
                                                    "D7ygCSp+M3O/e9ciD16yc7Mz3///XniAShwbtVnU2B8xKPOTBPPvNZb72X6L" +
                                                    "OP6NgeVBbDtE6zCw42yFs5A694rv0eO3o1VeVNqLarFpUoaZTk2nmzjUGCBa" +
                                                    "APkyp50GiTkMVQX24gGsxJluKAHdYSsDaFIWKkMtgZQICoiggAiKEEFpz0AB" +
                                                    "0hRixmMdHAObzNmH3kCeACq1VC4eQ825RCxs41iSTFBoABTK+ft2UEogJ2zU" +
                                                    "lNZd6pyn8Ik2ZeS93VWfFSFfL/LpZg8XRwUhGDDpRZNjJNZHbKdd04jWi6pN" +
                                                    "QrQeYuvY0IeE3L2oxtEjJmZxm6SNxA/jFrEFz4zlJqtcNzuuMmqn1QvrxNBS" +
                                                    "byVhA0dA1/qMrlLDdfwcFKzUQTA7jFWSQinu102NoTlujLSOLa8BAKCWxQiL" +
                                                    "0jSrYhPDAaqRvjOwGVF6mK2bEQAtoXHgwtDMcYlyW1tY7ccREmKowQ0XlFcA" +
                                                    "VSEMwVEYqnODCUrgpZkuL2X558HmVccPmBtMr5BZI6rB5S8HpEYXUjcJE5uY" +
                                                    "KpGIkxcFTuL660e9CAFwnQtYwlx7/eGrixvHbkqY5wrAbOnbS1QWUs/0Tb0z" +
                                                    "q6N1RREXo9yijs6dn6O5CP9g8mZlwoLMq09T5Jf+1OVY99c7Dn5CfvOiyi5U" +
                                                    "qlIjHoM4qlZpzNINYq8nJrExI1oXqiCm1iHuu1AZ7AO6SeTplnDYIawLFRvi" +
                                                    "qJSKdzBRGEhwE5XBXjfDNLW3MIuKfcJCCJXBgxbCU4nkT/wzFFK2ORDuClax" +
                                                    "qZtUgeAl2FajClFpqA+sG41hu99R1LjDaExx4mbYoIOKY6sKtSPpd5XaRHGi" +
                                                    "WCO20rW2R2z8PNCs/59FgmtZNejxgANmudPfgMzZQA2ADakj8TWdDy+FbnvT" +
                                                    "6ZC0D0NNwMmf5OTnnPySkz/FCXk8gsE0zlF6F3zTD1kO9W9ya8+ujXuOzi2C" +
                                                    "sLIGi8GwHHQu6JcUo1OlHZlS0CUKngrx2PDRziP+P8+9IuNRGb9uF8RGY6cG" +
                                                    "D21/c6kXeXMLMFcLjio5epCXzXR5bHEnXiG6viP3H10+OUwzKZhT0ZOVIR+T" +
                                                    "Z/ZctwNsqhINamWG/KImfDV0fbjFi4qhXECJZBhCGqpPo5tHToavTFVLrksJ" +
                                                    "KBymdgwb/CpV4ipZ1KaDmRMRGVP5UiODhDvQJaAotOu+HDt99f22Fd7smuzL" +
                                                    "6nI9hMkMr874f6tNCJz/dCr47okHR3YK5wPEvEIMWvjaAfkO3gCLvXVz34/3" +
                                                    "7p75zpsJGAaNL95n6GoCaCzIcIFqYEBF4m5t2WbGqKaHddxnEB53f/nmL7v6" +
                                                    "+/Eq6SgDTlJ+Xvx0ApnzGWvQwdu7/2gUZDwq70YZzTNg0gC1Gcrtto33czkS" +
                                                    "h76dffoG/hCKJRQoRx8iouYgoRkSpvcLj7SKdYnrbilfmqy8u4Q4aUi+iZdm" +
                                                    "sbbwZaG0G98+nw3pEfs6hqbn5bRMZW7g2eM1INE8zxweGdW2nF0m07Imt6h3" +
                                                    "wsxy8fu/v/Gf+vlWgUpSwai1xCADxMiSqYizzCkHm0RvziTFsfOfXmN32l6S" +
                                                    "LBeNXwnciDcO/zpz6+ronmcoAnNcyrtJnt904db6Beo7XlSUzv+8cSMXaWW2" +
                                                    "GYCpTWA+MrlB+UmlcHOjEKAazFHLHToDnknJZiT++W2txddpMlv58oIrbrzC" +
                                                    "nt6UjxvzfCxUJTDN8MBMgdVng/XI//Zgl2CzdoLI3MiXdkjNuKVBgwYvtk4w" +
                                                    "W9t6DNr9QHIeUYZr7vV/cP+i9Kh7eHEBk6Mjx574j494sya8eXlDVjaOnPKE" +
                                                    "lFOkYZ/AzwPPP/zhKvAD2eVrOpKjRlN61rAsngfNE4klWKz75fLwVx8PH/Em" +
                                                    "TbKCobI+Sg2CzfyMFQcvp/3MHzQLHl/Sz77/7GdPbi7PLpjLMLzy8Z0IMjsm" +
                                                    "8OMuvmxnaFKEsG7A4yFbsEzoMZhiecmk9lO1E0G7Gp66pHZ1zxrF/LWXLyEB" +
                                                    "SibQIMKXPga+VjGD6A5GKUsmeYAvQSldN0PFA1TXChRThspTwwyv4w15H0py" +
                                                    "uFcvjfrKp49u+0G05/QAXgFTcDhuGFlJnp3wpZZNwrqQtEJ2XUv80UJlWI5W" +
                                                    "kFVyI0Q1JTycVrnhQSX+lw0WB1dmgUFIJnfZQKBxEQDx7X4r5e0q0b74149f" +
                                                    "jvoJlNNlrNyek93OecKLj9BU2YzLz9CQenl04+YDD188K2pwCXy+Dg2Jjxb4" +
                                                    "BpNDSrr0No9LLUWrdEPr46lXKuan8i1nfHHJNqdwl++MWUz05aEvpn++6tzo" +
                                                    "XTFm/Av9PkF+HRAAAA==");
}
