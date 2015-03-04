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
                                                   close(
                                                     );
                                             bf = null; }
    public String getNextToken() throws IOException { while (true) {
                                                          String tok =
                                                            fetchNextToken(
                                                              );
                                                          if (tok ==
                                                                null)
                                                              return null;
                                                          if (tok.
                                                                equals(
                                                                  "/*")) {
                                                              do  {
                                                                  tok =
                                                                    fetchNextToken(
                                                                      );
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
                                     fetchNextToken(
                                       );
                                   if (t ==
                                         null)
                                       return false;
                                   if (t.
                                         equals(
                                           "/*")) {
                                       do  {
                                           t =
                                             fetchNextToken(
                                               );
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
                if (!getNextLine(
                       ))
                    return null;
        }
    }
    private boolean getNextLine() throws IOException {
        String line =
          bf.
          readLine(
            );
        if (line ==
              null)
            return false;
        ArrayList<String> tokenList =
          new ArrayList<String>(
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
               length(
                 );
             i++) {
            char c =
              line.
              charAt(
                i);
            if (current.
                  length(
                    ) ==
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
                      length(
                        ) >
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
              length(
                ) >
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
                      checkNextToken("<code>");
                      while (true) { String line;
                                     try {
                                         line =
                                           bf.
                                             readLine(
                                               );
                                     }
                                     catch (IOException e) {
                                         e.
                                           printStackTrace(
                                             );
                                         return null;
                                     }
                                     if (line.
                                           trim(
                                             ).
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
            getNextToken(
              )).
          booleanValue(
            );
    }
    public int getNextInt() throws IOException {
        return Integer.
          parseInt(
            getNextToken(
              ));
    }
    public float getNextFloat() throws IOException {
        return Float.
          parseFloat(
            getNextToken(
              ));
    }
    public void checkNextToken(String token)
          throws ParserException,
        IOException { String found = getNextToken(
                                       );
                      if (!token.equals(found)) {
                          close(
                            );
                          throw new ParserException(
                            token,
                            found);
                      } }
    @SuppressWarnings("serial") 
    public static class ParserException extends Exception {
        private ParserException(String token,
                                String found) {
            super(
              String.
                format(
                  "Expecting %s found %s",
                  token,
                  found));
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "jpszD+Ww/wuuYzm1rQsAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1yfVl8o3OJVJNTZ19ledVGzY3/c/x8FdGgMZAAA=");
}
