/*Justin Mendiguarin*/
package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
//extends Form
public class ScoreView extends Container implements Observer{
    private Label totalScore, dogsCaptured, catsCaptured, dogsRemaining, catsRemaining, sound;
    //private String soundString;
    private GameWorld targetWorld;	
    ScoreView() {

		this.setLayout(new FlowLayout(Component.CENTER));
		Label totalScoreText = new Label("Total Score:");
		totalScoreText.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(totalScoreText);
		totalScore = new Label();
		totalScore.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(totalScore);
		dogsCaptured = new Label();
		dogsCaptured.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(dogsCaptured);
		catsCaptured = new Label();
		catsCaptured.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(catsCaptured);
		dogsRemaining = new Label();
		dogsRemaining.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(dogsRemaining);
		catsRemaining = new Label();
		catsRemaining.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(catsRemaining);
		sound = new Label();
		sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		this.add(sound);

		this.setVisible(true);
	}

	public void update(Observable observable, Object data) {
		totalScore.setText(((GameWorld) observable).getCurrentScore() + "   ");
		dogsCaptured.setText("Dogs Captured:    " + ((GameWorld) observable).getDogsCaptured());
		catsCaptured.setText("Cats Captured:    " + ((GameWorld) observable).getCatsCaptured());
		dogsRemaining.setText("Dogs Remaining:    " + ((GameWorld) observable).getDogsRemaining());
		catsRemaining.setText("Cats Remaining:    " + ((GameWorld) observable).getCatsRemaining());

		if (((GameWorld) observable).checkSound()) {
			sound.setText("Sound:   ON");
		} else {
			sound.setText("Sound:   OFF");
		}

		this.setVisible(true);
	}
	
	
	
	
	public void setTargetWorld(GameWorld gw){
		if(gw != null){targetWorld = gw;}
		else{System.out.println("Given target world is null");}
	}
	

}
