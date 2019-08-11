/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
/**
 *
 * @author easynote
 */
public abstract class BadConsequence {
    
    static final int MAXTREASURES = 10;
    private String text;
    private int levels;
    
    public BadConsequence(String text, int levels){
        this.text = text;
        this.levels = levels;
    }
    
    public abstract Boolean isEmpty();
    
    public String getText(){
        return text;
    }
    
    public int getLevels(){
        return levels;
    }
    
    public abstract int getNVisibleTreasures();
    
    public abstract int getNHiddenTreasures();
    
    public abstract ArrayList<TreasureKind> getSpecificVisibleTreasures();
    
    public abstract ArrayList<TreasureKind> getSpecificHiddenTreasures();
    
    public abstract void substractVisibleTreasure(Treasure t);
    
    public abstract void substractHiddenTreasure(Treasure t);
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);
    
    @Override
    public abstract String toString();
}
