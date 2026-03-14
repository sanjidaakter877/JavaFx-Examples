import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MoneyConverter extends Application {

    @Override
    public void start(Stage stage) {
        // Labels
        Label lblUSD = new Label("USD:");
        Label lblINR = new Label("INR:");
        Label lblEUR = new Label("EUR:");
        Label lblGBP = new Label("GBP:");

        // Apply Times New Roman font to labels
        String labelStyle = "-fx-font-family: 'Times New Roman'; -fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2c3e50;";
        lblUSD.setStyle(labelStyle);
        lblINR.setStyle(labelStyle);
        lblEUR.setStyle(labelStyle);
        lblGBP.setStyle(labelStyle);

        // TextFields
        TextField tfUSD = new TextField();
        TextField tfINR = new TextField(); tfINR.setEditable(false);
        TextField tfEUR = new TextField(); tfEUR.setEditable(false);
        TextField tfGBP = new TextField(); tfGBP.setEditable(false);

        // Style input fields (light gray rounded)
        String inputStyle = "-fx-background-color: #9ac1ee;-fx-padding: 5; -fx-font-family: 'Times New Roman'; -fx-font-size: 14; -fx-background-radius: 10;";
        tfUSD.setStyle(inputStyle);
        tfINR.setStyle("-fx-background-color: #669ccf; -fx-text-fill:black; -fx-font-family: 'Times New Roman'; -fx-font-weight:bold; -fx-font-size:14; -fx-background-radius:10;");
        tfEUR.setStyle("-fx-background-color: #4b90c8; -fx-text-fill:black; -fx-font-family: 'Times New Roman'; -fx-font-weight:bold; -fx-font-size:14; -fx-background-radius:10;");
        tfGBP.setStyle("-fx-background-color: #386689; -fx-text-fill:black; -fx-font-family: 'Times New Roman'; -fx-font-weight:bold; -fx-font-size:14; -fx-background-radius:10;");

        // Convert button
        Button btnConvert = new Button("Convert");

        // White button with bold black border
        String whiteButtonStyle = "-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 14; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 10; -fx-border-radius: 10;";
        String whiteHover = "-fx-background-color: #f0f0f0; -fx-text-fill: black; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 14; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 10; -fx-border-radius: 10;";
        btnConvert.setStyle(whiteButtonStyle);

        btnConvert.setOnMouseEntered(e -> btnConvert.setStyle(whiteHover));
        btnConvert.setOnMouseExited(e -> btnConvert.setStyle(whiteButtonStyle));

        // Button action
        btnConvert.setOnAction(e -> {
            try {
                double usd = Double.parseDouble(tfUSD.getText());
                tfINR.setText(String.format("%.2f", usd * 83.5));
                tfEUR.setText(String.format("%.2f", usd * 0.92));
                tfGBP.setText(String.format("%.2f", usd * 0.81));
            } catch (NumberFormatException ex) {
                tfINR.setText("Error");
                tfEUR.setText("Error");
                tfGBP.setText("Error");
            }
        });

        // Layout using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Add controls to grid
        grid.add(lblUSD,0,0); grid.add(tfUSD,1,0);
        grid.add(lblINR,0,1); grid.add(tfINR,1,1);
        grid.add(lblEUR,0,2); grid.add(tfEUR,1,2);
        grid.add(lblGBP,0,3); grid.add(tfGBP,1,3);
        grid.add(btnConvert,1,4);

        // Fun background gradient
        grid.setStyle("-fx-background-color: linear-gradient(to bottom right, #fdfbfb, #ebedee); -fx-background-radius:15;");

        // Scene and Stage
        Scene scene = new Scene(grid, 350, 250);
        stage.setTitle("Fun Money Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}