package et.parse;

import polyglot.lex.StringLiteral;
import polyglot.util.Position;

public class ModeLiteral extends StringLiteral{

	public ModeLiteral(Position position, String s, int sym) {
		super(position, s, sym);
	}

}
