package tool;

import java.util.HashMap;
import java.util.Map;

public class WorkCounter {
	public static Map<String, Integer> workCount = new HashMap<String, Integer>();
	
	public static int getTotal(){
		int total = 0;
		for (int value : WorkCounter.workCount.values()) {
			total += value;
		}
		return total;
	}
	
	static{
		workCount.put("3D.svg", 27);
		workCount.put("anne.svg", 23);
		workCount.put("asf-logo.svg", 23);
		workCount.put("barChart.svg", 24);
		workCount.put("batik3D.svg", 28);
		workCount.put("batik70.svg", 39);
		workCount.put("batikCandy.svg", 31);
		workCount.put("batikFX.svg", 92);
		workCount.put("batikLogo.svg", 24);
		workCount.put("batikYin.svg", 29);
		workCount.put("chessboard.svg", 23);
		workCount.put("chessFont.svg", 24);
		workCount.put("gradients.svg", 24);
		workCount.put("GVT.svg", 23);
		workCount.put("henryV.svg", 42);
		workCount.put("logoShadowOffset.svg", 24);
		workCount.put("logoTexture.svg", 27);
		workCount.put("mapSpain.svg", 26);
		workCount.put("mapWaadt.svg", 26);
		workCount.put("mines.svg", 24);
		workCount.put("moonPhases.svg", 27);
		workCount.put("sizeOfSun.svg", 28);
		workCount.put("starfield.svg", 29);
		workCount.put("strokeFont.svg", 24);
		workCount.put("sydney.svg", 30);
		workCount.put("textRotate.svg", 28);
		workCount.put("textRotateShadows.svg", 30);
		
	}
}
