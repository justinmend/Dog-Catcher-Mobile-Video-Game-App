/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class KittenCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static KittenCommand locInstance;
	
	private KittenCommand(){
		super("Kitten");
	}

	
	public void actionPerformed(ActionEvent evt) {
		target.catFight();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static KittenCommand getInstance(){
		if(locInstance == null){
			locInstance = new KittenCommand();
		}
		return (KittenCommand)locInstance;
	}
	
}
