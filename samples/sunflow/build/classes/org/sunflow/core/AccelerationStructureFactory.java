package org.sunflow.core;
import org.sunflow.core.accel.BoundingIntervalHierarchy;
import org.sunflow.core.accel.KDTree;
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.core.accel.UniformGrid;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class AccelerationStructureFactory {
    static final AccelerationStructure create(String name, int n, boolean primitives) {
        if (name ==
              null ||
              name.
              equals(
                "auto")) {
            if (primitives) {
                if (n >
                      20000000)
                    return new UniformGrid(
                      );
                else
                    if (n >
                          2000000)
                        return new BoundingIntervalHierarchy(
                          );
                    else
                        if (n >
                              2)
                            return new KDTree(
                              );
                        else
                            return new NullAccelerator(
                              );
            }
            else {
                if (n >
                      2)
                    return new BoundingIntervalHierarchy(
                      );
                else
                    return new NullAccelerator(
                      );
            }
        }
        else
            if (name.
                  equals(
                    "uniformgrid"))
                return new UniformGrid(
                  );
            else
                if (name.
                      equals(
                        "null"))
                    return new NullAccelerator(
                      );
                else
                    if (name.
                          equals(
                            "kdtree"))
                        return new KDTree(
                          );
                    else
                        if (name.
                              equals(
                                "bih"))
                            return new BoundingIntervalHierarchy(
                              );
                        else {
                            UI.
                              printWarning(
                                Module.
                                  ACCEL,
                                "Unrecognized intersection accelerator \"%s\" - using auto",
                                name);
                            return create(
                                     null,
                                     n,
                                     primitives);
                        }
    }
    public AccelerationStructureFactory() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfXr9L4dryVRH6DVhKbyUREyxRy6WF1gMaCiSU" +
                                                    "QJnuzrVL53aX2bn2WqwCiSnhgRgtCEb7YCCI8hUjQWNI+iQQfMEYjQ+CbxKV" +
                                                    "B17QBBX/M7t3e7e9Vn3xkpvbnfl/f/zmfxceoHyboSbLpCP91OQhkuCh/XRt" +
                                                    "iI9YxA51RtZ2YWYTLUyxbW+HvV617krw0eM3B8oCqKAHzceGYXLMddOwtxHb" +
                                                    "pENEi6Cgt9tGSczmqCyyHw9hJc51qkR0m7dE0Jw0Vo4aIkkTFDBBARMUaYLS" +
                                                    "6lEB01xixGNhwYENbh9Ar6GcCCqwVGEeR7WZQizMcMwV0yU9AAlF4n0nOCWZ" +
                                                    "EwzVpHx3fJ7m8IkmZeKdvWWf5KJgDwrqRrcwRwUjOCjpQaUxEusjzG7VNKL1" +
                                                    "oHKDEK2bMB1TfVTa3YMqbL3fwDzOSCpIYjNuESZ1epErVYVvLK5yk6Xci+qE" +
                                                    "asm3/CjF/eDrIs9Xx8N2sQ8OluhgGItilSRZ8gZ1Q+Oo2s+R8rHhFSAA1sIY" +
                                                    "4QNmSlWegWEDVTi5o9joV7o5041+IM0346CFoyUzChWxtrA6iPtJL0eVfrou" +
                                                    "5wioimUgBAtHC/1kUhJkaYkvS2n5ebBl/fGDxiYjIG3WiEqF/UXAVOVj2kai" +
                                                    "hBFDJQ5j6arISbzo+tEAQkC80Efs0Fx79eHLq6umbjo0T2eh2dq3n6i8Vz3T" +
                                                    "N+/O0nDjulxhRpFl2rpIfobnsvy73JOWhAWdtyglURyGkodT277cdegj8ksA" +
                                                    "lXSgAtWk8RjUUblqxiydEraRGIRhTrQOVEwMLSzPO1AhPEd0gzi7W6NRm/AO" +
                                                    "lEflVoEp3yFEURAhQlQIz7oRNZPPFuYD8jlhIfeTD98S91n+coSVATNGFKxi" +
                                                    "QzdMBWqXYKYOKEQ1FRvHLApZs+NGlJrDis1UxWT9qXfVZERpVVVChfngZ7es" +
                                                    "dWiMdixKfiQkSs36P5QkhKdlwzk5kISlfgig0D2bTKoR1qtOxDe0PbzUezuQ" +
                                                    "agk3Rhw1g9qQqzYk1IZmU4tycqS2BUK9k25I1iC0PQBiaWP3ns59R+tyoc6s" +
                                                    "4TyItCCtA39dm9pUM+xhQ4dEQBUKtPKD3eOh38+95BSoMjOQZ+VGU6eGD+98" +
                                                    "/dkACmQisvARtkoEe5fA0RReNvg7MZvc4Pj9R5dPjpleT2ZAvAsV0zlFq9f5" +
                                                    "s8FMlWgQR0/8qhp8tff6WEMA5QF+AGZyDDUOcFTl15HR8i1J+BS+5IPDUZPF" +
                                                    "MBVHScwr4QPMHPZ2ZJnME0uFUzEigT4DJfK2fz51+uq7TesC6SAdTLv2ugl3" +
                                                    "Wr7cy/92Rgjs/3Cq6+0TD8Z3y+QDRX02BQ1iDQMAQDYgYm/cPPD9vbtnvgmk" +
                                                    "CgYlgHWFJxxQgQIyiWw27DBipqZHddxHiSi3P4LL11z99XiZkx8KO8n0rv5n" +
                                                    "Ad7+UxvQodt7f6uSYnJUcSt5Dntkjt/zPcmtjOERYUfi8NfLTt/A7wNoAlDZ" +
                                                    "+ihxsMd1SBjVLBPxjFxX+84UsdRY084ScqcyjbI+YwesWTbThSIvwzNHJia1" +
                                                    "rWfXOF1VkQnSbTCDXPz2z69Cp368lQUVirlpNVMyRGiaTtHNyzK6ebO8a72a" +
                                                    "Pnb+42v8TtMLjspVMzeyn/HGkZ+XbH9xYN9/6OFqn/N+kec3X7i1cYX6VgDl" +
                                                    "ptp32viQydSSHgZQyghAnyECKnZKZBKqpAHlEI6VIg3N8J3jXi7yV5zOt8S6" +
                                                    "wG02sdbKtUEsK2UsAxwV2HII49BpuoFpwlcjuZIuV74vhOlTVp4YYELOAAPJ" +
                                                    "aJxl5GV6DG7hIXdMUMYq7g2+d/+ikxj/TOEjJkcnjj0JHZ8IpA1e9dNmn3Qe" +
                                                    "Z/iS/s514vMEPjnw/Ut8RVzEhnP5VoTdCaAmNQJYlijn2tnMkiraf7o89sWH" +
                                                    "Y+MBtyOe4yhXd4fodrF0OlGMcFTYZ5qUYEPShWfpv26xrId0qIyALcl4r/h3" +
                                                    "92KWvuVo6WxXqICRymnzujNjqpcmg0WLJ3d8Jy+F1BxYDMNYNE5pWm2m12mB" +
                                                    "xUhUl84UO1hvyZ8eqBq/FxzliR9p9S6HbA9Hc9LIIHTuUzrRPog0EIlHbGWp" +
                                                    "SWeCTKAMiLL8gFWfUbDyv02ye+POv5te9fJk55aDD58/K6EgH/4VjY7KWRhG" +
                                                    "e+eqSyFA7YzSkrIKNjU+nneleHmyXjIuQZ9t1dkvjbaYxSXMj362+NP15ybv" +
                                                    "ysvqb/PFIZh0DgAA");
}
