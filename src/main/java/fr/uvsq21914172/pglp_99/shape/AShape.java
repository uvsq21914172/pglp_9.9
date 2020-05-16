package fr.uvsq21914172.pglp_99.shape;

import fr.uvsq21914172.pglp_99.utils.Position;

public abstract class AShape implements IElement{
  
  protected Position position;
  protected int id;
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public AShape(int x, int y) {
    position = new Position(x,y);
  }
  
  public int getX() {
    return position.x;
  }
  
  public int getY() {
    return position.y;
  }
  
  public void move(int x, int y) {
    position.x += x;
    position.y += y;
  }
  
  public AShape(Position position) {
    super();
    this.position = position;
  }

  public String display() {
    return "AbstractShape";
  }

  public abstract int type();
}
