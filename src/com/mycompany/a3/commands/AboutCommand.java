/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class AboutCommand extends Command implements ActionListener, ICommand {
	private GameWorld target;
	private static AboutCommand locInstance;

	private AboutCommand() {
		super("About");
	}

	public void actionPerformed(ActionEvent evt) {
	//target.showAbout();
		//System.out.println("About command is invoked...");
		String Message = ("Justin Mendiguarin, CSC 133, Assignment #3");
		Dialog.show("About", Message, "Ok", null);
	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}

	public static AboutCommand getInstance() {
		if (locInstance == null) {
			locInstance = new AboutCommand();
		}
		return (AboutCommand) locInstance;
	}

}
