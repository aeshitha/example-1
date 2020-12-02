package backEnd;

import javafx.application.Platform;
import javafx.scene.control.Label;


public class MessageManager {
    static Thread messageThread = null;


    public static void giveAWarning(Label label, String warningText, String afterText) {
        message(label, warningText, afterText, "-fx-background-color: red");
    }

    public static void giveSuccessMessage(Label label, String warningText, String afterText) {
        message(label, warningText, afterText, "-fx-background-color: lawngreen");
    }

    private static void message(Label label, String warningText, String afterText, String s) {
        Platform.runLater(() -> {
            label.setText(warningText);
            label.setStyle(s);
        });
        if (null != messageThread) {
            messageThread.interrupt();
        }
        messageThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    label.setStyle("-fx-background-color: royalblue");
                    label.setText(afterText);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        messageThread.start();
    }


}
