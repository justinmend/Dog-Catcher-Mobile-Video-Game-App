/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class ExpandCommand extends Command implements ActionListener, ICommand {
	private GameWorld target;
	private static ExpandCommand locInstance;

	ExpandCommand() {
		super("Expand Net");
	}

	public void actionPerformed(ActionEvent evt) {
		target.expand();
	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}

	public static ExpandCommand getInstance() {
		if (locInstance == null) {
			locInstance = new ExpandCommand();
		}
		return (ExpandCommand) locInstance;
	}

}
