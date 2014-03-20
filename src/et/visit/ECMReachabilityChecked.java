package et.visit;
//package et.visit;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import polyglot.ast.NodeFactory;
//import polyglot.frontend.Job;
//import polyglot.frontend.Scheduler;
//import polyglot.frontend.goals.Goal;
//import polyglot.frontend.goals.ReachabilityChecked;
//import polyglot.frontend.goals.VisitorGoal;
//import polyglot.types.TypeSystem;
//import polyglot.visit.ReachChecker;
//
//public class ECMReachabilityChecked extends VisitorGoal {
//	public static Goal create(Scheduler scheduler, Job job, TypeSystem ts,
//			NodeFactory nf) {
//		return scheduler.internGoal(new ECMReachabilityChecked(job, ts, nf));
//	}
//
//	protected ECMReachabilityChecked(Job job, TypeSystem ts, NodeFactory nf) {
//		super(job, new ECMReachChecker(job, ts, nf));
//	}
//
//	public Collection prerequisiteGoals(Scheduler scheduler) {
//		List l = new ArrayList();
//		l.add(scheduler.TypeChecked(job));
//		// l.add(scheduler.ConstantsChecked(job));
//		l.addAll(super.prerequisiteGoals(scheduler));
//		return l;
//	}
// }
