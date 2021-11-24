package cn.edu.nju.entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.edu.nju.gui.Window;
import cn.edu.nju.utils.Direction;

public class PlayerControl implements Runnable, KeyListener{
    //change to private after debugging
    public Player player;
    public boolean active;

    public PlayerControl(Player player){
        this.player = player;
        Window.screen.addKeyListener(this);
        active = true;
    }

    @Override
    public void run() {
        while(active){
            if(!player.isAlive()){
                active = false;
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setDirection(Direction.UP);
                player.move();
                break;
            case KeyEvent.VK_S:    
                player.setDirection(Direction.DOWN);
                player.move();
                break;
            case KeyEvent.VK_A: 
                player.setDirection(Direction.LEFT);
                player.move();
                break;
            case KeyEvent.VK_D:
                player.setDirection(Direction.RIGHT);
                player.move();
                break;
            case KeyEvent.VK_SPACE:
                player.fire(player.dir);
                break;    
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setDirection(Direction.UP);
                player.move();
                break;
            case KeyEvent.VK_S:    
                player.setDirection(Direction.DOWN);
                player.move();
                break;
            case KeyEvent.VK_A: 
                player.setDirection(Direction.LEFT);
                player.move();
                break;
            case KeyEvent.VK_D:
                player.setDirection(Direction.RIGHT);
                player.move();
                break;
            case KeyEvent.VK_SPACE:
                player.fire(player.dir);
                break;    
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
