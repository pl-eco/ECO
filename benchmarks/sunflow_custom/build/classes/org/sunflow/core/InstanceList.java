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
    public final float getPrimitiveBound(int primID, int i) { return instances[primID].
                                                                getBounds(
                                                                  ).
                                                                getBound(
                                                                  i);
    }
    public final BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                             new BoundingBox(
                                                             );
                                                           for (Instance i
                                                                 :
                                                                 instances)
                                                               bounds.
                                                                 include(
                                                                   i.
                                                                     getBounds(
                                                                       ));
                                                           return bounds;
    }
    public final void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        instances[primID].
          intersect(
            r,
            state);
    }
    public final int getNumPrimitives() { return instances.
                                                   length;
    }
    public final int getNumPrimitives(int primID) { return instances[primID].
                                                      getNumPrimitives(
                                                        );
    }
    public final void prepareShadingState(ShadingState state) {
        state.
          getInstance(
            ).
          prepareShadingState(
            state);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ze2wcRxmfOzt+1bEdp3GcEDuJY1d1HrdNRFBDSktinMTB" +
                                                    "SUzsBuGquY735s6b7O1ud+ecS4JpGgklqkTEww0pFEtASmhxk4IILaoq+Q8g" +
                                                    "LUWIVqioQjQFJFoRIpE/KBUFyvfN7Ov21pdCUyzt7NzMfDPf73vPeuYqmefY" +
                                                    "ZI1l6odzuslTrMhTB/SNKX7YYk5q5+DGIWo7LNOnU8cZgbG02vVU81vvfHG8" +
                                                    "JUlqRslCahgmp1wzDWcvc0x9gmUGSXMw2q+zvMNJy+ABOkGVAtd0ZVBz+OZB" +
                                                    "clOIlJPuQY8FBVhQgAVFsKBsCVYB0XxmFPJ9SEEN7txPPkcSg6TGUpE9TlaW" +
                                                    "bmJRm+bdbYYEAtihDn/vA1CCuGiTFT52ibkM8MNrlKmv7m/5QRVpHiXNmjGM" +
                                                    "7KjABIdDRkljnuXHmO1syWRYZpQsMBjLDDNbo7p2RPA9SlodLWdQXrCZLyQc" +
                                                    "LFjMFmcGkmtUEZtdULlp+/CyGtMz3q95WZ3mAGtbgFUi3IbjALBBA8bsLFWZ" +
                                                    "R1J9UDMynCyPUvgYuz8JC4C0Ns/4uOkfVW1QGCCtUnc6NXLKMLc1IwdL55kF" +
                                                    "OIWTpXNuirK2qHqQ5liak/bouiE5BavqhSCQhJNF0WViJ9DS0oiWQvq5uvuO" +
                                                    "U0eNHUZS8Jxhqo781wFRZ4RoL8symxkqk4SNqwdP07bnTiYJgcWLIovlmqc/" +
                                                    "e+3jaztnn5drPhSzZs/YAabytHp2rOmlZX29m6qQjTrLdDRUfglyYf5D7szm" +
                                                    "ogWe1+bviJMpb3J2788+c+wJdiVJGgZIjWrqhTzY0QLVzFuazuztzGA25Swz" +
                                                    "QOqZkekT8wOkFvqDmsHk6J5s1mF8gFTrYqjGFL9BRFnYAkVUC33NyJpe36J8" +
                                                    "XPSLFiGkBh6yHp5aIv/Em5OMMm7mmUJVamiGqYDtMmqr4wpTzbTNLFPp79uj" +
                                                    "jIGUx/PUPugoTsHI6uahtFpwuJlXHFtVTDvnDSuqaTNlQDi0yjA0pNDarP/T" +
                                                    "OUXE23IokQBVLIsGAh18aIepZ5idVqcKW/uvnU+/mPQdw5UUJx1wTMo9JoXH" +
                                                    "pMLHkERC7H4zHieVDCo6CM4Ok429w/fuvO9kVxVYl3WoGuSbhKVdANHloV81" +
                                                    "+4KI4O2bVtu/dc+J1Nvn7pJmqcwdvmOpyeyZQw/ue+C2JEmWxmHEBEMNSD6E" +
                                                    "0dOPkt1R/4vbt/nEm29dOD1pBp5YEtjdAFFOiQ7eFZW+baosAyEz2H71Cnox" +
                                                    "/dxkd5JUQ9SASMkpWDYEoc7oGSWOvtkLmohlHgDOmnae6jjlRboGPm6bh4IR" +
                                                    "YRZNor8AlIKKIS3wNLiuIN44u9DC9mZpRqjlCAoRlLf9ePaRi19bsykZjt/N" +
                                                    "oYw4zLiMBgsCIxmxGYPx350Z+srDV0/cIywEVqyKO6Ab2z6IDaAyEOvnn7//" +
                                                    "1cuvnf110rcqUgTSW4LNIWDoELRQ5d13G3kzo2U1OqYLg/1nc8/6i3851SKV" +
                                                    "qMOIZwNrr79BML5kKzn24v6/d4ptEiomrABwsEziXhjsvMW26WHko/jgyx2P" +
                                                    "XKLfgHgKMczRjjARlogLCJlKCQ31inZdZO42bFZYZXNiYGm5aptc1TbFqhab" +
                                                    "7shpCbEj+nbP3P4n4MhUMv2dVb98YHrV70Eio6ROc6AI2WLnYnJbiOavM5ev" +
                                                    "vDy/47ww+eox6oiw0xAtCspzfkkqFxgafcwrEWIvPHkXc15G9ns/gIg7rEKq" +
                                                    "8kL6B3uAVO0iTpbMGZBjdNc7t+62oYhDMfcfe/Sx4394W1h0WdSMKTci9KPK" +
                                                    "zKNL++68IuiD8IXUy4vl6QfMI6Dd8ET+b8mump8mSe0oaVHdAngf1QsYJEbB" +
                                                    "AByvKoYiuWS+tICT1cpmPzwvi4bO0LHRwBmkPejjamGLMlZis6mYIMJpPiEo" +
                                                    "ukTbg82tMrdxKNULY7oG0WBeVjOoXoQRnRk5Pl5ZE0O2loe6aMIt3JTJ1ssH" +
                                                    "H33zSZn9omKPLGYnpx56N3VqKhkqhVeVVaNhGlkOC1zzpdO8C38JeP6NDzoL" +
                                                    "Dkinae1za7IVflFmWRhuV1ZiSxyx7Y0Lk89+d/KEhNFaWgn2w0XnyVf+9YvU" +
                                                    "mddfiCk6qsDj8cftVtE36qRbQbhuIIMqah2qZdNgGJ+9OVmNaGbKv6nAZDHG" +
                                                    "PTpKapFdIqgEZvnQ4997mr+05qMSweq5FRglvHT8z0tH7hy/77+oQJZH5Bnd" +
                                                    "8vFdMy9sv0X9cpJU+dZdduUpJdpcatMNNoM7mjFSYtmd0rKFrOPNOoHd7cUK" +
                                                    "uWiswlwGm/3gECrqSKoU5L48Ptf25y0usuORZxb/8I5z06+JHF+UW22scIyG" +
                                                    "zYbydCjZbnf1jf27/DzRSNxkUefmibpobnT9XZ9DMJzUWrY2QfGiTOq1cBC+" +
                                                    "XaRV13jjRbqjnDew3M6y4O47lqga0GbnuisKrzt7fGo6s+ex9UlXMp8C1rhp" +
                                                    "rdPZBNNDJ9aJ/qgvjcUI/lZ4Wl1ptMYXgfGhT9hICFVv1Gvx5y7RiH2OVdDl" +
                                                    "cWyOcrIgx7gPfqtZMGS634nNbnnMEEZa3aR8jkIogNeBg2vgaXPhtd0YeIlS" +
                                                    "9bWH1ZeHC2dqF+W2Vvyw2P4LFVB/CZuTnDQB6k+btp6RiL2Nl5VtLOY1I7fV" +
                                                    "LF4XPT5kxBOD937/6KvEgio/7JYZ7156WCjdW9EVU7tAaefIAICfnphg5esV" +
                                                    "JPVNbE5DatI8Ut9K4gykesLUMteV0CIc7PGqR+/9/iUUZnymwtx5bM5x0gLq" +
                                                    "313IlyLa9d7Z73HZ77mh5h047o8qYHgGm+//zxiEkX6MyNqdeO8b7qLl3zOG" +
                                                    "xyk6UmB9sxVA/gSbZ6H8sKCkpzYLE+PUt6+LcyEOLoFnnYtzXSxObNLxwbRC" +
                                                    "ssCagoFbYLLwlrWFlw3L95ahAXHMzytA/RU2l6CGLVgZQBfnXLVjpqkzalwX" +
                                                    "NOIla+HZ4ILe8J5Bh1l6tcLcb7F5BTQD5reVQiWcK7XAQkx5wElj+GsWlibt" +
                                                    "ZR/M5Ude9fx0c93i6bt/Iy+r3ofY+kFSly3oevjyEOrXgJlkNcFfvbxKWOL1" +
                                                    "OrhJVHsQq/AluLwsl/2Rk5tCy0Dgbi+86E9QL8Mi7L5heUpvCcpjeSkqklD2" +
                                                    "J27O8H6VfHrB8lb8c8ErRQvy3wtp9cL0zt1Hr33kMVHXQklHjxzBXergQi6/" +
                                                    "Ovnl7Mo5d/P2qtnR+07TU/U9XqEi7litIR9vD+k29x8OsAQNyhkAAA==");
}
