import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;  
import java.util.Vector;
import java.util.Properties;
import model.Usuario;  
import dao.DaoUsuarioMV;
import dao.DaoUsuarioAGHU;
import java.util.Timer;
import java.util.TimerTask;


public class IntegracaoUsuarioAGHUxMV {

	
	public static final long TEMPO = (1000); // atualiza a cada 3 minutos
	
	public static void log(String msg, String msgErro) {	
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd.MM.yyyy");		
		File log = new File ("log_" + formatador.format( data ) + ".txt");
		//
		try
		{
			if (!log.exists()) {
				log.createNewFile ();				
			}
			FileWriter fileW = new FileWriter (log, true);
			BufferedWriter buffW = new BufferedWriter (fileW);
			//buffW.newLine ();	
			buffW.write (msg + " " + msgErro + "\n");
				    
		    buffW.close ();
		    //System.out.println(msg + " " + msgErro);
		    
		} catch (IOException io){
			System.out.println(io.getMessage());			 
		}
	}
	
	public static void main(String[] args) throws IOException, IOException{	
		
		/*Timer timer = null;
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {*/
					
					try {
						
						String so = String.valueOf( System.getProperty("os.name") );		
						Properties config = new Properties();
						String arquivo;		
						
						if (so.equals("Linux")) {
							arquivo = "/etc/rob_usu/config.ini";
						} else {
							arquivo = "c:/rob_usu/config.ini";
						}						
						config.load(new FileInputStream(arquivo));
					
						DaoUsuarioAGHU daoUsuarioAGHU = new DaoUsuarioAGHU(config.getProperty("prop.server.aghu.url"), config.getProperty("prop.server.aghu.nome"), config.getProperty("prop.server.aghu.senha"), config.getProperty("prop.server.aghu.drive"));
						DaoUsuarioMV daoUsuarioMV = new DaoUsuarioMV(config.getProperty("prop.server.mv.url"), config.getProperty("prop.server.mv.nome"), config.getProperty("prop.server.mv.senha"), config.getProperty("prop.server.mv.drive"));	      
						
						Vector<Usuario> resultado_mv = daoUsuarioMV.ListarUsuarios();	      
						Vector<Usuario> resultado_aghu = daoUsuarioAGHU.ListarUsuarios();
					      
						int contador = 0;
						for (Iterator<Usuario> iterator_aghu = resultado_aghu.iterator(); iterator_aghu.hasNext();) {  
							Usuario AGHU = iterator_aghu.next(); 
							boolean naoexiste = true;
					    	  
							for (Iterator<Usuario> iterator_mv = resultado_mv.iterator(); iterator_mv.hasNext();) {  
								Usuario MV = iterator_mv.next();	
						    	  
								if (MV.getUsuario().equals(AGHU.getUsuario()) || (MV.getCPF() != null && MV.getCPF().equals(AGHU.getCPF())) ) {
									naoexiste = false;
									break;
								}
							}
					    	 
							if (naoexiste) {
								daoUsuarioMV.insereUsuario(AGHU);
								daoUsuarioMV.associaUsuarioPapel(AGHU);
								daoUsuarioMV.associaUsuarioEmpresa(AGHU);
								daoUsuarioMV.associaUsuarioEstoque(AGHU);
								contador ++;
							}
						}
						daoUsuarioAGHU.log("quantida Inserida: " +contador, "");

					} catch (Exception e) {
						log("Erro: ", e.getMessage());
					}
				}
			//};
			//timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		//}
	}
//}
