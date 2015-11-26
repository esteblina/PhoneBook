package net.ukr.steblina.models;

import java.sql.SQLWarning;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@Profile("db")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@Override
	public void save(User user) {
		getSession().save(user);
	}

	@Override
	public User getByLogin(String login) throws SQLWarning {
		Criteria criteria = getSession().createCriteria(User.class);
		User user=(User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
		if(user==null)
			throw new SQLWarning("No user");
		return user;
	}
	
}
