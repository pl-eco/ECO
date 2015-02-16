package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
final class InstanceList implements PrimitiveList {
    private Instance[] instances;
    InstanceList() { super();
                     instances = (new Instance[0]); }
    InstanceList(Instance[] instances) { super();
                                         this.instances = instances; }
    final public float getPrimitiveBound(int primID, int i) { return instances[primID].
                                                                getBounds().
                                                                getBound(
                                                                  i);
    }
    final public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                             new BoundingBox(
                                                             );
                                                           for (Instance i
                                                                 :
                                                                 instances)
                                                               bounds.
                                                                 include(
                                                                   i.
                                                                     getBounds());
                                                           return bounds;
    }
    final public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        instances[primID].
          intersect(
            r,
            state);
    }
    final public int getNumPrimitives() { return instances.
                                                   length;
    }
    final public int getNumPrimitives(int primID) { return instances[primID].
                                                      getNumPrimitives();
    }
    final public void prepareShadingState(ShadingState state) {
        state.
          getInstance().
          prepareShadingState(
            state);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1165455008000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAK1Ze2wUxxmfu/PbDn6BeQQwNiYUsO8wMU+nSoxtguHArh+Q" +
                                                   "mFBnvDd3Xtjb3ezO\nmbODaGgkoEFtg9pKTdUQUiHxaGio0opESiiI0KahlQ" +
                                                   "hSQxU1tBVVG6lJVdoqpY8/+s3M7u3e3gMa\nY2nndne++Wa+3/dev/IJKjQN" +
                                                   "NFcyg3RCJ2awc6APGyaJdCrYNAfh1Yh0ubC078RmVfMjXxj55QhF\nlWHJDE" +
                                                   "UwxSE5Eurpak8aaJmuKRMxRaNBkqTBXcpKi9+m8MoMhtuPnqvZf7yg3o8Kw6" +
                                                   "gSq6pGMZU1\ntVshcZOiqvAuPI5DCSorobBs0vYwuo+oiXinppoUq9R8Cu1D" +
                                                   "gTAq0iXGk6KGsL15CDYP6djA8RDf\nPtTHtwUOtQahWFZJpCO1HaxsTl8Jx7" +
                                                   "bW9WdSA5MSNrkNxOEnAKkXpKQW0maIqgdOblu199ipAKoc\nRpWyOsCYSSAJ" +
                                                   "hf2GUUWcxEeJYXZEIiQyjKpVQiIDxJCxIk/yXYdRjSnHVEwTBjH7iakp44yw" +
                                                   "xkzo\nxOB72i/DqEJiMhkJiWpGCqOoTJSI/VQYVXAMxK5zxBbibmDvQcAyGQ" +
                                                   "5mRLFE7CUFu2UVNF7vXZGS\nsWkzEMDS4jihY1pqqwIVwwtUI3SpYDUWGqCG" +
                                                   "rMaAtFBLwC4UzcnJlGGtY2k3jpERimZ56frEFFCV\nciDYEopmeMk4J9DSHI" +
                                                   "+WXPpZVvfpoZPfPf8It+2CCJEUdv4yWDTfs6ifRIlBVImIhbcTwW/2PJ6Y\n" +
                                                   "60cIiGd4iAVNx6JzQ+GPflIvaO7PQtM7uotIdETaeqS+/+lHNRRgxyjRNVNm" +
                                                   "yk+TnLtDnzXTntTB\na+tSHNlk0J680P/Tx585Tf7sR2U9qEjSlEQc7Kha0u" +
                                                   "K6rBDjUaISA1MS6UGlRI108vkeVAz3YTB5\n8bY3GjUJ7UEFCn9VpPFngCgK" +
                                                   "LBhEpXAvq1HNvtcxHeP3SR0hVAQXaoWrGIk//kvR8mDITKhRRdsT\nMg0ppB" +
                                                   "mx1LOkGSTUwz1cIsztg8xydIrCoTEtTkJYwqqsaqGYDL4qaS0RMs5+/09+SX" +
                                                   "bGmj0+Hwt6\nXudVwO43akqEGCPSiZvv7u3e/JVDwjCYMVvSUTQPtgla2wTZ" +
                                                   "NkH3Nsjn49yns+2EYgDW3eCgMFmx\nZGDnpicPNQbAIvQ9BYCJH0gbQQ7rDN" +
                                                   "2S1ul4sc13RJr1vR0Hg7dPPCxMKZQ72GZdXX71zJVjf69Y\n6kf+7JGQyQax" +
                                                   "uIyx6WPhMxXhmry+k43/X57b8tr7Vz78nONFFDVlOHfmSuacjV4tGJpEIhDu" +
                                                   "HPbH\nZ1cGtqNtR/yoADweohw/PwSQ+d490py03Q54TJbiMCqPakYcK2zKjl" +
                                                   "JldMzQ9jhvuHlU8ftaUA5T\nEKqCq8wyY/7LZmfobKwT5sS07ZGCB9TbzxYt" +
                                                   "v/5m+WUOix17K13ZbYBQ4cnVjrEMGoTA+w+/3feN\nb31ycAe3FGEqKAmUDz" +
                                                   "iU4LkKRA+mv6YhNa5F5KiMRxVuhf+tXNT644+/ViU0osAbW6HNd2bgvJ+9\n" +
                                                   "Hj1z5Yv/nM/Z+CSWOZzTO2RCiFqHc4dh4Al2juT+a/Ne+Bl+EQIbBBNTniQ8" +
                                                   "PiBLIHaoIId7CR9b\nPHPL2dAIvJtzWHyWPD0i7T0da0w89fM3+KnLsTvhu9" +
                                                   "HfgvV2oXA2LGTozvQ67UZsjgFd24WtT1Qp\nF/4DHIeBowT50ew1IFAk03Rn" +
                                                   "URcWf3DxUt2T7wWQfwMqUzQc2YC52aNSsDdijkGMSeoPP8JNqmpP\niWVkKI" +
                                                   "k4CHNsbbOHuZnGOM0yxmlZjZEND3gg9XGOPCq5Cj2uJGb7p57vqu1fu+NZHh" +
                                                   "5KofbA5lY7\n4PGKj935QNRFueNOitmItHjnub9ePE8Wc7BKZBMKpg4jliUP" +
                                                   "u9bcwqfJluvRo9zFC0axyXev8BYw\nmfVJWtnBEZiWQqyaATQ7H2KcdCZFs3" +
                                                   "OGdF3XIW+Vc1Bal7eual0LKNYAiqz8DcqRYFiTsNLT9fLF\n8mtHEqs2iQB9" +
                                                   "n4ugp2vv2U0VJS8fPuBnB7bgLHXleeu5eBwbW51Ew346KRq6l+lvXWtbc+vq" +
                                                   "lgdX\nU1QqW5MmR609jxduYsMaPtXKhrUCt7Y7WnDS9cSsb0lu+9nA1OyE/J" +
                                                   "HR5pPhd3tf5C6cM2NlKdM8\nfCbPDx29/Ut6g/NxUgdb3ZDMLAHAVJ21a94f" +
                                                   "ry46+1Lcj4qHUZVkdR/bsJJgAXoYjNG0WxLoUNLm\n0wtfUeW1p1LjXG/acm" +
                                                   "3rTVpO6QH3jJr7hSdPVTC0G+AqsQy9xGvoPsRvBvmSJj4uFlnFR1Gxbsjj\n" +
                                                   "mHUkLqvgNA+lbzE93xZ39qWMeMSee9mwwz7gSOYB/RQQkVUMwBXpiVFFluCc" +
                                                   "RQpRY3QMbGqWu3U1\n5Dh41DhP/zcPNL71ztBLB4VH5jG9tFUj0pd++7vdga" +
                                                   "9fHBXrvPblIT4y//gfX7vZP13kWdErLcxo\nV9xrRL/EFVips5zTkG8HTv32" +
                                                   "soZX9vXfsE5Uk171d0Nn/KeJS2TxQ1/9fZZiNQARkz0M68kU/H6r\n8rQUJv" +
                                                   "I2s1TojDSVsBLAnhNVrKwFU10pTCazJJZ5aTXsFh6UHVd67tT3z9H3lq0TEi" +
                                                   "zNrQvvwqXr\njk3Wr3v18GeoXOs9uHpZV4/f/4XAmPwOj8qWZ2a0uemL2tP9" +
                                                   "sQzOkzDUwTSvXCDsmWOexdvYvZTM\nE2z35pnbxwaw/kKJ6UmoFbCvz17Sdc" +
                                                   "d1youwyddn/uihE0dvMPT1ZHo6W+1JZyJ+dl3bMHo6qp6O\ncHDKeA7uYGss" +
                                                   "yUv5G1eRENB0k7Wrru9CFqemXt0U5t6d2nhV2/K21nud2h5c09y6smVFG0XT" +
                                                   "Bjf2\nDARld9T5sju07YPWOVNkdlArtqEabqfTHMdgXuaeBM8q6O/u6BL1fy" +
                                                   "ozDuXJjMkc1jCaniUtx5uf\nEUVTcYHX1czlcn3W4EHj4GO3Kg7gt3f6LdvZ" +
                                                   "DQhQTW9RyDhRXDuW8HuaXjS1CzFtcbP1PJlxmguT\n1b79DoHMB87jaB5LP8" +
                                                   "aGFyiqjhGaEny9llBFpUesaLaL5QYosKmjgu/cbXGSTe41cNVZctdNXW5f\n" +
                                                   "uk5nuXUax3QsuAVTQ062cdZn8sDxQzacBMsGOLZrhhIRUNiM52Yw5vOyGluv" +
                                                   "JR1oTk0FmkG45lnQ\nzJs6NAFOEEjlmQxz78cT3FRsisYsZQX0AqaIduy7Ku" +
                                                   "HHeCsPjJfY8DoUZ7K9NGVbXrMqGNfkiAPd\nG1OBbjES1Rmyf6cGnVuiq3nm" +
                                                   "rrHhCkVVYDRbE/F0UWVHtl9MRTb2kW+RJduie+YxTpD4TR4Bb7Dh\n+p0F/P" +
                                                   "VUBPw8XEssAZfc85CQ+S1xYAwzx3UM+qM8CHzMhptQwukG0bFB3IvZ1GUHhD" +
                                                   "98RhDK2UuW\nD1osEFqygsCGiexhP09KYwUbAVdkKc0mq3OTDYjfjr4evs0/" +
                                                   "8mDxbzbcgtYgoUNmz3Do4lFNUwhW\nHUT+NhVEgnCtsBBZcdeIuM7rK8wzV8" +
                                                   "wGaHxqwbLXY2grYo5x32WFkJLTF7jrdp2iCnddxWrLWRn/\n4RL/lZHCHzz9" +
                                                   "xKfhX/1LfLGx/3NSHkYl0YSiuLtW130R2GlUOHa56GF1Lm8VOLFXHIjB7Ied" +
                                                   "0Fcp\nyGqhgnSRgVKtOzdRHTQ9QMRuZ+o2WFVOKSe68WSa5EzShWldCf8not" +
                                                   "05JMS/EUekx87sWJA8PPg8\nb0egCseTk4xNWRgVi4/Lqe6jISc3m9dVdPbV" +
                                                   "bW/+YK1doPGvkNNdISXN/FrFbG5Nson9+v8A1pu+\ntdAdAAA=");
}
