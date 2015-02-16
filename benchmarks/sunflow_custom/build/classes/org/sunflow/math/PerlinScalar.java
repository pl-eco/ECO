package org.sunflow.math;
public final class PerlinScalar {
    private static final float[] G1 = { -1, 1 };
    private static final float[][] G2 = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0,
    -1 } };
    private static final float[][] G3 = { { 1, 1, 0 }, { -1, 1, 0 }, { 1,
    -1,
    0 },
    { -1,
    -1,
    0 },
    { 1,
    0,
    1 },
    { -1,
    0,
    1 },
    { 1,
    0,
    -1 },
    { -1,
    0,
    -1 },
    { 0,
    1,
    1 },
    { 0,
    -1,
    1 },
    { 0,
    1,
    -1 },
    { 0,
    -1,
    -1 },
    { 1,
    1,
    0 },
    { -1,
    1,
    0 },
    { 0,
    -1,
    1 },
    { 0,
    -1,
    -1 } };
    private static final float[][] G4 = { { -1, -1, -1, 0 }, { -1, -1, 1,
    0 },
    { -1,
    1,
    -1,
    0 },
    { -1,
    1,
    1,
    0 },
    { 1,
    -1,
    -1,
    0 },
    { 1,
    -1,
    1,
    0 },
    { 1,
    1,
    -1,
    0 },
    { 1,
    1,
    1,
    0 },
    { -1,
    -1,
    0,
    -1 },
    { -1,
    1,
    0,
    -1 },
    { 1,
    -1,
    0,
    -1 },
    { 1,
    1,
    0,
    -1 },
    { -1,
    -1,
    0,
    1 },
    { -1,
    1,
    0,
    1 },
    { 1,
    -1,
    0,
    1 },
    { 1,
    1,
    0,
    1 },
    { -1,
    0,
    -1,
    -1 },
    { 1,
    0,
    -1,
    -1 },
    { -1,
    0,
    -1,
    1 },
    { 1,
    0,
    -1,
    1 },
    { -1,
    0,
    1,
    -1 },
    { 1,
    0,
    1,
    -1 },
    { -1,
    0,
    1,
    1 },
    { 1,
    0,
    1,
    1 },
    { 0,
    -1,
    -1,
    -1 },
    { 0,
    -1,
    -1,
    1 },
    { 0,
    -1,
    1,
    -1 },
    { 0,
    -1,
    1,
    1 },
    { 0,
    1,
    -1,
    -1 },
    { 0,
    1,
    -1,
    1 },
    { 0,
    1,
    1,
    -1 },
    { 0,
    1,
    1,
    1 } };
    private static final int[] p = { 151, 160, 137, 91, 90, 15, 131, 13, 201,
    95,
    96,
    53,
    194,
    233,
    7,
    225,
    140,
    36,
    103,
    30,
    69,
    142,
    8,
    99,
    37,
    240,
    21,
    10,
    23,
    190,
    6,
    148,
    247,
    120,
    234,
    75,
    0,
    26,
    197,
    62,
    94,
    252,
    219,
    203,
    117,
    35,
    11,
    32,
    57,
    177,
    33,
    88,
    237,
    149,
    56,
    87,
    174,
    20,
    125,
    136,
    171,
    168,
    68,
    175,
    74,
    165,
    71,
    134,
    139,
    48,
    27,
    166,
    77,
    146,
    158,
    231,
    83,
    111,
    229,
    122,
    60,
    211,
    133,
    230,
    220,
    105,
    92,
    41,
    55,
    46,
    245,
    40,
    244,
    102,
    143,
    54,
    65,
    25,
    63,
    161,
    1,
    216,
    80,
    73,
    209,
    76,
    132,
    187,
    208,
    89,
    18,
    169,
    200,
    196,
    135,
    130,
    116,
    188,
    159,
    86,
    164,
    100,
    109,
    198,
    173,
    186,
    3,
    64,
    52,
    217,
    226,
    250,
    124,
    123,
    5,
    202,
    38,
    147,
    118,
    126,
    255,
    82,
    85,
    212,
    207,
    206,
    59,
    227,
    47,
    16,
    58,
    17,
    182,
    189,
    28,
    42,
    223,
    183,
    170,
    213,
    119,
    248,
    152,
    2,
    44,
    154,
    163,
    70,
    221,
    153,
    101,
    155,
    167,
    43,
    172,
    9,
    129,
    22,
    39,
    253,
    19,
    98,
    108,
    110,
    79,
    113,
    224,
    232,
    178,
    185,
    112,
    104,
    218,
    246,
    97,
    228,
    251,
    34,
    242,
    193,
    238,
    210,
    144,
    12,
    191,
    179,
    162,
    241,
    81,
    51,
    145,
    235,
    249,
    14,
    239,
    107,
    49,
    192,
    214,
    31,
    181,
    199,
    106,
    157,
    184,
    84,
    204,
    176,
    115,
    121,
    50,
    45,
    127,
    4,
    150,
    254,
    138,
    236,
    205,
    93,
    222,
    114,
    67,
    29,
    24,
    72,
    243,
    141,
    128,
    195,
    78,
    66,
    215,
    61,
    156,
    180,
    151,
    160,
    137,
    91,
    90,
    15,
    131,
    13,
    201,
    95,
    96,
    53,
    194,
    233,
    7,
    225,
    140,
    36,
    103,
    30,
    69,
    142,
    8,
    99,
    37,
    240,
    21,
    10,
    23,
    190,
    6,
    148,
    247,
    120,
    234,
    75,
    0,
    26,
    197,
    62,
    94,
    252,
    219,
    203,
    117,
    35,
    11,
    32,
    57,
    177,
    33,
    88,
    237,
    149,
    56,
    87,
    174,
    20,
    125,
    136,
    171,
    168,
    68,
    175,
    74,
    165,
    71,
    134,
    139,
    48,
    27,
    166,
    77,
    146,
    158,
    231,
    83,
    111,
    229,
    122,
    60,
    211,
    133,
    230,
    220,
    105,
    92,
    41,
    55,
    46,
    245,
    40,
    244,
    102,
    143,
    54,
    65,
    25,
    63,
    161,
    1,
    216,
    80,
    73,
    209,
    76,
    132,
    187,
    208,
    89,
    18,
    169,
    200,
    196,
    135,
    130,
    116,
    188,
    159,
    86,
    164,
    100,
    109,
    198,
    173,
    186,
    3,
    64,
    52,
    217,
    226,
    250,
    124,
    123,
    5,
    202,
    38,
    147,
    118,
    126,
    255,
    82,
    85,
    212,
    207,
    206,
    59,
    227,
    47,
    16,
    58,
    17,
    182,
    189,
    28,
    42,
    223,
    183,
    170,
    213,
    119,
    248,
    152,
    2,
    44,
    154,
    163,
    70,
    221,
    153,
    101,
    155,
    167,
    43,
    172,
    9,
    129,
    22,
    39,
    253,
    19,
    98,
    108,
    110,
    79,
    113,
    224,
    232,
    178,
    185,
    112,
    104,
    218,
    246,
    97,
    228,
    251,
    34,
    242,
    193,
    238,
    210,
    144,
    12,
    191,
    179,
    162,
    241,
    81,
    51,
    145,
    235,
    249,
    14,
    239,
    107,
    49,
    192,
    214,
    31,
    181,
    199,
    106,
    157,
    184,
    84,
    204,
    176,
    115,
    121,
    50,
    45,
    127,
    4,
    150,
    254,
    138,
    236,
    205,
    93,
    222,
    114,
    67,
    29,
    24,
    72,
    243,
    141,
    128,
    195,
    78,
    66,
    215,
    61,
    156,
    180 };
    public static final float snoise(float x) { int xf = (int) Math.floor(
                                                                      x);
                                                int X = xf & 255;
                                                x -= xf;
                                                float u = fade(x);
                                                int A = p[X];
                                                int B = p[X + 1];
                                                return lerp(u, grad(p[A],
                                                                    x), grad(
                                                                          p[B],
                                                                          x -
                                                                            1));
    }
    public static final float snoise(float x, float y) { int xf = (int) Math.
                                                                    floor(
                                                                      x);
                                                         int yf = (int) Math.
                                                                    floor(
                                                                      y);
                                                         int X = xf & 255;
                                                         int Y = yf & 255;
                                                         x -= xf;
                                                         y -= yf;
                                                         float u = fade(x);
                                                         float v = fade(y);
                                                         int A = p[X] + Y;
                                                         int B = p[X + 1] +
                                                           Y;
                                                         return lerp(v, lerp(
                                                                          u,
                                                                          grad(
                                                                            p[A],
                                                                            x,
                                                                            y),
                                                                          grad(
                                                                            p[B],
                                                                            x -
                                                                              1,
                                                                            y)),
                                                                     lerp(
                                                                       u,
                                                                       grad(
                                                                         p[A +
                                                                             1],
                                                                         x,
                                                                         y -
                                                                           1),
                                                                       grad(
                                                                         p[B +
                                                                             1],
                                                                         x -
                                                                           1,
                                                                         y -
                                                                           1)));
    }
    public static final float snoise(float x, float y, float z) {
        int xf =
          (int)
            Math.
            floor(
              x);
        int yf =
          (int)
            Math.
            floor(
              y);
        int zf =
          (int)
            Math.
            floor(
              z);
        int X =
          xf &
          255;
        int Y =
          yf &
          255;
        int Z =
          zf &
          255;
        x -=
          xf;
        y -=
          yf;
        z -=
          zf;
        float u =
          fade(
            x);
        float v =
          fade(
            y);
        float w =
          fade(
            z);
        int A =
          p[X] +
          Y;
        int AA =
          p[A] +
          Z;
        int AB =
          p[A +
              1] +
          Z;
        int B =
          p[X +
              1] +
          Y;
        int BA =
          p[B] +
          Z;
        int BB =
          p[B +
              1] +
          Z;
        return lerp(
                 w,
                 lerp(
                   v,
                   lerp(
                     u,
                     grad(
                       p[AA],
                       x,
                       y,
                       z),
                     grad(
                       p[BA],
                       x -
                         1,
                       y,
                       z)),
                   lerp(
                     u,
                     grad(
                       p[AB],
                       x,
                       y -
                         1,
                       z),
                     grad(
                       p[BB],
                       x -
                         1,
                       y -
                         1,
                       z))),
                 lerp(
                   v,
                   lerp(
                     u,
                     grad(
                       p[AA +
                           1],
                       x,
                       y,
                       z -
                         1),
                     grad(
                       p[BA +
                           1],
                       x -
                         1,
                       y,
                       z -
                         1)),
                   lerp(
                     u,
                     grad(
                       p[AB +
                           1],
                       x,
                       y -
                         1,
                       z -
                         1),
                     grad(
                       p[BB +
                           1],
                       x -
                         1,
                       y -
                         1,
                       z -
                         1))));
    }
    public static final float snoise(float x, float y, float z,
                                     float w) { int xf = (int)
                                                           Math.
                                                           floor(
                                                             x);
                                                int yf = (int)
                                                           Math.
                                                           floor(
                                                             y);
                                                int zf = (int)
                                                           Math.
                                                           floor(
                                                             z);
                                                int wf = (int)
                                                           Math.
                                                           floor(
                                                             w);
                                                int X = xf &
                                                  255;
                                                int Y = yf &
                                                  255;
                                                int Z = zf &
                                                  255;
                                                int W = wf &
                                                  255;
                                                x -= xf;
                                                y -= yf;
                                                z -= zf;
                                                w -= wf;
                                                float u =
                                                  fade(
                                                    x);
                                                float v =
                                                  fade(
                                                    y);
                                                float t =
                                                  fade(
                                                    z);
                                                float s =
                                                  fade(
                                                    w);
                                                int A = p[X] +
                                                  Y;
                                                int AA = p[A] +
                                                  Z;
                                                int AB = p[A +
                                                             1] +
                                                  Z;
                                                int B = p[X +
                                                            1] +
                                                  Y;
                                                int BA = p[B] +
                                                  Z;
                                                int BB = p[B +
                                                             1] +
                                                  Z;
                                                int AAA =
                                                  p[AA] +
                                                  W;
                                                int AAB =
                                                  p[AA +
                                                      1] +
                                                  W;
                                                int ABA =
                                                  p[AB] +
                                                  W;
                                                int ABB =
                                                  p[AB +
                                                      1] +
                                                  W;
                                                int BAA =
                                                  p[BA] +
                                                  W;
                                                int BAB =
                                                  p[BA +
                                                      1] +
                                                  W;
                                                int BBA =
                                                  p[BB] +
                                                  W;
                                                int BBB =
                                                  p[BB +
                                                      1] +
                                                  W;
                                                return lerp(
                                                         s,
                                                         lerp(
                                                           t,
                                                           lerp(
                                                             v,
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[AAA],
                                                                 x,
                                                                 y,
                                                                 z,
                                                                 w),
                                                               grad(
                                                                 p[BAA],
                                                                 x -
                                                                   1,
                                                                 y,
                                                                 z,
                                                                 w)),
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[ABA],
                                                                 x,
                                                                 y -
                                                                   1,
                                                                 z,
                                                                 w),
                                                               grad(
                                                                 p[BBA],
                                                                 x -
                                                                   1,
                                                                 y -
                                                                   1,
                                                                 z,
                                                                 w))),
                                                           lerp(
                                                             v,
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[AAB],
                                                                 x,
                                                                 y,
                                                                 z -
                                                                   1,
                                                                 w),
                                                               grad(
                                                                 p[BAB],
                                                                 x -
                                                                   1,
                                                                 y,
                                                                 z -
                                                                   1,
                                                                 w)),
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[ABB],
                                                                 x,
                                                                 y -
                                                                   1,
                                                                 z -
                                                                   1,
                                                                 w),
                                                               grad(
                                                                 p[BBB],
                                                                 x -
                                                                   1,
                                                                 y -
                                                                   1,
                                                                 z -
                                                                   1,
                                                                 w)))),
                                                         lerp(
                                                           t,
                                                           lerp(
                                                             v,
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[AAA +
                                                                     1],
                                                                 x,
                                                                 y,
                                                                 z,
                                                                 w -
                                                                   1),
                                                               grad(
                                                                 p[BAA +
                                                                     1],
                                                                 x -
                                                                   1,
                                                                 y,
                                                                 z,
                                                                 w -
                                                                   1)),
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[ABA +
                                                                     1],
                                                                 x,
                                                                 y -
                                                                   1,
                                                                 z,
                                                                 w -
                                                                   1),
                                                               grad(
                                                                 p[BBA +
                                                                     1],
                                                                 x -
                                                                   1,
                                                                 y -
                                                                   1,
                                                                 z,
                                                                 w -
                                                                   1))),
                                                           lerp(
                                                             v,
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[AAB +
                                                                     1],
                                                                 x,
                                                                 y,
                                                                 z -
                                                                   1,
                                                                 w -
                                                                   1),
                                                               grad(
                                                                 p[BAB +
                                                                     1],
                                                                 x -
                                                                   1,
                                                                 y,
                                                                 z -
                                                                   1,
                                                                 w -
                                                                   1)),
                                                             lerp(
                                                               u,
                                                               grad(
                                                                 p[ABB +
                                                                     1],
                                                                 x,
                                                                 y -
                                                                   1,
                                                                 z -
                                                                   1,
                                                                 w -
                                                                   1),
                                                               grad(
                                                                 p[BBB +
                                                                     1],
                                                                 x -
                                                                   1,
                                                                 y -
                                                                   1,
                                                                 z -
                                                                   1,
                                                                 w -
                                                                   1)))));
    }
    public static final float snoise(Point2 p) { return snoise(
                                                          p.
                                                            x,
                                                          p.
                                                            y);
    }
    public static final float snoise(Point3 p) { return snoise(
                                                          p.
                                                            x,
                                                          p.
                                                            y,
                                                          p.
                                                            z);
    }
    public static final float snoise(Point3 p, float t) {
        return snoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t);
    }
    public static final float noise(float x) { return 0.5F +
                                                 0.5F *
                                                 snoise(
                                                   x); }
    public static final float noise(float x, float y) { return 0.5F +
                                                          0.5F *
                                                          snoise(
                                                            x,
                                                            y);
    }
    public static final float noise(float x, float y, float z) {
        return 0.5F +
          0.5F *
          snoise(
            x,
            y,
            z);
    }
    public static final float noise(float x, float y, float z,
                                    float t) { return 0.5F +
                                                 0.5F *
                                                 snoise(
                                                   x,
                                                   y,
                                                   z,
                                                   t); }
    public static final float noise(Point2 p) { return 0.5F +
                                                  0.5F *
                                                  snoise(
                                                    p.
                                                      x,
                                                    p.
                                                      y);
    }
    public static final float noise(Point3 p) { return 0.5F +
                                                  0.5F *
                                                  snoise(
                                                    p.
                                                      x,
                                                    p.
                                                      y,
                                                    p.
                                                      z);
    }
    public static final float noise(Point3 p, float t) { return 0.5F +
                                                           0.5F *
                                                           snoise(
                                                             p.
                                                               x,
                                                             p.
                                                               y,
                                                             p.
                                                               z,
                                                             t);
    }
    public static final float pnoise(float xi, float period) {
        float x =
          xi %
          period +
          (xi <
             0
             ? period
             : 0);
        return ((period -
                   x) *
                  noise(
                    x) +
                  x *
                  noise(
                    x -
                      period)) /
          period;
    }
    public static final float pnoise(float xi, float yi, float w,
                                     float h) { float x =
                                                  xi %
                                                  w +
                                                  (xi <
                                                     0
                                                     ? w
                                                     : 0);
                                                float y =
                                                  yi %
                                                  h +
                                                  (yi <
                                                     0
                                                     ? h
                                                     : 0);
                                                float w_x =
                                                  w -
                                                  x;
                                                float h_y =
                                                  h -
                                                  y;
                                                float x_w =
                                                  x -
                                                  w;
                                                float y_h =
                                                  y -
                                                  h;
                                                return (noise(
                                                          x,
                                                          y) *
                                                          w_x *
                                                          h_y +
                                                          noise(
                                                            x_w,
                                                            y) *
                                                          x *
                                                          h_y +
                                                          noise(
                                                            x_w,
                                                            y_h) *
                                                          x *
                                                          y +
                                                          noise(
                                                            x,
                                                            y_h) *
                                                          w_x *
                                                          y) /
                                                  (w *
                                                     h); }
    public static final float pnoise(float xi, float yi, float zi,
                                     float w,
                                     float h,
                                     float d) { float x =
                                                  xi %
                                                  w +
                                                  (xi <
                                                     0
                                                     ? w
                                                     : 0);
                                                float y =
                                                  yi %
                                                  h +
                                                  (yi <
                                                     0
                                                     ? h
                                                     : 0);
                                                float z =
                                                  zi %
                                                  d +
                                                  (zi <
                                                     0
                                                     ? d
                                                     : 0);
                                                float w_x =
                                                  w -
                                                  x;
                                                float h_y =
                                                  h -
                                                  y;
                                                float d_z =
                                                  d -
                                                  z;
                                                float x_w =
                                                  x -
                                                  w;
                                                float y_h =
                                                  y -
                                                  h;
                                                float z_d =
                                                  z -
                                                  d;
                                                float xy =
                                                  x *
                                                  y;
                                                float h_yXd_z =
                                                  h_y *
                                                  d_z;
                                                float h_yXz =
                                                  h_y *
                                                  z;
                                                float w_xXy =
                                                  w_x *
                                                  y;
                                                return (noise(
                                                          x,
                                                          y,
                                                          z) *
                                                          w_x *
                                                          h_yXd_z +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z) *
                                                          w_xXy *
                                                          d_z +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z) *
                                                          x *
                                                          h_yXd_z +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z) *
                                                          xy *
                                                          d_z +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z_d) *
                                                          xy *
                                                          z +
                                                          noise(
                                                            x,
                                                            y,
                                                            z_d) *
                                                          w_x *
                                                          h_yXz +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z_d) *
                                                          w_xXy *
                                                          z +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z_d) *
                                                          x *
                                                          h_yXz) /
                                                  (w *
                                                     h *
                                                     d); }
    public static final float pnoise(float xi, float yi, float zi,
                                     float ti,
                                     float w,
                                     float h,
                                     float d,
                                     float p) { float x =
                                                  xi %
                                                  w +
                                                  (xi <
                                                     0
                                                     ? w
                                                     : 0);
                                                float y =
                                                  yi %
                                                  h +
                                                  (yi <
                                                     0
                                                     ? h
                                                     : 0);
                                                float z =
                                                  zi %
                                                  d +
                                                  (zi <
                                                     0
                                                     ? d
                                                     : 0);
                                                float t =
                                                  ti %
                                                  p +
                                                  (ti <
                                                     0
                                                     ? p
                                                     : 0);
                                                float w_x =
                                                  w -
                                                  x;
                                                float h_y =
                                                  h -
                                                  y;
                                                float d_z =
                                                  d -
                                                  z;
                                                float p_t =
                                                  p -
                                                  t;
                                                float x_w =
                                                  x -
                                                  w;
                                                float y_h =
                                                  y -
                                                  h;
                                                float z_d =
                                                  z -
                                                  d;
                                                float t_p =
                                                  t -
                                                  p;
                                                float xy =
                                                  x *
                                                  y;
                                                float d_zXp_t =
                                                  d_z *
                                                  p_t;
                                                float zXp_t =
                                                  z *
                                                  p_t;
                                                float zXt =
                                                  z *
                                                  t;
                                                float d_zXt =
                                                  d_z *
                                                  t;
                                                float w_xXy =
                                                  w_x *
                                                  y;
                                                float w_xXh_y =
                                                  w_x *
                                                  h_y;
                                                float xXh_y =
                                                  x *
                                                  h_y;
                                                return (noise(
                                                          x,
                                                          y,
                                                          z,
                                                          t) *
                                                          w_xXh_y *
                                                          d_zXp_t +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z,
                                                            t) *
                                                          xXh_y *
                                                          d_zXp_t +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z,
                                                            t) *
                                                          xy *
                                                          d_zXp_t +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z,
                                                            t) *
                                                          w_xXy *
                                                          d_zXp_t +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z_d,
                                                            t) *
                                                          xy *
                                                          zXp_t +
                                                          noise(
                                                            x,
                                                            y,
                                                            z_d,
                                                            t) *
                                                          w_xXh_y *
                                                          zXp_t +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z_d,
                                                            t) *
                                                          w_xXy *
                                                          zXp_t +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z_d,
                                                            t) *
                                                          xXh_y *
                                                          zXp_t +
                                                          noise(
                                                            x,
                                                            y,
                                                            z,
                                                            t_p) *
                                                          w_xXh_y *
                                                          d_zXt +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z,
                                                            t_p) *
                                                          xXh_y *
                                                          d_zXt +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z,
                                                            t_p) *
                                                          xy *
                                                          d_zXt +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z,
                                                            t_p) *
                                                          w_xXy *
                                                          d_zXt +
                                                          noise(
                                                            x_w,
                                                            y_h,
                                                            z_d,
                                                            t_p) *
                                                          xy *
                                                          zXt +
                                                          noise(
                                                            x,
                                                            y,
                                                            z_d,
                                                            t_p) *
                                                          w_xXh_y *
                                                          zXt +
                                                          noise(
                                                            x,
                                                            y_h,
                                                            z_d,
                                                            t_p) *
                                                          w_xXy *
                                                          zXt +
                                                          noise(
                                                            x_w,
                                                            y,
                                                            z_d,
                                                            t_p) *
                                                          xXh_y *
                                                          zXt) /
                                                  (w *
                                                     h *
                                                     d *
                                                     t); }
    public static final float pnoise(Point2 p, float periodx,
                                     float periody) { return pnoise(
                                                               p.
                                                                 x,
                                                               p.
                                                                 y,
                                                               periodx,
                                                               periody);
    }
    public static final float pnoise(Point3 p, Vector3 period) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final float pnoise(Point3 p, float t, Vector3 pperiod,
                                     float tperiod) { return pnoise(
                                                               p.
                                                                 x,
                                                               p.
                                                                 y,
                                                               p.
                                                                 z,
                                                               t,
                                                               pperiod.
                                                                 x,
                                                               pperiod.
                                                                 y,
                                                               pperiod.
                                                                 z,
                                                               tperiod);
    }
    public static final float spnoise(float xi, float period) {
        float x =
          xi %
          period +
          (xi <
             0
             ? period
             : 0);
        return ((period -
                   x) *
                  snoise(
                    x) +
                  x *
                  snoise(
                    x -
                      period)) /
          period;
    }
    public static final float spnoise(float xi, float yi,
                                      float w,
                                      float h) { float x =
                                                   xi %
                                                   w +
                                                   (xi <
                                                      0
                                                      ? w
                                                      : 0);
                                                 float y =
                                                   yi %
                                                   h +
                                                   (yi <
                                                      0
                                                      ? h
                                                      : 0);
                                                 float w_x =
                                                   w -
                                                   x;
                                                 float h_y =
                                                   h -
                                                   y;
                                                 float x_w =
                                                   x -
                                                   w;
                                                 float y_h =
                                                   y -
                                                   h;
                                                 return (snoise(
                                                           x,
                                                           y) *
                                                           w_x *
                                                           h_y +
                                                           snoise(
                                                             x_w,
                                                             y) *
                                                           x *
                                                           h_y +
                                                           snoise(
                                                             x_w,
                                                             y_h) *
                                                           x *
                                                           y +
                                                           snoise(
                                                             x,
                                                             y_h) *
                                                           w_x *
                                                           y) /
                                                   (w *
                                                      h);
    }
    public static final float spnoise(float xi, float yi,
                                      float zi,
                                      float w,
                                      float h,
                                      float d) { float x =
                                                   xi %
                                                   w +
                                                   (xi <
                                                      0
                                                      ? w
                                                      : 0);
                                                 float y =
                                                   yi %
                                                   h +
                                                   (yi <
                                                      0
                                                      ? h
                                                      : 0);
                                                 float z =
                                                   zi %
                                                   d +
                                                   (zi <
                                                      0
                                                      ? d
                                                      : 0);
                                                 float w_x =
                                                   w -
                                                   x;
                                                 float h_y =
                                                   h -
                                                   y;
                                                 float d_z =
                                                   d -
                                                   z;
                                                 float x_w =
                                                   x -
                                                   w;
                                                 float y_h =
                                                   y -
                                                   h;
                                                 float z_d =
                                                   z -
                                                   d;
                                                 float xy =
                                                   x *
                                                   y;
                                                 float h_yXd_z =
                                                   h_y *
                                                   d_z;
                                                 float h_yXz =
                                                   h_y *
                                                   z;
                                                 float w_xXy =
                                                   w_x *
                                                   y;
                                                 return (snoise(
                                                           x,
                                                           y,
                                                           z) *
                                                           w_x *
                                                           h_yXd_z +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z) *
                                                           w_xXy *
                                                           d_z +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z) *
                                                           x *
                                                           h_yXd_z +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z) *
                                                           xy *
                                                           d_z +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z_d) *
                                                           xy *
                                                           z +
                                                           snoise(
                                                             x,
                                                             y,
                                                             z_d) *
                                                           w_x *
                                                           h_yXz +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z_d) *
                                                           w_xXy *
                                                           z +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z_d) *
                                                           x *
                                                           h_yXz) /
                                                   (w *
                                                      h *
                                                      d);
    }
    public static final float spnoise(float xi, float yi,
                                      float zi,
                                      float ti,
                                      float w,
                                      float h,
                                      float d,
                                      float p) { float x =
                                                   xi %
                                                   w +
                                                   (xi <
                                                      0
                                                      ? w
                                                      : 0);
                                                 float y =
                                                   yi %
                                                   h +
                                                   (yi <
                                                      0
                                                      ? h
                                                      : 0);
                                                 float z =
                                                   zi %
                                                   d +
                                                   (zi <
                                                      0
                                                      ? d
                                                      : 0);
                                                 float t =
                                                   ti %
                                                   p +
                                                   (ti <
                                                      0
                                                      ? p
                                                      : 0);
                                                 float w_x =
                                                   w -
                                                   x;
                                                 float h_y =
                                                   h -
                                                   y;
                                                 float d_z =
                                                   d -
                                                   z;
                                                 float p_t =
                                                   p -
                                                   t;
                                                 float x_w =
                                                   x -
                                                   w;
                                                 float y_h =
                                                   y -
                                                   h;
                                                 float z_d =
                                                   z -
                                                   d;
                                                 float t_p =
                                                   t -
                                                   p;
                                                 float xy =
                                                   x *
                                                   y;
                                                 float d_zXp_t =
                                                   d_z *
                                                   p_t;
                                                 float zXp_t =
                                                   z *
                                                   p_t;
                                                 float zXt =
                                                   z *
                                                   t;
                                                 float d_zXt =
                                                   d_z *
                                                   t;
                                                 float w_xXy =
                                                   w_x *
                                                   y;
                                                 float w_xXh_y =
                                                   w_x *
                                                   h_y;
                                                 float xXh_y =
                                                   x *
                                                   h_y;
                                                 return (snoise(
                                                           x,
                                                           y,
                                                           z,
                                                           t) *
                                                           w_xXh_y *
                                                           d_zXp_t +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z,
                                                             t) *
                                                           xXh_y *
                                                           d_zXp_t +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z,
                                                             t) *
                                                           xy *
                                                           d_zXp_t +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z,
                                                             t) *
                                                           w_xXy *
                                                           d_zXp_t +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z_d,
                                                             t) *
                                                           xy *
                                                           zXp_t +
                                                           snoise(
                                                             x,
                                                             y,
                                                             z_d,
                                                             t) *
                                                           w_xXh_y *
                                                           zXp_t +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z_d,
                                                             t) *
                                                           w_xXy *
                                                           zXp_t +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z_d,
                                                             t) *
                                                           xXh_y *
                                                           zXp_t +
                                                           snoise(
                                                             x,
                                                             y,
                                                             z,
                                                             t_p) *
                                                           w_xXh_y *
                                                           d_zXt +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z,
                                                             t_p) *
                                                           xXh_y *
                                                           d_zXt +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z,
                                                             t_p) *
                                                           xy *
                                                           d_zXt +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z,
                                                             t_p) *
                                                           w_xXy *
                                                           d_zXt +
                                                           snoise(
                                                             x_w,
                                                             y_h,
                                                             z_d,
                                                             t_p) *
                                                           xy *
                                                           zXt +
                                                           snoise(
                                                             x,
                                                             y,
                                                             z_d,
                                                             t_p) *
                                                           w_xXh_y *
                                                           zXt +
                                                           snoise(
                                                             x,
                                                             y_h,
                                                             z_d,
                                                             t_p) *
                                                           w_xXy *
                                                           zXt +
                                                           snoise(
                                                             x_w,
                                                             y,
                                                             z_d,
                                                             t_p) *
                                                           xXh_y *
                                                           zXt) /
                                                   (w *
                                                      h *
                                                      d *
                                                      t);
    }
    public static final float spnoise(Point2 p, float periodx,
                                      float periody) { return spnoise(
                                                                p.
                                                                  x,
                                                                p.
                                                                  y,
                                                                periodx,
                                                                periody);
    }
    public static final float spnoise(Point3 p, Vector3 period) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final float spnoise(Point3 p, float t, Vector3 pperiod,
                                      float tperiod) { return spnoise(
                                                                p.
                                                                  x,
                                                                p.
                                                                  y,
                                                                p.
                                                                  z,
                                                                t,
                                                                pperiod.
                                                                  x,
                                                                pperiod.
                                                                  y,
                                                                pperiod.
                                                                  z,
                                                                tperiod);
    }
    private static final float fade(float t) { return t *
                                                 t *
                                                 t *
                                                 (t *
                                                    (t *
                                                       6 -
                                                       15) +
                                                    10); }
    private static final float lerp(float t, float a, float b) {
        return a +
          t *
          (b -
             a);
    }
    private static final float grad(int hash, float x) { int h =
                                                           hash &
                                                           1;
                                                         return x *
                                                           G1[h];
    }
    private static final float grad(int hash, float x, float y) {
        int h =
          hash &
          3;
        return x *
          G2[h][0] +
          y *
          G2[h][1];
    }
    private static final float grad(int hash, float x, float y,
                                    float z) { int h = hash &
                                                 15;
                                               return x *
                                                 G3[h][0] +
                                                 y *
                                                 G3[h][1] +
                                                 z *
                                                 G3[h][2];
    }
    private static final float grad(int hash, float x, float y,
                                    float z,
                                    float w) { int h = hash &
                                                 31;
                                               return x *
                                                 G4[h][0] +
                                                 y *
                                                 G4[h][1] +
                                                 z *
                                                 G4[h][2] +
                                                 w *
                                                 G4[h][3];
    }
    public PerlinScalar() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163966492000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL2aDZAUxRWAe/b+j4M7DtGTePzcHSKgu+JFjYEY4YpDyCqX" +
                                                    "A6lwJJ5zs713A7Mz40wvLBgkaixQK2opikZyVuIPUVGpVNDElBVSZYI/iRVN" +
                                                    "JLEqStSqYIJUQaX8KUwk7/X07szO7s55k123at7OTP+9r9/r1z0zvfcYqbEt" +
                                                    "Mt80tM3DmsGiNMOi67ULo2yzSe3oiviFfbJl00SPJtv2arg3qHTsa/7o0ztH" +
                                                    "WiKkdoBMkXXdYDJTDd3up7ahbaSJOGl27y7VaMpmpCW+Xt4ox9JM1WJx1WYL" +
                                                    "42SCpygjXfGsCjFQIQYqxLgKscVuLig0kerpVA+WkHVmX0uuJ1Kc1JoKqsfI" +
                                                    "rPxKTNmSU6KaPk4ANdTj9RqA4oUzFpmZY3eYC4DvmR/buevqlp9VkeYB0qzq" +
                                                    "q1AdBZRg0MgAaUrR1BC17MWJBE0MkMk6pYlV1FJlTd3C9R4grbY6rMssbdFc" +
                                                    "J+HNtEkt3qbbc00KsllphRlWDi+pUi2RvapJavIwsJ7usjqEvXgfABtVUMxK" +
                                                    "ygrNFqneoOoJRmb4S+QYu74BGaBoXYqyESPXVLUuww3S6thOk/Xh2Cpmqfow" +
                                                    "ZK0x0tAKI9NKVop9bcrKBnmYDjLS5s/X5yRBrgbeEViEkan+bLwmsNI0n5U8" +
                                                    "9jl25aLbr9Mv1yNc5wRVNNS/HgpN9xXqp0lqUV2hTsGmefF75dOf3xEhBDJP" +
                                                    "9WV28jz73ROXnTv9wItOni8VybNyaD1V2KDy8NCk187qmXtJFapRbxq2isbP" +
                                                    "I+fu3ydSFmZMGHmn52rExGg28UD/79Z+73F6NEIal5NaxdDSKfCjyYqRMlWN" +
                                                    "WsuoTi2Z0cRy0kD1RA9PX07q4Dyu6tS5uzKZtClbTqo1fqvW4NfQRUmoAruo" +
                                                    "Ds5VPWlkz02ZjfDzjEkImQgHaYWjkTg//s/It2NX2eDuMVmRdVU3YuC8VLaU" +
                                                    "kRhVjMEh6N2RlGxtsGNK2mZGKman9aRmbIrZlhIzrOHcdQoai/VRC5Rbpcia" +
                                                    "bEXRy8wK159BvpZNkgRdf5Z/4GswZi43tAS1BpWd6SVLTzw1+EokNxBEzzDS" +
                                                    "Ds1ERTNRbCbqbYZIEq/9NGzOMSqYZAMMbgh7TXNXfWfFNTs6qsCbzE3V0J+Y" +
                                                    "tQPIhA5LFaPHjQDLeZxTwA3bfrJue/STPV933DBWOlwXLU0O3LfphjXbzo+Q" +
                                                    "SH7cRSa41YjF+zBa5qJil3+8Fau3efv7Hz1971bDHXl5gVwEhMKSOKA7/L1v" +
                                                    "GQpNQIh0q583U94/+PzWrgiphigBkZHJ4MkQdKb728gb2AuzQRJZagA4aVgp" +
                                                    "WcOkbGRrZCOWscm9w91iEopWx0PQgD4FeXzt/eWB+/f/cP4lEW8obvZMbqso" +
                                                    "cwb2ZNf+qy1K4f5b9/Xdfc+x7eu48SFHZ7EGulD2wDAHa0CP3fzitW8efvvh" +
                                                    "P0dch2Ew36WHNFXJQB1nu61AENAgEKFZu67SU0ZCTarykEbR7/7TPHvB/g9u" +
                                                    "b3EMpcGdrJ3PHbsC9/6ZS8j3Xrn64+m8GknBScgld7M5HTDFrXmxZcmbUY/M" +
                                                    "Da+3339Q/hHESIhLtrqF8lBDOBnhXR/lFpnL5Xm+tPNRzDQL0jL8Thu/wuXM" +
                                                    "3NLjoxfnUs+4OrlSG7rx3U84UcHIKDKF+MoPxPbuntZz6VFe3nVRLD0jUxhi" +
                                                    "YN3hlr3g8dSHkY7a30ZI3QBpUcSiZo2spdFbBmAit7MrHVj45KXnT8rODLQw" +
                                                    "NwTP8g8PT7P+weGGNjjH3Hje6IwHnmcy9OmZ2MvnwzFBTAX8H1OnmChPy0iE" +
                                                    "n3yFF5nFZReKOdwmVYzUmZa6EWYs8F6br58Y6KHqspZhJLJsARhtdmmjcf9x" +
                                                    "5uPRRztf3Tba+Q50+ACpV21AW2wNF1kgeMoc33v46OsT25/icaR6SLYdSP/K" +
                                                    "qnDhlLce4n3SZGaC/avPUlMwg28US4zY1tbDG3a//6QTt/3O5MtMd+y89VT0" +
                                                    "9p0Rz6Kts2Dd5C3jLNy4ZhMda52CnwTHZ3iglfCGM3G39ojVw8zc8sHkOLOC" +
                                                    "1OJN9B55euuvfrp1u4PRmr9mWQpL8icP/ff30fv+/lKR6RLczZBZbsxKYtrD" +
                                                    "624z6zhXFnecCJ6eg+JS8JNajerDbIRn6kGxzBn4yxmpAtvh6RIzk2sp4lTB" +
                                                    "r6cyEY9wwMDi0dAphrZsmjNZq0Y0t3CHxEyBzhZpz5uqr+Du4Y7oWx974ln2" +
                                                    "2vyvOt00r7SX+AsevPFf01ZfOnLNOCboGT6j+at87Iq9Ly07W7krQqpygaHg" +
                                                    "CSC/0ML8cNBoUXhk0VfnBYXpJv9bUtxeErdXJiCEKwFpvBl4VqhR0D6OOaHP" +
                                                    "ZxSfopamTMYnlS2/OOPni/aMvs3nyAwPSy3OHNGdH8Hasqta+DWViGBqiQiG" +
                                                    "p4tQfC3rjZFlF/Bcl3l7pLSHm2N7OMpvouhHoRe6Ml6uQfGtQt/E63WOKjqK" +
                                                    "awI6elNA2mYUaRRJRwuUI0HdOk88J+BvUolu3TKObu3OMRRvLwpHu2hvSon2" +
                                                    "to2jvS+P0d7FcCwW7U0v0d73P3d7kun1mv4xvOa28XrNLaG95paxvOaugLSd" +
                                                    "KO7Mec0t+V6TGZvCs4IjOCe1l3re5/PRwzfuHE2sfGRBRDS/gpEGZpjnaXQj" +
                                                    "1TxVdTiAOXty83XB0SPs2eO3J1c4wJjneIzp6w/JDYG9vJ4fB3TYQyh242pI" +
                                                    "N1RnQdJbuLAtpX5cqB8vo/oeb+l1GZ4IYHgSxaNhGdYKhrVlZKhyc/X6QPYH" +
                                                    "gDyLYl9YkPUCZH0ZQap5rmofiEvz6wCa36B4LizNNkGzrfwjI7vaOqPwBYoB" +
                                                    "67cLeBMvBZD9AcULYcluEmQ3feFk3byJNwLI3kTxx7Bk2wXZ9sqEg0Ou370T" +
                                                    "wPAeir+FYeiE4zbBcFv5reOqfzRA/WMo/gGrzzDa3yG0v6MyFvCM/A8DED5G" +
                                                    "cTwkwt0C4e4yIgTE41OlOSRusZMhOXYJjl1l5BgjHEsNATATUFSHhHlAwDxQ" +
                                                    "mVHxMtdwSoD2U1FMCqn9g0L7Byuj/SGuYXuA9jNQtIXU/iGh/UOVGdNuVJXm" +
                                                    "BCDMRdERAgEnhj0CYU9lEDwjYEEAQjeKc/G9dRiGvYJh7xc4nhcG0GCN0kVh" +
                                                    "aZ4RNM+UkaaW56otRuPjWhrAtQzFZWG5Dgqug2Xkque56oO5fIQrAwjxKVla" +
                                                    "EZbwsCA8XEZCz/z4sg9kbQDIOhSrw4K8J0Deq0xQOJRdDLcVLIbXUPwKyFfD" +
                                                    "UsCLQGkYxdVh+Y4IviOVCRiHsoaSEq61jACaa1GsD0MzG46jguZoZazl8bjN" +
                                                    "AQzXoWCM1NmhII4LiOOVMUnRSHBTAM7NKK4PjXNS4JwsI87nD+I/CAC7A8WO" +
                                                    "sGCS+L4nFXzf+z/AwkTxXQGI96O4KzRip0DsLCNiQBgPeAco4TtAaXdokjmC" +
                                                    "ZE5lIgPGOSnBFQ14CSjhS0Dp0dAQ8wXE/MpEhqLBOuBVoISvAqV948WZgTfB" +
                                                    "o6SYwImNF2dRaRz/Wwwp4OWfhC//pOcYqU7KifEq3y2U7y6j8qXfAEgBb/qk" +
                                                    "V1C8ABgatcxxYlwsMC4uI4ZnXPS7BH8KIHgDxatAMGzJiXESLBIEiypjiH6f" +
                                                    "Id4KwDiM4q/hMMRHM+e/TBiese3BcFmOBLD8E8W74Vh6BUtvGVlqeK6aYiwu" +
                                                    "0IkAoH+j+CAYKMNIk3d/In5RbyvY8uxs01WeGm2uP2P0qr84O2WyW2kb4qQ+" +
                                                    "mdY071Yhz3mtadGkyjuiwdk4xD8oSp8w0uJ/BABd8Q+1lD52sn3KyARPNgy9" +
                                                    "zpk302eMVEEmPD3lMMIDRou7o8PZApUhPCn7GdHMu8rbcYc7Mvj28OzuibSz" +
                                                    "QXxQeXp0xZXXnbjoEb4Vo0bR5C1bsJb6OKlz9hHySnEHxqyStWXrqr187qeT" +
                                                    "9jXMzn6mzNth6NUNz0f+By3H8i+MLwAA");
}
