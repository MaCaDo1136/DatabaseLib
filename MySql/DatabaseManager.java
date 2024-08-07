 /*
 * Written by Mario Casas
 */

 package MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (SQLException e) {  
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexion cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //Database Manager

    public void insertData(String tableName, String data, String value) {
        try {
            String sql = "INSERT INTO " + tableName + "(" + data + ")" + " VALUES (" + value + ")";
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(String tableName, String newData, String newValue, String refData, String refValue) {
        try {
            String sql = "UPDATE " + tableName + " SET " + newData + " = " + newValue + " WHERE " + refData + " = " + refValue;
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable(String tableName) {
        try {
            String sql = "DROP TABLE " + tableName;
            connection.createStatement().executeUpdate(sql);
            System.out.println("Tabla eliminada con exito");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void selectTable(String tableName) {
        try {
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = connection.createStatement().executeQuery(sql);

            System.out.printf("%-10s %-20s %-20s %-20s%n", "ID", "Nombre", "Primer Apellido", "Segundo Apellido");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");

                System.out.printf("%-10d %-20s %-20s %-20s%n", id, nombre, primerApellido, segundoApellido);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteData(String tableName, String idNum) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE id = " + idNum;
            connection.createStatement().executeUpdate(sql); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Advanced Database Manager

    public void updateLine(String tableName, String[] newData, String[] newValue, String refData, String refValue) {
        for (int i = 0; i < newData.length; i++) {
            updateData(tableName, newData[i], newValue[i], refData, refValue);
        }
    }

    public void insertLine(DatabaseManager db, String tableName, String refData, String refValue, String newData[], String newValue[]) {
        db.insertData(tableName, refData, refValue);

        updateLine(tableName, newData, newValue, refData, refValue);
    }

    public void selectData(String tableName) {
        try {
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = connection.createStatement().executeQuery(sql);

            System.out.printf("%-10s %-20s %-20s %-20s%n", "ID", "Nombre", "Primer Apellido", "Segundo Apellido");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");

                System.out.printf("%-10d %-20s %-20s %-20s%n", id, nombre, primerApellido, segundoApellido);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}

}
