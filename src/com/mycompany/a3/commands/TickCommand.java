/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class TickCommand extends Command implements ActionListener, ICommand {
	private GameWorld target;
	private static TickCommand locInstance;

	private TickCommand() {
		super("Tick");
	}

	public void actionPerformed(ActionEvent evt) {
		target.tick();
	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}

	public static TickCommand getInstance() {
		if (locInstance == null) {
			locInstance = new TickCommand();
		}
		return (TickCommand) locInstance;
	}
}
