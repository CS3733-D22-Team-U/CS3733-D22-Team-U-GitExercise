package edu.wpi.team_u.frontEnd.controllers;

import edu.wpi.team_u.frontEnd.Uapp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {

  public void toEquipmentDelivery(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/equipmentDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void toMealDelivery(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/mealDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  public void togiftFloralService(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/giftFloralService.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }
  /*
  public Button laundryService;
  public Button equipmentDelivery;

  public void backToServicePage(ActionEvent actionEvent) throws IOException {
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

  public void toMealDelivery(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/mealDelivery.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

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

  public void toServicePage(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/team_u/views/HomePage.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }*/
}
