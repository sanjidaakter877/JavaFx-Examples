import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private boolean xTurn = true;
    private Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Button btn = new Button("");
                btn.setPrefSize(80,80);
                btn.setOnAction(e -> handleMove(btn));
                buttons[i][j] = btn;
                grid.add(btn,j,i);
            }
        }

        Scene scene = new Scene(grid, 260, 260);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(Button btn){
        if(!btn.getText().isEmpty()) return;
        btn.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
