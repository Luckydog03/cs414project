public abstract class Piece {
    String color = "";
    int x = 0;
    int y = 0;

    public abstract String toString();

    public void setLocation(int a, int b){
        this.x = a;
        this.y = b;
    }

    public void setColor(String color){
        this.color = color;
    }
}
