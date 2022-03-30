package edu.wpi.team_u.BackEnd.Equipment;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public interface EquipmentDao {

<<<<<<< HEAD
  public void CSVToJava(InputStream csvFile) throws IOException;
=======
  /**
   * Reads CSV file and puts the Equipment into an array list: EquipmentList
   *
   * @param csvFile
   * @throws IOException
   */
  public void CSVToJava(String csvFile) throws IOException;
>>>>>>> 40cdb6e83b70fee1f789ff632a6f5c92a3992785

  /**
   * Reads the array list: EquipmentList, then opens up a connection to the UDB database, then it
   * creates a new table called in the UDB database table: EquipmentList. It then inserts the array
   * list: EquipmentList into the UDB database table: EquipmentList
   */
  public void JavaToSQL();

  /**
   * Clears the array list: EquipmentList and then reads the UDB database table: EquipmentList then
   * copies the to the cleared array list
   *
   * @throws SQLException
   */
  public void SQLToJava() throws SQLException;

  /**
   * Copies the array list: EquipmentList and writes it into the CSV file
   *
   * @param csvFile
   * @throws IOException
   */
  public void JavaToCSV(String csvFile) throws IOException;

  /**
   * Prints out the Contents of the CSV file TowerEquipment.csv
   *
   * @param csvFile
   * @throws IOException
   */
  public void printEquipTableInTerm(String csvFile) throws IOException;


  //-------------------------------Start of frontend to backend functions------------------------------------------//

  /**
   * Asks user for name of item they wish to edit and then ask to change the total amount and the
   * amount in use, Then changes the values in the database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void editEquipValue(String csvFile, String inputName, String inputNewAmount, String inputInUse) throws IOException, SQLException;

  /**
   * Prompts user for the name of a new item and then adds it to the csv file and database
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void addEquip(String csvFile, String newName) throws IOException, SQLException;

  /**
   * Prompts user for the name of the item they wish to remove and then removes that item from the
   * database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void removeEquip(String csvFile, String userNodeID) throws IOException, SQLException;

  /**
   * Prompts user for the name of a new file and then creates the new file in the project folder
   * then it copies the database table: EquipmentList into the CSV file
   * @throws SQLException
   */
  public void saveEquipTableAsCSV(String CSVName) throws SQLException;

  //-------------------------------End of frontend to backend functions------------------------------------------//




  //-------------------------------Start of debugging backend functions------------------------------------------//

  /**
   * Asks user for name of item they wish to edit and then ask to change the total amount and the
   * amount in use, Then changes the values in the database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void editEquipValue(String csvFile) throws IOException, SQLException;

  /**
   * Prompts user for the name of a new item and then adds it to the csv file and database
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void addEquip(String csvFile) throws IOException, SQLException;

  /**
   * Prompts user for the name of the item they wish to remove and then removes that item from the
   * database and csv file
   *
   * @param csvFile
   * @throws IOException
   * @throws SQLException
   */
  public void removeEquip(String csvFile) throws IOException, SQLException;

  /**
   * Prompts user for the name of a new file and then creates the new file in the project folder
   * then it copies the database table: EquipmentList into the CSV file
   *
   * @throws SQLException
   */
  public void saveEquipTableAsCSV() throws SQLException;


}
