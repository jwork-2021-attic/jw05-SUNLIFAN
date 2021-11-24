package cn.edu.nju.entity;

import java.util.List;


import cn.edu.nju.scene.Map;

public class Monster extends Creature{

    private Type type;
    public Monster(Type type, int x, int y, Map map, List<Bullet> bullets) {
        super(type.getName(), x, y, map, bullets);
        this.type = type;
        this.strength = type.getStr();
        this.defence = type.getDef();
        this.maxHealth = type.getHp();
        this.health = type.getHp();
    }

    public Type getType(){return this.type;}

    public enum Type {
        BAT("bat", 7, 2, 0),
        RAT("rat", 11, 2, 0),
        GHOST("ghost", 13, 3, 1);
        
        private String name;
        private int hp;
        private int str;
        private int def;
        
        Type(String name, int hp, int str, int def) {
            this.name = name;
            this.hp = hp;
            this.str = str;
            this.def = def;
        }

        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public int getStr() {
            return str;
        }

        public int getDef() {
            return def;
        }
    }
    
}
