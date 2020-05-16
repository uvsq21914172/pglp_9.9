package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Add extends Command {

  public Add(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    String shape = null;
    String group = null;
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
            + "Format : Add -s ${shape} -g ${group}");
      }else {
        System.out.println("[DrawingApp] Parameter Shape (-s) Missing");
      }
    }else {
      if(group == null) {
        System.out.println("[DrawingApp] Parameter Group (-g) Missing");
      }else {
        drawingtui.getDrawing().getGroup(group).add(shape);
      }
    }
    
  }

}
