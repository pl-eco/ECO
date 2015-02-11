package eco.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;

import polyglot.ext.jl7.ast.J7Lang_c;

public class EcoLang_c extends J7Lang_c implements EcoLang {
  public static final EcoLang_c instance = new EcoLang_c();

  public static EcoLang lang(NodeOps n) {
    while (n != null) {
      Lang lang = n.lang();
      if (lang instanceof EcoLang) return (EcoLang) lang;
      if (n instanceof Ext)
        n = ((Ext) n).pred();
      else return null;
    }
    throw new InternalCompilerError("Impossible to reach");
  }

  protected EcoLang_c() {
  }

  protected static EcoExt ecoExt(Node n) {
    return EcoExt.ext(n);
  }

  @Override
  protected NodeOps NodeOps(Node n) {
    return ecoExt(n);
  }

}
