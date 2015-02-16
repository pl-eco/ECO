package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class InvertedBucketOrder implements BucketOrder {
    private BucketOrder order;
    public InvertedBucketOrder(BucketOrder order) { super();
                                                    this.order = order; }
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = order.
                                                         getBucketSequence(
                                                           nbw,
                                                           nbh);
                                                       for (int i = 0; i <
                                                                         coords.
                                                                           length /
                                                                         2;
                                                            i +=
                                                              2) {
                                                           int src =
                                                             i;
                                                           int dst =
                                                             coords.
                                                               length -
                                                             2 -
                                                             i;
                                                           int tmp =
                                                             coords[src +
                                                                      0];
                                                           coords[src +
                                                                    0] =
                                                             coords[dst +
                                                                      0];
                                                           coords[dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[src +
                                                                      1];
                                                           coords[src +
                                                                    1] =
                                                             coords[dst +
                                                                      1];
                                                           coords[dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1160723124000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWwc1RW+O7bXP3Hin5CfpomTOA6qY7oDVFBRIyBZOYmT" +
                                                    "JbbsJBKLwnJ39u567NmZycwde+NgApFQIh4i1Do0VNQPVSBAQ4KqRrSqkPLS" +
                                                    "AqKqBKpa9aGk7UtR00jNQykCWjjn3p2fnV0H8VJLM3vn3nPuOeeec75zri/e" +
                                                    "IC2uQ4ZsyzhWMiyeYhWemjbuSfFjNnNT+zL3jFPHZYW0QV33IMzltP43uj7+" +
                                                    "7LmpboUks2Q1NU2LU65bpjvBXMuYZYUM6QpnRwxWdjnpzkzTWap6XDfUjO7y" +
                                                    "4QxZEWHlZCDjq6CCCiqooAoV1J0hFTCtZKZXTiMHNbl7lDxJEhmStDVUj5Ot" +
                                                    "tZvY1KHl6jbjwgLYoQ2/D4NRgrnikC2B7dLmOoPPDqmLP3ys+2dNpCtLunRz" +
                                                    "EtXRQAkOQrKks8zKeea4OwsFVsiSHpOxwiRzdGro80LvLOl19ZJJueew4JBw" +
                                                    "0rOZI2SGJ9epoW2Op3HLCcwr6swo+F8tRYOWwNa1oa3Swt04DwZ26KCYU6Qa" +
                                                    "81maZ3SzwMnmOEdg48B+IADW1jLjU1YgqtmkMEF6pe8MapbUSe7oZglIWywP" +
                                                    "pHCyYdlN8axtqs3QEstxsj5ONy6XgKpdHASycLImTiZ2Ai9tiHkp4p8bB+4/" +
                                                    "c9zcaypC5wLTDNS/DZj6YkwTrMgcZmpMMnbuyDxP1751WiEEiNfEiCXNm0/c" +
                                                    "fOiOvqvvSJpvNqAZy08zjee08/lV729MD97XhGq02Zaro/NrLBfhP15dGa7Y" +
                                                    "kHlrgx1xMeUvXp34zSNPvcauK6RjlCQ1y/DKEEc9mlW2dYM5e5jJHMpZYZS0" +
                                                    "M7OQFuujpBXGGd1kcnasWHQZHyXNhphKWuIbjqgIW+ARtcJYN4uWP7YpnxLj" +
                                                    "ik0IaYWHfBeeFiL/xC8n0+ohF8JdpRo1ddNSIXgZdbQplWlWLg+nO1Wmzoyr" +
                                                    "ap7LrbLqembRsOZU19FUyykF35rlMDXvaTOMq6PmLMQSK+wSn2NOgTkpjDn7" +
                                                    "/yqtgrZ3zyUS4JaNcVAwIJ/2WgbQ5rRFb9fIzUu595QgSaqnxskQCE1VhaZQ" +
                                                    "aEoKTTUQShIJIes2FC7dD86bARgAgOwcnDyy7/HT/U0Qd/ZcM5w8kvaD1VWN" +
                                                    "RjQrHWLFqEBEDQJ2/U8ePZX65MKDMmDV5YG9ITe5em7u6cMn7lSIUovQaCFM" +
                                                    "dSD7OOJqgJ8D8cxstG/XqY8+vvz8ghXmaA3kV6GjnhNTvz/uC8fSWAHANNx+" +
                                                    "xxZ6JffWwoBCmgFPAEM5hZgHeOqLy6iBgGEfTtGWFjC4aDllauCSj4EdfMqx" +
                                                    "5sIZESSrxLgHnLICc6IPnrZqkohfXF1t4/s2GVTo5ZgVAq53//LqC1d+NHSf" +
                                                    "EkX2rkitnGRc4kRPGCQHHcZg/s/nxn9w9sapR0WEAMW2RgIG8J0G1ACXwbE+" +
                                                    "887RP1378PzvlTCqOJRPL2/oWgX2uD2UAphiAK6h7wcOmWWroBd1mjcYBufn" +
                                                    "XdvvuvLPM93SmwbM+MFwx1dvEM5/Yxd56r3H/tMntkloWNNCy0MyeQCrw513" +
                                                    "Og49hnpUnv5g0wtv0x8D5ALMufo8E8iVqOYLKrWGk411ORlJQuEfVZDuEO8U" +
                                                    "OlBsQMTad/C1xa5bq4iZ9UFqDi6fabuxfkcy9NMxI3/yb58Is+tyrEHZivFn" +
                                                    "1Ysvbkg/cF3wh8GO3Jsr9dAFvU7Ie/dr5X8r/clfK6Q1S7q1aiN1mBoehlQW" +
                                                    "mgfX766g2apZr20EZNUbDpJ5YzzRImLjaRZCJoyRGscdsczq9DMrWc2sZDyz" +
                                                    "EkQMhgVLv3hvx9e3/MButR19lmKXBu0KOhtX7xQJaQtpA1EfikVMgk3LtRqi" +
                                                    "TTp/cnGpMPbSXRJfe2vL9wh0p6//4b+/TZ37y7sNqkM7t+xvG2yWGbHg2VSD" +
                                                    "6w+LLix02rOv/vRN/v7Q96TIHcsHWpzx7ZP/2HDwganHvwaab44ZH9/y1Ycv" +
                                                    "vrvndu37CmkKfF/XWNYyDdd6vMNh0AmbB2v83hf4HR/S7weA/1uHqKH7wrRV" +
                                                    "xHkqt07GcUcvQ4s1W+0B1YXeazMvfvS6PNt45sWI2enFZ79InVlUIl31trrG" +
                                                    "NsojO2uh8kpp4hfwl4Dnf/igaTghO6vedLW92xL0d7aNEbn1VmoJEbv/fnnh" +
                                                    "V68snFKqqJXmpAnuATicEBMHbgFxj+BrPyc9JcYlNE6yox4WSZC9ffmjFDgs" +
                                                    "T2bp5W2/O7G07a9wMlnSpruQ/TudUoO+PcLzr4vXrn+wctMlUbSb89SV8RC/" +
                                                    "8NTfZ2quKcKCTpnPE4F50RJwr+1DRa4xVCg4HASQKOomNQAtkgYzS7IXnsDX" +
                                                    "EbtSF2TV4iLLEkIi4IRlMqxw/pps7HQrFVwHYbHSUMc90oAjjcI66irjFmsm" +
                                                    "vqbBDA0VkXqDAzc3LskjZZuLIjr/i3U/v//C0oeiJ6iQBpUOrGzQvuLe6+vu" +
                                                    "zvK+p11a6mpbt3Toj9K3/p2sHS5GRc8wovgfGSdthxV1YUu7rAbyVDxO1i3T" +
                                                    "V4Ov5EBozSU9KNwdp+ekGX+iZMc5WREhg3pRHUWJnoREAiIcnrB9x3aHTpd1" +
                                                    "sEIigI7tWPSrpjfDJBL/l/Dx1ZP/mchpl5f2HTh+896XBFiDD+n8vLjHQrzL" +
                                                    "tjTA6K3L7ubvldw7+NmqN9q3+3CwCl+91V40qhuOj34J/H5VyAUSAAA=");
}
