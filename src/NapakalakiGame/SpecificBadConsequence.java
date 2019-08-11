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
public class SpecificBadConsequence extends BadConsequence{
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private ArrayList<TreasureKind> specificHiddenTreasures;
    
    public SpecificBadConsequence(String t, int l, ArrayList<TreasureKind> v, ArrayList<TreasureKind> h){
        super(t,l);
        specificVisibleTreasures = v;
        specificHiddenTreasures = h;
    }
    @Override
    public Boolean isEmpty(){
        if (specificVisibleTreasures.isEmpty() && specificHiddenTreasures.isEmpty())
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
        return specificVisibleTreasures.size();
    }
    @Override
    public int getNHiddenTreasures(){
        return specificHiddenTreasures.size();
    }
    @Override
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return specificVisibleTreasures;
    }
    @Override
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return specificHiddenTreasures;
    }
    @Override
    public void substractVisibleTreasure(Treasure t){
        specificVisibleTreasures.remove(t.getType());
    }
    @Override
    public void substractHiddenTreasure(Treasure t){
        specificHiddenTreasures.remove(t.getType());
    }
    @Override
    public SpecificBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        ArrayList<TreasureKind> arvt = new ArrayList();
        for (TreasureKind i : specificVisibleTreasures)
            arvt.add(i);
        specificVisibleTreasures.clear();
        for (Treasure tk : v){
            if (arvt.remove(tk.getType()))
               specificVisibleTreasures.add(tk.getType());
        }
        
        ArrayList<TreasureKind> arht = new ArrayList();
        for (TreasureKind i : specificHiddenTreasures)
            arht.add(i);
        specificHiddenTreasures.clear();
        for (Treasure tk : h){
            if (arht.remove(tk.getType()))
                specificHiddenTreasures.add(tk.getType());
        }
        
        return this;
    }
    
    @Override
    public String toString(){
        String ret = "\n\t\tText = " + super.getText()+ "\n\t\tlevels = " + 
                Integer.toString(super.getLevels()) + "\n\t\tvisible treasures = " + 
                Integer.toString(specificVisibleTreasures.size());
        if (specificVisibleTreasures != null){
            for (TreasureKind i : specificVisibleTreasures){
                ret = ret + " " + i.toString();
            }
        }
        ret = ret.concat("\n\t\thidden treasures = " + Integer.toString(specificHiddenTreasures.size()));
        if (specificHiddenTreasures != null){
            for (TreasureKind j : specificHiddenTreasures){
                ret = ret + " " + j.toString();
            }
        }
        return ret;
    }
}
