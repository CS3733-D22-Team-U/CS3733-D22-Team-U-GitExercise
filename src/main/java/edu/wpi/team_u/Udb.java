package edu.wpi.team_u;

import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Udb {

  public ArrayList<Location> locations = new ArrayList<Location>();
  public ArrayList<Location> SQLBuiltLocations = new ArrayList<Location>();

  public void storeCSVtoOBJ(String csvFile) throws IOException {
    String s;
    File file = new File(csvFile);
    BufferedReader br = new BufferedReader(new FileReader(file));
    int i = 0;
    while ((s = br.readLine()) != null) {
      String[] row = s.split(",");

      locations.add(new Location());
      if (i > 0) {
        locations.get(i).StrtoLoc(row);
      }
      i++;
    }
    for (int j = 1; j < locations.size(); j++) {
      System.out.println(locations.get(j).nodeID);
    }
  }

  public class Location {

    String nodeID;
    int xcoord;
    int ycoord;
    String floor;
    String building;
    String nodeType;
    String longName;
    String shortName;

    void StrtoLoc(String[] row) {
      this.nodeID = row[0];
      this.xcoord = Integer.parseInt(row[1]);
      this.ycoord = Integer.parseInt(row[2]);
      this.floor = row[3];
      this.building = row[4];
      this.nodeType = row[5];
      this.longName = row[6];
      this.shortName = row[7];
    }
  }

  public void JavaToSQL() {
    int j = 1;

    System.out.println("-------Embedded Apache Derby Connection Testing --------");
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    } catch (ClassNotFoundException e) {
      System.out.println("Apache Derby Driver not found. Add the classpath to your module.");
      System.out.println("For IntelliJ do the following:");
      System.out.println("File | Project Structure, Modules, Dependency tab");
      System.out.println("Add by clicking on the green plus icon on the right of the window");
      System.out.println(
          "Select JARs or directories. Go to the folder where the database JAR is located");
      System.out.println("Click OK, now you can compile your program and run it.");
      e.printStackTrace();
      return;
    }

    System.out.println("Apache Derby driver registered!");
    Connection connection = null;

    try {
      connection = DriverManager.getConnection("jdbc:derby:UDB;create=true");
      Statement exampleStatement = connection.createStatement();
      try {
        exampleStatement.execute("Drop table Locations");
      } catch (Exception e) {
      }
      exampleStatement.execute(
          "CREATE TABLE Locations(nodeID varchar(18) not null, "
              + "xcoord int not null,"
              + "ycoord int not null,"
              + "floor varchar(3) not null,"
              + "building varchar(6),"
              + "nodeType varchar(6),"
              + "longName varchar(900) not null,"
              + "shortName varchar(600))");
      while (j < locations.size()) {
        Location currLoc = locations.get(j);
        exampleStatement.execute(
            "INSERT INTO Locations VALUES("
                + "'"
                + currLoc.nodeID
                + "',"
                + currLoc.xcoord
                + ","
                + currLoc.ycoord
                + ",'"
                + currLoc.floor
                + "','"
                + currLoc.building
                + "','"
                + currLoc.nodeType
                + "','"
                + currLoc.longName
                + "','"
                + currLoc.shortName
                + "')");
        j++;
      }
      connection.close();

    } catch (SQLException e) {
      System.out.println("Connection failed. Check output console.");
      e.printStackTrace();
      return;
    }
    System.out.println("Apache Derby connection established!");
  }

  public void SQLToJava() {

    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:derby:UDB;");
      Statement exampleStatement = connection.createStatement();

      try {
        ResultSet results;
        results = exampleStatement.executeQuery("SELECT * FROM Locations");

        while (results.next()) {
          String nodeID = results.getString("nodeID");
          int xcoord = results.getInt("xcoord");
          int ycoord = results.getInt("ycoord");
          String floor = results.getString("floor");
          String building = results.getString("building");
          String nodeType = results.getString("nodeType");
          String longName = results.getString("longName");
          String shortName = results.getString("shortName");

          Location SQLRow = new Location();
          SQLRow.nodeID = nodeID;
          SQLRow.xcoord = xcoord;
          SQLRow.ycoord = ycoord;
          SQLRow.floor = floor;
          SQLRow.building = building;
          SQLRow.nodeType = nodeType;
          SQLRow.longName = longName;
          SQLRow.shortName = shortName;

          SQLBuiltLocations.add(SQLRow);
        }
      } catch (SQLException e) {
        System.out.println("Locations not found");
      }

    } catch (SQLException e) {
      System.out.println("Database does not exist.");
    }
  }

  public void JavaToCSV(ArrayList<Location> locs, String csvFilem) throws IOException {
    FileWriter fw = new FileWriter(csvFilem);

    fw.append("nodeID");
    fw.append(",");
    fw.append("xcoord");
    fw.append(",");
    fw.append("ycoord");
    fw.append(",");
    fw.append("floor");
    fw.append(",");
    fw.append("building");
    fw.append(",");
    fw.append("nodeType");
    fw.append(",");
    fw.append("longName");
    fw.append(",");
    fw.append("shortName");
    fw.append("\n");

    for (int i = 1; i <= locs.size(); i++) { // ask about how this was working without and = sign
      System.out.println(i);
      fw.append(locations.get(i).nodeID);
      fw.append(",");
      fw.append(Integer.toString(locations.get(i).xcoord));
      fw.append(",");
      fw.append(Integer.toString(locations.get(i).ycoord));
      fw.append(",");
      fw.append(locations.get(i).floor);
      fw.append(",");
      fw.append(locations.get(i).building);
      fw.append(",");
      fw.append(locations.get(i).nodeType);
      fw.append(",");
      fw.append(locations.get(i).longName);
      fw.append(",");
      fw.append(locations.get(i).shortName);
      fw.append("\n");
    }
    fw.flush();
    fw.close();
  }

  public void menu() {
    System.out.println(
        "1 – Location Information\n"
            + "2 – Change Floor and Type\n"
            + "3 – Enter Location\n"
            + "4 – Delete Location\n"
            + "5 – Save Locations to CSV file\n"
            + "6 – Exit Program");

    Scanner userInput = new Scanner(System.in);
    int inputNumber = Integer.parseInt(userInput.nextLine());
    switch (inputNumber) {
      case 1:
        // csv to java

        break;
      case 2:
        break;

      case 3:
        break;
      case 4:
        //
        break;
      case 5:
        System.out.println("Enter CSV file location name");
        Scanner sc = new Scanner(System.in);
        String CSVName = sc.nextLine();
        String csvFilePath = "src/main/resources/" + CSVName + ".csv";

        try {
          new File(csvFilePath);
          this.SQLToJava();
          this.JavaToCSV(this.SQLBuiltLocations, csvFilePath);

        } catch (IOException e) {

        }
        break;
      case 6:
        //
        break;
      default:
        break;
    }
  }
}
