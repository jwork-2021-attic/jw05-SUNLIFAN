package cn.edu.nju.entity;

import cn.edu.nju.utils.Direction;

public class Bullet {
    public static final int speed = 1;
    private boolean active;
    private int attack;
    private Direction direction;
    private int xPos;
    private int yPos;
    private String shotBy;
    
    public Bullet(int attackVal, Direction direction,int x, int y,String shotBy){
        this.attack = attackVal;
        this.direction = direction;
        this.active = true;
        this.xPos = x;
        this.yPos = y;
        this.shotBy = shotBy;
    }

    public int getXPos(){return this.xPos;}

    public int getYPos(){return this.yPos;}

    public void forward(){
        switch(direction){
            case LEFT:
                this.yPos-=speed;
                break;
            case RIGHT:
                this.yPos+=speed;
                break;
            case UP:
                this.xPos-=speed;
                break;
            case DOWN:    
                this.xPos+=speed;
                break;
        }
    }

    public boolean isActive(){return this.active;}
    
    public void setActive(boolean active){this.active = active;}
    
    /**
     * hit has to take effect orderly, thus need synchronized
     * @param c
     */
    public synchronized boolean hit(Creature c){
        if((shotBy.equals("player") && c.getName().equals("player")) || 
        (shotBy.equals("monster") && (c.getName().equals("bat") || c.getName().equals("rat") ||
         c.getName().equals("ghost"))))return false;
        c.damage(attack);
        this.active = false;
        return true;
    } 


    public Direction getDirection(){return direction;}
}
