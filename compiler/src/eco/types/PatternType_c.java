package eco.types;

import polyglot.ext.jl5.types.JL5PrimitiveType_c;
import polyglot.types.PrimitiveType;
import polyglot.types.Type;
import polyglot.types.Resolver;
import polyglot.types.TypeSystem;

public class PatternType_c extends JL5PrimitiveType_c implements PatternType {
	private Type base = null;

	public PatternType_c(Type base, TypeSystem ts) {
		super(ts, PrimitiveType.VOID);
		base(base);
	}
	
	public void base(Type base) {
		this.base = base;
	}

	public Type base() {
		return base;
	}

	@Override
	public boolean isCanonical() {
	  return base.isCanonical();
	}

	@Override
	public String toString() {
		return "mcase<" + base + ">";
	}
  
  @Override
  public String translate(Resolver c) {
    return base + "[]";
  }

}
