/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author mario43514
 */
public class Treasure {
    private String name;
    private int bonus;
    private TreasureKind type;
    
    public Treasure(String n, int b, TreasureKind t){
        name = n;
        bonus = b;
        type = t;
    }
    public String getName(){
        return name;
    }
    public int getBonus(){
        return bonus;
    }
    public TreasureKind getType(){
       return type; 
    }
    @Override
    public String toString(){
        return "Name: " + name + "\nType: " + type.toString() + "\nBonus: " + Integer.toString(bonus);
    }
}
