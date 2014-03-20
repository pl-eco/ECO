package et.visit;

import cs.graph.CSGraph;
import cs.types.csTypeSystem_c;
import cs.util.EasyDebugger;
import cs.visit.CS2ndPass;
import et.types.etTypeSystem_c;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.TypeSystem;

public class ET2ndPass extends CS2ndPass {
	public ET2ndPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	@Override
	protected void postCSEngine(CSGraph graph) {
		graph.addExtraConstraint();
		graph.solve(((etTypeSystem_c) ts).getValueHolder(), true, false);

		EasyDebugger.message("DONE.");
	}
}
