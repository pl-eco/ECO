package org.sunflow.core.tesselatable;
public class Teapot extends BezierMesh {
    final private static float[][] PATCHES = { { -80.0F, 0.0F, 30.0F, -80.0F,
    -44.8F,
    30.0F,
    -44.8F,
    -80.0F,
    30.0F,
    0.0F,
    -80.0F,
    30.0F,
    -80.0F,
    0.0F,
    12.0F,
    -80.0F,
    -44.8F,
    12.0F,
    -44.8F,
    -80.0F,
    12.0F,
    0.0F,
    -80.0F,
    12.0F,
    -60.0F,
    0.0F,
    3.0F,
    -60.0F,
    -33.6F,
    3.0F,
    -33.6F,
    -60.0F,
    3.0F,
    0.0F,
    -60.0F,
    3.0F,
    -60.0F,
    0.0F,
    0.0F,
    -60.0F,
    -33.6F,
    0.0F,
    -33.6F,
    -60.0F,
    0.0F,
    0.0F,
    -60.0F,
    0.0F },
    { 0.0F,
    -80.0F,
    30.0F,
    44.8F,
    -80.0F,
    30.0F,
    80.0F,
    -44.8F,
    30.0F,
    80.0F,
    0.0F,
    30.0F,
    0.0F,
    -80.0F,
    12.0F,
    44.8F,
    -80.0F,
    12.0F,
    80.0F,
    -44.8F,
    12.0F,
    80.0F,
    0.0F,
    12.0F,
    0.0F,
    -60.0F,
    3.0F,
    33.6F,
    -60.0F,
    3.0F,
    60.0F,
    -33.6F,
    3.0F,
    60.0F,
    0.0F,
    3.0F,
    0.0F,
    -60.0F,
    0.0F,
    33.6F,
    -60.0F,
    0.0F,
    60.0F,
    -33.6F,
    0.0F,
    60.0F,
    0.0F,
    0.0F },
    { -60.0F,
    0.0F,
    90.0F,
    -60.0F,
    -33.6F,
    90.0F,
    -33.6F,
    -60.0F,
    90.0F,
    0.0F,
    -60.0F,
    90.0F,
    -70.0F,
    0.0F,
    69.0F,
    -70.0F,
    -39.2F,
    69.0F,
    -39.2F,
    -70.0F,
    69.0F,
    0.0F,
    -70.0F,
    69.0F,
    -80.0F,
    0.0F,
    48.0F,
    -80.0F,
    -44.8F,
    48.0F,
    -44.8F,
    -80.0F,
    48.0F,
    0.0F,
    -80.0F,
    48.0F,
    -80.0F,
    0.0F,
    30.0F,
    -80.0F,
    -44.8F,
    30.0F,
    -44.8F,
    -80.0F,
    30.0F,
    0.0F,
    -80.0F,
    30.0F },
    { 0.0F,
    -60.0F,
    90.0F,
    33.6F,
    -60.0F,
    90.0F,
    60.0F,
    -33.6F,
    90.0F,
    60.0F,
    0.0F,
    90.0F,
    0.0F,
    -70.0F,
    69.0F,
    39.2F,
    -70.0F,
    69.0F,
    70.0F,
    -39.2F,
    69.0F,
    70.0F,
    0.0F,
    69.0F,
    0.0F,
    -80.0F,
    48.0F,
    44.8F,
    -80.0F,
    48.0F,
    80.0F,
    -44.8F,
    48.0F,
    80.0F,
    0.0F,
    48.0F,
    0.0F,
    -80.0F,
    30.0F,
    44.8F,
    -80.0F,
    30.0F,
    80.0F,
    -44.8F,
    30.0F,
    80.0F,
    0.0F,
    30.0F },
    { -56.0F,
    0.0F,
    90.0F,
    -56.0F,
    -31.36F,
    90.0F,
    -31.36F,
    -56.0F,
    90.0F,
    0.0F,
    -56.0F,
    90.0F,
    -53.5F,
    0.0F,
    95.25F,
    -53.5F,
    -29.96F,
    95.25F,
    -29.96F,
    -53.5F,
    95.25F,
    0.0F,
    -53.5F,
    95.25F,
    -57.5F,
    0.0F,
    95.25F,
    -57.5F,
    -32.2F,
    95.25F,
    -32.2F,
    -57.5F,
    95.25F,
    0.0F,
    -57.5F,
    95.25F,
    -60.0F,
    0.0F,
    90.0F,
    -60.0F,
    -33.6F,
    90.0F,
    -33.6F,
    -60.0F,
    90.0F,
    0.0F,
    -60.0F,
    90.0F },
    { 0.0F,
    -56.0F,
    90.0F,
    31.36F,
    -56.0F,
    90.0F,
    56.0F,
    -31.36F,
    90.0F,
    56.0F,
    0.0F,
    90.0F,
    0.0F,
    -53.5F,
    95.25F,
    29.96F,
    -53.5F,
    95.25F,
    53.5F,
    -29.96F,
    95.25F,
    53.5F,
    0.0F,
    95.25F,
    0.0F,
    -57.5F,
    95.25F,
    32.2F,
    -57.5F,
    95.25F,
    57.5F,
    -32.2F,
    95.25F,
    57.5F,
    0.0F,
    95.25F,
    0.0F,
    -60.0F,
    90.0F,
    33.6F,
    -60.0F,
    90.0F,
    60.0F,
    -33.6F,
    90.0F,
    60.0F,
    0.0F,
    90.0F },
    { 80.0F,
    0.0F,
    30.0F,
    80.0F,
    44.8F,
    30.0F,
    44.8F,
    80.0F,
    30.0F,
    0.0F,
    80.0F,
    30.0F,
    80.0F,
    0.0F,
    12.0F,
    80.0F,
    44.8F,
    12.0F,
    44.8F,
    80.0F,
    12.0F,
    0.0F,
    80.0F,
    12.0F,
    60.0F,
    0.0F,
    3.0F,
    60.0F,
    33.6F,
    3.0F,
    33.6F,
    60.0F,
    3.0F,
    0.0F,
    60.0F,
    3.0F,
    60.0F,
    0.0F,
    0.0F,
    60.0F,
    33.6F,
    0.0F,
    33.6F,
    60.0F,
    0.0F,
    0.0F,
    60.0F,
    0.0F },
    { 0.0F,
    80.0F,
    30.0F,
    -44.8F,
    80.0F,
    30.0F,
    -80.0F,
    44.8F,
    30.0F,
    -80.0F,
    0.0F,
    30.0F,
    0.0F,
    80.0F,
    12.0F,
    -44.8F,
    80.0F,
    12.0F,
    -80.0F,
    44.8F,
    12.0F,
    -80.0F,
    0.0F,
    12.0F,
    0.0F,
    60.0F,
    3.0F,
    -33.6F,
    60.0F,
    3.0F,
    -60.0F,
    33.6F,
    3.0F,
    -60.0F,
    0.0F,
    3.0F,
    0.0F,
    60.0F,
    0.0F,
    -33.6F,
    60.0F,
    0.0F,
    -60.0F,
    33.6F,
    0.0F,
    -60.0F,
    0.0F,
    0.0F },
    { 60.0F,
    0.0F,
    90.0F,
    60.0F,
    33.6F,
    90.0F,
    33.6F,
    60.0F,
    90.0F,
    0.0F,
    60.0F,
    90.0F,
    70.0F,
    0.0F,
    69.0F,
    70.0F,
    39.2F,
    69.0F,
    39.2F,
    70.0F,
    69.0F,
    0.0F,
    70.0F,
    69.0F,
    80.0F,
    0.0F,
    48.0F,
    80.0F,
    44.8F,
    48.0F,
    44.8F,
    80.0F,
    48.0F,
    0.0F,
    80.0F,
    48.0F,
    80.0F,
    0.0F,
    30.0F,
    80.0F,
    44.8F,
    30.0F,
    44.8F,
    80.0F,
    30.0F,
    0.0F,
    80.0F,
    30.0F },
    { 0.0F,
    60.0F,
    90.0F,
    -33.6F,
    60.0F,
    90.0F,
    -60.0F,
    33.6F,
    90.0F,
    -60.0F,
    0.0F,
    90.0F,
    0.0F,
    70.0F,
    69.0F,
    -39.2F,
    70.0F,
    69.0F,
    -70.0F,
    39.2F,
    69.0F,
    -70.0F,
    0.0F,
    69.0F,
    0.0F,
    80.0F,
    48.0F,
    -44.8F,
    80.0F,
    48.0F,
    -80.0F,
    44.8F,
    48.0F,
    -80.0F,
    0.0F,
    48.0F,
    0.0F,
    80.0F,
    30.0F,
    -44.8F,
    80.0F,
    30.0F,
    -80.0F,
    44.8F,
    30.0F,
    -80.0F,
    0.0F,
    30.0F },
    { 56.0F,
    0.0F,
    90.0F,
    56.0F,
    31.36F,
    90.0F,
    31.36F,
    56.0F,
    90.0F,
    0.0F,
    56.0F,
    90.0F,
    53.5F,
    0.0F,
    95.25F,
    53.5F,
    29.96F,
    95.25F,
    29.96F,
    53.5F,
    95.25F,
    0.0F,
    53.5F,
    95.25F,
    57.5F,
    0.0F,
    95.25F,
    57.5F,
    32.2F,
    95.25F,
    32.2F,
    57.5F,
    95.25F,
    0.0F,
    57.5F,
    95.25F,
    60.0F,
    0.0F,
    90.0F,
    60.0F,
    33.6F,
    90.0F,
    33.6F,
    60.0F,
    90.0F,
    0.0F,
    60.0F,
    90.0F },
    { 0.0F,
    56.0F,
    90.0F,
    -31.36F,
    56.0F,
    90.0F,
    -56.0F,
    31.36F,
    90.0F,
    -56.0F,
    0.0F,
    90.0F,
    0.0F,
    53.5F,
    95.25F,
    -29.96F,
    53.5F,
    95.25F,
    -53.5F,
    29.96F,
    95.25F,
    -53.5F,
    0.0F,
    95.25F,
    0.0F,
    57.5F,
    95.25F,
    -32.2F,
    57.5F,
    95.25F,
    -57.5F,
    32.2F,
    95.25F,
    -57.5F,
    0.0F,
    95.25F,
    0.0F,
    60.0F,
    90.0F,
    -33.6F,
    60.0F,
    90.0F,
    -60.0F,
    33.6F,
    90.0F,
    -60.0F,
    0.0F,
    90.0F },
    { -64.0F,
    0.0F,
    75.0F,
    -64.0F,
    12.0F,
    75.0F,
    -60.0F,
    12.0F,
    84.0F,
    -60.0F,
    0.0F,
    84.0F,
    -92.0F,
    0.0F,
    75.0F,
    -92.0F,
    12.0F,
    75.0F,
    -100.0F,
    12.0F,
    84.0F,
    -100.0F,
    0.0F,
    84.0F,
    -108.0F,
    0.0F,
    75.0F,
    -108.0F,
    12.0F,
    75.0F,
    -120.0F,
    12.0F,
    84.0F,
    -120.0F,
    0.0F,
    84.0F,
    -108.0F,
    0.0F,
    66.0F,
    -108.0F,
    12.0F,
    66.0F,
    -120.0F,
    12.0F,
    66.0F,
    -120.0F,
    0.0F,
    66.0F },
    { -60.0F,
    0.0F,
    84.0F,
    -60.0F,
    -12.0F,
    84.0F,
    -64.0F,
    -12.0F,
    75.0F,
    -64.0F,
    0.0F,
    75.0F,
    -100.0F,
    0.0F,
    84.0F,
    -100.0F,
    -12.0F,
    84.0F,
    -92.0F,
    -12.0F,
    75.0F,
    -92.0F,
    0.0F,
    75.0F,
    -120.0F,
    0.0F,
    84.0F,
    -120.0F,
    -12.0F,
    84.0F,
    -108.0F,
    -12.0F,
    75.0F,
    -108.0F,
    0.0F,
    75.0F,
    -120.0F,
    0.0F,
    66.0F,
    -120.0F,
    -12.0F,
    66.0F,
    -108.0F,
    -12.0F,
    66.0F,
    -108.0F,
    0.0F,
    66.0F },
    { -108.0F,
    0.0F,
    66.0F,
    -108.0F,
    12.0F,
    66.0F,
    -120.0F,
    12.0F,
    66.0F,
    -120.0F,
    0.0F,
    66.0F,
    -108.0F,
    0.0F,
    57.0F,
    -108.0F,
    12.0F,
    57.0F,
    -120.0F,
    12.0F,
    48.0F,
    -120.0F,
    0.0F,
    48.0F,
    -100.0F,
    0.0F,
    39.0F,
    -100.0F,
    12.0F,
    39.0F,
    -106.0F,
    12.0F,
    31.5F,
    -106.0F,
    0.0F,
    31.5F,
    -80.0F,
    0.0F,
    30.0F,
    -80.0F,
    12.0F,
    30.0F,
    -76.0F,
    12.0F,
    18.0F,
    -76.0F,
    0.0F,
    18.0F },
    { -120.0F,
    0.0F,
    66.0F,
    -120.0F,
    -12.0F,
    66.0F,
    -108.0F,
    -12.0F,
    66.0F,
    -108.0F,
    0.0F,
    66.0F,
    -120.0F,
    0.0F,
    48.0F,
    -120.0F,
    -12.0F,
    48.0F,
    -108.0F,
    -12.0F,
    57.0F,
    -108.0F,
    0.0F,
    57.0F,
    -106.0F,
    0.0F,
    31.5F,
    -106.0F,
    -12.0F,
    31.5F,
    -100.0F,
    -12.0F,
    39.0F,
    -100.0F,
    0.0F,
    39.0F,
    -76.0F,
    0.0F,
    18.0F,
    -76.0F,
    -12.0F,
    18.0F,
    -80.0F,
    -12.0F,
    30.0F,
    -80.0F,
    0.0F,
    30.0F },
    { 68.0F,
    0.0F,
    51.0F,
    68.0F,
    26.4F,
    51.0F,
    68.0F,
    26.4F,
    18.0F,
    68.0F,
    0.0F,
    18.0F,
    104.0F,
    0.0F,
    51.0F,
    104.0F,
    26.4F,
    51.0F,
    124.0F,
    26.4F,
    27.0F,
    124.0F,
    0.0F,
    27.0F,
    92.0F,
    0.0F,
    78.0F,
    92.0F,
    10.0F,
    78.0F,
    96.0F,
    10.0F,
    75.0F,
    96.0F,
    0.0F,
    75.0F,
    108.0F,
    0.0F,
    90.0F,
    108.0F,
    10.0F,
    90.0F,
    132.0F,
    10.0F,
    90.0F,
    132.0F,
    0.0F,
    90.0F },
    { 68.0F,
    0.0F,
    18.0F,
    68.0F,
    -26.4F,
    18.0F,
    68.0F,
    -26.4F,
    51.0F,
    68.0F,
    0.0F,
    51.0F,
    124.0F,
    0.0F,
    27.0F,
    124.0F,
    -26.4F,
    27.0F,
    104.0F,
    -26.4F,
    51.0F,
    104.0F,
    0.0F,
    51.0F,
    96.0F,
    0.0F,
    75.0F,
    96.0F,
    -10.0F,
    75.0F,
    92.0F,
    -10.0F,
    78.0F,
    92.0F,
    0.0F,
    78.0F,
    132.0F,
    0.0F,
    90.0F,
    132.0F,
    -10.0F,
    90.0F,
    108.0F,
    -10.0F,
    90.0F,
    108.0F,
    0.0F,
    90.0F },
    { 108.0F,
    0.0F,
    90.0F,
    108.0F,
    10.0F,
    90.0F,
    132.0F,
    10.0F,
    90.0F,
    132.0F,
    0.0F,
    90.0F,
    112.0F,
    0.0F,
    93.0F,
    112.0F,
    10.0F,
    93.0F,
    141.0F,
    10.0F,
    93.75F,
    141.0F,
    0.0F,
    93.75F,
    116.0F,
    0.0F,
    93.0F,
    116.0F,
    6.0F,
    93.0F,
    138.0F,
    6.0F,
    94.5F,
    138.0F,
    0.0F,
    94.5F,
    112.0F,
    0.0F,
    90.0F,
    112.0F,
    6.0F,
    90.0F,
    128.0F,
    6.0F,
    90.0F,
    128.0F,
    0.0F,
    90.0F },
    { 132.0F,
    0.0F,
    90.0F,
    132.0F,
    -10.0F,
    90.0F,
    108.0F,
    -10.0F,
    90.0F,
    108.0F,
    0.0F,
    90.0F,
    141.0F,
    0.0F,
    93.75F,
    141.0F,
    -10.0F,
    93.75F,
    112.0F,
    -10.0F,
    93.0F,
    112.0F,
    0.0F,
    93.0F,
    138.0F,
    0.0F,
    94.5F,
    138.0F,
    -6.0F,
    94.5F,
    116.0F,
    -6.0F,
    93.0F,
    116.0F,
    0.0F,
    93.0F,
    128.0F,
    0.0F,
    90.0F,
    128.0F,
    -6.0F,
    90.0F,
    112.0F,
    -6.0F,
    90.0F,
    112.0F,
    0.0F,
    90.0F },
    { 50.0F,
    0.0F,
    90.0F,
    50.0F,
    28.0F,
    90.0F,
    28.0F,
    50.0F,
    90.0F,
    0.0F,
    50.0F,
    90.0F,
    52.0F,
    0.0F,
    90.0F,
    52.0F,
    29.12F,
    90.0F,
    29.12F,
    52.0F,
    90.0F,
    0.0F,
    52.0F,
    90.0F,
    54.0F,
    0.0F,
    90.0F,
    54.0F,
    30.24F,
    90.0F,
    30.24F,
    54.0F,
    90.0F,
    0.0F,
    54.0F,
    90.0F,
    56.0F,
    0.0F,
    90.0F,
    56.0F,
    31.36F,
    90.0F,
    31.36F,
    56.0F,
    90.0F,
    0.0F,
    56.0F,
    90.0F },
    { 0.0F,
    50.0F,
    90.0F,
    -28.0F,
    50.0F,
    90.0F,
    -50.0F,
    28.0F,
    90.0F,
    -50.0F,
    0.0F,
    90.0F,
    0.0F,
    52.0F,
    90.0F,
    -29.12F,
    52.0F,
    90.0F,
    -52.0F,
    29.12F,
    90.0F,
    -52.0F,
    0.0F,
    90.0F,
    0.0F,
    54.0F,
    90.0F,
    -30.24F,
    54.0F,
    90.0F,
    -54.0F,
    30.24F,
    90.0F,
    -54.0F,
    0.0F,
    90.0F,
    0.0F,
    56.0F,
    90.0F,
    -31.36F,
    56.0F,
    90.0F,
    -56.0F,
    31.36F,
    90.0F,
    -56.0F,
    0.0F,
    90.0F },
    { -50.0F,
    0.0F,
    90.0F,
    -50.0F,
    -28.0F,
    90.0F,
    -28.0F,
    -50.0F,
    90.0F,
    0.0F,
    -50.0F,
    90.0F,
    -52.0F,
    0.0F,
    90.0F,
    -52.0F,
    -29.12F,
    90.0F,
    -29.12F,
    -52.0F,
    90.0F,
    0.0F,
    -52.0F,
    90.0F,
    -54.0F,
    0.0F,
    90.0F,
    -54.0F,
    -30.24F,
    90.0F,
    -30.24F,
    -54.0F,
    90.0F,
    0.0F,
    -54.0F,
    90.0F,
    -56.0F,
    0.0F,
    90.0F,
    -56.0F,
    -31.36F,
    90.0F,
    -31.36F,
    -56.0F,
    90.0F,
    0.0F,
    -56.0F,
    90.0F },
    { 0.0F,
    -50.0F,
    90.0F,
    28.0F,
    -50.0F,
    90.0F,
    50.0F,
    -28.0F,
    90.0F,
    50.0F,
    0.0F,
    90.0F,
    0.0F,
    -52.0F,
    90.0F,
    29.12F,
    -52.0F,
    90.0F,
    52.0F,
    -29.12F,
    90.0F,
    52.0F,
    0.0F,
    90.0F,
    0.0F,
    -54.0F,
    90.0F,
    30.24F,
    -54.0F,
    90.0F,
    54.0F,
    -30.24F,
    90.0F,
    54.0F,
    0.0F,
    90.0F,
    0.0F,
    -56.0F,
    90.0F,
    31.36F,
    -56.0F,
    90.0F,
    56.0F,
    -31.36F,
    90.0F,
    56.0F,
    0.0F,
    90.0F },
    { 8.0F,
    0.0F,
    102.0F,
    8.0F,
    4.48F,
    102.0F,
    4.48F,
    8.0F,
    102.0F,
    0.0F,
    8.0F,
    102.0F,
    16.0F,
    0.0F,
    96.0F,
    16.0F,
    8.96F,
    96.0F,
    8.96F,
    16.0F,
    96.0F,
    0.0F,
    16.0F,
    96.0F,
    52.0F,
    0.0F,
    96.0F,
    52.0F,
    29.12F,
    96.0F,
    29.12F,
    52.0F,
    96.0F,
    0.0F,
    52.0F,
    96.0F,
    52.0F,
    0.0F,
    90.0F,
    52.0F,
    29.12F,
    90.0F,
    29.12F,
    52.0F,
    90.0F,
    0.0F,
    52.0F,
    90.0F },
    { 0.0F,
    8.0F,
    102.0F,
    -4.48F,
    8.0F,
    102.0F,
    -8.0F,
    4.48F,
    102.0F,
    -8.0F,
    0.0F,
    102.0F,
    0.0F,
    16.0F,
    96.0F,
    -8.96F,
    16.0F,
    96.0F,
    -16.0F,
    8.96F,
    96.0F,
    -16.0F,
    0.0F,
    96.0F,
    0.0F,
    52.0F,
    96.0F,
    -29.12F,
    52.0F,
    96.0F,
    -52.0F,
    29.12F,
    96.0F,
    -52.0F,
    0.0F,
    96.0F,
    0.0F,
    52.0F,
    90.0F,
    -29.12F,
    52.0F,
    90.0F,
    -52.0F,
    29.12F,
    90.0F,
    -52.0F,
    0.0F,
    90.0F },
    { -8.0F,
    0.0F,
    102.0F,
    -8.0F,
    -4.48F,
    102.0F,
    -4.48F,
    -8.0F,
    102.0F,
    0.0F,
    -8.0F,
    102.0F,
    -16.0F,
    0.0F,
    96.0F,
    -16.0F,
    -8.96F,
    96.0F,
    -8.96F,
    -16.0F,
    96.0F,
    0.0F,
    -16.0F,
    96.0F,
    -52.0F,
    0.0F,
    96.0F,
    -52.0F,
    -29.12F,
    96.0F,
    -29.12F,
    -52.0F,
    96.0F,
    0.0F,
    -52.0F,
    96.0F,
    -52.0F,
    0.0F,
    90.0F,
    -52.0F,
    -29.12F,
    90.0F,
    -29.12F,
    -52.0F,
    90.0F,
    0.0F,
    -52.0F,
    90.0F },
    { 0.0F,
    -8.0F,
    102.0F,
    4.48F,
    -8.0F,
    102.0F,
    8.0F,
    -4.48F,
    102.0F,
    8.0F,
    0.0F,
    102.0F,
    0.0F,
    -16.0F,
    96.0F,
    8.96F,
    -16.0F,
    96.0F,
    16.0F,
    -8.96F,
    96.0F,
    16.0F,
    0.0F,
    96.0F,
    0.0F,
    -52.0F,
    96.0F,
    29.12F,
    -52.0F,
    96.0F,
    52.0F,
    -29.12F,
    96.0F,
    52.0F,
    0.0F,
    96.0F,
    0.0F,
    -52.0F,
    90.0F,
    29.12F,
    -52.0F,
    90.0F,
    52.0F,
    -29.12F,
    90.0F,
    52.0F,
    0.0F,
    90.0F },
    { 0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    32.0F,
    0.0F,
    120.0F,
    32.0F,
    18.0F,
    120.0F,
    18.0F,
    32.0F,
    120.0F,
    0.0F,
    32.0F,
    120.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    8.0F,
    0.0F,
    102.0F,
    8.0F,
    4.48F,
    102.0F,
    4.48F,
    8.0F,
    102.0F,
    0.0F,
    8.0F,
    102.0F },
    { 0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    32.0F,
    120.0F,
    -18.0F,
    32.0F,
    120.0F,
    -32.0F,
    18.0F,
    120.0F,
    -32.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    8.0F,
    102.0F,
    -4.48F,
    8.0F,
    102.0F,
    -8.0F,
    4.48F,
    102.0F,
    -8.0F,
    0.0F,
    102.0F },
    { 0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    -32.0F,
    0.0F,
    120.0F,
    -32.0F,
    -18.0F,
    120.0F,
    -18.0F,
    -32.0F,
    120.0F,
    0.0F,
    -32.0F,
    120.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    -8.0F,
    0.0F,
    102.0F,
    -8.0F,
    -4.48F,
    102.0F,
    -4.48F,
    -8.0F,
    102.0F,
    0.0F,
    -8.0F,
    102.0F },
    { 0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    120.0F,
    0.0F,
    -32.0F,
    120.0F,
    18.0F,
    -32.0F,
    120.0F,
    32.0F,
    -18.0F,
    120.0F,
    32.0F,
    0.0F,
    120.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    0.0F,
    108.0F,
    0.0F,
    -8.0F,
    102.0F,
    4.48F,
    -8.0F,
    102.0F,
    8.0F,
    -4.48F,
    102.0F,
    8.0F,
    0.0F,
    102.0F } };
    public Teapot() { super(PATCHES); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163983660000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYe2wcRxkf352fd60fSZwHSRw7Tt02zi12k7bBFYnrR+Lk" +
                                                   "HFtnx22dhut4d+68\n8d7udnbOvrhRaFUpCVRAI1oJJJoWFCmp1UKkggJSCY" +
                                                   "na8miEVCrRSpUaQJEoCIqokEoQ/ME3M3u3\ne3tng5CwdLuzM99jvtdvvvEr" +
                                                   "H6Nqh6KNqhNnx23ixAcmxjF1iDZgYMeZhKmU+lZ1/fiFg6YVQlUJ\nFNI1hh" +
                                                   "oTqqNomGFF15SRwb48RdttyzieMSwWJ3kWP2bscuUdSOwqE/jQucstT52PtI" +
                                                   "VQdQI1YtO0\nGGa6ZQ4ZJOsw1JQ4huexkmO6oSR0h/Ul0G3EzGUHLNNh2GTO" +
                                                   "4+gkCidQja1ymQy1JwrKFVCu2Jji\nrCLUK+NCLUhYRQnDukm0/qI64Owu5Y" +
                                                   "Rtu3zJcmoQUscXp8AcsQOwekvRamltmal2+OLUvSdeejmM\nGqdRo25OcGEq" +
                                                   "WMJA3zSKZUl2hlCnX9OINo2aTUK0CUJ1bOiLQus0anH0jIlZjhInSRzLmOeE" +
                                                   "LU7O\nJlToLEwmUEzlNtGcyixa9FFaJ4ZW+KpOGzgDZrd6Zktzh/k8GNigw8" +
                                                   "ZoGqukwBKZ002IeFuQo2hj\n50EgANbaLGGzVlFVxMQwgVpkLA1sZpQJRnUz" +
                                                   "A6TVVg60MLRhWaHc1zZW53CGpBhaF6Qbl0tAVS8c\nwVkYWhMkE5IgShsCUf" +
                                                   "LFZ3vrp2cufuvKXpHbEY2oBt9/AzBtDjAlSZpQYqpEMt7KxZ8beSS3MYQQ\n" +
                                                   "EK8JEEua/m2XDyf+8JM2SfOZCjRjM8eIylLqobNtySf2WSjMt1FnW47Og19i" +
                                                   "uSiHcXelL29D1bYW\nJfLFeGHxavKnjzy5RP4UQg0jqEa1jFwW8qhZtbK2bh" +
                                                   "C6j5iEYka0EVRPTG1ArI+gWhgnIOXl7Fg6\n7RA2giKGmKqxxDe4KA0iuIvq" +
                                                   "YaybaaswtjGbFeO8jRCqhR+KwS+M5J94M3RfXHFyZtqwFhSHqopF\nM8Vv1a" +
                                                   "JEYcRxiAHAMmMQZZJgG2zjCWQzNKHMWlmiYBWbumkpGR1KVrV2aGSev/83sX" +
                                                   "m+45aFqioO\ngcFSNqAK9luGRmhKvXDz7RNDB790RqYJT23XVoY6QVvc1Rbn" +
                                                   "2uJ+bXGpDVVVCSWruVYZLfD1HFQt\n4FvsromjBx470wE+ytsLEXAUJ+0Aq9" +
                                                   "ytDKnWgFfaIwIFVcivdd85cjp+68IemV/K8ghckTv6zqvX\nX/pb7O4QClWG" +
                                                   "R24iAHQDFzPOMbUIe53Bgqok/y9fHn3tvesf3umVFjirrOLLOXnFdgSDQS2V" +
                                                   "aICB\nnvjz6xvDD6GpsyEUARgA6BP7B1TZHNRRUrl9BRTkttQmUDRt0Sw2+F" +
                                                   "IBuhrYLLUWvBmRJU1ivAqC\nE+Wp3Ay/P7q5Ld58dY3Nn60yq3i0A1YIlL31" +
                                                   "dM1n3389+pZwSwGQG31H3gRhsrybvWSZpITA/Iff\nGP/68x+fPiIyxU0VBu" +
                                                   "dgbsbQ1Tyw3OGxQF0bgC08kJ2Hzayl6WmdZyTPuH81buv5wZ+/2iRDY8BM\n" +
                                                   "IbLd/1mAN7/+QfTk9S/8fbMQU6Xyc8UzwyOT1qzyJPdTio/zfeSfenfTN3+G" +
                                                   "XwDYA6hx9EUi0AMJ\ny5DwoyL8frd4xgNrPfzRAbK7l0n9Cqd4Sj2xlOnIPf" +
                                                   "6LH4ldR7G/HfCHYRTbfTLy/LGVe3dtsHr3\nY2cW6HZePfRok3H1nyBxGiSq" +
                                                   "cHo6YxSAI18SRJe6uvaDa2+0PvarMAoNowbDwtowFvmP6iHxiDML\nmJO39+" +
                                                   "wVudW0UMefwmQknLDBdUDe98Xx4q7ly3+Y9wBe5aRmui8m3h57QThg2cKvcA" +
                                                   "QG5CxeOXzu\n1i/ZDSHHq0DO3Z4vB1Tomzze+9+bb6659GI2hGqnUZPqdnZT" +
                                                   "2MjxPJ+GRsQptHvQ/ZWslzYV8gTt\nKyLMxmD1+9QGa98Dchhzaj6OBcp9Pf" +
                                                   "c2D8ZHbrlXB8u9ConBHsHSKZ5dsjjDDBTqJoZ91dpUn4dT\nF8rVET1gHubG" +
                                                   "+ycH9g9NcKjwNeCiPDj8vPzs4Krk7iNPC4Suh54QO4e8HUMnzkdV4Opty8e+" +
                                                   "KCyl\ndh29/NdrV0iXSNM63QHP9NNMhf7Ix/MJXiKj76fPCZSNzGBH+ijYWJ" +
                                                   "b3jSXtoHDp7bZM2X02r6V1\n/hsH1bPQucwLgL55quPHPz/84ml5qK2Q1SVc" +
                                                   "KfWLv/ntXPhr12YkXzB1A8RnN5///Ws3k6slAMoW\nd2tZl+nnkW2uMKRRGN" +
                                                   "C+kgZB/eb29ldOJm+4O2opbdaG4ELz0fE3SNcDX/ldha4C8tTCzLZtaJpk\n" +
                                                   "5Hf33t9rBybugX20gCP5VSyua26BDr47PLOUNpe0kAiNCFQ/53ATp17M+DIp" +
                                                   "bNkO7zV9lzpXUueY\n7fDz+DafkpHBE5cOxOq+/cwpId9Nw3pf3+p+185jes" +
                                                   "hvVFTsu+e+Xbvv3cnQ0f9DQ/e53nt2dvfs\n3NHbw9Dn/5vGLO6WYJezhWGa" +
                                                   "IWxLYYbvesqr0ZPQbpd7mvvHhQXUIsJ8u8B7Dk0ixP5F8EAkOdQ/\nKNsD/u" +
                                                   "zlj72yLHZVQnyRb3eU4D6EfNNy9xmRdqcf/iR2Cr95NOSekklIGGbZOwwyT4" +
                                                   "wSUYBF0gv8\nfF5XdpWV1y818cETj36a+PU/JAQUrkhRiHc6Zxh+CPWNa2xK" +
                                                   "0rrQH5WAaosXIOGmFePCUMz/KfY7\nJ3mhjW4K8oJL+ctPBi1g1EcGIXRHfq" +
                                                   "Icg6ynGT6ct8XCWobuXDljHiSLOqGjcELnS9zIvbe1BKfE\nfyBcP47m5P8g" +
                                                   "UurDrx7Zkn9m8lkB59WqgRcXxWUTKkU2ocX+un1ZaQVZ76BL35t6/bu7C0EW" +
                                                   "Tcrq\nvJdaxXQp5tnqFfIMLGir3PkNZW0merXFH679/gMXzt0Iid7z32R8ER" +
                                                   "U4EgAA");
}
