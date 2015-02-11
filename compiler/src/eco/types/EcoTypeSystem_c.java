package eco.types;

import polyglot.types.*;
import polyglot.util.Position;

import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.jl7.types.JL7TypeSystem_c;

import java.util.Collections;
import java.util.List;

public class EcoTypeSystem_c extends JL7TypeSystem_c implements EcoTypeSystem {

	public PatternType createPatternType(Type type) {
		return new PatternType_c(type, this);
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
	public LocalInstance localInstance(Position pos, 
                                     Flags flags, 
                                     Type type,
			                               String name) {
		assert_(type);
		return new EcoLocalInstance_c(this, pos, flags, type, name);
	}

  @Override
  public MethodInstance methodInstance(Position pos, 
                                       ReferenceType container,
                                       Flags flags, 
                                       Type returnType, 
                                       String name,
                                       List<? extends Type> argTypes, 
                                       List<? extends Type> excTypes) {
    return methodInstance(pos, 
                          container, 
                          flags, 
                          returnType, 
                          name,
                          argTypes, 
                          excTypes, 
                          Collections.<TypeVariable> emptyList());
  }

  @Override
  public EcoMethodInstance methodInstance(Position pos,
                                          ReferenceType container, 
                                          Flags flags, 
                                          Type returnType, 
                                          String name,
                                          List<? extends Type> argTypes, 
                                          List<? extends Type> excTypes,
                                          List<TypeVariable> typeParams) {
    assert_(container);
    assert_(returnType);
    assert_(argTypes);
    assert_(excTypes);
    assert_(typeParams);
    return new EcoMethodInstance_c(this, 
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
	public EcoConstructorInstance 
  constructorInstance(Position pos, 
                      ClassType container, 
                      Flags flags, 
                      List<? extends Type> argTypes,
                      List<? extends Type> excTypes, 
                      List<TypeVariable> typeParams) {
		assert_(container);
		assert_(argTypes);
		assert_(excTypes);
		assert_(typeParams);
		return new EcoConstructorInstance_c(this, 
                                        pos, 
                                        container, 
                                        flags,
				                                argTypes, 
                                        excTypes, 
                                        typeParams);
	}




}
