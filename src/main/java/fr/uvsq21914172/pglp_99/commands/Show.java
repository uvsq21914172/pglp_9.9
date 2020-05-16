package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Show extends Command{

  public Show(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    if(params.containsKey("groups")) {
      System.out.println();
      for(String g: drawingtui.getDrawing().getGroups()) {
        System.out.print(":"+g+":");
      }
      System.out.println();
    }else if(params.containsKey("all")) {
      System.out.println();
      for(String g: drawingtui.getDrawing().getShapes()) {
        System.out.println(":"+g+":"+drawingtui.getDrawing().getShape(g).display()+":");
      }
      for(String g: drawingtui.getDrawing().getGroups()) {
        System.out.println(":"+g+":");
        for(String s: drawingtui.getDrawing().getGroup(g).getShapes()) {
          System.out.print(":"+s+":");
        }
        System.out.println();
      }
      System.out.println();
    }else {
      if(params.containsKey("group")) {
        if(drawingtui.getDrawing().containsGroup(params.get("group"))) {
          System.out.println(drawingtui.getDrawing().getGroup(params.get("group")).display());
        }else {
          System.out.println("[DrawingApp] Symbol \""+params.get("group")+"\" non-existant for Group type");
        }
      }else {
        System.out.println(drawingtui.getDrawing().display());
      }
    }
    
  }
  
}
