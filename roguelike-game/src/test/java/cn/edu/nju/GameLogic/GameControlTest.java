package cn.edu.nju.GameLogic;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import cn.edu.nju.entity.Player;

public class GameControlTest {
    @Before
    public void setUp() throws InterruptedException{
        GameControl.initGame();
    }

    @Test
    public void storeAndRestoreTest(){
        Player player = GameControl.getPlayer();
        player.damage(5);
        GameControl.saveGameState();
        GameControl.initGame();
        GameControl.restoreGameState();
        assertEquals(5, GameControl.getPlayer().getHealth());
    }
}
