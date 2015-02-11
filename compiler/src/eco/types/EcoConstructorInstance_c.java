package eco.types;

import java.util.ArrayList;
import java.util.List;

import polyglot.ext.jl5.types.JL5ConstructorInstance_c;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.types.ClassType;
import polyglot.types.Flags;
import polyglot.types.Type;
import polyglot.util.Position;

public class EcoConstructorInstance_c extends JL5ConstructorInstance_c implements EcoConstructorInstance {
  
  public EcoConstructorInstance_c(EcoTypeSystem_c ts, 
                                  Position pos, 
                                  ClassType container, 
                                  Flags flags, 
                                  List<? extends Type> argTypes, 
                                  List<? extends Type> excTypes, 
                                  List<TypeVariable> typeParams) {
    super(ts, pos, container, flags, argTypes, excTypes, typeParams);
  }
  
  @Override
  public boolean callValidImpl(List<? extends Type> argTypes) {
    List<Type> decayedArgTypes = new ArrayList<Type>();
      
    for (Type t : argTypes) {
      if (t instanceof PatternType) {
        PatternType pt = (PatternType) t;
        decayedArgTypes.add(pt.base());
      } else {
        decayedArgTypes.add(t);
      }
    }
      
    // Decay types from mcases down to base
    return super.callValidImpl(decayedArgTypes);
  }

}
