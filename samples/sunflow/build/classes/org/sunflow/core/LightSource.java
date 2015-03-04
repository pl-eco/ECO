package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public interface LightSource extends RenderObject {
    public int getNumSamples();
    public void getSamples(ShadingState state);
    public void getPhoton(double randX1, double randY1, double randX2, double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power);
    public float getPower();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2xURXR2+y6lL14FSukLklLYG0IQsYQAtYXWhTZdIKFG" +
                                "yvTe2d1L771zmTvbF5YAiYH4QYwWBaP9gijKK0aCxpDwJRD8wRiNH4J/GpUP" +
                                "fvQDFc/M3cfd220xBjeZ2blnzpnzPmfm4kNU4DDUalNjLGZQHiKjPHTQWB/i" +
                                "YzZxQt3h9b2YOURrN7Dj7AbYgNp4teL3x2/EK4OosB/Nw5ZFOeY6tZw+4lBj" +
                                "mGhhVJGBdhjEdDiqDB/Ew1hJcN1QwrrD28JojoeUo+ZwSgQFRFBABEWKoGzN" +
                                "YAHRXGIlzHZBgS3uHEJHUCCMCm1ViMdRQ/YhNmbYTB7TKzWAE4rF915QShKP" +
                                "MlSf1t3VeZrCp1uVyXf2V36Shyr6UYVuRYQ4KgjBgUk/KjOJOUiYs1XTiNaP" +
                                "qixCtAhhOjb0cSl3P6p29JiFeYKRtJEEMGETJnlmLFemCt1YQuWUpdWL6sTQ" +
                                "Ul8FUQPHQNeFGV1dDTsFHBQs1UEwFsUqSZHkD+mWxtFyP0Vax+aXAAFIi0zC" +
                                "4zTNKt/CAEDVru8MbMWUCGe6FQPUApoALhwtmfFQYWsbq0M4RgY4qvHj9bpb" +
                                "gFUiDSFIOFrgR5MngZeW+Lzk8c/DXZtOHbZ2WEEps0ZUQ8hfDER1PqI+EiWM" +
                                "WCpxCctWhd/GC2+cDCIEyAt8yC7O9VcfbVldd/O2i7M0B07P4EGi8gH13GD5" +
                                "vdr2lo15Qoximzq6cH6W5jL8e5M7baM2ZN7C9IliM5TavNn35b6jH5Ffg6i0" +
                                "CxWq1EiYEEdVKjVt3SBsO7EIw5xoXaiEWFq73O9CRbAO6xZxoT3RqEN4F8o3" +
                                "JKiQym8wURSOECYqgrVuRWlqbWMel+tRGyFUBAMFYDyP3N8cMXHUq8SpSRSs" +
                                "Yku3qAKxSzBT4wpRqeJg0zbAa07Cihp0RHGYqlAWS3+rlBHI/1icR2iCqSQk" +
                                "Isv+H84cFXpUjgQCYOJaf4IbkBs7qKERNqBOJrZ1PLo8cDeYDvikBTiqBS6h" +
                                "JJeQ4BLycEGBgDx8vuDm+g4sPwQ5DNWtrCXySveBk415EDT2SL6w26hMqprU" +
                                "BxD6pJLp2/n5zbPX3m3dGPRmeoWndkYId+OmKsN3NyME4D+c6X3r9MMTL0um" +
                                "gNGUi0GzmNshiqA0Qol57fah7x/cP/dNMC1oHodymhg0dJWjYjwItQirnKOS" +
                                "dFHxKhKQ6wUcLZtmqj4IQMLc3BDqLpspf2XtOXd8ckrrOb/WzbLq7JzogJJ/" +
                                "6du/vgqd+fFODjeVcGqvMcgwMTyS5QuWED1Jbh0q3SlLW5fsHCok9usXPr7O" +
                                "77W+4LJcNXMD9BPeOv7Lkt2b4weCKJjdxAR3AJUKyl7RetItZrlPef+RF3Ze" +
                                "vLN9pfpmEOUlK1iOap1N1OY1AzBlBNqLJQwqIKXAtNEf9oyqRIMelOG7qh5f" +
                                "G7gx0RxE+VCGofVwDKUCqnqdn3lW5WxLxaZgVQBGiFJmYkNspVpHKY8zOpKB" +
                                "yHwsl+sqcE+FyIJaGNXJ2iL/xe48W8zz3fyV+EvlXCemBunboFg2iqlJRNbK" +
                                "TCZAHTQg3oQzmvdYJtX0qI4HDSJy8s+KFWuv/Xaq0o0gAyAp76x++gEZ+OJt" +
                                "6Ojd/X/UyWMCqujDmezMoLlJOi9z8lbG8JiQY/TY18vO3sLvQ5uA0uzo40RW" +
                                "W5SsDEKoTVLjDXJu8+1tFtM6jubGCN+VMCNucQReLbNc4ZhuQlcZTrY9ZaL6" +
                                "wdB7P19yI9/fI33I5OTk609CpyaDnotE07Re7qVxLxNS2Lmuw5/ALwDjbzGE" +
                                "JgLgNpPq9mRHq0+3NNsWXm2YTSzJovOnKxNffDhxIpi0TAtHeVCl7Glmk4A1" +
                                "6diTbSwEoz4Ze/X/NfayvRR4WkmMxLEGlydxfSSSR88sfo6IqZujUvBzyskC" +
                                "tEVML7q8OznKH6a69u9UHoOhJFVWno3KRRKhKJdghRqFLiKL0UBmShlnkdc4" +
                                "Jlw74OIDvluX2q+Ztr+XiCvxupwH6CZcJEXuUiYV2T+LXXUx7YOuAXbtjVNO" +
                                "LQHY81QTioEWw9iQNOGGZ2NCr2yHZtmTQGgLxUJuOkJYLqNDVaY4RwaMcjTH" +
                                "c3cRpalm2qvHvamrl6cqihdN7flO9oT0bboErrTRhGF4Wo63/RTajER1KWiJ" +
                                "W+pt+XcYXn7+PICgFX9SyHEX7QjI50HjEFLuyot0DPIbkMTyONQIlHWhsv3X" +
                                "q6ascihfgqnmm3DfggPqlanuXYcfPXdedvICeEOOj8uXAzyE3I6WbuANM56W" +
                                "OqtwR8vj8qslK1LVqFxM1Z6g8Mi2PHfD6TBtLlvE+GeLPt30wdR9eSv7B/44" +
                                "7JCiDwAA");
}
