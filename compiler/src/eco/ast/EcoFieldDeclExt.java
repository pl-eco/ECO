package eco.ast;

import java.util.List;

import eco.types.PatternType;

import polyglot.ast.Expr;
import polyglot.ast.FieldDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;

import polyglot.ext.jl5.ast.JL5FieldDeclExt;

public class EcoFieldDeclExt extends EcoExt {

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
    FieldDecl decl = (FieldDecl) this.node();
    TypeNode type = decl.type();
    Expr init = decl.init();
    String name = decl.name();

		if (!(type.type() instanceof PatternType) 
        && init != null 
        && init.type() instanceof PatternType) {
			Expr newInit = (Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(init);
			return reconstruct(type, name, newInit).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

  private Node reconstruct(TypeNode type, String name, Expr init) {
    FieldDecl f = (FieldDecl) this.node().copy();
    f = f.type(type);
    f = f.name(name);
    f = f.init(init);
    return f;
  }

}
