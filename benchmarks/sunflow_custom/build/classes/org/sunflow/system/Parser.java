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
          1158386604000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "e1wOx/Tnyz7dfmPmgRz2fwEfI1d+wwsAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1158386604000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
