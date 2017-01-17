/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class UpCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static UpCommand locInstance;
	
	private UpCommand(){
		super("Move Up");
	}

	
	public void actionPerformed(ActionEvent evt) {
		target.moveUp();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static UpCommand getInstance(){
		if(locInstance == null){
			locInstance = new UpCommand();
		}
		return (UpCommand)locInstance;
	}

}
