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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL2aDYwU1R3A3+x9Hwd3HAIn5Ti4O0Q+3AWuSi1UhQuH0FXO" +
                                                    "A0k5Uo652bd3A7Mzw+wsLFBEsQbUVA2gaKVnWj+oikqaIq2NKU1sEWlNtZXW" +
                                                    "pEqVpNgiCaRRCLTS///N253Z2d05b7rrJvPf+Xgf/9/7f7y3s+/AWVIWN8h0" +
                                                    "XVM29SmaGaRJM7hWuT5obtJpPLgkfH2naMRppF0R4/HlcK9Haj5Y+/nlR/rr" +
                                                    "AqS8m4wSVVUzRVPW1HgXjWvKBhoJk1r77kKFxuImqQuvFTeIoYQpK6GwHDfn" +
                                                    "hskwR1WTtIZTKoRAhRCoEGIqhObbpaDScKomYu1YQ1TN+HpyFxHCpFyXUD2T" +
                                                    "TMpsRBcNMcab6WQE0EIlXq8AKFY5aZCJaXaLOQv40emhPXtX1/2shNR2k1pZ" +
                                                    "XYbqSKCECZ10k5oYjfVSIz4/EqGRbjJSpTSyjBqyqMibmd7dpD4u96mimTBo" +
                                                    "epDwZkKnBuvTHrkaCdmMhGRqRhovKlMlkroqiypiH7COsVktwg68D4DVMihm" +
                                                    "REWJpqqUrpPViEma3DXSjK3fhgJQtSJGzX4t3VWpKsINUm/ZThHVvtAy05DV" +
                                                    "PihapiWgF5OMy9sojrUuSuvEPtpjkgZ3uU7rEZSqYgOBVUwy2l2MtQRWGuey" +
                                                    "ksM+Z2+f99AW9VY1wHSOUElB/Suh0gRXpS4apQZVJWpVrJkWfkwc8/rOACFQ" +
                                                    "eLSrsFXm8PfO3zJjwpE3rTJfy1Fmae9aKpk90jO9I94Z3z71xhJUo1LX4jIa" +
                                                    "P4OcuX8nfzI3qUPkjUm3iA+DqYdHun638u4X6JkAqV5MyiVNScTAj0ZKWkyX" +
                                                    "FWosoio1RJNGFpMqqkba2fPFpALOw7JKrbtLo9E4NReTUoXdKtfYNQxRFJrA" +
                                                    "IaqAc1mNaqlzXTT72XlSJ4QMh4PUw1FNrA/7NskdoX4tRkOiJKqyqoXAd6lo" +
                                                    "SP0hKmmhuBjTFbBaPKFGFW1jKG5IIc3oS1/HoIdQJzVAo2WSqIhGEF1LL0aj" +
                                                    "SSSp2ygIMMjj3SGuQHTcqikRavRIexILFp5/ued4IO3yfAxM0gjdBHk3Qewm" +
                                                    "6OyGCAJr/SrszjIfDP46CGNIcDVTl313yZqdzSXgN/rGUhg5LNoMPFyHhZLW" +
                                                    "bsf6YpbRJHC4hp+s2hG8uP9my+FC+RNzztrkyOMb71mxbWaABDIzLDLBrWqs" +
                                                    "3ol5MZ3/Wt2Rlavd2h2ffP7KY1s1O8YyUjYP/eyaGLrN7tE3NIlGIBnazU+b" +
                                                    "KB7qeX1ra4CUQj6AHGiK4LOQXia4+8gI4bmpdIgsZQAc1YyYqOCjVA6rNvsN" +
                                                    "baN9h7nFCBT1loegAV0Kskza8csjTxz64fQbA86kW+uYxpZR0wrhkbb9lxuU" +
                                                    "wv0PHu/c/ejZHauY8aFES64OWlG2Q0CDNWDE7ntz/fsnP3zmzwHbYUyY2RK9" +
                                                    "iiwloY1r7F4g3BVIOWjW1jvVmBaRo7LYq1D0u//UTp516NOH6ixDKXAnZecZ" +
                                                    "gzdg3796Abn7+OoLE1gzgoTTjU1uF7MGYJTd8nzDEDehHsl73m184qj4I8iG" +
                                                    "kIHi8mbKkgphZIQNfZBZZCqT17mezUQxUc96lmR3GtgVLlym5o+PDpw1HXF1" +
                                                    "aanSu/3ji4woKzJyTBau+t2hA/vGtd90htW3XRRrNyWzUwysMOy6s1+IfRZo" +
                                                    "Lv9tgFR0kzqJL19WiEoCvaUbpux4ak0DS5yM55nTrzXXzE2H4Hh3eDi6dQeH" +
                                                    "ndrgHEvjebUVD6zMSBjTq3GUZ8IxjCd99o1PR+kor0oKhJ18g1WZxGQriinM" +
                                                    "JiUmqdANeQPMTeC9cbZSMkEPWRWVpEkCi2aB0SbnNxrzH2vmHXiu5e1tAy0f" +
                                                    "wYB3k0o5Dmjzjb4cSwFHnXMHTp55d3jjyyyPlPaKcQvSvYbKXiJlrHzYmNTo" +
                                                    "SW//6jTkGMzVG/hiIrS1/uS6fZ+8ZOVttzO5CtOdex64EnxoT8CxPGvJWiE5" +
                                                    "61hLNKbZcMtaV+AjwPEFHmglvGFN0fXtfJ0wMb1Q0BnOJC+1WBcdp1/Z+quf" +
                                                    "bt1hYdRnrk4WwuL7pRP//X3w8b8fyzFdgrtpopmOWYFPe3jdpqcc5/bcjhPA" +
                                                    "02tR3AR+Uq5Qtc/sZ4XaUSyyAn+xSUrAdni6QE+mewpYTbDr0SbPRxgwsEzU" +
                                                    "VIqpLfXMmqxlLZheosPDZJbOBmnMmKpvY+5hR/QDz7942Hxn+jetYZqW30vc" +
                                                    "FY9u/9e45Tf1rxnCBN3kMpq7yedvO3Bs0TXSrgApSSeGrLV+ZqW5memg2qDw" +
                                                    "40RdnpEUJujsa0FuewnMXkmPFC55PGPdwK+CMgntY5kTxrwp9xS1MKabbFLZ" +
                                                    "/IuxP5+3f+BDNkcmWVqqs+aItswM1pBav8KnJk8Gk/NkMDydh+JbKW8MLJrN" +
                                                    "St3iHJH8Hq4P7uEo70DRhULNdmW8XIHiO9m+iderLFVUFGs8Bnqjx7NNKBIo" +
                                                    "opYWKPu9hnUa/0WAnxF5hnXzEIa1Lc2Qu78gHI28v1F5+ts2hP6+Pkh/c+CY" +
                                                    "z/ubkKe/73/p/gTd6TVdg3jNg0P1mvt9e839g3nNLo9ne1A8kvaa+zO9Jjk4" +
                                                    "hWMFR3BOasz3y57NR89s3zMQWfrsrADvfolJqkxNv06hG6jiaKrZAkzbk5mv" +
                                                    "FY52bs92tz2Zwh7GvNZhTNd4CHYK7GDt/NhjwJ5GsQ9XQ6omWwuSjuyFbT71" +
                                                    "w1z9cAHVd3hLh83wogfDSyie88uwkjOsLCBDiV2qwwVyyAPkMIqDfkHWcpC1" +
                                                    "BQQpZaVKXSA2za89aH6D4jW/NNs4zbbCR0ZqtTU2+wWKBuu32ayLYx5kf0Dx" +
                                                    "hl+yeznZvV85WRvr4j0PsvdR/NEv2Q5OtqM46eCE7XcfeTCcQvE3PwwtcDzI" +
                                                    "GR4svHVs9c94qH8WxT9g9elH+4e59g8XxwKOyP/MA+ECinM+EXZzhN0FRPDI" +
                                                    "x1fycwjMYpd8cuzlHHsLyDFIOhaqPGCGoSj1CfMkh3myOFHxFtNwlIf2o1GM" +
                                                    "8Kn9U1z7p4qj/QmmYaOH9k0oGnxq/zTX/unixLSdVYUpHghTUTT7QMCJYT9H" +
                                                    "2F8cBEcEzPJAaEMxA99b+2E4wBkOfIXxPNeDBlsUbvBL8yqnebWANOWsVHku" +
                                                    "GhfXQg+uRShu8ct1lHMdLSBXJStV6c3lIlzqQYi/koUlfglPcsKTBSR0zI9v" +
                                                    "uUBWeoCsQrHcL8gpDnKqOEnhRGox3JC1GF5B8V9AthoWPF4ECn0oVvvlO835" +
                                                    "ThcnYZxIGUqI2NbSPGjWo1jrh2YyHGc4zZniWMvhcZs8GLagME1SEfcFcY5D" +
                                                    "nCuOSXJmgns9cO5DcZdvnEsc51IBcb58Ev+BB9jDKHb6BRP4/3tC1v97/weY" +
                                                    "nyy+1wPxCRS7fCO2cMSWAiJ6pHGPd4ACvgMU9vkmmcJJphQnM2CeEyJMUY+X" +
                                                    "gAK+BBSe8w0xnUNML05myJmsPV4FCvgqUDg4VJwmvAkeJYQ4TmioOPPy47jf" +
                                                    "YggeL/8EfPknvGaS0qgYGarybVz5tgIqn/8NgODxpk84juINwFCooQ8RYw7H" +
                                                    "mFNADEdcdNkEf/IgeA/F20DQZ4iRIRLM4wTzimOILpchPvDAOInir/4w+J9m" +
                                                    "1neBMByx7cCwWU57sPwTxcf+WDo4S0cBWcpYqbJcLDbQeQ+gf6P41BsoaZIa" +
                                                    "5/5E/Ee9IWtzs7UhV3p5oLZy7MCdf7F2yqQ2zVaFSWU0oSjOrUKO83LdoFGZ" +
                                                    "DUSVtXGI/aEoXDRJnfsnAOiKX6ilcMEqdtkkwxzFMPVaZ85CX5ikBArh6RWL" +
                                                    "EX5g1Nk7OqwtUEnCHqX+RtQzrjJ23OGODLYRPLV7ImFtBe+RXhlYcvuW8zc8" +
                                                    "y7ZilEmKuHkztlIZJhXWPkLWKO7AmJS3tVRb5bdOvTziYNXk1N+UGTsMnbrh" +
                                                    "ef//ADOiMpF2LwAA");
}
