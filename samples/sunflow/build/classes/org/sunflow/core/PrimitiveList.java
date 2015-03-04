package org.sunflow.core;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface PrimitiveList extends RenderObject {
    public BoundingBox getWorldBounds(Matrix4 o2w);
    public int getNumPrimitives();
    public float getPrimitiveBound(int primID, int i);
    public void intersectPrimitive(Ray r, int primID, IntersectionState state);
    public void prepareShadingState(ShadingState state);
    public PrimitiveList getBakingPrimitives();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2wUVfTu9l2gL95QCrQFUh47AUUCJQZaChSXUtviowbK" +
                                "7czd7bSzM8PM3bIt1geJAUkkPoqC0X5BFOUVI0FjSPgSCP5gjMYPwT+Nygc/" +
                                "+oGK59zZmZ2dvozBTe7dO/eee96ve/YuybMtssI0tIG4ZvAIS/FIr7Y2wgdM" +
                                "Zkd2RNe2UstmSqNGbbsD9rrk6oulv99/vacsTPI7yXSq6wanXDV0u43ZhtbP" +
                                "lCgpzew2aSxhc1IW7aX9VEpyVZOiqs3ro2SK7yontVGXBQlYkIAFSbAgbc5A" +
                                "waVpTE8mGvEG1bm9n7xAQlGSb8rIHieLs5GY1KKJNJpWIQFgKMTvp0AocTll" +
                                "kUWe7I7MowQ+vkIafmdv2Sc5pLSTlKp6O7IjAxMciHSSqQmW6GaWvVlRmNJJ" +
                                "ynXGlHZmqVRTBwXfnaTCVuM65UmLeUrCzaTJLEEzo7mpMspmJWVuWJ54MZVp" +
                                "ivuVF9NoHGSdlZHVkXAr7oOAxSowZsWozNwruX2qrnCyMHjDk7H2CQCAqwUJ" +
                                "xnsMj1SuTmGDVDi206gel9q5pepxAM0zkkCFk3njIkVdm1Tuo3HWxcmcIFyr" +
                                "cwRQRUIReIWTmUEwgQmsNC9gJZ997rZsPHZQ366HBc8KkzXkvxAuVQUutbEY" +
                                "s5guM+fi1OXRt+msK0fChADwzACwA3P5+XubVlZdve7AzB8DZld3L5N5l3yq" +
                                "u+RWZWPd+hxko9A0bBWNnyW5cP/W9El9yoTIm+VhxMOIe3i17ctnX/qI/Rom" +
                                "xc0kXza0ZAL8qFw2EqaqMWsb05lFOVOaSRHTlUZx3kwKYB1Vdebs7orFbMab" +
                                "Sa4mtvIN8Q0qigEKVFEBrFU9Zrhrk/IesU6ZhJACGCQEo4k4vyKcOGmTeowE" +
                                "k6hMdVU3JPBdRi25R2KyIdk0YWpgNTupxzTjgGRbsmRYce9bNiwmtVpqAoTs" +
                                "Z5gIIuhb5v+CNYWylB0IhUDNlcEg1yA+thuawqwueTjZ0HTvfNfNsOf0aS1w" +
                                "UgV0Imk6EaQTyaJDQiGBfgbScywI+u+DSIbDqXXte3bsO1KdA65jHshF7aVE" +
                                "aM1xP+BigC8RxFs/v3ry0rsr1of98V7qy6DtjDveU56h22ExBvs/nGh96/jd" +
                                "w88JogBRMxaBWpwbwZcgQUKieeX6/u/v3D71TdhjNIdDUk12a6rMSSHthoxE" +
                                "Zc5JkZda/IKExHomJwtGKasN3JBZToSguAvGi2KRgU4dGh5Rdp1e7cRaRXZk" +
                                "NEHiP/ftX19FTvx4YwxDFXHDXKWxfqb5OMtHkuBBaWpNsrFTJLhmUT9kCO+j" +
                                "Zz6+zG+t2OCQXD5+GQxevHbol3kdj/fsC5NwdilD6rBVjDdbsQB5hWZhQPgg" +
                                "yjM7z97YtlR+M0xy0nlsjJydfanerwYgajEoMjoqFHeKgWh10PEtQ2YKVKIM" +
                                "3eWL6KWuK0O1YZILyRgKEKeQMCC3VwWJZ+XPetc3kVQeKCFmWAmq4ZFbQIp5" +
                                "j2UcyOyIiCwR63Iwz2yMgiUwZqYzjPjH0+kmzjOcCBbw88VchdNiYdswLqtx" +
                                "qkHPWpqJBMiGGvgbGqN2t54wFDWm0m5NBOyfpUtWX/rtWJnjQRrsuNZZOTmC" +
                                "zP7cBvLSzb1/VAk0IRmrcSY6M2BOkE7PYN5sWXQA+Ui9/PWCk9fo+1AsIEHb" +
                                "6iATOTeUHVBz/AGVgOwc2UmhAKceFYrZKMDWibkeNZdOK/i9CadHOCmJM/60" +
                                "YWlKg5HUFdtFXDkKsTiH0t5gAM9BjGJjlWe3UtxcCKMybbfK/2q38QXYMcFZ" +
                                "FKcmaCpBuJZkwsvINii7boJO1oVzKrs0VHGn772fzzmhH2wVAsDsyPDRB5Fj" +
                                "w2FfP1UzqqXx33F6KsHvNEdzD+AXgvE3DhQGN5yaWtGYLuyLvMpumujWiydi" +
                                "S5DY+tOFoS8+HDocTiunjpMcSNOTGhEHqYaxLG3EZQ/HiOEMwJNiEnj2TGDO" +
                                "Lpye4aQczOnJ5/grnrTg1OaQ6eCYcww6uXhTcLMFxpq0eGsejng5Tn10A2nG" +
                                "6JJHB4TYLkT1KIhmLKK2kyHwLcEEK70TaEhwjJ246l7N+PwYKsrtN1Tl32lo" +
                                "HYwNaQ1teDgaCk3WFLT3UMw0GdEPTiD6izj1c+DJYtCuMP9lPLImlXMubq6E" +
                                "sSUt55aHn60OT3D2Kk6HQAJw7wYKqSOebbyy0RKkOJmW1WdiGZkz6p3qvK3k" +
                                "8yOlhbNHdn8n6rf3/imCR0gsqWm+9sDfKuSDPmOqYLDIKcum+DsGaTVoMfAo" +
                                "/BNsvuaAvcHJFB8YJwXplR9oGFIRAOHyOKQzktX8msFWuCYrc4u3u9soJZ3X" +
                                "e5d8YWRHy8F7j50WXVcevPoHBxFLITxdne7Da7YWj4vNxZW/ve5+ycWiJW7i" +
                                "LMGpwucMPt4Wjt0cNCVMLsr54GezP934wcht0UH/A9z9mnlUEQAA");
}
