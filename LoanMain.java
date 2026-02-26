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

        // TextFields for input
        TextField tfInterest = new TextField();
        TextField tfYears = new TextField();
        TextField tfAmount = new TextField();

        // TextFields for output (read-only)
        TextField tfMonthly = new TextField();
        tfMonthly.setEditable(false);
        TextField tfTotal = new TextField();
        tfTotal.setEditable(false);

        // Button to calculate
        Button btnCalculate = new Button("Calculate");

        btnCalculate.setOnAction(e -> {
            try {
                // Get input values
                double interest = Double.parseDouble(tfInterest.getText());
                int years = Integer.parseInt(tfYears.getText());
                double amount = Double.parseDouble(tfAmount.getText());

                // Create Loan object
                Loan loan = new Loan(interest, years, amount);

                // Show results
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

        // Scene and Stage
        Scene scene = new Scene(grid, 400, 300);
        stage.setTitle("Loan Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
