package bs.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import bs.controller.UserNotFoundException;
import bs.controller.UserNotUniqueNameOrEmailException;
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

	public Page<User> listAllUsers(Pageable pageable)
	{

		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		root.fetch("booksToSwap", JoinType.INNER);

		criteriaQuery.select(root).distinct(true);
		criteriaQuery.orderBy(builder.desc(root.get("id")));
				
		Query<User> query = session.createQuery(criteriaQuery);

		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		
		List<User> users = query.getResultList();
		
		Page<User> page = new PageImpl<>(users, pageable, getUsersCount());
		
		return page;

	}

	
	public void saveOrUpdateUser(User user)
	{
		try
		{
			getSession().saveOrUpdate(user);
			
		} catch (ConstraintViolationException e)
		{
			throw new UserNotUniqueNameOrEmailException("User with given name or email already exists!");
		}
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

	@Override
	public long getUsersCount()
	{
		Query<Long> query = getSession().createQuery("select count(1) from User", Long.class);
		
		return query.getSingleResult().longValue();
	}

}
