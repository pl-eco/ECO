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
    public static final long jlc$SourceLastModified$jl7 = 1170613220000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZa2wc1RW+u347jr12Xsbk6TioTuhO0zRtE9OU4DqJwzo2" +
                                                    "dpKqRsWMZ++uJ56dmczctdemLiRS6ohWaQXOi0LUVqa8AolQI/qC5kfLQ+RP" +
                                                    "2ipVVQERUgWCRlV+EGiB0nPuPHd2vV5LxtI9O3Mf3z3nnucdn71GykyDbNA1" +
                                                    "ZSypaCxKMyx6QNkcZWM6NaO7Y5t7RMOk8XZFNM290DcgNZ+vu/HxT4YiYVLe" +
                                                    "TxaJqqoxkcmaavZSU1NGaDxG6rzeDoWmTEYisQPiiCikmawIMdlkbTGywLeU" +
                                                    "kZaYw4IALAjAgsBZELZ7s2DRQqqmU+24QlSZeZB8n4RipFyXkD1G1mSD6KIh" +
                                                    "pmyYHi4BIFTi+34Qii/OGGS1K7slc47AxzcIUyfviTxfQur6SZ2s9iE7EjDB" +
                                                    "YJN+UpOiqUFqmNvjcRrvJ/UqpfE+asiiIo9zvvtJgyknVZGlDeoeEnamdWrw" +
                                                    "Pb2Tq5FQNiMtMc1wxUvIVIk7b2UJRUyCrEs9WS0Jd2A/CFgtA2NGQpSos6R0" +
                                                    "WFbjjKwKrnBlbLkTJsDSihRlQ5q7VakqQgdpsHSniGpS6GOGrCZhapmWhl0Y" +
                                                    "aZoRFM9aF6VhMUkHGGkMzuuxhmBWFT8IXMLIkuA0jgRaagpoyaefa3tuO3af" +
                                                    "uksNc57jVFKQ/0pYtDKwqJcmqEFViVoLa9bHTohLXzwaJgQmLwlMtua88L3r" +
                                                    "t9+68uKr1pyb88zpHjxAJTYgTQ/WXl7e3rqlBNmo1DVTRuVnSc7Nv8ceacvo" +
                                                    "4HlLXUQcjDqDF3tf/s4DT9P3w6S6k5RLmpJOgR3VS1pKlxVq7KQqNURG452k" +
                                                    "iqrxdj7eSSrgOSar1OrtTiRMyjpJqcK7yjX+DkeUAAg8ogp4ltWE5jzrIhvi" +
                                                    "zxmdELIQGmmAVkOsP/7LSJ+wzwRzF0RJVGVVE8B4qWhIQwKVtIFBON2hlGgM" +
                                                    "m4KUNpmWEsy0mlC0UcE0JEEzku67pBlU6BXHomhc+ucDm0FpIqOhEBz08qCb" +
                                                    "K+AhuzQlTo0BaSp9R8f15wZeD7tmb58DI4sBPWqjRxE9CugkFOKgi3EXS3Nw" +
                                                    "7sPgwRDbalr7vrv73qPNJWAy+mgpHFopTG0GOeytOySt3XPzTh7MJLC1xl/c" +
                                                    "PRn96IlvWrYmzByT864mF0+NHtp//5fCJJwdXFEU6KrG5T0YEt3Q1xJ0qny4" +
                                                    "dZPv3jh3YkLz3CsrWtten7sSvbY5eOiGJtE4xEEPfv1q8cLAixMtYVIKoQDC" +
                                                    "HxPBXCGyrAzukeW9bU4kRFnKQOCEZqREBYec8FXNhgxt1Ovh1lDLn+sdc66D" +
                                                    "tsi2b/6Lo4t0pIst60EtB6TgkXbHby6evvDIhi1hf1Cu86W5PsosF6/3jGSv" +
                                                    "QSn0v3Gq5+Hj1ybv5hYCM9bm26AFaTs4PKgMjvXIqwf//tab038Nu1YVYqRC" +
                                                    "N+QRiAMZALnF2wbigQIxCZXfsk9NaXE5IYuDCkXr/KRu3cYL/zoWsdSpQI9j" +
                                                    "DbfODuD133QHeeD1ez5cyWFCEuYjT3RvmnUCizzk7YYhjiEfmUN/XnH6FfEx" +
                                                    "CJcQokx5nPKoQ7hohJ+9wHW1ntNoYGwjktV6zhjvaHKVvAA7a6GttZW8Nr+S" +
                                                    "kTZzug7JF5wDLtfTg4osZQKclPPxchCttUDlZMgpCOYjdrYRJhreGn703Wct" +
                                                    "7w6mpsBkenTqwc+ix6bCvvy9NieF+tdYOZyLs9AS/zP4C0H7HzYUGzusGN7Q" +
                                                    "bieS1W4m0XU0ojWF2OJb7Hjn3MTvnpyYtMRoyE5fHVCdPXvl00vRU1dfyxNL" +
                                                    "wU00kdvbN/IRzvyWAkrvQLKpeKVvtZW+Na/SkWwO7BbmiGH+voSRZf7gn4L0" +
                                                    "CAkayqtNznhjzvh+ijFwE99hdwFRepDsLF6Uu2xR7pqrKPh6Jyd86r4CPH0b" +
                                                    "SW8uTxZTjfytqrDN78BS1ZfR/tutDB5++yNuCzk5KY8bBNb3C2cfbWrf9j5f" +
                                                    "7yUHXL0qk5vToaz31n756dQH4ebyP4VJRT+JSPadYb+opDEE90OdbDoXCbhX" +
                                                    "ZI1n17xWgdfmJr/lQT/0bRtMS579wzPOxufqQCbiSl7ulFzOr1/JIcIfBrie" +
                                                    "GQlrGddrIpa++ueONuiijc0DGnXRxotAq7XRamdAG3LQ4sVIOhvasItWjKSz" +
                                                    "oakuWgFJeXWxjFgVBnF+86AdRNLCwDq6ZLUIvIiNF5kBj3l4YoGzuwmXb4BW" +
                                                    "b+PV5+CF3NoF3SDaCdfKJDUa3v7Z9IeHJr8exrxdNoLuAp4Y8ebtSeN1+Adn" +
                                                    "j69YMHX1h7xccaBH8+fZEnxshWRr8ps15glZFZUM1DcdPX2dse49Pjky+TF4" +
                                                    "nPsakvv94YpgUlsx092RJ7Tpw1Nn4t2PbwzbUXA7I1VM07+o0BGq+KCqESmr" +
                                                    "iO/it2Uv4jz41DMvsMsbtlqpcf3MUTK48JXD7zXt3TZ07xxK91UBmYKQT3Wd" +
                                                    "fW3nLdJDYVLiBq6cDwDZi9qyw1W1QVnaUPdmBa2VrgFxe26ElrQNKFl0ZgpZ" +
                                                    "1dXMSbRLZIac+QpH+HGBhPUwkh+hvgxRNfEG4LP1nKx6NNvVb4Y2ZvM+VjTv" +
                                                    "/u1PFxj7KZITwJrK7yVQ3vJpW5Fss/i5HZx0RJPjs7KLbk9WQDtis3skL7tF" +
                                                    "OkZ+fn9ZYOxJJD8H/0xSN0gVz/Qxm+lj88/0+QJjzyN5xmbajoSzMs2j4jpo" +
                                                    "J22mT84/078tMPZ7JBcYqQGmvyUb1h0K+7qKY30NtGmb9en5Yd12Vn5+HOKP" +
                                                    "Bfh/GckfGKmUTYgqcjyv0VcMappCRXVWkTC6kNXQztkinZt3kayi+HIBkf6C" +
                                                    "5BKIBCrhxT9fVpz5N0F7yeb9pXnnvYtD/KMA728gucJISVxjc/BaZPuSzfal" +
                                                    "+WG7xEv0wQvfPwsI8A6Sq3MRoN7x4Cu2AFc+Jzf4dwG2ryN5DwsaN/Y8luda" +
                                                    "BXL1imP4gaQx538Q1ndz6bkzdZXLzuz7G/865n7broqRykRaUfyXC99zuW7Q" +
                                                    "hMy3rbKuGjr/ucFIJPg1E3IQ/nDmPrCm/YeRBb5p4LD2k3/SJ8A8TMLHT3Un" +
                                                    "k/uqQOvSlCFZtZgerMzWZtVI/P81Tj2Ttv5jMyCdO7N7z33Xv/o4L47KJEUc" +
                                                    "52V3ZYxUWN/83JpozYxoDlb5rtaPa89XrXNqvVokDT6T8PG2Kv/nsI6UzvgH" +
                                                    "rPFfL/vVbU+ceZNXuP8HkbPgcEgbAAA=");
}
