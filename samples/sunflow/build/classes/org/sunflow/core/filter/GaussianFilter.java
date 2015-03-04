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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWxURRSevW23tJT+8VcQCpRChMJegaDBEhQ2LRQXaChg" +
                                                    "XJQyvTvbXrh77+Xe2XYpVoHEQHggRguCwT4YCIL8xUjQGBJeFAi+YIzGB8H4" +
                                                    "IhF54EEkouKZmd37t9uqD24ys3Nnzplzzpxzvjlz5h4qsS3UZBrazm7NoBGS" +
                                                    "oZFt2uII3WkSO7I6trgdWzZJRDVs2xtgrlNpuFD14NGbPdUSCsfRWKzrBsVU" +
                                                    "NXR7PbENrZckYqjKnW3RSMqmqDq2DfdiOU1VTY6pNm2OodEeVooaYzkVZFBB" +
                                                    "BhVkroK83KUCpjFET6eijAPr1N6BXkOhGAqbClOPohn+TUxs4VR2m3ZuAeww" +
                                                    "in1vAqM4c8ZC0x3bhc15Bh9qkgff2VL9URGqiqMqVe9g6iigBAUhcVSRIqku" +
                                                    "YtnLEwmSiKManZBEB7FUrKn9XO84qrXVbh3TtEWcQ2KTaZNYXKZ7chUKs81K" +
                                                    "K9SwHPOSKtESua+SpIa7wdYJrq3CwlY2DwaWq6CYlcQKybEUb1f1BEXTghyO" +
                                                    "jY0vAAGwlqYI7TEcUcU6hglUK3ynYb1b7qCWqncDaYmRBikUTR52U3bWJla2" +
                                                    "427SSVFdkK5dLAFVGT8IxkLR+CAZ3wm8NDngJY9/7q1denCXvkqXuM4JomhM" +
                                                    "/1HAVB9gWk+SxCK6QgRjxdzYYTzh8n4JISAeHyAWNJdevf/8vPor1wTNEwVo" +
                                                    "1nVtIwrtVI53Vd6cEp2zpIipMco0bJU532c5D//27EpzxoTMm+DsyBYjucUr" +
                                                    "6794afdpcldC5W0orBhaOgVxVKMYKVPViLWS6MTClCTaUBnRE1G+3oZKYRxT" +
                                                    "dSJm1yWTNqFtqFjjU2GDf8MRJWELdkSlMFb1pJEbm5j28HHGRAiVQkPzoZUg" +
                                                    "8eP/FMXlHiNFZKxgXdUNGWKXYEvpkYliyDZOmRp4zU7rSc3ok21LkQ2r2/lW" +
                                                    "DIvIIB5CR16J07atYr2Vf0ZYjJn/6+4ZZlt1XygExz4lmPQa5MsqQ0sQq1MZ" +
                                                    "TK9ouX+u84bkJEH2VCiaDfIiWXkRJi8i5EX88lAoxMWMY3KFZ8Ev2yHDAfsq" +
                                                    "5nS8snrr/oYiCCmzrxgOlZE2gIVZZVoUI+rCQBsHOwVise79zfsiD08+J2JR" +
                                                    "Hh6zC3KjK0f69mx6/SkJSX7wZcbBVDljb2eQ6UBjYzDpCu1bte/Og/OHBww3" +
                                                    "/XxonkWFfE6W1Q1BN1iGQhKAk+72c6fji52XBxolVAxQAfBIMYQzIE99UIYv" +
                                                    "u5tzSMlsKQGDk4aVwhpbysFbOe2xjD53hsdHJR/XgFNGs3CfBK0sG//8n62O" +
                                                    "NVk/TsQT83LACo7ErZ9eOXrx3aYlkhe0qzzXYAehAgJq3CDZYBEC898faX/7" +
                                                    "0L19m3mEAMXMQgIaWR8FQACXwbG+cW3Hd7dvHf9acqOKws2Y7tJUJQN7zHal" +
                                                    "AFxoAFnM940b9ZSRUJMq7tIIC84/qmYtuPjLwWrhTQ1mcsEw7583cOcnrUC7" +
                                                    "b2z5rZ5vE1LYdeVa7pKJAxjr7rzcsvBOpkdmz1dTj17F7wGaAoLZaj/hoBRy" +
                                                    "8mXOCCWLpaYARXuzMC8P1N7efuzOWZE2wTshQEz2Dx54HDk4KHkuzpl5d5eX" +
                                                    "R1yePBjGiOB5DL8QtL9YY0HDJgR41kazCD7dgXDTZO6ZMZJaXETrT+cHPvtg" +
                                                    "YJ8wo9Z/b7RAWXT2mz+/jBz54XoB2IL4MzDlOspcx7m8jzCl+IkivtbMuulm" +
                                                    "3lqGz9TxL2nks29ltYoHsn5fp3Xt/fEh1ykPdAq4I8Afl88cmxxddpfzu9nP" +
                                                    "uKdl8mEc6jqXd+Hp1K9SQ/hzCZXGUbWSLRo3YS3NciwOhZKdqyShsPSt+4se" +
                                                    "ccM3O+g2JRgPHrFB3HH9AGNGzcblAaipYKc8Dlo4CzXhINSEEB9EOUsD72ex" +
                                                    "7slcppealtqLWUUK2cFWFnF0Er5c5pc0IXe75/4LSGpjXQtFRcRe6NmOK97o" +
                                                    "CQchbDxFE/NuRnEVsuCeOlzxxgP7+N7BocS6EwukbBQ+Q1EZNcz5GuklWiDy" +
                                                    "pvpuyTW8XHU9fuDUh5fozaZnRYrMHT5Kg4xX9/48ecOynq3/4W6cFrApuOWp" +
                                                    "NWeur5ytvCWhIidw8ipwP1OzP1zKLQJPBn2DL2jqHVeyhuqgVWZdWVnwfnId" +
                                                    "Vjjn4yOsvcy6FyG4ugntAAjmgZAPD3yiw68Yi+barGK1/1oxSTjaCbhFnLRr" +
                                                    "BBUTrIP3RRGoOIx6kBKV/vKMXTp1ec8+8VRRzg1VjZo4tPFbXnA4z4kyqOmT" +
                                                    "aU3zprNnHDYtklS5RmUiuUWqqIUSQ5SMlD3s2IAr3CPoIUiqg/QUFbM/L5lB" +
                                                    "0WgPGXgoO/ISgYQiIGJD28ylaTW/axmsRQSsZZAnxVi54f3y1R4si/iTOhfx" +
                                                    "afGo7lTOD61eu+v+0yd4+pTAY7y/nz/B4EUpyi4na2YMu1tur/CqOY8qL5TN" +
                                                    "yqFBJetqs7VWQLdphUuSlpRJeRHR/8nEj5eeHLrFa6K/AZryEcfrEAAA");
}
