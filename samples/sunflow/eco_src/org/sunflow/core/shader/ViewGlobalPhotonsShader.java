package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewGlobalPhotonsShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   return state.getGlobalRadiance();
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public ViewGlobalPhotonsShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166301454000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wURRif3vVBH9rSQkGUFgoiz1tIRCM1Sq0FDg45e1Ch" +
                                                   "KHW6O3e3MLez7s62\nRyX4ioAaH0RNNFEkhgTfmKBBEx8Q3/IPmKiJia+QqI" +
                                                   "mPxJgoRv/wm5m91961GG1ys7sz32O+12++\n6Qs/oxrXQRfpboTvtIkb6U3E" +
                                                   "seMSo5di190IU0P6ezX18cPrLBZCVTEUMg2OmmO6qxmYY800tOi1\n3VkHLb" +
                                                   "IZ3ZmijEdIlke20+W+vLWx5WUCbzhwrPWOQ9WdIVQTQ83YshjH3GRWHyUZl6" +
                                                   "OW2HY8gjWP\nm1SLmS7vjqHziOVlepnlcmxx9xa0G4VjqNbWhUyOZsdyyjVQ" +
                                                   "rtnYwRlNqtfiUi1IaHMIx6ZFjJ68\nOuBcXMoJ2/b5+supQcgksTgA5sgdgN" +
                                                   "Wz8lYra8tMtcPPDFy26+CzYdQ8iJpNKyGE6WAJB32DqClD\nMsPEcXsMgxiD" +
                                                   "aLJFiJEgjompOSa1DqJW10xZmHsOcfuJy+iIIGx1PZs4UmduMoaadGGT4+mc" +
                                                   "OXkf\nJU1CjdxXTZLiFJjdXjBbmbtKzIOBDSZszElineRYqneYFkS8M8iRt3" +
                                                   "HuOiAA1roM4WmWV1VtYZhA\nrSqWFFspLcEd00oBaQ3zQAtHM8YVKnxtY30H" +
                                                   "TpEhjqYH6eJqCajqpSMEC0dTg2RSEkRpRiBKRfFZ\n1P77vmeeeGulzO1qg+" +
                                                   "hU7L8BmDoCTP0kSRxi6UQxnvUij0S3eBeFEALiqQFiRdNz8bFNsR/e7lQ0\n" +
                                                   "F1ag2TC8neh8SL9uf2f/rasZCottTLKZa4rgl1guyyHur3Rnbaja9rxEsRjJ" +
                                                   "LR7vf3/L7c+RH0Oo\nIYpqdUa9DOTRZJ1lbJMSZzWxiIM5MaKonlhGr1yPoj" +
                                                   "p4j0HKq9kNyaRLeBRVUzlVy+Q3uCgJIoSL\n6uHdtJIs925jnpbvWRshVAc/" +
                                                   "dBn86pH6k0+OrolormclKRvVXEfXmJPKf+vMIZqbxgZxtAGTjK6m\nbBjTeJ" +
                                                   "pxyOqEnI+IXLI52qalWYZoWMeWaTEtZUL16myJQUbE839ryAo7WkerqgQwBg" +
                                                   "ucQm2sYRRo\nh/TDZz7e1bfunn0qeUTC+x7gSCiO+IojQnFEKY6MoxhVVUl9" +
                                                   "U8QGVDghGDugrAEAmxYkblp7876u\nMOSRPVoNnhSkXWCrv6s+nfUWaj8qYV" +
                                                   "KHBJz+9Na9kbOHr1YJqI0P0RW5G0+9ePLgb00LQyhUGT+F\ntYDgDUJMXIBu" +
                                                   "HhfnBiuukvxf7l1/9LOTX84v1B5Hc8sgoZxTlHRXMC4O04kBIFkQf+iC5vAN" +
                                                   "aGB/\nCFUDTgA2yv0D7HQEdZSUdncOJoUtdTHUmGROBlOxlMO2Bp522GhhRi" +
                                                   "ZMiximqNwRgQxsUCLs2btq\nl37+RuN70uIcGDcXHXcJwlVpTy7kwUaHEJj/" +
                                                   "8rH4w4/+vHerTAI/Czicgd4wNfUssMwrsEBNU8AV\nEaO5m6wMM8ykiYcpEc" +
                                                   "n0d/PFy1796YEW5XUKM7mgLT63gML8Bdeg209u+6NDiqnSxZlSMKNApqxp\n" +
                                                   "K0jucRy8U+wje8cnMx//AD8JkAcw45pjRCIHkpYh6ceIdO8COS4JrC0VQxfI" +
                                                   "XjxOVlc4wYf0Xc+l\nurxbPnpd7roRF7cCxWFYj+1uFVSpuw2UXo78oQTRxO" +
                                                   "pUW4ztIgTTgtW7BrtpEHbp8etubKHH/wK1\ng6BWh+PV3eBA2WdLIu1T19R9" +
                                                   "ceKd9ptPh1FoFWqgDBursMx/VA+JR9w0wE/Wvnql3EbL6CQxSr8g\nudsZvp" +
                                                   "eyJV/yY44c5/nZI97nF1NVyfdpHE0rQy+FUsLGmeMdprIR2Lv516Y9+N2bFO" +
                                                   "K0lh5QfdDE\nfb/zHXLJlfd/WwEz6zmzl1AyQmjRnsJCZQnSrZd9RqHO7332" +
                                                   "+WP89KIVSuXC8UEuyLhwxcGxzhVH\n7vsP+NYZcEJQ9OSRC68Pp80PQ7IVUt" +
                                                   "BW1kKVMnUXuwOUwn48xxKOFTNNMh1n5dOxUQS1G34Nfjo2\nBNNRApEYVgSq" +
                                                   "KCT9GsrFuqMs1tJUAh2aKNMcWXsxWUI9e+JRqWbdBHV6vRjWAFB5NlwYCERz" +
                                                   "evFd\nwzEz0LOMSOQ9s6frzQ83PbVXBXLBBBeKYq4h/bavv9kRfvDEsOIL9m" +
                                                   "0B4v0dh747eqZ/iso/1dzO\nKesvi3lUgyuNabZFBcyeSIOkfnfR7Bd2938l" +
                                                   "dyT4VnJUN8wYJVgl1TIxRFXpLT9nIcuPa0tDvxR+\n5/uhP/9fh76qtMxnVi" +
                                                   "xz6NHFLYVIMXiC0MrE3MZRY4rwfuATWVwRQcwMNOviTPHvJdL8of9j/lXw\n" +
                                                   "a/PNb/uvmT+x+eeyRKphE7jHEwPU/XmujjlUk9/vidmErazazFH1CDONglcy" +
                                                   "/9YrWdjZOL2kOHGn\nl11M1WVKj31x642/xz79U3ZF+QtPI9w6kh6lRQBUDE" +
                                                   "a1tkOSprSqUZ2LtnzcVumoUI0uVLx6kbve\nrejvhMt9kB5cIB7FZHdDThWR" +
                                                   "QfH4b8VE+zgKA5F4vcfOBatFHqbithlRV6vSQ1B4Zk4Jrsj/FeRA\n3FP/LR" +
                                                   "jSN7+4dVb2vo0PyZOhRqd4bExeC+GWq7rB/EEwe1xpOVmn0MtHBt546YocFp" +
                                                   "T0iWV5vkyt\nTpAEcPhU7tP6MjaXndXYa9NeufLwga9CslP8B9tqP+3iEQAA");
}
