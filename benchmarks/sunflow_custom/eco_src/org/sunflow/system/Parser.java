package org.sunflow.system;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Parser {
    private FileReader file;
    private BufferedReader bf;
    private String[] lineTokens;
    private int index;
    public Parser(String filename) throws FileNotFoundException { super();
                                                                  file = new FileReader(
                                                                           filename);
                                                                  bf =
                                                                    new BufferedReader(
                                                                      file);
                                                                  lineTokens =
                                                                    (new String[0]);
                                                                  index =
                                                                    0;
    }
    public void close() throws IOException { if (file != null)
                                                 file.
                                                   close();
                                             bf = null; }
    public String getNextToken() throws IOException { while (true) {
                                                          String tok =
                                                            this.
                                                            fetchNextToken();
                                                          if (tok ==
                                                                null)
                                                              return null;
                                                          if (tok.
                                                                equals(
                                                                  "/*")) {
                                                              do  {
                                                                  tok =
                                                                    this.
                                                                      fetchNextToken();
                                                                  if (tok ==
                                                                        null)
                                                                      return null;
                                                              }while(!tok.
                                                                       equals(
                                                                         "*/")); 
                                                          }
                                                          else
                                                              return tok;
                                                      }
    }
    public boolean peekNextToken(String tok)
          throws IOException { while (true) {
                                   String t =
                                     this.
                                     fetchNextToken();
                                   if (t ==
                                         null)
                                       return false;
                                   if (t.
                                         equals(
                                           "/*")) {
                                       do  {
                                           t =
                                             this.
                                               fetchNextToken();
                                           if (t ==
                                                 null)
                                               return false;
                                       }while(!t.
                                                equals(
                                                  "*/")); 
                                   }
                                   else
                                       if (t.
                                             equals(
                                               tok)) {
                                           return true;
                                       }
                                       else {
                                           index--;
                                           return false;
                                       }
                               } }
    private String fetchNextToken() throws IOException {
        if (bf ==
              null)
            return null;
        while (true) {
            if (index <
                  lineTokens.
                    length)
                return lineTokens[index++];
            else
                if (!this.
                      getNextLine())
                    return null;
        }
    }
    private boolean getNextLine() throws IOException {
        String line =
          bf.
          readLine();
        if (line ==
              null)
            return false;
        java.util.ArrayList<String> tokenList =
          new java.util.ArrayList<String>(
          );
        String current =
          new String(
          );
        boolean inQuotes =
          false;
        for (int i =
               0;
             i <
               line.
               length();
             i++) {
            char c =
              line.
              charAt(
                i);
            if (current.
                  length() ==
                  0 &&
                  (c ==
                     '%' ||
                     c ==
                     '#'))
                break;
            boolean quote =
              c ==
              '\"';
            inQuotes =
              inQuotes ^
                quote;
            if (!quote &&
                  (inQuotes ||
                     !Character.
                     isWhitespace(
                       c)))
                current +=
                  c;
            else
                if (current.
                      length() >
                      0) {
                    tokenList.
                      add(
                        current);
                    current =
                      new String(
                        );
                }
        }
        if (current.
              length() >
              0)
            tokenList.
              add(
                current);
        lineTokens =
          tokenList.
            toArray(
              new String[0]);
        index =
          0;
        return true;
    }
    public String getNextCodeBlock() throws ParserException,
        IOException { String code = new String(
                        );
                      this.checkNextToken(
                             "<code>");
                      while (true) { String line;
                                     try {
                                         line =
                                           bf.
                                             readLine();
                                     }
                                     catch (IOException e) {
                                         e.
                                           printStackTrace();
                                         return null;
                                     }
                                     if (line.
                                           trim().
                                           equals(
                                             "</code>"))
                                         return code;
                                     code +=
                                       line;
                                     code +=
                                       "\n";
                      } }
    public boolean getNextBoolean() throws IOException {
        return Boolean.
          valueOf(
            this.
              getNextToken()).
          booleanValue();
    }
    public int getNextInt() throws IOException {
        return Integer.
          parseInt(
            this.
              getNextToken());
    }
    public float getNextFloat() throws IOException {
        return Float.
          parseFloat(
            this.
              getNextToken());
    }
    public void checkNextToken(String token)
          throws ParserException,
        IOException { String found = this.
                        getNextToken();
                      if (!token.equals(found)) {
                          this.
                            close();
                          throw new ParserException(
                            token,
                            found);
                      } }
    @SuppressWarnings("serial") 
    public static class ParserException extends Exception {
        private ParserException(String token,
                                String found) {
            super(String.
                    format(
                      "Expecting %s found %s",
                      token,
                      found));
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1158386604000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJVXe2wURRif3qNv0/YKLVRooRSRh7eaCIaUBGotULrQpgcF" +
           "Climu3PXhb3dZXa2\nvRZiICaCGo1E8JEoIYakSERN1KCRKAbRIP+giZqQYG" +
           "L8QxPFRE0Qo3/4zezuPfZ6MTa53dmZ7zHf\n6/d9feMWitoUzVPsOJu0iB3v" +
           "TgxgahO1W8e2vRW2RpQr0aqB6T7DDKEyGYU0laE6WbElFTMsaarU\n+0hnhq" +
           "LllqlPpnSTxUmGxffpKz15m+SVRQK3n7oQO3Im0hZCURnVYcMwGWaaafToJG" +
           "0zVC/vw+NY\ncpimS7Jms04Z3UUMJ91tGjbDBrMPoMdQWEbllsJlMrRQ9pVL" +
           "oFyyMMVpSaiXBoRakNBICcOaQdSu\nrDrgXFHICdf2+AaLqUFIJT8cAnPEDc" +
           "DqBVmrXWuLTLXCZ4dWHTr9ehjVDaM6zUhwYQpYwkDfMKpN\nk/QooXaXqhJ1" +
           "GDUYhKgJQjWsa1NC6zCK2VrKwMyhxB4ktqmPc8KY7ViECp3+poxqFW4TdRRm" +
           "0qyP\nkhrRVf8rmtRxCsxuypntmrue74OB1RpcjCaxQnyWyH7NgIi3BTmyNn" +
           "b0AQGwVqQJGzOzqiIGhg0U\nc2OpYyMlJRjVjBSQRk0HtDDUUlIo97WFlf04" +
           "RUYYmhOkG3CPgKpKOIKzMDQ7SCYkQZRaAlHKi8/y\nptvHzr7y0TqR2xGVKD" +
           "q/fzUwtQaYBkmSUGIoxGW848RP9O505oUQAuLZAWKXpmvxhW3yTx+3uTR3\n" +
           "z0DTP7qPKGxE2XK8bfDgBhOF+TUqLdPWePALLBflMOCddGYsqNqmrER+GPcP" +
           "Lw1+tvPwOfJzCFX3\nonLF1J005FGDYqYtTSd0AzEIxYyovaiKGGq3OO9FFb" +
           "CWIeXd3f5k0iasF0V0sVVuim9wURJEcBdV\nwVozkqa/tjAbE+uMhRCKwQ81" +
           "w+8F5P6JN9RbXLIdI6mbE5JNFcmkqdz3pM1IWhIVROM8ayyGNkpj\nZppIWM" +
           "GGZphSSoM6Vcz7VDLO3/9DVobfLTZRVsbBLli0OuT7RlNXCR1Rpn/44lBP35" +
           "PH3ITgSexZ\nxdBSUBH3VMRdFW7B0w731ZNRiMUjgMrKhKZZXLUbHHDtfihS" +
           "gLPapYk9m/Yeaw9DVlgTEfALJ20H\ne7z79Chmd66SewXoKZBOc17bdTR+Z3" +
           "qtm05SacCdkbvm+vlrp/+oXRZCoZnRkNsJeFzNxQxwCM2i\nXEewfmaS/+tT" +
           "m9/55trNe3OVxFBHUYEXc/ICbQ9GhJoKUQHycuLPzK0Lb0dDx0MoAlUPSCfu" +
           "DyDS\nGtRRUKidPuhxWypkVJM0aRrr/MhHqmo2Rs2J3I5IlXqxboTg1PPMbY" +
           "Pfi14qizc/nW3xZ5ObWjza\nASsEqN55vPz+by/WXBFu8fG3Lq/DJQhzq7kh" +
           "lyxbKSGwf/OlgedP3jq6S2SKlyoMVVhUG4f6zQDP\nPTkeqGMdsIRHsmObkT" +
           "ZVLanhUZ3wlPunbvED7/3ybL0bGx12/NCu+G8Buf25D6PD1x79s1WIKVN4\n" +
           "H8nZkSNzzWnMSe6iFE/ye2SOfDX/5c/xqwBzAC22NkUEWoSEaSHh8mbo/4KT" +
           "t4y42zJKHgjXS+J4\nmXjGeWyENCTOVvJHO9xmRYlqmaHPjyiHzqXanQNXPx" +
           "B21uD8gSE/cpux1ekmC38s4vFoDhb8RmyP\nAd2Dl7bsrtcv/Q0Sh0GiAv3V" +
           "7qcAOJmCuHvU0Yobn1xu2vtlGIXWo2rdxOp6LEoGVUGuEnsMsCpj\nrV0n0r" +
           "F+otLP0gwSTmjxHJAp+BIfHeK5xM2lEIMRyhnVNYhkuS0GkwALRfNLtU7R9o" +
           "/u+K32Cfzp\nHheRYoXtqAdGth8nL5Mla575fgY0LfdGn3yF4N8Akvqhn1sS" +
           "e+GSc4qmMHdyUOQbB3fflr/+S4BG\ntrvXQItNOrqevU7+1fhYSUlSE4Gtcc" +
           "NriZcM00zxLbjrxELctM8l7WeoJo8UatZb5RMNMhQGIr5M\nWL6djbkUz/qg" +
           "MCi8thYVZLOYdz3TNzvuxDui7Di/a0Hm6a3PCbyPwqQ8NSVGG5jUXAzMwvvC" +
           "ktJ8\nWdfR228NXXxzdcgrK5HwszzgK0qyVe6pWK/2srQ2m6VlvqktedXsWO" +
           "B0296OqcHrvRQrQ9FxrDsE\nXPBQiYLuKvhvghhsiDP4/zqMKCd2X531e0Pj" +
           "h8ItlWJiBorCQdWdyqB1RLhMfpfaQFNo4hdrhd9J\nrymcDDYFnhdikC8NXr" +
           "xeKWqbGYF70hYTmDn1fvO7a6ZPfRcSTeBf7xTjhrANAAA=");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1158386604000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAL1ZC2wUxxmeO7/w2WAwYN6YhwkBzB20AQGOkoCxg+FsHNtA" +
       "YkKd9e6cvXhvd7M7\nZw4HkaAoPIJIgtpKiVoIqZB4lDRIaUoqUQrKO6hSEj" +
       "VBjRSaCPUhtamCoqZUTdX+/8zu7d3enYFz\nVUs7Nzsz/z/zv77/n/WZL0mJ" +
       "bZHpsh1mO01qhxs72yXLpkqjJtl2Fwz1yG+VlLef2KAbQRKIkqCq\nMFIVle" +
       "2IIjEpoiqRlrUNSYssMg1tZ59msDBNsvB2bZnDb310WRbDLUfPVe85XlwbJC" +
       "VRUiXpusEk\nphp6k0bjNiNjo9ulQSmSYKoWiao2a4iS0VRPxBsN3WaSzuxH" +
       "yW5SFCWlpow8GZkddTePwOYRU7Kk\neIRvH2nn2wKH8RZlkqpTZXVqO6Csz6" +
       "SEYzt0HdmrgckonNwM4vATgNSzUlILabNENYtObl6+69ip\nIlLVTapUvROZ" +
       "ySAJg/26SWWcxnupZa9WFKp0k3E6pUontVRJU4f4rt2k2lb7dIklLGp3UNvQ" +
       "BnFh\ntZ0wqcX3dAejpFJGmayEzAwrpaOYSjXFfSuJaVIfiF3jiS3EbcZxED" +
       "CkwsGsmCRTl6R4QNXB4rV+\nipSMdRtgAZCWxSnrN1JbFesSDJBqYUtN0vsi" +
       "ncxS9T5YWmIkYBdGpuZliro2JXlA6qM9jEz2r2sX\nU7CqnCsCSRiZ6F/GOY" +
       "GVpvqslGafRTXf7D/54wv3cd8uVqis4flDQDTTR9RBY9SiukwF4Y1E+Act\n" +
       "DyWmBwmBxRN9i8Wa1fPObYr++de1Ys20HGs29m6nMuuR2w7Xdjx2v0GK8Bij" +
       "TMNW0fgZkvNwaHdm\nGpImRG1NiiNOht3Jix1vP/TEafqXIAm1kFLZ0BJx8K" +
       "NxshE3VY1a91OdWhKjSgspp7rSyOdbSBn0\no+DyYnRjLGZT1kKKNT5UavB3" +
       "UFEMWKCKyqGv6jHD7ZsS6+f9pEkIKYOHVMJTTsQf/4V4C0fshB7T\njB0R25" +
       "IjhtXnve+0GY1HeARZYfQak5F1kX4jTiOSLOmqbkT6VIhT2Vis0EH8vQ1eST" +
       "xb9Y5AAMHO\nH7Qa+Ps6Q1Oo1SOfuPb+rqYNB/YLh0AndqRiZApsEXa2CIst" +
       "RMBbJBDgnCfgVsIYoMoBCEqAr8oF\nndvWP7J/ThF4gbmjGPSAS+fA+Z39m2" +
       "Sj0YvcFg5yMrjP5J9s3Re+ceJe4T6R/ACbk7rig5cvH/u6\ncmGQBHOjH8oF" +
       "+BtCNu0ImSlUq/PHSy7+f3u69dVPLn92pxc5jNRlBXQ2JQbkHL8FLEOmCkCc" +
       "x/74\nlKqiLWTz4SAphigHZOPnB9CY6d8jIzAbXJBDWcqipCJmWHFJwykXmU" +
       "Ks3zJ2eCPcNcby/ngwTgV6\n6jh4qhzX5b84O9HEtka4ElrbJwUH0RtPli65" +
       "cr7iLa4WF2+r0jJaJ2Uiesd5ztJlUQrjnz3f/v0f\nfrlvK/cUx1UYpLlEr6" +
       "bKSSC5wyOBsNUAOtCQdZv0uKGoMVXq1Sh63LdV85a+9tdnxgrTaDDiWrb+\n" +
       "5gy88SlryBOXv/ePmZxNQMa04YnhLRPSjPc4r7YsaSeeI7nnoxkvvCMdAVQD" +
       "JLHVIcrBIeAEAR5q\nEqR7TokZIiwyBFdwhE8v5G04m2gGJ1KNcDPgUZvBmo" +
       "2ErjQlZWrikTiH72IzB45WnydycuT4HnnX\n6b45iUff+yUXukJKLxbSrdgq" +
       "mQ3CcbCZi8aZ5A/+dZLdD+vuutj28Fjt4r+AYzdwlCG32hstAJtk\nhg84q0" +
       "vKPr30Rs0jHxaRYDMJaYakNEs8fEg5+C21+wGnkua993HXHLtjFLbYTRKumq" +
       "lcTSSZTHsr\nhsMtyI8ezVgheIHX01t/Mvr+xiNcAXlxI0eC9PEZurDp6I3f" +
       "sKucjxfASD07mQ3CUFV5tCs+GRxX\nevbFeJCUdZOxslP3bZa0BIZJN5Qptl" +
       "sMQm2YMZ9Zcoj82pACqOl+8Ejb1g8dHvhDH1djv9KHFpjj\nyHR4Qg5ahPxo" +
       "ESC808hJ6ng7PxXbZaalDkpYC4rM6rp3dbp7d1BJEYqr5vPLsVkrLLwyryfc" +
       "k3nG\nmS60ub85ztiKTRMjwd6Ye5Aa9yBrEjGEWSXnYdpu8zCz3I77m+MwXc" +
       "5hQliBdBkDVLcRc9MuKhxn\nEMdPPbd2fMfKrU/yVFcOtbNkt3m2gxsL9gLg" +
       "dPPyR0GKWY88f9u5ry5doPN5wI5SbfCR1VZfjjoy\njea6dJq2Xokd5emquF" +
       "eyhbf4C/Ds+jqjbObONSZTV9XD6SovhubEzXuw2eoquCfbI4MMQkDVJV4E\n" +
       "LwCnLNWo3sf6QfGT02+IlhqHSnOQZ9xre+f86t1NL+4TVcowOJNB1SM//vvP" +
       "B4qevdQr6Pxg4lt8\neObxP756rWOCyGjiSjI361aQTiOuJVyhVSbC8+zhdu" +
       "Cr31w0+8zujqvOiaozi+smuID+aecbdP7d\nh77IURsWgWHxpdtMplQfFDp1" +
       "bTTes1GjZugUk607N8ENs9TlDyaTWUa0yIyMsrGV+46Hm0+f+uk5\n9uGiVU" +
       "KChflt4SdcuOrYUO2qVw4WUCzW+vTqZz1ucNoDRf3qu0F+KxQwnHWbzCRqyA" +
       "TfEJwnYeld\nGRA8S/gy1zk2d/jqBeIkQnzfOczcY9jsAL+X0SbChKDn2tyF" +
       "UlPcZLy0GXp90s/vPnH0KmraTMLN\npoLDztIlS5cv+w7QV0O44AeSsKo4iX" +
       "HtR829p2P6aYUrIsRhYTXSOFKW85E03CoyTBtvgGmfWhxO\ndRtNG8vo0Wmb" +
       "tKzddXZ95aiXDu7l/B3QK0+7TTrvZYOS1Zbuus7Jly9betcSRh74X924Vi1d" +
       "sqx+\nxeKlK6Bw6lrX0hn2cBy3fjwD2XfDZTZbYying3wIgyDxGC+GMCDTJ0" +
       "GSsi0dLV1dTW2+7LTpNrNT\nDTyjHc6j82SnZ5zsVAK4Qbkj9ft2fXaYXZOe" +
       "x7oFG8Z2vs8UHJ32PXi9cq/05rag47jbGVSEhrlY\no4NUS2MV4n078z4zyR" +
       "HLFS/7PnOzGPrR8PX4eBfAWjZmVuFHsHlexJct8E5xMFKFomfQUBVPZy/c\n" +
       "zFIpbeWQEYuK6Y6M0wuS8XSBMp7B5gQjlX2UtQHWel6eNzVzcU+ORNx653H7" +
       "tybuLd29Xi9QD+ex\neY2R0SalA5mKSLN5Wa9haFTSPT38okA98GidC88aRw" +
       "9r8uqhaRizv1eguJexeZuRMTHK5P7bMfw7\nIxF4NjytjsCtBQl8pUCBf4fN" +
       "byFjOH6Onwtx6KIn2ccjcek6eHRHMr2gCL7mm/OVYAvyfsGrEz8p\neW+qiz" +
       "9g8zlY2dFFo6HQNZohD9yC+b8YiZLQ3/c4StpTkJK+LtD8f8fmK/B3R+Q1Io" +
       "59HnB9JMJN\ng+cpR7inChLuP4UJFwjggm+hKHGEaxH1fL8n2L9HIlgtPAcc" +
       "wQ4UIligokDBRmMzyktOzZohMT8m\nl8RSoyhroHykmemQI+uhW5b1VjJTYO" +
       "r/K74DM7GBBWPkfip7yQzJXvL0NPlW9YR3aXEEvF1Mzvq3\nofhXlxz99LGH" +
       "v4l+/E/xGcH9d1QFVPGxhKalf5BK65eaFo2pXLMV4vOUyUVYwEh1tkLgIKKD" +
       "5wvc\nKZbWA6inLYUk7fTSF0XgmguLsLvEzGEn8bEtmSE3Sjs34x7K/zvr3h" +
       "UT4v+zPfKDL2+dlTzY9Ry/\ngEKtKA0NIZsQ3FfEF3zOFe+bs/Nyc3l9QM6+" +
       "svn8z1a6lTL/RDvB+Wyf5bzLxWx+O+LEbvO/NXI1\n+ikfAAA=");
}
