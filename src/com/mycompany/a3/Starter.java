/*Justin Mendiguarin
 *Assignment 1
 *4/26/16
 */

package com.mycompany.a3;

import java.io.IOException;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

//This class is a driver for running the Game class. It creates a Form.
//When you create your CN1 project, you should name the main class as Starter.
public class Starter {
	private Form current;
	@SuppressWarnings("unused")
	private Resources theme;

	public void init(Object context) {
		theme = UIManager.initFirstTheme("/theme");
	}

	// *modify the start() method of the Starter class
	// *so that it would construct an instance of the Game class.
	public void start() throws IOException {
		if (current != null) {
			current.show();
			return;
		}

		new Game();

	}

	public void stop() {
		current = Display.getInstance().getCurrent();
	}

	public void destroy() {
	}

}
