package eco.ast;

import polyglot.ast.Id;
import polyglot.ast.Local;
import polyglot.ast.Node;
import polyglot.ast.LocalDecl;
import polyglot.types.LocalInstance;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoLocalExt extends EcoExt {
	private boolean calibrate = false;
	
  public boolean calibrate() {
    return this.calibrate;
  }
  
  public void calibrate(boolean calibrate) {
    this.calibrate = calibrate;
  }

  // TODO: Need to figure out the right way to have outside
  // touch the boxed node.
  public LocalInstance localInstance() {
    Local local = (Local) this.node();
    return local.localInstance();
  }

  public String name() {
    Local local = (Local) this.node();
    return local.name();
  }

  // TODO: Let's remove this and use the direct setters instead?
	public void markCalibrate() {
		this.calibrate = true;
	}
	
	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		super.prettyPrint(w, tr);
		if (calibrate) {
			w.write("[0]");
		}
	}
}
