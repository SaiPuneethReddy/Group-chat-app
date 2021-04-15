package com.main.src;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class LoginPage
 */
@WebServlet(urlPatterns="/LoginPage",asyncSupported=true)
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<AsyncContext> contextsLoginPage = new LinkedList<>(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
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
		contextsLoginPage.add(AsyncCont);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		List<AsyncContext> AsyncContList = new LinkedList<>(this.contextsLoginPage); 
		this.contextsLoginPage.clear();
		PrintWriter out=response.getWriter();
		//ArrayList<HttpSession> array_session=new ArrayList<HttpSession>();
		out.print("in LoginPage");
	
				 DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			       Date dateobj = new Date();
			  
			    // Medium.array_session.add(session);
				String handler=request.getParameter("handler");
				 //session.setAttribute("handler",handler); 
				request.setAttribute("handler", handler);
				//request.setAttribute("request", request);
				
				String NewJoin= "["+df.format(dateobj)+"] "+handler+" is join in chat";
				ServletContext cont=request.getServletContext();
				if(cont.getAttribute("message")==null)
				{
				
					cont.setAttribute("message", NewJoin);
				}
				else
				{   
					
					String prev_messages=(String)cont.getAttribute("message");
					cont.setAttribute("message",prev_messages+"<br>"+NewJoin);
				}
				RequestDispatcher rd=request.getRequestDispatcher("chat.jsp");
				rd.forward(request, response);
				
				for(AsyncContext AsyncCont:AsyncContList )
				{
					try(PrintWriter res_Writer=AsyncCont.getResponse().getWriter())
					{   
					
						res_Writer.println(NewJoin);
						res_Writer.flush();
						
						AsyncCont.complete();
						
					}
					catch(Exception e)
					{
						
					}
				}
	}
	
		


}
