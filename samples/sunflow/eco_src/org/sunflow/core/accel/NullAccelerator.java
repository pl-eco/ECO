package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
public class NullAccelerator implements AccelerationStructure {
    private PrimitiveList primitives;
    private int n;
    public NullAccelerator() { super();
                               primitives = null;
                               n = 0; }
    public void build(PrimitiveList primitives) { this.primitives = primitives;
                                                  n = primitives.getNumPrimitives();
    }
    public void intersect(Ray r, IntersectionState state) { for (int i =
                                                                   0;
                                                                 i <
                                                                   n;
                                                                 i++)
                                                                primitives.
                                                                  intersectPrimitive(
                                                                    r,
                                                                    i,
                                                                    state);
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170005348000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ0YfWzc1P3dXT6aS6a7pG1aSts0aVpGW84DrdPaMLVZSOFa" +
                                                   "tw2XJkAKhBf73cWN\nz3bt58slVKzVJlJasVGxSZs0SjVVavlGAtRNYqwI2B" +
                                                   "jVJJgESEh0myptSHxI0yTWaftjv/eefT77\n7lJEJNvv3vt9f788+zlqdmy0" +
                                                   "WnEydM4iTmZodATbDlGHdOw4B2BrUnmruW3k3B7DjKOYjOKaSlFK\nVhxJxR" +
                                                   "RLmiplbxso22izZepzBd2kGVKmmUP6Vo/ebnlrDcG7Tl/oOna2qSeOmmWUwo" +
                                                   "ZhUkw10xjW\nSdGhKC0fwiUsuVTTJVlz6ICMvkEMtzhkGg7FBnUOo4dQQkYt" +
                                                   "lsJoUtQr+8wlYC5Z2MZFibOXRjhb\noLDUJhRrBlEHK+wAc0sYE8T28HK10E" +
                                                   "BkCTscB3W4BKD1uorWQtsaVa3E+fHvHDnzVAKlJlBKM0YZ\nMQU0ocBvAnUU" +
                                                   "SXGK2M6gqhJ1AnUahKijxNawrs1zrhOoy9EKBqauTZwccUy9xAC7HNciNufp" +
                                                   "b8qo\nQ2E62a5CTbtio7xGdNX/1ZzXcQHU7g7UFuruYvugYFIDwew8VoiP0j" +
                                                   "SjGeDxnihGRcf+PQAAqK1F\nQqfNCqsmA8MG6hK+1LFRkEaprRkFAG02XeBC" +
                                                   "0aqGRJmtLazM4AKZpGhlFG5EHAFUGzcEQ6FoeRSM\nUwIvrYp4qco/m7u/PH" +
                                                   "7+l6/t5LHdpBJFZ/InAWltBClH8sQmhkIE4lU389PsPe7qOEIAvDwCLGAG\n" +
                                                   "N1wYkz/5XY+Aub4OzP6pQ0Shk8q+Uz25B283UYKJscQyHY05P6Q5T4cR72Sg" +
                                                   "bEHWdlcossOMf3gx\n9/t7jj5NPo2jZBa1KKbuFiGOOhWzaGk6sW8nBrExJW" +
                                                   "oWtRFDHeLnWdQKaxlCXuzuz+cdQrOoSedb\nLSb/DSbKAwlmojZYa0be9NcW" +
                                                   "ptN8XbYQQq3woO/BswSJP/6laFtGclwjr5uzkmMrkmkXKr8V0yYS\nVhSiS/" +
                                                   "tcXR9kKyanaWdYCFkUjUnTZpHBYEMzTKmgQdIq5k0qKbHv1yVcZlJ3zcZirA" +
                                                   "xG01mHTLjD\n1FViTyrnrrxzZHjPI8dFqLDw9vSlaCPwy3j8MoxfhvPLRPih" +
                                                   "WIyzWcb4Cp+BxWcgd6HKddw4et/u\nB473JSBYrNkmMBcD7QPNPGGGFXMoSP" +
                                                   "Asr4UKRNnKXx1cyFw9t0NEmdS4DtfFbn/3uUtn/tWxKY7i\n9YskUxLKdJKR" +
                                                   "GWGVtVL8+qNpVY/+Fyf2vvTBpY+/GSQYRf01eV+LyfK2L+oO21SICpUwIH/2" +
                                                   "ulTi\nLjR+Ko6aoBhAAeTyQ21ZG+URyt8BvxYyXVpl1J437SLW2ZFfwJJ02j" +
                                                   "Zngx0eJ2m+XgrOaWcBvQqe\nDi/C+ZedLrfYu1vEFfN2RAtea6/+sOVbH77a" +
                                                   "/hY3i1+WU1WNb5RQkeSdQbAcsAmB/Y9/PvL4zz5f\nOMgjxQsVCt3QndI1pQ" +
                                                   "woGwMUyG4dKgxzZP+YUTRVLa/hKZ2wiPtfasPNr3z247RwjQ47vme3XJtA\n" +
                                                   "sH/d99HRS/f/ey0nE1NYdwnUCMCENksDyoO2jeeYHOVjf17ziz/gJ6D4QcFx" +
                                                   "tHnCawjimiFuR4nb\nfRN/ZyJnN7NXH9De0iD06/TySeXI04U+9/Aff8Olbs" +
                                                   "fVQ0G1G/Zia0B4nr3WM+uuiGbvHdiZBrhv\nX9x3b1q/+F+gOAEUIe0dZ78N" +
                                                   "paMccqIH3dz60etvdD/wXgLFd6GkbmJ1F+bxj9og8IgzDVWnbO3Y\nyWMrPc" +
                                                   "vqZ5qrjLgRVnkGKFf9ioNwNzZO/11sEggyZ3Jqy3n5nf1PcAM0TPw6jTBCZ/" +
                                                   "61sdNX/0Qv\nczpBBjLs3nJtSYXpKcD97gelzpYXnyzGUesESivefDeOdZfF" +
                                                   "+QSMI44/9MEMGDoPjxaijw5UKszq\naPZXsY3mflDKYc2g2bojku48uzfA0+" +
                                                   "ale1s03WOIL3ZwlH7+vqGSnK2WrZUwm/lQEpZF6NMlCA8G\ntAJqVU37GPFh" +
                                                   "ZC8ruzjsLey1U/h8a8PY2B6WuguepCd1soHUWfYapChmQAytrL4M+IKwQnjl" +
                                                   "4b7f\nvj325ILoNIuEWghrUvnBX/46k/jJ61MCLxpPEeBTa8/+/aUruWWiKo" +
                                                   "npc33NAFiNIyZQ7rCUxTK0\ndzEOHPrNzb3PPpS77EnUFZ6jhuGu8Y+5N8gN" +
                                                   "tz76tzrNPgEzcsQluxdxCZdrY1Waxny3104NlVkB\n8m+UN0RodkyjNY0mWK" +
                                                   "7Nwt3/7HgYv3lf3KuIOQpFxLRu0kmJ6JECsSY0UOzlM3uQkCeeeuYCfW/z\n" +
                                                   "dmGXTY09HEXctP3MfM/2F05+jTGiJ6JblHRn6fo7E9Pa23F+rRD5XXMdCSMN" +
                                                   "hLM6CfK4tnEglNvr\nwq1cgqfTy5LOuq088GPQh2Jhj14jkTkdskgr09hriq" +
                                                   "LmKVfzbm2yJWLrToqaSqamBkGnXKsO+D2C\n/7g/rO42eLo9dbu/srpxL4w8" +
                                                   "dZfVqJvDc/5hX81hlt0tHTENsHsw4YzoIvaYZ6/DEM2aj8o2ZgIb\n2F/VBl" +
                                                   "B4U5FpnI0jK2vu7+LOqcgfPXjvl/L7/+FzZeVe2A6XszxQqe4YVesWyyZ5jU" +
                                                   "veLvqHxT/H\nYCaqf0MAV/Mvl/WogP4RRekoNDiffarBFihqrwKDHuOtqoFO" +
                                                   "QLkCILY8afmeSfNhhPXNjOib5ZCp\nmF3WhzKf/0PFz05X/EtlUrn7uYPryi" +
                                                   "cPPMZTvlnR8Tx3V1JGrWKarmR4b0NqPq130YsvjL/6/Da/\ngvFpa1k5KLOh" +
                                                   "WL5FnC7ieqgq9UfY4aJF+dA5/+sVL9967vTlOB+i/w9f69UpBxMAAA==");
}
