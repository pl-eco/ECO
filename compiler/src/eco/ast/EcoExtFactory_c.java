package eco.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public final class EcoExtFactory_c extends EcoAbstractExtFactory_c {

  public EcoExtFactory_c() {
    super();
  }

  public EcoExtFactory_c(ExtFactory nextExtFactory) {
    super(nextExtFactory);
  }

  @Override
  protected Ext extNodeImpl() {
    return new EcoExt();
  }

  @Override
  protected Ext extCallImpl() {
    return new EcoCallExt();
  }

  @Override
  protected Ext extBinaryImpl() {
    return new EcoBinaryExt();
  }

  @Override
  protected Ext extFieldDeclImpl() {
    return new EcoFieldDeclExt();
  }

  @Override
  protected Ext extLocalDeclImpl() {
    return new EcoLocalDeclExt();
  }

  @Override
  protected Ext extReturnImpl() {
    return new EcoReturnExt();
  }

  @Override
  protected Ext extLocalImpl() {
    return new EcoLocalExt();
  }
  
  @Override
  protected Ext extLocalAssignImpl() {
    return new EcoLocalAssignExt();
  }
  

}
