package edu.wpi.team_u;

import edu.wpi.team_u.BackEnd.Udb;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class DBController {
  public static void main(String[] args) throws IOException, URISyntaxException {
    Udb udb = new Udb();
    String username, password;
    try {
      username = args[0];
      password = args[1];
    } catch (Exception e) {
      username = "admin";
      password = "admin";
    }
    InputStream csvLocationFile = fromResources("edu/wpi/team_u/csvTables/TowerLocations.csv");
    InputStream csvEmployee = fromResources("edu/wpi/team_u/csvTables/TowerEmployees.csv");
    InputStream csvEquipment = fromResources("edu/wpi/team_u/csvTables/TowerEquipment.csv");
    InputStream[] CSVfiles = {csvLocationFile, csvEmployee, csvEquipment};

    udb.start(username, password, CSVfiles);
    // udb.menu(CSVfiles); //Terminal menu
  }

  public static InputStream fromResources(String pathFromResource) throws URISyntaxException {
    InputStream a = DBController.class.getClassLoader().getResourceAsStream(pathFromResource);
    return a;
  }
}
