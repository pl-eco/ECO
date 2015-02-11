package eco.ast;

import eco.util.Names;

import polyglot.ast.Node;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl_c;
import polyglot.ast.Id;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ModesDecl_c extends ClassDecl_c implements ModesDecl {

	private static Set<String> modes = new TreeSet<String>();

	private static List<String> modesOrder = new LinkedList<String>();

	public ModesDecl_c(Position pos, 
                     Flags flags, 
                     Id name, 
                     TypeNode superClass,
			               List interfaces, 
                     ClassBody body) {
		super(pos, flags, name, superClass, interfaces, body);
	}

  // TODO: Static methods may never be used in code
	public static boolean hasMode(String modeName) {
		return modeName == null ? false : modes.contains(modeName);
	}

  // TODO: Static methods may never be used in code
	public static Set<String> getModes() {
		return modes;
	}

  // TODO: Static methods may never be used in code
	public static List<String> getModesOrder() {
		return modesOrder;
	}

  @Override
	public void setList(List<String> modes) {
		for (String stat : modes) {
			if (stat.contains("<:")) {
				String[] two = stat.split("<:");
				this.setMode(two[0], two[1]);
			} else {
				this.setMode(stat);
			}
		}

	}

	private void setMode(String mode) {
		modes.add(mode);
	}

	private void setMode(String lMode, String hMode) {
		modes.add(lMode);
		modes.add(hMode);

		if (modesOrder.size() == 0) {
			modesOrder.add(lMode);
			modesOrder.add(hMode);
		} else {
			for (int i = 0; i < modesOrder.size(); i++) {
				String mode = (String) modesOrder.get(i);
				if (mode.endsWith(lMode)) {
					modesOrder.add(i + 1, hMode);
					break;
				}
				if (mode.endsWith(hMode)) {
					modesOrder.add(i, lMode);
					break;
				}
			}
		}
	}

  @Override
	public String toString() {
		return "{ mode_body }";
	}

  @Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.begin(4);
		w.write("class " + Names.MODE_DECL_CLASS_NAME + "{"); w.newline();

		int counter = modes.size();
		w.write("public static final int $MAX = " + --counter + ";");
		for (Iterator iter = modes.iterator(); iter.hasNext();) {
			 w.newline();
			String mode = (String) iter.next();
			w.write("public static final int " + mode + " = " + counter--
					+ ";");
		}
		w.end(); w.newline();
		w.write("}"); w.newline();
  }

  @Override
  public Node typeCheckOverride(Node parent, TypeChecker tc) throws SemanticException {
    return this;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    return this;
  }

}
