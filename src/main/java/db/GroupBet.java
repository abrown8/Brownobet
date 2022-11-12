package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupBet {

    public GroupBet(SQLite db, String user, String team, boolean result) throws SQLException {
        if (!firstBet(user, db)){
            String deleteReq = "DELETE FROM bets WHERE user='"+user+"';";
            db.delete(deleteReq);
        }
        String insertReq = "INSERT INTO bets (user, team, type, result) VALUES ('"+user+"', '"+team+"', 'group', '"+result+"');";
        db.insert(insertReq);
    }

    private boolean firstBet(String user, SQLite db) throws SQLException {
        String selectReq = "SELECT * FROM bets WHERE user='"+user+"';";
        HashMap<String, ArrayList<String>> result = db.select(selectReq);
        if (result.isEmpty()){
            return false;
        }
        return true;
    }

    private void insertInDatatase() {

    }
}
