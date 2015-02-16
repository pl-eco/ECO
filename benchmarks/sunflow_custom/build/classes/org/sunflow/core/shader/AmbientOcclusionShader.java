package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class AmbientOcclusionShader implements Shader {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;
    public AmbientOcclusionShader() { super();
                                      bright = Color.WHITE;
                                      dark = Color.BLACK;
                                      samples = 32;
                                      maxDist = Float.POSITIVE_INFINITY; }
    public AmbientOcclusionShader(Color c, float d) { this();
                                                      bright = c;
                                                      maxDist = d; }
    public boolean update(ParameterList pl, SunflowAPI api) { bright = pl.
                                                                         getColor(
                                                                           "bright",
                                                                           bright);
                                                              dark =
                                                                pl.
                                                                  getColor(
                                                                    "dark",
                                                                    dark);
                                                              samples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    samples);
                                                              maxDist =
                                                                pl.
                                                                  getFloat(
                                                                    "maxdist",
                                                                    maxDist);
                                                              if (maxDist <=
                                                                    0)
                                                                  maxDist =
                                                                    Float.
                                                                      POSITIVE_INFINITY;
                                                              return true;
    }
    public Color getBrightColor(ShadingState state) { return bright;
    }
    public Color getRadiance(ShadingState state) { return state.
                                                     occlusion(
                                                       samples,
                                                       maxDist,
                                                       getBrightColor(
                                                         state),
                                                       dark);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169096380000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfO/8523F8jtM4rnHsxHUi4pRdAkpRcEjruE7i" +
                                                    "cI0tO4mEI+rO7c3dbbz/ujtnX5yatpEqR5EaELhpWrUWoFSlJW0qRFQQqpQv" +
                                                    "0FblSxEC8YEW8YWKkg/5QKkoUN6b2b292zu7CR+wtHOzs2/en3nv/d4bX7lB" +
                                                    "GjyX7HJs43TOsLnCilw5ZexR+GmHecqR1J4J6nosM2JQzzsGazNa/2vJjz75" +
                                                    "Tr49ThqnyUZqWTanXLctb5J5tjHHMimSDFdHDWZ6nLSnTtE5qha4bqgp3eND" +
                                                    "KbKubCsnA6lABRVUUEEFVaigDodUsGk9swrmCO6gFvceJt8isRRpdDRUj5Nt" +
                                                    "lUwc6lLTZzMhLAAOTfh+AowSm4su2VqyXdpcZfBTu9Tlpx9s/0kdSU6TpG5N" +
                                                    "oToaKMFByDRpNZmZZq43nMmwzDTZYDGWmWKuTg19Qeg9TTo8PWdRXnBZ6ZBw" +
                                                    "seAwV8gMT65VQ9vcgsZtt2ReVmdGJnhryBo0B7Z2hrZKCw/iOhjYooNibpZq" +
                                                    "LNhSP6tbGU76ojtKNg58HQhga8JkPG+XRNVbFBZIh/SdQa2cOsVd3coBaYNd" +
                                                    "ACmcdK/KFM/aodoszbEZTrqidBPyE1A1i4PALZxsipIJTuCl7oiXyvxz4+i+" +
                                                    "C2esw1Zc6JxhmoH6N8Gm3simSZZlLrM0Jje2DqYu0s43zsUJAeJNEWJJ8/oj" +
                                                    "N++7u/f6W5LmczVoxtOnmMZntMvptnd7RnburUM1mhzb09H5FZaL8J/wvwwV" +
                                                    "Hci8zhJH/KgEH69P/uobj73MPoyTljHSqNlGwYQ42qDZpqMbzD3ELOZSzjJj" +
                                                    "pJlZmRHxfYwkYJ7SLSZXx7NZj/ExUm+IpUZbvMMRZYEFHlEC5rqVtYO5Q3le" +
                                                    "zIsOISQBD9kDTzORf+KXE1M97kG4q1Sjlm7ZKgQvo66WV5lmz6ThdPMmdWc9" +
                                                    "VSt43DZVr2BlDXte9VxNtd1c6V2zXaZ6eZphrjpspnVm8XFNMwoeWD8llhUM" +
                                                    "O+f/LbCIJ9A+H4uBc3qi0GBAVh22DaCd0ZYLB0ZvvjrzTryUKv7ZcaKAXMWX" +
                                                    "q6BcRcpVasslsZgQdwfKl3EAXpwFPACkbN059c0jD53rr4MAdObrwQVxIO0H" +
                                                    "232lRjV7JASNMQGNGkRu1w9PLikfv3ivjFx1dYSvuZtcvzT/+IlHvxgn8Uqo" +
                                                    "RiNhqQW3TyDAloB0IJqitfgmlz746OrFRTtM1grs9zGkeidiQH/UHa6tsQyg" +
                                                    "ash+cCu9NvPG4kCc1AOwAJhyCsEPONUblVGBBUMBrqItDWBw1nZNauCnAAxb" +
                                                    "eN6158MVESdtYr4BnLIOk2MbPEk/W8Qvft3o4HiHjCv0csQKgdsHf379mWvP" +
                                                    "7tobL4f4ZFnRnGJcAsaGMEiOuYzB+h8vTXzvqRtLJ0WEAMVdtQQM4DgC8AEu" +
                                                    "g2N94q2H//D+e5d/Gy9FVYxDHS2kDV0rAo8doRQAFwMADn0/cNwy7Yye1Wna" +
                                                    "YBic/0pu333tbxfapTcNWAmC4e7PZhCu33mAPPbOg//oFWxiGha30PKQTB7A" +
                                                    "xpDzsOvS06hH8fHfbHnmTfo8YC/gnacvMAFhRFhGxNGrwlWDYlQi33bjsNWp" +
                                                    "+iYWuqt93On7uLOmj3EYiEiL+5mL75s42VwOELoJdRBPCUq+S3au0Yq5ugnV" +
                                                    "Yc4vX+pix/uzz33wikzwaK2LELNzy+c/VS4sx8sagruqanL5HtkUCJPWyyP4" +
                                                    "FP5i8PwHHzQdF2RR6BjxK9PWUmlyHAykbWupJUQc/MvVxV/8aHFJmtFRWQ9H" +
                                                    "od175Xf//rVy6U9v1wBayBSbcqHjPWv4934cvlztX+ngLvFWv/bZH8QerAxc" +
                                                    "/zlupM/++WOhUxU81nBHZP+0euW57pH9H4r9IU7h7r5ideGBfjXc+6WXzb/H" +
                                                    "+xt/GSeJadKu+c3wCWoUEA2moQH0gg4ZGuaK75XNnOxchko43BONhzKxUYQM" +
                                                    "/QBzpMZ5SwQUW/GU74SnxU+YlmjCxIiYpMSWfjFux+HzASYlHFefo9hpk8a0" +
                                                    "q+fyAmG+IhJNOvRQpbjNQaIGvzXETeJwlIPd0Dh8Br+uYBL81uB33OeX8Kjp" +
                                                    "GMwTHPbi8DUZYvdyUgdt+epSuuFZ70tZv4qUk4EUkxbv96F2n2RZDDFHRnOs" +
                                                    "JtKIVkT2HJibW1brqUVeXj67vJIZf2F33E+i+zhp5rbzBYPNMSOSOFsq2pEH" +
                                                    "xC0iDNjzL/34df7urq/KDB9cPcmiG988+9fuY/vzD91GE9IXsSnK8qUHrrx9" +
                                                    "aIf23TipK8V91cWoctNQZbS3uAxuctaxipjvLblzYxDzPb47e/7XItFb5Tph" +
                                                    "KoN7F9a8gKyznGxK/g5PjAkxxhqgKMBGh7QqOBlIsFoxm0jbtsGotUplZCWj" +
                                                    "8SF98Ozwjd5xy0bHKuN1S814hTsn3rqZYHNmDaMexQGwoi3H+AEBF6Kuihy/" +
                                                    "NSvQXYO+FYO3awW+PiKonlhDySUcznKyDpScBOMwyG5NQwFo++FRfA2V2w0u" +
                                                    "oaEQJkifXEPNb+NwnkP11yiHoJvI29zPvUic1M/ZeqZGeYXgrH3jwT6uq+r/" +
                                                    "LvJ/BdqrK8mmzSvHfy96+NJ9vhku1dmCYZTXnbJ5o+OyrC70bpZVSILi07Ug" +
                                                    "UN7GIPTlRCh+UdI/y0l7lB4MxJ9ysufBe2VkCP1yVk70fUB9IMLpD5wgwNtF" +
                                                    "+4r1V5H1t0jKwBQ7+PK3inYe8VL8TyvAtoL8r9aMdnXlyNEzN+95QQBlg2bQ" +
                                                    "hQXk0pQiCXmTKeHjtlW5BbwaD+/8pO215u0B7rfh0OFfXyK69dXu8kdNh4u+" +
                                                    "fOFnm3+678WV98Q1479TeAggbBQAAA==");
}
