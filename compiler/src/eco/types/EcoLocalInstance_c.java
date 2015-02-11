package eco.types;

import polyglot.types.Flags;
import polyglot.types.LocalInstance_c;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.Position;

import polyglot.ext.jl5.types.JL5LocalInstance_c;

public class EcoLocalInstance_c extends JL5LocalInstance_c implements EcoLocalInstance {
	public boolean calibrate = false;
	
	public EcoLocalInstance_c(TypeSystem ts, 
                            Position pos, 
                            Flags flags, 
                            Type type, 
                            String name) {
    super(ts, pos, flags, type, name);
  }

  public boolean calibrate() {
    return calibrate;
  }

  public void calibrate(boolean calibrate) {
    this.calibrate = calibrate;
  }

}
