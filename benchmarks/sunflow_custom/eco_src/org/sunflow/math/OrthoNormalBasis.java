package org.sunflow.math;
final public class OrthoNormalBasis {
    private Vector3 u;
    private Vector3 v;
    private Vector3 w;
    private OrthoNormalBasis() { super();
                                 u = new Vector3();
                                 v = new Vector3();
                                 w = new Vector3(); }
    public void flipU() { u.negate(); }
    public void flipV() { v.negate(); }
    public void flipW() { w.negate(); }
    public void swapUV() { Vector3 t = u;
                           u = v;
                           v = t; }
    public void swapVW() { Vector3 t = v;
                           v = w;
                           w = t; }
    public void swapWU() { Vector3 t = w;
                           w = u;
                           u = t; }
    public Vector3 transform(Vector3 a, Vector3 dest) { dest.x = a.x * u.
                                                                         x +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     x +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     x;
                                                        dest.y = a.x * u.
                                                                         y +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     y +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     y;
                                                        dest.z = a.x * u.
                                                                         z +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     z +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     z;
                                                        return dest; }
    public Vector3 transform(Vector3 a) { float x = a.x * u.x + a.y * v.x +
                                            a.
                                              z *
                                            w.
                                              x;
                                          float y = a.x * u.y + a.y * v.y +
                                            a.
                                              z *
                                            w.
                                              y;
                                          float z = a.x * u.z + a.y * v.z +
                                            a.
                                              z *
                                            w.
                                              z;
                                          return a.set(x, y, z); }
    public Vector3 untransform(Vector3 a, Vector3 dest) { dest.x = Vector3.
                                                                     dot(
                                                                       a,
                                                                       u);
                                                          dest.y = Vector3.
                                                                     dot(
                                                                       a,
                                                                       v);
                                                          dest.z = Vector3.
                                                                     dot(
                                                                       a,
                                                                       w);
                                                          return dest; }
    public Vector3 untransform(Vector3 a) { float x = Vector3.dot(a, u);
                                            float y = Vector3.dot(a, v);
                                            float z = Vector3.dot(a, w);
                                            return a.set(x, y, z); }
    public float untransformX(Vector3 a) { return Vector3.dot(a, u); }
    public float untransformY(Vector3 a) { return Vector3.dot(a, v); }
    public float untransformZ(Vector3 a) { return Vector3.dot(a, w); }
    final public static OrthoNormalBasis makeFromW(Vector3 w) { OrthoNormalBasis onb =
                                                                  new OrthoNormalBasis(
                                                                  );
                                                                w.
                                                                  normalize(
                                                                    onb.
                                                                      w);
                                                                if (Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          y) &&
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          z)) {
                                                                    onb.
                                                                      v.
                                                                      x =
                                                                      0;
                                                                    onb.
                                                                      v.
                                                                      y =
                                                                      onb.
                                                                        w.
                                                                        z;
                                                                    onb.
                                                                      v.
                                                                      z =
                                                                      -onb.
                                                                         w.
                                                                         y;
                                                                }
                                                                else
                                                                    if (Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              y) <
                                                                          Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              z)) {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            z;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          0;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                    }
                                                                    else {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            y;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          0;
                                                                    }
                                                                Vector3.
                                                                  cross(
                                                                    onb.
                                                                      v.
                                                                      normalize(),
                                                                    onb.
                                                                      w,
                                                                    onb.
                                                                      u);
                                                                return onb;
    }
    final public static OrthoNormalBasis makeFromWV(Vector3 w,
                                                    Vector3 v) {
        OrthoNormalBasis onb =
          new OrthoNormalBasis(
          );
        w.
          normalize(
            onb.
              w);
        Vector3.
          cross(
            v,
            onb.
              w,
            onb.
              u).
          normalize();
        Vector3.
          cross(
            onb.
              w,
            onb.
              u,
            onb.
              v);
        return onb;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1159026718000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAMVZC2wUxxmeu/MDPyI/eD9iY2NCsMldSaChOBUQF4PhMK6N" +
       "bTAhznhvzl68t7vZ\nnTufDU1BUQMFpRQ1URupAdoiAXk0qdKKRE0ToiTkQa" +
       "uSSiVqJGgi2lAppVKVitKH1P7/7N7t3Z7v\nwL6iWtrx3M4///9//2se+9xV" +
       "UmgaZJ5k+vmozkx/S1cHNUwWalGoaW6BV/3S24UlHSc2qpqXeILE\nK4c4qQ" +
       "hKZiBEOQ3IoUDbV5rjBmnSNWV0UNG4n8W5f6ey3Oa3Ibg8g2HvkdPVe48X1H" +
       "pJYZBUUFXV\nOOWypq5VWMTkpDK4k8ZoIMplJRCUTd4cJLcxNRpp0VSTU5Wb" +
       "D5NHiC9IinQJeXJSF0wID4DwgE4N\nGgkI8YEOIRY4TDUYp7LKQmuS4mDmkv" +
       "SZoLY9rzOTGphMwcEegCM0ANTzk6gttBlQdd/Jni/uPnbK\nRyr6SIWsdiEz" +
       "CZBwkNdHyiMsMsAMc00oxEJ9pEplLNTFDJkq8piQ2keqTXlQpTxqMLOTmZoS" +
       "Q8Jq\nM6ozQ8hMvAyScgkxGVGJa0bSRmGZKaHEr8KwQgcB9gwHtgW3Fd8DwF" +
       "IZFDPCVGKJKQXDsgoer3XP\nSGJs2AgEMLU4wviQlhRVoFJ4QaotXypUHQx0" +
       "cUNWB4G0UIuCFE7mZGWKttapNEwHWT8ns9x0HdYQ\nUJUIQ+AUTqa7yQQn8N" +
       "Icl5dS/NM049r+k99/bbWI7YIQkxTUvxQm1bgmdbIwM5gqMWvi9aj/ibZt\n" +
       "0XleQoB4uovYolmz8HR38E+v11o0c8eh2Tywk0m8X2o/XNu5a51GfKjGFF0z" +
       "ZXR+GnKRDh32SHNc\nh6ydkeSIg/7E4JnOs9v2PMM+85LSNlIkaUo0AnFUJW" +
       "kRXVaYsY6pzKCchdpICVNDLWK8jRRDPwgh\nb73dHA6bjLeRAkW8KtLEbzBR" +
       "GFigiUqgL6thLdHXKR8S/bhOCLkNHlINj49Yf+I/J8v8ATOqhhVt\nJGAaUk" +
       "AzBpO/I8AgsNmAGGrXjAhV7qembPoxenROOgJDWoQFqERVWdUCgzLkq6TdFW" +
       "Ix/D8JnnHU\ntXrE48Hi505iBeJ/vaaEmNEvnbj8/u61G7+53woQDGobJZQd" +
       "EOW3RflRlN8ting8QsI0FGk5CUw8\nDMkKZa18cdeODQ/trwfTxPWRArAPkt" +
       "YDHluPtZLW4mR0myh+EoTVrB9u3+e/fmKVFVaB7IV33Nll\n558/d+zz8kYv" +
       "8Y5fFREf1OVSZNOBpTRZ7RrceTQe/78c2PTShXMX73QyipOGjETPnImJWu/2" +
       "hKFJ\nLASlz2F/fHaFr5f0HPaSAsh+qHhCfygmNW4ZaQnbnCh+iKU4SMrCwk" +
       "s4lKhYpXzI0EacNyJEKkV/\nKjinHCN4HjxFdkiL/zg6Xcd2hhVS6G0XClFc" +
       "rz9a9IUPXy17W5glUYcrUla6LsatrK5ygmWLwRi8\nv/i9ju88eXXfdhEpdq" +
       "hwUqwbcgzyOA5z7nDmQD4rUFPQkw3dakQLyWGZDigMQ+7fFQuX/uzP36q0\n" +
       "fKPAm4Rrl9yYgfN+9v1kz7kH/14j2HgkXE8cHA6ZBWeqw3mNYdBR1CO+9ze3" +
       "P/UOfRrKHZQYUx5j\nomoQAY0IQwaE4RtF63eNLcWmHngvyRL746ze/dLuZw" +
       "brow+/94rQuoymbgNS/bCJ6s2W67FZgNad\n6U7f9dQcArplZ9ofqFTO/As4" +
       "9gFHCVZNc7MBZSOe5kWburD4ozfenPHQBz7ibSWlikZDrVQkACmB\nyGPmEF" +
       "ScuL5qtQiuypEp2ArIRBhhjm2AeMovHyi3OHv+t+La76RO/8CSk8H3Nz8tDJ" +
       "A188dZ+lx8\nxl7rPnL9V/yS4OOkIM6ui2eWU9gvOXNXXIhVFb14NOIlxX2k" +
       "UrJ3dD1UiWKg98EGxExs82DXlzae\nvpmwVs7mZImZ507/FLHu5HfKOPSRGv" +
       "vl2fK9wM73Ane+e4jorMLmDk48UTF3JqzaGQtDD0NP32NV\nCGzvxma15cvl" +
       "WX2+cuLatCa0iU1Um3W3QJtNCW1GJqpNew5trKEG0S6y6qGXg4tllUIkFOnR" +
       "AUWW\n4mlZA4lye7ZdoNjB7tv61/LH6Fs7rEW1On1ntRZOH1dG32SL7nv8k3" +
       "E2AiVc0+9SWIwpKTIrUGTa\nYr5JbJCdXDhw6tnT/IOmlZbIxux57J7YuPLY" +
       "WO3KFw5OYgmvdRnBzboqNverviH5Xa/Yw1uplbH3\nT5/UnJ5QpaBP1FC3pK" +
       "XV/GTolKE7ZiZiKPE/cxnN8LAH+73xHMvCYI4xGZsBDBJF1rvBNbNST7yG\n" +
       "HIGdc0zsFC4/Vv+Ld7uP7rO8kqO6ps3ql77++4+HfYfeGLDmuUuoi/hwzfFP" +
       "X7rcOc0KJuuItSDj\nlJM6xzpmCSwVOoZzXS4JgvqtprrnHum8JDTCeX2cFM" +
       "Q0OeQkmXSjlE+mD/54MNOJlbYTK8d1Ijah\nHE4ZzTG2C5uY7bAe/KE5eo/k" +
       "q/c0W+9pk9L70Rxj38Bmj613r0vvvfnoPct+Ev2J6/14jrFD2ByA\n6mmOUL" +
       "3bbfCD+SpeYyteMynFv5tj7ClsnrAV73Fb/Ml8FW+wFW+YlOI/yDH2I2yO2I" +
       "r3drsUP5qP\n4svhabQVb7xpxb3WSnrDlfqGBELEszmg/wSbk7hwGlQ18Sh2" +
       "c0KFZU7lY5lF8Cy1LbP0pi3jsY/n\nNwX85zmAv47Ny5ME/ko+wO+FZ4UNfM" +
       "X/KSTey2GZX2NzlpOyqDoZ27yTj20Ww7PKts2qWxMUv8sB\n/SI2FyYN/cN8" +
       "oN8JT6sNvfXWQL+SA/pn2PyBk/IU6FsF5bBu6auLpVSj3AH8x3wBb7ABb7g1" +
       "gK/l\nAPxPbD5PB7wN31118P0tX3ztNr72W4LP482Oz1OEL/+Tjq8vHZ+HTB" +
       "Kf2PR8GZ5OG1/nuPgEfdrR\nwYd9saXpxeVWfIJxHyQmZIGqHBaYjk0ZlPgI" +
       "HWathhbpNb+GnMnslBNHu6aKa3hZwt16PHHFhDcb\nfoOF8QINLz/jo58s+m" +
       "j+LytbzllXbUOcLEy5A7EpA21qTJPEyW89VUMKM6ybt3njCuw1qK4z4/zH\n" +
       "n+441HTlLB4OdMc35fn4pg2esO2b8MR9g/Ir3H75n65AnoU5HNeEzXxOSpOO" +
       "60E6z0zRznVsVHez\nNopzUun+JICOmZXx1dD60iUFP9r1wLXgb/8hLreTX6" +
       "PKgmRKOKooqbdWKf0iHeJAFvYts+6wdIFn\nGUh3GwNOgPhPILrHIrsXFp4U" +
       "Mk6K7V4q0UpOfECE3WY9YepKJ2at27j0KxdEuiDt+Cw+zCYuHqLW\np9l+ae" +
       "vz2+fHD275trjNKJQUOjaGbEqDpNi6pBdc8fKiLiu3BK/z5MUXel798ZcSR1" +
       "5xhzstJfbS\nwvduazSHNw1SO/7F+NqIzsVV9tjLM39634kjl0Qe6f8FeET9" +
       "Ek8fAAA=");
}
