package et.data;

import cs.data.id.ID;
import cs.data.id.VariableGenerator;
import cs.graph.CSGraph;
import cs.visit.AnalysisContext;

public class AttributeID extends DecoratorID {
	public AttributeID(String varName, String position, ID att) {
		super(VariableGenerator.nextAlias(), varName, position, att);
	}

	@Override
	public void buildGraph(CSGraph graph) {
		// new mode value with same phase
		assert false;
	}
}
