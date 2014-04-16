package calculS;

public class PointImpl implements Point {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public PointImpl(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PointImpl() {
        this.x = 0;
        this.y = 0;
    }
}
