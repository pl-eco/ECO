package et.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.JL5ClassDecl_c;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.ParsedClassType;
import polyglot.types.SemanticException;
import polyglot.util.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import cs.util.EasyDebugger;
import polyglot.visit.*;
import et.ast.mswitch.ECMContext_c;
import et.linersolve.ETValueHolder;
import et.types.*;
import et.util.Names;

/**
 * NodeFactory for et extension.
 */

public class ECMClassDecl_c extends JL5ClassDecl_c {
	private EnergyFlags eFlags = null;

	/*
	 * this field must be set in buildTypes method it will be either the default
	 * name or the name given from the source code
	 */
	private String modeFieldName = null;

	private static Map<String, Boolean> mptnClassMap = new HashMap<String, Boolean>();

	/*
	 * format: key: className value: mode field name in that class
	 * 
	 * if the value cannot be found, the mode name will be default and it means
	 * it was not specified from the souce code
	 */
	private static Map<String, String> classNameToModeNameMap = new HashMap<String, String>();

	public ECMClassDecl_c(Position pos, Flags flags,
			List<AnnotationElem> annotations, Id name, TypeNode superType,
			List<TypeNode> interfaces, ClassBody body,
			List<ParamTypeNode> paramTypes, EnergyFlags eFlags) {
		super(pos, flags, annotations, name, superType, interfaces, body,
				paramTypes);
		assert eFlags != null;
		this.eFlags = eFlags;
	}

	public static String getModeFieldNameOfClass(String fullClassName) {
		if (classNameToModeNameMap.containsKey(fullClassName))
			return classNameToModeNameMap.get(fullClassName);
		// return the default name if it was not specified from the source code
		return Names.MODE_FIELD;
	}

	public static void addMPatternClassToClass(String extendsFromClassName) {
		mptnClassMap.put(extendsFromClassName, true);
	}

	public EnergyFlags getEnergyFlags() {
		return eFlags;
	}

	@Override
	public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
		Node ret = super.disambiguate(ar);

		ETValueHolder vh = ((etTypeSystem_c) type().typeSystem())
				.getValueHolder();
		
		vh.bindTypeValueToClass(type().fullName(), eFlags.getFlags());
		return ret;
	}

	@Override
	public Node copy(NodeFactory nf) {
		ECMClassDecl_c eClassDecl = (ECMClassDecl_c) super.copy(nf);
		eClassDecl.eFlags = this.eFlags;
		eClassDecl.modeFieldName = this.modeFieldName;
		return eClassDecl;
	}

	public Context enterChildScope(Node child, Context c) {
		ECMContext_c ecmContext = (ECMContext_c) super
				.enterChildScope(child, c);
		ecmContext.setValues(eFlags);

		return ecmContext;
	}

	@Override
	public Node buildTypes(TypeBuilder tb) throws SemanticException {
		ECMClassDecl_c n = (ECMClassDecl_c) super.buildTypes(tb);

		this.modeFieldName = eFlags.getMode();
		if (modeFieldName == null || ModesDecl.hasMode(modeFieldName)
				|| modeFieldName.equals(EnergyFlags.ANONYMOUS)) {
			modeFieldName = Names.MODE_FIELD;
		} else {
			classNameToModeNameMap.put(n.type().fullName(), modeFieldName);
		}

		etTypeSystem_c ts = (etTypeSystem_c) tb.typeSystem();

		// the mode is a variable, add a fieldInstance to the class
		FieldInstance fi = tb.typeSystem().fieldInstance(this.position(),
				n.type(), Flags.PUBLIC, ts.modeVT(), modeFieldName);

		((ParsedClassType) n.type()).addField(fi);

		FieldDecl fd = tb.nodeFactory().FieldDecl(
				this.position(),
				Flags.PUBLIC,
				tb.nodeFactory()
						.CanonicalTypeNode(this.position(), ts.modeVT()),
				new Id_c(this.position(), modeFieldName));
		fd = fd.fieldInstance(fi);

		n = (ECMClassDecl_c) n.body(body.addMember(fd));

		n.modeFieldName = this.modeFieldName;

		return n;
	}

	// add by Kenan
	static ConcurrentHashMap<String, HashSet<Object>> modeStaticFamilies = new ConcurrentHashMap<String, HashSet<Object>>();
	static ConcurrentHashMap<String, HashSet<Object>> modeDynamicFamilies = new ConcurrentHashMap<String, HashSet<Object>>();
	static ConcurrentHashMap<String, HashSet<Object>> phaseStaticFamilies = new ConcurrentHashMap<String, HashSet<Object>>();
	static ConcurrentHashMap<String, HashSet<Object>> phaseDynamicFamilies = new ConcurrentHashMap<String, HashSet<Object>>();

	static HashSet<String> modeObjects = new HashSet<String>();
	static HashSet<String> phaseObjects = new HashSet<String>();
	public static HashSet<String> modes = new HashSet<String>();
	static HashSet<Id> node = new HashSet<Id>();
	static boolean isFamilyInitialized = false;
	static boolean isObjectInitialized = false;
	static boolean isRightBracket = false;

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		prettyPrintHeader(w, tr);
		print(body(), w, tr);

		/*
		 * print the extra methods after the whole class body is printed
		 * 
		 * public %class_name% setMode(int mode){ this.%MODE_FIELD%; return
		 * this; }
		 * 
		 * public int getMode() { return this.%MODE_FIELD%; }
		 */
		w.newline(4);
		w.write("public " + this.name() + " " + Names.SET_MODE_METHOD_NAME
				+ "(int mode){  this." + modeFieldName
				+ " = mode; return this; }");

		w.newline(4);
		w.write("public int " + Names.GET_MODE_METHOD_NAME
				+ "() { return this." + modeFieldName + "; }");

		w.newline();

		prettyPrintFooter(w, tr);

		/*
		 * after the class is printed, print the extra classes for mpatters.
		 * They should be within the same class file with the current class
		 * decl.
		 */
		if (!mptnClassMap.containsKey(type().fullName())) {
			return;
		}

		// platform independent new line symbol
		String newLine = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		String mptnName = "$" + name().toUpperCase()
				+ Names.MPTN_CLASS_DECL_POSTFIX;

		sb.setLength(0);
		// class decl
		sb.append("class ").append(mptnName).append(" extends ")
				.append(type().fullName()).append("{").append(newLine);

		// field decl
		sb.append("  ").append("private Map<Integer, ").append(name())
				.append("> map = new HashMap<Integer, ").append(name())
				.append(">();");

		// constructor decl
		sb.append(newLine);
		sb.append(newLine);
		sb.append("  ").append("public ").append(mptnName).append("(")
				.append(name()).append("... o)").append("{").append(newLine);

		// for loop for assignment
		sb.append("    ").append("for (").append(name())
				.append(" element : o)").append("{").append(newLine);

		sb.append("      ").append("map.put(element.")
				.append(Names.GET_MODE_METHOD_NAME).append("(), element);");
		sb.append("    ").append("}").append(newLine);
		sb.append("    ").append("}");

		// print getObject method
		sb.append(newLine);
		sb.append("  ");
		printGetObjectModeMethod(sb);
		// print setObject method
		sb.append(newLine);
		sb.append("  ");
		// printSetModeMethod(sb);
		// sb.append(newLine);
		sb.append(newLine).append("}");
		w.write(sb.toString());
	}

	private void printGetObjectModeMethod(StringBuilder sb) {
		sb.append("public ").append(name()).append(" ")
				.append(Names.MPTN_GET_OBJECT_METHOD_NAME)
				.append("(int mode) { return map.get(mode); }");
	}

	private void printSetModeMethod(StringBuilder sb) {
		sb.append("public void ").append(Names.SET_MODE_METHOD_NAME)
				.append("(int mode) {").append("  ").append(modeFieldName)
				.append(" = mode; }");
	}
}