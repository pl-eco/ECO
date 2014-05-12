package et.types;

import polyglot.types.Flags;
import polyglot.types.LocalInstance_c;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;

public class EcoLocalInstance_c extends LocalInstance_c {
	public boolean calibrate = false;
	
	public EcoLocalInstance_c(TypeSystem ts, Position pos, Flags flags, Type type,
            String name) {
        super(ts, pos, flags, type, name);
    }
	
	public void markCalibrate() {
		calibrate = true;
	}
}
