package eco.types;

import polyglot.types.LocalInstance;
import polyglot.ext.jl5.types.JL5LocalInstance;

public interface EcoLocalInstance extends JL5LocalInstance {

  boolean calibrate();

  void calibrate(boolean calibrate);

}
