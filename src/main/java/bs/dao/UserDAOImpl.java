package bs.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bs.controller.UserNotFoundException;
import bs.entity.User;

@Repository
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

		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);

		userRoot.fetch("booksToSwap", JoinType.INNER);

		criteriaQuery.select(userRoot).distinct(true);
		criteriaQuery.orderBy(builder.desc(userRoot.get("id")));
				
		Query<User> query = session.createQuery(criteriaQuery);

		List<User> users = query.getResultList();

		return users;
	}

	public void saveOrUpdateUser(User user)
	{
		getSession().saveOrUpdate(user);
	}

	public User getUserById(int id)
	{
		User user;

		try
		{
			Query<User> query = getSession().createQuery("select u from User u left join fetch u.booksToSwap where u.id=:theId", User.class);
			query.setParameter("theId", id);
			user = query.getSingleResult();
			
		} catch (NoResultException e)
		{
			throw new UserNotFoundException("User id not found - " + id);
		}

		return user;
	}

	public void deleteUser(int id)
	{
		User user = getUserById(id);
		getSession().delete(user);

	}

}
