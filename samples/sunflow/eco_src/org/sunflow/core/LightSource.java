package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public interface LightSource extends RenderObject {
    public int getNumSamples();
    public void getSamples(ShadingState state);
    public void getPhoton(double randX1, double randY1, double randX2, double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power);
    public float getPower();
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170611812000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXaYwURRSuOXaXPcwuCyzIsbusqEFgWoOKskRBXGTZEcYd" +
                               "WHE9ltrumpmG7q62\nunp3diUeMXJ4EzXRRNEfJAiimKhBEw+Mt/5RE2Ni4h" +
                               "USNVF/+Mcj+sNXVdMzPb2HB5N0TXfVq3fV\ne997dexnVOMxtFD3UnzMJV5q" +
                               "fTaDmUeM9Rb2vK0wNaS/U1OfOdzn0DiKpVHcNDhqTuueZmCONdPQ\neq/sLj" +
                               "K0zKXWWN6iPEWKPLXTuqjEb1P6ogkMrz14ovWOQ8mOOKpJo2bsOJRjblKnxy" +
                               "K2x1FLeice\nwZrPTUtLmx7vTqMziOPb66njcexw72Z0K0qkUa2rC54cLU4H" +
                               "wjUQrrmYYVuT4rWMFAscZjHCsekQ\nY11ZHOxcXr0T1C7t659IDUxmiMUBME" +
                               "dqAFZ3lq1W1k4w1U08PXDx7qeOJFDzIGo2naxgpoMlHOQN\noiab2MOEeesM" +
                               "gxiDaKZDiJElzMSWOS6lDqJWz8w7mPuMeP3Eo9aIIGz1fJcwKTOYTKMmXdjE" +
                               "fJ1T\nVvZRziSWEXzV5CycB7PbKmYrczeIeTCwwQTFWA7rJNiS3GU6cOId0R" +
                               "1lG5f0AQFsrbMJL9CyqKSD\nYQK1qrO0sJPXspyZTh5Ia6gPUjiaPyVT4WsX" +
                               "67twngxxNC9Kl1FLQFUvHSG2cDQnSiY5wSnNj5xS\n6HyWtf267+nHX18rYz" +
                               "tpEN0S+jfApvbIpn6SI4w4OlEbf/dTD/de5y+MIwTEcyLEimbd2Se2pX94\n" +
                               "o0PRLJiEZsvwTqLzIX3zgY7+W66iKCHUmOFSzxSHX2W5TIdMaaW76ELWtpU5" +
                               "isVUsHiy/93rbj9K\nfoyjhl5Uq1PLtyGOZurUdk2LsKuIQxjmxOhF9cQx1s" +
                               "v1XlQH72kIeTW7JZfzCO9FSUtO1VL5DS7K\nAQvhonp4N50cDd5dzAvyvegi" +
                               "hOrgQTF4LkHq1ygGjrSU5vlOzqKjmsd0jbJ8+VunjEC65ws8S32m\nk5QIHJ" +
                               "ejPq1AbaJhHTumQ7W8Camq0xUGGRH//41dUWjYOhqLCciLpq4FUb+RWgZhQ/" +
                               "rhUx/u7unb\nv0+FhQjlkm0cLQQpqZKUlJCSCklBsZhkPltIU6cCPt0F2Qk4" +
                               "1rQ0e+OmHfu6EhAO7mhSeKQo02V+\n8AEbI1rJxPz9ztrzP3+18R2pTZDDzS" +
                               "GUzBKuImJmRe5WRgjMf/lo5qFHft57vRSqpCY4moGHASmw\nzjmqL6c8B0T1" +
                               "hy1TD2sVk+9zOVo0we5+iBPCVAgL3RdNlWYSIvZu/6VpD377RpUMrdWh2wPw" +
                               "/v3Y\nW+TcNfd9O4nP6zl1V1hkhFghzZJCJIRASVqPTq+WCNQrq4QO+Xf3kW" +
                               "dO8E+WrVYiz5u6QkU3nrf6\nqfGO1cfviaP45JVDaAG1q0FwyIhyU64IHREn" +
                               "RFnPHFlwTaJgvh+XICkAZxJwrd7UHXYHCAV9fOYI\nx4qZJhDaFY1lRnViQM" +
                               "moyD10ZnPiWjRwII6SgJpQKaRNAMLtUeFVQNcdBJwQVZdGjTnKbGyJpQDp\n" +
                               "G3iB0dHKjEyyFvk+K8j8dnhaS1Ag/8XqHFeMbSopJf0iOXaqMI2L9y4xLBHR" +
                               "dU4ltAGyLIg5cRBL\ntjk2NcyciYctIpLsr+azL3jpp/tbVBRZMBOczPJ/Zl" +
                               "CZP/MKdPtHN/3WLtnEdFEyK+lWIVNZN6vC\neR1jeEzoUbzj00WPvYefAEQH" +
                               "FPXMcSKBEZVSXSi1Rlq7So7dkbXLxLCSozPyhG/27Sy2XYt4IGte\nuFNjpg" +
                               "2IPyJP6tSertfe3/bkXhXsS6dpx8K7hvTbvv5mV+KBN4fVvmjVixAfaD/03Q" +
                               "un+mcr76rW\n4KwJ1Tm8R7UH0qZmV5zj4ukkSOq3ly0+dmv/V1IjsW8pRwkA" +
                               "Kdi7fAqrJunWhvTdR/Nd/s0fvCJ1\nbcThti+MnVdjV6nXIoYLhYpzo+i9EX" +
                               "sFoLvw5OYbWqyTfwLHQeCoQ5fkbWGAgcUq5C1R19R98eZb\nbTs+SaD4BtRg" +
                               "UWxswKI1gwILGUO8AtSaonv5WpkULaMzxCjPH1UXBfGxojqdUvB0ltKp8/+k" +
                               "U3Xg\nxf4J6bMFbEDrJppXIvlvnSZ0t4thC0cNELpB3Iqpta6S28NRcoSaql" +
                               "PcKIaMWuj7X74Yg0cr+UI7\nfV/USYK6qMa1BoXSKAE3VxkCj80Ne8yGTgh6" +
                               "MYjYlcH6vAnrA0SEwspJGZg29LYCoyiTBpBpnC3t\n3AEVEpydKVBOHTExWP" +
                               "EtPh3fLoBnVcm3q07ft2HFx6dZ2y0GH7oUYRQdJSx6GlCSKOYVI0f+rZFF\n" +
                               "jhpD7ZoA73kTrnDq2qGnv7jlhl/Tn/0hK2b5atAI/XnOt6xQQQ4X51qXkZwp" +
                               "rWhUqOLKvz1wrY2m\nFeSB+JMK3qXI9oN+ITIOgajewkT3Ah4CkXi9zw0iqE" +
                               "Xij7hnpYKOLGy4sPSsKvSUt+SgSfHVPXlI\n3/7s9Z3Fe7Y+KDufGrhfj4/L" +
                               "CxHc71TlLzc6i6fkFvD6GD1/fODV5y4NcFwC7OxQ3FSF3ka1Os1B\nQnM1eQ" +
                               "nvsV0ui+74y3NfXHP44Fdx2ez+DU8TmI/cEAAA");
}
