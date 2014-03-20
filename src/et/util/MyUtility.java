// import java.util.*;
package et.util;



import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.reflect.Field;




import et.ast.ECMClassDecl_c;




public class MyUtility {
	static int[] availableFreqs = null;//freqAvailable();
	public static int phaseSize = 0;

//	static AnalyzerThread pt = new AnalyzerThread();
	// java.util.HashMap MlabelsMapping = new java.util.HashMap(); // mapping
	// @mode(?) objects to mode labels
	// java.util.HashMap PlabelsMapping = new java.util.HashMap (); // mapping
	// @phase(?) objects to phase labels

	//kenan
	/*************For methods-level characterization**************/
//	public static ConcurrentHashMap<Object, HashSet<Double>> modeDynamicObjectMapping = new ConcurrentHashMap<Object, HashSet<Double>>(); 
																									
//	public static ConcurrentHashMap<Object, HashSet<Double>> modeStaticObjectMapping = new ConcurrentHashMap<Object, HashSet<Double>>();																								// objects
																									
//	public static ConcurrentHashMap<Object, HashSet<Double>> phaseStaticObjectMapping = new ConcurrentHashMap<Object, HashSet<Double>>(); 
																									
//	public static ConcurrentHashMap<Object, HashSet<Double>> phaseDynamicObjectMapping = new ConcurrentHashMap<Object, HashSet<Double>>();
	
	/*********************For Object-level characterization**********************************/
	public static ConcurrentHashMap<Object, Double> modeDynamicObjectMapping = new ConcurrentHashMap<Object, Double>(); 
	
	public static ConcurrentHashMap<Object, Double> modeStaticObjectMapping = new ConcurrentHashMap<Object, Double>();																								// objects
																									
	public static ConcurrentHashMap<Object, Double> phaseStaticObjectMapping = new ConcurrentHashMap<Object, Double>(); 
																									
	public static ConcurrentHashMap<Object, Double> phaseDynamicObjectMapping = new ConcurrentHashMap<Object, Double>();
	
	//For gradual mapping tables
	public static ConcurrentHashMap<Object, String> modeDynamicScope = new ConcurrentHashMap<Object, String>(); 
	public static ConcurrentHashMap<Object, String> phaseDynamicScope = new ConcurrentHashMap<Object, String>(); 
	 
	
	public static ConcurrentHashMap<Object, Double> modeObjectMapping = new ConcurrentHashMap<Object, Double>();
	public static ConcurrentHashMap<Object, Double> phaseObjectMapping = new ConcurrentHashMap<Object, Double>();

	/*************For methods-level characterization**************/
//	public static ConcurrentHashMap<Object, HashSet<Double>> tempDynObjModes = new ConcurrentHashMap<Object, HashSet<Double>>();
//	public static ConcurrentHashMap<Object, HashSet<Double>> tempStaticObjModes = new ConcurrentHashMap<Object, HashSet<Double>>();
//	public static ConcurrentHashMap<Object, Double> tempDynObjPhases = new ConcurrentHashMap<Object, Double>();
//	public static ConcurrentHashMap<Object, HashSet<Double>> tempStaticObjPhases = new ConcurrentHashMap<Object, HashSet<Double>>();
	
	/*********************For Object-level characterization**********************************/
	public static ConcurrentHashMap<Object, Double> tempDynObjModes = new ConcurrentHashMap<Object, Double>();
	public static ConcurrentHashMap<Object, Double> tempStaticObjModes = new ConcurrentHashMap<Object, Double>();
//	public static ConcurrentHashMap<Object, Double> tempDynObjPhases = new ConcurrentHashMap<Object, Double>();
	public static ConcurrentHashMap<Object, Double> tempStaticObjPhases = new ConcurrentHashMap<Object, Double>();
	
	public static SortedMap<Object,Double> sorted_modeStaticObject = Collections.synchronizedSortedMap(new TreeMap<Object,Double>());
	public static SortedMap<Object,Double> sorted_modeDynObject = Collections.synchronizedSortedMap(new TreeMap<Object,Double>());
	public static SortedMap<Object,Double> sorted_modeObject = Collections.synchronizedSortedMap(new TreeMap<Object,Double>());

	public static ConcurrentHashMap<String, HashSet<Object>> modeDynamicFamilyMapping = new ConcurrentHashMap<String, HashSet<Object>>();
	public static ConcurrentHashMap<String, HashSet<Object>> modeStaticFamilyMapping = new java.util.concurrent.ConcurrentHashMap<String, HashSet<Object>>();
	public static ConcurrentHashMap<String, HashSet<Object>> phaseDynamicFamilyMapping = new java.util.concurrent.ConcurrentHashMap<String, HashSet<Object>>();
	public static ConcurrentHashMap<String, HashSet<Object>> phaseStaticFamilyMapping = new java.util.concurrent.ConcurrentHashMap<String, HashSet<Object>>();
	
	
	public static ConcurrentHashMap<Object, Integer> MlabelsMapping = new ConcurrentHashMap<Object, Integer>();
	
	public static ConcurrentHashMap<Object, Integer> PlabelsMapping = new ConcurrentHashMap<Object, Integer>(); // mapping
	
	
	static Double previousEnergyValue = 0.0;
	
	public static String upperLimit = "100000";
	public static String lowerLimit = "-1";
	
	public static boolean firstStaticObj = true;
	public static boolean lastDynObj = false;
	private static boolean upperlimitFound = false;
	private static boolean lowerlimitFound = false;
	

	// edit by Kenan
	static ExecutorService exec = Executors.newFixedThreadPool(3);

	public static Object checkMode(Object o, int staticLabel) {
/*		int runtimeEnergyLevel = pt.getLatestMode(o);
		if (runtimeEnergyLevel == 0) {
			throw new RuntimeException("?");
		}

		if (runtimeEnergyLevel > staticLabel) {
			throw new RuntimeException("?");
		}*/
		return o;
	}

	public static Object checkPhase(Object o, int staticLabel) {
/*		int runtimePhase = pt.getLatestPhase(o);
		if (runtimePhase == 0) { // null
			throw new RuntimeException("?");
		}
		if (runtimePhase != staticLabel) {
			throw new RuntimeException("?");
		}*/
		return o;
	}

	// end edit

	// add by Kenan

/*	public static Object initMFadd(String familyName, Object exp) {

//		modeObjectMapping.put(exp, new HashSet<Object>());

		modeFamilyMapping.get(familyName).add(exp);
		return exp;
	}

	public static Object initPFadd(String familyName, Object exp) {

//		phaseObjectMapping.put(exp, new HashSet<Object>());

		phaseFamilyMapping.get(familyName).add(exp);
		return exp;
	}
*/
	//kenan 8/18/2012
/*	public static Object MFadd(String familyName, Object exp) {
		modeFamilyMapping.get(familyName).add(exp);
		return exp;
	}*/
	
	public static Object modeStaticFamilyAdd(String familyName, Object exp) {
		modeStaticFamilyMapping.get(familyName).add(exp);
		return exp;
	}
	public static Object modeDynamicFamilyAdd(String familyName, Object exp) {
		modeDynamicFamilyMapping.get(familyName).add(exp);
		return exp;
	}

	public static Object phaseDynamicFamilyAdd(String familyName, Object exp) {
		phaseDynamicFamilyMapping.get(familyName).add(exp);
		return exp;
	}
	public static Object phaseStaticFamilyAdd(String familyName, Object exp) {
		phaseStaticFamilyMapping.get(familyName).add(exp);
		return exp;
	}
	
	public static Object staticModeAdd(Object obj, int modeNo) {
		MlabelsMapping.put(obj, modeNo);
		return obj;
	}
	
	public static Object staticPhaseAdd(Object obj, int phaseNo) {
		PlabelsMapping.put(obj, phaseNo);
		return obj;
	}

/*	public static Object PFadd(String familyName, Object exp) {
		phaseFamilyMapping.get(familyName).add(exp);
		return exp;
	}*/

	//end kenan 8/18/2012
	// edit by Kenan
	public static Object preamble(Object o, boolean isProfilingNeeded,
			boolean isScalingNeeded, int phaseNO) {
		if (isProfilingNeeded) {
			previousEnergyValue = EnergyStatcheck();
			System.out.println("previousEnergyValue: "+ previousEnergyValue);
		}
		if (isScalingNeeded) {
			scale(availableFreqs[phaseNO*(availableFreqs.length)/phaseSize]);
		}

		return o;
	}

	public static Object epilogueStaticObject(Object o, Object useless, boolean isProfilingNeeded,
			boolean isScalingNeeded, int phaseNO) {
//		System.out.println("epilogueStaticObject" + o);
		if (isProfilingNeeded) {
			double currentEnergyValue = EnergyStatcheck();
			System.out.println("currentEnergyValue in static: "+ currentEnergyValue);
			System.out.println("Static Object: " + o);
			Double Value = modeStaticObjectMapping.get(o);
			
			double powerConsumption = currentEnergyValue - previousEnergyValue;

			if (Value != null) {
				modeStaticObjectMapping.put(o, powerConsumption + Value);
				
			} else {
				modeStaticObjectMapping.put(o,powerConsumption);
				
			}
		}
		if (isScalingNeeded) {
			scale(availableFreqs[phaseNO*(availableFreqs.length)/phaseSize]);
		}

		return useless;
	}
	
/*	public static Object epilogueStaticScope(Object o, boolean isProfilingNeeded,
			boolean isScalingNeeded, int phaseNO) {
//		System.out.println("epilogueStaticObject" + o);
		if (isProfilingNeeded) {
			double currentEnergyValue = EnergyStatcheck();
			System.out.println("currentEnergyValue in static: "+ currentEnergyValue);
			Double Value = modeStaticObjectMapping.get(o);
			int label = MlabelsMapping.get(o);
			
			if(label == 0) {
				upperlimitFound = true;
			}
			

			if (Value != null) {
				modeStaticObjectMapping.put(o,
						((currentEnergyValue - previousEnergyValue) + Value));
				
			} else {
				modeStaticObjectMapping.put(o,
						(currentEnergyValue - previousEnergyValue));
			}
		}
		if (isScalingNeeded) {
			scale(availableFreqs[phaseNO*(availableFreqs.length)/phaseSize]);
		}

		return o;
	}
	
	public static Object epilogueDynamicObjectScope(Object o, boolean isProfilingNeeded,
			boolean isScalingNeeded, int phaseNO) {
		
		if (isProfilingNeeded) {
			double currentEnergyValue = EnergyStatcheck();
			String Value = modeDynamicScope.get(o);
			if (!Value.isEmpty()) {
				for (int i = 0; i < Value.length(); i++) {
					if (Value.charAt(i) == ('-')) {
						lowerLimit = Value.substring(0, i+1);
					}
					else {
						upperLimit = Value.substring(i+1, Value.length());
					}
				}
			}
			System.out.println("currentEnergyValue in Dyn: "+ currentEnergyValue);
			if (Value != null) {
				modeDynamicScope.put(o,
						((currentEnergyValue - previousEnergyValue) + Value));
				
			} else {
				modeDynamicScope.put(o,
						(currentEnergyValue - previousEnergyValue));
			}
		}
		if (isScalingNeeded) {
			scale(availableFreqs[phaseNO*(availableFreqs.length)/phaseSize]);
		}

		
		return o;
	}*/
	
	public static Object epilogueDynamicObject(Object o, Object useless, boolean isProfilingNeeded,
			boolean isScalingNeeded, int phaseNO) {
		double powerConsumption = -1.0;
		if (isProfilingNeeded) {
			double currentEnergyValue = EnergyStatcheck();
			Double Value = modeDynamicObjectMapping.get(o);
			System.out.println("currentEnergyValue in Dyn: "+ currentEnergyValue);
			powerConsumption = currentEnergyValue - previousEnergyValue;
			if (Value!=null) {
				
				modeDynamicObjectMapping.put(o, powerConsumption + Value);
				
			} else {
				modeDynamicObjectMapping.put(o, powerConsumption);
				
			}
		}
		if (isScalingNeeded) {
			scale(availableFreqs[phaseNO*(availableFreqs.length)/phaseSize]);
		}

		return useless;
	}
	// end

	public static void runAnaThreadOriginal() {

//		exec.execute(new AnalyzerThreadOriginal());
//		exec.shutdown();
		new AnalyzerThreadOriginal().run1();
	}
	
	public static void runAnaThreadGradual() {

		exec.execute(new AnalyzerThreadGradual());
		exec.shutdown();

	}
	//kenan
	public native static void init();

	public native static double EnergyStatcheck();

	public native static int scale(int freq);

	public native static int[] freqAvailable();

//	static {
//		System.out.println(System.getProperty("java.library.path"));
//		System.setProperty("java.library.path",
//				"/home/kenan/polyglot/src/polyglot/ext/et/compiler/src/et/util");
//		try {
//			Field fieldSysPath = ClassLoader.class
//					.getDeclaredField("sys_paths");
//			fieldSysPath.setAccessible(true);
//			fieldSysPath.set(null, null);
//		} catch (Exception e) {
//
//		}
//
//		System.loadLibrary("energyCheck");
//		init();
//	}
	public static void main (String[] args) {
		double cpuPower;
		int[] freqValue;
		int ret;
//		MyUtility.init();
		cpuPower = MyUtility.EnergyStatcheck();
		
		freqValue = MyUtility.freqAvailable();
		System.out.println("cpuPower"+cpuPower);

		ret = MyUtility.scale(freqValue[2]);
		System.out.println(ret);
		MyUtility.modeDynamicFamilyMapping.putIfAbsent("Binghamton", new HashSet<Object>());
		MyUtility.modeStaticFamilyMapping.putIfAbsent("Binghamton", new HashSet<Object>());
		MyUtility.modeStaticFamilyMapping.putIfAbsent("NYC", new HashSet<Object>());
		
		
/*		t1 v =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(10000
		             )));
		t1 a =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(10000
		             )));
		
		t1 z =
				(t1)MyUtility.staticModeAdd((t1)MyUtility.modeStaticFamilyAdd("Binghamton",new t1(100000)),1);
		t1 c =
				(t1)MyUtility.staticModeAdd((t1)MyUtility.modeStaticFamilyAdd("Binghamton",new t1(100000)),2);
		t1 d =
				(t1)MyUtility.staticModeAdd((t1)MyUtility.modeStaticFamilyAdd("NYC",new t1(1000000)),1);
		t1 e =
				(t1)MyUtility.staticModeAdd((t1)MyUtility.modeStaticFamilyAdd("NYC",new t1(1000000)),2);
		MyUtility.epilogueDynamicObject((t1)MyUtility.preamble(v,true,false,0), ((t1)MyUtility.preamble(v,true,false,0)).loop(), true, false, 0);
		MyUtility.epilogueDynamicObject((t1)MyUtility.preamble(a,true,false,0), ((t1)MyUtility.preamble(a,true,false,0)).loop(),true, false, 0);
		MyUtility.epilogueStaticObject((t1)MyUtility.preamble(z,true,false,0), ((t1)MyUtility.preamble(z,true,false,0)).loop(), true, false, 0);
//		MyUtility.epilogueStaticObject(((t1)MyUtility.preamble(z,true,false,0)).loop(), true, false, 0);
		MyUtility.epilogueStaticObject((t1)MyUtility.preamble(c,true,false,0), ((t1)MyUtility.preamble(c,true,false,0)).loop(), true, false, 0);
		MyUtility.epilogueStaticObject((t1)MyUtility.preamble(d,true,false,0), ((t1)MyUtility.preamble(d,true,false,0)).loop(), true, false, 0);
		MyUtility.epilogueStaticObject((t1)MyUtility.preamble(e,true,false,0), ((t1)MyUtility.preamble(e,true,false,0)).loop(), true, false, 0);
*/
		
		t1 a1 =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(
		             )));
		t1 a2 =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(
		             )));
		t1 a3 =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(
		             )));
		t1 a4 =
		          ((t1)MyUtility.modeDynamicFamilyAdd("Binghamton",new t1(
		             )));
		
		t2 z =
				(t2)MyUtility.staticModeAdd((t2)MyUtility.modeStaticFamilyAdd("Binghamton",new t2()),1);
		t3 c =
				(t3)MyUtility.staticModeAdd((t3)MyUtility.modeStaticFamilyAdd("Binghamton",new t3()),2);
		t3 d =
				(t3)MyUtility.staticModeAdd((t3)MyUtility.modeStaticFamilyAdd("NYC",new t3()),1);
		t3 e =
				(t3)MyUtility.staticModeAdd((t3)MyUtility.modeStaticFamilyAdd("NYC",new t3()),2);
		
//		for (int i = 0; i < 10; i++) {
			MyUtility.epilogueDynamicObject(
					(t1) MyUtility.preamble(a1, true, false, 0),
					((t1) MyUtility.preamble(a1, true, false, 0)).loop(), true,
					false, 0);
			MyUtility.epilogueDynamicObject(
					(t1) MyUtility.preamble(a2, true, false, 0),
					((t1) MyUtility.preamble(a2, true, false, 0)).loop(), true,
					false, 0);
			MyUtility.epilogueDynamicObject(
					(t1) MyUtility.preamble(a3, true, false, 0),
					((t1) MyUtility.preamble(a3, true, false, 0)).loop(), true,
					false, 0);
			MyUtility.epilogueDynamicObject(
					(t1) MyUtility.preamble(a4, true, false, 0),
					((t1) MyUtility.preamble(a4, true, false, 0)).loop(), true,
					false, 0);
			MyUtility.epilogueStaticObject(
					(t2) MyUtility.preamble(z, true, false, 0),
					((t2) MyUtility.preamble(z, true, false, 0)).loop(), true,
					false, 0);
			// MyUtility.epilogueStaticObject(((t1)MyUtility.preamble(z,true,false,0)).loop(),
			// true, false, 0);
			MyUtility.epilogueStaticObject(
					(t3) MyUtility.preamble(c, true, false, 0),
					((t3) MyUtility.preamble(c, true, false, 0)).loop(), true,
					false, 0);
			
			MyUtility.epilogueStaticObject(
					(t3) MyUtility.preamble(d, true, false, 0),
					((t3) MyUtility.preamble(d, true, false, 0)).loop(), true,
					false, 0);
			
			MyUtility.epilogueStaticObject(
					(t3) MyUtility.preamble(e, true, false, 0),
					((t3) MyUtility.preamble(e, true, false, 0)).loop(), true,
					false, 0);
//		}
		
			new AnalyzerThreadOriginal().run1();
//		exec.execute(new AnalyzerThreadOriginal());
//		exec.execute(new AnalyzerThreadGradual());
		exec.shutdown();
		
/*		for(int i=0; i<freqValue.length; i++) {
			System.out.println("freqValue"+freqValue[i]);
		}
	*/	
	}
}
//end kenan
/*class t1 {
	private static int bound;
	t1(int bound) {
		this.bound = bound;
	}
	public static int loop() {
		int a = 0;
		for(int i = 0; i < bound; i++) {
			a=1;
		}
		return a;
	}
}*/
class t1 {
	
	public static int loop() {
		int a = 0;
		for(int i = 0; i < 10000; i++) {
			System.out.println("t1");
		}
		return a;
	}
}

class t2 {
	
	public static int loop() {
		int a = 0;
		for(int i = 0; i < 100000; i++) {
			System.out.println("t2");
		}
		return a;
	}
}

class t3 {
	
	public static int loop() {
		int a = 0;
		for(int i = 0; i < 1000; i++) {
			System.out.println("t3");
		}
		return a;
	}
}

class AnalyzerThreadGradual implements Runnable {
	//add by kenan 9/1/2012
	public Object lessPowerObj = null;
	public Object morePowerObj = null;
	public Double lessObjPower = -1.0;
	public Double moreObjPower = -1.0;
	public String modeScope = null;
	public int label = -1;
	
	public void run() {
		
		for (Map.Entry<String, HashSet<Object>> staticEntry : MyUtility.modeStaticFamilyMapping
				.entrySet()) {
			HashSet<Object> staticObjs = staticEntry.getValue();
			String staticModeFamily = staticEntry.getKey();

			HashSet<Object> dynObjs = MyUtility.modeDynamicFamilyMapping
					.get(staticModeFamily);
			for (Iterator staticIt = staticObjs.iterator(); staticIt.hasNext();) {

				Object obj = staticIt.next();
				Double value = MyUtility.modeStaticObjectMapping.get(obj);
				// System.out.println("modeStaticObjectMapping.get(obj): "+obj);
				// For the situation that programmer declare mode/phase but
				// not call it;
				if (value != null) {
					MyUtility.tempStaticObjModes.putIfAbsent(obj, value);
				}
			}

			MyUtility.sorted_modeStaticObject = arrangeObject
					.arrangeStaticObjOrder();

			if (dynObjs != null) {
				for (Iterator dynIt = dynObjs.iterator(); dynIt.hasNext();) {

					Object obj = dynIt.next();
					Double value = MyUtility.modeDynamicObjectMapping.get(obj);
					// For the situation that programmer declare mode/phase but
					// not call it;
					if (value != null) {
						MyUtility.tempDynObjModes.putIfAbsent(obj, value);

					}
				}
				// sorting dynamic objects to easily compare with static objects
				MyUtility.sorted_modeDynObject = arrangeObject
						.arrangeDynObjOrder();
			}

			for (Iterator it = MyUtility.sorted_modeStaticObject.entrySet()
					.iterator(); it.hasNext();) {

				Map.Entry<Object, Double> entry = (Map.Entry<Object, Double>) it
						.next();
				Object staticObjectKey = entry.getKey();
				Double staticPower = entry.getValue();

				if (staticPower != null) {
					for (Iterator dynIt = MyUtility.sorted_modeDynObject
							.keySet().iterator(); dynIt.hasNext();) {

						Object dynObj = dynIt.next();
						Double dynPower = MyUtility.modeDynamicObjectMapping
								.get(dynObj);
						if (MyUtility.firstStaticObj) {

							lessPowerObj = staticObjectKey;
							morePowerObj = staticObjectKey;
							lessObjPower = staticPower;
							moreObjPower = staticPower;

							// TODO: Threshold to decide whether change
							// lowerLimit
							if (dynPower < lessObjPower) {
								modeScope = MyUtility.lowerLimit + "-"
										+ MyUtility.upperLimit;
								MyUtility.modeDynamicScope.put(dynObj,
										modeScope);
								System.out
										.println("if dynamic object less than first static obj -- Scope: "
												+ MyUtility.modeDynamicScope
														.get(dynObj));

							} else {
								MyUtility.firstStaticObj = false;
								if (it.hasNext()) {
									updateLimit(it, lessObjPower, moreObjPower);
									while (moreObjPower < dynPower
											&& it.hasNext()) {
										updateLimit(it, lessObjPower,
												moreObjPower);
									}
								}

							}

						}
						if (!MyUtility.firstStaticObj) {
							while (morePowerObj != lessPowerObj
									&& dynPower <= moreObjPower) {

								if ((dynPower - lessObjPower) < (moreObjPower - dynPower)) {
									label = MyUtility.MlabelsMapping
											.get(lessPowerObj);
									MyUtility.lowerLimit = Integer
											.toString(label);
									modeScope = MyUtility.lowerLimit + "-"
											+ MyUtility.upperLimit;
									MyUtility.modeDynamicScope.put(dynObj,
											modeScope);
									System.out
											.println("object in the middle -- Scope: "
													+ MyUtility.modeDynamicScope
															.get(dynObj));
								} else if ((dynPower - lessObjPower) >= (moreObjPower - dynPower)) {
									label = MyUtility.MlabelsMapping
											.get(morePowerObj);

									MyUtility.upperLimit = Integer
											.toString(label);
									modeScope = MyUtility.lowerLimit + "-"
											+ MyUtility.upperLimit;
									MyUtility.modeDynamicScope.put(dynObj,
											modeScope);
									System.out
											.println("object in the middle -- Scope: "
													+ MyUtility.modeDynamicScope
															.get(dynObj));
								}
								// System.out.println("Scope: "+MyUtility.modeDynamicScope.get(dynObj));
								System.out.println("Whether dynIt has next: "
										+ dynIt.hasNext());
								// if (!dynIt.hasNext()) {
								// break;
								// }

								if (!dynIt.hasNext() && MyUtility.lastDynObj) {
									break;
								} else if (!dynIt.hasNext()) {
									MyUtility.lastDynObj = true;
								} else {
									dynObj = dynIt.next();
									dynPower = MyUtility.modeDynamicObjectMapping
											.get(dynObj);
								}
							}

							// dynamic object power consumption is bigger than
							// the labor
							// upper bound(temperate upper bound) of static
							// object
							while (moreObjPower < dynPower && it.hasNext()) {
								updateLimit(it, lessObjPower, moreObjPower);
							}

							// If it doesn't have next, power consumption of
							// dynamic object
							// Accede the metric of static object.
							if (moreObjPower < dynPower && !it.hasNext()) {
								modeScope = MyUtility.lowerLimit + "-"
										+ MyUtility.upperLimit;
								MyUtility.modeDynamicScope.put(dynObj,
										modeScope);
								System.out
										.println("object in the last -- Scope: "
												+ MyUtility.modeDynamicScope
														.get(dynObj));
							}
						}
					}
				}
			}
			/*********** Print out for test *************/
			Test test = new Test(MyUtility.modeDynamicObjectMapping,
					MyUtility.MlabelsMapping,
					MyUtility.sorted_modeStaticObject,
					MyUtility.sorted_modeDynObject, MyUtility.modeDynamicScope);
			test.printLabelRelt();

			MyUtility.modeDynamicScope.clear();
			MyUtility.modeObjectMapping.clear();
			MyUtility.sorted_modeStaticObject.clear();
			MyUtility.sorted_modeDynObject.clear();
			MyUtility.tempStaticObjModes.clear();
			MyUtility.tempStaticObjPhases.clear();
			if (MyUtility.sorted_modeStaticObject.isEmpty()
					&& MyUtility.tempStaticObjModes.isEmpty()
					&& MyUtility.sorted_modeDynObject.isEmpty()
					&& MyUtility.modeDynamicScope.isEmpty()
					&& MyUtility.modeObjectMapping.isEmpty()) {
				System.out.println("clear temp mappings for next family");
			}

		}
	}
	
	public AnalyzerThreadGradual updateLimit(Iterator it, double lessObjPower, double moreObjPower) {

		if (lessObjPower == moreObjPower) {
			Map.Entry<Object, Double> entry = (Map.Entry<Object, Double>) it
					.next();
			Object staticObjectKey = entry.getKey();
			double staticPower = entry.getValue();
			this.morePowerObj = staticObjectKey;
			this.moreObjPower = staticPower;

		} else {
			Map.Entry<Object, Double> entry = (Map.Entry<Object, Double>) it
					.next();
			this.lessPowerObj = this.morePowerObj;
			Object staticObjectKey = entry.getKey();
			double staticPower = entry.getValue();
			lessObjPower = moreObjPower;
			this.morePowerObj = staticObjectKey;
			this.moreObjPower = staticPower;

		}

		return this;
	}
	
}
// end kenan 9/1/2012
//class AnalyzerThreadOriginal implements Runnable {
class AnalyzerThreadOriginal {
	// kenan

	public void run1() {

		// kenan 8/19/2012

		for (Map.Entry<String, HashSet<Object>> staticEntry : MyUtility.modeStaticFamilyMapping
				.entrySet()) {
			HashSet<Object> staticObjs = staticEntry.getValue();
			String staticModeFamily = staticEntry.getKey();

			HashSet<Object> dynObjs = MyUtility.modeDynamicFamilyMapping
					.get(staticModeFamily);
			// String dynModeFamily = dynEntry.getKey();
			for (Iterator staticIt = staticObjs.iterator(); staticIt.hasNext();) {

				Object obj = staticIt.next();
				Double value = MyUtility.modeStaticObjectMapping.get(obj);
				 System.out.println("modeStaticObjectMapping.get(obj): "+obj);
				// For the situation that programmer declare mode/phase but
				// not call it;
				if (value != null) {
					MyUtility.tempStaticObjModes.putIfAbsent(obj, value);
					MyUtility.modeObjectMapping.putIfAbsent(obj, value);
				}
			}

			MyUtility.sorted_modeStaticObject = arrangeObject
					.arrangeStaticObjOrder();

			if (dynObjs != null) {
				for (Iterator dynIt = dynObjs.iterator(); dynIt.hasNext();) {

					Object obj = dynIt.next();
					Double value = MyUtility.modeDynamicObjectMapping.get(obj);
					// For the situation that programmer declare mode/phase but
					// not call it;
					if (value != null) {

						MyUtility.modeObjectMapping.putIfAbsent(obj, value);
					}
				}
			}

			MyUtility.sorted_modeObject = arrangeObject.arrangeAllObjectOrder();

			runModeAnaByFstMethod();

			// }
		}

	}

	

	// end kenan 8/19/2012

	public void runModeAnaByFstMethod() {

		// kenan 8/20/2012
		Object lessPowerObj = null;
		Object morePowerObj = null;
		boolean passedFirstStaticObj = false;
		boolean lastObj = true;
		double objPower;
		double lessStaticObjPower = 0.0;
		double moreStaticObjPower = 0.0;
		int label;

		for (Iterator it = MyUtility.sorted_modeStaticObject.keySet()
				.iterator(); it.hasNext();) {

			Object staticObjectKey = it.next();
			lessPowerObj = staticObjectKey;
			for (Iterator i = MyUtility.sorted_modeObject.keySet().iterator(); i
					.hasNext();) {
				Object objectKey = i.next();

				objPower = MyUtility.sorted_modeObject.get(objectKey);

				// find objects which are smaller than the first static object,
				// and insert them into the
				// location of the first static object map

				if (!(passedFirstStaticObj)) {
					lessStaticObjPower = MyUtility.sorted_modeStaticObject
							.get(staticObjectKey);
					moreStaticObjPower = MyUtility.sorted_modeStaticObject
							.get(staticObjectKey);
					if (objPower <= lessStaticObjPower) {
						/*System.out
								.println("staticObjectKey:"
										+ staticObjectKey
										+ " label:"
										+ MyUtility.MlabelsMapping
												.get(staticObjectKey));*/
						label = MyUtility.MlabelsMapping.get(staticObjectKey);

						MyUtility.MlabelsMapping.putIfAbsent(objectKey, label);
					} else {

						passedFirstStaticObj = true;
						
						if (it.hasNext()) {
							staticObjectKey = it.next();
							morePowerObj = staticObjectKey;
							moreStaticObjPower = MyUtility.sorted_modeStaticObject
									.get(staticObjectKey);
							while (moreStaticObjPower < objPower && it.hasNext()) {
								
								lessStaticObjPower = moreStaticObjPower;
								lessPowerObj = morePowerObj;
								staticObjectKey = it.next();
								morePowerObj = staticObjectKey;
								
								moreStaticObjPower = MyUtility.sorted_modeStaticObject
										.get(staticObjectKey);
							}
						}
					}
				} if(passedFirstStaticObj){

					while (morePowerObj != null
							&& objPower <= moreStaticObjPower && i.hasNext()) {

						if ((objPower - lessStaticObjPower) < (moreStaticObjPower - objPower)) {
							label = MyUtility.MlabelsMapping.get(lessPowerObj);
							MyUtility.MlabelsMapping.putIfAbsent(objectKey,
									label);
						} else if ((objPower - lessStaticObjPower) >= (moreStaticObjPower - objPower)) {
							label = MyUtility.MlabelsMapping.get(morePowerObj);
							MyUtility.MlabelsMapping.putIfAbsent(objectKey,
									label);
						}
						
							objectKey = i.next();
							objPower = MyUtility.sorted_modeObject.get(objectKey);
						
						
					}

				}

				// For the objects before the first static objects in
				// sorted_modeObject

				// before pass the first static object, both
				// "moreStaticObjPower" and "lessStaticObjPower" is
				// the first static object power, After pass the first static
				// object, when the value of object
				// is greater than the following static object, change the
				// marks: "lessStaticObjPower" and
				// "moreStaticObjPower"
				//TODO: jump
				while (it.hasNext() && objPower > moreStaticObjPower) {
					lessStaticObjPower = moreStaticObjPower;
					lessPowerObj = morePowerObj;
					staticObjectKey = it.next();
					morePowerObj = staticObjectKey;
					
					moreStaticObjPower = MyUtility.sorted_modeStaticObject
							.get(staticObjectKey);
				}

				// For the objects after the last static objects in
				// sorted_modeObject
				if (!it.hasNext()) {
					if (objPower > moreStaticObjPower) {
						label = MyUtility.MlabelsMapping.get(staticObjectKey);
						MyUtility.MlabelsMapping.putIfAbsent(objectKey, label);
					} else
						continue;
				}

			}
			// end kenan 8/20/2012
		}
		
		/*********Print out for test*********/
		Test test = new Test(MyUtility.modeDynamicObjectMapping, MyUtility.MlabelsMapping, MyUtility.sorted_modeStaticObject, null, null);
		test.printLabelRelt();
		

		MyUtility.sorted_modeStaticObject.clear();
		MyUtility.sorted_modeObject.clear();
		MyUtility.modeObjectMapping.clear();
		MyUtility.tempStaticObjModes.clear();
		MyUtility.tempStaticObjPhases.clear();
		if (MyUtility.sorted_modeStaticObject.isEmpty()
				&& MyUtility.sorted_modeObject.isEmpty()&& MyUtility.tempStaticObjModes.isEmpty()
				&& MyUtility.tempStaticObjPhases.isEmpty()) {
			System.out.println("clear temp mappings for next family");
		}
		/*
		 * for (Iterator<Object> obj = objs.iterator(); obj.hasNext();) { Double
		 * Value = MyUtility.modeStaticObjectMapping.get(obj.next());
		 * MlabelsMapping.put(obj, value);
		 * 
		 * if (Value.doubleValue() > modeThreshold) { MlabelsMapping.put(key,
		 * 3); // high }
		 * 
		 * else { MlabelsMapping.put(key, 2); // low } } return
		 * MlabelsMapping.get(key);
		 */
		// return 0;
	}

	public int runPhaseAnalyzer(Object key) {
		// Actual code which will run and estimatehifi the current / projected
		// phase
		// for given key

		/*
		 * HashSet hs = MyUtility.phaseFamilyMapping.get(key); for
		 * (Iterator<Object> i = hs.iterator(); i.hasNext();) { Double Value =
		 * MyUtility.phaseObjectMapping.get(i.next()); if (Value.doubleValue() >
		 * phaseThreshold) { PlabelsMapping.put(key, 3); // cpu }
		 * 
		 * else { PlabelsMapping.put(key, 2);// memory } }
		 */
		return MyUtility.PlabelsMapping.get(key);
	}

	public int getLatestMode(Object methodKey) {
		// runModeAnalyzer(methodKey);
		return MyUtility.MlabelsMapping.get(methodKey);
	}

	public int getLatestPhase(Object methodKey) {
		// runPhaseAnalyzer(methodKey);
		return MyUtility.PlabelsMapping.get(methodKey);
	}

}

class Test {
	ConcurrentHashMap<Object, Double> modeDynamicObjectMapping; 
	ConcurrentHashMap<Object, Integer> MlabelsMapping;
	SortedMap<Object, Double> sorted_modeStaticObject;
	SortedMap<Object, Double> sorted_modeDynObject;
	ConcurrentHashMap<Object, String> modeDynamicScope;
	public Test(ConcurrentHashMap<Object, Double> modeDynamicObjectMapping, 
			ConcurrentHashMap<Object, Integer> MlabelsMapping, 
			SortedMap<Object, Double> sorted_modeStaticObject,
			SortedMap<Object, Double> sorted_modeDynObject,
			ConcurrentHashMap<Object, String> modeDynamicScope) {
		this.modeDynamicObjectMapping = modeDynamicObjectMapping;
		this.MlabelsMapping = MlabelsMapping;
		this.sorted_modeStaticObject = sorted_modeStaticObject;
		this.sorted_modeDynObject = sorted_modeDynObject;
		this.modeDynamicScope = modeDynamicScope;
	}
	
	
	
	public void printLabelRelt() {
		
		System.out.println("*************Print labels relationship*******************");
		
		for(Iterator t = MyUtility.sorted_modeStaticObject.entrySet().iterator();t.hasNext();) {
			Map.Entry<Object, Double> staticT = (Map.Entry<Object, Double>)t.next();
			Object staticObject = staticT.getKey();
			Double staticValue = staticT.getValue();
			int staticLabel = MyUtility.MlabelsMapping.get(staticObject);
			System.out.println("sorted_modeStaticObject object: "+ staticObject +" value: " + staticValue
					+ " label: " + staticLabel);
		}
		if (sorted_modeDynObject!=null) {
			for (Iterator t = MyUtility.modeDynamicObjectMapping.entrySet().iterator(); t.hasNext();) {
				Map.Entry<Object, Double> dynT = (Map.Entry<Object, Double>) t.next();
				Object dynObject = dynT.getKey();
				Double dynValue = dynT.getValue();
				System.out.println("modeDynamicObjectMapping object: "
						+ dynObject + " value: " + dynValue);
			}
		}
		for(Iterator t = MyUtility.MlabelsMapping.entrySet().iterator();t.hasNext();) {
			Map.Entry<Object, Integer> labelT = (Map.Entry<Object, Integer>)t.next();
			Object labelObject = labelT.getKey();
			Integer labelValue = labelT.getValue();
			System.out.println("MlabelsMapping object: "+ labelObject +" value: " + labelValue);
		}
		if(sorted_modeDynObject!=null) {
			for(Iterator t = MyUtility.sorted_modeDynObject.entrySet().iterator();t.hasNext();) {
				Map.Entry<Object, Double> dynT = (Map.Entry<Object, Double>)t.next();
				Object dynObject = dynT.getKey();
				Double dynValue = dynT.getValue();
				
				System.out.println("sorted_modeDynObject object: "+ dynObject +" value: " + dynValue);
			}
			
			for(Iterator t = MyUtility.modeDynamicScope.entrySet().iterator();t.hasNext();) {
				Map.Entry<Object, String> scopeT = (Map.Entry<Object, String>)t.next();
				Object dynObject = scopeT.getKey();
				String scopeValue = scopeT.getValue();
				
				System.out.println("sorted_modeDynObject object: "+ dynObject +" scopeValue: " + scopeValue);
			}
		}
	}
	
}

class arrangeObject {
	
	public static SortedMap<Object, Double> arrangeAllObjectOrder() {
		ValueComparator bvc = new ValueComparator(MyUtility.modeObjectMapping);
		// TreeMap<Object,Double> sorted_map = new TreeMap<Object,Double>(bvc);
		SortedMap<Object, Double> sorted_map = Collections
				.synchronizedSortedMap(new TreeMap<Object, Double>(bvc));
		System.out.println("before all sorted map: " + MyUtility.modeObjectMapping);
		sorted_map.putAll(MyUtility.modeObjectMapping);
		System.out.println("All sorted map: " + sorted_map);
		return sorted_map;
	}

	public static SortedMap<Object, Double> arrangeStaticObjOrder() {
		ValueComparator bvc = new ValueComparator(
				MyUtility.tempStaticObjModes);
		// TreeMap<Object,Double> sorted_map = new TreeMap<Object,Double>(bvc);
		SortedMap<Object, Double> sorted_map = Collections
				.synchronizedSortedMap(new TreeMap<Object, Double>(bvc));
		System.out.println("before static sorted map: " + MyUtility.tempStaticObjModes);
		sorted_map.putAll(MyUtility.tempStaticObjModes);
		System.out.println("static sorted map: " + sorted_map);
		return sorted_map;
	}
	
	public static SortedMap<Object, Double> arrangeDynObjOrder() {
		ValueComparator bvc = new ValueComparator(
				MyUtility.tempDynObjModes);
		// TreeMap<Object,Double> sorted_map = new TreeMap<Object,Double>(bvc);
		SortedMap<Object, Double> sorted_map = Collections
				.synchronizedSortedMap(new TreeMap<Object, Double>(bvc));
		System.out.println("before dynamic sorted map: " + MyUtility.tempDynObjModes);
		sorted_map.putAll(MyUtility.tempDynObjModes);
		System.out.println("dynamic sorted map: " + sorted_map);
		return sorted_map;
	}
}

class ValueComparator implements Comparator<Object> {

	ConcurrentHashMap<Object, Double> base;
    public ValueComparator(ConcurrentHashMap<Object, Double> base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {
        return base.get(a).compareTo(base.get(b));
    }
}
