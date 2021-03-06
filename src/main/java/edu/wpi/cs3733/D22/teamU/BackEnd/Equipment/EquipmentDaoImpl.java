package edu.wpi.cs3733.D22.teamU.BackEnd.Equipment;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentDaoImpl implements EquipmentDao {
  public String DB_LOC;
  public ArrayList<Equipment> EquipmentList = new ArrayList<Equipment>();

  /**
   * Constructor for EquipmentDaoImpl
   *
   * @param db_loc
   */
  public EquipmentDaoImpl(String db_loc) {
    this.DB_LOC = db_loc;
  }

  /**
   * Reads CSV file and puts the Equipment into an array list: EquipmentList
   *
   * @param csvFile
   * @throws IOException
   */
  public void CSVToJava(String csvFile) throws IOException {
    EquipmentList = new ArrayList<Equipment>();
    String s;
    File file = new File(csvFile);
    BufferedReader br = new BufferedReader(new FileReader(file));
    br.readLine();
    while ((s = br.readLine()) != null) {
      String[] row = s.split(",");
      if (row.length == 4) {
        EquipmentList.add(
            new Equipment(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2])));
      }
    }
  }

  /**
   * Reads the array list: EquipmentList, then opens up a connection to the UDB database, then it
   * creates a new table called in the UDB database table: EquipmentList. It then inserts the array
   * list: EquipmentList into the UDB database table: EquipmentList
   */
  public void JavaToSQL() {

    try {
      Connection connection = null;
      connection = DriverManager.getConnection(DB_LOC);

      Statement exampleStatement = connection.createStatement();
      try {
        exampleStatement.execute("Drop table EquipmentList");
      } catch (Exception e) {
        System.out.println("didn't drop table");
      }

      // do
      exampleStatement.execute(
          "CREATE TABLE EquipmentList(name varchar(18) not null, "
              + "amount int not null,"
              + "inUse int not null,"
              + "available int not null)");

      for (int j = 0; j < EquipmentList.size(); j++) {
        Equipment currLoc = EquipmentList.get(j);
        exampleStatement.execute(
            "INSERT INTO EquipmentList VALUES("
                + "'"
                + currLoc.getName()
                + "',"
                + currLoc.getAmount()
                + ","
                + currLoc.getInUse()
                + ","
                + currLoc.getAvailable()
                + ")");
      }

      connection.close();

    } catch (SQLException e) {
      System.out.println("Connection failed. Check output console.");
      e.printStackTrace();
      return;
    }
  }

  /**
   * Clears the array list: EquipmentList and then reads the UDB database table: EquipmentList then
   * copies the to the cleared array list
   *
   * @throws SQLException
   */
  public void SQLToJava() throws SQLException {
    EquipmentList = new ArrayList<Equipment>();

    try {
      Connection connection = null;
      connection = DriverManager.getConnection(DB_LOC);

      Statement exampleStatement = connection.createStatement();

      try {
        ResultSet results;
        results = exampleStatement.executeQuery("SELECT * FROM EquipmentList");

        while (results.next()) {
          String name = results.getString("Name");
          int amount = results.getInt("Amount");
          int inUse = results.getInt("InUse");

          Equipment SQLRow = new Equipment(name, amount, inUse);

          EquipmentList.add(SQLRow);
        }
      } catch (SQLException e) {
        System.out.println(e);
      }

      connection.close();

    } catch (SQLException e) {
      System.out.println("Database does not exist.");
    }
  }

  /**
   * Copies the array list: EquipmentList and writes it into the CSV file
   *
   * @param csvFile
   * @throws IOException
   */
  public void JavaToCSV(String csvFile) throws IOException {
    PrintWriter fw = new PrintWriter(new File(csvFile));

    fw.append("Name");
    fw.append(",");
    fw.append("Amount");
    fw.append(",");
    fw.append("In Use");
    fw.append(",");
    fw.append("Available");
    fw.append("\n");

    for (int i = 0;
        i < EquipmentList.size();
        i++) { // ask about how this was working without and = sign
      fw.append(EquipmentList.get(i).getName());
      fw.append(",");
      fw.append(Integer.toString(EquipmentList.get(i).getAmount()));
      fw.append(",");
      fw.append(Integer.toString(EquipmentList.get(i).getInUse()));
      fw.append(",");
      fw.append(Integer.toString(EquipmentList.get(i).getAvailable()));
      fw.append("\n");
    }
    fw.close();
  }

  /**
   * Prints out the Contents of the CSV file TowerEquipment.csv
   *
   * @param csvFile
   * @throws IOException
   */
  public void printEquipTableInTerm(String csvFile) throws IOException {
    // csv to java
    this.CSVToJava(csvFile);
    // display locations and attributes
    System.out.println("Name |\t Amount |\t In Use |\t Available");
    for (Equipment equipment : EquipmentList) {
      System.out.println(
          equipment.getName()
              + " | \t"
              + equipment.getAmount()
              + " | \t"
              + equipment.getInUse()
              + " | \t"
              + equipment.getAvailable()
              + " | \t");
    }
    // menu
  }

  // -------------------------------Start of debugging backend
  // functions------------------------------------------//

  /**
   * Asks user for name of item they wish to edit and then ask to change the total amount and the
   * amount in use, Then changes the values in the database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void editEquipValue(String csvFile) throws IOException, SQLException {
    // takes entries from SQL table that match input node and updates it with a new floor and
    // location type
    // input ID
    Scanner s = new Scanner(System.in);
    System.out.println("Please input the name: ");
    String inputName = s.nextLine();
    // input new floor
    System.out.println("New Amount: ");
    String inputNewAmount = s.nextLine();
    // input new location type
    System.out.println("How many in use? ");
    String inputInUse = s.nextLine();
    this.CSVToJava(csvFile); // t
    for (int i = 0; i < this.EquipmentList.size(); i++) {
      if (this.EquipmentList.get(i).getName().equals(inputName)) {
        this.EquipmentList.get(i).Amount = Integer.parseInt(inputNewAmount);
        this.EquipmentList.get(i).InUse = Integer.parseInt(inputInUse);
      }
    }
    this.JavaToSQL();
    ; // t
    this.SQLToJava(); // t
    this.JavaToCSV(csvFile); // t
  }

  /**
   * Prompts user for the name of a new item and then adds it to the csv file and database
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void addEquip(String csvFile) throws IOException, SQLException {
    // add a new entry to the SQL table
    // prompt for ID
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the new equipment name");
    String newName = s.nextLine();
    Equipment newEquipment = new Equipment(newName);
    this.EquipmentList.add(newEquipment);
    this.JavaToSQL();
    this.SQLToJava();
    this.JavaToCSV(csvFile);
  }

  /**
   * Prompts user for the name of the item they wish to remove and then removes that item from the
   * database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void removeEquip(String csvFile) throws IOException, SQLException {
    // removes entries from SQL table that match input node
    // prompt for ID
    Scanner s = new Scanner(System.in);
    System.out.println("Input ID for to delete location: ");
    String userNodeID = s.nextLine(); // remove locations that match user input
    for (int i = this.EquipmentList.size() - 1; i >= 0; i--) {
      if (this.EquipmentList.get(i).getName().equals(userNodeID)) {
        this.EquipmentList.remove(i);
      }
    }
    this.JavaToSQL();
    this.SQLToJava();
    this.JavaToCSV(csvFile);
  }

  /**
   * Prompts user for the name of a new file and then creates the new file in the project folder
   * then it copies the database table: EquipmentList into the CSV file
   *
   * @throws SQLException
   */
  public void saveEquipTableAsCSV() throws SQLException {
    // takes entries from SQL table and an input name, from there it makes a new CSV file
    Scanner s = new Scanner(System.in);

    System.out.println("Enter CSV file location name");

    String CSVName = s.nextLine();
    String csvFilePath = "./" + CSVName + ".csv";

    try {
      new File(csvFilePath);
      this.SQLToJava();
      this.JavaToCSV(csvFilePath);

    } catch (IOException e) {
      System.out.println(e.fillInStackTrace());
    }
  }

  // -------------------------------End of debugging backend
  // functions------------------------------------------//

  // -------------------------------Start of frontend to backend
  // functions------------------------------------------//

  /**
   * Asks user for name of item they wish to edit and then ask to change the total amount and the
   * amount in use, Then changes the values in the database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void editEquipValue(
      String csvFile, String inputName, String inputNewAmount, String inputInUse)
      throws IOException, SQLException {
    // takes entries from SQL table that match input node and updates it with a new floor and
    // location type
    // input ID

    this.CSVToJava(csvFile); // t
    for (int i = 0; i < this.EquipmentList.size(); i++) {
      if (this.EquipmentList.get(i).getName().equals(inputName)) {
        this.EquipmentList.get(i).Amount = Integer.parseInt(inputNewAmount);
        this.EquipmentList.get(i).InUse = Integer.parseInt(inputInUse);
      }
    }
    this.JavaToSQL();
    ; // t
    this.SQLToJava(); // t
    this.JavaToCSV(csvFile); // t
  }

  /**
   * Prompts user for the name of a new item and then adds it to the csv file and database
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void addEquip(String csvFile, String newName) throws IOException, SQLException {
    // add a new entry to the SQL table
    // prompt for ID

    Equipment newEquipment = new Equipment(newName);
    this.EquipmentList.add(newEquipment);
    this.JavaToSQL();
    this.SQLToJava();
    this.JavaToCSV(csvFile);
  }

  /**
   * Prompts user for the name of the item they wish to remove and then removes that item from the
   * database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void removeEquip(String csvFile, String userNodeID) throws IOException, SQLException {
    // removes entries from SQL table that match input node
    // prompt for ID

    for (int i = this.EquipmentList.size() - 1; i >= 0; i--) {
      if (this.EquipmentList.get(i).getName().equals(userNodeID)) {
        this.EquipmentList.remove(i);
      }
    }
    this.JavaToSQL();
    this.SQLToJava();
    this.JavaToCSV(csvFile);
  }

  /**
   * Prompts user for the name of a new file and then creates the new file in the project folder
   * then it copies the database table: EquipmentList into the CSV file
   *
   * @throws SQLException
   */
  public void saveEquipTableAsCSV(String CSVName) throws SQLException {
    // takes entries from SQL table and an input name, from there it makes a new CSV file

    String csvFilePath = "./" + CSVName + ".csv";

    try {
      new File(csvFilePath);
      this.SQLToJava();
      this.JavaToCSV(csvFilePath);

    } catch (IOException e) {
      System.out.println(e.fillInStackTrace());
    }
  }
}
