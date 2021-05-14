import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoKanbanLeito;
import model.Kanban;

@WebServlet("/incluir_obs")
public class IncluirObsKanban extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		String leito = request.getParameter("leito");
		int prontuario = Integer.parseInt(request.getParameter("prontuario"));
		String Obs = request.getParameter("observacao");
		int fugulin = Integer.parseInt(request.getParameter("fugulin"));

		String so = String.valueOf(System.getProperty("os.name"));
		Properties config = new Properties();
		String arquivo;

		if (so.equals("Linux")) {
			arquivo = "/etc/KANBAN/config.ini";
		} else {
			arquivo = "c:/KANBAN/config.ini";
		}
		config.load(new FileInputStream(arquivo));

		DaoKanbanLeito daoKanbanLeito = new DaoKanbanLeito(config.getProperty("kanban_url"),
				config.getProperty("kanban_nome"), config.getProperty("kanban_senha"), "org.postgresql.Driver");
		System.out.println(Obs);

		daoKanbanLeito.incluirObs(leito, prontuario, Obs, fugulin);

		daoKanbanLeito = new DaoKanbanLeito(config.getProperty("aghu_url"), config.getProperty("aghu_nome"),
				config.getProperty("aghu_senha"), "org.postgresql.Driver");

		Vector<Kanban> resultado = daoKanbanLeito.ListarLeito();

		response.setContentType("text/html");
		request.setAttribute("resultado", resultado);
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.getRequestDispatcher("cadastro.jsp").forward(request, response);

		response.setContentType("text/html");
	}
}
