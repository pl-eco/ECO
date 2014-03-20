package et.data;

import cs.data.id.ID;
import cs.visit.AnalysisContext;

/*
 * This class provide a convenient way to implement Adapt, Reconstruct and Attribute IDs
 */
public abstract class DecoratorID extends ID {
	private ID innerID;

	public DecoratorID(String id, String varName, String position, ID innerID) {
		super(id, varName, position);
		this.innerID = innerID;
	}

	public ID getInnerID() {
		return innerID;
	}

	@Override
	public DecoratorID refresh(AnalysisContext context) {
		String nId = context.refreshID(id);
		ID newInnerID = innerID.refresh(context);
		DecoratorID newDecoID = null;
		try {
			newDecoID = (DecoratorID) this.clone();
			newDecoID.id = nId;
			newDecoID.innerID = newInnerID;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return newDecoID;
	}

}
