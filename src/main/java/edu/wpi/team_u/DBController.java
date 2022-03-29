package edu.wpi.team_u;

import edu.wpi.team_u.BackEnd.Udb;
import edu.wpi.team_u.frontEnd.Uapp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class DBController {
  public static void main(String[] args) throws IOException, SQLException {
    Udb udb = new Udb();
    String username, password;
    try {
      username = args[0];
      password = args[1];
    } catch (Exception e) {
      username = "admin";
      password = "admin";
    }
    String csvLocationFile = fromResources("edu/wpi/team_u/csvTables/TowerLocations.csv");
    String csvEmployee = fromResources("edu/wpi/team_u/csvTables/TowerEmployees.csv");
    String csvEquipment = fromResources("edu/wpi/team_u/csvTables/TowerEquipment.csv");

    URL a = Uapp.class.getClassLoader().getResource("edu/wpi/team_u/icons/hospitalIcon.png");
    System.out.println(String.valueOf(a));
    String[] CSVfiles = {csvLocationFile, csvEmployee, csvEquipment};

    udb.start(username, password, CSVfiles);
    // udb.menu(CSVfiles); Terminal menu
  }

  public static String fromResources(String pathFromResource) {
    URL a = Main.class.getClassLoader().getResource(pathFromResource);
    return String.valueOf(a);
  }
}
