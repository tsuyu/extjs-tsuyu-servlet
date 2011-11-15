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
import com.tsuyu.model.Accordian;
import com.tsuyu.model.Children;
import com.tsuyu.model.Leaf;
import com.tsuyu.util.Util;

@WebServlet(urlPatterns = {"/setting/accordian"}, name = "accordian-servlet")
public class AccordianServlet extends HttpServlet {

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
		if (action.equals("viewa") && mode.equals("reada")) {
			out.print(getAccordianFacadeGrid(request));
		} else if (action.equals("icona") && mode.equals("reada")) {
			out.print(getIconFacade());
		}else if (action.equals("savea") && mode.equals("accordiana")) {
			out.print(addAccordianFacade(request));
		}else if (action.equals("deletea") && mode.equals("destroya")) {
			out.print(deleteAccordianFacade(Integer.parseInt(request.getParameter("accordianId"))));
		}else if (action.equals("updatea") && mode.equals("reada")) {
			out.print(util.replaceUpdate(updateAccordianFacade(request).toString()));
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
		if (action.equals("icona") && mode.equals("reada")) {
			out.print(getIconFacade());
		}else if (action.equals("reada") && mode.equals("chaineda")) {
			out.print(getAccessLevelFacade());
		}else {
			out.print(error);
		}
	}
	
	

	private JSONObject getAccordianFacadeGrid(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.showAccordianGrid(request),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,false);
		}
	}
	
	private JSONObject getAccessLevelFacade(){
		try {
			return util.getModelMap(this.menu.showAccessLevelList(),"accessLevel");

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

	
	private JSONObject addAccordianFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.addAccordian(request),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject deleteAccordianFacade(int accordianId){
		try {
			return util.getModelMap(this.menu.deleteAccordian(accordianId),"data");
		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		}
	}
	
	private JSONObject updateAccordianFacade(HttpServletRequest request){
		try {
			return util.getModelMap(this.menu.updateAccordian(request),"data");

		} catch (Exception e) {
			return util.getModelMapMsg("Error "+e,
				false);
		} 
	}
	
	

	public void destroy() {
	}

}

