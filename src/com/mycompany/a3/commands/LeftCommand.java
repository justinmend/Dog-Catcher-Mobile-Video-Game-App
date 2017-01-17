/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class LeftCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static LeftCommand locInstance;
	
	private LeftCommand(){super("Move net Left");}

	
	public void actionPerformed(ActionEvent evt) {
		target.moveLeft();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static LeftCommand getInstance(){
		if(locInstance == null){
			locInstance = new LeftCommand();
		}
		return (LeftCommand)locInstance;
	}

}
