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
public class DeathBadConsequence extends NumericBadConsequence{
    public DeathBadConsequence(String text){
        super(text,Player.MAXLEVEL,BadConsequence.MAXTREASURES,BadConsequence.MAXTREASURES);
    }
    @Override
    public Boolean isEmpty(){
        return super.isEmpty();
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
        return super.getNVisibleTreasures();
    }
    @Override
    public int getNHiddenTreasures(){
        return super.getNHiddenTreasures();
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
        super.substractVisibleTreasure(t);
    }
    @Override
    public void substractHiddenTreasure(Treasure t){
        super.substractHiddenTreasure(t);
    }
    @Override
    public DeathBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        super.adjustToFitTreasureLists(v, h);
        return this;
    }
    @Override
    public String toString(){
        String ret = "\n\t\tText = " + super.getText() + "\n\t\tlevels = " + 
        Integer.toString(super.getLevels()) + "\n\t\tvisible treasures = " + 
        Integer.toString(super.getNVisibleTreasures());
        ret = ret.concat("\n\t\thidden treasures = " + Integer.toString(super.getNHiddenTreasures()));
        return ret;
    }
}
