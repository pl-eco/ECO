package eco.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.SerialVersionUID;

public class EcoExt extends Ext_c {
    private static final long serialVersionUID = SerialVersionUID.generate();

    public static EcoExt ext(Node n) {
        Ext e = n.ext();
        while (e != null && !(e instanceof EcoExt)) {
            e = e.ext();
        }
        if (e == null) {
            throw new InternalCompilerError("No Eco extension object for node "
                    + n + " (" + n.getClass() + ")", n.position());
        }
        return (EcoExt) e;
    }

    @Override
    public final EcoLang lang() {
        return EcoLang_c.instance;
    }

    // TODO:  Override operation methods for overridden AST operations.
}
