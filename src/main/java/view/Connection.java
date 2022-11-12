package view;

import javax.swing.*;
import java.sql.SQLException;



public class Connection extends JFrame {

    public Connection() throws SQLException {
        super("World Cup Bet");
        this.setSize(600, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setConnectionWindow();
    }

    public void setConnectionWindow() throws SQLException {
        ConnectionWindow cw = new ConnectionWindow(this);
        this.setContentPane(cw);
    }

    public void setCreateUser() throws SQLException {
        CreateUser cu = new CreateUser(this);
        this.setContentPane(cu);
    }
}
