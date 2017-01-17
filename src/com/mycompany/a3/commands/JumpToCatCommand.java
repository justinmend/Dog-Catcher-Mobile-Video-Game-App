/*Justin Mendiguarin*/

package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class JumpToCatCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static JumpToCatCommand locInstance;
	
	private JumpToCatCommand(){
		super("Jump-To-Cat");
	}

	
	public void actionPerformed(ActionEvent evt) {
		target.jumpToCat();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static JumpToCatCommand getInstance(){
		if(locInstance == null){
			locInstance = new JumpToCatCommand();
		}
		return (JumpToCatCommand)locInstance;
	}
	
	
}
