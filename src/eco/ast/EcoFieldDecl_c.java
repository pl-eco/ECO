package eco.ast;

import java.util.List;

import eco.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.FieldDecl_c;
import polyglot.ast.Id;
import polyglot.ast.LocalDecl_c;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.JL5FieldDecl_c;
import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoFieldDecl_c extends JL5FieldDecl_c {

	public EcoFieldDecl_c(Position pos, Flags flags, List<AnnotationElem> annotations, TypeNode type, Id name,
			Expr init) {
		super(pos, flags, annotations, type, name, init);
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (!(type.type() instanceof PatternType) &&
				init != null && init.type() instanceof PatternType) {
			Expr newInit = (Expr) ((EcoNodeFactory_c) tc.nodeFactory()).Select(init);
			return reconstruct(type, name, newInit).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
