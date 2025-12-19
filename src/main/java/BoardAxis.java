public class BoardAxis {

    //edge1 is the left/top border of the inner playable box depending on the parameter of coord
    //edge2 is the right/bottom border of the inner playable box depending on the parameter of coord
    //innerSize is the size of the current playable box within the 5x5 box
    public int edge1;
    public int edge2;
    public int innerSize;

    public BoardAxis(int edge1, int edge2, int innerSize) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.innerSize = innerSize;
    }
}
