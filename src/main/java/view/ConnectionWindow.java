package view;

import db.SQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionWindow extends JPanel implements ActionListener {

    private Connection instance;
    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);
    private JButton connectionBtn = new JButton("Connection");
    private JButton createUserBtn = new JButton("Create new user");
    private JComboBox selectUserCB = new JComboBox();
    private SQLite db = new SQLite();


    public ConnectionWindow(Connection instance) throws SQLException {
        this.instance = instance;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(this.purple1, 3));
        this.setBackground(this.bgColor);

        JPanel createUserPanel = new JPanel();
        createUserPanel.setBackground(this.bgColor);
        createUserPanel.add(this.createUserBtn);
        this.createUserBtn.addActionListener(this);

        JPanel cbPan = new JPanel();
        cbPan.setBackground(this.bgColor);
        JLabel tfLabel = new JLabel("Select your username :");
        tfLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        tfLabel.setForeground(this.purple1);
        cbPan.add(tfLabel);
        fillCB();
        cbPan.add(this.selectUserCB);

        JPanel btnPan = new JPanel();
        btnPan.setBackground(this.bgColor);
        this.connectionBtn.setForeground(this.bgColor);
        this.connectionBtn.setBackground(this.purple1);
        this.connectionBtn.addActionListener(this);
        this.connectionBtn.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        btnPan.add(this.connectionBtn);

        this.add(createUserPanel);
        this.add(cbPan);
        this.add(btnPan);
    }

    private void fillCB() throws SQLException {
        String selectReq = "SELECT username FROM users";
        HashMap<String, ArrayList<String>> selectResult = db.select(selectReq);
        ArrayList<String> userList = selectResult.get("username");
        for (String username : userList){
            if (!username.equals("RNG1") && !username.equals("RNG2")){
                this.selectUserCB.addItem(username);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.connectionBtn){
            String username = this.selectUserCB.getSelectedItem().toString();
            try{
                SQLite db = new SQLite();
                String selectRequest = "SELECT * FROM users WHERE username='"+username+"';";
                HashMap<String, ArrayList<String>> result = db.select(selectRequest);
                String firstname = result.get("firstname").get(0);
                String lastname = result.get("lastname").get(0);
                WorldCupBet wcb = new WorldCupBet(username, firstname, lastname);
                wcb.setHome();
                this.instance.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == this.createUserBtn){
            try {
                this.setVisible(false);
                this.instance.setCreateUser();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
