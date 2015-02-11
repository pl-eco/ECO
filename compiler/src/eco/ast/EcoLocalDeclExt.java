package eco.ast;

import eco.types.PatternType;

import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.types.LocalInstance;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;


public class EcoLocalDeclExt extends EcoExt {
	private boolean calibrate = false;

  public boolean calibrate() {
    return this.calibrate;
  }

  public void calibrate(boolean calibrate) {
    this.calibrate = calibrate;
  }

  // TODO: Need to figure out the right way to have outside
  // touch the boxed node.
  public LocalInstance localInstance() {
    LocalDecl localDecl = (LocalDecl) this.node();
    return localDecl.localInstance();
  }

  public String name() {
    LocalDecl localDecl = (LocalDecl) this.node();
    return localDecl.name();
  }
	
  @Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
    LocalDecl decl = (LocalDecl) this.node();
    TypeNode type = decl.type();
    Id id = decl.id();
    Expr init = decl.init();

		if (!(type.type() instanceof PatternType) 
        && init != null 
        && init.type() instanceof PatternType) {
			Expr newInit = (Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(init);
      return reconstruct(type, id, newInit).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

  private Node reconstruct(TypeNode type, Id id, Expr init) {
    LocalDecl d = (LocalDecl) this.node().copy();
    d = d.type(type);
    d = d.id(id);
    d = d.init(init);
    return d;
  }

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    boolean printSemi = tr.appendSemicolon(true);
    boolean printType = tr.printType(true);

    LocalDecl decl = (LocalDecl) this.node();
    TypeNode type = decl.type();
    Id id = decl.id();
    Expr init = decl.init();
    Flags flags = decl.flags();

    if (calibrate) {
      flags = flags.Final();
    }
    w.write(flags.translate());
    if (printType) {
        // TODO: Why does this not call prettyPrint?
        print(type, w, tr);
        if (calibrate) {
          w.write("[]");
        }
        w.write(" ");
    }
    tr.print(decl, id, w);

    if (init != null) {
      w.write(" =");
      if (calibrate) { 
        w.write(" {");
      }
      w.allowBreak(2, " ");
      print(init, w, tr);
      if (calibrate) { 
        w.write(" }");
      }
    } else if (calibrate) {
      w.write(" = new ");
      print(type, w, tr);
      w.write("[1]");
    }

    if (printSemi) {
      w.write(";");
    }

    tr.printType(printType);
    tr.appendSemicolon(printSemi);
	} 

	public void markCalibrate() {
		calibrate = true;
	}

}
