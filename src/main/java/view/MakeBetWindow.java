package view;

import db.GroupBet;
import db.SQLite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakeBetWindow extends JPanel implements ActionListener {

    private WorldCupBet instance;
    private SQLite db = new SQLite();
    private JPanel errorMessagePanel;
    private Group groupA;
    private Group groupB;
    private Group groupC;
    private Group groupD;
    private Group groupE;
    private Group groupF;
    private Group groupG;
    private Group groupH;
    private ArrayList<String> teams = new ArrayList<>();
    private Font font1 = new Font("Arial Unicode MS", Font.PLAIN, 20);

    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);
    private Color blue1 = new Color(45, 11, 137);
    private Color blue2 = new Color(240, 237, 247);
    private JButton confirmBtn = new JButton("Confirm my predictions");
    public MakeBetWindow(WorldCupBet instance, boolean hasBet) throws SQLException {
        this.instance = instance;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillTeams();
        ArrayList<String> teamPredictedQualify = new ArrayList<>();
        if (hasBet){
            teamPredictedQualify = getPredictedTeams("group");
        }

        JPanel headerPanel = getHeaderPanel();
        JPanel groupsPanel = getGroupsPanel(teamPredictedQualify);
        this.errorMessagePanel = getErrorMessagePanel();
        JPanel confirmPanel = getConfirmPanel();



        this.add(headerPanel);
        this.add(groupsPanel);
        this.add(this.errorMessagePanel);
        this.add(confirmPanel);

        errorMessagePanel.setVisible(false);

    }

    private void deleteBet() throws SQLException {
        String deleteReq = "DELETE FROM bets WHERE user='"+this.instance.getUsername()+"';";
        this.db.delete(deleteReq);
    }

    private void fillTeams() {
        this.teams.add("Qatar");
        this.teams.add("Ecuador");
        this.teams.add("Senegal");
        this.teams.add("Netherlands");
        this.teams.add("England");
        this.teams.add("Iran");
        this.teams.add("United States");
        this.teams.add("Wales");
        this.teams.add("Argentina");
        this.teams.add("Saudi Arabia");
        this.teams.add("Mexico");
        this.teams.add("Poland");
        this.teams.add("France");
        this.teams.add("Australia");
        this.teams.add("Denmark");
        this.teams.add("Tunisia");
        this.teams.add("Spain");
        this.teams.add("Costa Rica");
        this.teams.add("Germany");
        this.teams.add("Japan");
        this.teams.add("Belgium");
        this.teams.add("Canada");
        this.teams.add("Morocco");
        this.teams.add("Croatia");
        this.teams.add("Brazil");
        this.teams.add("Serbia");
        this.teams.add("Switzerland");
        this.teams.add("Cameroon");
        this.teams.add("Portugal");
        this.teams.add("Ghana");
        this.teams.add("Uruguay");
        this.teams.add("South Korea");
    }

    private ArrayList<String> getPredictedTeams(String round) throws SQLException {
        String selectReq = "SELECT * FROM bets WHERE user='"+this.instance.getUsername()+"' AND type='"+round+"' AND result='true';";
        return this.db.select(selectReq).get("team");
    }

    private JPanel getErrorMessagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(this.bgColor);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

        JLabel errorMessage = new JLabel("Please select 2 teams for each group !");
        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(this.font1);

        panel.add(errorMessage);
        return panel;
    }

    private JPanel getConfirmPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(this.blue2);
        this.confirmBtn.addActionListener(this);
        this.confirmBtn.setForeground(this.blue1);
        this.confirmBtn.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        panel.add(this.confirmBtn);
        return panel;
    }

    private JPanel getHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(this.blue2);
        JLabel indications = new JLabel("Indicate the 2 teams per group that you see qualifying for the next round.");
        indications.setForeground(this.blue1);
        indications.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        panel.add(indications);
        return panel;
    }

    private JPanel getGroupsPanel(ArrayList<String> teamPredictedQualify) {
        ArrayList<Group> groups = teamBuilder(teamPredictedQualify);
        JPanel panel = new JPanel();
        panel.setBackground(this.blue2);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(groups.get(0), c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(groups.get(1), c);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(groups.get(2), c);
        c.gridx = 3;
        c.gridy = 0;
        panel.add(groups.get(3), c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(groups.get(4), c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(groups.get(5), c);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(groups.get(6), c);
        c.gridx = 3;
        c.gridy = 1;
        panel.add(groups.get(7), c);

        return panel;
    }

    private ArrayList<Group> teamBuilder(ArrayList<String> teamPredictedQualify) {
        int cpt = 0;
        ArrayList<Team> preGroup = null;
        ArrayList groups = new ArrayList();
        System.out.println(groups.size());
        for (int i=0 ; i<this.teams.size() ; i++){
            String team = this.teams.get(i);
            String flag = "icon/" + Character.toLowerCase(team.charAt(0)) + team.substring(1) + ".png";
            if (team.contains(" ")) {
                String[] teamSplit = team.split(" ");
                flag = "icon/" + Character.toLowerCase(teamSplit[0].charAt(0)) + teamSplit[0].substring(1) + "_" + Character.toLowerCase(teamSplit[1].charAt(0)) + teamSplit[1].substring(1) + ".png";
            }
            if (cpt == 0) {
                preGroup = new ArrayList<>();
            }

            Team teamObj = new Team(team, flag);

            if (teamPredictedQualify.contains(team)){
                teamObj.quoteCB();
            }
            preGroup.add(teamObj);
            cpt++;
            if (cpt==4){
                cpt=0;
                if (team.equals("Netherlands")){
                    this.groupA = new Group("Group A", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupA);
                    }
                    groups.add(this.groupA);
                }else if (team.equals("Wales")){
                    this.groupB = new Group("Group B", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupB);
                    }
                    groups.add(this.groupB);
                }else if (team.equals("Poland")){
                    this.groupC = new Group("Group C", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupC);
                    }
                    groups.add(this.groupC);
                }else if (team.equals("Tunisia")){
                    this.groupD = new Group("Group D", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupD);
                    }
                    groups.add(this.groupD);
                }else if (team.equals("Japan")){
                    this.groupE = new Group("Group E", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupE);
                    }
                    groups.add(this.groupE);
                }else if (team.equals("Croatia")){
                    this.groupF = new Group("Group F", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupF);
                    }
                    groups.add(this.groupF);
                }else if (team.equals("Cameroon")){
                    this.groupG = new Group("Group G", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupG);
                    }
                    groups.add(this.groupG);
                } else{
                    this.groupH = new Group("Group H", preGroup.get(0), preGroup.get(1), preGroup.get(2), preGroup.get(3));
                    for (Team t : preGroup){
                        t.setGroup(this.groupG);
                    }
                    groups.add(this.groupH);
                }
            }
        }
        return groups;
        /*Team qatar = new Team("Qatar", "icon/qatar.png");
        Team ecuador = new Team("Ecuador", "icon/ecuador.png");
        Team senegal = new Team("Senegal", "icon/senegal.png");
        Team netherlands = new Team("Netherlands", "icon/netherlands.png");
        this.groupA = new Group("Groupe A", qatar, ecuador, senegal, netherlands);
        qatar.setGroup(this.groupA);
        ecuador.setGroup(this.groupA);
        senegal.setGroup(this.groupA);
        netherlands.setGroup(this.groupA);

        Team england = new Team("England", "icon/england.png");
        Team iran = new Team("Iran", "icon/iran.png");
        Team united_states = new Team("United States", "icon/germany.png");
        Team wales = new Team("Wales", "icon/united_states.png");
        this.groupB = new Group("Groupe B", england, iran, united_states, wales);
        england.setGroup(this.groupB);
        iran.setGroup(this.groupB);
        united_states.setGroup(this.groupB);
        wales.setGroup(this.groupB);

        Team argentina = new Team("Argentina", "icon/argentina.png");
        Team saudi_arabia = new Team("Saudi Arabia", "icon/saudi_arabia.png");
        Team mexico = new Team("Mexico", "icon/mexico.png");
        Team poland = new Team("Poland", "icon/poland.png");
        this.groupC = new Group("Groupe C", argentina, saudi_arabia, mexico, poland);
        argentina.setGroup(this.groupC);
        saudi_arabia.setGroup(this.groupC);
        mexico.setGroup(this.groupC);
        poland.setGroup(this.groupC);

        Team france = new Team("France", "icon/france.png");
        Team australia = new Team("Australia", "icon/australia.png");
        Team denmark = new Team("Denmark", "icon/denmark.png");
        Team tunisia = new Team("Tunisia", "icon/tunisia.png");
        this.groupD = new Group("Groupe D", france, australia, denmark, tunisia);
        france.setGroup(this.groupD);
        australia.setGroup(this.groupD);
        denmark.setGroup(this.groupD);
        tunisia.setGroup(this.groupD);

        Team spain = new Team("Spain", "icon/spain.png");
        Team costa_rica = new Team("Costa Rica", "icon/costa_rica.png");
        Team germany = new Team("Germany", "icon/germany.png");
        Team japan = new Team("Japan", "icon/japan.png");
        this.groupE = new Group("Groupe E", spain, costa_rica, germany, japan);
        spain.setGroup(this.groupE);
        costa_rica.setGroup(this.groupE);
        germany.setGroup(this.groupE);
        japan.setGroup(this.groupE);

        Team belgium = new Team("Belgium", "icon/belgium.png");
        Team canada = new Team("Canada", "icon/canada.png");
        Team morocco = new Team("Morocco", "icon/morocco.png");
        Team croatia = new Team("Croatia", "icon/croatia.png");
        this.groupF = new Group("Groupe F", belgium, canada, morocco, croatia);
        belgium.setGroup(this.groupF);
        canada.setGroup(this.groupF);
        morocco.setGroup(this.groupF);
        croatia.setGroup(this.groupF);

        Team brazil = new Team("Brazil", "icon/brazil.png");
        Team serbia = new Team("Serbia", "icon/serbia.png");
        Team switzerland = new Team("Switzerland", "icon/switzerland.png");
        Team cameroon = new Team("Cameroon", "icon/cameroon.png");
        this.groupG = new Group("Groupe G", brazil, serbia, switzerland, cameroon);
        brazil.setGroup(this.groupG);
        serbia.setGroup(this.groupG);
        switzerland.setGroup(this.groupG);
        cameroon.setGroup(this.groupG);


        Team portugal = new Team("Portugal", "icon/portugal.png");
        Team ghana = new Team("Ghana", "icon/ghana.png");
        Team uruguay = new Team("Uruguay", "icon/uruguay.png");
        Team south_korea = new Team("South Korea", "icon/south_korea.png");
        this.groupH = new Group("Groupe H", portugal, ghana, uruguay, south_korea);
        portugal.setGroup(this.groupH);
        ghana.setGroup(this.groupH);
        uruguay.setGroup(this.groupH);
        south_korea.setGroup(this.groupH);*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.confirmBtn){
            if (this.groupA.getNumberOfTeamSlected()==2 && this.groupB.getNumberOfTeamSlected()==2 && this.groupC.getNumberOfTeamSlected()==2 && this.groupD.getNumberOfTeamSlected()==2 && this.groupE.getNumberOfTeamSlected()==2 && this.groupF.getNumberOfTeamSlected()==2 && this.groupG.getNumberOfTeamSlected()==2 && this.groupH.getNumberOfTeamSlected()==2){
                String user = this.instance.getUsername();
                try {
                    deleteBet();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbA1 = new GroupBet(this.db, user, this.groupA.getTeam1().getName(), this.groupA.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbA2 = new GroupBet(this.db, user, this.groupA.getTeam2().getName(), this.groupA.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbA3 = new GroupBet(this.db, user, this.groupA.getTeam3().getName(), this.groupA.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbA4 = new GroupBet(this.db, user, this.groupA.getTeam4().getName(), this.groupA.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbB1 = new GroupBet(this.db, user, this.groupB.getTeam1().getName(), this.groupB.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbB2 = new GroupBet(this.db, user, this.groupB.getTeam2().getName(), this.groupB.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbB3 = new GroupBet(this.db, user, this.groupB.getTeam3().getName(), this.groupB.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbB4 = new GroupBet(this.db, user, this.groupB.getTeam4().getName(), this.groupB.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbC1 = new GroupBet(this.db, user, this.groupC.getTeam1().getName(), this.groupC.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbC2 = new GroupBet(this.db, user, this.groupC.getTeam2().getName(), this.groupC.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbC3 = new GroupBet(this.db, user, this.groupC.getTeam3().getName(), this.groupC.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbC4 = new GroupBet(this.db, user, this.groupC.getTeam4().getName(), this.groupC.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbD1 = new GroupBet(this.db, user, this.groupD.getTeam1().getName(), this.groupD.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbD2 = new GroupBet(this.db, user, this.groupD.getTeam2().getName(), this.groupD.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbD3 = new GroupBet(this.db, user, this.groupD.getTeam3().getName(), this.groupD.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbD4 = new GroupBet(this.db, user, this.groupD.getTeam4().getName(), this.groupD.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbE1 = new GroupBet(this.db, user, this.groupE.getTeam1().getName(), this.groupE.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbE2 = new GroupBet(this.db, user, this.groupE.getTeam2().getName(), this.groupE.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbE3 = new GroupBet(this.db, user, this.groupE.getTeam3().getName(), this.groupE.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbE4 = new GroupBet(this.db, user, this.groupE.getTeam4().getName(), this.groupE.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbF1 = new GroupBet(this.db, user, this.groupF.getTeam1().getName(), this.groupF.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbF2 = new GroupBet(this.db, user, this.groupF.getTeam2().getName(), this.groupF.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbF3 = new GroupBet(this.db, user, this.groupF.getTeam3().getName(), this.groupF.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbF4 = new GroupBet(this.db, user, this.groupF.getTeam4().getName(), this.groupF.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbG1 = new GroupBet(this.db, user, this.groupG.getTeam1().getName(), this.groupG.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbG2 = new GroupBet(this.db, user, this.groupG.getTeam2().getName(), this.groupG.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbG3 = new GroupBet(this.db, user, this.groupG.getTeam3().getName(), this.groupG.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbG4 = new GroupBet(this.db, user, this.groupG.getTeam4().getName(), this.groupG.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbH1 = new GroupBet(this.db, user, this.groupH.getTeam1().getName(), this.groupH.getTeam1().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbH2 = new GroupBet(this.db, user, this.groupH.getTeam2().getName(), this.groupH.getTeam2().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbH3 = new GroupBet(this.db, user, this.groupH.getTeam3().getName(), this.groupH.getTeam3().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    GroupBet gbH4 = new GroupBet(this.db, user, this.groupH.getTeam4().getName(), this.groupH.getTeam4().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                this.setVisible(false);
                try {
                    this.instance.setHome();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{
                this.errorMessagePanel.setVisible(true);
            }
        }
    }
}
