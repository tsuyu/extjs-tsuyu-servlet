package com.tsuyu.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.tsuyu.model.Accordian;
import com.tsuyu.model.Children;
import com.tsuyu.model.Leaf;

public interface IMenuDAO {
	ArrayList<Accordian> accordianList();
	ArrayList<Children> childrenList(int accordianId);
	ArrayList<Leaf> showLeaf(HttpServletRequest request) throws HibernateException, SecurityException, NoSuchFieldException, ParseException;
}
