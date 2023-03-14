
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.*;
import java.util.Stack;

public class BadGuy {

	private Node nodesMap[][] = new Node[40][40];
	private LinkedList linkList = new LinkedList();
	private Stack directionStack = new Stack<>();


	Image myImage;
	int x=0,y=0;
	boolean hasPath=false;
	int playerX, playerY;

	public BadGuy( Image i, boolean map[][], int playerX, int playerY) {
		myImage=i;
		x = 30;
		y = 10;
		this.playerX = playerX;
		this.playerY = playerY;

		/*
		for (int mapX=0;mapX<40;mapX++) {
        	for (int mapY=0;mapY<40;mapY++) {
				nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
        		nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				//System.out.println("X: " + mapX + " Y: " + mapY);
        	}
        } */
	}

	public void initNodes(boolean map[][], int playerX, int playerY) {
		for (int mapX=0;mapX<40;mapX++) {
        	for (int mapY=0;mapY<40;mapY++) {
				nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
        		nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				System.out.println("X: " + mapX + " Y: " + mapY);
        	}
        }
	}
	
	public void reCalcPath(boolean map[][],int targx, int targy) {
		// TO DO: calculate A* path to targx,targy, taking account of walls defined in map[][]
		for (int mapX=0;mapX<40;mapX++) {
        	for (int mapY=0;mapY<40;mapY++) {
				nodesMap[mapX][mapY].setGH(targx, targx, x, y);
				nodesMap[mapX][mapY].setWalls(map);
				nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
        		//nodesMap[x][y].setClosed(map[x][y]);
				//System.out.println("X: " + x + " Y: " + y);
				//System.out.println("f: " + nodesMap[mapX][mapY].getF());
        	}
        }


		
	}
	
	public void move(boolean map[][],int targx, int targy) {
		reCalcPath(map, targx, targy);
		if (hasPath) {
			// TO DO: follow A* path, if we have one defined
			


		}
		else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx=x, newy=y;
			if (targx<x)
				newx--;
			else if (targx>x)
				newx++;
			if (targy<y)
				newy--;
			else if (targy>y)
				newy++;
			if (!map[newx][newy]) {
				x=newx;
				y=newy;
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}
	
}

