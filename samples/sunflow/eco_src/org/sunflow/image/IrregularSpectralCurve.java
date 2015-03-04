package org.sunflow.image;
public class IrregularSpectralCurve extends SpectralCurve {
    final private float[] wavelengths;
    final private float[] amplitudes;
    public IrregularSpectralCurve(float[] wavelengths, float[] amplitudes) {
        super();
        this.
          wavelengths =
          wavelengths;
        this.
          amplitudes =
          amplitudes;
        if (wavelengths.
              length !=
              amplitudes.
                length)
            throw new RuntimeException(
              String.
                format(
                  ("Error creating irregular spectral curve: %d wavelengths and " +
                   "%d amplitudes"),
                  wavelengths.
                    length,
                  amplitudes.
                    length));
        for (int i =
               1;
             i <
               wavelengths.
                 length;
             i++)
            if (wavelengths[i -
                              1] >=
                  wavelengths[i])
                throw new RuntimeException(
                  String.
                    format(
                      ("Error creating irregular spectral curve: values are not sort" +
                       "ed - error at index %d"),
                      i));
    }
    public float sample(float lambda) { if (wavelengths.length ==
                                              0) return 0;
                                        if (wavelengths.length ==
                                              1 ||
                                              lambda <=
                                              wavelengths[0])
                                            return amplitudes[0];
                                        if (lambda >= wavelengths[wavelengths.
                                                                    length -
                                                                    1])
                                            return amplitudes[wavelengths.
                                                                length -
                                                                1];
                                        for (int i = 1; i <
                                                          wavelengths.
                                                            length;
                                             i++) { if (lambda <
                                                          wavelengths[i]) {
                                                        float dx =
                                                          (lambda -
                                                             wavelengths[i -
                                                                           1]) /
                                                          (wavelengths[i] -
                                                             wavelengths[i -
                                                                           1]);
                                                        return (1 -
                                                                  dx) *
                                                          amplitudes[i -
                                                                       1] +
                                                          dx *
                                                          amplitudes[i];
                                                    } }
                                        return amplitudes[wavelengths.
                                                            length -
                                                            1];
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170479716000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYfWwUxxUf3/kDf4A/AEMIwdgYCGDfAbZxwFES1x9wcIBj" +
                                                   "8xEMxIz35s4Le7ub\n3Tn7cCgNQgEKbQJKKjVSIaRF4qNJQ0UjWpUSKEmbBl" +
                                                   "VKkJpIkUJbIbWV2lSNKqVU7R99b3b3bm/v\nfKhI/HFzszNv3sx77/d+83bf" +
                                                   "/IIUmQaZLZkBvldnZqBroI8aJot0KdQ0N8HQkPR+UWnf2XWq5iMF\nYeKTI5" +
                                                   "xUhiUzGKGcBuVIMNTdkTTIEl1T9sYUjQdYkgd2K222vrXhtiyFW09drjlwpr" +
                                                   "DOR4rCpJKq\nqsYplzW1R2Fxk5Oq8G46SoMJLivBsGzyjjCZzNREvEtTTU5V" +
                                                   "bj5H9hN/mBTrEurkpD7sbB6EzYM6\nNWg8KLYP9oltQcNUg3EqqyzSmdoOVj" +
                                                   "ZlroRj2+v6s6VBySSc3ALmiBOA1XNTVlvWZpmq+89tWbHv\n9Hk/qRwklbI6" +
                                                   "gMoksITDfoOkIs7iw8wwOyMRFhkk1SpjkQFmyFSRx8Wug6TGlGMq5QmDmf3M" +
                                                   "1JRR\nFKwxEzozxJ7OYJhUSGiTkZC4ZqR8FJWZEnGeiqIKjYHZtWmzLXN7cR" +
                                                   "wMLJPhYEaUSsxZUrhHViHi\ndd4VKRsb14EALC2JMz6ipbYqVCkMkBorlgpV" +
                                                   "Y8EBbshqDESLtATswsmsCZWir3Uq7aExNsTJTK9c\nnzUFUqXCEbiEk+leMa" +
                                                   "EJojTLEyVXfJbUfnXk3PeuPiWwXRhhkoLnL4NFczyL+lmUGUyVmLXwbiLw\n" +
                                                   "amhbYraPEBCe7hG2ZDrnX94c/su7dZbMwzlkNg7vZhIfkjacqOt/frVG/HiM" +
                                                   "Sbpmyhj8DMtFOvTZ\nMx1JHbK2NqURJwPO5LX+X2174QL7q4+UhUixpCmJOO" +
                                                   "CoWtLiuqwwYzVTmUE5i4RIKVMjXWI+REqg\nHwbIW6Mbo1GT8RApVMRQsSae" +
                                                   "wUVRUIEuKoW+rEY1p69TPiL6SZ0QUgI/UmH/nD7hpCMQNBNqVNHG\ngqYhBT" +
                                                   "UjlnqW4xDRYMgwWCyhUGNAB8cYVOlKGKMsgCDSOdkaHNHiLEglqsqqFozJkL" +
                                                   "aS1hxho/h/\n/6qTePKasYICpEJvSiuQDWs0JcKMIensnQ/39az75hELLghx" +
                                                   "22ZOHoUdA/aOAbFjIPeOpKBAbDQN\nd7YiB37fAxkMXFexaGDn2l1HGvwAGX" +
                                                   "2sEJyGog1gnX2cHknrSqd5SDCiBFib+f3thwN3zz5pYS04\nMRvnXF3+0Vs3" +
                                                   "T/+zYrGP+HJTJZoJZF2GavqQX1MU2OhNrlz6/350/aVPbn7+aDrNOGnMyv7s" +
                                                   "lZi9\nDd6AGJrEIsCHafVnHqr0byVbTvhIIVAC0KA4PzDMHO8eGVnc4TAi2l" +
                                                   "ISJuVRzYhTBaccGivjI4Y2\nlh4RSKkS/akQnHKEdT38Zto4F/84O13HttZC" +
                                                   "FkbbY4Vg3LsHi5d+eqX8feEWh5wrXdffAONWqlen\nwbLJYAzGP/9u3yvf+e" +
                                                   "LwdoEUGyoc7sTEsCJLSViyIL0EclwBBGIgGzercS0iR2U6rDBE3H8r5y97\n" +
                                                   "528vVVmhUWDEiWzTvRWkxx/6Gnnh5rP/miPUFEh4x6TNSItZ1kxNa+40DLoX" +
                                                   "z5E8cOuR135NTwIF\nAu2Y8jgTTOITlvnQg64aRazCqJw/3j21f+X2gwK4pX" +
                                                   "BtUnODk5WiWMFeARgzf+KMSCkbkhbuvPyP\n61fZQrBikEySTbjrO41YjivE" +
                                                   "teZLeoGt/zR6SoCvcJiaYvcK792bfbVm3JgCKFN0DNxMdzFmyHEg\n9VGB1z" +
                                                   "uHGn7xwebXD1s5vihPxeVeNSR94/d/2ON/+fqwtc57sXmET8w586dLd/qnWX" +
                                                   "iwbv95WRew\ne41VAQgDKoUB9fl2ENLvLal/c3//bftENZn3WA/Uen/ee4Mt" +
                                                   "fPzbf8xBtJAnGrWKwACCPsWSeRzS\ni5FI88XQcNO58IcbTwrtE9JdDl959I" +
                                                   "xf3Xzq7m/5baEnzTu4uj6ZfZUAmtJrH/tktLr44utxHykZ\nJFWSXdtuoUoC" +
                                                   "s3sQ8GI6BS/UvxnzmWWVVUN0pHh1tjdYrm29jJf2LPRRWkDXIjk9WUAEiXUJ" +
                                                   "6UbR\nLrSYxodhkFUqqqVFSeAdhakxPiJkWgEE+L+SEz+AHrst9lDAldH4PI" +
                                                   "PbXICWQF2mqQxpxZmzrkhZ\nC6RqYphM5gj9IxkX5HqRV2lXHz3/w8v84yWr" +
                                                   "LLQtnhgm3oWLV50er1v19rH7uBbrPLH3qq4effhp\n/4j8gU8Uy1bksorszE" +
                                                   "UdmfEqg/MkDHVTRtTm6uKvBZsF4jCLU+4iwl1ECGzNM7cNm80QYQnjYYUP\n" +
                                                   "fFyX+zroietcEPj4T2f85PGzp26jl/UkFHvlgo6XLVve3tIO62uA1vCdMSBH" +
                                                   "AmFNokqo+43r5bdO\nJFastQIz2SUQ6t53cW3FpDeOHRIOsom81FUc288lo9" +
                                                   "TYkKYH/NvJya4HVCiuWt7a1NLa3NYC1o3R\nUWajXrhxqeX6x7Lgic9PpLKJ" +
                                                   "5cgm7PdaqYTtamzWYCNl5w0+hrHZkJ0I+Py0dQzpXghQ8swJ5+7G\nZtA6Bb" +
                                                   "Y7PDG1DH0WG+3BerytrbkdPF5G47oi80SEmeKUwTwWCNZpANQ1TZDtOV7wh6" +
                                                   "R9F2INied+\n8zPB5+XU/aXAXZWtp7p13VVhMw+vvBneYn4NNUdArvXahh1V" +
                                                   "yrX/iIKinEpw+5sbDXiXSGbUdLZ0\nUcln12/U7vrYT3y9pAyuuUgvFeUwKY" +
                                                   "U6lJkj8BqS1J98SpSaVWOTsBUmE+GEWbYDkq4ni2efSFWs\nNSixAH6T7Yp1" +
                                                   "srditXH69YlwykmJbsijFL9g3HcWvPj/ZMHB+86Cg/fKgm/lmXsJm6OpLDiY" +
                                                   "Mwva\nPMxmVQjdt3qHL0TVCxHBXmWiEOzENTZrlYoRV6Xq13QTX/dd39VsTY" +
                                                   "0bddNC25Cz8fL2pcva2h5g\nzrW2Ny1f0dzSzknVpjWhgYAryHiQlzPjvt8g" +
                                                   "tdkewHPbEEPQAd6npG96LPHck1DWFfb3dHaj9hqx\nRwKb/VZwkxMi3oPr+f" +
                                                   "CbYqudMgGuT+ZD3oFkBs1kIFrX9czQrxDTxzOi0vpgo7KsrXk5BL5SRCV9\n" +
                                                   "TjzHDzJOjiOvebx5Ko83k+lEcVgEy6qJPpyJIv7wM19WHKLv7fTZ+bKKA01p" +
                                                   "erPCABouVXZGZr40\nY6ga7FA15HxpzpW6doqLeAipS3ny9x1sLkJtaqJfWJ" +
                                                   "qWhDd+fC9spdgU3mVzhwWroplZX4Ctr5ZS\n+LPnd3wV/t2/rddC58tiOVQw" +
                                                   "0YSiuOtuV79YN1hUFqcvd6pw/LvCSXXW9yWo0MS/OO3PLcF3AY0u\nQeBqu+" +
                                                   "cW+iUU5iCE3Ru6U2vXZX+/yjA2meEUNH1extUqvro7xW7C+u4+JD3z1va5yW" +
                                                   "ObjosKGgpK\nOj6OasqgcLM+tqQK5voJtTm6PiIX395y5UcrHbyJ23daMo3y" +
                                                   "FHJTQZ6WJ8iCzfX/AYei1jMBGQAA\n");
}
