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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "v9qywzvAS25BQd2Ev/8L2sj6ZVsUAAA=");
}
