package view;

import db.SQLite;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.sql.SQLException;

public class WorldCupBet extends JFrame {

    private String username;
    private String firstname;
    private String lastname;
    private JScrollPane homePanel;
    private SQLite db = new SQLite();
    public WorldCupBet(String username, String firstname, String lastname) throws SQLException {
        super("World cup bet");
        this.setSize(1300, 855);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;

        
        this.setJMenuBar(createMenuBar());
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        JButton home = new JButton("Home");
        home.addActionListener((event) -> {
            try {
                setHome();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        menuFile.setMnemonic('F');
        menuHelp.setMnemonic('H');
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        JSeparator sep = new JSeparator(1);
        menuBar.add(sep);
        menuBar.add(home);

        return menuBar;
    }

    public void setHome() throws SQLException{
        Home home = new Home(this, this.username, this.firstname, this.lastname);
        this.setContentPane(home);
        this.revalidate();
    }
    public void setMakeBet(boolean hasBet) throws SQLException {
        MakeBetWindow mbw = new MakeBetWindow(this, hasBet);
        this.setContentPane(mbw);
    }

    public void setSeeBet() throws SQLException {
        SeeBetWindow sbw = new SeeBetWindow(this);
        this.setContentPane(sbw);
    }
    public String getUsername(){
        return this.username;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public String getLastname(){
        return this.lastname;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, SQLException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Connection connectionFrame = new Connection();
        connectionFrame.setVisible(true);
    }



}
