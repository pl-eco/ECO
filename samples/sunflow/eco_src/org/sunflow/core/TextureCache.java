package org.sunflow.core;
import java.util.HashMap;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
final public class TextureCache {
    private static java.util.HashMap<String,Texture> textures = new java.util.HashMap<String,Texture>(
      );
    private TextureCache() { super(); }
    public static synchronized Texture getTexture(String filename,
                                                  boolean isLinear) {
        if (textures.
              containsKey(
                filename)) {
            UI.
              printInfo(
                Module.
                  TEX,
                "Using cached copy for file \"%s\" ...",
                filename);
            return textures.
              get(
                filename);
        }
        UI.
          printInfo(
            Module.
              TEX,
            "Using file \"%s\" ...",
            filename);
        Texture t =
          new Texture(
          filename,
          isLinear);
        textures.
          put(
            filename,
            t);
        return t;
    }
    public static synchronized void flush() { UI.printInfo(Module.
                                                             TEX,
                                                           "Flushing texture cache");
                                              textures.clear(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170616086000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAK0YC2wUx3V85w+2D2zMz+HnD6YUbO5wwCTgSGCMCYYDXPsw" +
                                                   "wUAv492588Le7mZ3\n9nwYRElSAYU0KWoahap8WqFCaFJQaUtbpSkRoU1DWy" +
                                                   "WRSiWkkFZUbdQ0lapKKVWrtG9m9u729u5M\n0vak3Z2bee/NvP978+IHqMwy" +
                                                   "0WzJCtK9BrGC3QN92LSI3K1iy4rAVFS6XlbZd26jpvtQSRj5FJmi\nmrBkhW" +
                                                   "RMcUiRQ71rO1MmajV0dW9c1WmQpGhwt9rh0NsQ7sgjuO3UlbrHz5Y2+FBZGN" +
                                                   "VgTdMppoqu\n9agkYVFUG96NkzhkU0UNhRWLdobRRKLZiW5dsyjWqPUYOoD8" +
                                                   "YVRuSIwmRU3h9OYh2DxkYBMnQnz7\nUB/fFihMMQnFikbkrsx2gNmWiwnHdv" +
                                                   "D686GByAS2OAjs8BMA140ZrgW3eawa/vODy/efecGPaoZQ\njaINMGIScEJh" +
                                                   "vyEUSJDEMDGtLlkm8hCarBEiDxBTwaoyxncdQnWWEtcwtU1i9RNLV5MMsM6y" +
                                                   "DWLy\nPdOTYRSQGE+mLVHdzMgophBVTv8ri6k4DmxPz7It2F3H5oHBKgUOZs" +
                                                   "awRNIopXsUDTTe4MXI8Niy\nEQAAtSJB6Iie2apUwzCB6oQuVazFQwPUVLQ4" +
                                                   "gJbpNuxC0cyiRJmsDSztwXESpajeC9cnlgCqkguC\noVA0zQvGKYGWZnq05N" +
                                                   "JP6/QPj5z/2iuruW2XykRS2fmrAGmuB6mfxIhJNIkIxLt28Nne7fZsH0IA\n" +
                                                   "PM0DLGC65l/ZGn7vJw0CZlYBmC3Du4lEo9Lm4w39+x7WkZ8dY4KhWwpTfg7n" +
                                                   "3B36nJXOlAFeOz1D\nkS0G04tX+3+6/eAF8r4PVfWicklX7QTY0WRJTxiKSs" +
                                                   "yHiUZMTInciyqJJnfz9V5UAeMwmLyY3RKL\nWYT2olKVT5Xr/D+IKAYkmIgq" +
                                                   "YaxoMT09NjAd4eOUgRCaCA+qgyeAxI9/KVoSDFm2FlP10ZBlSiHd\njGf+S7" +
                                                   "pJQhHwCrD0biyNkCCzHIOicGhET5AQlrCmaHooroCvSvpimSTZ9xPSS7Ez1o" +
                                                   "2WlLCg53Ve\nFex+va7KxIxK5+68sb9n4xeOCMNgxuxwR9Ec2CbobBNk2wTd" +
                                                   "26CSEk59KttOKAbEugccFEJZYOHA\nrg2PHmn2g0UYo6UgEwbaDHw4Z+iR9O" +
                                                   "6sF/fygCeBKdV/Y8fh4N1zq4QphYoH24LY1W++dOPM3wKL\nfMhXOBIy3iAW" +
                                                   "VzEyfSx8ZiJci9d3CtH/y9FNl2/eeOfTWS+iqCXPufMxmXM2e7Vg6hKRQZpZ" +
                                                   "8mfv\nq/FvQ4PHfagUPB6iHD8/BJC53j1ynLQzHfAYLxVhVB3TzQRW2VI6Sl" +
                                                   "XREVMfzc5w86jl4ylpq62H\np8YxY/5lq9MM9p4uzIlp28MFD6h3nyxf8puX" +
                                                   "q69zsaRjb40ruw0QKjx5ctZYIiYhMP/O831f/soH\nh3dwS3FMhaIKw1SS4L" +
                                                   "spwPlUFgd8WIU4wjTZslVL6LISU/CwSpjJ/atmfvv3/vx0rdCNCjNp1bbd\n" +
                                                   "m0B2/r416OCNz/59LidTIrEckuUjCybYmZKl3GWaeC87R+rxt+ec+Bk+CSEO" +
                                                   "woqljBEeKRBnDXFB\nhrjgF/F30LPWzl7NQLutiO0XyNhRaf+FeLP92M9/yE" +
                                                   "9djd2p362HTdjoFKpnr3lMujO87rseWyMA\nt+zq5p216tV/AsUhoChBprS2" +
                                                   "mBAyUjladKDLKm69em36o2/5kW8dqlJ1LK/D3AFQJVgesUYg2qSM\nVau5cd" +
                                                   "WOTmBvzjLiQpjpCCDl+scCxsLi/r+O5fus60SH286H39hykgugqOcXSHceOm" +
                                                   "OvbD1195f0\nNqeTdUGG3ZTKD6VQI2VxH7yZnFx+6XTChyqGUK3kVHGDWLWZ" +
                                                   "oQ9B0WGlSzuo9HLWcwsIkS07MyFm\nttf9Xdt6nT8bwmHMoNk44PF39qDBdP" +
                                                   "5Kf93+XoL4YBVHaeHvBcI7fWy8kKJyi5d4KQrVokgLFmhs\nlqvAHrCHLeqq" +
                                                   "QTpWP9D2nfU7r/HoXAmlH7Y2Zw8LBTcblYCUFxVXu5emeS1yndyZ9pzIF7n2" +
                                                   "wutj\nB9WL99zC95OtS544yc9SOowtfowqEKbFIClqLF5rc1rCjyYKkf4bfg" +
                                                   "iej9jDRMkneCUwI+t9jq8E\neTVvGMLYZ9AC7sQWwiDNOq80o9KMncbWQfTU" +
                                                   "TsFw0z0kFZVm/uJHN7fdGjS4QdckFSidiBxxyvvc\n6JZN3505JX9BWUal3z" +
                                                   "fvuv3V0ndP8FpOiI2deylERvZd7nh7IOPtPhZuXBw5p+AB+E+/OjH18+YR\n" +
                                                   "neujjNtGvl3ML8Ktm1BUevq9NR8dDO9Z4GPuUMU8CJtQBEKVGSzWBbkJtERg" +
                                                   "tBawwLMmCWyo5rnp\nOCZSl5nN5HCKFhejzRpDb6r3Oi1EzFFirtFtjfPalO" +
                                                   "vGVbZhuFe55U36XyzPYD+KSgbTJljLTZAF\nn6AIPtRx0KXLl7Ubaaj6YgUh" +
                                                   "B4iw1/YM7Y0fg/bSFUYBKNE/5SQDBGGmiiO1dyxtb3dcg3XmQUV2\nYvjat9" +
                                                   "cNX4hpF2Qfr3h4Q9jlMqRKPuOKOH7dsFjr4erxHUotWwyL+cBE1ya9a/df2h" +
                                                   "CY8PVjhzh9\nxywrXW2M878iic3N7jq6Whx8xYoHOpZSFPl/lvkrl3W0Pbi4" +
                                                   "fTlFK8Yt1oPpEL3AaqTYjBPamJli\n51RcUfwANFz5wmUicZIFa3hAOJOyGu" +
                                                   "vR7IR7EZgu7e/pWiuqRva+n71WC5V2FKoDUgUyDQUvUTQM\nGbDcsIdVSDY5" +
                                                   "JgFWMKdYx8u79cOP/DVwCL+2S8TKutwukp35j3uvkQUPffF3BZqfSqobi1WS" +
                                                   "JKpr\nTxbB5uQ0MZv4ZUC2Bjj6wreu0LdaV4otx0lkXsRFK8+MNay8eOy/aF" +
                                                   "0aPELwkp6cnPUZ/4jyOrdb\np6TIu+fIRer0RCA4j21qkZxyojFTTjQwdUTg" +
                                                   "me3YwOzC7UOehv1svI+9uikKWHs1CdoUDUpmOeUp\nkX2O/ItFC1BMvftuz1" +
                                                   "QS4JNJ3h/dOdT849e3nj5cqEbIvcBzY0Wlz7372z3+Z14dFnjewtEDfHzu\n" +
                                                   "2T9cvtM/VZiSuEyal3ef48YRF0pcNDUGM+am8Xbg0K+1Nr14oP82PxHDOwCd" +
                                                   "0rCuqwRr/P9T4zQW\nz7PXIQij4PzusH3PuM5d9/A4rpvrlOzPE7mm0eo86f" +
                                                   "EnNA32Ouo1CDdrZ8dZ+yZ7nWaRRLWtEQ7y\njFN6PQtRKqkrcpbLMx+XS0hH" +
                                                   "AXd8Zc1gfd4dqbjXk8K39u38MPzrf/C2PnP3Vg2ZI2arqrted43L\nDZPEFM" +
                                                   "5CtajeDf65BMbvVRfwwT78hBcF2GVIOy4wsBNn5Ab6PoUcaMbZ8EqhRCzSdW" +
                                                   "7QZZzOK1oa\nbrLFRXRUeuSlHY2pY5EviYIOCo+xMad8qhDXE5nw1VSUWprW" +
                                                   "m+jSxcGXv70ibfa8e53qspwc47tf\nrI6jSQiZha8EehIG5U382A9mfPehc6" +
                                                   "du+/itxH8A5HO/tj0YAAA=");
}
