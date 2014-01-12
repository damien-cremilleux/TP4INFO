package fr.inria.triskell.k3;

@SuppressWarnings("all")
public class Tuple<X extends Object, Y extends Object> {
  private X _x;
  
  public X getX() {
    return this._x;
  }
  
  public void setX(final X x) {
    this._x = x;
  }
  
  private Y _y;
  
  public Y getY() {
    return this._y;
  }
  
  public void setY(final Y y) {
    this._y = y;
  }
  
  public Tuple(final X x, final Y y) {
    this.setX(x);
    this.setY(y);
  }
}
