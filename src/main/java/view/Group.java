package view;

import javax.swing.*;
import java.awt.*;

public class Group extends JPanel {

    private String groupName;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);

    public Group(String groupName, Team team1, Team team2, Team team3, Team team4){
        this.groupName = groupName;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(this.purple1, 3));
        this.setBackground(this.bgColor);
        this.add(team1.getJPanelType1());
        this.add(team2.getJPanelType1());
        this.add(team3.getJPanelType1());
        this.add(team4.getJPanelType1());

    }

    public int getNumberOfTeamSlected() {
        int cpt = 0;
        if (this.team1.isSelected()){
            cpt++;
        }
        if (this.team2.isSelected()){
            cpt++;
        }
        if (this.team3.isSelected()){
            cpt++;
        }
        if (this.team4.isSelected()){
            cpt++;
        }
        return cpt;
    }

    public Team getTeam1() {
        return this.team1;
    }
    public Team getTeam2() {
        return this.team2;
    }
    public Team getTeam3() {
        return this.team3;
    }
    public Team getTeam4() {
        return this.team4;
    }
}
