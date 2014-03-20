package et.ast;

import java.util.LinkedList;
import java.util.List;

import polyglot.ast.Import;
import polyglot.ast.Import_c;
import polyglot.ast.PackageNode;
import polyglot.ast.SourceFile_c;
import polyglot.ast.TopLevelDecl;
import polyglot.util.ListUtil;
import polyglot.util.Position;

/*
 * This extension is to add extra import statement to the class file
 */
public class ECMSourceFile_c extends SourceFile_c {

	public ECMSourceFile_c(Position pos, PackageNode package_,
			List<Import> imports, List<TopLevelDecl> decls) {
		super(pos, package_, imports, decls);

		// create extra import and add
		List<Import> list = new LinkedList<Import>(imports);
		// import java.util.Map
		list.add(new Import_c(pos.startOf(), Import.CLASS, "java.util.Map"));

		// import java.util.HashMap
		list.add(new Import_c(pos.startOf(), Import.CLASS, "java.util.HashMap"));

		// import et.util.MyUtility;

		this.imports = ListUtil.copy(list, true);
	}
}
