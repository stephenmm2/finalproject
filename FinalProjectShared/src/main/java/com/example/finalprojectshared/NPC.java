package com.example.finalprojectshared;

public class NPC {

    private int hitPointsForNPC;
    private int strengthDexterityAndIntelligenceOfNPC;
    private int dieRollForPlayersAttack;
    private int playersHitAmount;
    private int dieRollForNPCAttack;
    private int npcHitAmount;
    private int dieRollForRunAway;
    private boolean ranAway;


    public NPC(int hitPointsForNPC, int strengthDexterityAndIntelligenceOfNPC, boolean ranAway) {
        this.hitPointsForNPC = hitPointsForNPC;
        this.strengthDexterityAndIntelligenceOfNPC = strengthDexterityAndIntelligenceOfNPC;
        this.ranAway = ranAway;
    }

    public int getHitPointsForNPC() {
        return hitPointsForNPC;
    }

    public void setHitPointsForNPC(int hitPointsForNPC) {
        this.hitPointsForNPC = hitPointsForNPC;
    }

    public int getStrengthDexterityAndIntelligenceOfNPC() {
        return strengthDexterityAndIntelligenceOfNPC;
    }

    public void setStrengthDexterityAndIntelligenceOfNPC(int strengthDexterityAndIntelligenceOfNPC) {
        this.strengthDexterityAndIntelligenceOfNPC = strengthDexterityAndIntelligenceOfNPC;
    }

    public int getDieRollForPlayersAttack() {
        return dieRollForPlayersAttack;
    }

    public void setDieRollForPlayersAttack(int dieRollForPlayersAttack) {
        this.dieRollForPlayersAttack = dieRollForPlayersAttack;
    }

    public int getPlayersHitAmount() {
        return playersHitAmount;
    }

    public void setPlayersHitAmount(int playersHitAmount) {
        this.playersHitAmount = playersHitAmount;
    }

    public int getDieRollForNPCAttack() {
        return dieRollForNPCAttack;
    }

    public void setDieRollForNPCAttack(int dieRollForNPCAttack) {
        this.dieRollForNPCAttack = dieRollForNPCAttack;
    }

    public int getNpcHitAmount() {
        return npcHitAmount;
    }

    public void setNpcHitAmount(int npcHitAmount) {
        this.npcHitAmount = npcHitAmount;
    }

    public int getDieRollForRunAway() {
        return dieRollForRunAway;
    }

    public void setDieRollForRunAway(int dieRollForRunAway) {
        this.dieRollForRunAway = dieRollForRunAway;
    }

    public boolean isRanAway() {
        return ranAway;
    }

    public void setRanAway(boolean ranAway) {
        this.ranAway = ranAway;
    }
}
