package cn.edu.nju.GameLogic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
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
    private static Vector<Bullet> bullets;
    public static boolean isOnTileScreen;
    public static boolean gameState;
    public static boolean playerWin;
    public static volatile boolean suspend;
    
    private static void init(){
        //create entities and prepare map
        String[] mapData = MapData.MAP_DATA;
        suspend = false;
        map = new Map(mapData);
        bullets = new Vector<>();
        myPlayer = new Player(1, 1, map, bullets); 
        Random rand = new Random();
        monsters = new ArrayList<>();
        int width = mapData[0].length();
        int height = mapData.length;
        isOnTileScreen = true;
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

    public static void initGame(){
        Textures.init();
        init();
    }

    public static void startGame(){
        isOnTileScreen = false;
        gameState = true;
        playerWin = true;
        cachedPool.execute(new BulletAI(bullets));
        cachedPool.execute(new PlayerControl(myPlayer));
        for(Monster m : monsters){
            cachedPool.execute(new MonsterAI(m));
        }
        
    }

    public static void saveGameState(){
        try (FileOutputStream fileOut = new FileOutputStream("progressSaved/progress"+".ser");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            GameState state = new GameState(bullets, myPlayer, monsters, map, suspend);
            objOut.writeObject(state);
            System.out.println("[GameControl]:progress saved.......");
        } catch (Exception e) {
            System.out.println("[GameControl]:exception occurs when saving progress......");
            e.printStackTrace();
        }
    }


    public static void restoreGameState(){
        try (FileInputStream fileIn = new FileInputStream("progressSaved/progress"+".ser");
        ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            GameState state = (GameState)objIn.readObject();
            bullets = state.getBullets();
            myPlayer = state.getPlayer();
            monsters = state.getMonster();
            map = state.getMap();
            suspend = state.isSuspended();
            System.out.println("[GameControl]:progress restored.......");
        } catch (Exception e) {
            System.out.println("[GameControl]: exception occurs when restoring game state....");
            System.out.println("[GameControl]: no such game process files.......");
        }
    }

    public static Map getMap(){return map;}

    public static Player getPlayer(){return myPlayer;}

    public static  ArrayList<Monster> getMonsters(){return monsters;}

    public static Vector<Bullet> getBullets(){return bullets;}

}
