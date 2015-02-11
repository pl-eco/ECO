package eco.ast;

import polyglot.ast.Stmt;

import java.util.Set;

public interface Sustainable extends Stmt {

	void addFieldTrigger(String string);

	Set<String> getFieldTriggers();

}
