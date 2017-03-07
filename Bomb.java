package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bomb {
	
	//createBomb method to create bomb as a circle
	public Circle createBomb(){
		final Circle bomb = new Circle(200, 150, 50, Color.RED);
		return bomb;
	}
	
	//moveBombOnMousePress method to place bomb at coordinates of where user clicks
	public void moveBombOnMousePress(Scene scene, final Circle bomb) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
		          bomb.setCenterX(event.getSceneX());
		          bomb.setCenterY(event.getSceneY());
		     }
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
