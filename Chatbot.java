import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chatbot extends Application {

    VBox chatBox = new VBox(10);

    @Override
    public void start(Stage stage) {

        chatBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background:#fafafa;");

        TextField input = new TextField();
        input.setPromptText("Type a message...");

        Button sendBtn = new Button("Send");

        // Blue send button
        sendBtn.setStyle(
                "-fx-background-color:#3498db;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;"
        );

        sendBtn.setOnAction(e -> {

            String question = input.getText();
            if(question.isEmpty()) return;

            addUserMessage(question);

            String response = getResponse(question);
            addBotMessage(response);

            input.clear();
        });

        HBox inputArea = new HBox(10, input, sendBtn);
        inputArea.setPadding(new Insets(10));

        VBox root = new VBox(scrollPane, inputArea);

        // Off-white background
        root.setStyle("-fx-background-color:#f5f5f5;");

        Scene scene = new Scene(root, 400, 450);

        stage.setTitle("Job Site Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    private void addUserMessage(String text){

        String time = getTime();

        Label message = new Label(text + "\n" + time);

        message.setStyle(
                "-fx-background-color:#3498db;" +
                "-fx-text-fill:white;" +
                "-fx-padding:10;" +
                "-fx-background-radius:15;"
        );

        HBox box = new HBox(message);
        box.setAlignment(Pos.CENTER_RIGHT);

        chatBox.getChildren().add(box);
    }

    private void addBotMessage(String text){

        String time = getTime();

        Label message = new Label("🤖 " + text + "\n" + time);

        message.setStyle(
                "-fx-background-color:white;" +
                "-fx-text-fill:black;" +
                "-fx-padding:10;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#ddd;" +
                "-fx-border-radius:15;"
        );

        HBox box = new HBox(message);
        box.setAlignment(Pos.CENTER_LEFT);

        chatBox.getChildren().add(box);
    }

    private String getTime(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return LocalDateTime.now().format(format);
    }

    private String getResponse(String question){

        question = question.toLowerCase();

        if(question.contains("hello") || question.contains("hi"))
            return "Hello! How can I help you?";

        if(question.contains("job") || question.contains("apply"))
            return "You can apply through our jobs page.";

        return "Sorry, I don't understand.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}