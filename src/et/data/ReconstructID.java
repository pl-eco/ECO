package et.data;

import cs.data.id.ID;
import cs.data.id.VariableGenerator;
import cs.graph.CSGraph;

public class ReconstructID extends DecoratorID {
	public ReconstructID(String varName, String position, ID innerID) {
		super(VariableGenerator.nextAlias(), varName, position, innerID);
	}

	@Override
	public void buildGraph(CSGraph graph) {
		// new mode value with same phase
		assert false;
	}

}
