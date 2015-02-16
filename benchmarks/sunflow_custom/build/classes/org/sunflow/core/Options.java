package org.sunflow.core;
import org.sunflow.SunflowAPI;
import java.util.Map;
public final class Options extends ParameterList implements RenderObject {
    public boolean update(ParameterList pl, SunflowAPI api) { for (Map.Entry<String,Parameter> e
                                                                    :
                                                                    pl.
                                                                      list.
                                                                     entrySet(
                                                                       )) {
                                                                  list.
                                                                    put(
                                                                      e.
                                                                        getKey(
                                                                          ),
                                                                      e.
                                                                        getValue(
                                                                          ));
                                                                  e.
                                                                    getValue(
                                                                      ).
                                                                    check(
                                                                      );
                                                              }
                                                              return true;
    }
    public Options() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698393000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXhu+18K1x8oBaG0paClcAsPmECJWppCiwdtWsBY" +
                                                    "Isfc7ly7dG93mZ1rj2IVSEwJD8RgQTDaBwNBlL8YCRpD0ieB4AvGaHwQfJOo" +
                                                    "PPCCJqj4zeze7d3eFeODl+zs7Mz3/38XHqAim6JWy9T3D+omC5EkC+3V14bY" +
                                                    "fovYoS3htb2Y2kTt0LFtb4eziNJ0Jfjo8dtDlRIqHkA12DBMhplmGnYfsU19" +
                                                    "hKhhFPROO3UStxmqDO/FI1hOME2Xw5rN2sJoVgYqQ83hlAgyiCCDCLIQQW73" +
                                                    "oABpNjES8Q6OgQ1m70NvoEAYFVsKF4+hxmwiFqY47pLpFRoAhVL+vROUEshJ" +
                                                    "ihrSujs65yh8olWefHd35acFKDiAgprRz8VRQAgGTAZQRZzEo4Ta7apK1AFU" +
                                                    "ZRCi9hOqYV0bE3IPoGpbGzQwS1CSNhI/TFiECp6e5SoUrhtNKMykafViGtHV" +
                                                    "1FdRTMeDoGutp6uj4SZ+DgqWayAYjWGFpFAKhzVDZWiJHyOtY/PLAACoJXHC" +
                                                    "hsw0q0IDwwGqdnynY2NQ7mdUMwYBtMhMABeGFs5IlNvawsowHiQRhur8cL3O" +
                                                    "FUCVCUNwFIbm+cEEJfDSQp+XMvzzYNuGYweMLkMSMqtE0bn8pYBU70PqIzFC" +
                                                    "iaEQB7FiRfgkrr1+REIIgOf5gB2Ya68/fGll/fRNB+aZPDA90b1EYRHlTHTO" +
                                                    "nUUdLesKuBillmlr3PlZmovw73Vv2pIWZF5tmiK/DKUup/u+evXgx+RXCZV3" +
                                                    "o2LF1BNxiKMqxYxbmk7oZmIQihlRu1EZMdQOcd+NSmAf1gzinPbEYjZh3ahQ" +
                                                    "F0fFpvgGE8WABDdRCew1I2am9hZmQ2KftBBCs+FBXfCUI+cn3gy9Iu+wIdxl" +
                                                    "rGBDM0wZgpdgqgzJRDEjUbDuUBzTYVtWEjYz47KdMGK6OSrbVJFNOpj+VkxK" +
                                                    "5B5LJHaIB5j1/5FOcq0qRwMBMPgif7rrkCldpq4SGlEmExs7H16K3JbS4e/a" +
                                                    "A9wIHEIuhxDnEHI5oEBAEJ7LOTleBB8MQzZDnato6X9ty54jTQUQPtZoIRiQ" +
                                                    "gzaBPi77TsXs8FK+WxQ2BeKu7sNdE6E/zr3oxJ08c33Oi42mT40e2vnmaglJ" +
                                                    "2YWWqwNH5Ry9l5fHdBls9idYPrrBifuPLp8cN71Uy6rcbgXIxeQZ3OQ3PDUV" +
                                                    "okJN9MivaMBXI9fHmyVUCGUBSiHDELpQZer9PLIyuS1VFbkuRaBwzKRxrPOr" +
                                                    "VCkrZ0PUHPVORETM4Uu1ExzcgT4BRUHd9MX06avvta6TMmtvMKOb9RPmZHKV" +
                                                    "5//tlBA4//FU7zsnHkzsEs4HiKX5GDTztQPyGrwBFnvr5r4f7t09863kBQyD" +
                                                    "BpeI6pqSBBrLPS6Q9TpUHu7W5h1G3FS1mIajOuFx92dw2Zqrvx2rdBylw0nK" +
                                                    "zyv/nYB3vmAjOnh79+/1gkxA4V3H09wDcwxQ41FupxTv53IkD32z+PQN/AEU" +
                                                    "RShEtjZGRG1BQjMkTB8SHmkR6yrf3Wq+NFg5d0lxUud+iY9GsTbz5VlxLvHt" +
                                                    "cwycphlYz8QIiP08hhbn5HQf1E1CnZLOzb14prYjWuaZw5NTas/ZNU6SVmeX" +
                                                    "8k6YVC5+99fXoVM/3cpTT8qYaa3SyQjRsyQDllnFYavoyF6KHD3/yTV2p3W9" +
                                                    "w3LFzHXBj3jj8C8Lt78wtOc/lIQlPuX9JM9vvXBr83LluIQK0tUgZ8jIRmrL" +
                                                    "NAMwpQSmIoMblJ+UC6fXCwGqwBw13L0L4JnltiDx5rc1Fl/nurmb1/8B4f+k" +
                                                    "L7okJzZSEVCfEwHCBARmGx6+KbDaTLB+593e2y3Yb35K/G7ly0ZI4ISlQrsG" +
                                                    "77Y8ZdKmWhya/4g7ncjj1feG379/0fG0f5TxAZMjk0efhI5NShnz3tKckSsT" +
                                                    "x5n5hJSzHYM/gV8Anr/5w1XgB07Pr+5wB4+G9ORhWTw/Gp8mlmCx6efL419+" +
                                                    "ND4huSZZz1BJ1DR1go08eQ2XblvlFaUuZzR3xknl0lSwdP7Uju9Fo0iPfGUw" +
                                                    "d8USup4RYJnBVmxREtOEEGVO/bfEaxf8PfGHAUOF/CUEHHDAdjM0KwMMJHV3" +
                                                    "mUCYoQIA4tuoCNGuJMqqVVZ25cpsCjwgxF+WVLolnD8tEeXy1JZtBx4+f1bk" +
                                                    "bhH82RkbEyMuTOxOq0unbOOM1FK0irtaHs+5UrYs5Y+sJuiTbUn+XtEZt5io" +
                                                    "7mOfz/9sw7mpu6JZ/QP/lGWmSw4AAA==");
}
