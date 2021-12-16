package cn.edu.nju.entity;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import cn.edu.nju.resources.MapData;
import cn.edu.nju.scene.Map;

public class MonsterTest {
    private Map map;
    private Monster bat;
    private Vector<Bullet> bullets;

    @Before
    public void setUp(){
        map = new Map(MapData.MAP_DATA);
        bullets = new Vector<>();
        bat = new Monster(Monster.Type.BAT, 2, 2, map, bullets);
    }

    @Test
    public void fireTest(){
        for(int i = 0; i <= 1000; i ++)bat.fire(bat.dir);
        assert(bat.bullets.size() == 51);
    }
}
