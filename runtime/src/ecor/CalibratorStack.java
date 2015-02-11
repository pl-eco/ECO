package ecor;

import java.util.Stack;

public class CalibratorStack {
	private static Stack<Calibrator> newStack() {
		Stack<Calibrator> stack = new Stack<Calibrator>();
		stack.push(new Calibrator () {
			@Override
			public int getMode(int max) {
				return max;
			}
			@Override
			public Object calibrate(Object input) {
				return input;
			} 
		  @Override
		  public void adjust() {
		  }
		  
		  @Override
		  public int supply() {
		    return 0;
		  }});
		
		return stack;
	}
	private static Stack<Calibrator> stack = newStack();
	public static void push(Calibrator calibrator) {
		stack.push(calibrator);
	}
	public static Calibrator peek() {
		return stack.peek();
	}
	public static void pop() {
		stack.pop();
	}
	public static Object calibrate(Object input) {
		Object object = stack.peek().calibrate(input);
		stack.peek().adjust();
		return object;
	}
	public static int getMode(int max) {
		return stack.peek().getMode(max);
	}
}
