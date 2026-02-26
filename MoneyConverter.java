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
        Label lblUSD = new Label("USD:");
        TextField tfUSD = new TextField();

        Label lblINR = new Label("INR:");
        TextField tfINR = new TextField();
        tfINR.setEditable(false);

        Label lblEUR = new Label("EUR:");
        TextField tfEUR = new TextField();
        tfEUR.setEditable(false);

        Label lblGBP = new Label("GBP:");
        TextField tfGBP = new TextField();
        tfGBP.setEditable(false);

        Button btnConvert = new Button("Convert");

        btnConvert.setOnAction(e -> {
            try {
                double usd = Double.parseDouble(tfUSD.getText());
                tfINR.setText(String.format("%.2f", usd * 83.5));  // Example rates
                tfEUR.setText(String.format("%.2f", usd * 0.92));
                tfGBP.setText(String.format("%.2f", usd * 0.81));
            } catch (NumberFormatException ex){
                tfINR.setText("Error");
                tfEUR.setText("Error");
                tfGBP.setText("Error");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(lblUSD,0,0); grid.add(tfUSD,1,0);
        grid.add(lblINR,0,1); grid.add(tfINR,1,1);
        grid.add(lblEUR,0,2); grid.add(tfEUR,1,2);
        grid.add(lblGBP,0,3); grid.add(tfGBP,1,3);
        grid.add(btnConvert,1,4);

        Scene scene = new Scene(grid, 300, 200);
        stage.setTitle("Money Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
