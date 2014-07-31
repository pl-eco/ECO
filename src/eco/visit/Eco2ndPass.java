package eco.visit;

import cs.graph.CSGraph;
import cs.types.csTypeSystem_c;
import cs.util.EasyDebugger;
import cs.visit.CS2ndPass;
import eco.types.EcoTypeSystem_c;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.TypeSystem;

public class Eco2ndPass extends CS2ndPass {
	public Eco2ndPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	@Override
	protected void postCSEngine(CSGraph graph) {
		graph.addExtraConstraint();
		graph.solve(((EcoTypeSystem_c) ts).getValueHolder(), true, false);

		EasyDebugger.message("DONE.");
	}
}
