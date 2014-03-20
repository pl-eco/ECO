package et.data;

import cs.data.id.ID;
import cs.data.id.VariableGenerator;
import cs.graph.CSGraph;
import cs.graph.GraphElement;
import cs.util.CSUtil;
import cs.visit.AnalysisContext;

public class AdaptID extends DecoratorID implements GraphElement {
	private AnalysisContext context;
	private String adaptTo;

	public AdaptID(String varName, String phase, String position, ID adaptee) {
		super(VariableGenerator.nextAlias(), varName, position, adaptee);
		assert phase == null || !phase.equals("");
		this.adaptTo = phase;
	}

	public ID getAdaptee() {
		return super.getInnerID();
	}

	public String getToPhase() {
		return adaptTo;
	}

	public DecoratorID refresh(AnalysisContext context) {
		AdaptID newID = (AdaptID) super.refresh(context);
		newID.context = context;
		return newID;
		// String nId = context.refreshID(id);
		// ID nAdaptee = adaptee.refresh(context);
		// return new AdaptID(nId, varName, nAdaptee, adaptTo, context,
		// position);
	}

	@Override
	// (adapt)m or (adapt[ph])m
	public void buildGraph(CSGraph graph) {
		// flow from adaptee to adaptor
		// ((ETGraph) graph).addAdapt(this);
		assert false;
	}
}
