package edu.wpi.team_u.frontEnd.controllers;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import edu.wpi.team_u.Uapp;
import edu.wpi.team_u.frontEnd.services.IService;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class giftFloralController implements Initializable, IService {

  @FXML JFXHamburger hamburger;
  @FXML VBox vBoxPane;
  @FXML Text processingText;
  @FXML TextField senderField;
  @FXML TextField recieverField;
  @FXML TextField staffField;
  @FXML TextField roomField;
  @FXML CheckBox plantBox;
  @FXML CheckBox balloonBox;
  @FXML CheckBox basketBox;
  @FXML CheckBox flowerBox;
  @FXML JFXTextArea messageText;
  @FXML Button submitButton;
  @FXML Button clearButton;

  public void initialize(URL location, ResourceBundle resources) {
    HamburgerBasicCloseTransition closeTransition = new HamburgerBasicCloseTransition(hamburger);
    closeTransition.setRate(-1);
    hamburger.addEventHandler(
        MouseEvent.MOUSE_CLICKED,
        e -> {
          closeTransition.setRate(closeTransition.getRate() * -1);
          closeTransition.play();
          vBoxPane.setVisible(!vBoxPane.isVisible());
        });
  }

  public void clear(ActionEvent actionEvent) {
    plantBox.setSelected(false);
    balloonBox.setSelected(false);
    flowerBox.setSelected(false);
    basketBox.setSelected(false);
    senderField.clear();
    recieverField.clear();
    staffField.clear();
    roomField.clear();
    messageText.clear();
  }

  public void submit(ActionEvent actionEvent) {
    plantBox.setSelected(false);
    balloonBox.setSelected(false);
    flowerBox.setSelected(false);
    basketBox.setSelected(false);
    senderField.clear();
    recieverField.clear();
    staffField.clear();
    roomField.clear();
    messageText.clear();
    processingText.setText("processing...");
    processingText.setVisible(true);
    new Thread(
            () -> {
              try {
                Thread.sleep(1500);
                Platform.runLater(
                    () -> {
                      processingText.setText("Done!");
                    });
                Thread.sleep(1500);
                Platform.runLater(
                    () -> {
                      processingText.setVisible(false);
                    });
              } catch (InterruptedException ie) {
              }
            })
        .start();
  }

  public void toServicePage(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/HomePage.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void toLaundryService(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/laundryService.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void toMedicineDelivery(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/medicineDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
  }

  @Override
  public void toMap(ActionEvent actionEvent) throws IOException {}

  public void toMealDelivery(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/mealDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  @Override
  public void toGiftAndFloral(ActionEvent actionEvent) throws IOException {}

  @Override
  public void toLaundry(ActionEvent actionEvent) throws IOException {}

  public void togiftFloralService(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/giftFloralService.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void toDeliverEquipmentController(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/equipmentDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void toSecurityService(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/labRequestServices.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  @Override
  public void addRequest() {}

  @Override
  public void removeRequest() {}

  @Override
  public void updateRequest() {}

  @Override
  public void displayRequest() {}

  public void toHome(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/HomePage.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  @Override
  public void toLabRequest(ActionEvent actionEvent) throws IOException {}
}
