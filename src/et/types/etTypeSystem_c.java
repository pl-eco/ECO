package et.types;

import et.ast.mswitch.ECMContext_c;
import et.linersolve.ETCplexSolver;
import et.linersolve.ETValueHolder;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.types.*;
import polyglot.util.Position;
import cs.graph.CSGraph;
import cs.graph.CSSimpleGraph;
import cs.linearsolver.CPlexWrapper;
import cs.types.CSNonGenericType;
import cs.types.CSNonGenericWrapper;
import cs.types.csTypeSystem_c;

public class etTypeSystem_c extends csTypeSystem_c implements etTypeSystem {
	/*
	 * allow "noscale" as a method modifier
	 */
	@Override
	public Flags legalMethodFlags() {
		return super.legalMethodFlags().set(ETFlags.NOSCALE);
	}

	@Override
	public LocalInstance localInstance(Position pos, Flags flags, Type type,
			String name) {
		assert_(type);
		return new EcoLocalInstance_c(this, pos, flags, type, name);
	}
	
	@Override
	public Context createContext() {
		return new ECMContext_c(this);
	}

	private Type MODE_;

	public Type modeVT() {
		if (MODE_ != null)
			return MODE_;
		return MODE_ = new ModeVTType_c(this);
	}

	@Override
	public CPlexWrapper createCPlexSolver() {
		return new ETCplexSolver(getValueHolder());
	}

	private static ETValueHolder valueHolder = new ETValueHolder();

	@Override
	public ETValueHolder getValueHolder() {
		return valueHolder;
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
}
