package cn.edu.nju.entity;

import java.util.List;

import cn.edu.nju.scene.Map;

public class Player extends Creature{
    public Player(int x, int y, Map map, List<Bullet> bullets){
        super("player",x, y, map, bullets);
    }
}
