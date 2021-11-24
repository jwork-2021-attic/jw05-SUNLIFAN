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
            if(internalCounter == 10000000){
            for(int i = 0; i < bullets.size(); i ++){
                Bullet b = bullets.get(i);
                b.forward();
                int xPos = b.getXPos();
                int yPos = b.getYPos();
                Tile tile = map.getTile(xPos, yPos);
                if(tile.getName().equals("wall")){
                    bullets.remove(i);
                    System.out.println("[BulletAI]:remove a bullet");
                }
                else{
                    if(tile!=null && tile.getCreature()!=null && !tile.getCreature().getName().equals("player")){
                        Creature c = tile.getCreature();
                        b.hit(c);
                        bullets.remove(b);
                    }
                }
            }
            internalCounter = 0L;
        }
        internalCounter++;
        }    
    }

    
    
}
