
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Stack;

public class BadGuy {
	Stack<Node> directionStack = new Stack<>();

	Image myImage;
	int x = 0, y = 0;
	boolean hasPath = false;
	//int playerX, playerY;

	public BadGuy(Image i) {
		myImage = i;
		x = 30;
		y = 10;		
	}

	/*
	public void initNodes(boolean map[][], int targx, int targy) {
		for (int mapX = 0; mapX < 40; mapX++) {
			for (int mapY = 0; mapY < 40; mapY++) {
				//nodesMap[mapX][mapY] = new Node(map, mapX, mapY, playerX, playerY, x, y);
				//nodesMap[mapX][mapY].setClosed(map[mapX][mapY]);
				// System.out.println("X: " + mapX + " Y: " + mapY);
			}
		}
	}
	 */

	public void reCalcPath(boolean map[][], int targx, int targy) {
		
		directionStack = new Stack<>();

		Node[][] nodesMap = new Node[40][40];
		hasPath = false;

		LinkedList<Node> openList = new LinkedList();
		LinkedList<Node> closedList = new LinkedList();

		Node currentNode = null, nextNode = null;


		
		for (int mapX = 0; mapX < 40; mapX++) {
			for (int mapY = 0; mapY < 40; mapY++) {
				nodesMap[mapX][mapY] = new Node(mapX, mapY);

				/* trying to eliminate diagnol wall jumping 
				if(nodesMap[mapX][mapY].diagnolCheck(map[][])) {
					nodesMap[mapX][mapY].setClosed(true); //not used
					closedList.add(nodesMap[mapX][mapY]);
				} 
				*/
			}
		}
		
		

		Node startNode = nodesMap[Math.floorMod(x, 39)][Math.floorMod(y, 39)];
		startNode.setParent(null);
		startNode.setG(0);
		startNode.setH(((Math.abs(targx - startNode.getX())) + (Math.abs(targy - startNode.getY()))) * 10);
		startNode.setF();
		openList.add(startNode);

		
		/* Code that was for improvement that I couldn't work
		 * for (int loopX = x - 1; loopX <= x + 1; loopX++) { //the 9 squares around the
		 * bad guy
		 * for (int loopY = y - 1; loopY <= y + 1; loopY++) {
		 * if(!nodesMap[loopX][loopY].getClosed() && !nodesMap[loopX][loopY].getOpen())
		 * {
		 * nodesMap[loopX][loopY].setGH(targx, targx, x, y);
		 * openList.addLast(nodesMap[loopX][loopY]);
		 * } } }*/

		 

		while (!openList.isEmpty() && !hasPath) {
			currentNode = openList.getFirst();

			for (Node n : openList) {
				if (n.f <= currentNode.f) {
					currentNode = n;
				}
			}

			openList.remove(currentNode);
			closedList.add(currentNode);

			if (currentNode.getX() != targx || currentNode.getY() != targy) {
				for (int borderX = -1; borderX <= 1; borderX++) {
					for (int borderY = -1; borderY <= 1; borderY++) {
						int g;
						if (borderX != 0 || borderY != 0) { //if not the same square bad guy is on							
							if (((borderX == 1) && (borderY == 1)) || ((borderX == -1) && (borderY == -1)) //checking the 8 squares around
							|| ((borderX == 1) && (borderY == -1)) || ((borderX == -1) && (borderY == 1))) {
								g = 14;
							} else {
								g = 10;
							}

							nextNode = nodesMap[Math.floorMod(currentNode.getX() + borderX, 39)][Math.floorMod(currentNode.getY() + borderY, 39)];

							if (!closedList.contains(nextNode)) {
								if (!map[nextNode.getX()][nextNode.getY()]) { //if nextnode isn't closed, found isClosed to be redundant
									if (!openList.contains(nextNode)) {
										nextNode.setParent(currentNode);
										nextNode.setG(nextNode.getParent().getG() + g);
										nextNode.setH((Math.abs(targx - nextNode.getX()) + Math.abs(targy - nextNode.getY()))* 10);
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
			System.out.print("pursuing player on path\n");
			if (!directionStack.isEmpty()) {
				Node n = directionStack.pop();
				
				x = n.getX();
				y = n.getY();
			}

		} else {
			System.out.println("following blind path");
			// no path found so sprint at player 'base case' 
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
