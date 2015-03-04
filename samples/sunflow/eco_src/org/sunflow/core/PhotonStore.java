package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Vector3;
public interface PhotonStore {
    int numEmit();
    void prepare(BoundingBox sceneBounds);
    void store(ShadingState state, Vector3 dir, Color power, Color diffuse);
    void init();
    boolean allowDiffuseBounced();
    boolean allowReflectionBounced();
    boolean allowRefractionBounced();
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170612556000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAK1YfWwcRxWf+7Av/kBnO4mdpkkcuylR6vSWAonUOC1NXLt1" +
                               "fUmMnbip++GOd+fu\nNtnb2e7O2mc3SltVNClVC6GtRBGE/BGRNvQDCVBAKp" +
                               "CqLRT4hyIhpEoUUCRAgv7BP6UI/uC9md27\nvbV9QB1LOzc78+a993vzvtYv" +
                               "f0CaPJds0r2cWHCYlxuaHKeux4whi3reYVia0d9uahm/MGbzJEnk\nSdI0BM" +
                               "nmdU8zqKCaaWijtw9WXDLgcGuhaHGRYxWRO2btCvjdld+1hOHdZy91PXY+3Z" +
                               "skTXmSpbbN\nBRUmt4ctVvYE6cgfo3NU84VpaXnTE4N58glm++UhbnuC2sJ7" +
                               "iJwkqTxpdnTkKUhfPhSugXDNoS4t\na1K8Ni7FAoe1LhPUtJmxryoOTu6sPw" +
                               "lqB+cmllIDkzW4OQVwpAaAemsVtUK7BKqTenFq94lzL6VI\ndppkTXsSmemA" +
                               "RIC8adJeZuVZ5nr7DIMZ06TTZsyYZK5JLXNRSp0mXZ5ZtKnwXeZNMI9bc0jY" +
                               "5fkO\nc6XMcDFP2nXE5Pq64G7VRgWTWUb41lSwaBFgd9dgK7gjuA4AW01QzC" +
                               "1QnYVH0sdNG268N36iinHb\nGBDA0UyZiRKvikrbFBZIl7pLi9pFbVK4pl0E" +
                               "0ibugxRBNq7IFG3tUP04LbIZQTbE6cbVFlC1SEPg\nEUHWx8kkJ7iljbFbit" +
                               "zPQPeHp1/8+o9vk76dNphuof6tcGhL7NAEKzCX2TpTBz/yc8+N3uNvShIC\n" +
                               "xOtjxIpm3/WXjuT/8pNeRXPtMjSHZo8xXczoB8/0Tjx8BycpVGONwz0TL78O" +
                               "uQyH8WBnsOJA1HZX\nOeJmLty8PPHTex69yP6aJK2jpFnnll8GP+rUedkxLe" +
                               "bewWzmUsGMUdLCbGNI7o+SDMzz4PJq9VCh\n4DExStKWXGrm8h1MVAAWaKIW" +
                               "mJt2gYdzh4qSnFccQkgGHpKA51ai/lpxEETLaZ5vFyw+r3murnG3\nWH3Xuc" +
                               "u08RIXHIIE5jl0HEeQMa3Ey0yjOrVNm2tFE0JV5zcabA5//z92FdSwaz6RwJ" +
                               "QXD10LvP5O\nbhnMndEvXPnFieGxJ08rt0BXDrAJsgmk5AIpOZSSi0ghiYRk" +
                               "vg6lqVsBmx6H6IQ81r5j8v67Hjzd\nnwJ3cObTaJGKDJeN4QscjGklA/Ojx5" +
                               "s/9dvX296W2oQxnI1kyUkmlEd01uQedhmD9d99dfzZ5z84\nda8UqqSmBFlD" +
                               "ZyFTUF0I0lINeQEZ1Z+1TH2JVptXCiAZ/KeO/r39CfrW/crNu+qdchgS958X" +
                               "3mTb\n9z79x2Ws2SK4c6PF5pgVkZlBkXC5gbRhnR+QuWVU5n8dIuuLL337kn" +
                               "h3YI8SecPKtSd+8IY95xZ7\n97z2VJIkl68JqAVUpVbkMI6FpJrre2NGiLPu" +
                               "nLv286mS+U5Spj9MJcukzfpDg1FzgFDQx3dtNCyu\ntIPQ/riXulxnBhSDmt" +
                               "zz12RTd5OpM0mShnwINUBigvS6JS68LoUNhq6EojJ50lbgbplauBXm8FZR\n" +
                               "cvl8bUWGT4ecr4Vrku7RCU9HEOTyF3fXOzh2q3CT9JvluFU5YBLn/ThsQ+/6" +
                               "ZM1pIRlZkBDxIrYd\nscvcMAsmnbUYhs+/s9ff9P2/PdOhvMiClfBmdv53Br" +
                               "X1a/aTR3/5wD+2SDYJHYthLZBqZCqe1tY4\n73NduoB6VB779eYXfka/Abka" +
                               "8qNnLjKZ8kgQLqjUHol2lxxvju3txeEmQTIQGMNlU4CUDdHuyzVh\n0ZyTd3" +
                               "Tlif4fvXPkm6eUm+9o0GJFT83oj/z+D8dTX3pjVp2LV7IY8Zkt5//03SsT65" +
                               "RdVbm/bknF\njZ5RJV+iyTp4g32NJEjqtwb6Xj458b7UCM9tFyQFiQfO7lwB" +
                               "1TId2Ix+4mKx33/o5z+UurbRaCsX\nzYcHqKPU68Dh06hiTzwj30m9EtB99v" +
                               "LB+zqsy/8CjtPAUYfOxzvkQhGo1GXTgLop894bb3Y/+G6K\nJEdIq8WpMUKx" +
                               "3YKiCbHCvBLUj4rzudtUOMyvCYOiQupTKr4M1AfSDnh6gkDq+TiBVO9yCUmQ" +
                               "kO89\nsapVhlqd289924B+bD+vSPYTDXx2CocD4LOOy6C7VungVkfJHBIkPc" +
                               "dN1fmN4HBQbYx+LDuMwdMX\n2KFv9XZIS4J0aIfNS6r3ZImiGbAxZyHVhiXW" +
                               "mmJ4zZ8JCXqiBGYZelHMPEHb3WBfAtAbWLqMwwMC\nPsqwncCXozW7zqzGrl" +
                               "l4tgd23b56u0aVnm+wt4ADFCNoFk0Rw+OtBg/6Ri7Ak7u6eB5vsPcFHE4K\n" +
                               "spZacLe3m4WC7zGMJSjL8bDIzHJuMWrXED+yGsTb4NkdIN59dRF/pcHeczg8" +
                               "DXVSIoYeIqiQAWjc\nPVVD+MxqEd4SILzl6iI822DvHA4vRBBiZ7wiwq/9rw" +
                               "grgrRFvg2wn9iw5P8F6htXz7/38H0f5n/z\nT9nEVb9D2+BjsOBbVqRHjPaL" +
                               "zZCOC6aE0KbKnSN/LgjSEc9zEIH4IxX8liK7CPpFyMBfg1mU6BUo\n1ECE01" +
                               "edML11yMKIH/U59QVb/8WASK+rK+vyXzJh3+yrf8rM6EdfuXdr5anDX5bNeJ" +
                               "Nu0cVFZNOa\nJxnVjFZ7774VuYW8fkW+89rU66/eHDYYsvKvizhNnd+NqN0G" +
                               "Fwn9/vJd5XDZEbIPXPxBz/f2Xjj7\nflJ+Wf0H4eZK8EkTAAA=");
}
