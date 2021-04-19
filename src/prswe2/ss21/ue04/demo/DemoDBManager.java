package prswe2.ss21.ue04.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prswe2.ss21.ue04.demo.person.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DemoDBManager {

    // DB QUERY STRINGS
    private static final String DB_URL = "jdbc:derby:demoDB;create=true";
    private static final String TABLE_NAME = "Persons";
    private static final String PERSON_ID = "ID";
    private static final String PERSON_FIRST_NAME = "First_Name";
    private static final String PERSON_LAST_NAME = "Last_Name";

    private static final String IMPORT_PERSONS = "SELECT * from " + TABLE_NAME;

    private static final String SELECT_PERSONS = "SELECT * from " + TABLE_NAME + " WHERE " + PERSON_LAST_NAME + "=?";
    private static final int SELECT_PERSON_PARAM_LAST_NAME = 1;

    private static final String INSERT_PERSON = "INSERT INTO " + TABLE_NAME +
            " (" + PERSON_FIRST_NAME + ", " + PERSON_LAST_NAME + ") VALUES(?,?)";
    private static final int INSERT_PERSON_PARAM_FIRST_NAME = 1;
    private static final int INSERT_PERSON_PARAM_LAST_NAME = 2;

    private static final String UPDATE_PERSON_PROPERTY = "UPDATE " + TABLE_NAME + " SET %s=? WHERE "
            + PERSON_ID + "=?";
    private static final int UPDATE_PARAM_VALUE = 1;
    private static final int UPDATE_PARAM_ID = 2;

    // DBManager fields
    private Connection dbConnection;

    public DemoDBManager() {
        dbConnection = null;
    }

    public void printMetadata() throws SQLException {
        DatabaseMetaData metaData = dbConnection.getMetaData();
        ResultSet tableMetaData = metaData.getTables(null, null, null, null);
        while (tableMetaData.next()) {
            System.out.println(tableMetaData.getString("TABLE_NAME") + ", " + tableMetaData.getString("TABLE_TYPE")
                    + ", " + tableMetaData.getString("TABLE_SCHEM"));
        }
    }

    public void openConnection(boolean newDb) {
        if (dbConnection != null) {
            return;    // throw proper exception
        }

        try {
            dbConnection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (newDb) {
            deleteTables();
            createTables();
        }
    }

    void closeConnection() {
        if (dbConnection == null) {
            throw new IllegalStateException("Connection was already closed");
        }

        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not close database", e);
        }
    }

    private void deleteTables() {
        try (Statement statement = dbConnection.createStatement()) {
            statement.execute(String.format("DROP TABLE %s", TABLE_NAME));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {

        try (Statement statement = dbConnection.createStatement()) {
            statement.execute(String.format("CREATE TABLE %s ("
                            + "%s INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "%s VARCHAR(15), " +
                            "%s VARCHAR(30))",
                    TABLE_NAME, PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addPerson(String firstName, String lastName) {
        try (PreparedStatement insertStatement = dbConnection.prepareStatement(INSERT_PERSON,
                Statement.RETURN_GENERATED_KEYS)) {

            insertStatement.setString(INSERT_PERSON_PARAM_FIRST_NAME, firstName);
            insertStatement.setString(INSERT_PERSON_PARAM_LAST_NAME, lastName);

            final int affectedRows = insertStatement.executeUpdate();
            if (affectedRows != 1) {
                throw new RuntimeException("Failed to add new person to database");
            }

            // return created person ID (primary key)
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateProperty(String colName, String val, int personID) {
        try (final PreparedStatement updateStatement = dbConnection
                .prepareStatement(String.format(UPDATE_PERSON_PROPERTY, colName))) {
            updateStatement.setString(UPDATE_PARAM_VALUE, val); // works only for String columns
            updateStatement.setInt(UPDATE_PARAM_ID, personID);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> importPersons() {
        final List<Person> imported = new ArrayList<>();

        try {
            // try-statements for auto-closing
            try (PreparedStatement personQuery = dbConnection.prepareStatement(IMPORT_PERSONS)) {
                try (final ResultSet items = personQuery.executeQuery()) {
                    while (items.next()) {
                        final Person item = new Person(items.getInt(PERSON_ID), items.getString(PERSON_FIRST_NAME), items.getString(PERSON_LAST_NAME));

                        item.firstNameProperty().addListener(new ChangeListener<String>() {

                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                updateProperty("First_Name", newValue, item.getID());
                            }

                        });
                        imported.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return imported;
    }

    public List<Person> selectPersonsByLastName(String lastName) {
        final List<Person> selected = new ArrayList<>();

        // vulnerable query (in derby multiple ";"-separated statements are not allowed)
//		try {
//			// try-statements for auto-closing
//			try (Statement personQuery = dbConnection.createStatement()) {
//				System.out.println("SELECT * from " + TABLE_NAME + " WHERE " + PERSON_LAST_NAME + "='" + lastName + "'");
//				try (final ResultSet items = personQuery.executeQuery("SELECT * from " + TABLE_NAME + " WHERE " + PERSON_LAST_NAME + "='" + lastName + "'")) {
//					while (items.next()) {
//						final Person item = new Person(items.getInt(PERSON_ID), items.getString(PERSON_FIRST_NAME), items.getString(PERSON_LAST_NAME));
//
//						selected.add(item);
//					}
//				}
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}


        try {
            // try-statements for auto-closing
            try (PreparedStatement personQuery = dbConnection.prepareStatement(SELECT_PERSONS)) {
                personQuery.setString(SELECT_PERSON_PARAM_LAST_NAME, lastName);
                try (final ResultSet items = personQuery.executeQuery()) {
                    while (items.next()) {
                        final Person item = new Person(items.getInt(PERSON_ID), items.getString(PERSON_FIRST_NAME), items.getString(PERSON_LAST_NAME));

                        selected.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selected;
    }

    public static void main(String[] args) throws SQLException {
        DemoDBManager dbManager = new DemoDBManager();
        dbManager.openConnection(true);
        dbManager.printMetadata();

        List<Person> dbContent = dbManager.importPersons();

        System.out.println("Before insert:");
        for (Person p : dbContent) {
            System.out.println(p);
        }

        int ID = dbManager.addPerson("Raphael", "Mosaner"); // could be done via listener in an ObservableList
//		int ID = dbManager.addPerson("Raphael", "Mosaner);DROP TABLE PERSONS--"); // string with injection
        Person p1 = new Person(ID, "Raphael", "Mosaner");


        // write proper generic version of this (overwrite ChangeListener for example),
        // which will be called implicitly upon Person creation
        p1.firstNameProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                dbManager.updateProperty("First_Name", newValue, p1.getID());
            }

        });
        ID = dbManager.addPerson("Jacob", "Kreindl"); // could be done via listener in an ObservableList
        Person p2 = new Person(ID, "Jacob", "Kreindl");

        System.out.println("\nAfter insert:");
        dbContent = dbManager.importPersons();
        for (Person p : dbContent) {
            System.out.println(p);
        }

        p1.firstNameProperty().set("Better Raphael");
        System.out.println("\nAfter update:");
        for (Person p : dbManager.selectPersonsByLastName("Mosaner")) { // "Mosaner' OR '1'='1" for SQL injection
            System.out.println(p);
        }

        dbManager.closeConnection();
    }


}
