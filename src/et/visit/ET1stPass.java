package et.visit;

import cs.data.id.ID;
import cs.data.id.LocalID;
import cs.data.id.VariableGenerator;
import cs.util.CSUtil;
import cs.util.EasyDebugger;
import cs.visit.CS1stPass;
import polyglot.ast.Call;
import polyglot.ast.ClassDecl;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import et.ast.Adapt_c;
import et.ast.Attribute_c;
import et.ast.ECMClassDecl_c;
import et.ast.ETField_c;
import et.ast.ETLocal_c;
import et.ast.MPattern;
import et.ast.MPatternApp;
import et.ast.MPattern_Init_c;
import et.ast.MPtnAppID;
import et.ast.Reconstruct_c;
import et.ast.Select_c;
import et.data.AdaptID;
import et.data.AttributeID;
import et.data.ReconstructID;
import et.types.ETTYPE;
import et.types.etTypeSystem_c;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;

public class ET1stPass extends CS1stPass {
	public ET1stPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v)
			throws SemanticException {

		final Node m = super.leaveCall(parent, old, n, v);

		if (m instanceof Adapt_c) {
			Adapt_c adapt = (Adapt_c) m;
			ID adaptee = CSUtil.getID(adapt.getExpr().type());

			/*
			 * ((adapt)c).m();
			 * 
			 * ((adapt[xx])c).m();
			 */
			// set adapt ID to type
			AdaptID adaptID = new AdaptID(adapt.toString(), "to", adapt
					.position().toString(), adaptee);
			CSUtil.setID(adapt, adaptID);
		} else if (m instanceof Attribute_c) {
			Attribute_c att = (Attribute_c) m;
			ID exprID = CSUtil.getID(att.getExpr().type());

			AttributeID attID = new AttributeID(m.toString(), m.position()
					.toString(), exprID);

			CSUtil.setID(att, attID);
		} else if (m instanceof Reconstruct_c) {
			Reconstruct_c recon = (Reconstruct_c) m;
			ID exprID = CSUtil.getID(recon.getExpr().type());
			ReconstructID reconID = new ReconstructID(m.toString(), m
					.position().toString(), exprID);

			CSUtil.setID(recon, reconID);
		} else if (m instanceof MPatternApp) {
			MPatternApp app = (MPatternApp) m;
			ID exprID = CSUtil.getID(app.getExpr().type());
			MPtnAppID mptnAppID = new MPtnAppID(m.toString(), m.position()
					.toString(), exprID);

			CSUtil.setID(app, mptnAppID);
		} else if (m instanceof MPattern_Init_c) {
			String var = VariableGenerator.nextAlias();
			LocalID id = new LocalID(var, "mpattern" + var, var + "pos");
			CSUtil.setID((Expr) m, id);
		} else if (m instanceof Local) {
			// set if MPattern is for short hand form usage
			if (parent instanceof Field || parent instanceof Call) {
				((ETLocal_c) m).setShortHand();
			}
		} else if (m instanceof Field) {
			// set if MPattern is for short hand form usage
			if (parent instanceof Field || parent instanceof Call) {
				((ETField_c) m).setShortHand();
			}
		} else if(m instanceof Select_c){
			//set id to select ast node
			Select_c select = (Select_c) m;
			
			ID sltID = CSUtil.getID(select.getExpr().type());
			CSUtil.setID(select, sltID);
		}

		return m;
	}
}
