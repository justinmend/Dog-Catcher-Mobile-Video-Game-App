/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class RightCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static RightCommand locInstance;
	
	private RightCommand(){super("Move Net Right");}

	
	public void actionPerformed(ActionEvent evt) {
		target.moveRight();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static RightCommand getInstance(){
		if(locInstance == null){
			locInstance = new RightCommand();
		}
		return (RightCommand)locInstance;
	}

}
