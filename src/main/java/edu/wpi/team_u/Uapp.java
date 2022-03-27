package edu.wpi.team_u;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Uapp extends Application {

  @Override
  public void init() {
    log.info("Starting Up");
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Scene scene = getScene("edu/wpi/team_u/views/app.fxml");
    primaryStage.getIcons().add(new Image("edu/wpi/team_u/icons/hospitalicon.png"));
    primaryStage.setTitle("Mass General Brigham");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }

  public static Scene getScene(String pathFromResources) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    // InputStream is = AppController.class.getClassLoader().getResourceAsStream(pathFromResources);

    Parent root = fxmlLoader.load(Uapp.class.getClassLoader().getResource(pathFromResources));
    return new Scene(root);
  }
}
