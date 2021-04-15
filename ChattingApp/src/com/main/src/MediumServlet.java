package com.main.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class MediumServlet
 */
@WebServlet(urlPatterns="/MediumServlet",asyncSupported=true)
public class MediumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<AsyncContext> contexts = new LinkedList<>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MediumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		AsyncContext AsyncCont=request.startAsync(request, response);
		AsyncCont.setTimeout(10 * 60 * 1000);
		contexts.add(AsyncCont);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		List<AsyncContext> AsyncContList = new LinkedList<>(this.contexts); 
		this.contexts.clear();
			PrintWriter out=response.getWriter();
			String message=request.getParameter("message");
			String handler=request.getParameter("handler");
			
			String time[]=request.getParameter("t").split(" ");
			String date="("+time[2]+"-"+time[1]+"-"+time[3]+") "+time[4];
			String htmlMessage = "<p>"+ "["+date+"]"+handler + ": " + message + "</p>";
			ServletContext cont=request.getServletContext();
		
			//out.print(" htmlMessage:"+htmlMessage);
			if(cont.getAttribute("message")==null)
			{
			//out.print(" before cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
			//out.print("cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
				cont.setAttribute("message", htmlMessage);
				//out.print(" after cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
			}
			else
			{   
				//out.print("cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
				String prev_messages=(String)cont.getAttribute("message");
				cont.setAttribute("message",prev_messages+"<br>"+htmlMessage);
			}
			for(AsyncContext AsyncCont:AsyncContList )
			{
				try(PrintWriter res_Writer=AsyncCont.getResponse().getWriter())
				{  
//					HttpServletRequest Asynrequest=(HttpServletRequest)AsyncCont.getRequest();
//				String handler_asyn=Asynrequest.getParameter("handler");
//				Asynrequest.setAttribute("handler", handler_asyn);
					res_Writer.println(htmlMessage);
					res_Writer.flush();
					
					AsyncCont.complete();
					
				}
				catch(Exception e)
				{
					
				}
			}
			
	}

}
