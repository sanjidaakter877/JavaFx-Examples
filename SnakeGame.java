import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends Application {

    private static final int BLOCK_SIZE = 20;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private LinkedList<Rectangle> snake = new LinkedList<>();
    private KeyCode direction = KeyCode.RIGHT; // initial direction
    private boolean moved = false; // prevent reverse movement in one frame
    private boolean running = false;

    private Rectangle food;
    private Pane root;

    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        // Initialize snake
        Rectangle head = new Rectangle(BLOCK_SIZE, BLOCK_SIZE, Color.GREEN);
        head.setX(WIDTH / 2);
        head.setY(HEIGHT / 2);
        snake.add(head);
        root.getChildren().add(head);

        spawnFood();

        timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        Scene scene = new Scene(root);

        // Keyboard input
        scene.setOnKeyPressed(event -> {
            if (!moved) return; // prevent multiple moves in one frame
            KeyCode code = event.getCode();
            switch (code) {
                case UP -> { if (direction != KeyCode.DOWN) direction = KeyCode.UP; }
                case DOWN -> { if (direction != KeyCode.UP) direction = KeyCode.DOWN; }
                case LEFT -> { if (direction != KeyCode.RIGHT) direction = KeyCode.LEFT; }
                case RIGHT -> { if (direction != KeyCode.LEFT) direction = KeyCode.RIGHT; }
            }
            moved = false;
        });

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        running = true;
        timeline.play();
    }

    private void run() {
        if (!running) return;

        moved = true;

        // Move snake
        Rectangle head = snake.getFirst();
        double x = head.getX();
        double y = head.getY();

        switch (direction) {
            case UP -> y -= BLOCK_SIZE;
            case DOWN -> y += BLOCK_SIZE;
            case LEFT -> x -= BLOCK_SIZE;
            case RIGHT -> x += BLOCK_SIZE;
        }

        // Check collisions with walls
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            stopGame();
            return;
        }

        // Move body
        Rectangle newHead = new Rectangle(BLOCK_SIZE, BLOCK_SIZE, Color.GREEN);
        newHead.setX(x);
        newHead.setY(y);

        // Check collisions with itself
        for (Rectangle r : snake) {
            if (r.getX() == newHead.getX() && r.getY() == newHead.getY()) {
                stopGame();
                return;
            }
        }

        snake.addFirst(newHead);
        root.getChildren().add(newHead);

        // Check if food eaten
        if (newHead.getX() == food.getX() && newHead.getY() == food.getY()) {
            spawnFood();
        } else {
            Rectangle tail = snake.removeLast();
            root.getChildren().remove(tail);
        }
    }

    private void spawnFood() {
        if (food != null) root.getChildren().remove(food);

        Random rand = new Random();
        int x = rand.nextInt(WIDTH / BLOCK_SIZE) * BLOCK_SIZE;
        int y = rand.nextInt(HEIGHT / BLOCK_SIZE) * BLOCK_SIZE;

        food = new Rectangle(BLOCK_SIZE, BLOCK_SIZE, Color.RED);
        food.setX(x);
        food.setY(y);
        root.getChildren().add(food);
    }

    private void stopGame() {
        running = false;
        timeline.stop();
        System.out.println("Game Over! Score: " + (snake.size() - 1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
