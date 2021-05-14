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

@WebServlet("/carregar_leito")
public class CarregarLeito extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String leito = request.getParameter("leito");

		String so = String.valueOf(System.getProperty("os.name"));
		Properties config = new Properties();
		String arquivo;

		if (so.equals("Linux")) {
			arquivo = "/etc/CDMA/config.ini";
		} else {
			arquivo = "c:/CDMA/config.ini";
		}
		config.load(new FileInputStream(arquivo));

		DaoKanbanLeito daoKanbanLeito = new DaoKanbanLeito(config.getProperty("aghu_url"),
				config.getProperty("aghu_nome"), config.getProperty("aghu_senha"), "org.postgresql.Driver");

		Vector<Kanban> resultado = daoKanbanLeito.CarregarLeito(leito);

		response.setContentType("text/html");
		request.setAttribute("resposta", resultado);
		request.getRequestDispatcher("ajaxCarregaLeito.jsp").forward(request, response);

	}
}
