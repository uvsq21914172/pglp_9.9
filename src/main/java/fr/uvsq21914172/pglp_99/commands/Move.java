package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Move extends Command {

  public Move(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    String name = null;
    int x = 0, y = 0;
    if(params.containsKey("name")) {
      if(drawingtui.getDrawing().containsShape(params.get("name")) || drawingtui.getDrawing().containsGroup(params.get("name"))) {
        name = params.get("name");
      }else {
        System.out.println("[DrawingApp] Symbol \""+params.get("name")+"\" non-existant for Group nor Shape type");
      }
    }
    if(params.containsKey("x")) {
      if(params.get("x").matches("^-?\\d+$")) {
        x += Integer.parseInt(params.get("x"));
      }else {
        System.out.println("[DrawingApp] \""+params.get("x")+"\" not a number");
      }
    }
    if(params.containsKey("y")) {
      if(params.get("y").matches("^-?\\d+$")) {
        y += Integer.parseInt(params.get("x"));
      }else {
        System.out.println("[DrawingApp] \""+params.get("y")+"\" not a number");
      }
    }
    if(name == null) {
      System.out.println("[DrawingApp] Parameter Shape (-s) Missing");
    }else {
      if(drawingtui.getDrawing().containsShape(name)) {
        drawingtui.getDrawing().getShape(name).move(x, y);
      }else {
        drawingtui.getDrawing().getGroup(name).move(x, y);
      }
    }
    
  }
}
