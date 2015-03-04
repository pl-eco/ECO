package org.sunflow.core;
import org.sunflow.image.Color;
public interface GIEngine {
    public Color getGlobalRadiance(ShadingState state);
    public boolean init(Scene scene);
    public Color getIrradiance(ShadingState state, Color diffuseReflectance);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Zf3AU1R1/d/kdQn5CgggRQrACelc6xRmIYwnXAKEHpEmk" +
                                "Na0eL7vvckv2dpfdd8mBwqgzHRhnpI5GqxYzUwem2PJrWhlrLZ04zlQQnUrr" +
                                "tGJb8J9Wi9KRzrS2pYV+v2/vbvc2d5cf0Gbmvtl7P74/Pu/76+0dvkRKLJMs" +
                                "M3R1x4Cq8wBL8sA2dUWA7zCYFdgQXtFFTYvJIZVaVi+MRaSW4zV/v/J4rNZP" +
                                "SvtIA9U0nVOu6JrVzSxdHWJymNQ4ox0qi1uc1Ia30SEaTHBFDYYVi7eFyQzX" +
                                "Vk5aw2kVgqBCEFQIChWC7c4q2DSTaYl4CHdQjVvbyW7iC5NSQ0L1OFmYzcSg" +
                                "Jo2n2HQJC4BDOX7fAkaJzUmTLMjYbts8zuCnlgVHvnN/7Y+KSE0fqVG0HlRH" +
                                "AiU4COkjVXEW72em1S7LTO4jdRpjcg8zFaoqO4XefaTeUgY0yhMmy4CEgwmD" +
                                "mUKmg1yVhLaZCYnrZsa8qMJUOf2tJKrSAbC10bHVtnAtjoOBlQooZkapxNJb" +
                                "igcVTebkFu+OjI2tX4EFsLUsznhMz4gq1igMkHr77FSqDQR7uKloA7C0RE+A" +
                                "FE7m5mWKWBtUGqQDLMLJHO+6LnsKVlUIIHALJ7O9ywQnOKW5nlNync+lTXft" +
                                "e0Bbr/mFzjKTVNS/HDY1ezZ1sygzmSYxe2PV0vDTtPHkXj8hsHi2Z7G95uUH" +
                                "L6++vXnslL3m5hxrNvdvYxKPSAf6q8/OCy1ZWYRqlBu6peDhZ1ku3L8rNdOW" +
                                "NCDyGjMccTKQnhzr/sW9D/2AfewnlZ2kVNLVRBz8qE7S44aiMnMd05hJOZM7" +
                                "SQXT5JCY7yRl8BxWNGaPbo5GLcY7SbEqhkp18R0gigILhKgMnhUtqqefDcpj" +
                                "4jlpEELK4EN88FlC7L9KJJxsDcb0OAtSiWqKpgfBdxk1pViQSXrEZIYe7Aht" +
                                "DvYDyrE4NQetoJXQoqo+HJESFtfjQcuUgro5kB4OSrrJgus6O7QB0DKAnmb8" +
                                "H2Qk0c7aYZ8PjmCeNwGoEDvrdVVmZkQaSazpuHw0csafCYgUQpzcBCICKREB" +
                                "FBFIiyA+n+A8C0XZBwvHMggBDqmvaknPfRu27m0pAo8yhosR1KSIuDnpL7DR" +
                                "o5KI7bWvjD174rllK/3uNFDjSqw9jNtOVefI7TUZg/E/PNP15FOX9nxDCIUV" +
                                "i3IJaEUaAheDvAn551untp+7cP7Au/6MokUccm2iX1UkTsppPyQqKnFOKjIZ" +
                                "Z5wh8/OFrUg5Bx4ZGZU3H1xuB1d9dih0QKY/8pv/vBV45oPTOdCv4Lpxh8qG" +
                                "mOqSWYUiwUlS0jokfaPIaJ2iYEgQz4+++MOX+dllq2yRS/PXPe/GNx65OLf3" +
                                "7thWP/Fn1y6UDkOVuLMLK06mstziMd7L8sWNh0+vu1V6wk+KUokrR5LO3tTm" +
                                "hgGEmgyqioaA4kglCG3xerOpS0yG0uPIXbqAnoic3NXqJ8WQfaHicApOC8m8" +
                                "2Ss8K2G2pb0ORZUACFHdjFMVp9IVo5LHTH3YGRFhVi2e6+B48ENa4DMrlVLE" +
                                "f5xtMJDOssNSrL9Z0GYkC8XZ+vGxBcki9KxbHR+H9KdCCsbDaL1Hi+uyElVo" +
                                "v8ow2v5ds3j5iU/21doepMJI+nRun5iBM37TGvLQmfs/axZsfBKWXyfunGV2" +
                                "+DU4nNtNk+5APZIP/2r+s2/Q56E6QEa2lJ1MJFmfMM0nbJ3NyfxxKaUnRmWo" +
                                "uNhzMIHOKrF2haArEb5UsOH3u5Es56RugPF1qt5P1W7YjYeeFtDkFqDEoQCj" +
                                "8bppjOMnBpZljq4BB5vgMy91dPOme3TZ6nsAaBwPgAS1TjBfX8DyMJIQx3qm" +
                                "cDiCJQUaWlOJQ40dSjUBwV31Fwb3f3TETgjejsGzmO0defRaYN+I39VWLRrX" +
                                "2bj32K2V0HGmDeY1+PPB5yp+0AAcsEtrfShV3xdkCrxhoLMvLKSWELH2w2O7" +
                                "Xj20a48/BcjnOCnr13WVUW3CsxVh2Qyf21Jne9uNOVu/s6ANyWrB574CxxhB" +
                                "8nVOZoIDd0Lo2M4r9ha2QmCYG9RGp3u1W7WAuC0AsAWsKnKsgpoHvTtg7TXP" +
                                "rbhSYG7QVhSJlEw7eq3IEahVwNaqkB4cEq+iUdHXDgieok/bhgRqRjmgJQqr" +
                                "VbCi9SSgYrv65seU0bff/FvNw7bnZ0eMuDqltnr3nXuv6AszeOu3RSUs7qeW" +
                                "OKByqA4WruRkQf5rmOBlh8OMCU9utnNyQnzm4NIg1jggigU4vCMrXHKDEJE6" +
                                "45GeE+f23CkCuWZIga6byb2pm2F2dnc6t7as22JOmCLSR8ceO7Xw4pYGcQ1I" +
                                "I+Lu0jZSY1yXtp5aMRgvKXv/tdcbt54tIv61pFLVqbyW4k0Q+nkorMyKQVOa" +
                                "NL60WgRp1XA50FqRPU2yOI/JKZtEOYtID+6/+vafd50/XURKoVhjx0FNuETA" +
                                "LSWQ7/7tZtDaC09fhl3QClTbu6E2ZaAHF6jPjGb6Dk7uyMdbZGNPe4I3WEj+" +
                                "zFyjJzQZ2X7e0/MkDMM9K5ypavrOtBtagUmAl7E9lSNJvQibascFsWF1T0KT" +
                                "2hAKt/f0RHrv7eqIbGnv7mxfE+4QTmrApK8XH+PJAqf3NUWVQ9SU7cA7eK1i" +
                                "0eqm06tE4I0HaZrAzBHA0GEexOqja0xzMqRhaziurW/No6/rZU1EevzdT2du" +
                                "+fTnl0WMeTvm3XZrmEqKJmnyXpVSIfHFsU3frFXHrgCTPmAiQStjbTbhaib2" +
                                "P5SKhdpMLJAkyZmETSRDSJ5E8rQ7J0+uDtjJt0Ce/26Buee98uITVVM7zz+H" +
                                "ZD+SUcjzMcAkpMt2N7cJSbetUC8nRUrqhdgTrkIzkhMfuJnk7n074gYX3erO" +
                                "nzS9dNf3R8+Lqx8y+h6SF5AcRHJokvBN3PjZDob0SAH4jk0SPsGuRaw4jOQo" +
                                "kuNQwdn2BLUvil+dHEjCSiQ/FpuQvHSDLHcb9tMCcz+butGvIHkVyUlwF67b" +
                                "r+ty9ByuiclDMYbkNSSvTwWKqcTQ6QJzZyaJhyNvuxNIp5C8ieQtbOh0rkR3" +
                                "5Aqj4iFdkaeIyy+RvIPk1/8rXM4VmPvd9eDyHpL3kfyekwobl3ZVdJrvTBGG" +
                                "CxkYPriRMLhviN7DUnXNNuNPha+VzY7nd+K7KjNhQL/XkZSYgZlPsPj4emD8" +
                                "I5KLSP4Cag1ThU8HwcsZBP86FQQndwX7EMkLgs8/8ufiT8SCf025XG0Xyz5D" +
                                "8k8kVxx7pg3C1RsEgitYfMUTmO4rnZ7pPnQLXwmSsus33VcpxpKQxNPvlPHG" +
                                "MGfcT1X2zyvS0dGa8qbRe34r3uhlfgKpCJPyaEJVXc2zu5EuNUwWVYSNFXY3" +
                                "Jq7EvmqoFN7XMODU+A9t8M20l9VxMsO1jJOy1JN70SxoTGARPs6GfpJkNZKG" +
                                "t61clPdytTFh/4AXkY6Nbtj0wOU7D4omuAQa1Z07kQvcPcrs95GCKb7gW5iX" +
                                "W5pX6folV6qPVyxOvzQR7Wi9y8vmuNLsof8CLTeZQiwdAAA=");
}
