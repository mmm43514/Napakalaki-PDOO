/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import GUI.NapakalakiView;
import GUI.Dice;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;
/**
 *
 * @author easynote
 */
public class Main {
    public static void main(String[] args) {
        Napakalaki game = Napakalaki.getInstance();
        NapakalakiView napakalakiView = new NapakalakiView();
        ArrayList<String> names;
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        
        Dice.createInstance (napakalakiView);
      
        names = namesCapture.getNames();
        game.initGame(names);
        napakalakiView.setNapakalaki(game);
        
        napakalakiView.setVisible (true);
    }
}
