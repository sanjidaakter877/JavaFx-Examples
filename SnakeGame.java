import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
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
    private double headX, headY;
    private String direction = "RIGHT";
    private boolean running = true;

    private Pane root;
    private Timeline timeline;
    private Label scoreLabel;
    private int score = 0;

    private Pane rat;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        // Score display
        scoreLabel = new Label("Score: 0");
        scoreLabel.setTextFill(Color.BLACK);
        scoreLabel.setStyle("-fx-font-size: 20px;");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        root.getChildren().add(scoreLabel);

        // Initial snake head position
        headX = WIDTH / 2;
        headY = HEIGHT / 2;

        Rectangle head = createSnakeBody(headX, headY);
        snake.add(head);
        root.getChildren().add(head);

        spawnRat();

        timeline = new Timeline(new KeyFrame(Duration.millis(150), e -> gameLoop()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> { if (!direction.equals("DOWN")) direction = "UP"; }
                case DOWN -> { if (!direction.equals("UP")) direction = "DOWN"; }
                case LEFT -> { if (!direction.equals("RIGHT")) direction = "LEFT"; }
                case RIGHT -> { if (!direction.equals("LEFT")) direction = "RIGHT"; }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Cartoon Snake Game");
        stage.show();
    }

    private void gameLoop() {
        if (!running) return;

        // Move head
        switch (direction) {
            case "UP" -> headY -= BLOCK_SIZE;
            case "DOWN" -> headY += BLOCK_SIZE;
            case "LEFT" -> headX -= BLOCK_SIZE;
            case "RIGHT" -> headX += BLOCK_SIZE;
        }

        // Wall collision
        if (headX < 0 || headX >= WIDTH || headY < 0 || headY >= HEIGHT) {
            gameOver();
            return;
        }

        // Self collision
        for (Rectangle r : snake) {
            if (r.getX() == headX && r.getY() == headY) {
                gameOver();
                return;
            }
        }

        // Move snake body
        Rectangle newHead = createSnakeBody(headX, headY);
        snake.addFirst(newHead);
        root.getChildren().add(newHead);

        // Check rat collision
        if (rat.getLayoutX() == headX && rat.getLayoutY() == headY) {
            score++;
            scoreLabel.setText("Score: " + score);
            spawnRat();
        } else {
            Rectangle tail = snake.removeLast();
            root.getChildren().remove(tail);
        }

        // Draw cartoon head
        drawCartoonHead(headX, headY);
    }

    private Rectangle createSnakeBody(double x, double y) {
        Rectangle body = new Rectangle(BLOCK_SIZE, BLOCK_SIZE, Color.GREEN);
        body.setX(x);
        body.setY(y);
        return body;
    }

    private void drawCartoonHead(double x, double y) {
        // Remove previous head elements
        root.getChildren().removeIf(node -> node.getUserData() != null && node.getUserData().equals("head"));

        // Eyes
        Circle leftEye = new Circle(3, Color.BLACK);
        leftEye.setCenterX(x + 5);
        leftEye.setCenterY(y + 5);
        leftEye.setUserData("head");

        Circle rightEye = new Circle(3, Color.BLACK);
        rightEye.setCenterX(x + 15);
        rightEye.setCenterY(y + 5);
        rightEye.setUserData("head");

        // Lips
        Line lips = new Line(x + 5, y + 15, x + 15, y + 15);
        lips.setStroke(Color.RED);
        lips.setStrokeWidth(2);
        lips.setUserData("head");

        root.getChildren().addAll(leftEye, rightEye, lips);
    }

    private void spawnRat() {
    if (rat != null) root.getChildren().remove(rat);

    Random rand = new Random();
    int x = rand.nextInt(WIDTH / BLOCK_SIZE) * BLOCK_SIZE;
    int y = rand.nextInt(HEIGHT / BLOCK_SIZE) * BLOCK_SIZE;

    rat = new Pane();

    double bodyRadiusX = BLOCK_SIZE / 2.0;
    double bodyRadiusY = BLOCK_SIZE / 2.0;
    double centerX = bodyRadiusX;
    double centerY = bodyRadiusY;

    // Body
    double bodyRadius = BLOCK_SIZE / 2.0; // radius of rat face
    Circle body = new Circle(bodyRadius, bodyRadius, bodyRadius); 
    body.setFill(Color.GREY);      // grey face
    body.setStroke(Color.BLACK);  
    // Ears
    double earRadius = BLOCK_SIZE / 6.0;
    Circle leftEar = new Circle(centerX - bodyRadiusX / 2, centerY - bodyRadiusY / 2, earRadius, Color.BLACK);
    Circle rightEar = new Circle(centerX + bodyRadiusX / 2, centerY - bodyRadiusY / 2, earRadius, Color.BLACK);

    // Eyes
    double eyeRadius = BLOCK_SIZE / 10.0;
    Circle eye1 = new Circle(centerX - bodyRadiusX / 3, centerY - bodyRadiusY / 4, eyeRadius, Color.BLACK);
    Circle eye2 = new Circle(centerX + bodyRadiusX / 3, centerY - bodyRadiusY / 4, eyeRadius, Color.BLACK);

    // Nose
    Circle nose = new Circle(centerX, centerY, BLOCK_SIZE / 13.0, Color.RED);

    // Whiskers
    Line leftWhisker = new Line(centerX, centerY, centerX - bodyRadiusX / 2, centerY);
    leftWhisker.setStroke(Color.BLACK);
    Line rightWhisker = new Line(centerX, centerY, centerX + bodyRadiusX / 2, centerY);
    rightWhisker.setStroke(Color.BLACK);

    rat.getChildren().addAll(body, leftEar, rightEar, eye1, eye2, nose, leftWhisker, rightWhisker);
    rat.setLayoutX(x);
    rat.setLayoutY(y);
    root.getChildren().add(rat);
}
    private void gameOver() {
        running = false;
        Label gameOverLabel = new Label("GAME OVER\nScore: " + score);
        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        gameOverLabel.setLayoutX(WIDTH / 4.0);
        gameOverLabel.setLayoutY(HEIGHT / 2.0 - 50);
        root.getChildren().add(gameOverLabel);
    }
}