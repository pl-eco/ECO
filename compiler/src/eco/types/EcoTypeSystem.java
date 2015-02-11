package eco.types;

import polyglot.types.*;

import polyglot.ext.jl7.types.JL7TypeSystem;

public interface EcoTypeSystem extends JL7TypeSystem {

	PatternType createPatternType(Type type);

}
