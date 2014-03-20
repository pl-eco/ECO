package et.ast;

import cs.data.id.ID;
import cs.data.id.VariableGenerator;
import cs.graph.CSGraph;
import et.data.DecoratorID;

public class MPtnAppID extends DecoratorID {

	public MPtnAppID(String varName, String position, ID innerID) {
		super(VariableGenerator.nextAlias(), varName, position, innerID);
	}

	@Override
	public void buildGraph(CSGraph graph) {
		// new mode value with same phase
		assert false;
	}

}
