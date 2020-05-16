package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.dao.DrawingDaoBd;
import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Save extends Command {


  public Save(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    DrawingDaoBd ddb = new DrawingDaoBd();
    if(!params.containsKey("name")) {
      System.out.println("[DrawingApp] Parameter Name (-n) Missing");
      return;
    }
    drawingtui.getDrawing().setName(params.get("name"));
    if(ddb.find(drawingtui.getDrawing().getName()) != null) {
      if(drawingtui.ask("Delete the previous version?(Y/no)")) {
        ddb.update(drawingtui.getDrawing());
      }
    }else {
      ddb.create(drawingtui.getDrawing());
    }
  }

}
