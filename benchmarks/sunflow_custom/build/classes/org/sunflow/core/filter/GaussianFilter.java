package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class GaussianFilter implements Filter {
    private float s;
    private float es2;
    public GaussianFilter(float size) { super();
                                        s = size;
                                        es2 = (float) -Math.exp(-s * s); }
    public float getSize() { return s; }
    public float get(float x, float y) { float gx = (float) Math.exp(-x *
                                                                       x) +
                                           es2;
                                         float gy = (float) Math.exp(-y *
                                                                       y) +
                                           es2;
                                         return gx * gy; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSevW23tJS2lL+C0EIpRP72CgQNlqCwaaG4QEMB" +
                                                    "46KU6d3Z9tK7917unW2XYhVIDIQHYrQgGOyDgSDIX4wEjSHhRYHgC8ZofBCM" +
                                                    "LxKRBx5EIiqemdm9f7ut+uImMzt35pw558w555szZ+6hEttC80xD29mlGTRC" +
                                                    "MjSyXVsSoTtNYkfWxJa0YcsmiaiGbXsjzHUoDReqHjx6s7taQuE4God13aCY" +
                                                    "qoZubyC2ofWSRAxVubPNGknZFFXHtuNeLKepqskx1aZNMTTaw0pRYyynggwq" +
                                                    "yKCCzFWQV7hUwDSG6OlUlHFgndo70GsoFENhU2HqUTTDv4mJLZzKbtPGLYAd" +
                                                    "RrHvzWAUZ85YaLpju7A5z+BD8+TBd7ZWf1SEquKoStXbmToKKEFBSBxVpEiq" +
                                                    "k1j2ikSCJOJorE5Iop1YKtbUfq53HNXYapeOadoiziGxybRJLC7TPbkKhdlm" +
                                                    "pRVqWI55SZVoidxXSVLDXWDrRNdWYWELmwcDy1VQzEpiheRYintUPUFRfZDD" +
                                                    "sbHxBSAA1tIUod2GI6pYxzCBaoTvNKx3ye3UUvUuIC0x0iCFoinDbsrO2sRK" +
                                                    "D+4iHRTVBunaxBJQlfGDYCwUTQiS8Z3AS1MCXvL45966ZQd36at1ieucIIrG" +
                                                    "9B8FTHUBpg0kSSyiK0QwVsyNHcYTL++XEALiCQFiQXPp1fvPz6+7ck3QPFGA" +
                                                    "Zn3ndqLQDuV4Z+XNqdE5S4uYGqNMw1aZ832W8/Bvy640ZUzIvInOjmwxklu8" +
                                                    "suGLl3afJnclVN6KwoqhpVMQR2MVI2WqGrFWEZ1YmJJEKyojeiLK11tRKYxj" +
                                                    "qk7E7Ppk0ia0FRVrfCps8G84oiRswY6oFMaqnjRyYxPTbj7OmAihUmhoAbQS" +
                                                    "JH78nyIib7Ih3GWsYF3VDRmCl2BL6ZaJYnR0wul2p7DVY8tK2qZGSrbTelIz" +
                                                    "+mTbUmTD6nK+FcMiMmgCUSSvwmnbVrHewj8jLNzM/0tQhllc3RcKgTOmBqFA" +
                                                    "gyxabWgJYnUog+mVzffPddyQnNTInhVFs0FeJCsvwuRFhLyIXx4KhbiY8Uyu" +
                                                    "8Dd4qwfyHhCxYk77K2u27W8ogkAz+4rhqBlpA9iaVaZZMaIuOLRyCFQgQmvf" +
                                                    "37Iv8vDkcyJC5eGRvCA3unKkb8/m15+SkOSHZGYcTJUz9jYGpA5gNgZTsdC+" +
                                                    "VfvuPDh/eMBwk9KH8VmsyOdkud4QdINlKCQB6OluP3c6vthxeaBRQsUAIACa" +
                                                    "FEOQAx7VBWX4cr4ph5/MlhIwOGlYKayxpRzoldNuy+hzZ3h8VPLxWHDKaJYE" +
                                                    "k6GVZbOC/7PVcSbrx4t4Yl4OWMHxueXTK0cvvjtvqeSF8irP5dhOqACGsW6Q" +
                                                    "bLQIgfnvj7S9fejevi08QoBiZiEBjayPAkyAy+BY37i247vbt45/LblRReG+" +
                                                    "THdqqpKBPWa7UgBENAAy5vvGTXrKSKhJFXdqhAXnH1WzFl785WC18KYGM7lg" +
                                                    "mP/PG7jzk1ei3Te2/lbHtwkp7BJzLXfJxAGMc3deYVl4J9Mjs+eraUev4vcA" +
                                                    "YwHXbLWfcKgKOfkyZ4RCxlJTgK29WfCXB2pu9xy7c1akTfCmCBCT/YMHHkcO" +
                                                    "Dkqe63Rm3o3m5RFXKg+GMSJ4HsMvBO0v1ljQsAkBqTXRLK5Pd4DdNJl7Zoyk" +
                                                    "FhfR8tP5gc8+GNgnzKjx3ybNUCyd/ebPLyNHfrheALYg/gxMuY4y13Eu7yNM" +
                                                    "KX6iiK81sW66mbeW4TO1/Esa+exbWAXjgazf12ude398yHXKA50C7gjwx+Uz" +
                                                    "x6ZEl9/l/G72M+76TD6MQ7Xn8i46nfpVagh/LqHSOKpWsqXkZqylWY7FoXyy" +
                                                    "c/UllJu+dX8pJO79JgfdpgbjwSM2iDuuH2DMqNm4PAA1FeyUx0MLZ6EmHISa" +
                                                    "EOKDKGdp4P0s1j2Zy/RS01J7MatTITvYymKOTsKXy/2SJubu/Nx/AUmtrGum" +
                                                    "qIjYizzbccUbPeEghE2gaFLezSiuQhbc04Yr6XhgH987OJRYf2KhlI3CZygq" +
                                                    "o4a5QCO9RAtE3jTfLbmWF7Guxw+c+vASvTnvWZEic4eP0iDj1b0/T9m4vHvb" +
                                                    "f7gb6wM2Bbc8tfbM9VWzlbckVOQETl5d7mdq8odLuUXgIaFv9AVNneNK1lAt" +
                                                    "tMqsKysL3k+uwwrnfHyEtZdZ9yIEVxeh7QDBPBDy4YFPtPsVY9Fck1Ws5l8r" +
                                                    "JglHOwG3mJN2jqBignXw6igCFYdRD1Ki0l+esUunNu8xKB4wyrmhqlGThjZ9" +
                                                    "ywsO55FRBpV+Mq1p3nT2jMOmRZIq16hMJLdIFbVQYoiSkbLnHhtwhbsFPQRJ" +
                                                    "dZCeomL25yUzKBrtIQMPZUdeIpBQBERsaJu5NK3mdy2DtYiAtQzypBgrN7xf" +
                                                    "vtqDZRF/aOciPi2e2h3K+aE163bdf/oET58SeKL39/OHGbwzRdnlZM2MYXfL" +
                                                    "7RVePedR5YWyWTk0qGRdTbbWCuhWX7gkaU6ZlBcR/Z9M+njZyaFbvCb6GyXt" +
                                                    "1kABEQAA");
}
