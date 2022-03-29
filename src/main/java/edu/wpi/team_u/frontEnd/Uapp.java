package edu.wpi.team_u.frontEnd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
  public void start(Stage primaryStage) throws IOException, URISyntaxException {
    Scene scene = getScene("edu/wpi/team_u/views/HomePage.fxml");
    URL a = Uapp.class.getClassLoader().getResource("edu/wpi/team_u/icons/hospitalIcon.png");
    System.out.println(a.toURI().toString());
    primaryStage.getIcons().add(new Image(String.valueOf(a)));
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
    Parent root = fxmlLoader.load(Uapp.class.getClassLoader().getResource(pathFromResources));
    return new Scene(root);
  }
}
