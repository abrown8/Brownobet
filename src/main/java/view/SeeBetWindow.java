package view;


import db.SQLite;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeBetWindow extends JSplitPane {

    private WorldCupBet instance;
    private SQLite db = new SQLite();

    private Font font1 = new Font("Arial Unicode MS", Font.PLAIN, 20);
    private Font font2 = new Font("Arial Unicode MS", Font.PLAIN, 15);
    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);
    private Color blue1 = new Color(45, 11, 137);
    private Color blue2 = new Color(240, 237, 247);
    private Group groupA;
    private Group groupB;
    private Group groupC;
    private Group groupD;
    private Group groupE;
    private Group groupF;
    private Group groupG;
    private Group groupH;
    private ArrayList<String> teams = new ArrayList<>();
    public SeeBetWindow(WorldCupBet instance) throws SQLException {
        this.instance = instance;

        JPanel leftPanel = getLeftPanel();
        JPanel rightPanel = getRightPanel();
        fillTeams();

        this.setLeftComponent(leftPanel);
        this.setRightComponent(rightPanel);

    }

    private JPanel getRightPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(this.blue2);
        return panel;
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
            teamObj.setCBDisable();
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
    }

    private ArrayList<String> getPredictedTeams(String user, String round) throws SQLException {
        String selectReq = "SELECT * FROM bets WHERE user='"+user+"' AND type='"+round+"' AND result='true';";
        return this.db.select(selectReq).get("team");
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
    private void setRightPanel(String username, String firstname, String lastname) throws SQLException {
        JPanel panel = new JPanel();
        panel.setBackground(this.blue2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel titlePan = new JPanel();
        titlePan.setBackground(this.blue2);
        titlePan.setAlignmentX(FlowLayout.CENTER);


        if (doHasBet(username)){
            ArrayList<String> teamPredictedQualify = getPredictedTeams(username, "group");
            JLabel title = new JLabel(firstname + " "+ lastname + " predictions :");
            title.setForeground(this.purple1);
            title.setFont(this.font1);
            titlePan.add(title);
            panel.add(titlePan);
            panel.add(getGroupsPanel(teamPredictedQualify));

        }
        else{
            JLabel title = new JLabel(firstname + " " + lastname + " has not yet entered his predictions");
            title.setForeground(this.purple1);
            title.setFont(this.font1);
            titlePan.add(title);
            panel.add(titlePan);
            panel.add(new JLabel());
        }

        this.setRightComponent(panel);
    }

    private JPanel getLeftPanel() throws SQLException {
        JPanel panel = new JPanel();
        panel.setBackground(this.blue2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String selectReq1 = "SELECT * FROM users";
        HashMap<String, ArrayList<String>> usersHM = this.db.select(selectReq1);
        String selectReq2 = "SELECT * FROM results";
        HashMap<String, ArrayList<String>> result = this.db.select(selectReq2);

        ArrayList<String> usernameArray = new ArrayList<>();
        ArrayList<String> firstnameArray = new ArrayList<>();
        ArrayList<String> lastnameArray = new ArrayList<>();
        ArrayList<Integer> scoresArray = new ArrayList<>();

        int hmLen = usersHM.get("username").size();
        for (int i=0 ; i<hmLen ; i++){
            String usernameOc = usersHM.get("username").get(i);
            String firstnameOc = usersHM.get("firstname").get(i);
            String lastnameOc = usersHM.get("lastname").get(i);
            usernameArray.add(usernameOc);
            firstnameArray.add(firstnameOc);
            lastnameArray.add(lastnameOc);
            int userScore;
            if (doHasBet(usernameOc)){
                userScore = getScore(usernameOc, result);
            }
            else{
                userScore = 0;
            }
            scoresArray.add(userScore);
        }
        ArrayList<ArrayList<Object>> rankingArray = order3Array(usernameArray, firstnameArray, lastnameArray, scoresArray);
        panel.add(rankPanelHeader());
        int rankPos = 0;
        for (ArrayList<Object> a : rankingArray){
            rankPos++;
            JPanel rankPan = getRankPan(a, rankPos);
            panel.add(rankPan);
        }
        return panel;

    }

    private JPanel rankPanelHeader() {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(1200, 50));
        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, this.blue1));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(this.blue1);

        JPanel rankPositionPanel = new JPanel();
        rankPositionPanel.setBackground(this.blue1);
        JLabel rankPosition = new JLabel("Rank");
        rankPosition.setForeground(this.blue2);
        rankPosition.setFont(this.font2);
        rankPositionPanel.add(rankPosition);
        JPanel firstNameLastNamePanel = new JPanel();
        firstNameLastNamePanel.setBackground(this.blue1);
        JLabel firstNameLastNameLabel = new JLabel("Name");
        firstNameLastNameLabel.setForeground(this.blue2);
        firstNameLastNameLabel.setFont(this.font2);
        firstNameLastNamePanel.add(firstNameLastNameLabel);
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(this.blue1);
        JLabel pointsLabel = new JLabel("Points");
        pointsLabel.setForeground(this.blue2);
        pointsLabel.setFont(this.font2);
        pointsPanel.add(pointsLabel);


        c.insets = new Insets(2, 10, 2, 10);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(rankPositionPanel, c);
        c.gridx = 1;
        panel.add(firstNameLastNamePanel, c);
        c.gridx = 2;
        panel.add(pointsPanel, c);
        c.gridx = 3;
        c.weightx = 8;
        panel.add(new JLabel(" "), c);

        return panel;
    }

    private JPanel getRankPan(ArrayList<Object> a, int rankPos) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(1200, 50));
        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, this.blue1));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(this.blue2);

        JPanel rankPositionPanel = new JPanel();
        rankPositionPanel.setBackground(this.blue2);
        JLabel rankPosition = new JLabel(rankPos+".");
        rankPosition.setForeground(this.purple1);
        rankPosition.setFont(this.font2);
        rankPositionPanel.add(rankPosition);
        JPanel firstNameLastNamePanel = new JPanel();
        firstNameLastNamePanel.setBackground(this.blue2);
        String firstNameLastName = a.get(1) + " "+ a.get(2);
        JLabel firstNameLastNameLabel = new JLabel(firstNameLastName);
        firstNameLastNameLabel.setForeground(this.blue1);
        firstNameLastNameLabel.setFont(this.font2);
        firstNameLastNamePanel.add(firstNameLastNameLabel);
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(this.blue2);
        JLabel pointsLabel = new JLabel(String.valueOf(a.get(3)));
        pointsLabel.setForeground(this.blue1);
        pointsLabel.setFont(this.font2);
        pointsPanel.add(pointsLabel);
        JPanel seeHisBetPanel = new JPanel();
        JButton seeHisBet = new JButton("See his bet");
        seeHisBet.setFont(this.font2);
        seeHisBet.setBackground(this.blue1);
        seeHisBet.setForeground(this.blue2);
        seeHisBetPanel.add(seeHisBet);

        seeHisBet.addActionListener(e -> {
            try {
                setRightPanel((String) a.get(0), (String) a.get(1), (String) a.get(2));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        c.insets = new Insets(2, 10, 2, 10);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(rankPositionPanel, c);
        c.gridx = 1;
        panel.add(firstNameLastNamePanel, c);
        c.gridx = 2;
        panel.add(pointsPanel, c);
        c.gridx = 3;
        panel.add(seeHisBet, c);
        c.gridx = 4;
        c.weightx = 7;
        panel.add(new JLabel(" "), c);

        return panel;
    }

    private ArrayList<ArrayList<Object>> order3Array(ArrayList<String> usernameArray, ArrayList<String> firstnameArray, ArrayList<String> lastnameArray, ArrayList<Integer> scoresArray) {
        ArrayList<ArrayList<Object>> ranking = new ArrayList<>();
        while(firstnameArray.size()>0){
            ArrayList<Object> highScoreArray = new ArrayList<>();
            int max = getMaximum(scoresArray);
            highScoreArray.add(usernameArray.remove(max));
            highScoreArray.add(firstnameArray.remove(max));
            highScoreArray.add(lastnameArray.remove(max));
            highScoreArray.add(scoresArray.remove(max));
            ranking.add(highScoreArray);
        }
        return ranking;
    }

    private int getMaximum(ArrayList<Integer> scoresArray) {
        int maxValue = scoresArray.get(0);
        int max = 0;
        int n = scoresArray.size();
        for (int i = 1; i < n; i++) {
            if (scoresArray.get(i) > maxValue) {
                max = i;
            }
        }
        return max;
    }


    private boolean doHasBet(String user) throws SQLException {
        String selectReq = "SELECT * FROM bets WHERE user='"+user+"';";
        if (this.db.select(selectReq).isEmpty()){
            return false;
        }
        return true;
    }
    private int getScore(String user, HashMap<String, ArrayList<String>> resultHM) throws SQLException {
        int points = 0;
        if (resultHM.isEmpty()){
            return points;
        }
        int hmLen = resultHM.get("id").size();
        for (int i=0 ; i<hmLen ; i++){
            String team = resultHM.get("team").get(i);
            if (resultHM.get("round_group").get(i).equals("1")){
                String selectReq = "SELECT result FROM bets WHERE user='"+user+"' AND team='"+team+"' AND type='group';";
                ArrayList<String> betForThisTeamInGroupStage = this.db.select(selectReq).get("result");
                if (!betForThisTeamInGroupStage.isEmpty()){
                    if (betForThisTeamInGroupStage.get(0).equals("true")){
                        points++;
                    }
                }
            }
        }
        return points;
    }



}
