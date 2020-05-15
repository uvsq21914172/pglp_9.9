package fr.uvsq21914172.pglp_99.shape;

import java.util.List;
import fr.uvsq21914172.pglp_99.engine.Drawing;

public class Group implements IElement {

  List<String> elements;
  Drawing drawing;

  public boolean add(String element) {
    if (!elements.contains(element) && drawing.containsShape(element)) {
      elements.add(element);
      return true;
    } else {
      return false;
    }
  }

  public void remove(String element) {
    elements.remove(element);
  }
  
  public void deleteAll(String element) {
    for (int i = 0; i < elements.size(); i++) {
      drawing.removeShape(elements.get(i));
    }
  }

  public void move(int x, int y) {
    for (int i = 0; i < elements.size(); i++) {
      drawing.getShape(elements.get(i)).move(x, y);
    }
  }

  public String display() {
    StringBuffer buf = new StringBuffer();
    buf.append("Groupe");
    for (int i = 0; i < elements.size(); i++) {
      buf.append("\t" + drawing.getShape(elements.get(i)).display() + "\n");
    }
    return buf.toString();
  }

}
