package net.ukr.steblina.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Profile("db")
public class PhoneDAOImpl implements PhoneDAO {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Phone phone) {
		getSession().save(phone);
	}

	@Override
	public void delete(Phone phone) {
		getSession().delete(phone);
	}

	@Override
	public void update(Phone phone) {
		getSession().update(phone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Phone> getAll() {
		return getSession().createQuery("from Phone").list();
	}

	@Override
	public Phone getById(Integer id) {
		 return (Phone) getSession().get(Phone.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Phone> getAllByUser(User user) {
		return getSession().createQuery("from Phone where id_user="+user.getId()).list();
	}

}
