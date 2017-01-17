/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class DownCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static DownCommand locInstance;
	
	private DownCommand(){super("Net Move Down");}

	
	public void actionPerformed(ActionEvent evt) {
		target.moveDown();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static DownCommand getInstance(){
		if(locInstance == null){
			locInstance = new DownCommand();
		}
		return (DownCommand)locInstance;
	}

}
