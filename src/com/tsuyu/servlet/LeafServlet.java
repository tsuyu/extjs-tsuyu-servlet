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

@WebServlet(urlPatterns = {"/setting/leaf"}, name = "leaf-servlet")
public class LeafServlet extends HttpServlet {

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
		if (action.equals("viewl") && mode.equals("readl")) {
			out.print(getLeafFacadeGrid(request));
		} else if (action.equals("iconl") && mode.equals("readl")) {
			out.print(getIconFacade());
		} else if (action.equals("savel") && mode.equals("leafl")) {
			out.print(addLeafFacade(request));
		} else if (action.equals("deletel") && mode.equals("destroyl")) {
			out.print(deleteLeafFacade(Integer.parseInt(request.getParameter("leafId"))));
		} else if (action.equals("updatel") && mode.equals("readl")) {
			out.print(util.replaceUpdate(updateLeafFacade(request).toString()));
		} else {
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
		if (action.equals("iconl") && mode.equals("readl")) {
			out.print(getIconFacade());
		} else if (action.equals("readl") && mode.equals("chainedl")) {
			out.print(getChildrenFacade());
		} else {
			out.print(error);
		}
	}

	private JSONObject getLeafFacadeGrid(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.showLeafGrid(request),"data");

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
	
	private JSONObject getChildrenFacade(){
		try {
			return util.getModelMap(this.menu.showChildrenList(),"child");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject addLeafFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.addLeaf(request),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject deleteLeafFacade(int leafId){
		try {
			return util.getModelMap(this.menu.deleteLeaf(leafId),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject updateLeafFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.updateLeaf(request),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		} 
	}
	
	

	public void destroy() {
	}

}
