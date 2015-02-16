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
    long jlc$SourceLastModified$jl7 = 1170612854000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2wUVfTu9l2gL95QCpSiKY+diCKBEgMtBYpLqS34qMHl" +
                                "dubudujszHDnblmK+CAxIInER1Ew2i+IoqDGSNQYEr4Egj8Yo/FD8U+j8sGP" +
                                "fuDrnDs7s7PTlzG4yb07c+95v+fcTVLicLLctowDKcMSMZYVsb3G6pg4YDMn" +
                                "ti2+uotyh2ltBnWcnXCWUBs/qP7t9ov9NVFS2kumU9O0BBW6ZTrdzLGMQabF" +
                                "SXX+tN1gaUeQmvheOkiVjNANJa47oiVOpgRQBWmKeyIoIIICIihSBGVjHgqQ" +
                                "pjEzk25DDGoKZx95ikTipNRWUTxBFhcSsSmn6RyZLqkBUCjH94dBKYmc5WSR" +
                                "r7ur8yiFTyxXhl97oubDIlLdS6p1swfFUUEIAUx6ydQ0S/cx7mzUNKb1klqT" +
                                "Ma2HcZ0a+pCUu5fUOXrKpCLDmW8kPMzYjEueectNVVE3nlGFxX31kjozNO+t" +
                                "JGnQFOg6K6+rq+FmPAcFK3UQjCepyjyU4gHd1ARZGMbwdWx6EAAAtSzNRL/l" +
                                "syo2KRyQOtd3BjVTSo/gupkC0BIrA1wEmTcuUbS1TdUBmmIJQeaE4brcK4Cq" +
                                "kIZAFEFmhsEkJfDSvJCXAv652bn++EFzqxmVMmtMNVD+ckBqCCF1syTjzFSZ" +
                                "izh1WfxVOuvi0SghADwzBOzCfPzkrQ0rGi5dcWHmjwGzo28vU0VCPd1Xdb2+" +
                                "rXltEYpRbluOjs4v0FyGf1fupiVrQ+bN8iniZcy7vNT9+WPPvMN+iZLKDlKq" +
                                "WkYmDXFUq1ppWzcY38JMxqlgWgepYKbWJu87SBk8x3WTuac7kkmHiQ5SbMij" +
                                "Uku+g4mSQAJNVAbPupm0vGebin75nLUJIWWwSARWO3F/FbgJslvZ5UC4K1Sl" +
                                "pm5aCgQvo1ztV5hqJfrAuv1pygccRc04wkorTsZMGtZ+xeGqYvGU/65anCld" +
                                "XE+DvoMMa0IMw8z+vxlkUcOa/ZEIGL8+nPoGZM1Wy9AYT6jDmdb2W+8lrkX9" +
                                "VMjZRpAG4BPL8Ykhn1gBHxKJSPIzkJ/rV/DKAOQ3XE5t7tm9bc/RxiIIKHt/" +
                                "Mdo0KxNujvcCiCG5ZGpv/vTSqQuvL18bDVaB6kBd7WHCjanaPN+dnDE4/+5k" +
                                "1ysnbh55XDIFiCVjMWjCvQ0iDMomlJ/nruz79sb3p7+K+oIWCSi1mT5DVwUp" +
                                "p31Qp6gqBKnwC05QkYh8ninIglHG6obgZNzNG1R3wXi5LevS6cPDI9qOM/e4" +
                                "GVhXmC/t0A7Of/3nF7GTP1wdw1EVwrJXGmyQGQHJSpElxFKOW7tqbZdlr0N2" +
                                "FRWS/tjZdz8W15evc1kuG785hhEvH/553s4H+vdESbSwwSF3OKpEzC5sS377" +
                                "WRhSPkzy7PZzV7fcpb4cJUW56jZGJS9EagmaAZhyBq3HRIPiSSUwbQwHPrdU" +
                                "pkF/yvNdtoheSFw81BQlxVCioS0JCmUEKn5DmHlBVW3xYhNZlYARkhZPUwOv" +
                                "vLZSKfq5tT9/IjOySj7XgntmYxYshTUzV3fkP95Ot3Gf4WawhJ8v9wbcFkvf" +
                                "RvGxEbclGFl35TMBaqQB8YbOaNplpi1NT+q0z5AJ+0f10nsu/Hq8xo0gA048" +
                                "76yYnED+fG4reebaE783SDIRFXt0PjvzYG6STs9T3sg5PYByZJ/9csGpy/RN" +
                                "aCFQth19iMlKHClMqDnBhEpDzY5tp9CWs/dJw6yXYGvk3oKWy5UVfN+A272C" +
                                "VKWYeMTihtZqZUzN8QjXjyIs76Hht1ogc5iiPFjp+60aDxfCqs/5rf6/+m18" +
                                "BbZNcBfHrR1GTVCuM5P2K7IDxm6eYL714Nx+rxyquzHwxk/n3dQPDxAhYHZ0" +
                                "+NjfsePD0cCUtWTUoBPEcSctKe8013J/wy8C6y9cqAweuJ22ri3X7hf5/d62" +
                                "MawXTySWZLH5x/cPffb2oSPRnHGaBSmCMj2pE3GRRlh355x4951xYjQP8JDc" +
                                "JJ3dE7gzgdujgtSCO3393HjFm07cul02OwXWHItOrt4UPOyEtSqn3qo7o16R" +
                                "2x+9RJoxuuXRA1JtD6JxFEQHNlHHrRD4hcGkKHsnsJCUGOdz3UPNx/wYJioe" +
                                "tHTt31loDax1OQutuzMWikw2FPT0U6w0edUPTqD607gNCpCJMxhXWBAZr/ik" +
                                "es7FwxWwNuX03HTnq9WRCe6ex+0waADh3UqhdKQKnVczWoOsINMK5kxsI3NG" +
                                "fb26X1zqeyPV5bNHdn0j+7f/VVQBnybJjGEExoPgqFAK9kzqUsAKty3b8u84" +
                                "lNWwxyCi8E+K+YIL9pIgUwJggpTlnoJAw1CKAAgfT0A5IwXDrx0ehZcUVG75" +
                                "Re8NShn3mz6hvj+yrfPgrfvPyKmrRDXo0BBSKYcPWnf68IetxeNS82iVbm2+" +
                                "XfVBxVKvcFbhVhcIhoBsC8ceDtrTtpDtfOiT2R+tf2vkezlB/wNwP9jjahEA" +
                                "AA==");
}
