

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exames
 */
@WebServlet("/Exames")
public class Exames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exames() {
        super();
        // TODO Auto-generated constructor stub
    }
    
protected void printForm(PrintWriter out, String url, String a, String b) {
    	
    	out.println("<form action=\"" + url + "\" method=GET>");
    	
    	out.println("<label for=\"afname\">Indirizzo IP: </label>");
    	
    	out.print("<input type=\"text\" id=\"afname\" name=\"a\"");
    	if (a != null){
    		out.print(" value=\"" + a + "\"");
    	}
    	out.println(" >");
    	
    	out.println("<label for=\"bfname\"");
    	out.println(" >");
    	out.println("CIDR");
    	out.println("</label>");
		
		out.print("<input type=\"text\" id=\"bfname\" name=\"b\"");
		
		if (b != null){
			out.print(" value=\"" + b + "\"");
		}
		out.println(" >");
		
		out.println("<input type=\"submit\" name=\"Go\" value=\"Calcola\">");
		out.println("</form>");
    }

protected String getResult(String a, String b) {
	String res="NA";

	
	if(a == null) {
		return res;
	}
	if(b == null) {
		return res;
	}
	
	
	
	return "OK";
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = "Calcolo parametri";
		String  a, b, url, res;
		a = request.getParameter("a");
		b = request.getParameter("b");
		url = request.getRequestURI().toString();
		
		out.println("<html>");
		out.println("<head>");		
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<h1>"+title+"</h1>");
		
		printForm(out, url, a, b);
		res = getResult(a, b);
		
		System.out.println(res);
		
		if(!"NA".equals(res)) {
			
			//String str = "192.226.12.11";
			NetworkId k = new NetworkId();
			String classe = k.findClass(a);
			System.out.println(classe);
			String risulato = k.seprate(a,classe);
			
			out.println("<p>"+ "New adress is: "+ risulato+ "</p>");
			
				
	        
		}

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
