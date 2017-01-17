/*Justin Mendiguarin*/
package com.mycompany.a3;

public abstract class Catchers extends GameObject implements IGuided {
	public Catchers(GameWorld gw) {
		super(gw);
	}

	public Catchers(GameWorld gw, int inSize) {
		super(gw, inSize);

	}

	public Catchers() {
	}

	public void jumpToCat(Cats cat) {
		setLocation(cat.getXLocation(), cat.getYLocation());
	}

	public void jumpToDog(Dogs dog) {
		setLocation(dog.getXLocation(), dog.getYLocation());
	}

	public void moveUp() {
		float localX = getXLocation();
		float localY = getYLocation();
		if (localY < super.getHeightBound() - (getSize() / 2)) {
			setLocation(localX, localY + 1);
		}
	}

	public void moveDown() {
		float localX = getXLocation();
		float localY = getYLocation();
		if (localY > (getSize() / 2)) {
			setLocation(localX, localY - 1);
		}
	}

	public void moveRight() {
		float localX = getXLocation();
		float localY = getYLocation();
		if (localX < super.getWidthBound() - (getSize() / 2)) {
			setLocation(localX + 1, localY);
		}
	}

	public void moveLeft() {
		float localX = getXLocation();
		float localY = getYLocation();
		if (localX > (getSize() / 2)) {
			setLocation(localX - 1, localY);
		}
	}

}