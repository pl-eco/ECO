package eco;

import eco.parse.Lexer_c;
import eco.parse.Grm;
import eco.ast.*;
import eco.types.*; 

import polyglot.lex.Lexer;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.main.*;
import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl7.JL7ExtensionInfo;
import polyglot.ext.jl5.ast.JL5ExtFactory_c;
import polyglot.ext.jl7.ast.JL7ExtFactory_c;

import java.io.*;

public class EcoExtensionInfo extends JL7ExtensionInfo {
  static {
    // force Topics to load
    @SuppressWarnings("unused")
    Topics t = new Topics();
  }

  @Override
  public String defaultFileExtension() {
    return "eco";
  }

  @Override 
  public String[] defaultFileExtensions() {
    String ext = defaultFileExtension();
    return new String[] {ext, "java"};
  }

  @Override
  public String compilerName() {
    return "ecoc";
  }

  @Override
  public Parser parser(Reader reader, FileSource source, ErrorQueue eq) {
    reader = new polyglot.lex.EscapedUnicodeReader(reader);

    Lexer lexer = new Lexer_c(reader, source, eq);
    Grm grm = new Grm(lexer, ts, nf, eq);

    return new CupParser(grm, source, eq);
  }

  @Override
  protected NodeFactory createNodeFactory() {
    return new EcoNodeFactory_c(EcoLang_c.instance, 
        new EcoExtFactory_c(
          new JL7ExtFactory_c(
            new JL5ExtFactory_c())));
  }

  @Override
  protected TypeSystem createTypeSystem() {
    return new EcoTypeSystem_c();
  }

  @Override
  public Scheduler createScheduler() {
    return new EcoScheduler(this);
  }

}
