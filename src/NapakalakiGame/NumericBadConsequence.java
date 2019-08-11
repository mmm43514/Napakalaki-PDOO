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
public class NumericBadConsequence extends BadConsequence{
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    
    public NumericBadConsequence(String name, int l, int nVisible, int nHidden){
        super(name,l);
        nVisibleTreasures = nVisible;
        nHiddenTreasures = nHidden;
    }
    @Override
    public Boolean isEmpty(){
        if (nVisibleTreasures == 0 && nHiddenTreasures == 0)
            return true;
        return false;
    }
    @Override
    public String getText(){
        return super.getText();
    }
    @Override
    public int getLevels(){
        return super.getLevels();
    }
    @Override
    public int getNVisibleTreasures(){
        return nVisibleTreasures;
    }
    @Override
    public int getNHiddenTreasures(){
        return nHiddenTreasures;
    }
    @Override
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return null;
    }
    @Override
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return null;
    }
    @Override
    public void substractVisibleTreasure(Treasure t){
        nVisibleTreasures--;
    }
    @Override
    public void substractHiddenTreasure(Treasure t){
        nHiddenTreasures--;
    }
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        if (nVisibleTreasures > v.size())
            nVisibleTreasures = v.size();
        if (nHiddenTreasures > h.size())
            nHiddenTreasures = h.size();
        
        return this;
    }
    @Override
    public String toString(){
        String ret = "\n\t\tText = " + super.getText()+ "\n\t\tlevels = " + 
                Integer.toString(super.getLevels()) + "\n\t\tvisible treasures = " + 
                Integer.toString(nVisibleTreasures);
        ret = ret.concat("\n\t\thidden treasures = " + Integer.toString(nHiddenTreasures));
        return ret;
    }
}
