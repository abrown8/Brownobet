package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLite {

    private String jdbcUrl = "jdbc:sqlite:sqlite\\world_cup_bet_db";
    private Connection connection;
    private Statement statement;

    public SQLite() throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl);
        this.statement = connection.createStatement();
    }

    public HashMap<String, ArrayList<String>> select(String request) throws SQLException {
        ResultSet rs = this.statement.executeQuery(request);
        HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        ResultSetMetaData resultMD = rs.getMetaData();
        int count = resultMD.getColumnCount();
        ArrayList<String> colArray = new ArrayList<>();
        ArrayList<String> valArray = new ArrayList<>();
        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                String column = resultMD.getColumnName(i);
                colArray.add(column);
                String value = rs.getString(column);
                valArray.add(value);
            }
        }
        for (int i=0 ; i<colArray.size() ; i++){
            String col = colArray.get(i);
            String val = valArray.get(i);
            if (!result.containsKey(col)){
                ArrayList<String> initValueArray = new ArrayList<>();
                initValueArray.add(val);
                result.put(col, initValueArray);
            }
            else{
                ArrayList<String> values = result.get(col);
                values.add(val);
                result.replace(col, values);
            }
        }
        return result;
    }
    public void alter(String request) throws SQLException {
        this.statement.executeUpdate(request);
    }

    public void delete(String deleteRequest) throws SQLException {
        PreparedStatement pstmt = this.connection.prepareStatement(deleteRequest);
        pstmt.executeUpdate();
    }

    public void insert(String insertRequest) throws SQLException {
        this.statement.executeUpdate(insertRequest);
    }
}
