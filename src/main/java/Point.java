public class Point {
    public float x;
    public float y;

    public boolean last;

    public Point(float _x, float _y) {
        this.x = _x;
        this.y = _y;
        this.last = false;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
        this.last = true;
    }
}
