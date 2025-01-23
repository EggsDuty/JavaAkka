import java.util.ArrayList;
import java.util.List;

public class PointArray {
    List<Point> points;
    boolean last;

    public PointArray() {
        points = new ArrayList<>();
        last = false;
    }

    public PointArray(boolean _last) {
        points = new ArrayList<>();
        last = _last;
    }

    public void add(Point point) {
        points.add(point);
    }

    public Point get(int index) {
        return points.get(index);
    }

    public int size() {
        return points.size();
    }
}
