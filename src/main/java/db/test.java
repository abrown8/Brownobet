package db;

import java.sql.*;

public class test {

    public static void main (String[] args) throws SQLException {

        String jdbcUrl = "jdbc:sqlite:sqlite\\world_cup_bet_db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
        String request = "SELECT * FROM users;";
        Statement statement =  connection.createStatement();
        ResultSet result = statement.executeQuery(request);
        while(result.next()){
            String name = result.getString("username");
            String firstname = result.getString("firstname");
            System.out.println("name = "+name + " and firstname = "+firstname);
        }
    }
}
