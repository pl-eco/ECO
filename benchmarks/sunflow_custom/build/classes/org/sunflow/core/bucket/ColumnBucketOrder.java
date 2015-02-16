package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class ColumnBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int bx =
                                                             i /
                                                             nbh;
                                                           int by =
                                                             i %
                                                             nbh;
                                                           if ((bx &
                                                                  1) ==
                                                                 1)
                                                               by =
                                                                 nbh -
                                                                   1 -
                                                                   by;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       return coords;
    }
    public ColumnBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0YW2wUVfTu9F0LfSBQEQqUopbijmgwwSJaaqHFlTYUSFyj" +
                                                    "9e7s3e3Q2Zlh5m67VOuDxED4IEargsF+GPBZwBiJGmPSH1/RmGiMxg9B/dGI" +
                                                    "JPLhI77wnHt3d2ZntzX+uMncuXPvOfe8H3enL5AK1yEdtmXsSxoWD7MMD+8x" +
                                                    "1of5Ppu54W2R9QPUcVm826CuuxPWhrTWV+p/+ePR4QaFVEbJAmqaFqdct0x3" +
                                                    "B3MtY5TFI6TeW+0xWMrlpCGyh45SNc11Q43oLu+MkMt8qJy0RXIsqMCCCiyo" +
                                                    "ggW1y4MCpHnMTKe6EYOa3N1LHiChCKm0NWSPk5WFh9jUoansMQNCAjihGr93" +
                                                    "g1ACOeOQFXnZpcxFAj/RoU4+dU/Dq2WkPkrqdXMQ2dGACQ5EoqQuxVIx5rhd" +
                                                    "8TiLR0mjyVh8kDk6NfRxwXeUNLl60qQ87bC8knAxbTNH0PQ0V6ehbE5a45aT" +
                                                    "Fy+hMyOe+6pIGDQJsi7yZJUSbsF1ELBWB8acBNVYDqV8RDfjnCwPYuRlbLsd" +
                                                    "AAC1KsX4sJUnVW5SWCBN0nYGNZPqIHd0MwmgFVYaqHCyZNZDUdc21UZokg1x" +
                                                    "0hyEG5BbAFUjFIEonCwMgomTwEpLAlby2efC9o2H7zN7TUXwHGeagfxXA1JL" +
                                                    "AGkHSzCHmRqTiHVrIk/SRW8fVAgB4IUBYAnz+v0Xb13bMvO+hLmyBEx/bA/T" +
                                                    "+JB2PDb/k6Xd7RvKkI1q23J1NH6B5ML9B7I7nRkbIm9R/kTcDOc2Z3a8e+dD" +
                                                    "L7HzCqntI5WaZaRT4EeNmpWydYM5W5nJHMpZvI/UMDPeLfb7SBXMI7rJ5Gp/" +
                                                    "IuEy3kfKDbFUaYlvUFECjkAVVcFcNxNWbm5TPizmGZsQUgUPWQ9PBZE/8eZk" +
                                                    "WN3lgrurVKOmbloqOC+jjjasMs0aioF2h1PUGXFVLe1yK6W6aTNhWGOq62iq" +
                                                    "5STz35rlMDWW1kYYVyX/m8VHvxNnThg9zv4faWVQ7oaxUAhMsjSYEAyIpV7L" +
                                                    "ANghbTK9uefiqaEPlXyAZDXGSTuQDGdJhpFkWJIMF5EkoZCgdDmSloYHs41A" +
                                                    "AoDUWNc+ePe2ew+2loHH2WPloHMEbQWJs/z0aFa3lyX6RC7UwFWbn73rQPi3" +
                                                    "52+RrqrOntJLYpOZI2MP737wOoUohbkZ5YOlWkQfwIyaz5xtwZgsdW79ge9/" +
                                                    "Of3khOVFZ0GyzyaNYkwM+tagJRxLY3FIo97xa1bQM0NvT7QppBwyCWRPTsHb" +
                                                    "ITG1BGkUBH9nLpGiLBUgcMJyUtTArVz2q+XDjjXmrQgXmY9Dk/QWNGCAQZGD" +
                                                    "t7w5c/TM0x0bFH+6rvcVwEHGZfA3evbf6TAG618dGXj8iQsH7hLGB4hVpQi0" +
                                                    "4dgNqQCsARp75P29X547e/wzxXMYDjUxHTN0LQNnXOVRAU80IFmhWdt2mSkr" +
                                                    "rid0GjMY+t2f9avXnfnxcIM0lAErOTuv/fcDvPUrNpOHPrzn1xZxTEjDQuVJ" +
                                                    "7oFJBSzwTu5yHLoP+cg8/Omyo+/RZyCPQu5y9XEm0hERkhGh+rCwSLsYrw3s" +
                                                    "XYfDCrtoLyNWmrNf4mOlGNtwuFrqDafX+CFDYr6Qk6VFwe2LZ9TystkKlCiu" +
                                                    "x/dPTsX7T6yTsdlUmPR7oKc5+flfH4WPfP1BibxSwy37WoONMqOAMSBZkBPu" +
                                                    "ELXbi4xDL778Ov+k4yZJcs3s6SCI+N7+H5bs3DR873/IBMsDwgePfPGO6Q+2" +
                                                    "XqU9ppCyfBIoakcKkTr9agCiDoP+yUSF4kqtsHWLYKAR1IEPaYWnMlusxBt3" +
                                                    "F9g4Xi5DFocbAs6jCH0qoM/2ObpgR09BYR7Ndg7qRNO5kWPfn5S6DbYZAWB2" +
                                                    "cPLQpfDhScXXi60qaof8OLIfEyzPkyJegl8Inr/xQdFwQdbjpu5sU7Ai3xXY" +
                                                    "NnrkyrnYEiS2fHd64q0XJg4o2djZwEkZdI847RULt80RaP04dHHSmGRcxsIg" +
                                                    "25vGBAu0V8+uShHoUjNTz636+MGpVd+AZqKkWnehLe9ykiW6PR/OT9Pnzn86" +
                                                    "b9kpkfDLY9SV/hBsk4u74ILmVkhQZ4tXb168UD605nCFLUjGV3V/7zdi+7/9" +
                                                    "TVi3KFpKeEcAP6pOH1vSvem8wPcKGGIvzxQ3I6AiD/f6l1I/K62V7yikKkoa" +
                                                    "tOy1aDc10lhLoqAEN3dXgqtTwX5hWy972M58bC4NuqePbLB0+qO0nBfE53w7" +
                                                    "EyIi/qKlE64iEi6HA3WTGhkoWwYzk7IL7cVh0M4UBWo2I8vagexDrrVMhmUo" +
                                                    "tycbK90K5y9isJkpsjN+3yydYLBUavC7e3KOPR0HUGmFhoxIvsGJlpeumz0p" +
                                                    "m4tKN/7G4tc2Pj91VhTuDClRsyC6ippHPLm56M4q71naqan66sVTu76Q0ZG7" +
                                                    "C9XAhSSRNgy/pXzzStthCV1IUpOzG76g9iyepacFS8mJ4NmS8HjPD8JzUo4v" +
                                                    "P9goJ5f5wDipys78QPsgFQEQTsftnFkbPJNLj82QgqpuF9Z4f/uEYSz+D8hV" +
                                                    "qLT8R2BIOz21bft9F288IcodWJCOj4v7I2QM2RTmq9zKWU/LnVXZ2/7H/Fdq" +
                                                    "VucSakG76OcN58Y/3ScYcn0RAAA=");
}
