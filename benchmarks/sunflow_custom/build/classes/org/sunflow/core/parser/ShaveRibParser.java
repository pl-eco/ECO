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
                                                                            getNextToken(
                                                                              );
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
                                                                                getNextToken(
                                                                                  );
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
                                                                        getNextToken(
                                                                          );
                                                                      p.
                                                                        getNextToken(
                                                                          );
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
                                                                            getNextToken(
                                                                              );
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
                                                                  printStackTrace(
                                                                    );
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
                                                                  printStackTrace(
                                                                    );
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
                                                                  printStackTrace(
                                                                    );
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
                                       getNextToken(
                                         );
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
                                                 length(
                                                   ) -
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
                                 trim(
                                   ); }
    private float[] parseFloatArray(Parser p)
          throws IOException { FloatArray array =
                                 new FloatArray(
                                 );
                               boolean done =
                                 false;
                               do  { String s =
                                       p.
                                       getNextToken(
                                         );
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
                                                 length(
                                                   ) -
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
                                 trim(
                                   ); }
    public ShaveRibParser() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163561896000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "r5uI938B6qgkh0sUAAA=");
}
