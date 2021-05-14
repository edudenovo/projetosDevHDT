import java.io.FileInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAgenda;
import model.Agenda;

@WebServlet("/lista")
public class Lista extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String telefone="", nome ="";
		if (request.getParameter("telefone") != null) {
			telefone = request.getParameter("telefone");
		}
		if (request.getParameter("nome") != null) {
			nome = request.getParameter("nome");	
		}
		
		String so = String.valueOf( System.getProperty("os.name") );		
		Properties config = new Properties();
		String arquivo;		
		
		if (so.equals("Linux")) {
			arquivo = "/etc/agenda/config.ini";
		} else {
			arquivo = "c:/agenda/config.ini";
		}
		config.load(new FileInputStream(arquivo));
		nome = removerAcentos(nome);
		
		DaoAgenda daoAgenda = new DaoAgenda(config.getProperty("url"), config.getProperty("nome"), config.getProperty("senha"), "org.postgresql.Driver");
		Vector<Agenda> lista_telefonica = daoAgenda.ListarAgenda(telefone.toUpperCase(), nome.toUpperCase());
		//Vector<Agenda> lista_telefonica = daoAgenda.ListarAgenda();
		
		response.setContentType("text/html");						
		request.setAttribute("lista", lista_telefonica);		
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.getRequestDispatcher("lista.jsp").forward(request, response);
	}
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
