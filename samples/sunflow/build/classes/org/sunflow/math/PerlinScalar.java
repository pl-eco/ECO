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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL2aDZAUxRWAe/b+j4M7DtGTePzcHSKgu+JFjYEY4OoOIatc" +
                                                    "DqTCUfGcm+29G5idGWd7YcEg/sQCtCIWomgkZyUiREWlUkETU1ZIlQn+JFY0" +
                                                    "kcSqKFGrgglSBZXypzCRvNfTuzM7uzvnTXbdqnk7M/33vn6vX/fM9IGTpCpp" +
                                                    "kbmmoW0c0gwWpmkWXqtdHmYbTZoML4te3itbSRrr0uRkciXcG1DaDjZ+/Nm9" +
                                                    "w00hUt1PJsm6bjCZqYae7KNJQ1tPY1HS6Nzt1mgiyUhTdK28Xo6kmKpFomqS" +
                                                    "zY+Sca6ijHREMypEQIUIqBDhKkQWObmg0HiqpxJdWELWWfImcguRoqTaVFA9" +
                                                    "RmbkVmLKlpwQ1fRyAqihFq9XARQvnLbI9Cy7zZwHfP/cyK7dNzT9rII09pNG" +
                                                    "VV+B6iigBING+klDgiYGqZVcFIvRWD+ZqFMaW0EtVdbUTVzvftKcVId0maUs" +
                                                    "mu0kvJkyqcXbdHquQUE2K6Uww8rixVWqxTJXVXFNHgLWcx1Wm7AH7wNgvQqK" +
                                                    "WXFZoZkiletUPcbING+JLGPHtyADFK1JUDZsZJuq1GW4QZpt22myPhRZwSxV" +
                                                    "H4KsVUYKWmFkStFKsa9NWVknD9EBRlq8+XrtJMhVxzsCizAy2ZuN1wRWmuKx" +
                                                    "kss+J69bcM/N+jV6iOsco4qG+tdCoameQn00Ti2qK9Qu2DAn+oB87gvbQoRA" +
                                                    "5smezHae5753euHFUw+/ZOf5SoE8ywfXUoUNKHsHJ7x+QdfsqypQjVrTSKpo" +
                                                    "/Bxy7v69ImV+2oSRd262RkwMZxIP9/1u9a1P0BMhUr+UVCuGlkqAH01UjISp" +
                                                    "atRaQnVqyYzGlpI6qse6ePpSUgPnUVWn9t3l8XiSsqWkUuO3qg1+DV0Uhyqw" +
                                                    "i2rgXNXjRubclNkwP0+bhJDxcJBmOOqJ/eP/jMQiw0aCRmRF1lXdiIDvUtlS" +
                                                    "hiNUMQYsahqR7q7lkUHo5eGEbK1LRpIpPa4ZGwaUVJIZiUjSUiKGNZS5HUlA" +
                                                    "o5FeaoGSKxRZk60wepv5JbWTRt6mDZIEprjAGwg0GEPXGFqMWgPKrtTi7tNP" +
                                                    "D7wayg4M0VOMtEIzYdFMGJsJu5shksRrPwebs40MJloHgx3CYMPsFd9dduO2" +
                                                    "tgrwLnNDJfQvZm0DRKFDt2J0ORFhKY97Crhly0/WbA1/uv+btltGiofvgqXJ" +
                                                    "4Qc33LZqy6UhEsqNw8gEt+qxeC9Gz2yU7PCOv0L1Nm794ONnHthsOCMxJ7CL" +
                                                    "AJFfEgd4m7f3LUOhMQiZTvVzpsuHBl7Y3BEilRA1IFIyGTwbgtBUbxs5A31+" +
                                                    "JmgiSxUAxw0rIWuYlIl09WzYMjY4d7hbTEDRbHsIGtCjII+3Pb88/NChH869" +
                                                    "KuQOzY2uyW4FZfZAn+jYf6VFKdx/+8He++4/uXUNNz7kaC/UQAfKLhj2YA3o" +
                                                    "sTtfuumtY+/s/XPIcRgG819qUFOVNNRxodMKBAUNAhOateN6PWHE1LgqD2oU" +
                                                    "/e4/jTPnHfrwnibbUBrcydj54tErcO6fv5jc+uoNn0zl1UgKTkoOuZPN7oBJ" +
                                                    "Ts2LLEveiHqkb3uj9aEj8o8gZkKcSqqbKA89hJMR3vVhbpHZXF7iSbsUxXQz" +
                                                    "Ly3N77TwK1zezC4+PnpwbnWNqzPLtcHb3/uUE+WNjAJTiqd8f+TAnildV5/g" +
                                                    "5R0XxdLT0vkhBtYhTtnLnkh8FGqr/m2I1PSTJkUsclbJWgq9pR8m9mRm5QML" +
                                                    "oZz03EnanpHmZ4fgBd7h4WrWOzic0AbnmBvP6+3xwPNMhD49H3v5UjjGiamB" +
                                                    "/2PqJBPlOWmJ8JOv8SIzuOxAMYvbpIKRGtNS18MMBt6b5OspBnqouqylGQkt" +
                                                    "mQdGm1ncaNx/7Pl5ZF/7a1tG2t+FDu8ntWoS0BZZQwUWDK4ypw4cO/HG+Nan" +
                                                    "eRypHJSTNqR3pZW/kMpZH/E+aTDT/v7Va6kJmNHXiyVHZHPzsXV7PnjKjtte" +
                                                    "Z/Jkptt23XU2fM+ukGsR1563jnKXsRdyXLPxtrXOwk+C43M80Ep4w57Im7vE" +
                                                    "amJ6djlhcpwZfmrxJnqOP7P5Vz/dvNXGaM5dw3TDEv2po//9ffjBv79cYLoE" +
                                                    "dzNklh2zkpj28LrTzDjOdYUdJ4SnF6G4GvykWqP6EBvmmbpQLLEH/lJGKsB2" +
                                                    "eLrYTGdbCtlV8OvJTMQjHDCwmDR0iqEtk2ZP1qoRzi7kITGdp7NFWnOm6mu5" +
                                                    "ezgj+q7Hn3yOvT7363Y3zSnuJd6CR27/15SVVw/fOIYJeprHaN4qH7/2wMtL" +
                                                    "LlR2hkhFNjDkPRHkFpqfGw7qLQqPMPrKnKAw1eR/iwvbS+L2SvuEcMUnjTcD" +
                                                    "zw5VCtrHNif0+bTCU1R3wmR8Utn0i/N+vmD/yDt8jkzzsNRkzxGduRGsJbPK" +
                                                    "hV9DkQimFolgeLoAxTcy3hhachnPtdDdI8U93Bzdw1F+G0UfCj3flfFyFYrv" +
                                                    "5PsmXq+xVdFR3OjT0Rt80jaiSKGI21qgHPbr1jniuQF/E4p066YxdGtnlqFw" +
                                                    "e2E4WkV7k4q0t2UM7X11lPauhGORaG9qkfa+/4Xbk0y31/SN4jV3j9Vrtgf2" +
                                                    "mu2jec1On7RdKO7Nes32XK9Jj07hWsERnJNaiz3/8/lo7+27RmLLH5sXEs0v" +
                                                    "Y6SOGeYlGl1PNVdVbTZg1p7cfB1wdAl7dnntyRX2MeZFLmN6+kNyQmAPr+fH" +
                                                    "Ph32KIo9uBrSDdVekPTkL2yLqR8V6kdLqL7LW3ochid9GJ5CsS8ow2rBsLqE" +
                                                    "DBVOrh4PyCEfkOdQHAwKslaArC0hSCXPVekBcWh+7UPzGxTPB6XZImi2lH5k" +
                                                    "ZFZb5+W/QDFg/XYZb+JlH7I/oHgxKNkdguyOL52skzfxpg/ZWyj+GJRsqyDb" +
                                                    "Wp5wcNTxu3d9GN5H8bcgDO1w3C0Y7i69dRz1T/iofxLFP2D1GUT7HUL7HeWx" +
                                                    "gGvkf+SD8AmKUwER7hMI95UQwSceny3OIXGLnQnIsVtw7C4hxyjhWKrzgRmH" +
                                                    "ojIgzMMC5uHyjIpXuIaTfLSfjGJCQO0fEdo/Uh7tj3INW320n4aiJaD2jwrt" +
                                                    "Hy3PmHaiqjTLB2E2irYACDgx7BcI+8uD4BoB83wQOlFcjO+tgzAcEAwHvsTx" +
                                                    "PN+HBmuUrghK86ygebaENNU8V3UhGg9Xtw/XEhQLg3IdEVxHSshVy3PV+nN5" +
                                                    "CJf7EOJTsrQsKOExQXishISu+fEVD8hqH5A1KFYGBXlfgLxfnqBwNLMYbslb" +
                                                    "DK+i+BWQr4YlnxeB0hCKG4LyHRd8x8sTMI5mDCXFHGsZPjQ3oVgbhGYmHCcE" +
                                                    "zYnyWMvlcRt9GG5GwRipSQaCOCUgTpXHJAUjwR0+OHeiuCUwzhmBc6aEOF88" +
                                                    "iP/AB2wHim1BwSTxfU/K+773f4AFieK7fRAfQrEzMGK7QGwvIaJPGPd5Byjh" +
                                                    "O0BpT2CSWYJkVnkiA8Y5KcYV9XkJKOFLQGlfYIi5AmJueSJDwWDt8ypQwleB" +
                                                    "0sGx4kzDm+BRUkTgRMaKs6A4jvcthuTz8k/Cl3/S84xUxuXYWJXvFMp3llD5" +
                                                    "4m8AJJ83fdKrKF4EDI1a5hgxrhQYV5YQwzUu+hyCP/kQvIniNSAYsuTYGAkW" +
                                                    "CIIF5TFEn8cQb/tgHEPx12AY4qOZ/V8iDNfYdmE4LMd9WP6J4r1gLD2CpaeE" +
                                                    "LFU8V1UhFgfotA/Qv1F86A+UZqTBvT8Rv6i35G2BtrftKk+PNNaeN3L9X+yd" +
                                                    "MpmttXVRUhtPaZp7q5DrvNq0aFzlHVFnbxziHxSlTxlp8j4CgK74h1pKn9jZ" +
                                                    "PmNknCsbhl77zJ3pc0YqIBOenrUZ4QGjydnRYW+BShOelPmMaOZc5ey4wx0Z" +
                                                    "fLt4ZvdEyt4wPqA8M7LsuptPX/EY34pRpWjypk1YS22U1Nj7CHmluANjRtHa" +
                                                    "MnVVXzP7swkH62ZmPlPm7DB064bnw/8D8eqTMpwvAAA=");
}
