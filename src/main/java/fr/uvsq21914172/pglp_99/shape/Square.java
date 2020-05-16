package fr.uvsq21914172.pglp_99.shape;

public class Square extends AShape{
  
  int width;
  
  public Square(int x, int y, int width) {
    super(x,y);
    this.width = width;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getWidth() {
    return width;
  }
  
  public String display() {
    return "Square(pos="+position.display()+",width="+width+")";
  }

  @Override
  public int type() {
    return 2;
  }
  
}