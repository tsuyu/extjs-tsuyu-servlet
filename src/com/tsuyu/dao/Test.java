package com.tsuyu.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.tsuyu.model.AccessLevel;
import com.tsuyu.model.Accordian;
import com.tsuyu.model.Children;
import com.tsuyu.model.Leaf;
import com.tsuyu.model.SignIn;
import com.tsuyu.model.User;
import com.tsuyu.persistance.HibernateUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.HibernateTransformer;
public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        //session = HibernateUtil.getSessionFactory().openSession();
        /*Accordian acc = new Accordian();
        acc.setAccordianSequence(1);
        acc.setAccordianName("Ketetapan");*/
        
        
        //Children child = new Children();
        
        /*child.setChildrenName("Chilll");
        child.setChildrenSequence(1);
        child.setChildrenPath("lor.jsp");*/
        
       // child.setAccordian(acc);
        //acc.getChildrens().add(child);
        
        /*Children child = (Children)session.createQuery("from Children where " +
        		"childrenId = :childrenId").setParameter("childrenId", 5).list().get(0);
        
        Leaf leaf = new Leaf();
        leaf.setLeafSequence(1);
        leaf.setLeafName("itachi");
        leaf.setLeafMapper("itachi.jsp");
        leaf.setCreateTime(new Date());
        
        leaf.setChildren(child);
        child.getLeafs().add(leaf);
        session.save(child);*/
       /* String username = "root";
        String pass = "48690";
        Query query = session
		.createQuery("select count(*) from SignIn si where si.userName = :userName" +
				" and si.password = :password").setParameter("userName",username.trim())
		.setParameter("password",pass);
        Long result = (Long) query.uniqueResult();
		System.out.println(result);*/
         /*String sql_query = "from Accordian a";
	     Query query = session.createQuery(sql_query);
	     Iterator ite = query.list().iterator();
	     System.out.println();*/
        /*session.createCriteria(Accordian.class)
        .createCriteria("accessLevel", "accessLevels")
         .createCriteria("accordian", "accordians")
        .add(Expression.eq("accessLevels.accessLevelId", 1))
        .list();*/
        
      /*  DetachedCriteria subCriteria = DetachedCriteria.forClass(SignIn.class);
        subCriteria.createCriteria("accessLevel", "accessLevels");
        subCriteria.add(Restrictions.eq("accessLevelId", 1));
        Criteria criteria = session.createCriteria(Accordian.class)
        .createCriteria("accessLevel", "accessLevels");
        criteria.add(Subqueries.propertyEq("accessLevelId", subCriteria)).list();*/
        try{
       /* String sql_query = "select b.accordianName from AccessLevel a join a.accordians b "
        	+"where a.accessLevelId in(select a.accessLevelId from a where a.accessLevelId = 1)";*/
        	 String hql = "select b.accordianName as accordianName from AccessLevel a join a.accordians b "
             	+"where a.accessLevelId = (select c.accessLevelId from AccessLevel c join c.signIns d where d.signinId = 1)";      
        	 List data = session.createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
     		.list();
        	 for (Object object : data)
     		{
     		Map row = (Map)object;
     		System.out.println(row.get("accordianName"));
     		}
      
        }catch(Exception e ){
        	          System.out.println(e.getMessage());
        	        }
	    // System.out.println("Accordian\t"+"Children\t"+"Leaf\t");
	     /*while ( ite.hasNext() ) {
	          Object[] pair = (Object[]) ite.next();
	          Accordian acc = (Accordian) pair[0];
	          //Children child = (Children) pair[1];
	          //Leaf leaf = (Leaf) pair[2];
	          //AccessLevel access= (AccessLevel) pair[0];
	          System.out.print(acc.getAccordianName());
	         // System.out.print("\t"+child.getChildrenName());
	         // System.out.print("\t\t"+leaf.getLeafName());
	          //System.out.print("\t\t"+access.getAccessLevelName());
	          System.out.println();
	     }*/
        
         /*String sql_query = "from Accordian";
	     Query query = session.createQuery(sql_query);
	     Iterator ite = query.list().iterator();
	     System.out.println();
	     System.out.println("Accordian\t");
	     while ( ite.hasNext() ) {
	          Object[] pair = (Object[]) ite.next();
	          Accordian acc = (Accordian) pair[0];
	          System.out.print(acc.getAccordianName());
	          System.out.println();
	     }*/
        
        /*MenuDAO menu = new MenuDAO();
		for (Iterator iter = menu.accordianList().iterator(); iter.hasNext();)
		{
			System.out.print(((Accordian) iter.next()).getAccordianName());
		}*/
        
        /*Query query = session.createQuery("from Accordian a join a.childrens i where a.accordianId = :accordianId")
        .setParameter("accordianId", 7);
       
        Iterator ite = query.list().iterator();
	     System.out.println();
	     System.out.println("Accordian\t"+"Children\t"+"Leaf\t");
	     while ( ite.hasNext() ) {
	    	 Object[] pair = (Object[]) ite.next();
	    	 Accordian acc = (Accordian) pair[0];
	    	 Children child = (Children) pair[1];
	    	 System.out.println(child.getChildrenName());
	     }*/
        
        
		/*Query query = session.createQuery("from Accordian a join a.childrens i where " +
				"a.accordianId = :accordianId").setParameter("accordianId",7);
		query.setCacheable(true);
		query.setCacheRegion("query.Lor");
        Iterator ite = query.list().iterator();
         ArrayList<Children> arr = new ArrayList<Children>();
	     Object[] pair = null;
	     while ( ite.hasNext() ) {
	    	 pair = (Object[]) ite.next();
	    	 arr.add((Children) pair[1]);
	     }*/
        /*Query query = session.createQuery(
				"from Children a join a.leafs i where "
						+ "a.childrenId = :childrenId").setParameter(
				"childrenId", 5);*/
        
       /* Query query = session.createQuery(
		"select leafName from Leaf");

		Iterator<?> ite = query.list().iterator();*//*
		ArrayList<Leaf> leafAll = new ArrayList<Leaf>();
		Object[] pair = null;
		while (ite.hasNext()) {
			pair = (Object[]) ite.next();
			leafAll.add((Leaf) pair[0]);
		}
		
	     for(Leaf x : leafAll){
	    	 System.out.print(x.getLeafName());
	     }*/
	    
	    /*for (Iterator iter = arr.iterator(); iter.hasNext();)
		{
		//loop accordian
	    	System.out.print(((Children) iter.next()).getChildrenName());
		}*/
       // session.getTransaction().commit();
        
        /*Query query = session.createQuery("from Children c " +
		"join c.leafs d");
        Iterator<?> ite = query.list().iterator();
		ArrayList<Leaf> leafAll = new ArrayList<Leaf>();
		Object[] pair = null;
		Leaf leafs  = null;
		Leaf leafss  = null;
		while (ite.hasNext()) {
			leafs = new Leaf();
			pair = (Object[]) ite.next();
			leafss = (Leaf) pair[1];
			leafs.setLeafName(leafss.getLeafName());
			leafAll.add(leafs);
		}*/
		
	     /*for(Leaf x : leafAll){
	    	 System.out.print(x.getLeafName());
	     }*/
		
        /*List<Leaf> leafAll= new ArrayList<Leaf>();
		Children child = (Children)session.get(Children.class, 114); 
		Set<Leaf> sets = child.getLeafs();
		 
		for ( Iterator<Leaf> iter = sets.iterator();iter.hasNext(); ) { 
		      Leaf sdr = (Leaf) iter.next();
		      System.out.println(sdr.getLeafName());
		}*/
       
        /*List data = session.createSQLQuery("select  leafName,leafDescription from leaf WHERE 1")
		.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
		.list();
		
		for (Object object : data)
		{
		Map row = (Map)object;
		System.out.println(row.get("leafName") + ", " + row.get("leafDescription"));
		}*/
        
        /*Leaf leaf = (Leaf) session.get(Leaf.class, new Integer(9));
		session.delete(leaf);
        */
        
        /*Children child = (Children)session.createQuery("from Children where " +
		"childrenId = :childrenId").setParameter("childrenId", 5).list().get(0);
		
		Leaf leaf = (Leaf)session.createQuery("from Leaf where " +
		"leafId = :leafId").setParameter("leafId",5 ).list().get(0);
		
		leaf.setLeafSequence(3);
		leaf.setLeafName("lor");
		leaf.setLeafDescription("ko");
		leaf.setLeafMapper("bo");
		leaf.setLeafIcon("jo");
		
		leaf.setChildren(child);
		child.getLeafs().add(leaf);
		session.saveOrUpdate(child);*/
        session.getTransaction().commit();
	}
}
