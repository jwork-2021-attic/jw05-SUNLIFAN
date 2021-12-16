package cn.edu.nju.GameLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import cn.edu.nju.entity.Bullet;
import cn.edu.nju.entity.Monster;
import cn.edu.nju.entity.Player;
import cn.edu.nju.scene.Map;

public class GameState implements Serializable {
    
    private Vector<Bullet> bullets;
    private Player player;
    private ArrayList<Monster> monsters;
    private Map map;
    private Boolean suspend;

    public GameState(Vector<Bullet> bullets, Player player, ArrayList<Monster> monsters, Map map, Boolean suspend){
        this.bullets = bullets;
        this.player = player;
        this.monsters = monsters;
        this.map = map;
        this.suspend = suspend;
    }

    public Vector<Bullet> getBullets(){return bullets;}
    
    public Player getPlayer(){return player;}

    public ArrayList<Monster> getMonster(){return monsters;}

    public Map getMap(){return map;}

    public Boolean isSuspended(){return suspend;}
}
