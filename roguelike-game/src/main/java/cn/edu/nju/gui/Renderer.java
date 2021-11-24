package cn.edu.nju.gui;

import cn.edu.nju.entity.Bullet;
import cn.edu.nju.entity.Monster;
import cn.edu.nju.entity.Player;
import cn.edu.nju.resources.Textures;
import cn.edu.nju.scene.Map;
import cn.edu.nju.scene.Tile;
import cn.edu.nju.utils.Direction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class Renderer {
    private int zoomLevel;

    public Renderer(){
        this.zoomLevel = 2;
    }

    /**
     * render player image at correct place
     * @param player
     * @param graphics
     */
    public void renderPlayer(Player player, Graphics graphics){
        BufferedImage sprite = Textures.getSprite("player");

        if(player.dir == Direction.RIGHT)sprite = mirrorImage(Textures.getSprite("player"));

        int drawPosX = (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;
		int drawPosY = (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;

        graphics.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoomLevel,
        sprite.getHeight()*zoomLevel,null);

    }

    /**
     * render tiles at correct position
     * @param mapData
     * @param graphics
     */
    public void renderMap(Map mapData, Player player,Graphics graphics){
        int width = mapData.getWidth();
        int height = mapData.getHeight();

        for(int i = 0; i < height;i++)
            for(int j = 0; j < width;j ++){
                Tile tile = mapData.getTile(i, j);
                BufferedImage sprite = Textures.getSprite(tile.getName());
                int drawPosX = calculateWidthOffset(sprite, tile, player);
                int drawPosY = calculateHeightOffset(sprite, tile, player);
                graphics.drawImage(sprite, drawPosX, drawPosY, 
                sprite.getWidth()*zoomLevel,sprite.getHeight()*zoomLevel, null);
            }
    }

    /**
     * render current alive monsters
     * @param monsters
     * @param graphics
     */
    public void renderMonsters(Collection<Monster> monsters, Player player, Graphics graphics){
        if(monsters == null || monsters.isEmpty())return;

        for(Monster m : monsters){
            if(!m.isAlive())continue;
            BufferedImage sprite = Textures.getSprite(m.getName());
            int xPos = calculateWidthOffset(sprite, m, player);
            int yPos = calculateHeightOffset(sprite, m, player);
            graphics.drawImage(sprite, xPos, yPos, sprite.getWidth()*zoomLevel,
            sprite.getHeight()*zoomLevel, null);
        }
    }

    /**
     * render bullets at correct places
     * @param bullets
     * @param player
     * @param graphics
     */
    public void renderBullets(Collection<Bullet> bullets, Player player, Graphics graphics){
        if(bullets == null || bullets.isEmpty())return;

        for(Bullet b : bullets){
            BufferedImage sprite = Textures.getSprite("bullet");
            int drawPosX = calculateWidthOffset(sprite, b, player);
            int drawPosY = calculateHeightOffset(sprite, b, player);
            graphics.drawImage(sprite, drawPosX, drawPosY, 
            sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
        }
    }

    private BufferedImage mirrorImage(BufferedImage image) {
		int h = image.getHeight();
		int w = image.getWidth();
		BufferedImage rotated = new BufferedImage(h, w, image.getType());
		
		for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				rotated.setRGB(x, y, image.getRGB(w-1-x, y));
			}
		}
		return rotated;
	}

    private int calculateWidthOffset(BufferedImage sprite, Tile tile, Player player){
        int offsetOnScreen = (tile.getYPos() - player.getYPos())*sprite.getWidth()*zoomLevel + (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;;

        return offsetOnScreen;
    }

    private int calculateHeightOffset(BufferedImage sprite, Bullet bullet, Player player){
        int offsetOnScreen = (bullet.getXPos() - player.getXPos())*sprite.getHeight()*zoomLevel + (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;

        return offsetOnScreen;
    }

    private int calculateWidthOffset(BufferedImage sprite, Bullet bullet, Player player){
        int offsetOnScreen = (bullet.getYPos() - player.getYPos())*sprite.getWidth()*zoomLevel + (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;;

        return offsetOnScreen;
    }

    private int calculateHeightOffset(BufferedImage sprite, Tile tile, Player player){
        int offsetOnScreen = (tile.getXPos() - player.getXPos())*sprite.getHeight()*zoomLevel + (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;

        return offsetOnScreen;
    }

    private int calculateWidthOffset(BufferedImage sprite, Monster monster, Player player){
        int offsetOnScreen = (monster.getYPos() - player.getYPos())*sprite.getWidth()*zoomLevel + (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;

        return offsetOnScreen;
    }

    private int calculateHeightOffset(BufferedImage sprite, Monster monster, Player player){
        int offsetOnScreen = (monster.getXPos() - player.getXPos())*sprite.getHeight()*zoomLevel + (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;

        return offsetOnScreen;
    }

}
