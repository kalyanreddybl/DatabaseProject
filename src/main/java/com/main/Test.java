package com.main;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.model.Employee;
import com.util.HibernateUtil;

public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		
		criteria = session.createCriteria(Employee.class, "employee");
		criteria.setFetchMode("employee.address", FetchMode.JOIN);
		criteria.createAlias("employee.address", "address"); 

		ProjectionList columns = Projections.projectionList()
						.add(Projections.property("name"))
						.add(Projections.property("address.city"));
		criteria.setProjection(columns);

		List<Object[]> list = criteria.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}
		

	
		
		
		sessionFactory.close();
	}

}
