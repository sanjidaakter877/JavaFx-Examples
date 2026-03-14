import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField display = new TextField();

    private double num1 = 0;
    private String operator = "";

    @Override
    public void start(Stage stage) {

        display.setEditable(false);
        display.setPrefHeight(50);

        // Display style
        display.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-background-color: black;" +
                "-fx-text-fill: white;" +
                "-fx-alignment: center-right;" +
                "-fx-border-color: orange;" +
                "-fx-border-width: 2;"
        );

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(8);
        grid.setVgap(8);

        String[][] buttons = {
            {"7","8","9","/"},
            {"4","5","6","*"},
            {"1","2","3","-"},
            {"0",".","=","+"}
        };

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){

                String text = buttons[i][j];
                Button btn = new Button(text);
                btn.setPrefSize(55,55);

                // NUMBER BUTTONS (kitten style)
                if("0123456789.".contains(text)){
                    btn.setStyle(
                            "-fx-background-color: white;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-border-color: orange;" +
                            "-fx-border-width: 3;" +
                            "-fx-background-radius: 40;" +
                            "-fx-border-radius: 40;"
                    );
                }

                // OPERATOR BUTTONS (dog style)
                else{
                    btn.setStyle(
                            "-fx-background-color: lightgray;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-border-color: lime;" +
                            "-fx-border-width: 3;" +
                            "-fx-background-radius: 40;" +
                            "-fx-border-radius: 40;"
                    );
                }

                grid.add(btn,j,i+1);
                btn.setOnAction(e -> handleButton(btn.getText()));
            }
        }

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);

        // Whole background black
        root.setStyle("-fx-background-color: black;");

        root.add(display,0,0);
        root.add(grid,0,1);

        Scene scene = new Scene(root, 250, 320);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void handleButton(String text){
        if("0123456789.".contains(text)){
            display.appendText(text);
        } 
        else if("+-*/".contains(text)){
            num1 = Double.parseDouble(display.getText());
            operator = text;
            display.clear();
        } 
        else if("=".equals(text)){
            double num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch(operator){
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}