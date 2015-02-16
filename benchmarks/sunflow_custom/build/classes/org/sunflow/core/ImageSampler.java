package org.sunflow.core;
public interface ImageSampler {
    public boolean prepare(Options options, Scene scene, int w, int h);
    public void render(Display display);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1163551120000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZDXAU1fndXf4TuJBIiCiRn6AD0bvSKc4ATiWJQYIHyZBA" +
                                "a2o9Xvbe5Rb2dpfdd8kRwVFnOjjOiIxFBUcz4wxUaUEcK7W2tYO1rVBrWxxr" +
                                "S1vRdjojtaWFmf5Mi0q/7727273N3ZEEzMx+efve976/9/29vUNnSbltkTbT" +
                                "0LYNaQYPsTQPbdaWhvg2k9mhNZGlvdSyWaxTo7bdD3NRZf4LwX9f2J2o95OK" +
                                "AdJIdd3glKuGbq9ntqENs1iEBJ3ZLo0lbU7qI5vpMA2nuKqFI6rNV0RIrWsr" +
                                "J62RrAhhECEMIoSFCOF2Bws2TWN6KtmJO6jO7a3kXuKLkApTQfE4mZdPxKQW" +
                                "TWbI9AoNgEIVvm8EpcTmtEXm5nSXOo9T+LG28J4n7q5/MUCCAySo6n0ojgJC" +
                                "cGAyQOqSLDnILLs9FmOxATJDZyzWxyyVauqokHuANNjqkE55ymI5I+FkymSW" +
                                "4OlYrk5B3ayUwg0rp15cZVos+1Ye1+gQ6Nrk6Co1XIXzoGCNCoJZcaqw7Jay" +
                                "Laoe4+Q6746cjq13AAJsrUwynjByrMp0ChOkQZ6dRvWhcB+3VH0IUMuNFHDh" +
                                "ZHZRomhrkypb6BCLctLsxeuVS4BVLQyBWziZ6UUTlOCUZntOyXU+Z9fdsuse" +
                                "fbXuFzLHmKKh/FWwqcWzaT2LM4vpCpMb6xZHHqdNrz7oJwSQZ3qQJc7L28+v" +
                                "vLHl2HGJc00BnJ7BzUzhUWX/4PST13YuWhZAMapMw1bx8PM0F+7fm1lZkTYh" +
                                "8ppyFHExlF08tv6nd973TfZXP6npJhWKoaWS4EczFCNpqhqzbmc6syhnsW5S" +
                                "zfRYp1jvJpUwjqg6k7M98bjNeDcp08RUhSHewURxIIEmqoSxqseN7NikPCHG" +
                                "aZMQUgkP8cFzNZF/VQg4uSu8wQZ3D1OF6qpuhMF5GbWURJgpRnQQrJtIUmuL" +
                                "HVZSNjeSYTulxzVjJGxbStiwhnLvimGxcHcSXKCPJk3QKYReZn7G9NOoX/2I" +
                                "zwemv9Yb+BrEzGpDizErquxJdXSdfz76pj8XCBnLcDIH2IQybELIJuRmQ3w+" +
                                "Qf0qZCcPFY5kCwQ3pL26RX1fXbPpwfkB8CZzpAwNmhbR1px9gY0esURcr3rl" +
                                "2L6jT7Yt87tTQNCVVPsYlw41w+HbbzEG8+/t7f36Y2d3fkUwBYwFhRi0IuwE" +
                                "94KcCbnna8e3nnr/9P53/DlBAxzybGpQUxVOquggJCmqcE6qc9lmnCJzioWs" +
                                "SDf7H9gzFus5sEQGVkN+GHRBlj/87ic/D+394ESBE6jmhnmTxoaZ5uJZiyzB" +
                                "RzLcuhRjrchm3aJYKBDLDx381sv8ZNtyyXJx8Zrn3fjGAx/N7v9iYpOf+PPr" +
                                "FnKHqRrc2YvVJldVrvMo7yV5cO2hE7dfrzzqJ4FM0iqQoPM3rXCbAZhaDCqK" +
                                "jgbFmRpgOt/r0ZahsBiUHYfv4rn0aPTVHa1+UgaZF6oNp5AdIJG3eJnnJcsV" +
                                "Wa9DVuVghLhhJamGS9lqUcMTljHizIhQmy7GM+B4GtEtZsMTzKQT8R9XG02E" +
                                "V8nQFPjXCNiCYJ44Wz8O5yNYgJ51vePjkPo0SL94GK0b9KQRU+MqHdQYRtvH" +
                                "wYVLjv5tV730IA1msqdz46UJOPNXd5D73rz7Py2CjE/B0uvEnYMmw6/Rodxu" +
                                "WXQbypG+/+05+96gT0NlgGxsq6NMJNgyoVqZ0HUm1IlxaaXHFGJlEZrGIfQp" +
                                "UAeA6aIS7ZulJqGiDGdKXnhHw/tbnjpzWIaAtz56kNmDex66GNq1x+9qIhaM" +
                                "q+PuPbKRECc5TZ78RfjzwfMpPnjiOCELSUNnpprNzZUz08TjnVdKLMFi1YdH" +
                                "dnz/uR07UQ1kcwMnAUhEOOwQE8sF96UCLkN2mbSE790IlnBSaVoM0p1011sR" +
                                "dMoc1gVrg4ahMaqb4yiIibacW9fi5A3wNGfcunmqbp0vsE8g+Io7x22qbWp0" +
                                "myDfX0LbLyPogeQNsQyVrZCyZcOGGiutqTi1wsfY5HSHshUKiW4cjrKE5gFH" +
                                "cxANemM4Xa8J3FqwEmsyKbUhiKazBqsXcYhShaRUpeTgkNxUnYq+URE0Ywji" +
                                "CBJQ7oYYF8XLLlk1+lJQFV196cPq2Fs/+1fwfhlr+TEqriaZrd59p34b+Hwt" +
                                "b31EVJuyQWqL/F4FGdhGTE7mFr/mCFoyAGsveXIznZMT7HMHlzVi0DGiQMDp" +
                                "VF6AFjZCVOlORvuOntp5s0gdwWEVuloW68/cvPIzqNMdrci7jRU0U1Q5c+Th" +
                                "4/M+2tgo2uysRdyd0FpqjuuEVlM7AfPllb977fWmTScDxL+K1GgGja2ieNOC" +
                                "fhmKF7MT0PylzVtXikD2jWDD6xdRaJGFRVTO6CRKRlTZ/tSnb/1lx+kTAVIB" +
                                "BRGrOuQXKOCchIrdb90EWvthdBvsgnI7Xe6G21bO9OACDbnZXG3n5KZitEX+" +
                                "97QAeEOEJMKsDiOlCzf/nKevSJmme1U4U93UneleKLcTMF5O90weJQ0ibKY7" +
                                "LohNoXsRGsHGzkh7X1+0/87erujG9vXd7R2RLuGkJiz6+nGopkuc3pdULdZJ" +
                                "rZgMvAMXqxesnHViuQi88UaaomGahWHoCA9jvTN0pjsZ0pQSjmudW4vI6/oY" +
                                "ElV2v3Nu2sZzPzwvYszblY7K9iuTFC0yy3sdyYTEF46tu6teO3YBiAwAEUVh" +
                                "tt1jQaEQ+7dnYoGIWKgX4pGCSVhHYCF4BMGj7pw8sTogk2+JPP9EibV9Xn7q" +
                                "pSquzPOPI9iL4EnI8wmwSacRE5HQIbbuclWV3QWNAa1+4WayK2ly0f6NfnfW" +
                                "S7c8O3Za3KWQ0NOCPoIxBM9M0FaX7hakNyH8RglbPTdBWwly8wXGAQTPIjgI" +
                                "5ZptTVF587pjYkYSWiI4JDYhOHyFNHcr9u0Sa0cnr/SLCF5C8B3wDW7Ib18F" +
                                "GgzXwsRN8QqC7yH4wWRMMZmA+VGJtR9P0B4Ov6QTNa8heB3BT7B7M7ga34Zv" +
                                "A5O0wXGxCcGJz8oGvyyxdvJybPALBL9C8DYn1dIG7Zo2FTP8OmeGd6+kGdxX" +
                                "CG/Hrxn6kKD9h9L3jhbHy7vxQ4+VMqGR60orTNxNBYk/Xo4Zf4/gAwR/BrFG" +
                                "qMqnYsEPcxY8MxkLlsgrfgfhPQTyWvn34nn3TwLh3KTrUFKgnUXwDwTnHX2m" +
                                "bIR/XiEjuIPlf5dS/eMpqv5fBBcQfHL5qvvkXJqTOvdHWbwONI/7nUf+NqE8" +
                                "PxasmjW24Tfik1ju94PqCKmKpzTN1Rm7u+QK02JxWWurZasl7ru+MqgM3rs6" +
                                "ODb+Qz18AYlWyUmtC42TyszIjVTDSQCQcFgLzSLJ6xJNb8+4oOjNaW1K/voV" +
                                "VY6MrVl3z/mbD4gOtxy60NFRpAIXi0r5QU8QxS9k84pSy9KqWL3owvQXqhdm" +
                                "v8GIXrPB5WnNLu955v96KKWXaRwAAA==");
}
