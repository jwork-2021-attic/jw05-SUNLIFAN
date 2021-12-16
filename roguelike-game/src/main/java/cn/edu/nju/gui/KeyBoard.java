package cn.edu.nju.gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.edu.nju.GameLogic.GameControl;
public class KeyBoard implements KeyListener{

    public volatile boolean enter_be_pressed = false;
    public volatile boolean restoreMode = false;
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)enter_be_pressed = true;
        if(e.getKeyCode() == KeyEvent.VK_R && !GameControl.gameState)restoreMode = true;
        if(e.getKeyCode() == KeyEvent.VK_K && GameControl.suspend){
            GameControl.saveGameState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
