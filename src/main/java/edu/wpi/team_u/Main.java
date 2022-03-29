package edu.wpi.team_u;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    DBController.main(args);
    UIController.main(args);
  }
}
