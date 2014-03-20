package et.ast;

import java.util.Iterator;
import java.util.List;

import cs.ast.CSCall_c;
import cs.graph.CSGraph;
import cs.types.CSBaseType;
import cs.types.CSNonGenericType;
import et.linersolve.ETValueHolder;
import et.types.ETTYPE;
import et.types.PatternType;
import et.types.etTypeSystem_c;
import polyglot.types.*;
import polyglot.ast.Call_c;
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.TypeNode;
import polyglot.ext.jl5.ast.JL5Call_c;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class DynamicECMCall_c extends CSCall_c {
	// boolean isStatement = false;

	public DynamicECMCall_c(Position pos, Receiver target,
			List<TypeNode> typeArgs, Id name,
			List arguments) {
		super(pos, target, typeArgs, name, arguments);
	}


	// public Node typeCheckOverride(Node parent, TypeChecker tc)
	// throws SemanticException {
	// if (parent instanceof Eval) {
	// this.isStatement = true;
	// }
	// return super.typeCheckOverride(parent, tc);
	// }

	// private void getValueDemo() {
	// /*
	// * the following code shows how to get value of the call's target
	// */
	// if (!(this.target() instanceof CSBaseType))
	// // this is a call from an array(e.g. array.clone()), you don't want
	// // to get value from it
	// return;
	//
	// CSBaseType t = (CSBaseType) this.target();
	//
	// // the value holder that takes result of all variables
	// ETValueHolder vh = ((etTypeSystem_c) type().typeSystem())
	// .getValueHolder();
	//
	// // a auxiliary data structure to help get the value
	// CSGraph graph = ((etTypeSystem_c) type().typeSystem()).getGraph();
	//
	// String mode = vh.getFinalResult(t.getAliasName(), ETTYPE.MODE, graph);
	// }
	
	protected DynamicECMCall_c reconstruct(Receiver target, Id name, List<Expr> arguments) {

        if (target != this.target || name != this.name
                || !CollectionUtil.equals(arguments, this.arguments)) {
    		DynamicECMCall_c n = (DynamicECMCall_c) copy();
    		
            // If the target changes, assume we want it to be an explicit target.
            n.targetImplicit &= target == n.target;

            n.target = target;
            n.name = name;
            n.arguments = ListUtil.copy(arguments, true);
            return n;
        }

        return this;
    }
	

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (target != null && target.type() instanceof PatternType) {
			Receiver newtarget = (Receiver) ((etNodeFactory_c) tc.nodeFactory()).Select((Expr) target);
			return reconstruct(newtarget, this.name, this.arguments).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
	
	@Override
	// check methodInstance's flag. whether that flag = noscale
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		super.prettyPrint(w, tr);
	}
}