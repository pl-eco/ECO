package work;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tools.Mode;


public class Work {
	/**
	 * Gets the amount of work necessary to generate an image from the scene file, given the mode
	 * @param file the scene (.sc) file
	 * @param mode the mode under which the program will be run
	 * @return the amount of work to be done (number of pixels in resulting image)
	 * @throws FileNotFoundException
	 */
	public static int getWorkAmount(File file, int mode) throws FileNotFoundException {
		Scanner scn = new Scanner(file);
		int width = 640, height = 480;
		while (scn.hasNext()) {
			if (scn.next().equals("resolution")) {
				width = scn.nextInt();
				height = scn.nextInt();
				break;
			}
		}
		switch (mode) {
		case 0:
			break;
		case 1:
			width = 5 * width / 4;
			height = 5 * height / 4;
			break;
		case 2:
			width = 8 * width / 5;
			height = 8 * height / 5;
			break;
		}

	    for (int i = 0; i < 3; i++) {
	    	width = MathUtils.clamp(width, 1, 1 << 14);
        	height = MathUtils.clamp(height, 1, 1 << 14);
	    }
		scn.close();
	    return width * height;
	}
}
