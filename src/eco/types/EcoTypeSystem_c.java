package eco.types;

import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.types.*;
import polyglot.util.Position;
import cs.graph.CSGraph;
import cs.graph.CSSimpleGraph;
import cs.linearsolver.CPlexWrapper;
import cs.types.CSContext_c;
import cs.types.CSNonGenericType;
import cs.types.CSNonGenericWrapper;
import cs.types.csTypeSystem_c;
import eco.linearsolver.EcoValueHolder;

public class EcoTypeSystem_c extends csTypeSystem_c {

	@Override
	public LocalInstance localInstance(Position pos, Flags flags, Type type,
			String name) {
		assert_(type);
		return new EcoLocalInstance_c(this, pos, flags, type, name);
	}
	
	@Override
	public Context createContext() {
		return new CSContext_c(this);
	}

	private static CSGraph graph = null;

	@Override
	public CSGraph getGraph() {
		if (graph == null)
			graph = new CSSimpleGraph(this);
		return graph;
	}

	@Override
	public CSNonGenericType createDefaultObjectType(java.lang.String aliasName,
			Type type) {
		return CSNonGenericWrapper.createNonGenericType(aliasName,
				(JL5ParsedClassType_c) type);
	}

	@Override
	public CSNonGenericType createDefaultObjectType(Type type) {
		return CSNonGenericWrapper.createNonGenericType(type);
	}

	public CSNonGenericType createDefaultObjectType(Type type, String typeLabel) {
		return CSNonGenericWrapper.createNonGenericType(type, typeLabel);
	}
	
	public PatternType createPatternType(Type type) {
		return new PatternType(type, this);
	}
	
	@Override
	public boolean isSubtype(Type type1, Type type2) {
		if (type1 instanceof PatternType && type2 instanceof PatternType) {
			return isSubtype(((PatternType) type1).base(),
					((PatternType) type2).base());
		}
		return super.isSubtype(type1, type2);
	}
	

	@Override
	public boolean isImplicitCastValid(Type fromType, Type toType) {
		if (fromType instanceof PatternType && toType instanceof PatternType) {
			return isImplicitCastValid(((PatternType) fromType).base(),
					((PatternType) toType).base());
		}
		return super.isImplicitCastValid(fromType, toType);
	}

	@Override
	public CPlexWrapper createCPlexSolver() {
		return null;
	}

	private static EcoValueHolder valueHolder = new EcoValueHolder();

	@Override
	public EcoValueHolder getValueHolder() {
		return valueHolder;
	}
}
