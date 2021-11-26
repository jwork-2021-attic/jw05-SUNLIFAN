package cn.edu.nju.entity;

import java.util.List;

import cn.edu.nju.scene.Map;
import cn.edu.nju.scene.Tile;

public class BulletAI implements Runnable{
    private List<Bullet> bullets;
    //private boolean isActive;
    private Map map;
    private Long internalCounter;

    public BulletAI(List<Bullet> bullets,Map map){
        this.bullets = bullets;
        this.map = map;
        this.internalCounter = 0L;
        //this.isActive = true;
    }

    @Override
    public void run() {
        while(true){
            if(internalCounter == 1000000){
            for(int i = 0; i < bullets.size(); i ++){
                Bullet b = bullets.get(i);
                b.forward();
                int xPos = b.getXPos();
                int yPos = b.getYPos();
                if(xPos <= 0 || xPos>=map.getHeight() || yPos <= 0 || yPos >= map.getWidth()){
                    bullets.remove(b);
                    continue;
                }
                Tile tile = map.getTile(xPos, yPos);
                if(tile.getName().equals("wall")){
                    bullets.remove(i);
                    System.out.println("[BulletAI]:remove a bullet");
                }
                else{
                    if(tile!=null && tile.getCreature()!=null){
                        Creature c = tile.getCreature();
                        System.out.println("[BULLET AI]"+c.getName() + " was hit ! ");
                        System.out.println("[BULLET AI]the health of "+ c.getName() + " is " + c.getHealth());
                        boolean success = b.hit(c);
                        if(success)bullets.remove(b);
                    }
                }
            }
            internalCounter = 0L;
        }
        internalCounter++;
        }    
    }

    
    
}
