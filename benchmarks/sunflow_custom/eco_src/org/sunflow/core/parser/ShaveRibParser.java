package org.sunflow.core.parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.sunflow.SunflowAPI;
import org.sunflow.core.SceneParser;
import org.sunflow.core.primitive.Hair;
import org.sunflow.system.Parser;
import org.sunflow.system.UI;
import org.sunflow.system.Parser.ParserException;
import org.sunflow.system.UI.Module;
import org.sunflow.util.FloatArray;
import org.sunflow.util.IntArray;
public class ShaveRibParser implements SceneParser {
    public boolean parse(String filename, SunflowAPI api) { try { Parser p =
                                                                    new Parser(
                                                                    filename);
                                                                  p.checkNextToken(
                                                                      "version");
                                                                  p.
                                                                    checkNextToken(
                                                                      "3.04");
                                                                  p.
                                                                    checkNextToken(
                                                                      "TransformBegin");
                                                                  if (p.
                                                                        peekNextToken(
                                                                          "Procedural")) {
                                                                      boolean done =
                                                                        false;
                                                                      while (!done) {
                                                                          p.
                                                                            checkNextToken(
                                                                              "DelayedReadArchive");
                                                                          p.
                                                                            checkNextToken(
                                                                              "[");
                                                                          String f =
                                                                            p.
                                                                            getNextToken();
                                                                          UI.
                                                                            printInfo(
                                                                              Module.
                                                                                USER,
                                                                              "RIB - Reading voxel: \"%s\" ...",
                                                                              f);
                                                                          api.
                                                                            parse(
                                                                              f);
                                                                          p.
                                                                            checkNextToken(
                                                                              "]");
                                                                          while (true) {
                                                                              String t =
                                                                                p.
                                                                                getNextToken();
                                                                              if (t ==
                                                                                    null ||
                                                                                    t.
                                                                                    equals(
                                                                                      "TransformEnd")) {
                                                                                  done =
                                                                                    true;
                                                                                  break;
                                                                              }
                                                                              else
                                                                                  if (t.
                                                                                        equals(
                                                                                          "Procedural"))
                                                                                      break;
                                                                          }
                                                                      }
                                                                      return true;
                                                                  }
                                                                  boolean cubic =
                                                                    false;
                                                                  if (p.
                                                                        peekNextToken(
                                                                          "Basis")) {
                                                                      cubic =
                                                                        true;
                                                                      p.
                                                                        checkNextToken(
                                                                          "catmull-rom");
                                                                      p.
                                                                        checkNextToken(
                                                                          "1");
                                                                      p.
                                                                        checkNextToken(
                                                                          "catmull-rom");
                                                                      p.
                                                                        checkNextToken(
                                                                          "1");
                                                                  }
                                                                  while (p.
                                                                           peekNextToken(
                                                                             "Declare")) {
                                                                      p.
                                                                        getNextToken();
                                                                      p.
                                                                        getNextToken();
                                                                  }
                                                                  int index =
                                                                    0;
                                                                  boolean done =
                                                                    false;
                                                                  p.
                                                                    checkNextToken(
                                                                      "Curves");
                                                                  do  {
                                                                      if (cubic)
                                                                          p.
                                                                            checkNextToken(
                                                                              "cubic");
                                                                      else
                                                                          p.
                                                                            checkNextToken(
                                                                              "linear");
                                                                      int[] nverts =
                                                                        this.
                                                                        parseIntArray(
                                                                          p);
                                                                      for (int i =
                                                                             1;
                                                                           i <
                                                                             nverts.
                                                                               length;
                                                                           i++) {
                                                                          if (nverts[0] !=
                                                                                nverts[i]) {
                                                                              UI.
                                                                                printError(
                                                                                  Module.
                                                                                    USER,
                                                                                  "RIB - Found variable number of hair segments");
                                                                              return false;
                                                                          }
                                                                      }
                                                                      int nhairs =
                                                                        nverts.
                                                                          length;
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            USER,
                                                                          "RIB - Parsed %d hair curves",
                                                                          nhairs);
                                                                      api.
                                                                        parameter(
                                                                          "segments",
                                                                          nverts[0] -
                                                                            1);
                                                                      p.
                                                                        checkNextToken(
                                                                          "nonperiodic");
                                                                      p.
                                                                        checkNextToken(
                                                                          "P");
                                                                      float[] points =
                                                                        this.
                                                                        parseFloatArray(
                                                                          p);
                                                                      if (points.
                                                                            length !=
                                                                            3 *
                                                                            nhairs *
                                                                            nverts[0]) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                USER,
                                                                              "RIB - Invalid number of points - expecting %d - found %d",
                                                                              nhairs *
                                                                                nverts[0],
                                                                              points.
                                                                                length /
                                                                                3);
                                                                          return false;
                                                                      }
                                                                      api.
                                                                        parameter(
                                                                          "points",
                                                                          "point",
                                                                          "vertex",
                                                                          points);
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            USER,
                                                                          "RIB - Parsed %d hair vertices",
                                                                          points.
                                                                            length /
                                                                            3);
                                                                      p.
                                                                        checkNextToken(
                                                                          "width");
                                                                      float[] w =
                                                                        this.
                                                                        parseFloatArray(
                                                                          p);
                                                                      if (w.
                                                                            length !=
                                                                            nhairs *
                                                                            nverts[0]) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                USER,
                                                                              ("RIB - Invalid number of hair widths - expecting %d - found %" +
                                                                               "d"),
                                                                              nhairs *
                                                                                nverts[0],
                                                                              w.
                                                                                length);
                                                                          return false;
                                                                      }
                                                                      api.
                                                                        parameter(
                                                                          "widths",
                                                                          "float",
                                                                          "vertex",
                                                                          w);
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            USER,
                                                                          "RIB - Parsed %d hair widths",
                                                                          w.
                                                                            length);
                                                                      String name =
                                                                        String.
                                                                        format(
                                                                          "%s[%d]",
                                                                          filename,
                                                                          index);
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            USER,
                                                                          "RIB - Creating hair object \"%s\"",
                                                                          name);
                                                                      api.
                                                                        geometry(
                                                                          name,
                                                                          new Hair(
                                                                            ));
                                                                      api.
                                                                        instance(
                                                                          name +
                                                                          ".instance",
                                                                          name);
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            USER,
                                                                          "RIB - Searching for next curve group ...");
                                                                      while (true) {
                                                                          String t =
                                                                            p.
                                                                            getNextToken();
                                                                          if (t ==
                                                                                null ||
                                                                                t.
                                                                                equals(
                                                                                  "TransformEnd")) {
                                                                              done =
                                                                                true;
                                                                              break;
                                                                          }
                                                                          else
                                                                              if (t.
                                                                                    equals(
                                                                                      "Curves"))
                                                                                  break;
                                                                      }
                                                                      index++;
                                                                  }while(!done); 
                                                                  UI.
                                                                    printInfo(
                                                                      Module.
                                                                        USER,
                                                                      "RIB - Finished reading rib file");
                                                            }
                                                            catch (FileNotFoundException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      USER,
                                                                    "RIB - File not found: %s",
                                                                    filename);
                                                                e.
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            catch (ParserException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      USER,
                                                                    "RIB - Parser exception: %s",
                                                                    e);
                                                                e.
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            catch (IOException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      USER,
                                                                    "RIB - I/O exception: %s",
                                                                    e);
                                                                e.
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            return true;
    }
    private int[] parseIntArray(Parser p)
          throws IOException { IntArray array =
                                 new IntArray(
                                 );
                               boolean done =
                                 false;
                               do  { String s =
                                       p.
                                       getNextToken();
                                     if (s.
                                           startsWith(
                                             "["))
                                         s =
                                           s.
                                             substring(
                                               1);
                                     if (s.
                                           endsWith(
                                             "]")) {
                                         s =
                                           s.
                                             substring(
                                               0,
                                               s.
                                                 length() -
                                                 1);
                                         done =
                                           true;
                                     }
                                     array.
                                       add(
                                         Integer.
                                           parseInt(
                                             s));
                               }while(!done); 
                               return array.
                                 trim(); }
    private float[] parseFloatArray(Parser p)
          throws IOException { FloatArray array =
                                 new FloatArray(
                                 );
                               boolean done =
                                 false;
                               do  { String s =
                                       p.
                                       getNextToken();
                                     if (s.
                                           startsWith(
                                             "["))
                                         s =
                                           s.
                                             substring(
                                               1);
                                     if (s.
                                           endsWith(
                                             "]")) {
                                         s =
                                           s.
                                             substring(
                                               0,
                                               s.
                                                 length() -
                                                 1);
                                         done =
                                           true;
                                     }
                                     array.
                                       add(
                                         Float.
                                           parseFloat(
                                             s));
                               }while(!done); 
                               return array.
                                 trim(); }
    public ShaveRibParser() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163561896000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVYe2wcxRmfe/h5Dn4kcR6EOHGezmMXkziSbSTi2g655JKY" +
       "uyQEh3AZ786dN9nb\nXXbn7LNxeShSnBIViKBKW5U0oEghAQoSrdJKlCYCWt" +
       "r8A0gtEhK0VaS2UktVVImmav/oNzN7d3t7\nd6YKwtLtzs58j/lev/nGr3yG" +
       "ahwbLVMciU5ZxJEGEyPYdog6qGPH2Q9TSeXdmoaRi7sNM4gCMRTU\nVIqaY4" +
       "ojq5hiWVPl6FB/zkYbLVOfSusmlUiOSsf0HlferlhPmcD7z11pe+JCuCOIam" +
       "KoGRuGSTHV\nTGNYJxmHopbYMTyB5SzVdDmmObQ/huYRI5sZNA2HYoM6D6NH" +
       "USiGai2FyaRoZSyvXAblsoVtnJG5\nenmEqwUJ821CsWYQdaCgDjg3lXLCtl" +
       "2+eDk1CKlniwfBHL4DsHpFwWphbZmpVuilg9tmzl8KoeZR\n1KwZCSZMAUso" +
       "6BtFTRmSGSO2M6CqRB1FrQYhaoLYGta1aa51FLU5WtrANGsTJ04cU59ghG1O" +
       "1iI2\n15mfjKEmhdlkZxVq2gUfpTSiq/mvmpSO02B2e9FsYe4ONg8GNmqwMT" +
       "uFFZJnCR/XDIh4h5+jYOPq\n3UAArHUZQsfNgqqwgWECtYlY6thIywlqa0Ya" +
       "SGvMLGihaGlVoczXFlaO4zRJUrTYTzciloCqgTuC\nsVC00E/GJUGUlvqi5I" +
       "nPxvYvTr30g7e289wOq0TR2f4bgWm5jylOUsQmhkIE482s9Fz0geyyIEJA\n" +
       "vNBHLGgG1lw5EPvLLzoEze0VaPaNHSMKTSp7z3TEH7nXRCG2jXrLdDQW/BLL" +
       "eTmMuCv9OQuqtr0g\nkS1K+cWr8V8+8Phl8tcgaoyiWsXUsxnIo1bFzFiaTu" +
       "x7iUFsTIkaRQ3EUAf5ehTVwTgGKS9m96VS\nDqFRFNb5VK3Jv8FFKRDBXNQA" +
       "Y81Imfmxhek4H+cshFAd/NBd8GtB4o+/KeqVZCdrpHRzUnZsRTbt\ndOFbMW" +
       "3CStchtpwYxxMkro3xcrIllkIWRQfkcTNDZKxgQzNMOa1B0SrmZpVMsPetCs" +
       "6xXbdNBgIM\nBv3lrEMl7DR1ldhJ5eKN38wM7/7WKZEqLL1deylaC/okV5/E" +
       "9ElCn1SqDwUCXM0CplfEDDx+HGoX\nUK6pK3Fk19FTnSFIFmsyDO5ipJ1gmb" +
       "uZYcUcLBZ4lGOhAlm2+MXDs9LNi/eILJOr43BF7sj7r14/\n/8+mDUEUrAyS" +
       "zEiA6UYmZoQhawH8VvvLqpL8vz+5543fXf9kfbHAKFpdVvflnKxuO/3hsE2F" +
       "qICE\nRfEXljSH7kcHzwRRGMAAAJDvH7BluV9HSf3257GQ2VIXQ5GUaWewzp" +
       "byANZIx21zsjjD86SFPRaI\nlGGB9G2Qw+jNE7V3fvRm5F1ucR5xmz1nWoJQ" +
       "Ub+txTzYbxMC8598d+TZ73w2e5gngZsFFA667Jiu\nKTlgWVtkgcLVATxYjF" +
       "YfMDKmqqU0PKYTlkz/bV7T/ZO/PdUivK7DTD5om75cQHF+yTfQ49cf+tdy\n" +
       "LiagsIOjaEaRTFgzvyh5wLbxFNtH7okP7/jer/DzgGuAJY42TTg8IG4Z4n6U" +
       "uHu7+HOzb+1O9ugE\n2ZuqZHWFYzqpzFxOd2Yf/vXP+K4j2Hvee8OwB1v9Iq" +
       "hc93xQugW5jxLYYqsLLfZsZyFY5K/endgZ\nB2Fbr+59sEW/+h9QOwpqFThD" +
       "nX02QEeuJNIudU3dx9febj/6QQgFd6BG3cTqDszzHzVA4hFnHFAn\nZ92zXW" +
       "xjsj6/mRziu13qeilX8sU/VvHnWjd72Hi9lyrAx4soWlYGWgkFjgYBVczQO6" +
       "odm/zInz30\nedNJ/M4RATttpUfRMLRrf556m6y7+9t/rICXDdS0Nutkguie" +
       "jYWYyhK428M7imKxP3np5Sv0g419\nQuWG6kjnZ9zQd366o++107cAch0+J/" +
       "hFt07cfl9oXHsvyJsegW9lzVIpU7/XHaAU9pO1DeZYNtPE\nc3JFIScjLLK9" +
       "8Gt1c7LVn5Mcjdijz1dKQe7XYD7gLTwJWSsmiVYsv9DuzYSEeA+MRLng3XOU" +
       "533s\nsZOiGn7aQfgWe68RtpaBdmSC4+2Nk50/f+/AD2dF5LrmuCt4uZLKY7" +
       "//w/HQ09fGBJ+/JfMRn1l+\n4U9v3IgvEAkn+tZVZa2jl0f0rtyWZoul/Mq5" +
       "NHDqdzaufOXR+Kd8R4xvO0V1Y6apEyyyqJs9oqLg\ner60fPnHUCHWTWyyC3" +
       "6zbqxnK8a6QpnDLixbm4C2LucLWKC06pd4Y+1MOZRkJLfkGYU6N7fAeM2U\n" +
       "ovuGcwqxWPFwPt77PUTRPJ4KUYPyI4AdkZ6U4HPMk5eeGZof7z18gtdjA1x2" +
       "sLO3WBBwxWSjAER8\nTfVMKQhLKuuOXPnHtbfIOo689ZoDN7QBO12h8ffwfI" +
       "4vkz0fpc7xxiE8hh1RfP4bU/mFqOSew22/\nzRI+T7jvQxSFgM2yLOh4G7l1" +
       "3Vt7t20Bb7SBN9i9WdJUKWYqWI8OvXAt8uGZ7LZdIsXneQiiQzOv\n72qqf+" +
       "H0SQ4urlsaPBcE97tuAtt7i/jKXnDWJb+Wdrmve0vPpu67Nm/tBpwrCfZ6SZ" +
       "K61jkrinBW\nLIjkVykIlodn3YI4WxX8jn6lvH/sFvP+BHvMQGPBXbEDznE3" +
       "89m8Xp4Y0A8CiT81tvJl3qOd/voC\n19PNA9dDAeZKdztX6L75/4YuR9FtpX" +
       "pZV7i47D8k4lavxD5+5MEvYr/9tyjA/M07Atmdyuq653z0\nnpW1lk1SGvd8" +
       "RPRuFn89R9GiKncw1j7zAd/ss4L+LJyGfnqKwuzlJfs+RREPGYCsO/ISPQ/l" +
       "DkRs\neM6qcNaKO35po8Y8s6oE2/g/rfI9Rlb82yqpHHr18Irc6f3PcKCsUX" +
       "Q8Pc3ENELNixtLoU9ZWVVa\nXtb76PXXDr75o978yVVylymrwW6xOkfsoTeq" +
       "fJcYzliUd//TP13047svnvs0yG8z/wNbuyKQaxQA\nAA==");
}
