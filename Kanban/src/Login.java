
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

import dao.DaoKanbanLeito;
import model.Kanban;

@WebServlet("/login")
public class Login extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String so = String.valueOf(System.getProperty("os.name"));
		Properties config = new Properties();
		String arquivo;

		if (so.equals("Linux")) {
			arquivo = "/etc/KANBAN/config.ini";
		} else {
			arquivo = "c:/KANBAN/config.ini";
		}
		config.load(new FileInputStream(arquivo));

		DaoKanbanLeito daoKanbanLeito = new DaoKanbanLeito(config.getProperty("aghu_url"),
				config.getProperty("aghu_nome"), config.getProperty("aghu_senha"), "org.postgresql.Driver");

		Vector<Kanban> resultado = daoKanbanLeito.ListarLeito();
		// service user
		String serviceUserDN = "CN=Adm EduardoAmaral de Paula,OU=Administradores,OU=Usuarios,OU=HDT,OU=EBSERH,DC=ebserhnet,DC=ebserh,DC=gov,DC=br";
		String serviceUserPassword = "edu*20182";

		// user to authenticate
		String identifyingAttribute = "uid";
		String identifier = request.getParameter("usuario");
		String password = request.getParameter("password");
		String base = "OU=Empregados,OU=Usuarios,OU=HDT,OU=EBSERH,DC=ebserhnet,DC=ebserh,DC=gov,DC=br";

		String ldap = "10.98.0.5";
		int port = 389;
		String ldapUrl = "ldap://" + ldap + ":" + port;

		DirContext serviceCtx = null;
		try {

			Hashtable serviceEnv = new Hashtable(11);
			serviceEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			serviceEnv.put(Context.PROVIDER_URL, ldapUrl);
			serviceEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			serviceEnv.put(Context.SECURITY_PRINCIPAL, serviceUserDN);
			serviceEnv.put(Context.SECURITY_CREDENTIALS, serviceUserPassword);
			serviceCtx = new InitialDirContext(serviceEnv);

			String[] attributeFilter = { identifyingAttribute };
			SearchControls sc = new SearchControls();
			sc.setReturningAttributes(attributeFilter);
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(" + identifyingAttribute + "=" + identifier + ")";
			NamingEnumeration<SearchResult> results = serviceCtx.search(base, searchFilter, sc);
			if (results.hasMore()) {

				SearchResult result = results.next();
				String distinguishedName = result.getNameInNamespace();

				Hashtable authEnv = new Hashtable(11);
				authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				authEnv.put(Context.PROVIDER_URL, ldapUrl);
				authEnv.put(Context.SECURITY_PRINCIPAL, distinguishedName);
				authEnv.put(Context.SECURITY_CREDENTIALS, password);
				new InitialDirContext(authEnv);

				response.setContentType("text/html");
				request.setAttribute("resultado", resultado);
				request.setAttribute("rodape", config.getProperty("roda_pe"));
				request.setAttribute("so", so);
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);

				response.setContentType("text/html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serviceCtx != null) {
				try {
					serviceCtx.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
		response.setContentType("text/html");
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.getRequestDispatcher("permissao.jsp").forward(request, response);

		response.setContentType("text/html");

	}
}
