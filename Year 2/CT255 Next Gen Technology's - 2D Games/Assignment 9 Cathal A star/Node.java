import java.util.Map;

public class Node {
    
    private boolean isOpen = false;
    private boolean isClosed = false;
    private int g;
    private int h;
    private int x, y;
    private boolean map[][];
    

    public Node(boolean map[][], int x, int y, int targx, int targy, int badGuyX, int badGuyY) {
        this.x = x;
        this.y = y;
        
        if(map[x][y]) { 
            isClosed = true;
            //System.out.println();
        }

        setGH(targx, targy, badGuyX, badGuyY);


    }


    public void setGH(int targx, int targy, int badGuyX, int badGuyY) {
        int xDist = Math.round(Math.abs(targx - x) * 10); 
        int yDist = Math.round(Math.abs(targy - y) * 10);
        
        h = (yDist + xDist);

        int xDist2 = Math.round(Math.abs(badGuyX - x) * 10); 
        int yDist2 = Math.round(Math.abs(badGuyY - y) * 10);
        g = (yDist2 + xDist2);
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void setWalls(boolean map[][]) { //if there's a wall then close this node
        if(map[x][y]) isClosed = true;
        //System.out.println("Wall has closed cell at x: " + x + " y: " + y);
    }


    public int getF() {
        return (g + h);
    }

}
