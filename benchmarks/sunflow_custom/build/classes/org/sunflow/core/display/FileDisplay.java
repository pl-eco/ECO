package org.sunflow.core.display;
import org.sunflow.core.Display;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
public class FileDisplay implements Display {
    private Bitmap bitmap;
    private String filename;
    public FileDisplay(boolean saveImage) { super();
                                            bitmap = null;
                                            filename = saveImage ? "output.png"
                                                         : null; }
    public FileDisplay(String filename) { super();
                                          bitmap = null;
                                          this.filename = filename == null
                                                            ? "output.png"
                                                            : filename; }
    public void imageBegin(int w, int h, int bucketSize) { if (bitmap == null ||
                                                                 bitmap.
                                                                 getWidth(
                                                                   ) !=
                                                                 w ||
                                                                 bitmap.
                                                                 getHeight(
                                                                   ) !=
                                                                 h) bitmap =
                                                                      new Bitmap(
                                                                        w,
                                                                        h,
                                                                        filename ==
                                                                          null ||
                                                                          filename.
                                                                          endsWith(
                                                                            ".hdr"));
    }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) {  }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        for (int j =
               0,
               index =
                 0;
             j <
               h;
             j++)
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   index++)
                bitmap.
                  setPixel(
                    x +
                      i,
                    bitmap.
                      getHeight(
                        ) -
                      1 -
                      (y +
                         j),
                    data[index]);
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { Color cg =
                                       c;
                                     for (int j =
                                            0;
                                          j <
                                            h;
                                          j++)
                                         for (int i =
                                                0;
                                              i <
                                                w;
                                              i++)
                                             bitmap.
                                               setPixel(
                                                 x +
                                                   i,
                                                 bitmap.
                                                   getHeight(
                                                     ) -
                                                   1 -
                                                   (y +
                                                      j),
                                                 cg);
    }
    public void imageEnd() { if (filename !=
                                   null) bitmap.
                                           save(
                                             filename);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1Yb2wcRxWfO/87O07sOE1sTGInjl2RONy2iBaKS4jj2onD" +
       "NbFiJxJXiDu3O2dvvLe73Z2zzw6mbaQqURFRRZ2SVK2FIKW0pEmFiApClfIF" +
       "2qoUqRUC8YEG+EJFiEQ+UCoKlPdmdm/39s5HIgUs7Xh29r0378385vfe3Plr" +
       "pM51SL9tGfNThsWTrMCTR427knzeZm5yX+quMeq4TBsyqOtOwNik2vNyy/sf" +
       "PjHdGif1abKOmqbFKdct0z3IXMuYZVqKtASjwwbLuZy0po7SWarkuW4oKd3l" +
       "AymyKqTKSW/Kd0EBFxRwQREuKIOBFCitZmY+N4Qa1OTuQ+RrJJYi9baK7nGy" +
       "pdSITR2a88yMiQjAQgLfD0NQQrngkM3F2GXMZQGf7leWvnWk9Yc1pCVNWnRz" +
       "HN1RwQkOk6RJc47lMsxxBzWNaWmy1mRMG2eOTg19QfidJm2uPmVSnndYcZFw" +
       "MG8zR8wZrFyzirE5eZVbTjG8rM4MzX+ryxp0CmLdEMQqIxzBcQiwSQfHnCxV" +
       "ma9SO6ObGifdUY1ijL1fBAFQbcgxPm0Vp6o1KQyQNrl3BjWnlHHu6OYUiNZZ" +
       "eZiFk84VjeJa21SdoVNskpOOqNyY/ARSjWIhUIWT9VExYQl2qTOyS6H9ubb/" +
       "3lPHzL1mXPisMdVA/xOg1BVROsiyzGGmyqRi8/bUU3TDqyfjhIDw+oiwlHnl" +
       "q9d37ei6/LqU+XgFmQOZo0zlk+q5zJq3Nw5tu6cG3UjYlqvj5pdELuA/5n0Z" +
       "KNhw8jYULeLHpP/x8sGff+mRF9nVOGkaJfWqZeRzgKO1qpWzdYM5e5jJHMqZ" +
       "NkoamakNie+jpAH6Kd1kcvRANusyPkpqDTFUb4l3WKIsmMAlaoC+bmYtv29T" +
       "Pi36BZsQ0gAP6fc7/n9OVOWQC3BXqEpN3bQUAC+jjjqtMNWazMDqTueoM+Mq" +
       "at7lVk5x82bWsOYU11EVy5kqvquWwxRNd22Dzisj4NF9sp9EsNn/n2kKGG3r" +
       "XCwGG7ExSgMGnKC9lqExZ1Jdyu8evn5h8s148Vh46wTEBbMlvdmSOFvSmy0Z" +
       "mo3EYmKS23BWudOwTzNw4oELm7eNf2Xfgyd7agBi9lwtLHIcRHsgTs+VYdUa" +
       "CmhhVJCfCtjs+M4DJ5IfPP8FiU1lZQ6vqE0un5l79PDDd8RJvJSMMTQYakL1" +
       "MaTQIlX2Rg9hJbstJ957/+JTi1ZwHEvY3WOJck085T3RTXAslWnAm4H57Zvp" +
       "pclXF3vjpBaoA+iSU4A3MFFXdI6S0z7gMyfGUgcBZy0nRw385NNdE592rLlg" +
       "RKBjjeivhU1ZhfDf4Hf8//h1nY3tbRJNuMuRKAQzj/zk8tlLT/ffEw+TeEso" +
       "LY4zLilhbQCSCYcxGP/dmbEnT1878YBACEhsrTRBL7ZDQBCwZbCsj73+0G+v" +
       "vHvuV/EiqmIcMmU+Y+hqAWzcHswC9GEAheHe9x4yc5amZ3WaMRiC858tfXde" +
       "+supVrmbBoz4YNjx3w0E4x/bTR5588jfu4SZmIrpK4g8EJMLsC6wPOg4dB79" +
       "KDz6zqazr9FngV2B0Vx9gQmSisnIQGlblRLG0XPAqrMe7SuLbVdmnnnvJXls" +
       "ojkiIsxOLj3+UfLUUjyUSLeW5bKwjkymAgyrJXg+gr8YPP/GB0GDA5JM24Y8" +
       "Rt9cpHTbxu3ZUs0tMcXIny4u/vT7iydkGG2leWQYyqSXfv2vXyTP/P6NCqTV" +
       "kLEsg1FTeKkIL7eLNoluiTUl4tsANpvtsm9ioLP8bLR5Z6Ot4tnApjcym7+D" +
       "+L4eCkWx91hrJGWtITR3VXHxPmw+X+6i9LGjSKhVADKCBVaIV/9xwMgc/+MH" +
       "YuHKmLECZiL6aeX8M51DO68K/YCiULu7UJ5poBgNdD/1Yu5v8Z76n8VJQ5q0" +
       "ql6le5gaeSSCNFR3rl/+QjVc8r20UpNlyUCRgjdGQRuaNkqOAVigj9LYb4rw" +
       "YTOucic8CW/PE9E9jxHRSQmVHtH2YfMJn44abEefpVhGk/qMznPU9mHQEc6q" +
       "eg4KxeRuISBwJMX2lLqyEZ5Gz5XGFVyZwGY/JwksgfwgB6XNQoDOjlJMdpSl" +
       "eC+t40HdtFJhKg7pueNLy9qB5+6Me2D9DCeN3LI/abBZZoTmwkvYppKMf78o" +
       "xQNgPP7CD17hb/d/Th737SuDOar42vE/d07snH7wJvJ8dySmqMkX7j//xp7b" +
       "1W/GSU0RX2W3i1KlgVJUNTkMrkPmRAm2ukr55LPwtHsb2n7DfFIj1rNGvN+B" +
       "zaclEdzNSQ3cjLCbFY0wwaoQy1FsMhyOHOJvN5vSzUpWa2ctXVuBIY+URrTL" +
       "g6kP1xuLqE4iJPA80ghNt0ogeWyA9ZtFIGMOgxJBLLtxY24Pw9Ptud19K9wG" +
       "sPetDGCR82X2Xf7e1l8+vLz1D8CkaZLQXeCsQWeqwnUwpPPX81euvrN60wVR" +
       "INZmqCsRFr1Hl1+TS26/Iq7m4jrgEpB98LzlrcNbMn9/+dbcUtx5l7OcMir2" +
       "h5rM8G9B/1P7BZ/g2svZFmoyy7FtWyLoWBV0PYYNMPgqoXjI1oDQbwJcO+Hp" +
       "8xa171adieNC8+tVvP4GNieBjYXXcFUzbsJnzHo7PJ933LDP4emfrPLtNDZP" +
       "QJISrg2b2gqe4ZqH7phYOXeU/ZYlf39RLyy3JNqXD/1GHgr/N5LGFGTCvGGE" +
       "032oX287LKsLnxpl8pdJ8ulKKdG79UJS93rC37NS41ko7KIawJz4Lyz2bYgo" +
       "JAa2vF5Y6LtA5CCE3XPFeiFUNsrCp0BC2RVvTeG3kisU8o/4pdBPdnn5W+Gk" +
       "enF53/5j1+9+TmTOOtWgCwtoJQFUIW+PxYS5ZUVrvq36vds+XPNyY59fCKzB" +
       "ps27MkZ86658sxrO2VzchRZ+3P6je59ffldc7f4DIQSuScIVAAA=");
}
