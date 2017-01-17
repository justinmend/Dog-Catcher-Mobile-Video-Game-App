/*Justin Mendiguarin*/
package com.mycompany.a3;

//Some game objects are moving, meaning that they provide an interface that allows other objects to tell them to move. 
//Telling a moving object to move() causes the object to update its location. 
//All animals are moving and they all move the same way.

public interface IMoving {
	public void move(int elapsedTime);

}
