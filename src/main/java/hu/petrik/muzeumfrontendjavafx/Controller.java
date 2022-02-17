package hu.petrik.muzeumfrontendjavafx;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    protected Stage stage;

    public Stage getStage() {
        return stage;
    }

    protected void hibaKiir(Exception e) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Hibát észletünk!");
        errorAlert.setHeaderText(e.getClass().toString());
        errorAlert.setContentText(e.getMessage());
        Timer alertTimer = new Timer();
        alertTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> errorAlert.show());
            }
        }, 1000);

    }

    protected void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(msg);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.show();
    }

    protected void alertWait(String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(msg);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }

    protected boolean confirm(String szoveg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Biztos benne?");
        alert.setHeaderText(szoveg);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static Controller ujAblak(String fxml, String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MuzeumApp.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        controller.stage = stage;
        return controller;
    }
}