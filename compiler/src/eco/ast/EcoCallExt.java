package eco.ast;

import eco.types.PatternType;

import polyglot.types.*;
import polyglot.ast.Call;
import polyglot.ast.Call_c;
import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.TypeNode;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

import java.util.ArrayList;
import java.util.List;

public class EcoCallExt extends EcoExt {

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
	  // Reconstruct Arguments
	  List<Expr> arguments = new ArrayList<Expr>();
	  boolean argumentsNeedTransform = false;

    Call call = (Call) this.node();
	  
	  for (Expr e : call.arguments()) {
	    if (e.type() instanceof PatternType) {
	      arguments.add((Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(e));
	      argumentsNeedTransform = true;
	    } else {
	      arguments.add(e);
	    }
	  }

    Receiver target = call.target();
	  
		if (target != null && target.type() instanceof PatternType) {
			Receiver newtarget = 
        (Receiver) ((EcoNodeFactory) tc.nodeFactory()).Select((Expr) target);
			return reconstruct(newtarget, call.id(), arguments).typeCheck(tc);
		} else if (argumentsNeedTransform) {
		  return reconstruct(target, call.id(), arguments).typeCheck(tc);
		} else {
		  return super.typeCheck(tc);
		}
		
	}

  private Node reconstruct(Receiver target, Id id, List<Expr> arguments) {
    Call c = (Call_c) this.node().copy();
    c = c.target(target);
    c = c.id(id);
    c = (Call) c.arguments(arguments);
    return c;
  }

}
