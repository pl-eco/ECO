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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "KRkAAA==");
}
