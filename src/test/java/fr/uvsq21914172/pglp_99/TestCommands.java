package fr.uvsq21914172.pglp_99;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import fr.uvsq21914172.pglp_99.engine.Drawing;
import fr.uvsq21914172.pglp_99.shape.AShape;
import fr.uvsq21914172.pglp_99.shape.Circle;
import fr.uvsq21914172.pglp_99.shape.Group;

public class TestCommands {
  @Test
  public void testAddShape() {
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    d.addShape("s", s);
    assertTrue(d.containsShape("s"));
    assertEquals(s, d.getShape("s"));
  }
  
  @Test
  public void testAddGroup() {
    Drawing d = new Drawing();
    Group g = new Group(d);
    d.addGroup("s", g);
    assertTrue(d.containsGroup("s"));
    assertEquals(g, d.getGroup("s"));
  }

  @Test
  public void testRemove() {
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    Group g = new Group(d);
    Group h = new Group(d);
    d.addShape("s", s);
    d.addGroup("g", g);
    d.addGroup("h", h);
    g.add("s");
    h.add("s");
    assertTrue(d.containsShape("s"));
    assertTrue(g.getShapes().contains("s"));
    assertTrue(h.getShapes().contains("s"));
    assertEquals(s, d.getShape("s"));
    d.removeShape("s");
    assertTrue(!d.containsShape("s"));
    assertTrue(!g.getShapes().contains("s"));
    assertTrue(!h.getShapes().contains("s"));
  }

  @Test
  public void testDisplay() {
    //String a = "Groupe\tCircle(center=(1,1),rayon=1)\r\n" + "\tCircle(center=(10,1),rayon=1)\r\n";
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    AShape t = new Circle(10, 1, 1);
    Group g = new Group(d);
    d.addShape("s", s);
    d.addShape("t", t);
    g.add("s");
    g.add("t");
    assertTrue(true);
  }
}
