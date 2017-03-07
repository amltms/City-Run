package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	  private Player player;
	  private Bomb bomb;
	  private List<Node> cars = new ArrayList<>();
	  private Pane root;
	  private Node player1;
	  private AnimationTimer timer;
	  
	@Override
	//Set up stage
	public void start(Stage primaryStage) {
		try {
			
			player = new Player();
			bomb = new Bomb();
			
			primaryStage.setFullScreen(true);
			root = new Pane();
			player1 = player.createPlayer();
		    final Circle bomb1 = bomb.createBomb();
		    Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.getChildren().add(player1);
			root.getChildren().add(bomb1);
			
			timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	                onUpdate();
	            }
	        };
	        timer.start();

			
		    bomb.moveBombOnMousePress(scene, bomb1);
		    primaryStage.setScene(scene);

		    primaryStage.getScene().setOnKeyPressed(event -> {
	            switch (event.getCode()) {
                case W:
                    player1.setTranslateY(player1.getTranslateY() - 40);
                    break;
                case S:
                	player1.setTranslateY(player1.getTranslateY() + 40);
                    break;
                case A:
                	player1.setTranslateX(player1.getTranslateX() - 40);
                    break;
                case D:
                	player1.setTranslateX(player1.getTranslateX() + 40);
                    break;
                default:
                    break;
	            }
		    });
		    
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

    private Node spawnCar() {
        Rectangle rect = new Rectangle(80, 130, Color.RED);
        rect.setTranslateX((int)(Math.random() * 3) * 80);

        root.getChildren().add(rect);
        return rect;
    }

    private void onUpdate() {
        for (Node car : cars)
            car.setTranslateY(car.getTranslateY() + Math.random() * 10);

        if (Math.random() < 0.015) {
            cars.add(spawnCar());
        }

        checkState();
    }

    private void checkState() {
        for (Node car : cars) {
            if (car.getBoundsInParent().intersects(player1.getBoundsInParent())) {
            	timer.stop();
                String lose = "You lose.";

                HBox hBox = new HBox();
                hBox.setTranslateX(300);
                hBox.setTranslateY(250);
                root.getChildren().add(hBox);

                for (int i = 0; i < lose.toCharArray().length; i++) {
                    char letter = lose.charAt(i);

                    Text text = new Text(String.valueOf(letter));
                    text.setFont(Font.font(48));
                    text.setOpacity(0);

                    hBox.getChildren().add(text);

                    FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                    ft.setToValue(1);
                    ft.setDelay(Duration.seconds(i * 0.15));
                    ft.play();
                }
                
                player1.setTranslateX(0);
                player1.setTranslateY(600 - 39);
                return;
            }
        }
    }
	

	
	public static void main(String[] args) {
		launch(args);
	}

}

