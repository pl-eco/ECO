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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
