package fr.uvsq21914172.pglp_99.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import fr.uvsq21914172.pglp_99.shape.*;

public class Drawing implements IElement{
  
  Map<String, AShape> shapes = new HashMap<String, AShape>();
  Map<String, Group> groups = new HashMap<String, Group>();

  public boolean addShape(String symbol, AShape shape) {
    if(!shapes.containsKey(symbol) && !groups.containsKey(symbol)) {
      shapes.put(symbol, shape);
      return true;
    }else {
      return false;
    }
  }
  
  public void removeShape(String element) {
    shapes.remove(element);
    Iterator<Group> it = groups.values().iterator();
    while(it.hasNext()) {
      it.next().remove(element);
    }
  }
  
  
  public boolean addGroup(String symbol, Group group) {
    if(!shapes.containsKey(symbol) && !groups.containsKey(symbol)) {
      groups.put(symbol, group);
      return true;
    }else {
      return false;
    }
  }
  
  public void removeGroup(String element) {
    groups.remove(element);
  }
  
  public void deleteGroup(String element) {
    groups.get(element).deleteAll(element);
    groups.remove(element);
  }
  
  public void move(int x, int y) {
    Iterator<AShape> it = shapes.values().iterator();
    while(it.hasNext()) {
      it.next().move(x,y);
    }
  }

  public String display() {
    Iterator<AShape> it = shapes.values().iterator();
    StringBuffer buf = new StringBuffer();
    while(it.hasNext()) {
      buf.append(it.next().display()+"\n");
    }
    return buf.toString();
  }

  public AShape getShape(String string) {
    return shapes.get(string);
  }

  public boolean containsShape(String element) {
    return shapes.containsKey(element);
  }

}
