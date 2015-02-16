package org.sunflow.core;
public interface AccelerationStructure {
    public void build(PrimitiveList primitives);
    public void intersect(Ray r, IntersectionState istate);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170607546000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ze3BUVxk/u5t3IAkJL2lLIQk4QLsrjnQG6FTCNinBhWRI" +
                                "wCFalrN3zyYX7t57ufdsstDSaTvjgIxFbFNLteYPBZVaSqcWa9U61KoFa1U6" +
                                "tYpaWh1nilUUZnyM0ha/75zdvXfvPkgCmpn9cvc8vsfvfK9z94nzpNK2yBLT" +
                                "0HYOagYPsjQPbtOWBflOk9nBtZFlvdSyWTysUdvuh7Go0vpU4z8vHRhq8pOq" +
                                "AdJCdd3glKuGbm9gtqENs3iENDqjnRpL2pw0RbbRYRpKcVULRVSbr4yQetdW" +
                                "TtojWRVCoEIIVAgJFUIdzirYNJXpqWQYd1Cd2zvIPcQXIVWmgupxMj+fiUkt" +
                                "msyw6RUWAIca/L4JjBKb0xaZl7Nd2lxg8MNLQqOPbGl6OkAaB0ijqvehOgoo" +
                                "wUHIAJmSZMkYs+yOeJzFB8g0nbF4H7NUqqm7hN4DpNlWB3XKUxbLgYSDKZNZ" +
                                "QqaD3BQFbbNSCjesnHkJlWnx7LfKhEYHwdaZjq3Swi4cBwPrVFDMSlCFZbdU" +
                                "bFf1OCc3enfkbGz/GCyArdVJxoeMnKgKncIAaZZnp1F9MNTHLVUfhKWVRgqk" +
                                "cDKnJFPE2qTKdjrIopzM9q7rlVOwqlYAgVs4meFdJjjBKc3xnJLrfM6vv3X/" +
                                "Xfoa3S90jjNFQ/1rYNNcz6YNLMEspitMbpyyOPJ5OvP5vX5CYPEMz2K55tm7" +
                                "L666ae6Jk3LNdUXW9MS2MYVHlUOxhtPXhxctD6AaNaZhq3j4eZYL9+/NzKxM" +
                                "mxB5M3MccTKYnTyx4ceb732c/dlP6rpJlWJoqST40TTFSJqqxqw7mM4sylm8" +
                                "m9QyPR4W892kGp4jqs7kaE8iYTPeTSo0MVRliO8AUQJYIETV8KzqCSP7bFI+" +
                                "JJ7TJiGkGj7EB58WIv8CSDhhoY02uHuIKlRXdSMEzsuopQyFmGJEY4DuUJJa" +
                                "2+2QkrK5kQzZKT2hGSMh21JChjWY+64YFgt1KArT0BIwuU+4PcRIEN3N/H8J" +
                                "SqPFTSM+HxzG9d5UoEEUrTG0OLOiymhqdefFJ6Mv+3OhkcGKk4UgL5iRF0R5" +
                                "waLyiM8nxExHufK84bS2Q9xDRpyyqO/OtVv3tgLKaXOkArFOi0Ccnf0CGz36" +
                                "iZDveu7Eo8e/sGS5350dGl35to9x6WvTHLn9FmMw/sbB3ocePr/nE0IorGgr" +
                                "JqAdaRg8D9IppKVPndxx5s2zh17z5xQNcEjBqZimKpzU0BjkL6pwTmpziajA" +
                                "kBtKRbPIRIfuHx2L9xxeKmOuOT9COqEAHH39vZ8GD751qshR1HLDvFljw0xz" +
                                "yaxHkeA1GWmdirFOJLpuUUcUCPN9R77xLD+9ZIUUubh0OfRufOn+d+b03za0" +
                                "1U/8+SUNpcNQHe7sxUKUKzg3eoz3sjyy7olTdyxUHvSTQCafFcnd+ZtWumEA" +
                                "oRYDf9MRUBypA6GtXte2DIXFwSkduYvn0ePR53e3+0kFJGUoRJxC4oAcP9cr" +
                                "PC+Prsx6HYqqBBAShpWkGk5lC0kdH7KMEWdExFyDeJ6Gx4NuEYJPbSbTiP84" +
                                "22IinS5jVKy/TtC5SOaLs/XjYyuSNvSshY6PQ1bUIDPjYbRv1JNGXE2oNKYx" +
                                "jLZ3GxcsPf6X/U3SgzQYyZ7OTVdm4Ix/YDW59+Ut/5or2PgUrMpO3DnLZPi1" +
                                "OJw7LIvuRD3S9716w6Mv0S9B0YBEbau7mMi9PmGaT9g6Aw6gIL/0WmoSCsWw" +
                                "MEbAs0IsXibocsQvE234/TYkSzmpjKVU6CQssqhMv5flLOtfaHfzm9sfO3dU" +
                                "Boa3oHoWs72j+y4H94/6XV1HW0Hhd++RnYdQcqr0h8vw54PP+/hBC3BAVp7m" +
                                "cKb8zcvVP9PEQ59fTi0houvtY7u/+/Xde/wZRD7IScWwocbNArzEwJJ831wB" +
                                "n+aMbzZP1jfzj8cvF2RPeHrBCW+gO7OTrQWT3ZhZbelc2I4yoUVPGRfYiGRt" +
                                "NinjVhwIl7dfQF/8LGY6PaFsgIKiB4fzKINHwMEDSgZ0xHBEXmDcSkfLzFGp" +
                                "KJLN6SxSTSLEUKug1KqcHhAPCVWnolu8U/DcgmQrkhhUskHGRV2yyxaEvhQU" +
                                "PFc3+oA69spP/tF4nwyY/EATF5LMVu++M78OfLiet39WFJKKGLVF6q6B5Grj" +
                                "Sk7mlb7cCF4yiuqveHIznJMT4nMHlwWx0QFRLMBhIy/KioMQVbqT0b7jZ/bc" +
                                "IuK/cViFXpbF+zP3rfzk6DQ+K/PuYEVhiirnjj1wcv47m1pEc51FxN3krKNm" +
                                "QZOzhtpDMF5Z/ZsXXpy59XSA+LtInWbQeBfF+xV0yVCXmD0EDV7a/OgqEd6+" +
                                "kRqMS5F9LbKghMkZm0Q1iCp3P/b+K3/affZUgFRBrcOCTS1ozaH3D5a61boZ" +
                                "tPfD0+2wCyppg9wNd6wc9OACzbnRXNnm5OZSvEUS91R3vBdC9mDWaiOlx5Ht" +
                                "hzwtQ8o03bPCmaZM3pnugUo6DvBythMnvYI/NDguiP2eexJ6vJZwpKOvL9q/" +
                                "ubczuqljQ3fH6kincFITJn39+Kiky5zex6EIhqkVl4F3+HJt26pZp1aIwCsE" +
                                "aZLAzBbA0BEewqJl6Ex3MqQpNSzoittL6Ot6BRJVDrx2YeqmC9+/KGLM23DK" +
                                "VqAhkxQtMst708iExEdOrP9kk3biEjAZACZwWbHtHguuOWL/cCYWiIiFJqEe" +
                                "KZqExXVRJNBPI/mMOyePrw7I5Fsmz3+uzNxDXnnKleqwzPMHkDyIZBTy/BBg" +
                                "EjbisjddheR2qVAXJwE185ppr6vQ7CuKDzT2xVvHzqTJRbO369uznrn1a2Nn" +
                                "xc0JGT2C5CCSLyIZGyd8ZdqKTOMoHQzpV8rAd3ic8Al2rWLFl5EcQvJVqOBs" +
                                "R4pqdjHcqmOGoTGqjw87YTySI0geR3L0GgHitvebZeaOTxyLp5E8g+Rb4EXc" +
                                "kO/GirQironxQ/Ecku8g+d5EoJhIaP2gzNwPx4mHI2/Qia8XkLyI5EfY5xlc" +
                                "TezEb+EJYnBSbEJy6n+Fwc/LzJ2+Ggx+huQXSF6Frlti0KFpk4HhlzkYXr+W" +
                                "MLgvmZ7wrdAMfVDw/l2ZTXgzdbxc3EmslAktX2daYSYmP8Hi91cD42+RvIXk" +
                                "j6DWCFX5ZBB8O4fguYkgeOX7G359A8lBweevpdPxH8SCCxOuWPIYziP5G5KL" +
                                "jj2TBuHv1wgEd7D850qmvztJ0/+N5BKS967edJ8cS0PnWvQVLd4gZhf8ICR/" +
                                "xFCeHGusmTW28VfiBVnuh4baCKlJpDTN1Uy7G+sq02IJVVhSK7szcUX2VUCJ" +
                                "8N7rwcPxHxrkC8hl1ZzUu5ZBZc08uRfVQaMCi/CxHvpLktdYmt42s63kZWtd" +
                                "Sv5MFlWOja1df9fFWw6LprgSGtddu5AL3EWq5es9wRTfl80vyS3Lq2rNoksN" +
                                "T9UuyL57Ee1ps8vlZrvcaOy/MtwiPJIcAAA=");
}
