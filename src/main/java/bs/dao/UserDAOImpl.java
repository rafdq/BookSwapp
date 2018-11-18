package bs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import bs.entity.User;

public class UserDAOImpl implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();

	}

	public List<User> listAllUsers()
	{

		Query<User> query = getSession().createQuery("from User", User.class);
		List<User> users = query.getResultList();

		return users;
	}

	public void saveOrUpdateUser(User user)
	{
		getSession().saveOrUpdate(user);
	}

	public User getUserById(int id)
	{
		Query<User> query = getSession().createQuery("select u from User u join fetch u.booksToSwap where u.id=:theId", User.class);
		query.setParameter("theId", id);

		return query.getSingleResult();
	}

	public void deleteUser(int id)
	{
		User user = getUserById(id);
		getSession().delete(user);

	}

}
