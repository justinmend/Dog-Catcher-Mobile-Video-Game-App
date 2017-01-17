/*Justin Mendiguarin*/
package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

/* 
 * GameWorld holds a collection of game objects and
 * other state variables, and a set of methods to accept
 * and execute user commands.
 */
public class GameWorld extends Observable {

	private int dogsCaptured, catsCaptured, dogsRemaining, catsRemaining,
			initNets = 1, initCats = 3, initDogs = 3;;
	private Nets net;
	private Dogs dog;
	private Cats cat;
	private final int initNetSize = 100;
	private int currentScore, maxPossibleScore;
	private int count = 0;

	GameObjectCollection objects;
	int GW_WIDTH, GW_HEIGHT, mvAbsY, mvAbsX, scoreY;

	private int bottomY;
	private Sound catSound, netSound;
	private BGSound bgSound = new BGSound("jazzyfrenchy.wav");
	private boolean sound = true;

	Random random = new Random();
	private GameWorld gw;

	GameWorld() {
		objects = new GameObjectCollection();
		dog = new Dogs();
		net = new Nets();
		cat = new Cats();
		maxPossibleScore = initDogs * 10;

		catSound = new Sound("Cat.wav");
		netSound = new Sound("swoosh.wav");
		bgSound.play();
	}

	public void initLayout() {
		catsRemaining = initCats;
		dogsRemaining = initDogs;
		buildDogs(initDogs);
		buildCats(initCats);
		buildNet(initNets);
	}

	public void setGWSize(int mvAbsX, int mvAbsY, int mvWidth, int mvHeight,
			int scoreY, int bottomY) {
		this.mvAbsY = mvAbsY;
		this.mvAbsX = mvAbsX;
		GW_WIDTH = mvWidth;
		GW_HEIGHT = mvHeight;
		this.scoreY = scoreY;
		this.bottomY = bottomY;

		setChanged();
		notifyObservers();
	}

	public int getBottomHeight() {
		return this.bottomY;
	}

	private void buildNet(int amountToBuild) {
		if (amountToBuild > 0) {
			for (int i = 0; i < amountToBuild; i++) {
				net = new Nets(this, initNetSize);
				objects.add(net);
			}
		}

	}

	private void buildDogs(int amountToBuild) {
		if (amountToBuild > 0) {
			for (int i = 0; i < amountToBuild; i++) {
				dog = new Dogs(this, getRandIntBetween(20, 50));
				objects.add(dog);
			}
		}
	}

	private void buildCats(int amountToBuild) {
		if (amountToBuild > 0) {
			for (int i = 0; i < amountToBuild; i++) {
				cat = new Cats(this, getRandIntBetween(20, 50));
				objects.add(cat);
			}
		}
	}

	public void kitten(Cats initCat) {
		cat = new Cats(initCat, this);
		objects.add(cat);
		catsRemaining++;

	}

	public void kitten(Cats initCat, GameWorld gw) {
		cat = new Cats(initCat, gw);
		objects.add(cat);
		catsRemaining++;
	}

	void kitten() {
		cat = new Cats((Cats) objects.getRandomGameObject("CAT"), this);
		objects.add(cat);
		catsRemaining++;

	}

	public int getMapHeight() {
		return GW_HEIGHT;
	}

	public int getMapWidth() {
		return GW_WIDTH;
	}

	public int getgwHeight() {
		return GW_HEIGHT;
	}

	public int getgwWidth() {
		return GW_WIDTH;
	}

	public int getAbsY() {
		return this.mvAbsY;
	}

	public int getAbsX() {
		return this.mvAbsX;
	}

	public int getScoreY() {
		return this.scoreY;
	}

	public int getmvAbsY() {
		return this.mvAbsY;
	}

	public int getmvAbsX() {
		return this.mvAbsX;
	}

	public boolean checkSound() {
		return this.sound;
	}

	public void toggleSound() {

		if (sound) {
			sound = false;
			bgSound.pause();
			// System.out.println(sound);
			System.out.println("Sound off");
		} else {
			sound = true;
			bgSound.play();
			// System.out.println(sound);
			System.out.println("Sound on");
		}
		setChanged();
		notifyObservers();
	}

	public void setSound(boolean set) {
		this.sound = set;

		setChanged();
		notifyObservers();
	}

	public void expand() {
		if (net.getSize() < 100) {
			net.setSize(net.getSize() + 1);
			setChanged();
			notifyObservers();
		} else {
			System.out.println("Cannot Expand Net, too large");
		}
	}

	public void contract() {
		if (net.getSize() > 50) {
			net.setSize(net.getSize() - 1);
			setChanged();
			notifyObservers();
		} else {
			System.out.println("Cannot Contract Net, too small");
		}
	}

	public void jumpToDog() {
		if (dogsRemaining > 0) {
			net.jumpToDog((Dogs) objects.getRandomGameObject("DOG"));
			setChanged();
			notifyObservers();
		} else if (dogsRemaining < 1) {
			System.out.println("ERROR: There are no dogs to jump net to");
		}
	}

	public void jumpToCat() {
		if (catsRemaining > 0) {
			net.jumpToCat((Cats) objects.getRandomGameObject("CAT"));
			setChanged();
			notifyObservers();
		}
		if (catsRemaining < 1) {
			System.out.println("ERROR: There are no cats to jump net to");
		}

	}

	public IIterator getGameObjIterator() {
		return objects.getIterator();
	}

	public void scoop() {
		GameObject loc = null;
		IIterator scoopIterator = objects.getIterator();
		while (scoopIterator.hasNext()) {
			loc = scoopIterator.getNext();
			if (otherScoop(loc)) {
				if (loc instanceof Cats) {
					catsCaptured((Cats) loc);
				} else if (loc instanceof Dogs) {
					dogsCaptured((Dogs) loc);
				}
				if (this.checkSound()) {
					netSound.play();
				}

			}
		}
		setChanged();
		notifyObservers();
		printMap();
	}

	private boolean otherScoop(GameObject go) {
		boolean scoopBool = false;
		if ((go instanceof Animals)) {
			if ((go.getXLocation() >= net.getXLocation() - (net.getSize() / 2))
					&& (go.getXLocation() <= net.getXLocation()
							+ (net.getSize() / 2))
					&& (go.getYLocation() <= net.getYLocation()
							+ (net.getSize() / 2))
					&& (go.getYLocation() >= net.getYLocation()
							- (net.getSize() / 2))) {
				scoopBool = true;
			}
		}
		return scoopBool;
	}

	private void catsCaptured(Cats i) {
		currentScore -= 10;
		objects.remove(i);
		catsRemaining--;
		catsCaptured++;
		setChanged();
		notifyObservers();
	}

	private void dogsCaptured(Dogs i) {
		currentScore += 10 - i.getScratches();
		objects.remove(i);
		dogsRemaining--;
		dogsCaptured++;
		if (dogsRemaining < 1) {
			this.gameOver();
		}
		setChanged();
		notifyObservers();
	}

	// FIX QUIT BUTTON - MAKE QUIT TEXT BIGGER!
	public void gameOver() {

		final Dialog dlg = new Dialog("Final Score");
		// dlg.getAllStyles().setBorder(Border.createLineBorder(3,
		// ColorUtil.BLUE));
		dlg.setLayout(new GridLayout(1, 1));
		Button quitButton = new Button("QUIT");
		quitButton.getAllStyles().setBorder(
				Border.createLineBorder(3, ColorUtil.GRAY));
		quitButton.getAllStyles().setBgTransparency(255);
		quitButton.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		quitButton.getAllStyles().setFgColor(ColorUtil.BLUE);
		// quitButton.getAllStyles().setMargin(120, 120, 50, 50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dlg.dispose();
				Display.getInstance().exitApplication();
			}
		});
		dlg.add(new SpanLabel(this.getScore(), "DialogBody"));
		dlg.add(quitButton);

		dlg.setDisposeWhenPointerOutOfBounds(true);
		dlg.show();
	}

	public void moveLeft() {
		net.moveLeft();
		setChanged();
		notifyObservers();
	}

	public void moveDown() {
		net.moveDown();
		setChanged();
		notifyObservers();
	}

	public void moveRight() {
		net.moveRight();
		setChanged();
		notifyObservers();
	}

	public void moveUp() {
		net.moveUp();
		setChanged();
		notifyObservers();
	}

	public void catDogfight() {
		if (catsRemaining < 1) {
		} else if (dogsRemaining > 0) {
			((Dogs) (objects.getRandomGameObject("DOG"))).scratchThisDog();
			setChanged();
			notifyObservers();
		}
	}

	/*
	 * print a map showing the current world state.
	 */
	public void printMap() {
		GameObject loc = null;
		IIterator scoopI = objects.getIterator();

		System.out.println("				MAP");
		while (scoopI.hasNext()) {
			loc = scoopI.getNext();
			if ((loc instanceof Animals)) {
				if (loc instanceof Dogs) {
					System.out.println(((Dogs) (loc)).toString());
				} else if (loc instanceof Cats) {
					System.out.println(((Cats) (loc)).toString());
				}
			} else if ((loc instanceof Nets)) {
				System.out.println(((Nets) (loc)).toString());
			}
		}
	}

	public void catFight() {
		if (catsRemaining > 1) {
			kitten();
			setChanged();
			notifyObservers();
		} else {
		}
	}

	public int getRandIntBetween(int min, int max) {
		int r = random.nextInt(max - min) + min;
		return r;
	}

	public void tick() {
		GameObject loc = null;
		IIterator moveIterator = objects.getIterator();

		while (moveIterator.hasNext()) {
			loc = moveIterator.getNext();
			if (loc instanceof IMoving) {
				((Animals) loc).move(20);

				setChanged();
				notifyObservers();
			}
		}

		IIterator tickIterator = objects.getIterator();
		while (tickIterator.hasNext()) {
			ICollider obj1 = (ICollider) tickIterator.getNext();
			IIterator tickI2 = objects.getIterator();
			while (tickI2.hasNext()) {
				ICollider obj2 = (ICollider) tickI2.getNext();
				if (obj2 != obj1 && ((obj1 instanceof Animals) == true)
						&& ((obj2 instanceof Animals) == true)) {
					if (obj1.collidesWith(obj2)) {
						if (obj1 instanceof Cats && obj2 instanceof Cats) {
							count++;
							if (count > 1) {
								count = 0;
								obj1.handleCollision(obj2);
								if (catsRemaining < 30) {
									if (this.checkSound()) {
										catSound.play();
									}
								}

							}

						} else if (obj1 instanceof Dogs && obj2 instanceof Cats) {
							obj1.handleCollision(obj2);
							if (this.checkSound()) {
							}
						}
					}
				}
			}
		}
		setChanged();
		notifyObservers();

	}

	public void heal() {
		IIterator healIterator = objects.getIterator();
		while (healIterator.hasNext()) {
			GameObject object = healIterator.getNext();
			if (object instanceof Dogs && ((Dogs) object).isSelected()) {
				Dogs locDog = (Dogs) object;
				locDog.healThisDog();
				setChanged();
				notifyObservers();
			}
		}
	}

	public void quit() {
		System.exit(0);
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public int getDogsCaptured() {
		return dogsCaptured;
	}

	public int getCatsCaptured() {
		return catsCaptured;
	}

	public int getDogsRemaining() {
		return dogsRemaining;
	}

	public int getCatsRemaining() {
		return catsRemaining;
	}

	public void printPoints() {
		if (currentScore > maxPossibleScore) {
			System.out.println("Current Score: " + maxPossibleScore
					+ "  Dogs Captured: " + dogsCaptured + "  Cats Captured: "
					+ catsCaptured + "  Dogs Remaining: " + dogsRemaining
					+ "  Cats Remaining: " + catsRemaining);
		} else {
			System.out.println("Current-Score: " + currentScore
					+ "  Dogs Captured: " + dogsCaptured + "  Cats Captured: "
					+ catsCaptured + "  Dogs Remaining: " + dogsRemaining
					+ "  Cats Remaining: " + catsRemaining);
		}
	}

	public String getScore() {
		if (currentScore > maxPossibleScore) {
			return "Final Score: " + maxPossibleScore + "  Dogs Captured: "
					+ dogsCaptured + "  Cats Captured: " + catsCaptured
					+ "  Dogs Remaining: " + dogsRemaining
					+ "  Cats Remaining: " + catsRemaining;
		} else {
			return "Final Score: " + currentScore + "  Dogs Captured: "
					+ dogsCaptured + "  Cats Captured: " + catsCaptured
					+ "  Dogs Remaining: " + dogsRemaining
					+ "  Cats Remaining: " + catsRemaining;
		}
	}

}