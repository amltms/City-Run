package application;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Player {

	

	
    public Node createPlayer() {
        Rectangle rect = new Rectangle(38, 38, Color.BLUE);
        rect.setTranslateY(800 - 39);

        return rect;
    }
  

	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
