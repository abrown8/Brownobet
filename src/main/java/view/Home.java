package view;

import db.SQLite;
import javafx.scene.effect.Blend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Home extends JPanel implements ActionListener, MouseListener {

    private WorldCupBet instance;
    private SQLite db = new SQLite();
    private String username;
    private String firstname;
    private String lastname;
    private boolean hasBet;
    private JButton betBtn = new JButton();
    private JButton seeBetBtn = new JButton();
    private Color purple1 = new Color(155, 0, 176);
    private Color blue1 = new Color(45, 11, 137);
    private Color blue2 = new Color(240, 237, 247);

    public Home(WorldCupBet instance, String username, String firstname, String lastname) throws SQLException {
        this.instance = instance;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.setBackground(this.blue2);
        this.betBtn.addActionListener(this);
        this.seeBetBtn.addActionListener(this);


        ImageIcon betImage = new ImageIcon("icon/Bet1.png");
        ImageIcon rankingImage = new ImageIcon("icon/ranking.png");
        this.betBtn.setIcon(betImage);
        this.betBtn.setBackground(this.purple1);
        this.add(this.betBtn);
        this.seeBetBtn.setIcon(rankingImage);
        this.seeBetBtn.setBackground(this.purple1);
        this.add(this.seeBetBtn);
        this.hasBet = doHasBet(this.username);

        this.betBtn.addMouseListener(this);
        this.seeBetBtn.addMouseListener(this);

    }

    private boolean doHasBet(String user) throws SQLException {
        String selectReq = "SELECT * FROM bets WHERE user='"+user+"';";
        if (this.db.select(selectReq).isEmpty()){
            return false;
        }
        return true;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.betBtn){
            this.setVisible(false);
            try {
                this.instance.setMakeBet(this.hasBet);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==this.seeBetBtn){
            this.setVisible(false);
            try {
                this.instance.setSeeBet();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource()==this.betBtn){
            this.betBtn.setBackground(Color.YELLOW);
        }
        if (e.getSource()==this.seeBetBtn){
            this.seeBetBtn.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==this.betBtn){
            this.betBtn.setBackground(this.blue1);
        }
        if (e.getSource()==this.seeBetBtn){
            this.seeBetBtn.setBackground(this.blue1);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==this.betBtn){
            this.betBtn.setBackground(this.purple1);
        }
        if (e.getSource()==this.seeBetBtn){
            this.seeBetBtn.setBackground(this.purple1);
        }
    }
}
