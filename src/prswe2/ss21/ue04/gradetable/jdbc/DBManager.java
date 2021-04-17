package prswe2.ss21.ue04.gradetable.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import prswe2.ss21.ue04.gradetable.model.Results;
import prswe2.ss21.ue04.gradetable.model.Student;
import prswe2.ss21.ue04.gradetable.model.TestData;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBManager {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DBNAME = "gradetabledb";
    private static final String CONNECTION_URL = "jdbc:derby:" + DBNAME + ";create=true";

    private Connection dbConnection;

    public DBManager() {
        this.dbConnection = null;
    }

    public void openDBConnection() {
        if (dbConnection != null) {
            return; // TODO Exception
        }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("DRIVER class not found!");
            e.printStackTrace();
        }
        try {
            this.dbConnection = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.out.println("DB Connection couldn't be established!");
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            this.dbConnection.close();
            this.dbConnection = null;
        } catch (SQLException e) {
            System.out.println("DB Connection not closed or not opened");
            e.printStackTrace();
        }
    }

    public void createGradeTableDB() {
        this.openDBConnection();
        this.cleanUpDB();
        this.createResultsTable();
        this.testOutputOfResultsTable();
        this.closeDBConnection();
    }

    private void createResultsTable() {
        try {
            Statement s = dbConnection.createStatement();
            StringBuffer sb = new StringBuffer();
            sb.append(
                    "CREATE TABLE results(MAT VARCHAR(10) PRIMARY KEY, FIRSTNAME VARCHAR(50), NAME VARCHAR(50), SN INT, ");
            for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
                sb.append("A" + (i + 1)).append(" INT").append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(")");
            s.addBatch(sb.toString());
            s.executeBatch();
            System.out.println("Results table created!");
        } catch (SQLException se) {
            System.out.println("Results table couldn't be created!");
            se.printStackTrace();
        }
    }

    private void cleanUpDB() {
        try {
            dbConnection.createStatement().execute("DROP TABLE results");
        } catch (SQLException se) {
            System.out.println("Results table not existent, no need for cleanup!");
        }
    }

    public boolean insertResults(Results result) {
        try {
            String mat = result.getStudent().idProperty().get();
            String firstName = result.getStudent().firstNameProperty().get();
            String name = result.getStudent().nameProperty().get();
            int sn = result.getStudent().snProperty().get();

            StringBuffer sb = new StringBuffer();
            sb.append("INSERT INTO results(mat, firstname, name, sn");
            int counter = 1;
            for (IntegerProperty ip : result.getAssignments()) {
                sb.append(", " + "A" + counter);
                counter++;
            }
            sb.append(") ");
            sb.append("VALUES('" + mat + "', '" + firstName + "', '" + name + "', " + sn);
            for (IntegerProperty ip : result.getAssignments()) {
                sb.append(", " + ip.getValue());
                counter++;
            }
            sb.append(")");
            System.out.println(sb.toString());
            this.openDBConnection();
            dbConnection.createStatement().execute(sb.toString());
            this.testOutputOfResultsTable();
            this.closeDBConnection();
            System.out.println("Results inserted: " + result.toString());
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Results couldn't be inserted cause of DataIntegrityIssues (Duplicate Key)");
            return false;
        } catch (SQLException e) {
            System.out.println("Results couldn't be inserted");
            return false;
        }
    }

    public boolean removeResults(Results result) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("DELETE FROM results WHERE mat='" + result.getStudent().idProperty().get() + "'");
            this.openDBConnection();
            this.dbConnection.createStatement().execute(sb.toString());
            this.testOutputOfResultsTable();
            this.closeDBConnection();
            System.out.println("Results removed: " + result.toString());
            return true;
        } catch (SQLException e) {
            System.out.print("Results couldn't be removed!");
            return false;
        }
    }

    public boolean updateAssignmentPoints(Results result, int index, int newPoints) {
        try {
            index++;
            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE results SET A" + index + "=" + newPoints + " WHERE mat = '"
                    + result.getStudent().idProperty().get() + "'");
            this.openDBConnection();
            this.dbConnection.createStatement().execute(sb.toString());
            this.testOutputOfResultsTable();
            this.closeDBConnection();
            System.out.println("Points updated: " + result.toString());
            return true;
        } catch (SQLException e) {
            System.out.print("Points couldn't be updated!");
            return false;
        }
    }

    public ObservableList<Results> getActualDBContent() {
        ObservableList<Results> results = FXCollections.observableArrayList();
        this.openDBConnection();
        try {
            ResultSet rs = dbConnection.createStatement().executeQuery("SELECT * FROM results");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Results r = new Results(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
                    r.setPoints(i, rs.getInt(i + 4));
                }
                results.add(r);
            }
        } catch (SQLException e) {
            System.out.println("DB Content could not be read!");
        }
        this.closeDBConnection();
        return results;
    }

    public ObservableList<Results> insertTestData() {
        ObservableList<Results> results = FXCollections.observableArrayList();
        this.openDBConnection();
        TestData.createData().forEach(r -> {
            if (this.insertResults(r)) {
                results.add(r);
            }
        });
        this.closeDBConnection();
        return results;
    }

    public void testOutputOfResultsTable() {
        try {
            System.out.println("Contents of Results:");
            ResultSet rs = dbConnection.createStatement().executeQuery("SELECT * FROM results");
            ResultSetMetaData rsmd = rs.getMetaData();
            int nColumns = rsmd.getColumnCount();
            for (int i = 1; i <= nColumns; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String colName = rsmd.getColumnName(i);
                System.out.print(colName);
            }
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= nColumns; i++) {
                    if (i > 1)
                        System.out.print(",  ");
                    String colValue = rs.getString(i);
                    System.out.print(colValue);
                }
                System.out.println("");
            }
        } catch (SQLException se) {
            System.out.println("Couldn't query results table");
        }
    }
}