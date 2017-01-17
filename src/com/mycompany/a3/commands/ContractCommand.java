/*Justin Mendiguarin*/
package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ICommand;

public class ContractCommand extends Command implements ActionListener, ICommand{
	private GameWorld target;
	private static ContractCommand locInstance;
	
	private ContractCommand(){
		super("Contract Net");
	}
	
	public void actionPerformed(ActionEvent evt) {
		target.contract();	
	}
	
	public void setTarget(GameWorld gw){
		target = gw;
	}
	public static ContractCommand getInstance(){
		if(locInstance == null){
			locInstance = new ContractCommand();
		}
		return (ContractCommand)locInstance;
	}	
}
