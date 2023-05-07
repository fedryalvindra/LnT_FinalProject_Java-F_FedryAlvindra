package data.config;

     import java.sql.Connection;
     import java.sql.DriverManager;
     import java.sql.PreparedStatement;
     import java.sql.ResultSet;
     import java.sql.ResultSetMetaData;
     import java.sql.Statement;
public class Connectiondb {

     public Connection connect;
     public Statement statement;
     public ResultSet rs;
     public ResultSetMetaData rsMetaData;
     public PreparedStatement ps;

     public Connectiondb() {
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptpuding", "root", "");

               statement = connect.createStatement();

          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public ResultSet getPudsData() {
          try {
               ps = connect.prepareStatement("SELECT * FROM pudding");
               rs = ps.executeQuery();
               rsMetaData = rs.getMetaData();
          } catch (Exception e) {
               e.printStackTrace();
          }
          return rs;
     }

     public void insertPudsData(String id, String puddName, int price, int stock) {

          if (!id.matches("^PD-\\d{3}")) {
               System.out.println("ID must be PD-XXX (for example PD-123)");
               return;
          }
          try {
               ps = connect.prepareStatement("INSERT INTO pudding (pudding_id, pudding_name, pudding_price, pudding_stock) VALUES (?,?,?,?)");
               ps.setString(1, id);
               ps.setString(2, puddName);
               ps.setInt(3, price);
               ps.setInt(4, stock);

               ps.execute();

          } catch (Exception e) {
               e.printStackTrace();
          }

     }

     public void updateData(String id, String puddName, int price, int stock) {

          try {
               ps = connect.prepareStatement("UPDATE pudding SET pudding_name = ?, pudding_price = ?, pudding_stock = ?, WHERE pudding_id = ?");
               ps.setString(1, puddName);
               ps.setInt(2, price);
               ps.setInt(3, stock);
               ps.setString(4, id);

               ps.execute();

          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public void deleteData(String id) {

          try {
               ps = connect.prepareStatement("DELETE FROM pudding WHERE pudding_id = ?");
               ps.setString(1, id);

               ps.execute();

          } catch (Exception e) {
               e.printStackTrace();
          }
     }

}
