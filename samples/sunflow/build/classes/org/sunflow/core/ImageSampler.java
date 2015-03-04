package org.sunflow.core;
public interface ImageSampler {
    public boolean prepare(Options options, Scene scene, int w, int h);
    public void render(Display display);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0ZDXAU1fndXf4TuJBIiCiRn6AD0bvSKc4ATiU5gwQPkuEi" +
                                "rWnr8bL7Lrewt7vsvkuOCI4604FxRmQUFRzNjDNQpQVxrNTa1g7WtkKtbXGs" +
                                "LW1F2+mM1JYWZvozLSr9vrd3t3ubuyMJtJm5L3vvfe/7/3t7h86SSsskHYau" +
                                "bh1SdR5iGR7apC4N8a0Gs0Jrokv7qGkxOaJSy+qHtbg0/4XgPy/sTjb6SdUA" +
                                "aaaapnPKFV2z1jNLV4eZHCVBZ7VbZSmLk8boJjpMw2muqOGoYvEVUVLvOspJ" +
                                "ezQnQhhECIMIYSFCuNPBgkPTmJZORfAE1bi1hdxLfFFSZUgoHifzCokY1KSp" +
                                "LJk+oQFQqMHvG0ApcThjkrl53W2dxyn8WEd4zxN3N74YIMEBElS0GIojgRAc" +
                                "mAyQhhRLDTLT6pRlJg+QGRpjcoyZClWVUSH3AGmylCGN8rTJ8kbCxbTBTMHT" +
                                "sVyDhLqZaYnrZl69hMJUOfetMqHSIdC1xdHV1nAVroOCdQoIZiaoxHJHKjYr" +
                                "mszJdd4TeR3b7wAEOFqdYjyp51lVaBQWSJPtO5VqQ+EYNxVtCFAr9TRw4WR2" +
                                "SaJoa4NKm+kQi3PS6sXrs7cAq1YYAo9wMtOLJiiBl2Z7vOTyz9l1t+y6R1ut" +
                                "+YXMMpNUlL8GDrV5Dq1nCWYyTWL2wYbF0cdpy6s7/YQA8kwPso3z8rbzK29s" +
                                "O3bcxrmmCE7v4CYm8bi0f3D6yWsji5YFUIwaQ7cUdH6B5iL8+7I7KzIGZF5L" +
                                "niJuhnKbx9b/+K77vs7+7Cd1PaRK0tV0CuJohqSnDEVl5u1MYyblTO4htUyT" +
                                "I2K/h1TDc1TRmL3am0hYjPeQClUsVeniO5goASTQRNXwrGgJPfdsUJ4UzxmD" +
                                "EFINH+KDz9XE/qtBwIkcTuopFqYS1RRND0PsMmpKyTCT9LjJDD3cHekND4KV" +
                                "kylqbrbCVlpLqPpIXEpbXE+FLVMK6+ZQbjks6SYL96QgFGI0ZYBuIYw24//E" +
                                "J4P6No74fOCKa72FQIUcWq2rMjPj0p50V/f55+Nv+vOJkbUUJ3OATSjLJoRs" +
                                "Qm42xOcT1K9CdraTwUWbIdmhDDYsin1lzcad8wMQXcZIBRo4I7KvNfcFDnrE" +
                                "Enm+6pVj+44+2bHM7y4JQVeRjTFuB9gMh2+/yRisv7e379HHzu74kmAKGAuK" +
                                "MWhHGIFwgxoKteirx7ecev/0/nf8eUEDHOpuelBVJE5q6CAULSpxTmrz1Wec" +
                                "InNKpbAoP/sf2DMm9x5YYidaU2FadEPVP/zuJz8N7f3gRBEP1HLduEllw0x1" +
                                "8axHlhAsWW7dkr5WVLce0TwkyO0HD37jZX6yY7nNcnHpHug9+MYDH83u/3xy" +
                                "o5/4C/sYcoelOjzZh90n32Wu8yjvJXlw7aETt18vPeIngWwRK1KwCw+tcJsB" +
                                "mJoMOoyGBsWVOmA63xvRpi4xGdqQw3fxXHo0/ur2dj+pgEoM3YdTqBZQ2Nu8" +
                                "zAuK54pc1CGrSjBCQjdTVMWtXPeo40lTH3FWRKpNF88zwD3NGBaz4RPMlhfx" +
                                "H3ebDYRX2akp8K8RsA3BPOFbPz7OR7AAI+t6J8ahFKpQjtEZ7XdqKV1WEgod" +
                                "VBlm28fBhUuO/mVXox1BKqzkvHPjpQk461d3kfvevPtfbYKMT8JW7OSdg2an" +
                                "X7NDudM06VaUI3P/23P2vUGfhk4B1dlSRpkouBVCtQqh60zoG+PKSq8hxMoh" +
                                "tIxDiEnQF4DpojLjnKmkoMMMZ1tgeHvT+5ufOnPYTgFvv/Qgs517HrwY2rXH" +
                                "7xoqFozr6+4z9mAhPDnN9vxF+PPB51P8oMdxwW4sTZFsd5ubb2+Gge6dV04s" +
                                "wWLVh0e2f/e57TtQDWRzAycBKET42CUWlgvuSwVchuyyZQm/9yBYwkm1AW2F" +
                                "mna43oogYtewbtgb1HWVUc0YR0EsdOTDuh4Xb4BPazasW6ca1oUC+wSCr3Rw" +
                                "3KZYhkq3CvL9ZbT9IoJeKN6Qy9DZiilbMawrcnlNhdeKu7HFmRbt0SgkpnNw" +
                                "ZRnNA47mIBrMyuBdrwncWrAye3ZR6kAQz+QM1ijyEKUK2VKVk4NDcVM0KuZI" +
                                "SdCUESQQJKHdDTEumpdVtmvE0tAVXXPqQ8rYWz/5R/B+O9cKc1RcVbJHvedO" +
                                "/Trw2Xre/rDoNhWD1BL1vQYqsIWYnMwtfe0RtOwErL+k52Y6nhPs847LGTHo" +
                                "GFEg4HK6IEGLGyEu9aTisaOndtwsSkdwWIEpl8n92ZtYYQV1pqMVBbezomaK" +
                                "S2eOPHR83kcbmsXYnbOIexJaS41xk9BqaiVhvbL6N6+93rLxZID4V5E6Vafy" +
                                "Koo3L5ifoXkxKwnDX8a4daVI5IYRHIAbRRaaZGEJlbM6iZYRl7Y99elbf9p+" +
                                "+kSAVEFDxK4O9QUaOCehUvddN4H2fni6DU5Bu51un4bbV970EAJN+dV8b+fk" +
                                "plK0Rf33jAB4Y4QiwswuPa2JMP+MZ65IG4Z7VwRTw9SD6V5otxMwXl73bB0l" +
                                "TSJtpjshiEOhexMGweZItDMWi/ff1dcd39C5vqezK9otgtSATV8/PiqZMt77" +
                                "gqLKEWrKduIduFi7YOWsE8tF4o030hQN0yoMQ0d4GPudrjHNqZCGLeG40bm9" +
                                "hLyulyNxafc756ZtOPf98yLHvFPpqD1+ZYuiSWZ5ryPZlPjcsXVfblSPXQAi" +
                                "A0BEkphl9ZrQKMT5bdlcaMznAsmQokVYQ2AieBjBI+6aPLE+YBffMnX+iTJ7" +
                                "+7z8lEt1XLvOP45gL4Inoc4nwSYRXRaZ0CWO7nJ1ld1FjQGjfvFhsjtlcDH+" +
                                "jX571ku3PDt2WtylkNDTgj6CMQTPTNBWl54W7GhC+LUytnpugrYS5OYLjAMI" +
                                "nkVwENo125Km9s3rjokZSWiJ4JA4hODwFdLcrdg3y+wdnbzSLyJ4CcG3IDa4" +
                                "br8LKzJguDYmbopXEHwHwfcmY4rJJMwPyuz9cIL2cPilnKx5DcHrCH6E05vO" +
                                "lcRW/DYwSRscF4cQnPhf2eDnZfZOXo4NfobgFwje5qTWtkGnqk7FDL/Mm+Hd" +
                                "K2kG9xXCO/GrujYkaP+u/L2jzYnyHnzRY6YNGOS6MxITd1NB4veXY8bfIvgA" +
                                "wR9BrBGq8KlY8MO8Bc9MxoJl6orfQXgPgX2t/GvpuvsHgXBu0n0oJdDOIvgb" +
                                "gvOOPlM2wt+vkBHcyfKfS6n+8RRV/zeCCwg+uXzVffZahpMG90tZvA60jvvd" +
                                "x/6tQnp+LFgza+zOX4lXYvnfE2qjpCaRVlXXZOyekqsMkyXsXltrj1rivuur" +
                                "gM7gvatDYOM/1MMXsNGqOal3oXFSnX1yI9VxEgAkfKyHYZEUTImGd2ZcUPLm" +
                                "tDZt/xoWl46MrVl3z/mbD4gJtxKm0NFRpAIXi2r7hZ4gim/I5pWklqNVtXrR" +
                                "hekv1C7MvYMRs2aTK9JaXdHzzH8BuZnhOHkcAAA=");
}
