import java.util.Map;

public class Node {
    
    private boolean isOpen = false;
    private boolean isClosed = false;
    private int g, h, f, x, y;
    Node parent;
    //private boolean map[][];
    

    public Node(boolean map[][], int x, int y, int targx, int targy, int badGuyX, int badGuyY) {
        this.x = x;
        this.y = y;
        
        if(map[x][y]) { 
            isClosed = true;
            //System.out.println();
        }

        //setGH(targx, targy, badGuyX, badGuyY);


    }


    public void setGH(int targx, int targy, int badGuyX, int badGuyY) {
        int xDist = Math.round(Math.abs(targx - x) * 10); 
        int yDist = Math.round(Math.abs(targy - y) * 10);
        
        h = (yDist + xDist);

        int xDist2 = Math.round(Math.abs(badGuyX - x) * 10); 
        int yDist2 = Math.round(Math.abs(badGuyY - y) * 10);
        g = (yDist2 + xDist2);
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }


    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public void setClosed(boolean isClosed) {
        if(isClosed) {
            System.out.println("Node x:" + x + " y: " + y + " is closed: " + isClosed);
        }
        //System.out.println("Node x:" + x + " y: " + y + " is closed: " + isClosed);
        this.isClosed = isClosed;
    }

    /*
    public void setWalls(boolean map[][]) { //if there's a wall then close this node
        if(map[x][y]) isClosed = true;
        //System.out.println("Wall has closed cell at x: " + x + " y: " + y);
    } */

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public boolean getClosed() {
        return isClosed;
    }

    public boolean getOpen() {
        return isOpen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getG() {
        return g;
    }

    
    public void setF() {
        f = g+h;
    } 

    public int getF() {

        return f;
    }

}
