package eco;

import eco.visit.CalibratePass;
import eco.visit.MarkPass; 

import polyglot.ext.jl7.JL7Scheduler;

import polyglot.ast.NodeFactory;

import polyglot.frontend.JLExtensionInfo;
import polyglot.frontend.Job;
import polyglot.frontend.Scheduler;
import polyglot.frontend.JLScheduler;
import polyglot.frontend.goals.Barrier;
import polyglot.frontend.goals.Goal;
import polyglot.frontend.goals.Serialized;
import polyglot.frontend.goals.VisitorGoal;

import polyglot.types.TypeSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class EcoScheduler extends JL7Scheduler {

  public EcoScheduler(JLExtensionInfo extInfo) {
    super(extInfo);
  }

  /*
  public Goal FirstPassBarrier() {
    JLScheduler scheduler = (JLScheduler) extInfo.scheduler();
		return internGoal(new Barrier("PrePass_BARRIER", scheduler) {
      public Goal goalForJob(Job job) {
			  return FirstPass(job);
		  }
    }); 
  }
  */
		
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

  /*
	public Goal FirstPass(final Job job) {
		TypeSystem ts = job.extensionInfo().typeSystem();
		NodeFactory nf = job.extensionInfo().nodeFactory();

		Goal g = internGoal(new VisitorGoal(job, new Eco1stPass(job, ts, nf)) {
		  public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
			  List<Goal> l = new ArrayList<Goal>();
				l.addAll(super.prerequisiteGoals(scheduler));
				l.add(scheduler.TypeChecked(job));
				return l;
			}
		});
		return g;
	}

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
  */

	public Goal Serialized(final Job job) {
	  Goal g = internGoal(new Serialized(job) {
		  public Collection<Goal> prerequisiteGoals(Scheduler scheduler) {
		    List<Goal> l = new ArrayList<Goal>();
				l.addAll(super.prerequisiteGoals(scheduler));
				//l.add(FirstPass(job));
				//l.add(SecondPass(job));
				l.add(MarkPass(job));
				l.add(CalibratePass(job));
				return l;
			}
		});
		return g;
	}


}
