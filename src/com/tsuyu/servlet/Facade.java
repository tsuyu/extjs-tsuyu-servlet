package com.tsuyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.tsuyu.model.SignIn;
import com.tsuyu.util.Util;

import com.tsuyu.dao.MenuDAO;
import com.tsuyu.dao.SignInDAO;

@WebServlet(urlPatterns={"/FacadeServlet"},name="facade-servlet")
public class Facade extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4980526580706450676L;
	private static final String CONTENT_TYPE = "application/json";
	private MenuDAO mi;
	private Util util;
	private SignInDAO signinapi;

	public void init() throws ServletException {
		this.mi = new MenuDAO();
		this.util = new Util();
		this.signinapi = new SignInDAO();
	}
	
	/*public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        JSONArray error = JSONArray.fromObject(util.getModelMapMsg("Invalid Servlet Request - No Action specified.",false));
        if (action == null) {
        	out.println(error.toString());
            return;
        }
        if (action.equals("login")) {
           // empLogin(props, out, req, res);
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");
        	
        	try {
        		if ((username == null) || (username.length() == 0) || (password == null) || (password.length() == 0)) {
                    response.sendRedirect("index.jsp");
                } else {
                	if (login(request) == true){
						String login = new Util().getModelMapMsg("Welcome dudes!", true).toString();
						out.println(login.toString());
					}else{
						String login = new Util().getModelMapMsg("Please check your signin detail!", false).toString();
						out.println(login.toString());
					}
                }	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if(action.equals("view")){
        	try {
        		out.println(getTreeList1(request));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if(action.equals("save")){
        	try {
        		CatalogModel catalog = new CatalogModel();
        		catalog.setId(request.getParameter("id"));
        		catalog.setLeaf(request.getParameter("leaf"));
        		catalog.setText(request.getParameter("text"));
        		catalog.setNode("root");
        		JSONArray json = JSONArray.fromObject(addCatalog(catalog));
                out.println(json.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else {
        	out.println(error.toString());
        }
	}*/

	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        JSONArray error = JSONArray.fromObject(util.getModelMapMsg("Invalid Servlet Request - No Action specified.",false));
    	
        if (action == null) {
        	out.println(error.toString());
            return;
        }
        if (action.equals("read")) {
        	try {
        		JSONArray json = JSONArray.fromObject(getTreeModel1(request));
                out.println(json.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  else {
        	out.println(error.toString());
        }
		
	}*/

	/*public JSONObject getTreeList1(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		try {

			return util.getModelMap(this.mi.getTreeList(request));

		} catch (Exception e) {

			return util.getModelMapMsg("Error trying to retrieve treeList.",false);
		}
	}

	public List<CatalogModel> getTreeModel1(HttpServletRequest request)
			throws ClassNotFoundException, SQLException, SecurityException, NoSuchFieldException, ParseException {
		 return this.mi.getTreeModel(request);
	}

	public JSONObject addCatalog(CatalogModel catalogmodel)
			throws ClassNotFoundException, SQLException {

		try {

			return util.getModelMap(this.mi.addCatalog(catalogmodel));

		} catch (Exception e) {

			return util.getModelMapMsg("Error trying to retrieve treeList.",false);
		}

	}*/

	/*public JSONObject updateCatalog(CatalogModel catalogmodel)
			throws ClassNotFoundException, SQLException {

		try {

			return util.getModelMap(this.mi.updateCatalog(catalogmodel));

		} catch (Exception e) {

			return util.getModelMapMsg("Error trying to retrieve treeList.",false);
		}

	}

	public JSONObject deleteCatalog(String id) throws ClassNotFoundException,
			SQLException {

		try {

			return util.getModelMap(this.mi.deleteCatalog(id));

		} catch (Exception e) {

			return util.getModelMapMsg("Error trying to retrieve treeList.",false);
		}

	}*/

	/*public int addUser(SignIn user) throws ClassNotFoundException,
			SQLException {
		return this.signinapi.addUser(user);
	}

	public String tempPassword(String password) {
		return this.signinapi.tempPassword(password);
	}

	public boolean login(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		return this.signinapi.login(request);
	}

	public int deleteAccount(String userid) throws ClassNotFoundException,
			SQLException {
		return this.signinapi.deleteAccount(userid);
	}

	public Boolean checkFirstLogin(String userid, String password)
			throws ClassNotFoundException, SQLException {
		return this.signinapi.checkFirstLogin(userid, password);
	}

	public int checkPassword(String user_id, String password)
			throws ClassNotFoundException, SQLException {
		return this.signinapi.checkPassword(user_id, password);
	}

	public int changePassword(String user_id, String password)
			throws ClassNotFoundException, SQLException {
		return this.signinapi.changePassword(user_id, password);
	}

	public int checkStaffAvailable(String id) throws ClassNotFoundException,
			SQLException {
		return this.signinapi.checkStaffAvailable(id);
	}
	public void destroy() {}*/
}
