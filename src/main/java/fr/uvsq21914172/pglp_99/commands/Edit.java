package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;
import fr.uvsq21914172.pglp_99.shape.AShape;
import fr.uvsq21914172.pglp_99.shape.Circle;
import fr.uvsq21914172.pglp_99.shape.Rectangle;
import fr.uvsq21914172.pglp_99.shape.Square;
import fr.uvsq21914172.pglp_99.shape.Triangle;

public class Edit extends Command {

  public Edit(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    if(params.containsKey("name")) {
      if(drawingtui.getDrawing().containsShape(params.get("name"))) {
        AShape shape = drawingtui.getDrawing().getShape(params.get("name"));
        if(shape instanceof Circle) {
          if(params.containsKey("rayon") && params.get("rayon").matches("^-?\\d+$")) {
            ((Circle) shape).setRayon(Integer.parseInt(params.get("rayon")));;
          }
        }else if(shape instanceof Square) {
          if(params.containsKey("width") && params.get("width").matches("^-?\\d+$")) {
            ((Square) shape).setWidth(Integer.parseInt(params.get("width")));;
          }
        }else if(shape instanceof Rectangle) {
          if(params.containsKey("width") && params.get("width").matches("^-?\\d+$")) {
            ((Rectangle) shape).setWidth(Integer.parseInt(params.get("width")));;
          }
          if(params.containsKey("length") && params.get("length").matches("^-?\\d+$")) {
            ((Rectangle) shape).setWidth(Integer.parseInt(params.get("length")));;
          }
        }else if(shape instanceof Triangle) {
          if(params.containsKey("side1") && params.get("side1").matches("^-?\\d+$")) {
            ((Triangle) shape).setSide1(Integer.parseInt(params.get("side1")));;
          }
          if(params.containsKey("side2") && params.get("side2").matches("^-?\\d+$")) {
            ((Triangle) shape).setSide2(Integer.parseInt(params.get("side2")));;
          }
          if(params.containsKey("side3") && params.get("side3").matches("^-?\\d+$")) {
            ((Triangle) shape).setSide3(Integer.parseInt(params.get("side3")));;
          }
        }
      }else {
        System.out.println("[DrawingApp] Symbol \""+params.get("name")+"\" non-existant for Shape type");
      }
    }else {
      System.out.println("[DrawingApp] Name (-t) non-existant for Group nor Shape type");
    }
  }

}
