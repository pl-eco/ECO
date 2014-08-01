package eco.ast;

import java.util.List;

import cs.ast.CSCall_c;
import eco.types.PatternType;
import polyglot.types.*;
import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.TypeNode;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

public class EcoCall_c extends CSCall_c {
	// boolean isStatement = false;

	public EcoCall_c(Position pos, Receiver target,
			List<TypeNode> typeArgs, Id name,
			List arguments) {
		super(pos, target, typeArgs, name, arguments);
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (target != null && target.type() instanceof PatternType) {
			Receiver newtarget = (Receiver) ((EcoNodeFactory_c) tc.nodeFactory()).Select((Expr) target);
			return reconstruct(newtarget, this.name, this.arguments).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
		
		//add conversion for arguments
	}
}