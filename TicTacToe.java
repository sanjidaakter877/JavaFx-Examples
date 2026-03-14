import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private boolean xTurn = true;
    private Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button("");
                btn.setPrefSize(80, 80);
                btn.setStyle("-fx-font-size: 24; " +
                             "-fx-background-color: white; " +
                             "-fx-border-color: black; " +
                             "-fx-border-width: 2;");
                btn.setOnAction(e -> handleMove(btn));
                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        Scene scene = new Scene(grid, 260, 260);
        stage.setTitle("🎉 Tic Tac Toe Fun 🎉");
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(Button btn) {
        if (!btn.getText().isEmpty()) return;

        btn.setText(xTurn ? "X" : "O");
        btn.setTextFill(xTurn ? Color.BLUE : Color.RED);
        xTurn = !xTurn;

        String winner = checkWinner();
        if (winner != null) {
            showAlert(winner + " wins! 🎉");
            resetBoard();
        } else if (isDraw()) {
            showAlert("It's a draw! 🤝");
            resetBoard();
        }
    }

    private String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()))
                return buttons[i][0].getText();

            if (!buttons[0][i].getText().isEmpty() &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()))
                return buttons[0][i].getText();
        }

        if (!buttons[0][0].getText().isEmpty() &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()))
            return buttons[0][0].getText();

        if (!buttons[0][2].getText().isEmpty() &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()))
            return buttons[0][2].getText();

        return null;
    }

    private boolean isDraw() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                if (btn.getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void resetBoard() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setText("");
                btn.setStyle("-fx-font-size: 24; " +
                             "-fx-background-color: white; " +
                             "-fx-border-color: black; " +
                             "-fx-border-width: 2;");
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}