package fr.uvsq21914172.pglp_99.engine;

import fr.uvsq21914172.pglp_99.commands.Command;

/**
 * Hello world!
 *
 */
public class DrawingApp {
  public static void main(String[] args) {
    DrawingTui dtui = new DrawingTui();
    Command c;
    while (dtui.getState()) {
      c = dtui.input();
      if(c != null) {
        c.execute();
      }
    }
  }
}
