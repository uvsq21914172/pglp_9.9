package fr.uvsq21914172.pglp_99.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.uvsq21914172.pglp_99.engine.Drawing;
import fr.uvsq21914172.pglp_99.shape.AShape;
import fr.uvsq21914172.pglp_99.shape.Circle;
import fr.uvsq21914172.pglp_99.shape.Group;
import fr.uvsq21914172.pglp_99.shape.Rectangle;
import fr.uvsq21914172.pglp_99.shape.Square;
import fr.uvsq21914172.pglp_99.shape.Triangle;

/**
 * DAO bd de la classe groupe.
 * 
 * @author Dalil
 */

public class DrawingDaoBd extends Dao<Drawing> {


  Connection co;

  /**
   * Constructeur
   */
  public DrawingDaoBd() {
    String dbUrl = "jdbc:derby:D:BDTEST2;create=true";
    try {
      co = DriverManager.getConnection(dbUrl);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Insertion
   * 
   * @param grp Groupe.
   * @param co Connection a la bd.
   * @throws SQLException Erreur bd.
   */
  static void insertDrawing(final Drawing drw, final Connection co) throws SQLException {
    PreparedStatement prepare = co.prepareStatement("INSERT INTO DRAWING VALUES (?)");
    prepare.setString(1, drw.getName());
    prepare.execute();

    PreparedStatement shapes =
        co.prepareStatement("INSERT INTO SHAPE (type, name, nameDrawing, x, y) VALUES (?, ?, ?,?, ?)",
            Statement.RETURN_GENERATED_KEYS);
    PreparedStatement params =
        co.prepareStatement("INSERT INTO PARAM (id_shape, name, value) VALUES (?, ?, ?)");
    PreparedStatement groups = co.prepareStatement(
        "INSERT INTO GROUPS (name ,nameDrawing) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
    PreparedStatement appartient = co.prepareStatement("INSERT INTO APPARTIENT VALUES (?, ?)");
    groups.setString(2, drw.getName());
    shapes.setString(3, drw.getName());

    for (String name : drw.getShapes()) {
      AShape s = drw.getShape(name);
      shapes.setInt(1, s.type());
      shapes.setString(2, name);
      shapes.setInt(4, s.getX());
      shapes.setInt(5, s.getY());
      shapes.executeUpdate();

      ResultSet tableKeys = shapes.getGeneratedKeys();
      tableKeys.next();
      s.setId(tableKeys.getInt(1));
      params.setInt(1, s.getId());
      switch (s.type()) {
        case 0:
          if (s instanceof Circle) {
            params.setString(2, "rayon");
            params.setInt(3, ((Circle) s).getRayon());
            params.executeUpdate();
          }
          break;
        case 1:
          if (s instanceof Rectangle) {
            params.setString(2, "width");
            params.setInt(3, ((Rectangle) s).getWidth());
            params.executeUpdate();
            params.setString(2, "length");
            params.setInt(3, ((Rectangle) s).getLength());
            params.executeUpdate();
          }
          break;
        case 2:
          if (s instanceof Square) {
            params.setString(2, "width");
            params.setInt(3, ((Square) s).getWidth());
            params.executeUpdate();
          }
          break;
        case 3:
          if (s instanceof Triangle) {
            params.setString(2, "side1");
            params.setInt(3, ((Triangle) s).getSide1());
            params.executeUpdate();
            params.setString(2, "side2");
            params.setInt(3, ((Triangle) s).getSide2());
            params.executeUpdate();
            params.setString(2, "side3");
            params.setInt(3, ((Triangle) s).getSide3());
            params.executeUpdate();
          }
          break;
        default:
          break;
      }
    }

    for (String name : drw.getGroups()) {
      Group g = drw.getGroup(name);
      groups.setString(1, name);
      groups.executeUpdate();

      ResultSet tableKeys = groups.getGeneratedKeys();
      tableKeys.next();
      g.setId(tableKeys.getInt(1));
      appartient.setInt(1, g.getId());
      for (String shape : g.getShapes()) {
        AShape s = drw.getShape(shape);
        appartient.setInt(2, s.getId());
        appartient.executeUpdate();
      }
    }

  }

  static Drawing searchGroupe(String name, Connection co) throws SQLException {
    PreparedStatement prepare = co.prepareStatement("SELECT * FROM DRAWING WHERE name = ?",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    prepare.setString(1, name);
    ResultSet rs1 = prepare.executeQuery();

    Drawing drw = null;
    PreparedStatement shapes = co.prepareStatement("SELECT * FROM SHAPE WHERE nameDrawing = ?",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    PreparedStatement params = co.prepareStatement("SELECT * FROM PARAM WHERE id_shape = ?",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    PreparedStatement groups = co.prepareStatement("SELECT * FROM GROUPS WHERE nameDrawing = ?",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    PreparedStatement appartient =
        co.prepareStatement("SELECT name FROM APPARTIENT NATURAL JOIN SHAPE WHERE id_group = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    groups.setString(1, name);
    shapes.setString(1, name);
    if (rs1.first()) {
      drw = new Drawing();
      ResultSet rs2 = shapes.executeQuery();
      ResultSet rs3;
      AShape s = null;
      int a = 1, b = 1, c = 1;
      if (rs2.first()) {
        while (!rs2.isAfterLast()) {
          params.setInt(1, rs2.getInt("id"));
          rs3 = params.executeQuery();
          if (rs3.first()) {
            while (!rs3.isAfterLast()) {
              String param = rs3.getString("name");
              if (param.equalsIgnoreCase("width") || param.equalsIgnoreCase("side1")
                  || param.equalsIgnoreCase("rayon")) {
                a = rs3.getInt("value");
              }
              if (param.equalsIgnoreCase("length") || param.equalsIgnoreCase("side2")) {
                b = rs3.getInt("value");
              }
              if (param.equalsIgnoreCase("side3")) {
                c = rs3.getInt("value");
              }
              rs3.next();
            }
          }
          switch (rs2.getInt("type")) {
            case 0:
              s = new Circle(rs2.getInt("x"), rs2.getInt("y"), a);
              break;
            case 1:
              s = new Rectangle(rs2.getInt("x"), rs2.getInt("y"), a, b);
              break;
            case 2:
              s = new Square(rs2.getInt("x"), rs2.getInt("y"), a);
              break;
            case 3:
              s = new Triangle(rs2.getInt("x"), rs2.getInt("y"), a, b, c);
              break;
            default:
              break;
          }
          drw.addShape(rs2.getString("name"), s);
          rs2.next();
        }
      }

      rs2 = groups.executeQuery();
      Group g;
      if (rs2.first()) {
        while (!rs2.isAfterLast()) {
          g = new Group(drw);
          drw.addGroup(rs2.getString("name"), g);
          appartient.setInt(1, rs2.getInt("id"));
          rs3 = appartient.executeQuery();
          if (rs3.first()) {
            while (!rs3.isAfterLast()) {
              g.add(rs3.getString("name"));
              rs3.next();
            }
          }
          rs2.next();
        }
      }
    }
    return drw;
  }

  static void removeDrawing(String name, Connection co) throws SQLException {
    PreparedStatement prepare = co.prepareStatement("DELETE FROM DRAWING WHERE name = ?");
    prepare.setString(1, name);
    prepare.execute();
    prepare = co.prepareStatement("DELETE FROM PARAM WHERE id_shape = (SELECT id FROM SHAPE WHERE nameDrawing = ?)");
    prepare.setString(1, name);
    prepare.execute();
    prepare = co.prepareStatement("DELETE FROM APPARTIENT WHERE id_group = (SELECT id FROM GROUPS WHERE nameDrawing = ?)");
    prepare.setString(1, name);
    prepare.execute();
    prepare = co.prepareStatement("DELETE FROM SHAPE WHERE nameDrawing = ?");
    prepare.setString(1, name);
    prepare.execute();
    prepare = co.prepareStatement("DELETE FROM GROUPS WHERE nameDrawing = ?");
    prepare.setString(1, name);
    prepare.execute();
  }

  /**
   *
   */
  @Override
  public Drawing create(Drawing drw) {
    try {
      createTables(co);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    try {
      co.setAutoCommit(false);
      insertDrawing(drw, co);
      co.commit();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      try {
        co.rollback();
      } catch (SQLException e1) {
        System.err.println(e1.getMessage());
      }
    } finally {
      try {
        co.setAutoCommit(true);
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }

    return drw;
  }

  @Override
  public Drawing find(String name) {
    try {
      createTables(co);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    try {
      return searchGroupe(name, co);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Drawing update(Drawing drw) {
    try {
      createTables(co);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    try {
      removeDrawing(drw.getName(), co);
      insertDrawing(drw, co);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
    return drw;
  }

  @Override
  public void delete(Drawing drw) {
    try {
      createTables(co);
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    }
    try {
      removeDrawing(drw.getName(), co);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Verifie si un table existe.
   * 
   * @param co Connection a la BD.
   * @param table Table.
   * @return Si la table existe.
   * @throws SQLException
   */
  private static boolean existable(Connection co, String table) throws SQLException {
    DatabaseMetaData dbmd = co.getMetaData();
    ResultSet rs = dbmd.getTables(null, null, table, null);
    if (rs.next()) {
      return true;
    }
    return false;
  }

  /**
   * Cree toute les tables.
   * 
   * @param co Connection a la BD.
   * @throws SQLException Erreur bd.
   */
  public static void createTables(Connection co) throws SQLException {
    Statement stmt = co.createStatement();
    if (!existable(co, "DRAWING")) {
      stmt.executeUpdate("CREATE TABLE DRAWING (name VARCHAR(50) PRIMARY KEY)");
    }

    if (!existable(co, "GROUPS")) {
      stmt.executeUpdate("CREATE TABLE GROUPS (id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,"
          + "name VARCHAR(50) NOT NULL, nameDrawing VARCHAR(50) NOT NULL, "
          + "FOREIGN KEY (nameDrawing) REFERENCES DRAWING(name))");
    }

    if (!existable(co, "SHAPE")) {
      stmt.executeUpdate("CREATE TABLE SHAPE (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
          + " name VARCHAR(50) NOT NULL, type INT, nameDrawing VARCHAR(50) NOT NULL, x INT, y INT,"
          + "FOREIGN KEY (nameDrawing) REFERENCES DRAWING(name))");
    }
    if (!existable(co, "APPARTIENT")) {
      stmt.executeUpdate("CREATE TABLE APPARTIENT (id_group INT, id_shape INT,"
          + "PRIMARY KEY (id_group, id_shape)," + "FOREIGN KEY (id_group) REFERENCES GROUPS(id),"
          + "FOREIGN KEY (id_shape) REFERENCES SHAPE(id))");
    }
    if (!existable(co, "PARAM")) {
      stmt.executeUpdate("CREATE TABLE PARAM (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, id_shape INT NOT NULL, "
          + "name VARCHAR(50) NOT NULL, value INT,"
          + " FOREIGN KEY (id_shape) REFERENCES SHAPE(id))");
    }
  }
}
