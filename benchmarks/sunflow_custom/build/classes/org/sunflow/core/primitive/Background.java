package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public class Background implements PrimitiveList {
    public Background() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public void prepareShadingState(ShadingState state) { if (state.getDepth(
                                                                      ) ==
                                                                0) state.
                                                                     setShader(
                                                                       state.
                                                                         getInstance(
                                                                           ).
                                                                         getShader(
                                                                           0));
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (r.
              getMax(
                ) ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0YXWwcR3nu/O84/ouTuCF2YtepFCfcNhIBSiJofHIap5fY" +
                                                    "xEkBl+Y63p2723h3Z7s7Z18cTH8EclSJqKJOSSpqVSVVKbRNhYgKQpXyRFuV" +
                                                    "l1YI1Ie2vFEBechLQSpQvm9293Zv73xO+8BJM7v7zfc73898cy/dIE2uQ/bY" +
                                                    "3DibN7hIsZJInTH2p8RZm7mpo5n9U9RxmZY2qOueBFhWHX616+NPnih0J0nz" +
                                                    "DNlELYsLKnRuuSeYy415pmVIVwgdN5jpCtKdOUPnqVIUuqFkdFccyJANEVJB" +
                                                    "RjKBCgqooIAKilRBORRiAdFGZhXNNFJQS7gPke+TRIY02yqqJ8hQJRObOtT0" +
                                                    "2UxJC4BDK37fB0ZJ4pJDdpZt92yuMvjiHmXlJ6e7f9VAumZIl25NozoqKCFA" +
                                                    "yAzpMJk5yxz3kKYxbYb0WIxp08zRqaEvSr1nSK+r5y0qig4rbxICizZzpMxw" +
                                                    "5zpUtM0pqoI7ZfNyOjO04KspZ9A82LoltNWz8DDCwcB2HRRzclRlAUnjnG5p" +
                                                    "guyIU5RtHLkXEIC0xWSiwMuiGi0KANLr+c6gVl6ZFo5u5QG1iRdBiiDb1mSK" +
                                                    "e21TdY7mWVaQ/jjelLcEWG1yI5BEkM1xNMkJvLQt5qWIf24cP3jhnHXESkqd" +
                                                    "NaYaqH8rEA3GiE6wHHOYpTKPsGM08xTd8vr5JCGAvDmG7OG89r2bd+8dvP6m" +
                                                    "h/OFGjiTs2eYKrLqldnOd7and9/VgGq02tzV0fkVlsvwn/JXDpRsyLwtZY64" +
                                                    "mAoWr5/4/Xce+QX7e5K0T5BmlRtFE+KoR+WmrRvMuYdZzKGCaROkjVlaWq5P" +
                                                    "kBZ4z+gW86CTuZzLxARpNCSomctv2KIcsMAtaoF33crx4N2moiDfSzYhpAUG" +
                                                    "uRNGB/F+8imIoRS4yRSqUku3uAKxy6ijFhSm8qzDbK6MpyeVWdjlgkmdOVdx" +
                                                    "i1bO4AtZtegKbiquoyrcyQdgReUOU2xHN8HueaaMQVjkHV60tBRGnf1/lldC" +
                                                    "+7sXEglwzfZ4YTAgp45wQ2NOVl0pjo3ffCX7drKcKP7OCbILxKV8cSkUlyqL" +
                                                    "S4XiSCIhpfShWM/54Lo5KAJQHjt2Tz9w9MHzww0QdfZCI+w7og6Dyb4u4ypP" +
                                                    "h5ViQtZDFcK1/7n7l1P/euEbXrgqa5f1mtTk+qWFR+97+M4kSVbWZ7QNQO1I" +
                                                    "PoVVtVw9R+J5WYtv1/JHH199aomHGVpR8P3CUU2JiT8c94LDVaZBKQ3Zj+6k" +
                                                    "17KvL40kSSNUE6iggkLEQ3EajMuoKAAHgmKKtjSBwTnumNTApaACtouCwxdC" +
                                                    "iAyPTvneA07ZgBmxGcZGP0XkE1c32Tj3eeGEXo5ZIYv14d9ev3zt6T13JaN1" +
                                                    "vStyUk4z4VWJnjBITjqMAfz9S1NPXryxfL+MEMC4vZaAEZzTUDPAZbCtP3zz" +
                                                    "ofc+/ODKH5NhVAk4PIuzhq6WgMcdoRSoKAZUNfT9yCnL5Jqe0+mswTA4/921" +
                                                    "a9+1f1zo9rxpACQIhr3rMwjht42RR94+/c9BySah4okWWh6ieRuwKeR8yHHo" +
                                                    "WdSj9Oi7A5ffoM9AwYUi5+qLTNYtIi0jcusV6apROadia/tw2mlXrZUkpD+C" +
                                                    "ORKBJOT7ZoitqiyfCrJcKgdKD6x1Zsnz9spjK6va5PP7vFTtrTwHxqHNeflP" +
                                                    "//lD6tJf3qpRYtoEt79osHlmRFRrQZEVJeKYPM7DRHn8xV++Jt7Z8zVP5Oja" +
                                                    "1SFO+MZjf9t28uuFBz9DYdgRMz7O8sVjL711zx3qj5OkoVwTqjqUSqID0W0A" +
                                                    "oQ6DlsrCDUVIu/TVYDk5N6H/boPR7Sdnd83kDB0chklS7meyjqvRVAYNELo6" +
                                                    "QNsSRZv2noemJqSYdJ1AnMDpbsjEoq3BmQ5e3F2nHQ+CzGtPlKXeD+d++tHL" +
                                                    "nkfj/U4MmZ1fefzT1IWVZKQpvL2qL4vSeI2h1HKjt7Gfwi8B47840AQEeI1B" +
                                                    "b9rvTnaW2xPbxjwYqqeWFHH4r1eXfvfzpeWkvyVfFaRllnODUas6QSXgYGUR" +
                                                    "/gqMPt/Pfbfs50RlSg9U+Xm6QDXod7HjZ5LNt+v48bs4nRIgFtoS6rAosSS4" +
                                                    "F6dJT/9vCtI4z3VtXfO6ELjDD+UgpG/NvKhyrM5aHqdZuKTlmTheNMvucWtp" +
                                                    "3QDXi3WVxkGGYQz4Sg981tzDT11OEtWuo76DkylID6hf1n0Me6xa+sM5y+n6" +
                                                    "FmxF4C4YQ74FQ583qvqjUWVCi506RuEWVfqS5HCujmEP41QSpBMM+xZ3DM0z" +
                                                    "KmC8vYqxXIeQG+OlW0ub476RgbG3ZmCD5NgQ6NFXlTYn6FnpugBjuApjAu+o" +
                                                    "rnfCh9m1XGczfoTTD6DK6AFp2de4cnpdg2Xe7IUx6hs8+rkS6ck6axdxegIq" +
                                                    "ADhsjEKBzYe5hEv7a3QbgrSHlwJsdfqr/o/w7tDqK6tdrVtXT/1Ztrnle24b" +
                                                    "XDZzRcOIHIzRQ7IZilHOy6E2r3u15eNpuLqvfU+BDsOu0PyyR/UMFIk4FRQy" +
                                                    "fETRnhVkQwQNirn/FkX6GdQSQMLXK3YQKd2yz8O/GlLevbpEKtoxu+Krou/F" +
                                                    "o1L+4xM0HEXvP5+senX16PFzN7/8vOxemlSDLi4il9YMafFa/nLTMrQmt4BX" +
                                                    "85Hdn3S+2rYrOKk6cer1+/yYbjtqt8Pjpi1kA7v4m62/PvjC6geyH/8f+gHF" +
                                                    "E4oTAAA=");
}
