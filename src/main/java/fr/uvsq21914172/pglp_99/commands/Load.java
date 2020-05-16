package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.dao.DrawingDaoBd;
import fr.uvsq21914172.pglp_99.engine.Drawing;
import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class Load extends Command {

  public Load(DrawingTui drawingtui) {
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
    Drawing d;
    if((d = ddb.find(params.get("name"))) != null) {
      drawingtui.setDrawing(d);
    }else {
      System.out.println("[DrawingApp] Drawing "+params.get("name")+" Missing");
    }
  }

}
