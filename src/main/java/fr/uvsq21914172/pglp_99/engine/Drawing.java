package fr.uvsq21914172.pglp_99.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import fr.uvsq21914172.pglp_99.shape.*;

public class Drawing implements IElement{
  
  private Map<String, AShape> shapes = new HashMap<String, AShape>();
  private Map<String, Group> groups = new HashMap<String, Group>();
  private String name;

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
    Iterator<Entry<String, AShape>> it = shapes.entrySet().iterator();
    StringBuffer buf = new StringBuffer();
    Entry<String, AShape> ent;
    while(it.hasNext()) {
      ent = it.next();
      buf.append(ent.getKey() +"="+ent.getValue().display()+"\n");
    }
    return buf.toString();
  }

  public AShape getShape(String string) {
    return shapes.get(string);
  }

  public boolean containsShape(String element) {
    return shapes.containsKey(element);
  }

  public boolean containsGroup(String element) {
    return groups.containsKey(element);
  }

  public Group getGroup(String element) {
    return groups.get(element);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getGroups() {
    return groups.keySet();
  }

  public Set<String> getShapes() {
    return shapes.keySet();
  }

}
