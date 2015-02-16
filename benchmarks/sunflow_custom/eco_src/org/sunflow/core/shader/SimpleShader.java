package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class SimpleShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { return new Color(Math.
                                                                      abs(
                                                                        state.
                                                                          getRay().
                                                                          dot(
                                                                            state.
                                                                              getNormal())));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    public SimpleShader() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163966490000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wURRif3vVBH9pSoCBKC+UhULiFRDRSI9Ra4OCAoweI" +
       "RS3T3bm7hb2dZXe2\nPQoB1Cgo8UHURBNFYkhABTFBgyY+ML7lHzFRExNfIV" +
       "ETH4kxUYz+4Tcze3d7e9dCsMnN7s58j/le\nv/mmx39FVY6NrlGdCNthESfS" +
       "nYhj2yFat4EdZz1M9avvV9XGj64yaQhVxFBI1xhqjKmOomGGFV1T\nord2Zm" +
       "3UYVFjR8qgLEKyLLLVWOTJWxlbVCLwtkOnm+8+UtkWQlUx1IhNkzLMdGr2GC" +
       "TjMNQU24oH\nseIy3VBiusM6Y+gKYrqZbmo6DJvM2Y52o3AMVVsql8nQtFhO" +
       "uQLKFQvbOKMI9UpcqAUJ42zCsG4S\nrSuvDjjnFXPCtj2+3lJqEDKGL24Ec8" +
       "QOwOqpeaultSWmWuFjG6/fdfj5MGrsQ426meDCVLCEgb4+\n1JAhmQFiO12a" +
       "RrQ+NNYkREsQW8eGPiy09qFmR0+ZmLk2cXqJQ41BTtjsuBaxhc7cZAw1qNwm" +
       "21UZ\ntfM+SurE0HJfVUkDp8DsloLZ0txlfB4MrNNhY3YSqyTHUrlNNyHibU" +
       "GOvI0zVgEBsNZkCEvTvKpK\nE8MEapaxNLCZUhLM1s0UkFZRF7QwNHlEodzX" +
       "Fla34RTpZ2hSkC4ul4CqVjiCszA0IUgmJEGUJgei\n5ItPR8uf+489/dZSkd" +
       "uVGlENvv86YGoNMPWSJLGJqRLJeMGNPB693b0mhBAQTwgQS5qumac3xH56\n" +
       "u03SXF2GZu3AVqKyfnXNwbbencspCvNtjLGoo/PgF1kuyiHurXRmLajalrxE" +
       "vhjJLZ7p/eD2vS+Q\nn0OoLoqqVWq4GcijsSrNWLpB7OXEJDZmRIuiWmJq3W" +
       "I9imrgPQYpL2fXJpMOYVFUaYipaiq+wUVJ\nEMFdVAvvupmkuXcLs7R4z1oI" +
       "oRr4oQ741SL5J54M3RBRHNdMGnRIcWxVoXYq/61SmyhOGmvEVhJ6\nxjJIQn" +
       "xEeAJZDCWUNM0QBavY1E2qpHQoWZXO18ggf16e2CzfcfNQRQWHwGApG1AFK6" +
       "gBtP3q0fOf\n7OpZ9cB+mSY8tT1bGZoB2iKetgjXFpHaIn5tqKJCKBnPtcpo" +
       "ga+3QdUCvjXMSdy5csv+9jCkiTVU\nCY7ipO1glbeVHpV2F0o7KlBQhfya9N" +
       "zmfZELR5fI/FJGRuCy3PWfnjh7+I+GuSEUKg+P3EQA6Dou\nJs4xNQ97M4IF" +
       "VU7+bw+uPvXF2a9nF0oLnFVS8aWcvGLbg8GwqUo0wMCC+CNXNYZvQxsPhlAl" +
       "wABA\nn9g/oEprUEdR5XbmUJDbUhND9UlqZ7DBl3LQVcfSNh0qzIgsaeLDeJ" +
       "kwPJCBDQoAvXBv9YIv36h/\nX1icw9pG32mWIExW7thCHqy3CYH5r5+MP/bE" +
       "r/s2iyTwsoDBEecOGLqaBZZZBRYoWQNgg8doxgYz\nQzU9qeMBg/Bk+rdx5s" +
       "JXf3m4SXrdgJlc0OZdXEBh/qpb0N6zd/3VKsRUqPzIKJhRIJPWjCtI7rJt\n" +
       "vIPvI3v3Z1Oe+hA/A4gGKOLow0QAAxKWIeHHiHDvHDHOD6wt4EM7yJ43QlaX" +
       "OaD71V0vpNrd7R+/\nLnZdj/0nvT8Mq7HVKYMqdI8DpfOQNxQBFl+dYPGxhY" +
       "dgYrB6V2AnDcKuO7PmjibjzD+gtg/UqnB6\nOmttKPtsUaQ96qqar955t2XL" +
       "uTAKLUN1BsXaMizyH9VC4hEnDZiTtZYsFdtoGhrDR+EXJHY72fNS\ntuhLfE" +
       "wX4ywve/j7bD9VhXifyNDEEsiSKMVtnDLSWSnO+X2bfm+4H793p0Sc5uLzpw" +
       "d6tB93vEuu\nvemh78sAZS2j1nyDDBLDt6cwV1mEdKtFG1Go8weff/E0O9ex" +
       "WKqcOzLIBRnnLj483Lb45IHLwLe2\ngBOCoscOXr0unNY/ColOR0JbSYdUzN" +
       "Tpdwcohf24tskdy2caRDpOzadjPQ9qJ/zqvHSsC6ajACI+\nLA5UUUj4NZSL" +
       "dWtJrIWpBBowXqY5shY/WUI+u+JRoWbVKHW6jg8rAKhcC+4DBKI5yX+VsPUM" +
       "tCSD\nAnnP39/+5kcbnt0nAzlnlPuCn6tf3fPtd9vCj7wzIPmCbVmA+GDrkR" +
       "9One8dL/NP9q7TS9pHP4/s\nX4UxjRavgGmjaRDU73VMO7679xuxI863lKGa" +
       "AUoNgmVSLeRDVJbeoosWsvi4tTj0C+B3pRf6Ky85\n9BXFZT6lbJlDC84vIU" +
       "SIwaOEViTmXQzVpwjrBT6exWURRM9AL87PFO/aIczv/z/m3wy/Zs/85svN\n" +
       "/NHNv5glQg0dxT0uH6Dur3BUzKCa4mnKPFBJWNKqTQxVDlJdK3glc6leyTLU" +
       "4G8g+TE7qeSyKS9I\nauyrnXf8Gfv8b9EK5S8x9XCTSLqG4UMdPwJVWzZJ6s" +
       "KUenkYWuKxp9z5IFtaKHP5Ira6W9LfAxf2\nID3YzR9+svsgkXxkUDHem59o" +
       "P0NhIOKvD1i5CDWJE5TfICPyulR88nHPTC8CE3H/zyG3K/8D0K9u\nOrF5av" +
       "bA+kfFcVClGnh4WFz14OYqW8A8+k8bUVpO1qfo5ZMb33jpxhwAFDWHJcm9UK" +
       "6OEnk4cco3\nZz0Zi4l2avi1ia/cdPTQNyHRHv4HraxXtLYRAAA=");
}
