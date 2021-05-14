package banco;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;

public class ConFactory {
   public static Connection conexao(String url, String nome, String senha, String DRIVE) {                      
      try {
    	  System.out.println(url);
    	  Class.forName(DRIVE);    	  
    	  return DriverManager.getConnection(url, nome, senha);
      } catch(SQLException e) {
    	  throw new RuntimeException(e);
      } catch(ClassNotFoundException e) {    	
    	  return null;
      }      
   }
}
