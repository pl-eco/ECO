package et.ast.mswitch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import et.ast.etNodeFactory_c;
import et.types.etTypeSystem_c;
import polyglot.ast.Case;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.SwitchElement;
import polyglot.ast.Switch_c;
import polyglot.ext.jl5.ast.JL5Switch_c;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.ConstantChecker;
import polyglot.visit.TypeChecker;

public class MSwitch_c extends JL5Switch_c {
	public MSwitch_c(Position pos, Expr expr, List elements) {
		super(pos, expr, new LinkedList());

		// @2.5update
//		super.elements = TypedList.copyAndCheck(elements, MSwitchGroup.class,
//				true);
		super.elements = ListUtil.copy(elements, true);
				
	}

	public Node typeCheck(TypeChecker tc) throws SemanticException {
		TypeSystem ts = tc.typeSystem();

		if (!ts.isImplicitCastValid(expr.type(), ((etTypeSystem_c) ts).modeVT())) {
			throw new SemanticException("Switch index must be an mode type.",
					position());
		}

		return this;
	}

	public Node checkConstants(ConstantChecker cc) throws SemanticException {
		return this;
	}

	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(expr, this, EXIT);
		v.visitCFGList(elements, this, EXIT);
		return succs;
	}

	@Override
	public Node copy(NodeFactory nf) {
		return ((etNodeFactory_c) nf).MSwitch(this.position, this.expr,
				this.elements);
	}
}
