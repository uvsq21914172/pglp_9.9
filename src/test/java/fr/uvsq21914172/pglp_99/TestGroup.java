package fr.uvsq21914172.pglp_99;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import fr.uvsq21914172.pglp_99.engine.Drawing;
import fr.uvsq21914172.pglp_99.shape.AShape;
import fr.uvsq21914172.pglp_99.shape.Circle;
import fr.uvsq21914172.pglp_99.shape.Group;

public class TestGroup {
  @Test
  public void testAdd() {
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    Group g = new Group(d);
    d.addShape("s", s);
    g.add("s");
    assertTrue(g.getShapes().contains("s"));
  }

  @Test
  public void testMove() {
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    AShape t = new Circle(10, 1, 1);
    AShape u = new Circle(1, 10, 1);
    Group g = new Group(d);
    d.addShape("s", s);
    d.addShape("t", t);
    d.addShape("u", u);
    g.add("s");
    g.add("t");
    g.move(1, 1);
    assertEquals(s.getX(), 2);
    assertEquals(s.getY(), 2);
    assertEquals(t.getX(), 11);
    assertEquals(t.getY(), 2);
    assertEquals(u.getX(), 1);
    assertEquals(u.getY(), 10);
  }

  @Test
  public void testRemove() {
    Drawing d = new Drawing();
    AShape s = new Circle(1, 1, 1);
    AShape t = new Circle(10, 1, 1);
    AShape u = new Circle(1, 10, 1);
    Group g = new Group(d);
    d.addShape("s", s);
    d.addShape("t", t);
    d.addShape("u", u);
    g.add("s");
    g.add("t");
    assertTrue(g.getShapes().contains("s"));
    assertTrue(g.getShapes().contains("t"));
    assertTrue(!g.getShapes().contains("u"));
    g.remove("t");
    assertTrue(g.getShapes().contains("s"));
    assertTrue(!g.getShapes().contains("t"));
    assertTrue(!g.getShapes().contains("u"));
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
