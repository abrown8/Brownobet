package view;

import db.SQLite;
import javafx.scene.input.InputMethodTextRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUser extends JPanel implements ActionListener {
    private Connection instance;
    private SQLite db = new SQLite();
    private JButton confirmNewUserBtn = new JButton("Create user");
    private JTextField tfLabInitial = new JTextField(5);
    private JTextField tfFirstname = new JTextField(15);
    private JTextField tfLastname = new JTextField(15);
    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);
    public CreateUser(Connection instance) throws SQLException {
        this.instance = instance;
        this.confirmNewUserBtn.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(this.purple1, 3));
        this.setBackground(this.bgColor);

        JPanel labInitialPanel = new JPanel();
        labInitialPanel.setBackground(this.bgColor);
        JLabel labInitialLabel = new JLabel("Lab initial :");
        labInitialLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        labInitialLabel.setForeground(this.purple1);
        labInitialPanel.add(labInitialLabel);
        labInitialPanel.add(this.tfLabInitial);

        JPanel firstnamePanel = new JPanel();
        firstnamePanel.setBackground(this.bgColor);
        JLabel firstnameLabel = new JLabel("First name :");
        firstnameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        firstnameLabel.setForeground(this.purple1);
        firstnamePanel.add(firstnameLabel);
        firstnamePanel.add(this.tfFirstname);

        JPanel lastnamePanel = new JPanel();
        lastnamePanel.setBackground(this.bgColor);
        JLabel lastnameLabel = new JLabel("Last name :");
        lastnameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        lastnameLabel.setForeground(this.purple1);
        lastnamePanel.add(lastnameLabel);
        lastnamePanel.add(this.tfLastname);

        JPanel confirmPan = new JPanel();
        confirmPan.setBackground(this.bgColor);
        confirmPan.add(this.confirmNewUserBtn);

        this.add(labInitialPanel);
        this.add(firstnamePanel);
        this.add(lastnamePanel);
        this.add(confirmPan);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.confirmNewUserBtn){
            String username = this.tfLabInitial.getText();
            String firstname = this.tfFirstname.getText();
            String lastname = this.tfLastname.getText();
            if (!(username.length() > 6)){
                String sqlInsert = "INSERT INTO users VALUES ('"+username+"', '"+firstname+"', '"+lastname+"');";
                try {
                    db.insert(sqlInsert);
                } catch (SQLException error) {
                    throw new RuntimeException(error);
                }
                try {
                    this.instance.setConnectionWindow();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{
                System.out.println("Error, username is too long");
            }
        }
    }
}
