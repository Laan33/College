
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.*;
import java.util.Stack;

public class BadGuy {

	private Stack<Node> directionStack = new Stack<>();

	Image myImage;
	int x = 0, y = 0;
	boolean hasPath = false;
	int playerX, playerY;

	public BadGuy(Image i, boolean map[][], int playerX, int playerY) {
		myImage = i;
		x = 30;
		y = 10;
		this.playerX = playerX;
		this.playerY = playerY;

		/*
		 * for (int mapX=0;mapX<40;mapX++) {
		 * for (int mapY=0;mapY<40;mapY++) {
		 * nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
		 * nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
		 * //System.out.println("X: " + mapX + " Y: " + mapY);
		 * }
		 * }
		 */
	}

	public void initNodes(boolean map[][], int targx, int targy) {
		for (int mapX = 0; mapX < 40; mapX++) {
			for (int mapY = 0; mapY < 40; mapY++) {
				//nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
				//nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				// System.out.println("X: " + mapX + " Y: " + mapY);
			}
		}
	}

	public void reCalcPath(boolean map[][], int targx, int targy) {
		
		Node[][] nodesMap = new Node[40][40];
		hasPath = false;

		LinkedList<Node> openList = new LinkedList();
		LinkedList<Node> closedList = new LinkedList();

		Node currentNode = null, nextNode = null;

		for (int mapX = 0; mapX < 40; mapX++) {
			for (int mapY = 0; mapY < 40; mapY++) {
				nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
				//nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				// System.out.println("X: " + mapX + " Y: " + mapY);
			}
		}

		// int[][] currentNode = ;
		//initNodes(map, targx, targy);
		// System.out.println("ok recalc");
		// TO DO: calculate A* path to targx,targy, taking account of walls defined in
		// map[][]
		/*
		for (int mapX = 0; mapX < 40; mapX++) {
			for (int mapY = 0; mapY < 40; mapY++) {
				// nodesMap[mapX][mapY].setGH(targx, targx, x, y);
				// nodesMap[mapX][mapY].setWalls(map);
				nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				// nodesMap[x][y].setClosed(map[x][y]);
				// System.out.println("X: " + x + " Y: " + y);
				// System.out.println("f: " + nodesMap[mapX][mapY].getF());
			}
		}  */

		Node startNode = nodesMap[Math.floorMod(x, 39)][Math.floorMod(y, 39)];
		startNode.setParent(null);
		startNode.setH(((Math.abs(targx - startNode.getX())) + (Math.abs(targy - startNode.getY()))) * 10);
		startNode.setG(0);
		// startNode.setG(0);
		// startNode.setH(((Math.abs(targx - startNode.getX())) + (Math.abs(targy -
		// startNode.getY()))) * 10);
		startNode.setF();
		openList.add(startNode);

		/*
		 * for (int loopX = x - 1; loopX <= x + 1; loopX++) { //the 9 squares around the
		 * bad guy
		 * for (int loopY = y - 1; loopY <= y + 1; loopY++) {
		 * if(!nodesMap[loopX][loopY].getClosed() && !nodesMap[loopX][loopY].getOpen())
		 * {
		 * nodesMap[loopX][loopY].setGH(targx, targx, x, y);
		 * openList.addLast(nodesMap[loopX][loopY]);
		 * }
		 * }
		 * }
		 */

		while (!openList.isEmpty() && !hasPath) {
			currentNode = openList.getFirst();

			for (Node n : openList) {
				if (n.getF() <= currentNode.getF()) {
					currentNode = n;
				}
			}

			openList.remove(currentNode);
			closedList.add(currentNode);

			if (currentNode.getX() != targx || currentNode.getY() != targy) {
				for (int loopX = x - 1; loopX <= x + 1; loopX++) { // the 9 squares around the node
					for (int loopY = y - 1; loopY <= y + 1; loopY++) {
						if (loopX != 0 || loopY != 0) {
							int g;
							if (((loopX == 1) && (loopY == 1)) || ((loopX == -1) && (loopY == -1))
									|| ((loopX == 1) && (loopY == -1)) || ((loopX == -1) && (loopY == 1))) {
								g = 14;
							} else {
								g = 10;
							}

							// Node next = new Node(curr, curr.getX() + xx, curr.getY() + yy);
							nextNode = nodesMap[Math.floorMod(currentNode.getX() + loopX, 39)][Math
									.floorMod(currentNode.getY() + loopY, 39)];

							if (!closedList.contains(nextNode)) {
								if (!map[nextNode.getX()][nextNode.getY()]) { // TODO change this if closed
									if (!openList.contains(nextNode)) {
										nextNode.setParent(currentNode);
										nextNode.setG(nextNode.getParent().getG() + g);
										nextNode.setH(
												(Math.abs(targx - nextNode.getX()) + Math.abs(targy - nextNode.getY()))
														* 10);
										nextNode.setF();
										openList.add(nextNode);
									} else if (currentNode.getG() + g < nextNode.getG()) {
										nextNode.setParent(currentNode);
										nextNode.setG(nextNode.getParent().getG() + g);
										nextNode.setH(((targx - nextNode.getX()) + (targy - nextNode.getY()) * 10));
										nextNode.setF();
									}
								} else {
									closedList.add(nextNode);
								}
							}
						}
					}
				}

			} else {
				hasPath = true;
			}

		}
		System.out.println("\nStack sequence:");
		while (currentNode.parent != null) {
			System.out.println(currentNode.getX() + " " + currentNode.getY());
			directionStack.add(currentNode);
			currentNode = currentNode.getParent();
		}
		

	}

	public void move(boolean map[][], int targx, int targy) {
		reCalcPath(map, targx, targy);
		if (hasPath) {
			

			// TO DO: follow A* path, if we have one defined
			System.out.print("following path\n");
			// if (!route.isEmpty()) route.pop();
			if (!directionStack.isEmpty()) {
				Node n = directionStack.pop();
				// route.pop();
				System.out.println("previous x: " + x + " y: " + y);
				x = n.getX();
				y = n.getY();
				System.out.println("previous x: " + x + " y: " + y);

			}

		} else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx = x, newy = y;
			if (targx < x)
				newx--;
			else if (targx > x)
				newx++;
			if (targy < y)
				newy--;
			else if (targy > y)
				newy++;
			if (!map[newx][newy]) {
				x = newx;
				y = newy;
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(myImage, x * 20, y * 20, null);
	}

}
