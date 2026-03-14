import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanMain extends Application {

    @Override
    public void start(Stage stage) {
        // Labels
        Label lblInterest = new Label("Annual Interest Rate (%):");
        Label lblYears = new Label("Number of Years:");
        Label lblAmount = new Label("Loan Amount:");
        Label lblMonthly = new Label("Monthly Payment:");
        Label lblTotal = new Label("Total Payment:");

        // Style labels
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #2c3e50;";
        lblInterest.setStyle(labelStyle);
        lblYears.setStyle(labelStyle);
        lblAmount.setStyle(labelStyle);
        lblMonthly.setStyle(labelStyle);
        lblTotal.setStyle(labelStyle);

        // TextFields for input
        TextField tfInterest = new TextField();
        TextField tfYears = new TextField();
        TextField tfAmount = new TextField();

        // Style input fields
        String inputStyle = "-fx-background-color: #ecf0f1; -fx-padding: 5; -fx-font-size: 14; -fx-background-radius: 10;";
        tfInterest.setStyle(inputStyle);
        tfYears.setStyle(inputStyle);
        tfAmount.setStyle(inputStyle);

        // TextFields for output (read-only)
        TextField tfMonthly = new TextField();
        tfMonthly.setEditable(false);
        TextField tfTotal = new TextField();
        tfTotal.setEditable(false);

        // Style output fields (light pink with black text)
        String outputStyle = "-fx-background-color: #ffb6c1; -fx-text-fill: black; -fx-font-size: 14; -fx-font-weight: bold; -fx-background-radius: 10;";
        tfMonthly.setStyle(outputStyle);
        tfTotal.setStyle(outputStyle);

        // Button to calculate
        Button btnCalculate = new Button("Calculate");

        // Style button (blue)
        String blueStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 20;";
        String blueHover = "-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; -fx-background-radius: 20;";
        btnCalculate.setStyle(blueStyle);

        // Hover effect
        btnCalculate.setOnMouseEntered(e -> btnCalculate.setStyle(blueHover));
        btnCalculate.setOnMouseExited(e -> btnCalculate.setStyle(blueStyle));

        // Button action
        btnCalculate.setOnAction(e -> {
            try {
                double interest = Double.parseDouble(tfInterest.getText());
                int years = Integer.parseInt(tfYears.getText());
                double amount = Double.parseDouble(tfAmount.getText());

                Loan loan = new Loan(interest, years, amount);

                tfMonthly.setText(String.format("%.2f", loan.getMonthlyPayment()));
                tfTotal.setText(String.format("%.2f", loan.getTotalPayment()));
            } catch (NumberFormatException ex) {
                tfMonthly.setText("Invalid input");
                tfTotal.setText("Invalid input");
            }
        });

        // Layout using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add controls to grid
        grid.add(lblInterest, 0, 0);
        grid.add(tfInterest, 1, 0);
        grid.add(lblYears, 0, 1);
        grid.add(tfYears, 1, 1);
        grid.add(lblAmount, 0, 2);
        grid.add(tfAmount, 1, 2);
        grid.add(lblMonthly, 0, 3);
        grid.add(tfMonthly, 1, 3);
        grid.add(lblTotal, 0, 4);
        grid.add(tfTotal, 1, 4);
        grid.add(btnCalculate, 1, 5);

        // Fun background gradient
        grid.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #fdfbfb, #ebedee);" +
                "-fx-background-radius: 15;"
        );

        // Scene and Stage
        Scene scene = new Scene(grid, 450, 350);
        stage.setTitle("Fun Loan Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}