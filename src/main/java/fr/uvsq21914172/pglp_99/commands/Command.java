package fr.uvsq21914172.pglp_99.commands;

import java.util.HashMap;
import java.util.Map;
import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public abstract class Command {

  DrawingTui drawingtui;
  
  Map<String,String> params = new HashMap<String, String>();
  
  public void addParam(String name, String param) {
    params.putIfAbsent(name, param);
  }
  
  public Command(DrawingTui drawingtui) {
    super();
    this.drawingtui = drawingtui;
  }

  public abstract void execute();
}
