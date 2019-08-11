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
public class Napakalaki {
    private static Napakalaki instance = null;
    private Player currentPlayer = null;
    private Monster currentMonster = null;
    private ArrayList<Player> players;
    private CardDealer dealer;
    
    private Napakalaki(){
        dealer = CardDealer.getInstance();
    }
    private void initPlayers(ArrayList<String> names){
        players = new ArrayList();
        for (String n : names){
            players.add(new Player(n));
        }
        currentPlayer = players.get((int) (Math.random()*players.size()));
    }

    private Player nextPlayer(){
        int index = players.indexOf(currentPlayer);
        if (index == -1)
            return players.get((int) (Math.random()*players.size()));
        else{
            if (index+1 == players.size())
                return players.get(0);
            else
                return players.get(index+1);
        }
                
    }
    private Boolean nextTurnAllowed(){
        if (currentPlayer == null)
            return true;
        else
            return currentPlayer.validState();
    }
    public static Napakalaki getInstance(){
        if (instance == null){
            instance = new Napakalaki();
        }
        return instance;
    }
    public CombatResult developCombat(){
        CombatResult r = currentPlayer.combat(currentMonster);
        if (r == CombatResult.LOSEANDCONVERT){
            CultistPlayer cultistPlayer = new CultistPlayer(currentPlayer, dealer.nextCultist());
            players.set(players.indexOf(currentPlayer), cultistPlayer);
            currentPlayer = cultistPlayer;
        }
        dealer.giveMonsterBack(currentMonster);
        return r;
    }
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        for (Treasure treasure : treasures){
            currentPlayer.discardVisibleTreasure(treasure);
        }        
    }
    //EXAMEN
    public void convertTo(Player p){
        if (p instanceof PlayerCorpulento){
            players.set(players.indexOf(currentPlayer), p);
            currentPlayer = p;
        }
        else{
            if (p instanceof PlayerCuadrupedo){
                players.set(players.indexOf(currentPlayer), p);
            }
        }
    }
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
        for (Treasure treasure : treasures){
            currentPlayer.discardHiddenTreasure(treasure);
        }
    }
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
        for (Treasure treasure : treasures)
            currentPlayer.makeTreasureVisible(treasure);
    }
    public void initGame(ArrayList<String> players){
        initPlayers(players);
        dealer.initCards();
        nextTurn();
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    public Boolean nextTurn(){
        Boolean stateOK = false;
        if (currentPlayer != null){
            stateOK = nextTurnAllowed();
        }
        if (stateOK){
            currentMonster = dealer.nextMonster();
            currentPlayer = nextPlayer();
            Boolean dead = currentPlayer.isDead();
            if (dead){
                currentPlayer.initTreasures();
            }
        }
        return stateOK;
    }
    public Boolean endOfGame(CombatResult result){
        if (result == CombatResult.WINGAME)
            return true;
        else
            return false;
    }
}
