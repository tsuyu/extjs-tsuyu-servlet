package com.tsuyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import com.tsuyu.dao.SignInDAO;
import com.tsuyu.util.Util;

@WebServlet(urlPatterns = {"/setting/signin"}, name = "signin-servlet")
public class SignInServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6750504303791861485L;
	private static final String CONTENT_TYPE = "application/json";
	private SignInDAO si;
	private Util util;
	
	public void init() throws ServletException {
		this.si = new SignInDAO();
		this.util = new Util();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		HttpSession session = request.getSession(true);
		String action = request.getParameter("action");
		String mode = request.getParameter("mode");
		PrintWriter out = response.getWriter();
		
		JSONObject error = util.getModelMapMsg("Invalid Servlet Request - No Action specified.", false);
		if (action == null) {
			out.print(error);
			return;
		}
		if (action.equals("login")) {
	           
	       String username = request.getParameter("username");
	       String password = request.getParameter("password");
	        	
	        	
    		if ((username == null) || (username.length() == 0) || (password == null) || (password.length() == 0)) {
                response.sendRedirect("index.jsp");
            } else {
            	try {
					if (loginFacade(request) == true){
						String login = new Util().getModelMapMsg("Welcome dudes!", true).toString();
						session.setAttribute("username",username);
						session.setAttribute("signinId", 1);
						out.println(login.toString());
					}else{
						String login = new Util().getModelMapMsg("Please check your signin detail!", false).toString();
						out.println(login.toString());
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }	
		}else if(action.equals("viewu") && mode.equals("readu")) {
			out.print(getUserFacade(request));
		} else {
			out.print(error);
		}
	}
	
	protected boolean loginFacade(HttpServletRequest request) throws ClassNotFoundException, SQLException{
			return this.si.login(request);
	}
	
	private JSONObject getUserFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.si.showSigninGrid(),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
}
