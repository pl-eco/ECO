package org.sunflow.core;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
final public class Ray {
    public float ox;
    public float oy;
    public float oz;
    public float dx;
    public float dy;
    public float dz;
    private float tMin;
    private float tMax;
    final private static float EPSILON = 0;
    private Ray() { super(); }
    public Ray(float ox, float oy, float oz, float dx, float dy, float dz) {
        super();
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
    final public float getMin() { return tMin; }
    final public float getMax() { return tMax; }
    final public Vector3 getDirection() { return new Vector3(dx, dy, dz);
    }
    final public boolean isInside(float t) { return tMin < t && t < tMax;
    }
    final public Point3 getPoint(Point3 dest) { dest.x = ox + tMax * dx;
                                                dest.y = oy + tMax * dy;
                                                dest.z = oz + tMax * dz;
                                                return dest; }
    final public float dot(Vector3 v) { return dx * v.x + dy * v.y + dz *
                                          v.
                                            z; }
    final public float dot(float vx, float vy, float vz) { return dx * vx +
                                                             dy *
                                                             vy +
                                                             dz *
                                                             vz; }
    final public void setMax(float t) { tMax = t; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170613220000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVZe2wUxxmfu/MDP4LPBszLYDAGCoa7vKjApgJqTDActrHB" +
                                                   "JAbijHfnzgt7u5vd\nOXN2CA2NgmloSGmT0kqFoMoSkEJASipaKSWkITQNRI" +
                                                   "JWpS0ShJQ2aZWmUhspELV/9JvZvdu7vb3D\nwXDSzu7tzHzf/L73zB77DOUb" +
                                                   "OqoSjADt14gRaOxow7pBxEYZG8Y6eNUtvJtf1HZ4taJ6kSeEvJJI\nUVlIMI" +
                                                   "IipjgoicHm5Q1xHdVpqtwfkVUaIHEa2CIvsOitCi3IILjh4KmKnUN51V6UH0" +
                                                   "JlWFFUiqmk\nKk0yiRoU+UNbcB8OxqgkB0OSQRtC6D6ixKKNqmJQrFDjSbQD" +
                                                   "+UKoQBMYTYqmhxLMg8A8qGEdR4Oc\nfbCNswUKY3RCsaQQcVmSHcyclz4Tlm" +
                                                   "3Na88cDURGsc5OgMNXAKinJVGbaDOgar4jnV/ffuioD5V1\noTJJ6WDEBEBC" +
                                                   "gV8XKo2SaA/RjWWiSMQuVK4QInYQXcKyNMC5dqEKQ4oomMZ0YrQTQ5X72MAK" +
                                                   "I6YR\nnfNMvAyhUoFh0mMCVfWkjMISkcXEv/ywjCMAu9KGbcJdwd4DwGIJFq" +
                                                   "aHsUASU/K2SgpovNo5I4mx\ndjUMgKmFUUJ71SSrPAXDC1Rh6lLGSiTYQXVJ" +
                                                   "icDQfDUGXCialJUok7WGha04QropmuAc12Z2wagi\nLgg2haJxzmGcEmhpkk" +
                                                   "NLKfqpq/xi95GfnF7KbTtPJILM1l8Mk6Y6JrWTMNGJIhBz4q1Y4KXmx2JV\n" +
                                                   "XoRg8DjHYHPMspmn1of+/la1OWayy5jWni1EoN1Cy77q9qceUZGPLWOUphoS" +
                                                   "U34acu4ObVZPQ1wD\nr61MUmSdgUTnmfZzjz3zKvnUi4qbUYGgyrEo2FG5oE" +
                                                   "Y1SSb6I0QhOqZEbEZFRBEbeX8zKoTnEJi8\n+bY1HDYIbUZ5Mn9VoPL/IKIw" +
                                                   "kGAiKoJnSQmriWcN017+HNcQQvfBhSrgKkXmj98pmh0IGjElLKvb\ngoYuBF" +
                                                   "U9kvwvqDoJtuP+ADMYjaLGYK8aJUEsYEVS1GBEAhcV1Pki6WP34ZGJsxVVbP" +
                                                   "N4WIhzuqoM\nVr5SlUWidwuHb7y/vWn1d3abZsBM18JC0VigHrCoBxj1AFBH" +
                                                   "Hg8nOpZxMaUPstsKXgjxqnROx+ZV\nT+yu8YHatW15ADwPhtbAqi3WTYLaaL" +
                                                   "tqM49qAtjLhJ9uHAzcOrzEtJdg9ojqOrvk4vHzhz4vnetF\nXvdwxyBBwC1m" +
                                                   "ZNpYjEyGsVqng7jR/9fza16/fP7q12xXoag2w4MzZzIPrHEKX1cFIkJMs8kP" +
                                                   "TSzz\nbUCd+7woD9waQhlfP0SJqU4eaZ7YkIhqDEthCJWEVT2KZdaVCEXFtF" +
                                                   "dXt9lvuFX4+fOYhGmWwTXG\nslV+Z73jNNZWmlbEtO1AwaPmrWcL7v/jmyXv" +
                                                   "crEkAmxZSgrrINR013LbWNbphMD7qz9q+8HLnw1u\n5JZimoqHokJNl/rAQe" +
                                                   "MwZ5Y9BxxVhmDBNFm7XomqohSWcI9MmMn9r2zmAz//516/qRsZ3iRUO+/2\n" +
                                                   "BOz3E7+Jnjn/+M2pnIxHYInCxmEPM+GMsSkv03Xcz9YR3/m7KT/+DT4AcQxi" +
                                                   "hyENEB4OEIeGuCCD\nXPBzeRtw9D3AmhqgPS+L7buk5W5h+6uRmtiTv/0lX3" +
                                                   "UJTs3vqXpYg7UGU/WsmcGkO97pviux0Qvj\nHj7Tsskvn/kvUOwCigKkQ6NV" +
                                                   "h0gRT9OiNTq/8Mrb71Q+ccmHvCtQsaxicQXmDoCKwPKI0QtBJq4t\nWcqNy7" +
                                                   "9tFGs5ZMSFMMkSAP9TlTTLEvZyNFwzLLOc4W6WrK3l7eykBRVosR5ZEuIOUR" +
                                                   "fw/gIAPiG1\n3tOlKOSNPu5ON3bV/Oq99a8MmiFoTo6iLnVWt/CtD69v9b34" +
                                                   "do85z5k7HYP3TR36+PUb7WNNazUL\njBkZOT51jllkcMRlGtPc9Fwc+Oizdd" +
                                                   "OP7Wi/Zq2oIj1VNkE5+Un/O2T24hc+con54MYq5i603K3h\n61iSw45XsWYR" +
                                                   "73qQNfWmJhbcsQ3UWzZQ72oDrGlwLMfLKXr5//EUjU/NYlHI1VAtQK33UKJ/" +
                                                   "QkZ/\nJ2E2/BDnsDYH1g2sabGxto4U61oL69p7hTVbP2fweA6oIms22lA3DR" +
                                                   "dqPOVfkZHTr1awet3Oit09\n846E3m89wI00a1J3cTkHnYHT6w/e+oBe43Ts" +
                                                   "7MpmT49nFkewx7HnLrzcV15w8pWoFxV2Ib9g7cI6\nsRxjOawLNg1GYmsGO7" +
                                                   "W0/vQNgFntNiSrhyqnz6ewdeZ120HhmY1mz6WOVM5tqCpRfybuqTbkQfxB\n" +
                                                   "4WZEkVeNJ926wlasejvFSl+dpZFk2e/Gkt4DlvEkywE3lv13wHK0xXJ0FpZP" +
                                                   "J1iKroLdcQ9YfjvJ\n0lWwz94DloNJlq6C3T18lrz4HI/MAhQl7i4sv8uaWR" +
                                                   "Rsf42kuDF94Q6Y+i2m/ixM99lMsas+vz98\nphPZ2zq4yi2m5RlMPckimYWL" +
                                                   "QLNCSYToFX85NHRz5+BCL6sp8/tYWIGI5bfHtcTYGcquYy9PKXnp\n+h6W77" +
                                                   "UE6R9mlkc+lt4lBfON/hyolQx+JBOHurupraM51NriBnR/DqDxTCZe9nyANd" +
                                                   "9Ijf2IlS5T\nsp1G8LJl8NF/l+7CZzd7rZyzgkIVqWrzZdJH5BRSxYxS2pZy" +
                                                   "DT9/scP280d/dopeqqs3C6C52VOO\nc+Lc+kMD1fUn9tzBRrLagc1Jurxv8l" +
                                                   "pfr/Selx8RmVkg42gpfVJDeuwvhvXEdGVdWgaYlu7CrFKO\nWFYWcVoZVyz3" +
                                                   "X0em95jVc/aKaA2muhR/mFM4maNMeIM1x5nedKwYbD9qPM0oo4kpVXeLqvCD" +
                                                   "GElg\nUOKJvQg3aJ2E2U6L7ZLj/R/NvjLtgr/xvLkn66VoZkpGtUYGm5U+Ve" +
                                                   "DKWYkVUSa6uUWrcmW4Qcea\nRvSL1z/e/GLdJ+e4w9iW/tpXqt+Opkt+Mlz9" +
                                                   "luT7hy35VOH9OkffWda8BYJV+B4fdpd82FLNXEsT\nBKo+VRJtLGfuEAsPTd" +
                                                   "VwPWdhec4VyzD83h3IpRx9v2fNBYhLEZIM9TaiD0aKaK+FaO/dRXQ1R9+H\n" +
                                                   "rPmThcjKIzaiP48E0Wy49luI9t9dRP/I0fcpa/5KUSkgWi7p1qFK1sBhbaVs" +
                                                   "zH8bCea5cA1ZmIdG\njtkKe1wrfPrNHMC/ZM1/KBolGRCfJTHDAQt7VFUmWL" +
                                                   "Gxfj4SrPPhOmFhPXHXsA5nI+gpyC4GTzFr\nPCAG0D+fcjuKSXF4vCMRx0y4" +
                                                   "TlviOH3XxZHzDMBTmUMek1hTTpFPVGm6e3sqRoJ3IVwXLLwXRo7X\nZxaAfI" +
                                                   "V2wxHU5kA3izXVruimjVSbly10l+++I3vuz4GJr76OVb/JmHzOhjVv2AcbIJ" +
                                                   "V23M9qjQkZ\nn0LNz3dC6MpTm74I/eFLfrCf/MRWEkKjwjFZTt3WpzwXaFDa" +
                                                   "SHxdJeYmX+PrXkSR3/lBBtI+u7GF\neRaawxZTVJIyDOKS9ZQ6aAksHgaxx6" +
                                                   "VawglS9hXmcUV6/c6QzkgrpfnX5kS5GzO/N3cLjx7fOC2+\nZ933eA2dL8h4" +
                                                   "gO8Ui0Oo0PxAkSyZp2ellqB1EZ080fnma4sSWwJ+fj02xULSjOxBszeHAqFM" +
                                                   "d/8o\n0BTVKD/GH/jF+DcWHz54jZeG2v8BrK47SSQgAAA=");
}
