package fr.uvsq21914172.pglp_99.utils;

public class Position {
  
  public int x;
  public int y;
  
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String display() {
    return "("+x+","+y+")";
  }
  
}
