package edu.wpi.team_u.BackEnd.Equipment;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public interface EquipmentDao {

  public void CSVToJava(InputStream csvFile) throws IOException;

  public void JavaToSQL();

  public void SQLToJava() throws SQLException;

  public void JavaToCSV(String csvFile) throws IOException;

  public void printEquipTableInTerm(String csvFile) throws IOException;

  public void editEquipValue(String csvFile) throws IOException, SQLException;

  public void addEquip(String csvFile) throws IOException, SQLException;

  public void removeEquip(String csvFile) throws IOException, SQLException;

  public void saveEquipTableAsCSV() throws SQLException;
}
