/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class ExitCommand extends Command implements ActionListener, ICommand {
	private GameWorld target;
	private static ExitCommand locInstance;

//	private ExitCommand() {
//		super("Exit");
//	}
//
//	public void actionPerformed(ActionEvent evt) {
//		target.exitApp();
//	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}

	public static ExitCommand getInstance() {
		if (locInstance == null) {
			locInstance = new ExitCommand();
		}
		return (ExitCommand) locInstance;
	}

	public ExitCommand() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		//System.out.println("Exit command is invoked...");
		Boolean bOK = Dialog.show("Quit", "Are you sure you want to quit the game?", "OK", "Cancel");
		if (bOK) {
			Display.getInstance().exitApplication();
		}
	}
	
}
