package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Team implements ActionListener {

    private Group group;
    private String name;
    private JLabel flag;
    private int numOfWin;
    private int numOfDraw;
    private int numOfDefeats;
    private int points;
    private int numOfGoalsScored;
    private int numOfGoalsConceded;
    private int numOfUserWhoTrustedInGroupStage;

    private Color bgColor = Color.WHITE;
    private Color purple1 = new Color(155, 0, 176);

    private JCheckBox teamCB = new JCheckBox();

    public Team(String name, String flagIconPath){
        this.name = name;
        ImageIcon flagIcon = new ImageIcon(flagIconPath);
        this.flag = new JLabel(flagIcon);
        this.teamCB.addActionListener(this);
    }

    public String getName(){
        return this.name;
    }
    public JLabel getFlag(){
        return this.flag;
    }
    public boolean isPredictedToBeQualified(){
        if(this.teamCB.isSelected()){
            return true;
        }
        else{
            return false;
        }
    }

    public JPanel getJPanelType1(){

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(this.bgColor);

        JPanel cbPan = new JPanel();
        cbPan.setBackground(this.bgColor);
        cbPan.add(this.teamCB);

        JPanel flagPan = new JPanel();
        flagPan.setBackground(this.bgColor);
        flagPan.add(this.flag);

        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setForeground(this.purple1);
        JPanel namePan = new JPanel();
        namePan.setBackground(this.bgColor);
        namePan.add(nameLabel);

        panel.add(cbPan);
        panel.add(flagPan);
        panel.add(namePan);
        return panel;
    }

    public boolean isSelected() {
        if(this.teamCB.isSelected()){
            return true;
        }
        return false;
    }
    public JCheckBox getCB(){
        return this.teamCB;
    }

    public void quoteCB() {
        this.teamCB.setSelected(true);
    }

    public void setGroup(Group group){
        this.group = group;
    }
    public void setCBDisable(){
        this.teamCB.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.teamCB){
            if (this.teamCB.isSelected()){
                if (this.group.getNumberOfTeamSlected() == 3){
                    this.teamCB.setSelected(false);
                }
            }
        }
    }


}
