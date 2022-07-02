

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Permutate
 */
@WebServlet("/Permutate")
public class Permutate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Permutate() {
        super();
        // TODO Auto-generated constructor stub
    }
    

private String[] Fisher_Yates_Durstenfeld(String []elems) {
    String []shuffledWord = elems; // start with original
    int elemsSize = elems.length;
    for(int i=0;i<elemsSize-1;i++) {
        int j = ThreadLocalRandom.current().nextInt(i, elemsSize);
        String app = shuffledWord[i];
        shuffledWord[i] = shuffledWord[j];
        shuffledWord[j] = app;
    }
    return shuffledWord;
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		String lista = request.getParameter("lista");
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = "Es_esame";
		
		out.println("<html>");
		out.println("<head>");		
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<h1>"+title+"</h1>");
		out.println("<form method=GET action=\""+url+"\">");
	
				
				String nuovaLista = "";
				//System.out.println("ORA VALE"+lista);
				
				if(lista != null && !lista.equals("")) {
					
					String []elems = lista.split(" ");
					String []shaffle = Fisher_Yates_Durstenfeld(elems);
					
					for (String s : shaffle) {
						//out.println(s+"<br>");
						nuovaLista += s+" ";
						}
					}
				  

				  
				  out.print("<input type=text name=lista");
					if (nuovaLista != null) {
						out.print(" value=\""+nuovaLista+"\"");
					}
					out.println(" />");
					out.println("<button type=\"submit\" value=\"Submit\">Permutate</button>");
					out.println("</form>");

			    	
				          

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
