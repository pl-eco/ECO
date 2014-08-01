package eco;

import eco.ExtensionInfo;
import eco.ast.*;
import eco.parse.Grm;
import eco.parse.Lexer_c;
import eco.types.*;
import eco.visit.CalibratePass;
import eco.visit.Eco1stPass;
import eco.visit.Eco2ndPass;
import eco.visit.MarkPass;
import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.frontend.*;
import polyglot.frontend.goals.Barrier;
import polyglot.frontend.goals.Goal;
import polyglot.frontend.goals.Serialized;
import polyglot.frontend.goals.VisitorGoal;

import java.util.*;
import java.io.*;

import cs.CSScheduler;
import cs.data.Properties;
import cs.visit.CS1stPass;
import cs.visit.CS2ndPass;

/**
 * Extension information for eco extension.
 */
public class ExtensionInfo extends polyglot.ext.jl5.ExtensionInfo {
	public static final Object totalTime = new Object();
	static {
		// force Topics to load
		Topics t = new Topics();

		Properties.stopWatchStart(totalTime);
	}

	public String defaultFileExtension() {
		return "eco";
	}

	public String compilerName() {
		return "ecoc";
	}

	protected NodeFactory createNodeFactory() {
		return new EcoNodeFactory_c();
	}

	protected TypeSystem createTypeSystem() {
		return new EcoTypeSystem_c();
	}

	// Add by Steve 6/9/11
	public Scheduler createScheduler() {
		return new ETSchedule(this);
	}

	static class ETSchedule extends CSScheduler {
		ETSchedule(ExtensionInfo extInfo) {
			super(extInfo);
		}

		// public Goal ReachabilityChecked(Job job) {
		// TypeSystem ts = extInfo.typeSystem();
		// NodeFactory nf = extInfo.nodeFactory();
		// Goal g = ECMReachabilityChecked.create(this, job, ts, nf);
		// return g;
		// }

//		public Goal CT2OTPropPass(final Job job) {
//			TypeSystem ts = job.extensionInfo().typeSystem();
//			NodeFactory nf = job.extensionInfo().nodeFactory();
//
//			Goal g = internGoal(new VisitorGoal(job, new CT2OTPropPass(job, ts,
//					nf)) {
//				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
//					List<Goal> l = new ArrayList<Goal>();
//					l.addAll(super.prerequisiteGoals(scheduler));
//					l.add(FirstPassBarrier());
//					return l;
//				}
//			});
//			return g;
//		}

		public Goal FirstPassBarrier() {
			JLScheduler scheduler = (JLScheduler) extInfo.scheduler();
			return internGoal(new Barrier("PrePass_BARRIER", scheduler) {
				public Goal goalForJob(Job job) {
					return FirstPass(job);
				}
			});
		}

		public Goal MarkPass(final Job job) {
			TypeSystem ts = job.extensionInfo().typeSystem();
			NodeFactory nf = job.extensionInfo().nodeFactory();

			Goal g = internGoal(new VisitorGoal(job, new MarkPass(job, ts, nf)) {
				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
					List<Goal> l = new ArrayList<Goal>();
					l.addAll(super.prerequisiteGoals(scheduler));
					l.add(scheduler.TypeChecked(job));
					// l.add(PrePassBarrier());
					return l;
				}
			});
			return g;
		}

		public Goal CalibratePass(final Job job) {
			TypeSystem ts = job.extensionInfo().typeSystem();
			NodeFactory nf = job.extensionInfo().nodeFactory();

			Goal g = internGoal(new VisitorGoal(job, new CalibratePass(job, ts, nf)) {
				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
					List<Goal> l = new ArrayList<Goal>();
					l.addAll(super.prerequisiteGoals(scheduler));
					l.add(scheduler.TypeChecked(job));
					// l.add(PrePassBarrier());
					return l;
				}
			});
			return g;
		}

		public Goal FirstPass(final Job job) {
			TypeSystem ts = job.extensionInfo().typeSystem();
			NodeFactory nf = job.extensionInfo().nodeFactory();

			Goal g = internGoal(new VisitorGoal(job, new Eco1stPass(job, ts, nf)) {
				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
					List<Goal> l = new ArrayList<Goal>();
					l.addAll(super.prerequisiteGoals(scheduler));
					l.add(scheduler.TypeChecked(job));
					// l.add(PrePassBarrier());
					return l;
				}
			});
			return g;
		}

//		public Goal ClosureBarrier() {
//			JLScheduler scheduler = (JLScheduler) extInfo.scheduler();
//			return internGoal(new Barrier("My_BARRIER", scheduler) {
//				public Goal goalForJob(Job job) {
//					return CT2OTPropPass(job);
//				}
//			});
//		}

		public Goal SecondPass(final Job job) {
			TypeSystem ts = job.extensionInfo().typeSystem();
			NodeFactory nf = job.extensionInfo().nodeFactory();

			Goal g = internGoal(new VisitorGoal(job, new Eco2ndPass(job, ts, nf)) {
				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
					List<Goal> l = new ArrayList<Goal>();
					// l.addAll(super.prerequisiteGoals(scheduler));
					l.add(FirstPassBarrier());
					return l;
				}
			});
			return g;
		}

		// public Goal FinalPass(final Job job) {
		// TypeSystem ts = job.extensionInfo().typeSystem();
		// NodeFactory nf = job.extensionInfo().nodeFactory();
		//
		// Goal g = internGoal(new VisitorGoal(job, new ValuePass(job, ts, nf))
		// {
		// public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
		// List<Goal> l = new ArrayList<Goal>();
		// l.addAll(super.prerequisiteGoals(scheduler));
		// l.add(SecondPass(job));
		// return l;
		// }
		// });
		// return g;
		// }

		public Goal Serialized(final Job job) {
			Goal g = internGoal(new Serialized(job) {
				public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
					List<Goal> l = new ArrayList<Goal>();
					l.addAll(super.prerequisiteGoals(scheduler));
					// l.add(InnerTranslator(job));
					// l.add(LocalClassRemover(job));
					// l.add(FlattenVisitor(job));
					// l.add(ExpressionFlattener(job));
					l.add(FirstPass(job));
					l.add(SecondPass(job));
					l.add(MarkPass(job));
					l.add(CalibratePass(job));
					return l;
				}
			});
			return g;
		}
	}

	@Override
	public Parser parser(Reader reader, FileSource source, ErrorQueue eq) {
		Properties.setEntryClass(source);

		reader = new polyglot.lex.EscapedUnicodeReader(reader);

		polyglot.lex.Lexer lexer = new Lexer_c(reader, source, eq);
		polyglot.parse.BaseParser parser = new Grm(lexer, ts, nf, eq);

		return new CupParser(parser, source, eq);
	}

}
