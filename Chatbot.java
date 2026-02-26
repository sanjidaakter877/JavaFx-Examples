import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chatbot extends Application {

    @Override
    public void start(Stage stage) {
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setPrefHeight(300);

        TextField input = new TextField();
        input.setPromptText("Ask a question...");

        Button sendBtn = new Button("Send");
        sendBtn.setOnAction(e -> {
            String question = input.getText();
            chatArea.appendText("You: " + question + "\n");
            chatArea.appendText("Bot: " + getResponse(question) + "\n\n");
            input.clear();
        });

        VBox root = new VBox(10, chatArea, input, sendBtn);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Job Site Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    private String getResponse(String question){
        question = question.toLowerCase();
        if(question.contains("hello") || question.contains("hi")) return "Hello! How can I help you?";
        if(question.contains("job") || question.contains("apply")) return "You can apply through our jobs page.";
        return "Sorry, I don't understand.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
