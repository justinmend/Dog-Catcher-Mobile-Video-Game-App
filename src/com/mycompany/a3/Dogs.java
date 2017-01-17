/*Justin Mendiguarin*/
package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Dogs extends Animals implements ICollider, IDrawable, ISelectable {
	// Attributes
	private int scratches;
	private boolean selectedBool = false;
	private Random random = new Random();
	private GameWorld gw;

	// private int drawCount;
	// Constructors
	public Dogs(GameWorld gw) {
		super(gw);
		this.gw = gw;
		super.setSpeed(5);
		this.setSize(getRandIntBetween(20, 50));
		setColor(ColorHelper.getRed(1));
		scratches = 0;
	}

	public Dogs(GameWorld gw, int inSize) {
		super(gw, inSize);
		this.gw = gw;
		super.setSpeed(5);
		setColor(ColorHelper.getRed(1));
		scratches = 0;
	}

	public Dogs() {
		super();
		this.setSize(getRandIntBetween(20, 50));
		setColor(ColorHelper.getRed(1));
		scratches = 0;
		setSpeed(5);

	}

	// Methods
	@Override
	public void handleCollision(ICollider collidedIn) {
		Animals tempCat = null;
		if (collidedIn instanceof Cats) {

			tempCat = (Cats) collidedIn;
			if (tempCat.containsCollided(this)
					|| this.containsCollided(tempCat)) {

			}

			else {
				tempCat.addToCollidedList(this);
				this.addToCollidedList(tempCat);

				this.scratchThisDog();

				tempCat.removeFromCollidedList(this);
				this.removeFromCollidedList(tempCat);
			}
		}

	}

	@Override
	public boolean collidesWith(ICollider collidedIn) {
		boolean collidedBool = false;
		GameObject collidedObj = (GameObject) collidedIn;

		float thisRad = this.getSize() / 2;
		float collidedRad = collidedObj.getSize() / 2;

		float dx = (this.getXLocation() + thisRad)
				- (collidedObj.getXLocation() + collidedRad);

		float dy = (this.getYLocation() + thisRad)
				- (collidedObj.getYLocation() + collidedRad);
		int sqRad = (int) (thisRad * thisRad + 2 * thisRad * collidedRad + collidedRad
				* collidedRad);
		float dist = (dx * dx + dy * dy);
		if (dist <= sqRad) {
			collidedBool = true;
		}
		return collidedBool;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		int locX = (int) (pCmpRelPrnt.getX() + this.getXLocation() - this
				.getSize() / 2);
		int locY = (int) (pCmpRelPrnt.getY() + this.getYLocation() - this
				.getSize() / 2);
		if (this.isSelected()) {
			g.drawArc(locX, locY, this.getSize(), this.getSize(), 0, 360);
		} else {
			g.setColor(this.getColor());
			g.fillArc(locX, locY, this.getSize(), this.getSize(), 0, 360);
		}
	}

	public void healThisDog() {
		this.setScratches(0);
		this.setSpeed(5);
		this.setColor(ColorHelper.getRed(1));
		this.setSelected(false);
	}

	public void setSelected(boolean s) {
		selectedBool = s;
	}

	public boolean isSelected() {
		return selectedBool;
	}

	public boolean contains(Point pPtr, Point pCmp) {
		boolean locBool = false;
		int pX = pPtr.getX();
		int pY = pPtr.getY();

		int locX = (int) this.getXLocation();
		int locY = (int) this.getYLocation();
		if ((pX >= locX - getSize() / 2) && (pX <= locX + this.getSize() / 2)
				&& (pY <= locY + getSize() / 2) && (pY >= locY - getSize() / 2)) {

			locBool = true;
			System.out.println("contains == true");
		} else {
			locBool = false;
			System.out.println("contains == false");
		}
		return locBool;
	}

	public int getRandIntBetween(int min, int max) {
		int r = random.nextInt(max - min) + min;
		return r;
	}

	public int getScratches() {
		return this.scratches;
	}

	public void setScratches(int scratch) {
		this.scratches = scratch;
	}

	public void scratchThisDog() {
		if (scratches < 5) {
			this.scratches++;
			if (gw.checkSound()) {
				(new Sound("DogWoof.wav")).play();
			}

		} else {
			scratches = 5;
		}
		decreaseDogSpeed();
		switch (scratches) {
		case 1:
			setColor(ColorHelper.getRed(1));
			break;
		case 2:
			setColor(ColorHelper.getRed(2));
			break;
		case 3:
			setColor(ColorHelper.getRed(3));
			break;
		case 4:
			setColor(ColorHelper.getRed(4));
			break;
		case 5:
			setColor(ColorHelper.getRed(5));
			break;
		default:
			setColor(ColorHelper.getRed(5));
			break;
		}
	}

	@Override
	public String toString() {
		String locationString = Math.round(getXLocation() * 100.0) / 100.0
				+ ", " + Math.round(getYLocation() * 100.0) / 100.0;

		String colorString = "[" + ColorUtil.red(getColor()) + ","
				+ ColorUtil.green(getColor()) + ","
				+ ColorUtil.blue(getColor()) + "]";

		return ("DOG; Location: " + locationString + " Color: " + colorString
				+ " Size: " + getSize() + " Speed: " + getSpeed()
				+ " Direction: " + getDirection() + " Scratches: " + getScratches());
	}

	private void decreaseDogSpeed() {
		int locSpeed = getSpeed();
		if (locSpeed > 0) {
			locSpeed--;
			setSpeed(locSpeed);
		} else if (locSpeed <= 0) {
			setSpeed(0);
		}
		if (gw.checkSound()) {
		}
	}

}
