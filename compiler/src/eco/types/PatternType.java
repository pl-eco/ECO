package eco.types;

import polyglot.ext.jl5.types.JL5PrimitiveType;
import polyglot.types.Type;

public interface PatternType extends JL5PrimitiveType {
	
	void base(Type base);

	Type base();

}
