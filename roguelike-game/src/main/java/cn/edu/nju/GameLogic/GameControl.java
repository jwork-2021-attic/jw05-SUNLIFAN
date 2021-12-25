package cn.edu.nju.GameLogic;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.nju.entity.Bullet;
import cn.edu.nju.entity.BulletAI;
import cn.edu.nju.entity.Player;
import cn.edu.nju.entity.PlayerControl;
import cn.edu.nju.resources.MapData;
import cn.edu.nju.resources.Textures;
import cn.edu.nju.scene.Map;

public class GameControl {
    private static ExecutorService cachedPool = Executors.newCachedThreadPool();
    private static Player myPlayer;
    private static HashMap<Integer, Player> otherPlayers;
    private static Map map;
    private static Vector<Bullet> bullets;
    public static boolean isOnTileScreen;
    public static boolean gameState;
    public static boolean playerWin;
    
    private static void init(){
        //create entities and prepare map
        String[] mapData = MapData.MAP_DATA;
        map = new Map(mapData);
        bullets = new Vector<>();
        myPlayer = new Player(1, 1, map, bullets); 
        otherPlayers = new HashMap<>();
        isOnTileScreen = true;
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
        
    }

    public static Map getMap(){return map;}

    public static Player getPlayer(){return myPlayer;}

    public static Vector<Bullet> getBullets(){return bullets;}

    public static HashMap<Integer, Player> getOtherPlayers(){return otherPlayers;}

    
}
