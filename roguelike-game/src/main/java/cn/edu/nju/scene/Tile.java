package cn.edu.nju.scene;

import cn.edu.nju.entity.Creature;

public class Tile {
    private String name;
    private int xPos;
    private int yPos;

    private Creature creature;
    private boolean available;

    public Tile(String name, int x,int y, Creature c){
        this.xPos = x;
        this.yPos = y;
        this.name = name;
        this.creature = c;
        if(this.name.equals("floor") && (this.creature == null))this.available = true;
        else this.available = false;
    }

    public String getName(){return this.name;}
    
    public boolean isAvailable(){return this.available;}
    
    public int getXPos(){return this.xPos;}
    
    public int getYPos(){return this.yPos;}

    public synchronized void setCreature(Creature c){
        if(c == null){
            this.available = true;
            creature = null;
        }
        else if(available){
            this.available = false;
            this.creature = c;
        }
        return;
    }

    public Creature getCreature(){return this.creature;}
}
