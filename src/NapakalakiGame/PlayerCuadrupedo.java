/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;
//EXAMEN
/**
 *
 * @author easynote
 */
public class PlayerCuadrupedo extends Player{
    public PlayerCuadrupedo(Player p){
        super(p);
    }
    @Override
    protected Boolean canMakeTreasureVisible(Treasure t){
        if (t.getType() == TreasureKind.SHOES){
            for (Treasure tre : super.getVisibleTreasures()){
                if (tre.getType() == TreasureKind.SHOES)
                    return true;
            }
        }
        return super.canMakeTreasureVisible(t);
    }

}
//FINEXAMEN