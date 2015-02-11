package eco.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.ext.jl5.ast.JL5ClassDeclExt;

import polyglot.ext.jl7.ast.JL7AbstractExtFactory_c;

import java.util.List;
import java.util.ArrayList;

public abstract class EcoAbstractExtFactory_c extends JL7AbstractExtFactory_c
        implements EcoExtFactory {

    public EcoAbstractExtFactory_c() {
        super();
    }

    public EcoAbstractExtFactory_c(ExtFactory nextExtFactory) {
        super(nextExtFactory);
    }

    @Override
    public final Ext extModesDecl() {
      Ext e = extModesDeclImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extModesDecl();
        } else {
          e2 = nextExtFactory().extClassDecl();
        }
        e = composeExts(e, e2);
      }
      return postExtModesDecl(e);
    }

    @Override
    public final Ext extMPattern() {
      Ext e = extMPatternImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extMPattern();
        } else {
          e2 = nextExtFactory().extTypeNode();
        }
        e = composeExts(e, e2);
      }
      return postExtMPattern(e);
    }

    @Override
    public final Ext extMPatternInit() {
      Ext e = extMPatternInitImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extMPatternInit();
        } else {
          e2 = nextExtFactory().extExpr();
        }
        e = composeExts(e, e2);
      }
      return postExtMPatternInit(e);
    }

    @Override
    public final Ext extMPatternElement() {
      Ext e = extMPatternElementImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extMPatternElement();
        } else {
          e2 = nextExtFactory().extTerm();
        }
        e = composeExts(e, e2);
      }
      return postExtMPatternElement(e);
    }

    @Override
    public final Ext extSustainable() {
      Ext e = extSustainableImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extSustainable();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2);
      }
      return postExtSustainable(e);
    }

    @Override
    public final Ext extSupply() {
      Ext e = extSupplyImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extSupply();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2);
      }
      return postExtSupply(e);
    } 

    @Override
    public final Ext extBSupply() {
      Ext e = extBSupplyImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extBSupply();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2);
      }
      return postExtBSupply(e);
    } 

    @Override
    public final Ext extTSupply() {
      Ext e = extTSupplyImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extTSupply();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2); 
      }
      return postExtTSupply(e);
    } 

    @Override
    public final Ext extSupplyExpr() {
      Ext e = extSupplyExprImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extSupplyExpr();
        } else {
          e2 = nextExtFactory().extExpr();
        }
        e = composeExts(e, e2); 
      }
      return postExtSupplyExpr(e);
    } 

    @Override
    public final Ext extBatterySupplyExpr() {
      Ext e = extBatterySupplyExprImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extBatterySupplyExpr();
        } else {
          e2 = nextExtFactory().extExpr();
        }
        e = composeExts(e, e2); 
      }
      return postExtBatterySupplyExpr(e);
    } 

    @Override
    public final Ext extTemperatureSupplyExpr() {
      Ext e = extTemperatureSupplyExprImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extTemperatureSupplyExpr();
        } else {
          e2 = nextExtFactory().extExpr();
        }
        e = composeExts(e, e2); 
      }
      return postExtTemperatureSupplyExpr(e);
    } 

    @Override
    public final Ext extDemand() {
      Ext e = extDemandImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extDemand();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2); 
      }
      return postExtDemand(e);
    } 

    @Override
    public final Ext extSelect() {
      Ext e = extSelectImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extSelect();
        } else {
          e2 = nextExtFactory().extExpr();
        }
        e = composeExts(e, e2); 
      }
      return postExtSelect(e);
    } 

    @Override
    public final Ext extUniformStmt() {
      Ext e = extUniformStmtImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof EcoExtFactory) {
          e2 = ((EcoExtFactory) nextExtFactory()).extUniformStmt();
        } else {
          e2 = nextExtFactory().extStmt();
        }
        e = composeExts(e, e2); 
      }
      return postExtUniformStmt(e);
    } 

    protected Ext extModesDeclImpl() { 
      return extClassDeclImpl();
    }

    protected Ext extMPatternImpl() {
      return extTypeNodeImpl();
    }

    protected Ext extMPatternInitImpl() {
      return extExprImpl();
    }

    protected Ext extMPatternElementImpl() {
      return extTermImpl();
    }

    protected Ext extSustainableImpl() {
      return extStmtImpl();
    }

    protected Ext extSupplyImpl() {
      return extStmtImpl();
    }

    protected Ext extBSupplyImpl() {
      return extStmtImpl();
    }

    protected Ext extTSupplyImpl() {
      return extStmtImpl();
    }

    protected Ext extSupplyExprImpl() {
      return extExprImpl();
    }

    protected Ext extBatterySupplyExprImpl() {
      return extExprImpl();
    }

    protected Ext extTemperatureSupplyExprImpl() {
      return extExprImpl();
    }

    protected Ext extDemandImpl() {
      return extStmtImpl();
    }

    protected Ext extSelectImpl() {
      return extExprImpl();
    }

    protected Ext extUniformStmtImpl() {
      return extStmtImpl();
    }

    protected Ext postExtModesDecl(Ext e) { 
      return postExtClassDecl(e);
    }

    protected Ext postExtMPattern(Ext e) {
      return postExtTypeNode(e);
    }

    protected Ext postExtMPatternInit(Ext e) {
      return postExtExpr(e);
    }

    protected Ext postExtMPatternElement(Ext e) {
      return postExtTerm(e);
    }

    protected Ext postExtSustainable(Ext e) {
      return postExtStmt(e);
    }

    protected Ext postExtSupply(Ext e) {
      return postExtStmt(e);
    }

    protected Ext postExtBSupply(Ext e) {
      return postExtStmt(e);
    }

    protected Ext postExtTSupply(Ext e) {
      return postExtStmt(e);
    } 

    protected Ext postExtSupplyExpr(Ext e) {
      return postExtExpr(e);
    } 

    protected Ext postExtBatterySupplyExpr(Ext e) {
      return postExtExpr(e);
    } 

    protected Ext postExtTemperatureSupplyExpr(Ext e) {
      return postExtExpr(e);
    } 

    protected Ext postExtDemand(Ext e) {
      return postExtStmt(e);
    } 

    protected Ext postExtSelect(Ext e) {
      return postExtExpr(e);
    } 

    protected Ext postExtUniformStmt(Ext e) {
      return postExtStmt(e);
    } 

}
