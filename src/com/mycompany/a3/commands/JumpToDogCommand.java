/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class JumpToDogCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static JumpToDogCommand locInstance;
	
	private JumpToDogCommand(){
		super("Jump-to-Dog");
	}

	
	public void actionPerformed(ActionEvent evt) {
		target.jumpToDog();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static JumpToDogCommand getInstance(){
		if(locInstance == null){
			locInstance = new JumpToDogCommand();
		}
		return (JumpToDogCommand)locInstance;
	}
	
}
