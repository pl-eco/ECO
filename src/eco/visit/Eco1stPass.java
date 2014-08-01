package eco.visit;

import cs.data.id.ID;
import cs.data.id.LocalID;
import cs.data.id.VariableGenerator;
import cs.util.CSUtil;
import cs.visit.CS1stPass;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import eco.ast.MPattern_Init_c;
import eco.ast.Select_c;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;

public class Eco1stPass extends CS1stPass {
	public Eco1stPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v)
			throws SemanticException {

		final Node m = super.leaveCall(parent, old, n, v);

		if (m instanceof MPattern_Init_c) {
			String var = VariableGenerator.nextAlias();
			LocalID id = new LocalID(var, "mpattern" + var, var + "pos");
			CSUtil.setID((Expr) m, id);
		} else if(m instanceof Select_c){
			//set id to select ast node
			Select_c select = (Select_c) m;
			
			ID sltID = CSUtil.getID(select.getExpr().type());
			CSUtil.setID(select, sltID);
		}

		return m;
	}
}
