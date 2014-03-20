package et.visit;

import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class ValuePass extends TypeChecker {
	public ValuePass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	protected NodeVisitor enterCall(Node parent, Node n)
			throws SemanticException {
		return n.del().typeCheckEnter(this);
	}

	protected Node leaveCall(Node old, Node n, NodeVisitor v)
			throws SemanticException {
		Node m = n.del().typeCheck((TypeChecker) v);

//		if (m instanceof Expr && ((Expr) m).type() == null) {
//			throw new InternalCompilerError("Null type for " + m, m.position());
//		}
//
//		/*
//		 * insert the value of the solution to the call's target. Note that not
//		 * all of the calls in source code have the solution. Only the code
//		 * analyzed by the compiler have solution
//		 */
//		if (m instanceof Call) {
//			DynamicECMCall_c call = (DynamicECMCall_c) m;
//
//			// is ignorable
//			if (Utils.ignoreCall(call)) {
//				P.println("ignore this call: " + call);
//				return m;
//			}
//
//			// temporarily don't deal with method of return type of String
//			if (call.methodInstance().returnType().toString()
//					.startsWith("java.lang.String"))
//				return m;
//
//			// check the curPhase and target's phase
//			// Type tmpTarget = ;
//			if (call.target().type() instanceof ECMSuperType) {
//				ECMSuperType ecmType = (ECMSuperType) call.target().type();
//
//				ECMSuperType targetType = (ECMSuperType) call.target().type();
//				if (call.target() instanceof Adapt_c) {
//					targetType = (ECMSuperType) ((Adapt_c) call.target())
//							.getExpr().type();
//				}
//				
//				/*
//				 * if work with Lp solving result, this is only used when
//				 * object-level qualifier is allowed
//				 */
//				boolean workWithLpResult = false;
//				if (workWithLpResult) {
//					//set the target's phase value
//					
//					String originValue = targetType.getECMPhase(); 
//					originValue = originValue.endsWith(NameIndex.defaultPhase)? originValue.substring(0, originValue.indexOf('#')) : originValue;
//					double targetPhase = LinearSolverMapper.getResultFromOriginName(originValue, LinearSolverMapper.ValueType.PHASE);
//					call.setTPhase(targetPhase);
//					
//					//set the enclosing phase value
////					call.setCurPhase((Double) varToValue
////							.get(refreshed));
////					
////					if (finalMap.containsKey(((ECMSuperType) targetType)
////							.getFinalPhase())) {
////
////						call.setTPhase((Double) finalMap
////								.get(((ECMSuperType) targetType)
////										.getFinalPhase()));
////
////						String refreshed = (String) etTypeSystem_c.curPhaseFinder
////								.get(((ECMSuperType) targetType)
////										.getAlias_name() + "#Phase");
////
////						double curPhase = 0;
////						if (varToValue.containsKey(refreshed)) {
////							curPhase = (Double) varToValue.get(refreshed);
////							if (varToValue.containsKey(refreshed)) {
////								call.setCurPhase((Double) varToValue
////										.get(refreshed));
////							} else {
////								EasyDebugger.exit("!!!cannot find curPhase: "
////										+ refreshed + " in varToValue");
////							}
////						}
////					} else {
////						EasyDebugger.message("!!!cannot find target phase: "
////								+ ((ECMSuperType) targetType).getFinalPhase()
////								+ " in finalMap " + call.position());
////					}
//				}
//
//			}
//			// static method
//			else if (call.methodInstance().flags().isStatic())
//				;
//			else {
//				EasyDebugger
//						.exit("The target's type is not MySuperType!! Check it! "
//								+ call.target().type().getClass()
//								+ " in "
//								+ call.name() + " " + call.position());
//			}
//		}
//
//		// if (m instanceof SourceFile) {
//		// // if(true)return m;
//		// if (My2PassChecker.isPorcessed)
//		// return m;
//		// }

		return m;
	}
}
