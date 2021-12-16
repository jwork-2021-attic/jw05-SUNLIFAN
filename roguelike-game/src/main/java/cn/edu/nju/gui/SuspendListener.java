package cn.edu.nju.gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import cn.edu.nju.GameLogic.GameControl;
public class SuspendListener implements KeyListener {
    public SuspendListener(){
    }
    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_P:
                if(GameControl.gameState && !GameControl.suspend)
                    GameControl.suspend = true;
                else if(GameControl.gameState && GameControl.suspend)
                    GameControl.suspend = false;
                break;
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_P:
                if(GameControl.gameState && !GameControl.suspend){
                    GameControl.suspend = true;
                    System.out.println("Game suspended.....");
                }
                else if(GameControl.gameState && GameControl.suspend){
                    GameControl.suspend = false;
                    System.out.println("Game restore....");
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
