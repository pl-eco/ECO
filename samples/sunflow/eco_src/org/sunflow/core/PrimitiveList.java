package org.sunflow.core;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface PrimitiveList extends RenderObject {
    public BoundingBox getWorldBounds(Matrix4 o2w);
    public int getNumPrimitives();
    public float getPrimitiveBound(int primID, int i);
    public void intersectPrimitive(Ray r, int primID, IntersectionState state);
    public void prepareShadingState(ShadingState state);
    public PrimitiveList getBakingPrimitives();
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170612854000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAK1YfWwcRxWfu/NH/FH8kcT5qGPHbkKUOrmlkFBhRyKJ7STX" +
                               "XJzjnE+3wRnvzt1t\nsrez3Z2zz04oDZWS0ArSiIJAglJBRJpQKBKggFQgVT" +
                               "8olD9aJIQUidIqUosE/YN/ShH8wXszt3d7\ne7YLNZZ2bnfmzXvzvn7vjZ95" +
                               "l9R7LunWvbiYdZgXHx5PUddjxrBFPe8QTE3qL9U3pa7ut3mURJIk\nahqCtC" +
                               "V1TzOooJppaImRoaJLBhxuzWYtLuKsKOKnrO0lfvclt9cwPPrkjc5zV+p6o6" +
                               "Q+SdqobXNB\nhcntUYvlPUHak6foNNUKwrS0pOmJoSS5g9mF/DC3PUFt4T1I" +
                               "HiKxJGlwdOQpSF/SF66BcM2hLs1r\nUryWkmKBw3KXCWrazNhVFgc7t1TvhG" +
                               "OX9qVrqYHJMlw8AurIE4DW68taK21rVHViTx/55NmnrsVI\n2wRpM+1xZKaD" +
                               "JgLkTZDWPMtPMdfbZRjMmCAdNmPGOHNNaplzUuoE6fTMrE1FwWVemnncmkbC" +
                               "Tq/g\nMFfK9CeTpFVHndyCLrhbtlHGZJbhf9VnLJoFtbsqait19+A8KNhsws" +
                               "HcDNWZv6XutGmDx3vDO8o6\nbtgPBLC1Mc9EjpdF1dkUJkin8qVF7aw2LlzT" +
                               "zgJpPS+AFEHWLsgUbe1Q/TTNsklBVofpUmoJqJqk\nIXCLICvDZJITeGltyE" +
                               "sB/wx0vXfx6W/+cqeM7TqD6Raevxk29YQ2pVmGuczWmdr4fiH+ROJ4oTtK\n" +
                               "CBCvDBErml0bbxxO/uVXvYrmznloDk6dYrqY1Mcu96bP7OUkhsdY5nDPROdX" +
                               "aS7TIVVaGSo6kLVd\nZY64GPcXb6ZfPv7wdfbXKGlOkAadW4U8xFGHzvOOaT" +
                               "F3L7OZSwUzEqSJ2cawXE+QRnhPQsir2YOZ\njMdEgtRZcqqBy28wUQZYoIma" +
                               "4N20M9x/d6jIyfeiQwhphIdE4Bkl6q8JB0HuiWtewc5YfEbzXF3j\nbrb8rX" +
                               "OXaSnXzIMO0wzzPo6h4whyQMvxPNOoTm3T5lrWhGTV+VaDTePv/8qwiKfsnI" +
                               "lEEPbC6WtB\n5O/jlsHcSf3q7d+eHd3/xYsqNDCcS/oJ0gNy4iU5cZQTr5JD" +
                               "IhHJfgXKU74By56GHIXF1s3jJ+47\nebE/BkHhzNShXYoyadb6H7AxdC6Znu" +
                               "8/0vCxPz7X8pI8j5/JbQGsHGdCxUVHRe4hlzGY/9PXU1/5\n6rsX7pdCldSY" +
                               "IMvoFOAF1YUgTeXEF4CrhSnL1IOnisj3VYKsq9E8DdHCXBXIePZ1CyWbBIoL" +
                               "x/7e\nep6+eEKlRGd1AI8CyL8z+wLbtONLb81j9SbBna0Wm2ZW4GQNKBLCoC" +
                               "RtVOcHJA4lZK3QIQsfvfb9\nG+L1gUEl8u6F61R4492DT831Dj77WJRE568f" +
                               "eAqoYM3IIYVFp1wXekNGCLPumL7zM7Gc+UpUQiXC\nzjwQW71pKGgOEArnKb" +
                               "g2GhZnWkFofziaXa4zAwpHRe6VNW2xo+TI5SipA+yEeiF1AijuCQuvgrsh\n" +
                               "P+BQVGOStGS4m6cWLvl43yxyLp+pzMg0a5fvy8FNLRjaH4dnZQkQ5C+urnRw" +
                               "7FJpKenXyXG9CtMo\nvvfjsAGj66OV0AbgsiDm0BEbDtt5bpgZk05ZMgP/3b" +
                               "bxnp/+7cvtKoosmPE9s+WDGVTm1+wmD7/6\n2X/0SDYRHQtnJd0qZCrrllc4" +
                               "73JdOovnKJ77/bpv/Jp+C3AdsNQz55iEx0h1Uq0OJlUegDR+gEKt\nLG6TRt" +
                               "khye6V4xBarYQT+L0Th08I8pEsE0e5axm7ecE2PJ9xdw1juQ5VeDdHa25ZIB" +
                               "nm6X4m9bPX\ns/2FB3/zc2mMFhpso4IodIA6Q8r5OGxDMavCOLiPejmg23Zz" +
                               "7IF26+a/gOMEcNSh6/AOuoAmxSoM\nK1HXN956/oWuk6/HSHQPabY4NfZQbH" +
                               "WgYEHsMS8HuF10Pr1Thlf7zDIcpaFINbzix9bqwOyDp7sU\nmN0fJjAX9lBq" +
                               "kbU0Dvuh4QXvjRXy5Rrigc1WBztyfwHT6/b5/l+8cvjbFxScbV6k7Q7umtQ/" +
                               "/+c3\nT8cuPT+l9oW7mxDx5Z4rb//4dnqFyh/VAt5V04UF96g2UKrV5qDT+x" +
                               "aTIKlfHOh75qH0G/JEuG+z\nIDGz1N6P4JBUht37oXy6HZ5NJZ9uWrpPoxWC" +
                               "Y3KQPIxFvJvBAbrXDvBuWXmVn7hyyFEijgvEVk4D\nep9cit5jJaD1AXeJes" +
                               "dUs+Ajyora+k9npT18iv4aigS2Fp6CSrz/MHkMbxHTSY7Q/naa/tZKboRs\n" +
                               "VzfNTaNiOr4U090Lz2DJdINLN13kg1qn8RxFLK7Y5PwiNnkUh3OCLHdcBjdc" +
                               "FtyMS2cqRvjCUowQ\nh2ekZISR/y8WPrHI2tdwuATqQbbspoA42QAcfg7tSN" +
                               "YEIHGM2/IeY+oIJ0W/xuA9M+6yDBZm7H+K\ns29turX+d+3Dr6oSnhNkY+BG" +
                               "WqLUEvY012Ux20dtA+5IqqJ3zyvwqEsduHO+9ubbJy4NvPMyopdT\nsf3j/6" +
                               "3ti4LcUXVrQImra/6foO7AevLWmQfeS/7hn7JxK99TW+CymClYVqAvDPaIDR" +
                               "AqGVOat0WV\nZEf+fBdKTjgYIZPwRx7xO4rse4K0BMgEaSy9BYmuAWgDEb5e" +
                               "d/xQb684w78YBFVHTe+qKlzyXzZ+\nr1xQ/7SZ1I/94P71xccOPS4b8Hrdon" +
                               "NzyKY5SRpVA1rut/sW5Obzeo386Nkjz/3wU36xkd3JikBA\nV+XEiFpdxJXQ" +
                               "48/fSY7mHSF7v7mfrfrJjqtPviEDxPkPHgG/lmkTAAA=");
}
