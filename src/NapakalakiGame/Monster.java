/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author pc
 */
public class Monster {
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsequence;
    private int levelChangeAgainstCultistPlayer;
    
    public Monster(String n, int l, BadConsequence b, Prize p){
        name = n;
        combatLevel = l;
        prize = p;
        badConsequence = b;
        levelChangeAgainstCultistPlayer = 0;
    }
    public Monster(String n, int l, BadConsequence badConsequence, Prize p, int IC){
        name = n;
        combatLevel = l;
        prize = p;
        this.badConsequence = badConsequence;
        levelChangeAgainstCultistPlayer = IC;
    }
    public String getName(){
        return name;
    }
    public int getCombatLevel(){
        return combatLevel;
    }
    public int getCombatLevelAgainstCultistPlayer(){
        return getCombatLevel() + levelChangeAgainstCultistPlayer;
    }
    public BadConsequence getBadConsequence(){
        return badConsequence;
    }
    public Prize getPrize(){
        return prize;
    }
    public int getLevelsGained(){
        return prize.getLevel();
    }
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    @Override
    public String toString(){ 
        return "Name = " + name + "\nCombat level = " + Integer.toString(combatLevel) + "\nCombat level against cultist player: " + Integer.toString(combatLevel+levelChangeAgainstCultistPlayer)+ "\n\tPrize: " + prize.toString() + "\n\tBadConsequence: " + badConsequence.toString();
    }
}
