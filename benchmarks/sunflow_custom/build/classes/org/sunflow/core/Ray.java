package org.sunflow.core;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public final class Ray {
    public float ox;
    public float oy;
    public float oz;
    public float dx;
    public float dy;
    public float dz;
    private float tMin;
    private float tMax;
    private static final float EPSILON = 0;
    private Ray() { super(); }
    public Ray(float ox, float oy, float oz, float dx, float dy, float dz) {
        super(
          );
        this.
          ox =
          ox;
        this.
          oy =
          oy;
        this.
          oz =
          oz;
        this.
          dx =
          dx;
        this.
          dy =
          dy;
        this.
          dz =
          dz;
        float in =
          1.0F /
          (float)
            Math.
            sqrt(
              dx *
                dx +
                dy *
                dy +
                dz *
                dz);
        this.
          dx *=
          in;
        this.
          dy *=
          in;
        this.
          dz *=
          in;
        tMin =
          EPSILON;
        tMax =
          Float.
            POSITIVE_INFINITY;
    }
    public Ray(Point3 o, Vector3 d) { super();
                                      ox = o.x;
                                      oy = o.y;
                                      oz = o.z;
                                      dx = d.x;
                                      dy = d.y;
                                      dz = d.z;
                                      float in = 1.0F / (float) Math.sqrt(
                                                                       dx *
                                                                         dx +
                                                                         dy *
                                                                         dy +
                                                                         dz *
                                                                         dz);
                                      dx *= in;
                                      dy *= in;
                                      dz *= in;
                                      tMin = EPSILON;
                                      tMax = Float.POSITIVE_INFINITY; }
    public Ray(Point3 a, Point3 b) { super();
                                     ox = a.x;
                                     oy = a.y;
                                     oz = a.z;
                                     dx = b.x - ox;
                                     dy = b.y - oy;
                                     dz = b.z - oz;
                                     tMin = EPSILON;
                                     float n = (float) Math.sqrt(dx * dx +
                                                                   dy *
                                                                   dy +
                                                                   dz *
                                                                   dz);
                                     float in = 1.0F / n;
                                     dx *= in;
                                     dy *= in;
                                     dz *= in;
                                     tMax = n - EPSILON; }
    public Ray transform(Matrix4 m) { if (m == null) return this;
                                      Ray r = new Ray();
                                      r.ox = m.transformPX(ox, oy, oz);
                                      r.oy = m.transformPY(ox, oy, oz);
                                      r.oz = m.transformPZ(ox, oy, oz);
                                      r.dx = m.transformVX(dx, dy, dz);
                                      r.dy = m.transformVY(dx, dy, dz);
                                      r.dz = m.transformVZ(dx, dy, dz);
                                      r.tMin = tMin;
                                      r.tMax = tMax;
                                      return r; }
    public void normalize() { float in = 1.0F / (float) Math.sqrt(dx * dx +
                                                                    dy *
                                                                    dy +
                                                                    dz *
                                                                    dz);
                              dx *= in;
                              dy *= in;
                              dz *= in; }
    public final float getMin() { return tMin; }
    public final float getMax() { return tMax; }
    public final Vector3 getDirection() { return new Vector3(dx, dy, dz);
    }
    public final boolean isInside(float t) { return tMin < t && t < tMax;
    }
    public final Point3 getPoint(Point3 dest) { dest.x = ox + tMax * dx;
                                                dest.y = oy + tMax * dy;
                                                dest.z = oz + tMax * dz;
                                                return dest; }
    public final float dot(Vector3 v) { return dx * v.x + dy * v.y + dz *
                                          v.
                                            z; }
    public final float dot(float vx, float vy, float vz) { return dx * vx +
                                                             dy *
                                                             vy +
                                                             dz *
                                                             vz; }
    public final void setMax(float t) { tMax = t; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZa2wU1xW+u34bY6/Ny3F4GGOiGtKdUkpbcEpDXAMma+zY" +
                                                    "QFWjxhnP3l0Pnp0ZZu7aa1KagESN0opWjXm1idVWTvMiAUVF6SspP9o8FP7Q" +
                                                    "VlRVlQRFqhIlRRU/QtImaXrOnefOrtdrybF0z8zex7nfued5x+eukzLTIOt1" +
                                                    "TRlPKhqL0gyLHlA2Rdm4Ts3ortimXtEwabxDEU1zD/QNSi0X6m5+9KPhSJiU" +
                                                    "D5BFoqpqTGSyppp91NSUURqPkTqvt1OhKZORSOyAOCoKaSYrQkw2WXuMLPAt" +
                                                    "ZaQ15kAQAIIAEAQOQdjmzYJFC6maTnXgClFl5kHyXRKKkXJdQniMrM5moouG" +
                                                    "mLLZ9HIJgEMl/t4HQvHFGYM0u7JbMucIfHK9MHn63shzJaRugNTJaj/CkQAE" +
                                                    "g00GSE2KpoaoYW6Lx2l8gNSrlMb7qSGLinyI4x4gDaacVEWWNqh7SNiZ1qnB" +
                                                    "9/ROrkZC2Yy0xDTDFS8hUyXu/CpLKGISZF3qyWpJuB37QcBqGYAZCVGizpLS" +
                                                    "EVmNM7IquMKVsfVumABLK1KUDWvuVqWqCB2kwdKdIqpJoZ8ZspqEqWVaGnZh" +
                                                    "pGlGpnjWuiiNiEk6yEhjcF6vNQSzqvhB4BJGlgSncU6gpaaAlnz6ub77jhP3" +
                                                    "qzvVMMccp5KC+Cth0crAoj6aoAZVJWotrFkXOyUufeF4mBCYvCQw2Zrz/Hdu" +
                                                    "3Hn7ykuvWHNuzTOnZ+gAldigND1Ue2V5R9vmEoRRqWumjMrPkpybf6890p7R" +
                                                    "wfOWuhxxMOoMXup76VsPPkXfC5PqLlIuaUo6BXZUL2kpXVaosYOq1BAZjXeR" +
                                                    "KqrGO/h4F6mA95isUqu3J5EwKesipQrvKtf4bziiBLDAI6qAd1lNaM67LrJh" +
                                                    "/p7RCSELoZEGaDXE+uNPRvYLw1qKCqIkqrKqCWC7VDSkYYFK2qBBdU3o7OgR" +
                                                    "huCUh1OiMWIKZlpNKNrYoJQ2mZYSTEMSNCPpdAuSZlChTxyPopHpny37DEoX" +
                                                    "GQuF4OCXB91eAY/ZqSlxagxKk+m7Om88O/ha2HUD+1wYWQzcozb3KHKPAncS" +
                                                    "CnGmi3EXS5OghxHwaIh1NW3939513/GWEjAhfawUDrEUpraAQPbWnZLW4bl9" +
                                                    "Fw9uEthe4y/2T0Q/fPzrlu0JM8fovKvJpTNjR/Y98IUwCWcHWxQFuqpxeS+G" +
                                                    "SDcUtgadLB/fuol3bp4/dVjz3C0rettRIHclenFL8NANTaJxiIse+3XN4sXB" +
                                                    "Fw63hkkphAYIh0wE84VIszK4R5Y3tzuREWUpA4ETmpESFRxywlk1Gza0Ma+H" +
                                                    "W0Mtf693zLsO2iLb3vkTRxfpSBdb1oNaDkjBI+/231w6e/En6zeH/UG6zpf2" +
                                                    "+imzXL7eM5I9BqXQ//qZ3odPXp/Yzy0EZqzJt0Er0g4IAKAyONZjrxz8+5tv" +
                                                    "TP817FpViJEK3ZBHIS5kgMlt3jYQHxSIUaj81r1qSovLCVkcUiha58d1azdc" +
                                                    "/NeJiKVOBXoca7h9dgZe/y13kQdfu/eDlZxNSML85InuTbNOYJHHeZthiOOI" +
                                                    "I3PkzyvOviw+CuETQpYpH6I8ChEuGuFnL3BdreM0GhjbgKRZzxnjHU2ukhdg" +
                                                    "Zy20NbaS1+RXMtIWTtci+ZxzwOV6ekiRpUwASTkfLwfR2gpUUoacguA+amcf" +
                                                    "4XDDmyOPvPOM5d3BVBWYTI9PPvRp9MRk2JfP1+SkVP8aK6dzcRZa4n8KfyFo" +
                                                    "/8OGYmOHFdMbOuzE0uxmFl1HI1pdCBbfYvvb5w//7onDE5YYDdnprBOqtWeu" +
                                                    "fnI5eubaq3liKbiJJnJ7+1o+wsFvLqD0TiQbi1f6FlvpW/IqHcmmwG5hzjHM" +
                                                    "fy9hZJk/+KcgXULChnJrozPemDO+j2IM3Mh32FVAlF4kO4oX5R5blHvmKgr+" +
                                                    "vJsTPnVvAUzfRNKXi8kC1ch/VRW2+e1Yuvoy2n97lKGjb33IbSEnJ+Vxg8D6" +
                                                    "AeHcI00dW9/j673kgKtXZXJzOpT53tovPpV6P9xS/qcwqRggEcm+Q+wTlTSG" +
                                                    "4AGom03nYgH3jKzx7BrYKvja3eS3POiHvm2Dacmzf3jH2fheHchEXMnLnRLM" +
                                                    "efqVHCL8ZZDrmZGwlnG9JmLpa2Du3IZcbuPzwI263A4Vwa3W5lY7A7dhh1u8" +
                                                    "GEln4zbicitG0tm4qS63ApLy6mIZsSoM4jzzcDuIpJWBdXTLahH8Ija/yAz8" +
                                                    "mMdPLHB2t+Dy9dDqbX71OfxCbu2CbhDtgmtmkhoNb/1s+oMjE18NY94uG0V3" +
                                                    "AU+MePN2p/F6/L1zJ1csmLz2fV6uOKzH8ufZEnxtg2Rr8ps25glZFZUM1Ded" +
                                                    "vf1dsZ7dPjky+XnwOPcVJA/4wxXBpLZiprskT2jTRyen4j2PbQjbUXAbI1VM" +
                                                    "0z+v0FGq+FhVI6esIr6b3569iPPQk08/z66s32KlxnUzR8ngwpePvtu0Z+vw" +
                                                    "fXMo3VcFZAqyfLL73Ks7bpN+HCYlbuDK+SCQvag9O1xVG5SlDXVPVtBa6RoQ" +
                                                    "t+dGaEnbgJJFZ6aQVV3NnES7RWbImS9xDj8skLAeRvID1JchqibeAHy2npNV" +
                                                    "j2e7+q3Qxm3s40Vj929/tsDYT5GcAmgqv5dAecunbUGy1cJzJzjpqCbHZ4WL" +
                                                    "bk9WQDtmwz2WF26RjpEf7y8LjD2B5Ofgn0nqBqniQZ+wQZ+Yf9AXCow9h+Rp" +
                                                    "G7QdCWcFzaPiWminbdCn5x/0bwuM/R7JRUZqAPQ3ZMO6Q2Ffd3HQV0ObtqFP" +
                                                    "zw9021n5+XEWfyyA/yUkf2CkUjYhqsjxvEZfMaRpChXVWUXC6EKaoZ23RTo/" +
                                                    "7yJZRfGVAiL9BcllEAlUwot/vqw482+C9qKN/cV5x97NWfyjAPbXkVxlpCSu" +
                                                    "sTl4LcK+bMO+PD+wS7xEH7zw/bOAAG8juTYXAeodD75qC3D1M3KDfxeAfQPJ" +
                                                    "u1jQuLHn0TzXKpCrTxzHDySNOf+TsL6jS89O1VUum9r7N/51zP3WXRUjlYm0" +
                                                    "ovgvF773ct2gCZlvW2VdNXT+uMlIJPg1E3IQPji4961p/2FkgW8aOKz95p/0" +
                                                    "MYCHSfj6ie5kcl8VaF2aMiSrFtODldmarBqJ///GqWfS1n9wBqXzU7t233/j" +
                                                    "y4/x4qhMUsRDvOyujJEK65ufWxOtnpGbw6t8Z9tHtReq1jq1Xi2SBp9J+LCt" +
                                                    "yv85rDOlM/4B69Cvl/3qjsen3uAV7v8Bv3yLk1gbAAA=");
}
