/*Justin Mendiguarin*/

package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class ScoopCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static ScoopCommand locInstance;
	
	private ScoopCommand(){
		super("Scoop");
	}
	
	public void actionPerformed(ActionEvent evt) {
		target.scoop();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static ScoopCommand getInstance(){
		if(locInstance == null){
			locInstance = new ScoopCommand();
		}
		return (ScoopCommand)locInstance;
	}	
}
