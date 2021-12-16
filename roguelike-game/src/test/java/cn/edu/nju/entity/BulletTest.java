package cn.edu.nju.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.edu.nju.resources.MapData;
import cn.edu.nju.scene.Map;
import cn.edu.nju.utils.Direction;

public class BulletTest {
    private Map map;
    private Bullet bullet;
    private Creature creature;

    @Before
    public void init(){
        map = new Map(MapData.MAP_DATA);
        creature = new Creature("bat", 2, 2, map, null);
        bullet = new Bullet(2, Direction.UP, 3, 2, "player", map);
    }

    @Test
    public void hitTest(){
        Creature player = new Creature("player", 1, 1, map, null);
        assert(!bullet.hit(player));
        assert(bullet.hit(creature));
        assert(creature.health == 8);
    }

    @Test
    public void getterAndSetterTest(){
        assertEquals(bullet.getXPos(), 3);
        assertEquals(bullet.getYPos(), 2);
        assertEquals(bullet.getDirection(), Direction.UP);
        assert(bullet.isActive());
        bullet.setActive(false);
        assert(!bullet.isActive());
        bullet.setActive(true);
    }

    @Test
    public void forwardTest(){
        bullet.forward();
        assertEquals(2, bullet.getXPos());
        assertEquals(2, bullet.getYPos());
        bullet.forward();
        bullet.forward();
        assert(!bullet.isActive());
    }
}
