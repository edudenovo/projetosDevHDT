import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAgenda;
import model.Agenda;

@WebServlet("/cadastrar_telefone")
public class CadastrarAgenda extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int resposta;
		String telefone = request.getParameter("telefone");
		String nome = request.getParameter("nome");	
		
		String so = String.valueOf( System.getProperty("os.name") );		
		Properties config = new Properties();
		String arquivo;		
		
		if (so.equals("Linux")) {
			arquivo = "/etc/agenda/config.ini";
		} else {
			arquivo = "c:/agenda/config.ini";
		}
		config.load(new FileInputStream(arquivo));
		
		DaoAgenda daoAgenda = new DaoAgenda(config.getProperty("url"), config.getProperty("nome"), config.getProperty("senha"), "org.postgresql.Driver");
		
		resposta = daoAgenda.insereAgenda(telefone.toUpperCase(), nome.toUpperCase());
		
		response.setContentType("text/html");						
		request.setAttribute("resposta", resposta);		
		request.getRequestDispatcher("ajaxCadastroTelefone.jsp").forward(request, response);
	}
}
