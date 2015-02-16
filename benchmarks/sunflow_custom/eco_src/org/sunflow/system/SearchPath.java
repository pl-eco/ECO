package org.sunflow.system;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.sunflow.system.UI.Module;
public class SearchPath {
    private java.util.LinkedList<String> searchPath;
    private String type;
    public SearchPath(String type) { super();
                                     this.type = type;
                                     searchPath = new java.util.LinkedList<String>(
                                                    ); }
    public void resetSearchPath() { searchPath.clear(); }
    public void addSearchPath(String path) { File f = new File(path);
                                             if (f.exists() && f.
                                                   isDirectory()) {
                                                 try {
                                                     path =
                                                       f.
                                                         getCanonicalPath();
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
                                                           getMessage());
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
              isAbsolute()) {
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
                      exists()) {
                    return f.
                      getAbsolutePath();
                }
            }
        }
        return filename;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163561248000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK0YC2wUx3V85w8+G2wMGMLP2JhQwNyR2HyKkYIxJhgOcP0j" +
       "MdDLeHfOXtjb3ezO\nnQ8HUdKmmIQ2KWpLS1UITan4NGlo0oikSlMiQpuGtk" +
       "oilUpIIa2o2qhpKlWVUqpWad/M7N1+7s7Q\nJift7tzMvPfm/d+bpz9AJZaJ" +
       "ZktWmO41iBVu7+nCpkXkdhVbVi9MxaTLJeVdpzdregAVRVFAkSmq\nikpWRM" +
       "YURxQ50rm+NW2iJYau7h1SdRomaRrerS638W2KLs9BuP3EhZqHTxXXBVBJFF" +
       "VhTdMppoqu\ndagkYVFUHd2NUziSpIoaiSoWbY2iiURLJtp1zaJYo9aDaD8K" +
       "RlGpITGcFNVHM8QjQDxiYBMnIpx8\npIuTBQxTTEKxohG5LUsOIJu8kHBsG6" +
       "47dzcgmcAW+4EdfgLgel6Wa8FtDqtG8Ez/in0nzwZR1QCq\nUrQehkwCTijQ" +
       "G0CVCZIYJKbVJstEHkCTNULkHmIqWFVGOdUBVGMpQxqmSZNY3cTS1RTbWGMl" +
       "DWJy\nmpnJKKqUGE9mUqK6mZVRXCGqnPlXElfxELBd67At2N3A5oHBkAIHM+" +
       "NYIhmQ4j2KBhqv80NkeWzc\nDBsAtCxB6LCeJVWsYZhANUKXKtaGIj3UVLQh" +
       "2FqiJ4EKRTMLImWyNrC0Bw+RGEUz/Pu6xBLsKueC\nYCAUTfNv45hASzN9Wn" +
       "LpZ0nth4fOfPuVtdy2i2Uiqez8IQCa6wPqJnFiEk0iAvBmMvy1zvuTswMI\n" +
       "weZpvs1iT9uCC33R935aJ/bMyrNn2+BuItGYtPVIXfdD9+ooyI4xwdAthSnf" +
       "wzl3hy57pTVtgNfW\nZjGyxXBm8WL3z+4/cI68H0ChTlQq6WoyAXY0WdIThq" +
       "IS816iERNTIneicqLJ7Xy9E5XBOAomL2a3\nxeMWoZ2oWOVTpTr/DyKKAwom" +
       "onIYK1pcz4wNTIf5OG0ghMrgQZXwlCPx41+KloUjVlKLq/pIxDKl\niG4OOf" +
       "/3WpQkIj0Em9JwF2ALM8sxKIpGhvUEiWAJa4qmR4YU8FVJXyqTFPv+j/jS7I" +
       "w1I0VFLOj5\nnVcFu9+oqzIxY9LpG2/s69j86CFhGMyYbe4omgNkwjaZsCAT" +
       "dsigoiKOfSojJxQDYt0DDgqhrHJR\nz65NDxxqCIJFGCPFIBO2tQH4sM/QIe" +
       "ntjhd38oAngSnNeGrHWPjm6XuEKUUKB9u80BVvPnPl5N8r\nFwdQIH8kZLxB" +
       "LA4xNF0sfGYjXKPfd/Lh/+tjW56/euWdTzleRFFjjnPnQjLnbPBrwdQlIkO4" +
       "c9Cf\nuqMquB31HwmgYvB4iHL8/BBA5vppeJy0NRPwGC9lUVQR180EVtlSJk" +
       "qF6LCpjzgz3Dyq+XgKKKeC\nWe00eCbaZsy/bHWawd61wpyYtn1c8IB68wul" +
       "y377csVlLpZM7K1yZbceQoUnT3aMpdckBObf+WbX\nV7/+wdgObim2qVBIec" +
       "lBVZHSAHKnAwIurEIYYYps7NMSuqzEFTyoEmZx/65acNcLf3m8WqhGhZmM\n" +
       "ZptujcCZv2MdOnDls/+Yy9EUSSyFOGw42wQ3UxzMbaaJ97JzpB9+e86xn+Pj" +
       "EOEgqljKKOGBosh2\nAnao6ZD6OSTLFmGRLbiAI3x5MX+HmQY4EOJrzezVAE" +
       "SbCvhEnkwek/adG2pIPviLlzg7FdhdErj1\nswUbrcIk2Gs+E/t0v1tvxNYw" +
       "7Gu5uHVntXrxX4BxADBKkEGtbSaEkrRHu/bukrJrr16qfeCtIAps\nQCFVx/" +
       "IGzB0DlYNFEmsYolDauGctN7rqkQnszVlGXAgzbQGkXf8CcLhFhePCBlYHOC" +
       "4VG2w6E31j\n23EugIIRIU8a9OEZfaXvxM1f0escj+OaDLo+nRtioXZyYFdd" +
       "TU0uPf9kIoDKBlC1ZFd3/VhNMgcY\ngGLEypR8UAF61r2FhciirdnQM9sfFl" +
       "xk/UHBCe0wZrvZuNIXB1gmQ4vhCdlxIOSPA0WID9o4SCN/\nL8x6bZlhKinM" +
       "Kj4UsrKJAvQ1y1V29yQHLeqqTJavXdn03Madl3jMLoeCEFtbnaNCGc5GRSDj" +
       "xYWV\n7sdpXuq9TG5MOyqyiNdaeNVsg/rhji56P7Vk2eeP87MUD2KLHyMEor" +
       "TYTormFa7AOS7hRROFQP8D\nPwTPR+xhguQTvD6Y6a78M0kzzMt8w0hnokTe" +
       "1MrWtoJMa/wyjUnTdxp9/ehLOwXb9beQV0ya+csf\nX91+rd/gRl2VUqCsIn" +
       "KvXfp7Q59Dv9XTDuSVaEz6Q8Ou698qfvcYr/OE8Ni5l0PYZN9VtsdXZj2e\n" +
       "lQfTXRzZp+DR+c+/Pjb1EfOQzrVSwi0k1zoWFODWjSgmPf7euo8ORPcsDDCX" +
       "CDEvwiYUiFCBhgt1\nSG4Ejb0wWg9Q4F2TBDTEbm5AtqHUZGez+Z2ipYVws6" +
       "bRXwb4HRei5ggx1+lJjfNa73XlUNIw3Kvc\n/iZ9TPsz2I+ioo486UrEIHBw" +
       "roe7m1c0LzMKZ7W0J4y7oJa32CbMuuuwItvxdv3bGwbPxbVzcoBX\nLbypa3" +
       "MpvJzPuOJDUDcs1j64+nQbU+M2w2K2OtFFpHP9vvObKid85/BBjt82n3JXK2" +
       "L/L0thc6u7\nFq7gB79r5armu1dQ1PdJluqrW1Y0NTcvbWmGnNy7sbMn7IRO" +
       "Rl72BNP90A3lSo3xakdsVMOdf5Kj\nig4tmXAvAjfF3R1t60VJx94t7LVO6G" +
       "plwWS8xpsmZmTqxsw3T5rgEO1UJJyCVuI7CB3nINy87/QY\nFdjRnEJ9L+/Z" +
       "x+77W+VB/NouERVrvL0kE86f9l4iC9d8+fd5WqByqhtLVZIiqotmkJH0tDJb" +
       "+JWA\nk/EfO/v9C/StJasFyXESlx9w8eqTo3Wrnz38fzQwdT4h+FFPTs36TH" +
       "BYeZ1bvl1A5Nx2eIFafbEG\nzpM0tV5P8TDP20TUCyPLGFueJsLRYP5Cd2yc" +
       "tUfZ6xHwEygdCe1xlxgz3Dd7ppIAb07x7ujGwYaf\nvN735Fi+WsB7feeGik" +
       "mfe/d3e4JPvDoo4PzloW/zkbmn/vj8je6pwoTEVdL8nNscN4y4TuJcVRnM\n" +
       "iOvHo8B3v7ak/un93df5iRjcPnCslK7Ijut88VY+nHUa9ueAV3Ws7qu1VVd7" +
       "26q7rcbm2Dg6Pc5e\nRymaiGW5xxP5nnAY+8bHYYxRXGgztvCTZex74zB2lr" +
       "2egtRhiptLbqiFsDmsfvd2WWXp1BEY60dn\n5NzSiptFKXrtoZ0fRn/zT36x" +
       "kL39q4C8F0+qqrszcI1LDZPEFc5KhegTDP55DpqS3Jsh6NnFgJ/x\nh2LrC8" +
       "C9ayt0CPbIvelFClnc5FngpXylhKg4vJUE43Z+wSJ0S1Jch8ek+57ZMS99uP" +
       "cronSE+mZ0\n1C7UysQlSTZ81hfElsH1Jjr/bP/LP/h0xv14rzw17WQvjw22" +
       "iNVxdAkhO//NREfCoPwuYfTF6T9a\nc/rE9QC/G/kvhn8d/sMYAAA=");
}
