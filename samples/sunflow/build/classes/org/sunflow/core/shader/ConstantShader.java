package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ConstantShader implements Shader {
    private Color c;
    public ConstantShader() { super();
                              this.c = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { c = pl.getColor(
                                                                       "color",
                                                                       c);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) { return c; }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYX2wcRxmfW/+LHce+OHGShsRJXCciSXtLkIoUXKWkltM4" +
                                                    "XJpTnEZwhbjj3TnfJns7m905++LWtA1CifoQIXBLilo/VKlKS9pUiKggVCkv" +
                                                    "0FblpQiBeKBFvFBR8pAHSkWB8n0z+//OLn3A0s7Nznx/5/u+33zrqzdJh++R" +
                                                    "vS63z83YXBRYQxRO23cVxDmX+YUjxbtK1POZOWZT3z8Ba1PG8Kv9H3783Wpe" +
                                                    "I51lso46DhdUWNzxjzOf27PMLJL+eHXcZjVfkHzxNJ2lel1Ytl60fDFaJKsT" +
                                                    "rIKMFEMTdDBBBxN0aYJ+MKYCpjXMqdfGkIM6wj9LvkVyRdLpGmieIDvSQlzq" +
                                                    "0VogpiQ9AAmr8P0kOCWZGx7ZHvmufG5y+Mm9+uIPTuV/0kb6y6TfcibRHAOM" +
                                                    "EKCkTHprrDbNPP+gaTKzTNY6jJmTzLOobc1Lu8tkwLdmHCrqHosOCRfrLvOk" +
                                                    "zvjkeg30zasbgnuRexWL2Wb41lGx6Qz4uiH2VXl4CNfBwR4LDPMq1GAhS/sZ" +
                                                    "yzEF2ZbliHwc+SoQAGtXjYkqj1S1OxQWyICKnU2dGX1SeJYzA6QdvA5aBNm8" +
                                                    "rFA8a5caZ+gMmxJkU5aupLaAqlseBLIIMpglk5IgSpszUUrE5+b9d1962Dns" +
                                                    "aNJmkxk22r8KmIYyTMdZhXnMMZhi7N1TfIpueP2iRggQD2aIFc1rj9z6yh1D" +
                                                    "N95UNJ9rQXNs+jQzxJRxZbrvnS1ju/e3oRmrXO5bGPyU5zL9S8HOaMOFytsQ" +
                                                    "ScTNQrh54/ivvv7YS+wDjfRMkE6D2/Ua5NFag9dcy2befcxhHhXMnCDdzDHH" +
                                                    "5P4E6YJ50XKYWj1WqfhMTJB2Wy51cvkOR1QBEXhEXTC3nAoP5y4VVTlvuISQ" +
                                                    "LnjInfB0E/UnfwUp61VeYzo1qGM5XIfcZdQzqjozuO7TmmtD1Py6U7H5nO57" +
                                                    "hs69mejd4B7T/So1maeHVTwpXwuYY+7/VXoDfcvP5XJw7FuyRW9DvRzmNtBO" +
                                                    "GYv1e8dvvTL1thYVQXAqguwCfYVAXwH1FZS+QlofyeWkmvWoV0UW4nIGKhyw" +
                                                    "r3f35DePPHRxuA1Syp1rh0NF0mHwMDBm3OBjMQxMSMEG5OKm5x68UPjohXtU" +
                                                    "LurLY3ZLbnLj8tzjJx/9gka0NPiic7DUg+wlhMwIGkeyRddKbv+F9z+89tQC" +
                                                    "j8svheYBKjRzYlUPZ8PgcYOZgJOx+D3b6fWp1xdGNNIOUAHwKCikMyDPUFZH" +
                                                    "qrpHQ6REXzrA4Qr3atTGrRDeekTV43PxisyPPjlfC0FZjel+Gzy9Qf7LX9xd" +
                                                    "5+K4XuUTRjnjhUTiQz+/8fT1H+7dryVBuz9xDU4yoSBgbZwkJzzGYP2Pl0vf" +
                                                    "f/LmhQdlhgDF7a0UjOA4BoAAIYNj/c6bZ//w3rtXfqvFWSXgZqxP25bRABm7" +
                                                    "Yi0AFzZAFsZ+5AGnxk2rYtFpm2Fy/qt/577rf7uUV9G0YSVMhjs+XUC8ftu9" +
                                                    "5LG3T/1jSIrJGXhdxZ7HZOoA1sWSD3oePYd2NB7/zdan36DPApoCgvnWPJOg" +
                                                    "RKRnRB69LkO1R46FzN4+HLa7TXsNubIpqrrdyxfRIbx1E8X3z2P29Pk/fyQ9" +
                                                    "aiqfFpdNhr+sX31m89iBDyR/nMfIva3RDEjQocS8X3yp9ndtuPOXGukqk7wR" +
                                                    "QM1JatcxW8pw5fsh/kCLlNpPX9/qrhqN6nRLtoYSarMVFAMhzJEa5z2ZopE1" +
                                                    "sh6enqBoerJFkyNysl+yDMtxJw6fD3O2y/WsWYq9FSSO3B8UZGMSd60aNA6Y" +
                                                    "hFweX96VRoykQtuKT+K1AmisiK3LtRSyHbpyfnHJPPb8PgW2A+lrehy60Jd/" +
                                                    "9+9fFy7/6a0Wt0S34O6dNptldsKmNlSZAvmjstuKw/zEiz9+Tbyz98tK5Z7l" +
                                                    "UzPL+Mb5v24+caD60GeA9m0Z57MiXzx69a37dhnf00hblC1NDWSaaTSdIz0e" +
                                                    "g47XOZHKlKEoU9aF8JoPMiXfEl7jyMaFrsnz1MIYDzXFWLrKoD9FJAnJNiTJ" +
                                                    "JtXvwdKEVFNaAUpO4nAUsLTumpCWK4NGybNq0MDNBh2mvjDw3pln3n9ZRTSL" +
                                                    "EBlidnHxiU8Klxa1RM9+e1PbnORRfbu0co062E/gLwfPf/BBF3BB9W0DY0Hz" +
                                                    "uD3qHl0X62DHSmZJFYf+cm3hFz9auKAFRzIOVTrNuc2o0wyxcmEiijM+ZAs8" +
                                                    "g0GcB//nOOfStby1ZS3D5wh+kDEphq0QRwuHaUFWzzBxHPgwZXHpnk91QXYC" +
                                                    "B4J0DdP2M6UqvlakMknKVzDzLA5QbGt8gwpI4VKVi6CSv4bDN5R1pwRpn+WW" +
                                                    "2eKKE6Qv3Y3iHbup6StXfZkZryz1r9q49MDvZX8VfT11wydMpW7bScxPzDtd" +
                                                    "j1UsaW+3ugEUBp9rhbiqQ4YCUhNpcEPRPyJIPksPjuFPkuxRiFqCDLIvmCWJ" +
                                                    "zgvSBkQ4/bYb5kxethZ49xXU3dcgCUjG7ir5lmq1sLblfxBChKyr/yFMGdeW" +
                                                    "jtz/8K0vPS/htsOw6fy8/OKED2jVZUYou2NZaaGszsO7P+57tXtnWFp9OAwE" +
                                                    "rWXGtm2tO7DxmitkzzT/s40/vfuFpXdlC/hfueTPftoRAAA=");
}
