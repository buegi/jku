package prswe2.ss21.ue04.gradetable.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    private static final String TABLE_NAME = "RESULTS";
    private static final String DROP_RESULTS_TABLE = "DROP TABLE " + TABLE_NAME;

    private static final String MATRIKULATION_NUMBER = "MAT";
    private static final String FIRST_NAME = "FIRSTNAME";
    private static final String NAME = "NAME";
    private static final String STUDY_NUMBER = "SN";
    private static final String ASSIGNMENT = "A";

    private static final String SELECT_ALL_RESULTS = "SELECT * FROM " + TABLE_NAME;

    private static final String CREATE_RESULTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + MATRIKULATION_NUMBER
            + " VARCHAR(10) PRIMARY KEY, " + FIRST_NAME + " VARCHAR(50), " + NAME + " VARCHAR(50), " + STUDY_NUMBER
            + " INT, ";

    private final String INSERT_RESULTS;

    private static final String DELETE_RESULTS = "DELETE FROM " + TABLE_NAME + " WHERE " + MATRIKULATION_NUMBER + "=?";

    private static final String UPDATE_RESULTS_PART1 = "UPDATE results SET A"; // column index dynamically
    private static final String UPDATE_RESULTS_PART2 = "=? WHERE " + MATRIKULATION_NUMBER + "=?";

    private Connection dbConnection;

    public DBManager() {
        this.dbConnection = null;
        // create insert statement String based on number of Assignments
        this.INSERT_RESULTS = this.createInsertResultsSQLString();
    }

    public String createInsertResultsSQLString() {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO " + TABLE_NAME + "(" + MATRIKULATION_NUMBER + ", " + FIRST_NAME + ", " + NAME + ", "
                + STUDY_NUMBER);
        for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
            sb.append(", ").append("A").append(i + 1);
        }
        sb.append(") VALUES(?, ?, ?, ?");
        for (int j = 0; j < Results.NR_ASSIGNMENTS; j++) {
            sb.append(", ?");
        }
        sb.append(")");
        return sb.toString();
    }

    public void openDBConnection() {
        if (dbConnection != null) {
            return;
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
        this.dropResultsTable();
        this.createResultsTable();
        this.testOutputOfResultsTable(); // TODO just for testing, can be removed
        this.closeDBConnection();
    }

    private void createResultsTable() {
        try {
            Statement s = dbConnection.createStatement();
            StringBuffer sb = new StringBuffer();
            sb.append(CREATE_RESULTS_TABLE);
            for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
                sb.append(ASSIGNMENT + (i + 1)).append(" INT").append(", ");
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

    private void dropResultsTable() {
        try {
            dbConnection.createStatement().execute(DROP_RESULTS_TABLE);
        } catch (SQLException se) {
            System.out.println("Results table not existent, no need for removing!");
        }
    }

    public boolean insertResults(Results result) {
        try {
            this.openDBConnection();
            PreparedStatement insert = this.dbConnection.prepareStatement(INSERT_RESULTS);
            insert.setString(1, result.getStudent().idProperty().get());
            insert.setString(2, result.getStudent().firstNameProperty().get());
            insert.setString(3, result.getStudent().nameProperty().get());
            insert.setInt(4, result.getStudent().snProperty().get());
            int counter = 5;
            for (IntegerProperty ip : result.getAssignments()) {
                insert.setInt(counter, ip.getValue());
                counter++;
            }
            insert.executeUpdate();
            this.testOutputOfResultsTable(); // TODO just for testing, can be removed
            this.closeDBConnection();
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
            this.openDBConnection();
            PreparedStatement remove = this.dbConnection.prepareStatement(DELETE_RESULTS);
            remove.setString(1, result.getStudent().idProperty().get());
            remove.executeUpdate();
            this.testOutputOfResultsTable(); // TODO just for testing, can be removed
            this.closeDBConnection();
            return true;
        } catch (SQLException e) {
            System.out.print("Results couldn't be removed!");
            return false;
        }
    }

    public boolean updateAssignmentPoints(Results result, int index, int newPoints) {
        try {
            index++; // increase index by one to match assignments not starting by 0, but 1 in table
            this.openDBConnection();
            PreparedStatement update = this.dbConnection
                    .prepareStatement(UPDATE_RESULTS_PART1 + index + UPDATE_RESULTS_PART2);
            update.setInt(1, newPoints);
            update.setString(2, result.getStudent().idProperty().get());
            update.executeUpdate();
            this.testOutputOfResultsTable(); // TODO just for testing, can be removed
            this.closeDBConnection();
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
            ResultSet rs = dbConnection.createStatement().executeQuery(SELECT_ALL_RESULTS);
            while (rs.next()) {
                Results r = new Results(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
                    r.setPoints(i, rs.getInt(i + 5));
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

    // just for testing, used for interactive db checking output, can be removed /
    // ignored
    public void testOutputOfResultsTable() {
        try {
            System.out.println("Contents of Results:");
            ResultSet rs = dbConnection.createStatement().executeQuery(SELECT_ALL_RESULTS);
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