package org.sunflow.core;
import org.sunflow.image.Color;
public interface Shader extends RenderObject {
    public Color getRadiance(ShadingState state);
    public void scatterPhoton(ShadingState state, Color power);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXhu+19K//gVaKE/kJTCbYhBg0UCNIW2HrRpwYQa" +
                                "OKa7c3dLd3eW2bn2KNYgiYHwQIwWBaN9gijKX4wEjSHhSSD4gjEaHwTfNCoP" +
                                "vOgDKn4ze3u3t20hMfGSnZv95vv/nb3wAJW4DLU71DyUNCmPkgyPHjDXR/kh" +
                                "h7jR3tj6fsxconea2HV3ASyuNV+p+ePRm6laBZUOoXnYtinH3KC2O0Bcao4S" +
                                "PYZq8tAuk1guR7WxA3gUq2lumGrMcHlHDM0JkHLUGvNVUEEFFVRQpQrqljwW" +
                                "EM0ldtrqFBTY5u5B9BqKxFCpown1OGoqZOJghq0sm35pAXAoF+8vg1GSOMPQ" +
                                "ipztns3TDD7Vrk6+u6/20yJUM4RqDHtQqKOBEhyEDKEqi1jDhLlbdJ3oQ6jO" +
                                "JkQfJMzApjEu9R5C9a6RtDFPM5JzkgCmHcKkzLznqjRhG0trnLKceQmDmLr/" +
                                "VpIwcRJsXZi31bNwm4CDgZUGKMYSWCM+SfGIYescLQ9T5GxsfQkQgLTMIjxF" +
                                "c6KKbQwAVO/FzsR2Uh3kzLCTgFpC0yCFoyWzMhW+drA2gpMkztHiMF6/dwRY" +
                                "FdIRgoSjBWE0yQmitCQUpUB8HuzcePKw3W0rUmedaKbQvxyIGkNEAyRBGLE1" +
                                "4hFWrY69gxdeP64gBMgLQsgezrVXH25e03jjloezdAacvuEDRONx7exw9d1l" +
                                "nW0bioQa5Q51DRH8Astl+vdnTzoyDlTewhxHcRj1D28MfLXnyMfkNwVV9qBS" +
                                "jZppC/KoTqOWY5iEbSc2YZgTvQdVEFvvlOc9qAz2McMmHrQvkXAJ70HFpgSV" +
                                "UvkOLkoAC+GiMtgbdoL6ewfzlNxnHIRQGTwoAk8D8n7lYuFon5qiFlGxhm3D" +
                                "pirkLsFMS6lEo3FGHKp2dfapw+DllIXZiKu6aTth0rG4lnY5tVSXaSplSR+s" +
                                "apQRdTCFdcKiIs+c/11CRthYOxaJgPuXhYvfhLrppibgxrXJ9Nauh5fid5Rc" +
                                "MWS9w9EiEBDNCogKAVFPAIpEJN/5QpAXUgjICJQ2NL2qtsG9vfuPNxdBLjlj" +
                                "xcKdGVlri/0XIAwpJKt62xc3zlx9r32DEmwANYGWOki4l051ebm7GCEA//F0" +
                                "/9unHhx7RQoFjJaZBLSKtROSCzomdJ43bh384f69s98qOUWLOHTZ9LBpaByV" +
                                "42FoUVjjHFXkek3QkIjcL+CoYZqXBiAvCfNKRpjbMFtZy5Z09ujklN53bp1X" +
                                "fPWFpdIFk+Did39/HT390+0ZIlTBqbPWJKPEDGimCJGQRllpXRrdITtejxwo" +
                                "GtT7ifOfXON321/wRK6efS6GCW8e/XXJrk2p/QpSCmebkA6gSkHZLyZSbvIs" +
                                "DxkfZnl+x4Xb21dpbymoKNvYZmjihUQdQTeAUEZg6tjCoQJSCUKbwxnPqEZ0" +
                                "GE15uatX4Kvx6xOtCiqG7gwTiWPoINDsG8PCCxpqh5+bQlQJOCFBmYVNceRP" +
                                "lEqeYnQsD5GlWC33dRAe8aBl/sb/F6fzHLHO90pX4i+Va6NYmrzYim2zWFpE" +
                                "Zq3KVwK0RxPyTQSjdbdtUd1IGHjYJKIm/6pZue7q7ydrvQwyAeJHZ83TGeTh" +
                                "z2xFR+7s+7NRsoloYjznqzOP5hXpvDznLYzhQ0KPzOvfNJy5iT+A6QEd2zXG" +
                                "iWzCkacVlGg7MJHFnYRI72yUuM/LtUO4L9tbxPtmsTzL0Zwk4QNAJ8Ltsy7o" +
                                "aIYFo1mYTZkzjZMErM0FbY4AbsoGzg/gfwpaoeJKHuFFsWyVfHqfYF5MLF0c" +
                                "zXU1zKEv9acoh5Ax1PaE6y0zLJi4o9krgTpRf3/k/V8ueuUfvj+EkMnxyROP" +
                                "oycnlcAlq2XaPSdI4120pLJzPQc+hl8Enn/EIywRAG/Q1ndmp/2K3Lh3HJHa" +
                                "TU9SS4rY9vPliS8/mjimZD3TxlHxKDX06cHMQGP3RpfIzMXT7sLe/U27NFVT" +
                                "vmhq9/eyJeTuWBVw0UmkTTPQcYLdp9RhJGFIBSq8Snfk3xB8D4QTGRQUf1K/" +
                                "PR7aXkjUABpHZdldEGk/R0WAJLYYvIMK5qkTnq4tBYkgvw/83pv2vhDi2uWp" +
                                "3p2HHz53TjbyEviyGB+X90m4HnsNLde/m2bl5vMq7W57VH2lYqUfh2qx1AcK" +
                                "IqDb8pn7TZflcNkhxj9f9NnGD6fuyaH8LzeFHBu4DQAA");
}
