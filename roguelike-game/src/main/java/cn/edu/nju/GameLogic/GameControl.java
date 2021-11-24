package cn.edu.nju.GameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.nju.entity.Bullet;
import cn.edu.nju.entity.BulletAI;
import cn.edu.nju.entity.Monster;
import cn.edu.nju.entity.MonsterAI;
import cn.edu.nju.entity.Player;
import cn.edu.nju.entity.PlayerControl;
import cn.edu.nju.resources.MapData;
import cn.edu.nju.resources.Textures;
import cn.edu.nju.scene.Map;

public class GameControl {
    private static ExecutorService cachedPool = Executors.newCachedThreadPool();
    private static final int numOfMonsters = 10;
    private static Player myPlayer;
    private static ArrayList<Monster> monsters;
    private static Map map;
    private static List<Bullet> bullets;
    
    private static void init(){
        //create entities and prepare map
        String[] mapData = MapData.MAP_DATA;
        map = new Map(mapData);
        bullets = new LinkedList<>();
        myPlayer = new Player(1, 1, map, bullets); 
        Random rand = new Random();
        monsters = new ArrayList<>();
        int width = mapData[0].length();
        int height = mapData.length;
        for(int i = 0; i < numOfMonsters; i++){
            boolean found = false;
            while(!found){
                int x = rand.nextInt(height);
                int y = rand.nextInt(width);
                if(map.getTile(x, y).isAvailable()){
                    found = true;
                    Monster m = null;
                    switch(i%3){
                        case 0:
                            m = new Monster(Monster.Type.BAT, x, y, map, bullets);
                            break;
                        case 1:
                            m = new Monster(Monster.Type.GHOST, x, y, map, bullets);
                            break;
                        case 2:
                            m = new Monster(Monster.Type.RAT, x, y, map, bullets);  
                            break;
                    }
                    monsters.add(m);
                    break;
                }
            }
        }
    }

    public static void startGame(){
        Textures.init();
        init();

        cachedPool.execute(new BulletAI(bullets, map));
        cachedPool.execute(new PlayerControl(myPlayer));
        for(Monster m : monsters){
            cachedPool.execute(new MonsterAI(m));
        }
        
        
    }

    public static Map getMap(){return map;}

    public static Player getPlayer(){return myPlayer;}

    public static  ArrayList<Monster> getMonsters(){return monsters;}

    public static List<Bullet> getBullets(){return bullets;}

}