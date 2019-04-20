package com.pinkertone.pinkercardsmvp;

public class BattleItem {
    private String enemyName;
    private String battleDate;
    private String subjectName;
    private String battleState;
    private String battleScore;
    private boolean isAccepted;


    public void setEnemyName(String enemyName) { this.enemyName = enemyName; }

    public void setBattleDate(String battleDate) { this.battleDate = battleDate; }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public void setBattleState(String battleState) { this.battleState = battleState; }

    public void setBattleScore(String battleScore) { this.battleScore = battleScore; }

    public void setAccepted(boolean isAccepted) { this.isAccepted = isAccepted; }

    public String getEnemyName() { return this.enemyName; }

    public String getBattleDate() { return this.battleDate; }

    public String getSubjectName() { return this.subjectName; }

    public String getBattleState() { return this.battleState; }

    public String getBattleScore() {
        return this.battleScore;
    }

    public boolean getAccepted() { return this.isAccepted; }

}
