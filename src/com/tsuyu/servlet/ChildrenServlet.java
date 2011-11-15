package com.tsuyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import net.sf.json.JSONObject;

import com.tsuyu.dao.MenuDAO;
import com.tsuyu.util.Util;

@WebServlet(urlPatterns = {"/setting/children"}, name = "children-servlet")
public class ChildrenServlet extends HttpServlet {

	private static final long serialVersionUID = -4980526580706450676L;
	private static final String CONTENT_TYPE = "application/json";
	private MenuDAO menu;
	private Util util;

	public void init() throws ServletException {
		this.menu = new MenuDAO();
		this.util = new Util();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		
		String action = request.getParameter("action");
		String mode = request.getParameter("mode");
		PrintWriter out = response.getWriter();
		JSONObject error = util.getModelMapMsg("Invalid Servlet Request - No Action specified.", false);
		if (action == null) {
			out.print(error);
			return;
		}
		if (action.equals("viewc") && mode.equals("readc")) {
			out.print(getChildrenFacadeGrid(request));
		}else if (action.equals("iconc") && mode.equals("readc")) {
			out.print(getIconFacade());
		}else if (action.equals("readc") && mode.equals("chainedc")) {
			out.print(getAccordianFacade());
		}else if (action.equals("savec") && mode.equals("childrenc")) {
			out.print(addChildrenFacade(request));
		}else if (action.equals("deletec") && mode.equals("destroyc")) {
			out.print(deleteChildrenFacade(Integer.parseInt(request.getParameter("childrenId"))));
		}else if (action.equals("updatec") && mode.equals("readc")) {
			out.print(util.replaceUpdate(updateChildrenFacade(request).toString()));
		}else {
			out.print(error);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		response.setContentType(CONTENT_TYPE);
		
		String action = request.getParameter("action");
		String mode = request.getParameter("mode");
		PrintWriter out = response.getWriter();
		JSONObject error = util.getModelMapMsg("Invalid Servlet Request - No Action specified.", false);
		if (action == null) {
			out.print(error);
			return;
		}
		if (action.equals("iconc") && mode.equals("readc")) {
			out.print(getIconFacade());
		} else if (action.equals("readc") && mode.equals("chainedc")) {
			out.print(getAccordianFacade());
		}else {
			out.print(error);
		}
	}

	private JSONObject getChildrenFacadeGrid(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.showChildrenGrid(request),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,false);
		}
	}
	
	private JSONObject getIconFacade(){
		try {
			return util.getModelMap(this.menu.showIcon(),"icon");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject getAccordianFacade(){
		try {
			return util.getModelMap(this.menu.showAccordianList(),"accordian");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject addChildrenFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.addChildren(request),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject deleteChildrenFacade(int childrenId){
		try {
			return util.getModelMap(this.menu.deleteChildren(childrenId),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject updateChildrenFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.updateChildren(request),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		} 
	}
	
	

	public void destroy() {
	}

}
