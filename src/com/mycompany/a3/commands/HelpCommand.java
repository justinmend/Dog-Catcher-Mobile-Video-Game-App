/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class HelpCommand extends Command implements ActionListener, ICommand {
	private GameWorld target;
	private static HelpCommand locInstance;

//	private HelpCommand() {
//		super("Help?");
//	}

//	public void actionPerformed(ActionEvent evt) {
//		target.showHelp();
//	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}

	public static HelpCommand getInstance() {
		if (locInstance == null) {
			locInstance = new HelpCommand();
		}
		return (HelpCommand) locInstance;
	}

	public HelpCommand() {
		super("Help?"); // do not forget to call parent constructor with
						// command_name
	}

	@Override
	// do not forget @Override, makes sure you are overriding parent method
	// invoked to perform the 'help' operation
	public void actionPerformed(ActionEvent ev) {
		//System.out.println("Help command is invoked...");
		String Message = "COMMAND KEYS: e, c, s, r, l, u, d, o, a, k, f, t, x";
		Dialog.show("Help?", Message, "Ok", null);

	}
	
}
