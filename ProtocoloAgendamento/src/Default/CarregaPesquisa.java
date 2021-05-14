package Default;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoConsultaPaciente;
import model.Consulta;

@WebServlet("/carrega_pesquisa")
public class CarregaPesquisa extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String numero_paciente = request.getParameter("numero_paciente");

		String so = String.valueOf(System.getProperty("os.name"));
		Properties config = new Properties();
		String arquivo;

		if (so.equals("Linux")) {
			arquivo = "/etc/CDMA/config.ini";
		} else {
			arquivo = "c:/CDMA/config.ini";
		}
		config.load(new FileInputStream(arquivo));
		
		DaoConsultaPaciente agendamento = new DaoConsultaPaciente(config.getProperty("mv_url"),
				config.getProperty("mv_nome"), config.getProperty("mv_senha"), "oracle.jdbc.driver.OracleDriver");
		
		Vector<Consulta> resultado = agendamento.Listar(Integer.parseInt(numero_paciente));
		
		response.setContentType("text/html");
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.setAttribute("resultado", resultado);
		request.getRequestDispatcher("carregaPesquisa.jsp").forward(request, response);

		response.setContentType("text/html");

	}
}
