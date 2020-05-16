package fr.uvsq21914172.pglp_99.shape;

public class Triangle  extends AShape{
  
  int side1;
  int side2;
  int side3;
  
  public Triangle(int x, int y, int side1, int side2, int side3) {
    super(x,y);
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }
  
  public void setSide1(int side1) {
    this.side1 = side1;
  }
  
  public int getSide1() {
    return side1;
  }
  
  public void setSide2(int side2) {
    this.side2 = side2;
  }
  
  public int getSide2() {
    return side2;
  }
  
  public void setSide3(int side3) {
    this.side3 = side3;
  }
  
  public int getSide3() {
    return side3;
  }
  
  public String display() {
    return "Triangle(pos="+position.display()+",side1="+side1+",side2="+side2+",side3="+side3+")";
  }

  @Override
  public int type() {
    return 3;
  }
  
}