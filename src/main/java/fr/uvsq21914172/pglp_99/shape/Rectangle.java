package fr.uvsq21914172.pglp_99.shape;

public class Rectangle  extends AShape{
  
  int rayon;
  
  public Circle(int x, int y, int rayon) {
    super(x,y);
    this.rayon = rayon;
  }
  
  public void setRayon(int rayon) {
    this.rayon = rayon;
  }
  
  public int getRayon() {
    return rayon;
  }
  
  public String display() {
    return "Circle(centre="+position.display()+",rayon="+rayon+")";
  }
  
}