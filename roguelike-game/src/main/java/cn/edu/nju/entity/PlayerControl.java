package cn.edu.nju.entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.edu.nju.GameLogic.GameControl;
import cn.edu.nju.gui.Window;
import cn.edu.nju.utils.Direction;

public class PlayerControl implements Runnable, KeyListener{
    
    private Player player;
    public volatile boolean active;

    public PlayerControl(Player player){
        this.player = player;
        Window.screen.addKeyListener(this);
        active = true;
    }

    @Override
    public void run() {
        while(active){
            //System.out.println("player alive");
            if(!player.isAlive()){
                System.out.println("[Player Control:]player died");
                active = false;
                GameControl.gameState = false;
                GameControl.playerWin = false;
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setDirection(Direction.UP);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_S:    
                player.setDirection(Direction.DOWN);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_A: 
                player.setDirection(Direction.LEFT);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_D:
                player.setDirection(Direction.RIGHT);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
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
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_S:    
                player.setDirection(Direction.DOWN);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_A: 
                player.setDirection(Direction.LEFT);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_D:
                player.setDirection(Direction.RIGHT);
                if(GameControl.getMap().getNeighborTile(player.getXPos(), player.getYPos(), player.dir).getName().equals("stairs")){
                    GameControl.gameState = false;
                    GameControl.playerWin = true;
                    active = false;
                    break;
                }
                player.move();
                break;
            case KeyEvent.VK_SPACE:
                player.fire(player.dir);
                break;    
        }
        //System.out.format("player's current position : x = %d, y = %d %n", player.getXPos(),player.getYPos());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
