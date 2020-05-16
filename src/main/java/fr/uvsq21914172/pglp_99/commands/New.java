package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.Drawing;
import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class New extends Command {

  public New(DrawingTui drawingtui) {
    super(drawingtui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    drawingtui.setDrawing(new Drawing());
  }

}
