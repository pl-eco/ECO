package et.types;

import cs.types.CSPrimitiveType;
import polyglot.types.PrimitiveType;
import polyglot.types.PrimitiveType_c;
import polyglot.types.Resolver;
import polyglot.types.TypeSystem;
import polyglot.types.Type_c;

public class ModeVTType_c extends CSPrimitiveType {
	
	public ModeVTType_c(TypeSystem ts){
		super(ts, PrimitiveType.INT);
	}
	
	public boolean isCanonical() { return true; }

	@Override
	public String toString() {
		return "int";
	}

	@Override
	public String translate(Resolver c) {
		return this.toString();
	}
}
