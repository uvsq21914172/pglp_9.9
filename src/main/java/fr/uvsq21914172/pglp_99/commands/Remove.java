package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Remove extends Command{

  public Remove(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    String shape = null;
    String group = null;
    boolean del = params.containsKey("delete");
    if(params.containsKey("shape")) {
      if(drawingtui.getDrawing().containsShape(params.get("shape"))) {
        shape = params.get("shape");
      }else {
        System.out.println("[DrawingApp] Symbol \""+params.get("shape")+"\" non-existant for Group type");
      }
    }
    if(params.containsKey("group")) {
      if(drawingtui.getDrawing().containsGroup(params.get("group"))) {
        group = params.get("group");
      }else {
        System.out.println("[DrawingApp] Symbol \""+params.get("group")+"\" non-existant for Group type");
      }
    }
    if(shape == null) {
      if(group == null) {
        System.out.println("[DrawingApp] No parametters\n"
            + "Format : Remove (-s ${shape} [-g ${group}]| -g ${group} [-d])");
      }else {
        if(!del) {
          drawingtui.getDrawing().removeGroup(group);
        }else {
          drawingtui.getDrawing().deleteGroup(group);
        }
      }
    }else {
      if(group == null) {
        drawingtui.getDrawing().removeShape(shape);
      }else {
        drawingtui.getDrawing().getGroup(group).remove(shape);
      }
    }
    
  }

}
