/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Iterator;
import GUI.Dice;

/**
 *
 * @author mario43514
 */
public class Player {
    static final int MAXLEVEL = 10;
    private String name;
    private int level;
    private boolean dead;
    private BadConsequence pendingBadConsequence;
    private ArrayList<Treasure> hiddenTreasures;
    private ArrayList<Treasure> visibleTreasures;
    
    public Player(String n){
        name = n;
        pendingBadConsequence = null;
        dead = true;
        level = 1;
        hiddenTreasures = new ArrayList();
        visibleTreasures = new ArrayList();
    }
    public Player(Player p){
        name = p.name;
        pendingBadConsequence = p.pendingBadConsequence;
        dead = p.dead;
        level = p.level;
        hiddenTreasures = p.hiddenTreasures;
        visibleTreasures = p.visibleTreasures;
    }
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    protected Boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        if (dice.nextNumber("Ahora tiras dado para ver si te conviertes en jugador sectario","Te convertirás si sacas un 6") == 6)
            return true;
        return false;
    }
    public String getName(){
        return name;
    }
    private void bringToLife(){
        dead = false;
    }
    protected int getCombatLevel(){
        int sum = level;
        for (Treasure i : visibleTreasures){
            sum = sum + i.getBonus();
        }
        return sum;
    }
    private void incrementLevels(int l){
        level = level + l;
    }
    private void decrementLevels(int l){
        level = level - l;
        if (level < 1)
            level = 1;
    }
    private void setPendingBadConsequence(BadConsequence b){
        pendingBadConsequence = b;
    }
    private void applyPrize(Monster m){
        int nLevels = m.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures = m.getTreasuresGained();
        if (nTreasures > 0){
            CardDealer dealer = CardDealer.getInstance();
            Treasure treasure;
            for (int i = 1; i <= nTreasures; i++){
                treasure = dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }
    }
    private void applyBadConsequence(Monster m){
        BadConsequence badConsequence = m.getBadConsequence();
        int nLevels = badConsequence.getLevels();
        this.decrementLevels(nLevels);
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        this.setPendingBadConsequence(pendingBad);
    }
    protected Boolean canMakeTreasureVisible(Treasure t){
        Boolean coincide = false;
        Boolean dos_manos = false;
        int una_mano = 0;
        TreasureKind i;
        for (int j = 0; j < visibleTreasures.size(); j++){
            i = visibleTreasures.get(j).getType();
            if (i == t.getType())
                coincide = true;
            if (i == TreasureKind.ONEHAND)
                una_mano = una_mano + 1;
            if (i == TreasureKind.BOTHHANDS)
                dos_manos = true;
        }
        if (t.getType() == TreasureKind.ONEHAND){
            if (dos_manos)
                return false;
            if (una_mano < 2)
                return true;
        }
        if ( t.getType() == TreasureKind.BOTHHANDS){
            if (una_mano > 0)
                return false;
            else
                return true;
        }
        if (!coincide)
            return true;
        return false;     
    }
    private int howManyVisibleTreasures(TreasureKind tKind){
        int num = 0;
        for (Treasure i : visibleTreasures){
            if (i.getType() == tKind)
                num = num + 1;
        }
        return num;
    }
    private void dieIfNoTreasures(){
        if (visibleTreasures.isEmpty() && hiddenTreasures.isEmpty())
            dead = true;
    }
    public Boolean isDead(){
        return dead;
    }
    public BadConsequence getPendingBadConsequence(){
        return pendingBadConsequence;
    }
    public ArrayList<Treasure> getHiddenTreasures(){
        return hiddenTreasures;
    }
    public ArrayList<Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
    public CombatResult combat(Monster m){
        CombatResult combatResult;
        int myLevel = getCombatLevel();
        int monsterLevel = getOponentLevel(m);
        if (myLevel > monsterLevel){
            applyPrize(m);
            if (level >= MAXLEVEL)
                combatResult = CombatResult.WINGAME;
            else
                combatResult = CombatResult.WIN;
        }
        else{
            applyBadConsequence(m);
            combatResult = CombatResult.LOSE;
            if (shouldConvert())
                combatResult = CombatResult.LOSEANDCONVERT;
        }
        return combatResult;
    }
    public void makeTreasureVisible(Treasure t){
        if (this.canMakeTreasureVisible(t)){
            hiddenTreasures.remove(t);
            visibleTreasures.add(t);
        }
    }
    public void discardVisibleTreasure(Treasure t){
        visibleTreasures.remove(t);
        CardDealer dealer = CardDealer.getInstance();
        dealer.giveTreasureBack(t);
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractVisibleTreasure(t);
        this.dieIfNoTreasures();
    }
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);
        CardDealer dealer = CardDealer.getInstance();
        dealer.giveTreasureBack(t);
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractHiddenTreasure(t);
        this.dieIfNoTreasures();
    }
    public Boolean validState(){
        if (pendingBadConsequence == null)
            return true;
        if (pendingBadConsequence.isEmpty() && hiddenTreasures.size() <= 4)
            return true;
     
        return false;
    }
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number = dice.nextNumber("La tirada determina el número de tesoros","6 = 3 tesoros | 1 = 1 tesoro | 2 tesoros");
        //Esta parte la he modificado para  que sea coherente con las reglas del juego
        if (number == 6){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        else
            if (number > 1){
                treasure = dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
    }
    public int getLevels(){
        return level;
    }
    public void discardAllTreasures(){
        Treasure treasure;
        int tam_vis_tre = visibleTreasures.size();
        for (int i = 1; i <= tam_vis_tre; i++){
            this.discardVisibleTreasure(visibleTreasures.get(0));
        }
        int tam_hid_tre = hiddenTreasures.size();
        for (int i = 1; i <= tam_hid_tre; i++){
            this.discardHiddenTreasure(hiddenTreasures.get(0));
        }
    }
    @Override
    public String toString(){
        return name + "\tLevel=" + level;
    }
}
