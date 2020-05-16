package fr.uvsq21914172.pglp_99.shape;

public class Rectangle  extends AShape{
  
  int width;
  int length;
  
  public Rectangle(int x, int y, int width, int length) {
    super(x,y);
    this.width = width;
    this.length = length;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getWidth() {
    return width;
  }
  
  public void setLength(int length) {
    this.length = length;
  }
  
  public int getLength() {
    return length;
  }
  
  public String display() {
    return "Rectangle(centre="+position.display()+",width="+width+",length="+length+")";
  }

  @Override
  public int type() {
    return 1;
  }
  
}