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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "3DUUAAA=");
}
