package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;
import fr.uvsq21914172.pglp_99.shape.Circle;
import fr.uvsq21914172.pglp_99.shape.Group;
import fr.uvsq21914172.pglp_99.shape.Rectangle;
import fr.uvsq21914172.pglp_99.shape.Square;
import fr.uvsq21914172.pglp_99.shape.Triangle;

public class Create extends Command {

  public Create(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    String name = null;
    int x = 0, y = 0;
    int a = 1, b = 1, c = 1;
    if(params.containsKey("name")) {
      if(!drawingtui.getDrawing().containsShape(params.get("name")) || !drawingtui.getDrawing().containsGroup(params.get("name"))) {
        name = params.get("name");
        if(params.containsKey("type")) {
          if(params.containsKey("x") && params.get("x").matches("^-?\\d+$")) {
            x = Integer.parseInt(params.get("x"));
          }
          if(params.containsKey("y") && params.get("y").matches("^-?\\d+$")) {
            y = Integer.parseInt(params.get("y"));
          }
          if(params.get("type").equalsIgnoreCase("Circle")){
            if(params.containsKey("rayon") && params.get("rayon").matches("^-?\\d+$")) {
              a = Integer.parseInt(params.get("rayon"));
            }
            drawingtui.getDrawing().addShape(name, new Circle(x,y,a));
          }else if(params.get("type").equalsIgnoreCase("Square")){
            if(params.containsKey("width") && params.get("width").matches("^-?\\d+$")) {
              a = Integer.parseInt(params.get("width"));
            }
            drawingtui.getDrawing().addShape(name, new Square(x,y,a));
          }else if(params.get("type").equalsIgnoreCase("Rectangle")){
            if(params.containsKey("width") && params.get("width").matches("^-?\\d+$")) {
              a = Integer.parseInt(params.get("width"));
            }
            if(params.containsKey("length") && params.get("length").matches("^-?\\d+$")) {
              b = Integer.parseInt(params.get("length"));
            }
            drawingtui.getDrawing().addShape(name, new Rectangle(x,y,a,b));
          }else if(params.get("type").equalsIgnoreCase("Triangle")){
            if(params.containsKey("side1") && params.get("side1").matches("^-?\\d+$")) {
              a = Integer.parseInt(params.get("side1"));
            }
            if(params.containsKey("side2") && params.get("side2").matches("^-?\\d+$")) {
              b = Integer.parseInt(params.get("side2"));
            }
            if(params.containsKey("side3") && params.get("side3").matches("^-?\\d+$")) {
              c = Integer.parseInt(params.get("side3"));
            }
            drawingtui.getDrawing().addShape(name, new Triangle(x,y,a,b,c));
          }else if(params.get("type").equalsIgnoreCase("Group")){
            drawingtui.getDrawing().addGroup(name, new Group(drawingtui.getDrawing()));
          }else {
            System.out.println("[DrawingApp] Type \""+params.get("type")+"\" undefined");
          }
        }else {
          System.out.println("[DrawingApp] Type (-t) missing");
        }
      }else {
        System.out.println("[DrawingApp] Symbol \""+params.get("name")+"\" already existant for Group or Shape type");
      }
    }else {
      System.out.println("[DrawingApp] Name (-n) missing");
    }
  }

}
