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
public class Prize {
    private final int treasures;
    private final int level;
    
    public Prize(int t, int l){
        treasures = t;
        level = l;
    }
    public int getTreasures(){
        return treasures;
    }
    public int getLevel(){
        return level;
    }
    @Override
    public String toString(){
        return "\n\t\tTreasures = " + Integer.toString(treasures) + 
                "\n\t\tlevels = " + Integer.toString(level);
    }
}