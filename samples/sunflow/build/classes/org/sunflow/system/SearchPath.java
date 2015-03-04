package org.sunflow.system;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.sunflow.system.UI.Module;
public class SearchPath {
    private LinkedList<String> searchPath;
    private String type;
    public SearchPath(String type) { super();
                                     this.type = type;
                                     searchPath = new LinkedList<String>(
                                                    ); }
    public void resetSearchPath() { searchPath.clear(); }
    public void addSearchPath(String path) { File f = new File(path);
                                             if (f.exists() && f.isDirectory(
                                                                   )) { try {
                                                                            path =
                                                                              f.
                                                                                getCanonicalPath(
                                                                                  );
                                                                            for (String prefix
                                                                                  :
                                                                                  searchPath)
                                                                                if (prefix.
                                                                                      equals(
                                                                                        path))
                                                                                    return;
                                                                            UI.
                                                                              printInfo(
                                                                                Module.
                                                                                  SYS,
                                                                                "Adding %s search path: \"%s\"",
                                                                                type,
                                                                                path);
                                                                            searchPath.
                                                                              add(
                                                                                path);
                                                                        }
                                                                        catch (IOException e) {
                                                                            UI.
                                                                              printError(
                                                                                Module.
                                                                                  SYS,
                                                                                "Invalid %s search path specification: \"%s\" - %s",
                                                                                type,
                                                                                path,
                                                                                e.
                                                                                  getMessage(
                                                                                    ));
                                                                        }
                                             }
                                             else
                                                 UI.
                                                   printError(
                                                     Module.
                                                       SYS,
                                                     ("Invalid %s search path specification: \"%s\" - invalid direc" +
                                                      "tory"),
                                                     type,
                                                     path);
    }
    public String resolvePath(String filename) {
        if (filename.
              startsWith(
                "//"))
            filename =
              filename.
                substring(
                  2);
        UI.
          printDetailed(
            Module.
              SYS,
            "Resolving %s path \"%s\" ...",
            type,
            filename);
        File f =
          new File(
          filename);
        if (!f.
              isAbsolute(
                )) {
            for (String prefix
                  :
                  searchPath) {
                UI.
                  printDetailed(
                    Module.
                      SYS,
                    "  * searching: \"%s\" ...",
                    prefix);
                if (prefix.
                      endsWith(
                        File.
                          separator) ||
                      filename.
                      startsWith(
                        File.
                          separator))
                    f =
                      new File(
                        prefix +
                        filename);
                else
                    f =
                      new File(
                        prefix +
                        File.
                          separator +
                        filename);
                if (f.
                      exists(
                        )) {
                    return f.
                      getAbsolutePath(
                        );
                }
            }
        }
        return filename;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVYb2wcRxWfO/8/Oz7HblxjEsd2Li1x2tu0qJGKS1vH2InD" +
       "pXFzrqU6Ipfx7py98d7udnfOvrhx20Qgh0oJtDglRcEfUKr+S5uqIioIVQpC" +
       "0ITSD0EICBIp4guhJR/yoaWi0PJmZvf2z905BcRJuzc3M+/N+/t7b+7MNVRj" +
       "W2izaWgHpzSDJkmBJg9odyXpQZPYyZ2pu0axZRNlUMO2PQZzGbn3tfiHH397" +
       "uiWKaidQG9Z1g2KqGrq9h9iGNkuUFIp7s0MaydkUtaQO4Fks5amqSSnVpv0p" +
       "1OgjpSiRckWQQAQJRJC4CNKAtwuIVhE9nxtkFFin9iPoMRRJoVpTZuJR1BNk" +
       "YmIL5xw2o1wD4FDPfo+DUpy4YKHuou5C5xKFT2yWlr67r+X1KhSfQHFVTzNx" +
       "ZBCCwiETqClHcpPEsgcUhSgTaLVOiJImloo1dZ7LPYFabXVKxzRvkaKR2GTe" +
       "JBY/07Nck8x0s/IyNayielmVaIr7qyar4SnQtd3TVWg4zOZBwZgKgllZLBOX" +
       "pHpG1RWK1ocpijomvgobgLQuR+i0UTyqWscwgVqF7zSsT0lpaqn6FGytMfJw" +
       "CkWdFZkyW5tYnsFTJENRR3jfqFiCXQ3cEIyEojXhbZwTeKkz5CWff649cM/x" +
       "R/UdepTLrBBZY/LXA1FXiGgPyRKL6DIRhE19qWdw+5tHowjB5jWhzWLPG4eu" +
       "339b1/kLYs/ny+zZPXmAyDQjn55svrR2cNPdVUyMetOwVeb8gOY8/Eedlf6C" +
       "CZnXXuTIFpPu4vk9v3j4iZfI+1EUG0G1sqHlcxBHq2UjZ6oasbYTnViYEmUE" +
       "NRBdGeTrI6gOxilVJ2J2dzZrEzqCqjU+VWvw32CiLLBgJqqDsapnDXdsYjrN" +
       "xwUTIVQHD2qCpwGJD/+m6EFp2sgRCctYV3VDgtgl2JKnJSIbko1zpgZes/N6" +
       "VjPmJNuSJcOa8n4ftCnJSWlOMQrHJVlomf8PpgWmSctcJAJGXhtOcQ2yY4eh" +
       "KcTKyEv5bUPXX828HS2GvGMDitbBMUnnmKQ4JukdgyIRzv0mdpxwHxh/BtIY" +
       "AK5pU/prO/cf7a2CuDHnqsFybGsv6OPIMCQbg16uj3BEkyHgOn6wdzH50fP3" +
       "iYCTKgNzWWp0/uTc4fHHt0RRNIiwTCeYijHyUYaLRfxLhDOrHN/44tUPzz6z" +
       "YHg5FoBsJ/VLKVnq9oatbxkyUQAMPfZ93fhc5s2FRBRVAx4ABlIMMQvw0hU+" +
       "I5DC/S4cMl1qQOGsYeWwxpZcDIvRacuY82Z4WDTz8WpwSiOL6TXwrHKCnH+z" +
       "1TaTvW8SYcS8HNKCw+3wj88/e+57m++O+pE57qt1aUJFnq/2gmTMIgTm/3hy" +
       "9Dsnri3u5RECOzaUOyDB3oOQ9eAyMOs3Ljxy+d0rp38T9aKKQvnLT2qqXAAe" +
       "t3inACZogEvM94mH9JyhqFkVT2qEBec/4xvvOPe34y3CmxrMuMFw240ZePOf" +
       "24aeeHvf37s4m4jMapKnubdNGKDN4zxgWfggk6Nw+Nfrnn0Lfx8gE2DKVucJ" +
       "R56Iky9MqDXQO3BKVn6Sovxwn0h8uY+/k8xpnAjxtS+yV7dZslbgMx38VxRk" +
       "2lQ5u4ZZzfVl5T92a5NH/vwRV7Ukr8qUmhD9hHTmVOfgve9zei/AGfX6QilA" +
       "QX/i0d75Uu6DaG/tz6OobgK1yE7zM461PAujCSj4ttsRQYMUWA8Wb1Gp+osJ" +
       "vDacXL5jw6nlASOM2W42joWyiVUL1AdPzMmmWDibIogP+jlJL39vZK8vuMFc" +
       "Z1rqLGadFYrZRagFX/VV9lU6P2lTX0NwTF1+55cfxA8LCA06mfeEDmmY7vLv" +
       "q+5spIlvcfCsnsQ2V7IeLGGznRR1V+4vOa9+bo9GYY9P4YPg+YQ9zA58gpfQ" +
       "Tn8z7FaMJG9iTbPgxn3ZusLWtoM5em5gjow8ksukz11e3MpDLj6rQmNBlDGn" +
       "+Q3mqse+P9AQlzVYRr569tiFnvfG23in49rGj3q7sFmCejuwPQ3zNXV/+OnP" +
       "2vdfqkLRYRTTDKwMY14wUAMgNbGnoSoXzPvu5+HTNFcP7xaneG6soLKjEwen" +
       "jHzo1Cfv/HXhysUqVAvozwIdW9AnQSOWrHTF8DNIjMHoK0AFCdAsqAFxeJA4" +
       "wdBanC0WMopur8Sb3aDC9Y416dBREGubkdcVjgHBBIvlTdO/ysOq6X8Mq8cA" +
       "3T+DBYsGcFIYtfIsavZAeAiuYP5FaJTaBlMD6XRm7OHRocz4wJ6RgW2pIR6p" +
       "JixGhsogucAirtiWAC4LBG8R4L01CC0dbsV2v8tAyz72+jL1QGqL4MfZJgIH" +
       "QUytq3St4Fei00eWlpXdz90hgKQ12KozM7zy23/9KnnyTxfL9I4N1DBv18gs" +
       "0XxnVrEjAz3gLn7j8sD+yRdffoNe2vwlceQKoBcmfOvIe51j907v/w86v/Uh" +
       "5cMsX9x15uL2W+Sno6iqWDNKLpFBov5QIENu5C19LFAvuoLdV48IIzecynRf" +
       "nufKl3t9hTXOZwbgCbCF0LS/oqxQ/UctNQf3sFnnoigttL47c+rqK8Ip4VIf" +
       "2kyOLj35afL4UtR39d5Qcvv104jrNxd3lZfkkfJJ3jro3AG7i5dAVjP8FaGM" +
       "WPyI4b+cXfjJCwuLUcc20LFVzxqqUtoo8YmpoJ9YXW93/NT+mf3k9HIiz9n7" +
       "8RWcdZi9DlG0CiuK5yo2mb+hjDx01sFzqyPjrf+9jIsryPhN9vo6RY2W+NPG" +
       "lXBLmXYTehhPDVYPO0r+axL/j8ivLsfrb15+6Hf8AlT8D6MhheqzeU3z916+" +
       "ca1pkazKpWoQnZhAueMQJaU3V7goiAGX85jY+hQo4tsK/Zcz8m9aoqgKNrHh" +
       "CW7LvQUUAFIzDKsbKrYQu/Lin7iMfHZ55wOPXt/6HAesGqhS8/NOha0T17gi" +
       "TvVU5Obyqt2x6ePm1xo2upHdzF6tzt0tJNv68lecoZxJ+aVk/kc3//Ce55ev" +
       "8DvWvwFjfU+hIBUAAA==");
}
