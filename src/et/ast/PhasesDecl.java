package et.ast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import et.types.etTypeSystem_c;
import et.util.MyUtility;
import et.util.Names;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl_c;
import polyglot.ast.Id;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;

public class PhasesDecl extends ClassDecl_c {
	private static Set<String> phases = new TreeSet<String>();

	// from min to max
	private static List<String> phasesOrder = new LinkedList<String>();

	public static int minScale;

	public static int maxScale;

	// key: phase value: frequency
	public static Map<Integer, Integer> phaseMap = new HashMap<Integer, Integer>();

	private static boolean initPhaseMapping = false;

	static {
		System.out.println("Loading ECM File...");
		Properties prop = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream("ecm.prop");
			prop.load(is);

			minScale = Integer.parseInt(prop.getProperty("minScale").trim());
			maxScale = Integer.parseInt(prop.getProperty("maxScale").trim());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean hasPhase(String phaseName) {
		return phases.contains(phaseName);
	}

	public static int getScale(int phaseValue) {
		if (!initPhaseMapping) {
			initPhaseMapping = true;
			SortedSet<Integer> values = new TreeSet<Integer>();
			// FIX the for loop should be rewrite
			// collect all phases values
			// for (Iterator iter = phases.iterator(); iter.hasNext();) {
			// String phase = (String) iter.next();
			// int value =
			// ((Double)LinearSolverMapper.getResultFromRefreshedName(phase.substring(0,
			// phase.indexOf('#')),
			// LinearSolverMapper.ValueType.PHASE)).intValue();
			// values.add(value);
			// }

			int scaleUnit = (maxScale - minScale) / (values.size() - 1);
			// map values to frequencies
			int count = 0;
			for (Integer value : values) {
				phaseMap.put(value, minScale + scaleUnit * count++);
			}
		}
		return phaseMap.get(phaseValue);
	}

	public PhasesDecl(Position pos, Flags flags, Id name, TypeNode superClass,
			List interfaces, ClassBody body) {
		super(pos, flags, name, superClass, interfaces, body);
	}

	public void setList(List<String> phases) {
		for (String stat : phases) {
			if (stat.contains("<cpu")) {
				String[] two = stat.split("<cpu");
				this.setPhase(two[0], two[1]);
			} else {
				this.setPhase(stat);
			}
		}
	}

	private void setPhase(String phase) {
		phases.add(phase);
	}

	// lPhase < gPhase
	private void setPhase(String lPhase, String gPhase) {
		phases.add(lPhase);
		phases.add(gPhase);

		if (phasesOrder.size() == 0) {
			phasesOrder.add(lPhase);
			phasesOrder.add(gPhase);
		} else {
			for (int i = 0; i < phasesOrder.size(); i++) {
				String phase = (String) phasesOrder.get(i);
				if (phase.endsWith(lPhase)) {
					phasesOrder.add(i + 1, gPhase);
					break;
				}
				if (phase.endsWith(gPhase)) {
					phasesOrder.add(i, lPhase);
					break;
				}
			}
		}
	}

	public String toString() {
		return "{ phase_body }";
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write("class " + Names.PHASE_DECL_CLASS_NAME + "{  \n");

		int counter = 0;
		for (Iterator iter = phases.iterator(); iter.hasNext();) {
			String phase = (String) iter.next();
			w.write("		public static final int " + phase + " = " + counter++
					+ "; \n");
		}
		w.write("} \n");

		// System.out.println("Phases Order: ");
		for (String element : phasesOrder) {
			// System.out.println(element + "   ");
		}
		MyUtility.phaseSize = counter++;
	}

	public static Set<String> getPhases() {
		return phases;
	}

	public static List<String> getPhasesOrder() {
		return phasesOrder;
	}
}
