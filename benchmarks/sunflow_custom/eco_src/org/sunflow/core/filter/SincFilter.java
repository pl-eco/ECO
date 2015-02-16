package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class SincFilter implements Filter {
    private float s;
    public SincFilter(float size) { super();
                                    s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return this.sinc1d(x) * this.sinc1d(
                                                                        y);
    }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-4F) return 1.0F;
                                    x *= Math.PI;
                                    return (float) Math.sin(x) / x; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026716000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Ye2wcxRkf3/kRPyqfncQJIcSx4wTy4LZICWpjVEhdmzi5" +
                                                   "JMZOHHACznh27rzJ\n3u6yO2dfnCglaktSEI8IkECCEKFICW8kQGklHkE8S1" +
                                                   "QJkAAJidAqUluppVJViaZq/+g33+ze3u6d\nXRRLtzu78833mO/3/eZbv/At" +
                                                   "qfNcspR5aXHQ4V66b2SIuh7X+0zqeTvh1Th7v65x6MxWy06QmgxJ\nGLogrR" +
                                                   "nmaToVVDN0bfBnvUWXrHVs82DOtEWaF0V6v7nB17cls6FC4e6T59qPnq7tTJ" +
                                                   "C6DGmllmUL\nKgzb6jd53hMkldlPp6hWEIapZQxP9GbID7hVyPfZlieoJby7" +
                                                   "yRGSzJB6h0mdgnRlAuMaGNcc6tK8\nhua1ITQLGua7XFDD4vqmkjlYuS66Et" +
                                                   "z21w1XSoOSeXJyFMJBDyDq5aWoVbQVoTrJs6M3Hj71bJK0\njpFWwxqRyhhE" +
                                                   "IsDeGGnJ8/wEd71Nus71MdJmca6PcNegpjGDVsdIu2fkLCoKLveGuWebU1Kw" +
                                                   "3Ss4\n3EWbwcsMaWEyJrfAhO2W9ihrcFMPnuqyJs1B2B1h2CrcAfkeAmwywD" +
                                                   "E3SxkPltQeMCzIeGd8RSnG\nnq0gAEsb8lxM2iVTtRaFF6Rd5dKkVk4bEa5h" +
                                                   "5UC0zi6AFUGWzKpU7rVD2QGa4+OCLI7LDakpkGrE\njZBLBFkYF0NNkKUlsS" +
                                                   "yV5Wdtx3fHzz751i2I7VqdM1P63wSLlsUWDfMsd7nFuFp4uZB+dPCOwtIE\n" +
                                                   "ISC8MCasZDatPLcr85e3O5XM1VVkdkzs50yMs+0nOocP3WqTpHRjnmN7hkx+" +
                                                   "JHIshyF/prfoQNV2\nlDTKyXQweX74gzvueY7/NUGaBkk9s81CHnDUxuy8Y5" +
                                                   "jcvZVb3KWC64OkkVt6H84PkgYYZwDy6u2O\nbNbjYpDUmviq3sZn2KIsqJBb" +
                                                   "1Ahjw8rawdihYhLHRYcQ0gA/shp+dUT94V2QDWnNK1hZ057WPJdp\ntpsrPT" +
                                                   "Pb5RpoB2RoI4bFBnCYlvBxBLlNm7TzXKOMWoZlazkDCpbZ1+t8St6vRGlRet" +
                                                   "s+XVMj6S9e\nxiZUwGbb1Lk7zs5c+vhw/9ZfH1cQkbD24xSkG2ylfVtpaSut" +
                                                   "bKVDW6SmBk0skDZVnmCXD0C9ArO1\nrB65c8u+491JAIgzXQtbJEW7ISLfkX" +
                                                   "5m94VFPYj8xwBZi5/Zcyx9+czNClna7NxbdXXzJy9eOPXP\nljUJkqhOjDJA" +
                                                   "oOYmqWZIsmmJ8HripVRN/9/v2/bqFxe+vi4sKkF6Kmq9cqWs1e54KlybcR3Y" +
                                                   "L1R/\n+qrW5G4yeiJBaoEAgPTQf+CTZXEbkZrtDfhPxtKQIc1Z281TU04FpN" +
                                                   "UkJl17OnyDGEnheD4kp1mC\neCH85vmoxrucXejIa4fClMx2LArk18u/qP/h" +
                                                   "l280v4/bElBxa9lhN8KFKuy2ECw7Xc7h/dePDz3y\n2LfH9iBSfKgIOAELE6" +
                                                   "bBirBkVbgEKtoEVpGJ7Nll5W3dyBp0wuQScf9tXXnD6397MKVSY8KbILPr\n" +
                                                   "/r+C8P1VPyX3XLjrX8tQTQ2TJ0oYRiimopkfat7kuvSg9KN49LNrnviQPgWE" +
                                                   "ByTjGTMceaOmVASL\nyzsS18gDs01hGi/d2/3mR7uePqagv3qOtqN81Tj7+T" +
                                                   "d/OJB86J0JtS7O7jHhE8tO/+nVS8ML1Dap\nI3BFxSlUvkYdgwiAVkcmpGsu" +
                                                   "Cyj93tquF44MX/Q9ao+SeT80PH8++C6/9qYH/liFeQA+NhVoTkN8\nrsFrWg" +
                                                   "IS95Dg3EZ56QZv1s2yT1X6nHF2+Llcd+Hu3/0W7TbT8oapHK7bqKMCTsnLCh" +
                                                   "n0ojjLbabe\nJMitP799b8o8/x/QOAYaGfQX3g4X6LUYAbsvXdfw1Tvvduz7" +
                                                   "NEkSA6QJAtUHKPIEaYQC5d4kMHPR\nufkWrMHUtKzCFIZMcBOW+BtQLHuSkJ" +
                                                   "oDKwOySwoZZnxi3dnMxzuewg2YlSCrwCimZ+atXScv/15c\nRD0hU8nVXcXK" +
                                                   "Ywc6y3Dtj76Yaqt/5el8gjSMkRTze99RahYkH4xBq+YFDTH0x5H5aNuleoze" +
                                                   "EhMv\njeO4zGycI0PQwVhKy3FLjBZb5G4vgF+9T4v1cVqsITjYjEt68Hptic" +
                                                   "QaHNeYorIfhizJqfWKReW1\nV14GVSJ/Ui3h6MqqSKLleJEgiyrOZnUgS5he" +
                                                   "M1tDiHV57PZ/tNxL37sz4RfRjQJwZzvXm3yKm2Wm\nklJT5Kzehi1wmMP7nn" +
                                                   "3+nPh07UZV4Wtmx1984ZqNp2Y6N758/xWc0J2x2OKq26auvi05aXyUwC5d\n" +
                                                   "QaKiu48u6o0CoQn8KbjWzggclkdPySUBLoJ7xSkZJq46dd01x9w+edkD0Mlx" +
                                                   "MQJnB6ImBMzeOQAT\nYQh82B31fFUgG9y/l+cJ1JgowXc9ik7OEcN+eYFSTU" +
                                                   "IMMf+zV+g/bnSXX4xBUVb3f0vMM//gDV13\n53Ad/bWh/fCgzb1Bj3nvfF/v" +
                                                   "oeCbwkZZdgqLKz6n1Scgy3x1aO93mc//jS1f6TOtGb6VsgXTLCep\nsnG94/" +
                                                   "KsgR43K8py8HaoGjmoxl3ID2Y5QEdnlPwRQVJxeUFq5a1c7KggzWVigE5/VC" +
                                                   "70S0g4CMnh\nr5yAqlJ4AkqyTiuyLkb2Se7Migh34H84gvouqP9xjLPbX9yz" +
                                                   "vHj/zoeRNOqYSWdm8GMWvs1Vq1vi\niK5ZtQW6PiGvvDz6xks/DjgQj/gFxZ" +
                                                   "CZIxDsVbNz5B14qXp/2Z93BHaEM79Z9NpNZ05eTGCH+z9r\n0xYdmBIAAA==");
}
