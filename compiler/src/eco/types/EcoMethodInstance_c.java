package eco.types;

import polyglot.ext.jl5.types.JL5MethodInstance_c;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.types.Flags;
import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.util.Position;

import java.util.List;
import java.util.ArrayList;

public class EcoMethodInstance_c extends JL5MethodInstance_c implements EcoMethodInstance {

    public EcoMethodInstance_c(EcoTypeSystem_c ts, 
                               Position pos,
                               ReferenceType container, 
                               Flags flags, 
                               Type returnType, 
                               String name,
                               List<? extends Type> argTypes, 
                               List<? extends Type> excTypes,
                               List<TypeVariable> typeParams) {
        super(ts, 
              pos, 
              container, 
              flags, 
              returnType, 
              name, 
              argTypes, 
              excTypes, 
              typeParams);
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
