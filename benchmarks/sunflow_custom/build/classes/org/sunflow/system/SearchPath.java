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
      1163561248000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "P7r9hw88v3yN37H+DYKw0do2FQAA");
}
