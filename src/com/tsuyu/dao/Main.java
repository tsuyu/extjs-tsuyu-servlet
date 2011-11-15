package com.tsuyu.dao;

import org.hibernate.Session;

import com.tsuyu.model.SignIn;
import com.tsuyu.model.User;
import com.tsuyu.persistance.HibernateUtil;



public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        SignIn signin = new SignIn();
        
        signin.setUserName("user1");
        signin.setPassword("1234");
        
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@yo.com");
        

        
        user.setSignIn(signin);
        signin.setUser(user);
        
    
        //session.save(signin);
        //session.save(add);
        //session.save(add2);
        
        SignIn sn = new SignIn();
        int signinId = 9;
        Object record = session.load(SignIn.class, signinId);
        session.delete(record);
        
      /*  List results = createQuery(
        "SELECT l FROM login AS l WHERE l.email=:email AND l.password=:password")
        .setParameter("login",login.getEmail())
        .setParameter("password",login.getPassword()).list();
      if (results.isEmpty()) {
        //.. login failed
      } else if (result.size() > 1) {
        throw new SomethingWrongException();
      } else {
        Login login = (Login) results.get(0);
      }*/


        session.getTransaction().commit();

	}
}
