package fr.uvsq21914172.pglp_99.engine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import fr.uvsq21914172.pglp_99.commands.Command;
import fr.uvsq21914172.pglp_99.commands.CommandInput;

public class DrawingTui {

  private Drawing drawing = new Drawing();
  private boolean state = true;
  InputStream in = System.in;
  OutputStream out = System.out;
  Scanner sc = new Scanner(in,"UTF-8");
  
  public InputStream getIn() {
    return in;
  }

  public void setIn(InputStream in) {
    this.in = in;
  }

  public void setDrawing(Drawing drawing) {
    this.drawing = drawing;
  }

  public Command input() {
    return CommandInput.commandParser(sc.nextLine(), this);
  }
  
  public Drawing getDrawing() {
    return drawing;
  }

  public void quit() {
    state = false;
  }
  
  public boolean getState() {
    return state;
  }

  public boolean ask(String string) {
    ((PrintStream) out).println(string);
    return sc.nextLine().equalsIgnoreCase("y");
  }

}
