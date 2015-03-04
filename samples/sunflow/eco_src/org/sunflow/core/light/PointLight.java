package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class PointLight implements LightSource {
    private Point3 lightPoint;
    private Color power;
    public PointLight() { super();
                          lightPoint = new Point3(0, 0, 0);
                          power = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { lightPoint =
                                                                pl.
                                                                  getPoint(
                                                                    "center",
                                                                    lightPoint);
                                                              power = pl.
                                                                        getColor(
                                                                          "power",
                                                                          power);
                                                              return true;
    }
    public int getNumSamples() { return 1; }
    public void getSamples(ShadingState state) { Vector3 d =
                                                   Point3.
                                                   sub(
                                                     lightPoint,
                                                     state.
                                                       getPoint(),
                                                     new Vector3(
                                                       ));
                                                 if (Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getNormal()) >
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getGeoNormal()) >
                                                       0) {
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(),
                                                           lightPoint));
                                                     float scale =
                                                       1.0F /
                                                       (float)
                                                         (4 *
                                                            Math.
                                                              PI *
                                                            lightPoint.
                                                            distanceToSquared(
                                                              state.
                                                                getPoint()));
                                                     dest.
                                                       setRadiance(
                                                         power,
                                                         power);
                                                     dest.
                                                       getDiffuseRadiance().
                                                       mul(
                                                         scale);
                                                     dest.
                                                       getSpecularRadiance().
                                                       mul(
                                                         scale);
                                                     dest.
                                                       traceShadow(
                                                         state);
                                                     state.
                                                       addSample(
                                                         dest);
                                                 } }
    public void getPhoton(double randX1, double randY1, double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { p.set(lightPoint);
                                         float phi = (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          randX1);
                                         float s = (float)
                                                     Math.
                                                     sqrt(
                                                       randY1 *
                                                         (1.0F -
                                                            randY1));
                                         dir.x = (float) Math.
                                                   cos(
                                                     phi) *
                                                   s;
                                         dir.y = (float) Math.
                                                   sin(
                                                     phi) *
                                                   s;
                                         dir.z = (float) (1 -
                                                            2 *
                                                            randY1);
                                         power.set(this.power);
    }
    public float getPower() { return power.getLuminance();
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169179364000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYbWwUx3V8Z/v8Rc42YBww/sJJGiC3zQdVwZHAdU1iOODi" +
                                                   "Mw4xUGe8O3e3sLez\n2Z21zw5KSCMBTdQkpK3USgmhEQrkEyTa0lRpSpSkTY" +
                                                   "MqJZWaSpFCWyG1ldpUqiqlVO2Pvpndvd3b\nuzMITtrZuZn33rzv92Zf+xzV" +
                                                   "WSbqkq0EmzOIlRhOp7BpEWVYw5Y1DktT8vt1jamTW3UaQTVJFFEV\nhuJJ2Z" +
                                                   "IUzLCkKtLo1wcLJlpjUG0uq1GWIAWW2Ketc+ltSa4rI3j/sXPtj52o7Ymgui" +
                                                   "SKY12nDDOV\n6iMayVsMtSb34Rks2UzVpKRqscEkWkR0Oz9MdYthnVkPoUdQ" +
                                                   "NInqDZnTZKgv6R0uweGSgU2cl8Tx\nUkocCxQWm4RhVSfKUPE4wFxbiglsu3" +
                                                   "hj5dBApIFvToA4ggOQurcotSNtmahG9NTEVw4cfzmK4pMo\nruppTkwGSRic" +
                                                   "N4la8iQ/TUxrSFGIMonadEKUNDFVrKnz4tRJ1G6pWR0z2yTWGLGoNsMB2y3b" +
                                                   "IKY4\n01tMohaZy2TaMqNmUUcZlWiK968uo+EsiN3hi+2Iu5mvg4BNKjBmZr" +
                                                   "BMPJTa/aoOFu8JYxRlHNgK\nAIAayxOWo8WjanUMC6jdsaWG9ayUZqaqZwG0" +
                                                   "jtpwCkPLqxLlujawvB9nyRRDnWG4lLMFUI1CERyF\noaVhMEEJrLQ8ZKWAfd" +
                                                   "Z0fHHk1HNvbxK+XasQWeP8NwFSdwhpjGSISXSZOIiX7cR3Rx+wuyIIAfDS\n" +
                                                   "ELADM3TTuZ3Jv/6ix4FZUQFmx/Q+IrMpefvRnrGH76EoytloMKilcuOXSC7C" +
                                                   "IeXuDBYMiNqOIkW+\nmfA2z4/98oGDr5C/RVDTKKqXqWbnwY/aZJo3VI2Y9x" +
                                                   "CdmJgRZRQ1El0ZFvujKAbzJLi8s7ojk7EI\nG0W1mliqp+I/qCgDJLiKGmGu" +
                                                   "6hnqzQ3McmJeMBBCMXhQAp5FyPmJN0N3JSTL1jManZUsU5aomS3+\nl6lJJE" +
                                                   "3N5hhICW6Y5NME9x6DoZSUo3kiYRnrqk6lrArxKtPbFDLD39dAs8B5bZ+tqe" +
                                                   "HJLxzEGvj/\nvVRTiDkln7z04YGRrd864jgId2pXSkg7cFTCPSrBj0qIoxL+" +
                                                   "UaimRpywhB/pGAlUvB+CFdJay63p\nvVsePNIfBe8wZmtBPxy0H+Rx+RiR6b" +
                                                   "Af0aMi+cngVp0v7j6cuHxyo+NWUvXEWxG7+aPXLxz/V8vq\nCIpUzopcPsjL" +
                                                   "TZxMiqfSYrYbCMdRJfr/eGLb2U8ufPYlP6IYGigL9HJMHqj9YUuYVCYKpD6f" +
                                                   "/Ikb\n49H70cTRCKqF6IeMJ/iHZNIdPqMkYAe95MdliSVRc4aaeazxLS9jNb" +
                                                   "GcSWf9FeEirWK+GIzTzD14\nKTxtrkuLN99davCxw3Epbu2QFCK5Xn68/su/" +
                                                   "f6v5faEWLw/HA5UuTZgT1W2+s4ybhMD6Z99Pfed7\nnx/eLTzFdRUG5c+e1l" +
                                                   "S5ACg3+ygQzhqkFG7IgZ16nipqRsXTGuEe97/4Tbf/+O9PtTqm0WDFs+za\n" +
                                                   "KxPw12/8Gjp44Rv/7hZkamReTnwxfDBHmsU+5SHTxHOcj8Jjv135g1/h5yHb" +
                                                   "QYax1HkikgYSkiGh\nR0nofbUYE6G92/nQD7TXVnH9CsV7Sj7wSrbffujXbw" +
                                                   "qum3GwCwiaYRs2Bh3L82EV1+6ycPTei60c\nwN11fvueVu38f4HiJFCUoWha" +
                                                   "O0zIGoUSI7rQdbFP33m348GPoyiyGTVpFCubsfB/1AiOR6wcJJyC\nsXGT8K" +
                                                   "3W2QY+CpGRUMJyVwGFwL8IMHdr9fDfzEu/HzlT02tPJT/c8bxQQNXAr1D5Qn" +
                                                   "Tm39557PJv\n2EVBx49Ajt1XKM+m0C75uF/9ZKat/swL+QiKTaJW2W3oJrBm" +
                                                   "cz+fhP7D8ro8aPpK9kt7CadwDhYz\nTFc4+gPHhmPfz+Iw59B83hIK9xau7W" +
                                                   "54bnDD/YZwuNcgMdkoUAbEeEsxOGOGqc5g3uSBrXkpEEVB\nAC1jaFmwcuSh" +
                                                   "cjo1404ng/DxDj5scoy9rqpTbChltxOeuMtuvAq7o3wYYqjOoLPErMiQmofe" +
                                                   "iucB\naoYY2rIAQ0J/Nwe8s8Yj3lVWJ0VxTFPblAkPsJXV+jPRWx7e9c+WQ/" +
                                                   "i9vU65ay/teUbgXvCXuXfJ\nLXd/+08VSnQjo8ZtGpkhWoAxfuFZWVJmt4nW" +
                                                   "1XfTJ15+9Rz7eM0G58jV1UMsjLh6w/H5ng2nn7yG\n4toTUkKYdNvMivuiOf" +
                                                   "WDiOiuHa8v68pLkQZLfb0J+LFNfbzE43tLC9wgPB2uC3VULHC+mf3sHHHT\n" +
                                                   "kWvw7jKDC1EJNP08/XtgHUGwtPMeSo2KY6YWyP+C+z1QAG0D7qAErNkZvL6a" +
                                                   "ah7a4BlR9i8d6v/5\nBztfOOwYcoFcWYI1JT/6hz/ujz79zrSDF06IIeCj3S" +
                                                   "f+fPbS2BLH/5z70qqyK0sQx7kzCWHiBo+A\nvoVOENDvrel77ZGxi4IjjjcO" +
                                                   "OWaaUo1g3Y/PvVdKGF4VEX92lZqeJ7su1/RdV236oGHsBfZm+QBt\n2qIsYd" +
                                                   "vtfBrnDQ3qJV/NGg4/4NBRL0kKcazrEYcz0OuK03vV4tSUpq6VZZ6czmEFbr" +
                                                   "H8Hk8EmW8u\nIPQhPjwKJQCEriJx7QxVFV/kg9cj8hw8kiuydNUixwTFWJix" +
                                                   "eoVCfyli7Vl/uFIF8/Y7y/YnCO92\n7qxIIFBxBJ9HF9Dpc3x4ClI76DSVo4" +
                                                   "zqfOGIr8Knr0eFK+BZ76pw/TUFwYkF9l7iww8ZauC8F+tv\nQOnQp1AciIAX" +
                                                   "r1YW3mf4907eeHeWfZpyPqfIyU8f3vNF8nf/ETeo4ieP5iRqyNiaFuyNAvN6" +
                                                   "wyQZ\nVQjR7HRKhnidCaVx/xoMwoi3YPO0A32WodYwNIQAfwXBfsJQcwAMMp" +
                                                   "07CwK9CdkCgPj0Z4bnVa2i\n7eYdYsLpEAslWuJ6WVVSBMS3Qq/i2s7Xwil5" +
                                                   "1+u7ewtPjj8jynidrOH5eU6mKYlizr2xWLX7qlLz\naH2EzpyeeOuN9V7iFv" +
                                                   "eKJe5lscwd73B2F7A6dAqVL2sjeYOJ69X8T5f96O6Txy5GxHXx/3hvxOji\n" +
                                                   "FQAA");
}
