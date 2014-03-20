package et.types;

import cs.linearsolver.ValueHolder;
import et.linersolve.ETValueHolder;
import polyglot.ext.jl5.types.JL5TypeSystem;
import polyglot.types.*;

public interface etTypeSystem extends JL5TypeSystem {
	public Type modeVT();

	public ETValueHolder getValueHolder();
}
