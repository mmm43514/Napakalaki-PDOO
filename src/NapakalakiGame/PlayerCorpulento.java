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
//EXAMEN
public class PlayerCorpulento extends Player{
    public PlayerCorpulento(Player p){
        super(p);
    }
    @Override
    protected Boolean canMakeTreasureVisible(Treasure t){
        if (t.getType() == TreasureKind.ARMOR){
            for (Treasure tre : super.getVisibleTreasures()){
                if (tre.getType() == TreasureKind.ARMOR)
                    return true;
            }
        }
        return super.canMakeTreasureVisible(t);
    }
}
//FINEXAMEN