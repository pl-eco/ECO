/**************************************************************************
 *                                                                         *
 *         Java Grande Forum Benchmark Suite - Thread Version 1.0          *
 *                                                                         *
 *                            produced by                                  *
 *                                                                         *
 *                  Java Grande Benchmarking Project                       *
 *                                                                         *
 *                                at                                       *
 *                                                                         *
 *                Edinburgh Parallel Computing Centre                      *
 *                                                                         *
 *                email: epcc-javagrande@epcc.ed.ac.uk                     *
 *                                                                         *
 *      Original version of this code by Hon Yau (hwyau@epcc.ed.ac.uk)     *
 *                                                                         *
 *      This version copyright (c) The University of Edinburgh, 2001.      *
 *                         All rights reserved.                            *
 *                                                                         *
 **************************************************************************/

package montecarlo;

import java.util.*;
import java.awt.*;

import tool.FileWriter;
import tools.BatteryInfo;
import tools.Mode;

modes {
	high <: full;
	low <: high;
}

public class AppDemoEco extends Universal {
	// ------------------------------------------------------------------------
	// Class variables.
	// ------------------------------------------------------------------------

	public static double JGFavgExpectedReturnRateMC = 0.0;
	/**
	 * A class variable.
	 */
	public static boolean DEBUG = true;
	/**
	 * The prompt to write before any debug messages.
	 */
	protected static String prompt = "AppDemo> ";

	public static final int Serial = 1;
	// ------------------------------------------------------------------------
	// Instance variables.
	// ------------------------------------------------------------------------
	/**
	 * Directory in which to find the historical rates.
	 */
	private String dataDirname;
	/**
	 * Name of the historical rate to model.
	 */
	private String dataFilename;
	/**
	 * The number of time-steps which the Monte Carlo simulation should run for.
	 */
	private int nTimeStepsMC = 0;
	/**
	 * The number of Monte Carlo simulations to run.
	 */
	public int nRunsMC = 0;
	/**
	 * The default duration between time-steps, in units of a year.
	 */
	private double dTime = 1.0 / 365.0;
	/**
	 * Flag to determine whether initialisation has already taken place.
	 */
	private boolean initialised = false;
	/**
	 * Variable to determine which deployment scenario to run.
	 */
	private int runMode;

	public static Vector tasks;
	public static Vector results;

	public AppDemoEco(String dataDirname, String dataFilename,
			int nTimeStepsMC, int nRunsMC) {
		this.dataDirname = dataDirname;
		this.dataFilename = dataFilename;
		this.nTimeStepsMC = nTimeStepsMC;
		this.nRunsMC = nRunsMC;
		this.initialised = false;
		set_prompt(prompt);
		set_DEBUG(DEBUG);
	}

	/**
	 * Single point of contact for running this increasingly bloated class.
	 * Other run modes can later be defined for whether a new rate should be
	 * loaded in, etc. Note that if the <code>hostname</code> is set to the
	 * string "none", then the demonstrator runs in purely serial mode.
	 */

	/**
	 * Initialisation and Run methods.
	 */

	PriceStock psMC;
	double pathStartValue = 100.0;
	double avgExpectedReturnRateMC = 0.0;
	double avgVolatilityMC = 0.0;

	public static ToInitAllTasks initAllTasks = null;

	public void initSerial() {
		try {
			//
			// Measure the requested path rate.
			RatePath rateP = new RatePath(dataDirname, dataFilename);
			rateP.dbgDumpFields();
			ReturnPath returnP = rateP.getReturnCompounded();
			returnP.estimatePath();
			returnP.dbgDumpFields();
			double expectedReturnRate = returnP.get_expectedReturnRate();
			double volatility = returnP.get_volatility();
			//
			// Now prepare for MC runs.
			initAllTasks = new ToInitAllTasks(returnP, nTimeStepsMC,
					pathStartValue);
			String slaveClassName = "MonteCarlo.PriceStock";
			//
			// Now create the tasks.
			initTasks(nRunsMC);
			//
		} catch (DemoException demoEx) {
			dbgPrintln(demoEx.toString());
			System.exit(-1);
		}
	}

	public void runThread() {
		results = new Vector((int) (nRunsMC * 1.5));

		Runnable thobjects[] = new Runnable[JGFMonteCarloBench.nthreads];
		Thread th[] = new Thread[JGFMonteCarloBench.nthreads];

		for (int i = 1; i < JGFMonteCarloBench.nthreads; i++) {
			thobjects[i] = new AppDemoThread(i, nRunsMC, this);
			th[i] = new Thread(thobjects[i]);
			th[i].start();
		}

		thobjects[0] = new AppDemoThread(0, nRunsMC, this);
		thobjects[0].run();

		for (int i = 1; i < JGFMonteCarloBench.nthreads; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {
			}
		}

	}

	public void processSerial() {
		//
		// Process the results.
		try {
			processResults();
		} catch (DemoException demoEx) {
			dbgPrintln(demoEx.toString());
			System.exit(-1);
		}
	}

	// ------------------------------------------------------------------------
	/**
	 * Generates the parameters for the given Monte Carlo simulation.
	 * 
	 * @param nRunsMC
	 *            the number of tasks, and hence Monte Carlo paths to produce.
	 */
	private void initTasks(int nRunsMC) {
		tasks = new Vector(nRunsMC);
		for (int i = 0; i < nRunsMC; i++) {
			String header = "MC run " + String.valueOf(i);
			ToTask task = new ToTask(header, (long) i * 11);
			tasks.addElement((Object) task);
		}
	}

	/**
	 * Method for doing something with the Monte Carlo simulations. It's
	 * probably not mathematically correct, but shall take an average over all
	 * the simulated rate paths.
	 * 
	 * @exception DemoException
	 *                thrown if there is a problem with reading in any values.
	 */
	private void processResults() throws DemoException {
		double avgExpectedReturnRateMC = 0.0;
		double avgVolatilityMC = 0.0;
		double runAvgExpectedReturnRateMC = 0.0;
		double runAvgVolatilityMC = 0.0;
		ToResult returnMC;
		if (nRunsMC != results.size()) {
			errPrintln("Fatal: TaskRunner managed to finish with no all the results gathered in!");
			System.exit(-1);
		}
		//
		// Create an instance of a RatePath, for accumulating the results of the
		// Monte Carlo simulations.
		RatePath avgMCrate = new RatePath(nTimeStepsMC, "MC", 19990109,
				19991231, dTime);
		for (int i = 0; i < nRunsMC; i++) {
			// First, create an instance which is supposed to generate a
			// particularly simple MC path.
			returnMC = (ToResult) results.elementAt(i);
			avgMCrate.inc_pathValue(returnMC.get_pathValue());
			avgExpectedReturnRateMC += returnMC.get_expectedReturnRate();
			avgVolatilityMC += returnMC.get_volatility();
			runAvgExpectedReturnRateMC = avgExpectedReturnRateMC
					/ ((double) (i + 1));
			runAvgVolatilityMC = avgVolatilityMC / ((double) (i + 1));
		} // for i;
		avgMCrate.inc_pathValue((double) 1.0 / ((double) nRunsMC));
		avgExpectedReturnRateMC /= nRunsMC;
		avgVolatilityMC /= nRunsMC;
		/*
		 * try{ Thread.sleep(200); } catch( InterruptedException intEx) {
		 * errPrintln(intEx.toString()); }
		 */

		JGFavgExpectedReturnRateMC = avgExpectedReturnRateMC;

		// dbgPrintln("Average over "+nRunsMC+": expectedReturnRate="+
		// avgExpectedReturnRateMC+" volatility="+avgVolatilityMC +
		// JGFavgExpectedReturnRateMC);
	}

	//
	// ------------------------------------------------------------------------
	// Accessor methods for class AppDemo.
	// Generated by 'makeJavaAccessor.pl' script. HWY. 20th January 1999.
	// ------------------------------------------------------------------------
	/**
	 * Accessor method for private instance variable <code>dataDirname</code>.
	 * 
	 * @return Value of instance variable <code>dataDirname</code>.
	 */
	public String get_dataDirname() {
		return (this.dataDirname);
	}

	/**
	 * Set method for private instance variable <code>dataDirname</code>.
	 * 
	 * @param dataDirname
	 *            the value to set for the instance variable
	 *            <code>dataDirname</code>.
	 */
	public void set_dataDirname(String dataDirname) {
		this.dataDirname = dataDirname;
	}

	/**
	 * Accessor method for private instance variable <code>dataFilename</code>.
	 * 
	 * @return Value of instance variable <code>dataFilename</code>.
	 */
	public String get_dataFilename() {
		return (this.dataFilename);
	}

	/**
	 * Set method for private instance variable <code>dataFilename</code>.
	 * 
	 * @param dataFilename
	 *            the value to set for the instance variable
	 *            <code>dataFilename</code>.
	 */
	public void set_dataFilename(String dataFilename) {
		this.dataFilename = dataFilename;
	}

	/**
	 * Accessor method for private instance variable <code>nTimeStepsMC</code>.
	 * 
	 * @return Value of instance variable <code>nTimeStepsMC</code>.
	 */
	public int get_nTimeStepsMC() {
		return (this.nTimeStepsMC);
	}

	/**
	 * Set method for private instance variable <code>nTimeStepsMC</code>.
	 * 
	 * @param nTimeStepsMC
	 *            the value to set for the instance variable
	 *            <code>nTimeStepsMC</code>.
	 */
	public void set_nTimeStepsMC(int nTimeStepsMC) {
		this.nTimeStepsMC = nTimeStepsMC;
	}

	/**
	 * Accessor method for private instance variable <code>nRunsMC</code>.
	 * 
	 * @return Value of instance variable <code>nRunsMC</code>.
	 */
	public int get_nRunsMC() {
		return (this.nRunsMC);
	}

	/**
	 * Set method for private instance variable <code>nRunsMC</code>.
	 * 
	 * @param nRunsMC
	 *            the value to set for the instance variable
	 *            <code>nRunsMC</code>.
	 */
	public void set_nRunsMC(int nRunsMC) {
		this.nRunsMC = nRunsMC;
	}

	/**
	 * Accessor method for private instance variable <code>tasks</code>.
	 * 
	 * @return Value of instance variable <code>tasks</code>.
	 */
	public Vector get_tasks() {
		return (this.tasks);
	}

	/**
	 * Set method for private instance variable <code>tasks</code>.
	 * 
	 * @param tasks
	 *            the value to set for the instance variable <code>tasks</code>.
	 */
	public void set_tasks(Vector tasks) {
		this.tasks = tasks;
	}

	/**
	 * Accessor method for private instance variable <code>results</code>.
	 * 
	 * @return Value of instance variable <code>results</code>.
	 */
	public Vector get_results() {
		return (this.results);
	}

	/**
	 * Set method for private instance variable <code>results</code>.
	 * 
	 * @param results
	 *            the value to set for the instance variable
	 *            <code>results</code>.
	 */
	public void set_results(Vector results) {
		this.results = results;
	}
	// ------------------------------------------------------------------------
}

class AppDemoThread implements Runnable {

	int id, nRunsMC;
	AppDemoEco app;

	public AppDemoThread(int id, int nRunsMC, AppDemoEco app) {
		this.id = id;
		this.nRunsMC = nRunsMC;
		this.app = app;
	}

	public void run() {
		PriceStock ps;
		// Now do the computation.

		int ilow, iupper, slice;

		slice = (nRunsMC + JGFMonteCarloBench.nthreads - 1)
				/ JGFMonteCarloBench.nthreads;

		ilow = id * slice;
		iupper = (id + 1) * slice;
		if (id == JGFMonteCarloBench.nthreads - 1)
			iupper = nRunsMC;

		int tempLen = AppDemoEco.tasks.size();
		
		mcase<Double> adjust = {low: -0.1; high: 0; full: 0.1};
		
		sustainable{
			for (int iRun = ilow; iRun < iupper; iRun++) {
				ps = new PriceStock();
				ps.setInitAllTasks(AppDemoEco.initAllTasks);
				ps.setTask(AppDemoEco.tasks
						.elementAt(iRun % tempLen/* modified by Steve */));
				ps.run();
				AppDemoEco.results.addElement(ps.getResult());
	
				// check battery usage and make decision
				// if the left budget is > left work * 10%, then iupper *= 1.05
				if (iRun % 1000 == 0) {
					if (iRun % 4000 == 0) {
						iupper *= 1 + adjust;
					}
					app.nRunsMC = iupper;
				}
			}
		}
		bsupply (1234)
		demand (iupper) -> (iupper - iRun);
	}
}
