package bs.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import bs.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();

	}

	@Override
	public Page<Book> listAllActiveBooks(Pageable pageable)
	{

		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);

		Predicate condition = builder.equal(root.get("active"), 1);
		criteriaQuery.where(condition);
		
	    criteriaQuery.orderBy(builder.desc(root.get("id")));
				
		Query<Book> query = session.createQuery(criteriaQuery);

		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		
		List<Book> users = query.getResultList();
		
		Page<Book> page = new PageImpl<>(users, pageable, getBookCount());
		
		return page;

	}
	
	public List<Book> listAllBooks()
	{
		Query<Book> query = getSession().createQuery("select b from Book b order by b.id desc", Book.class);
		List<Book> books = query.getResultList();
		return books;
	}

	public List<Book> findBooks(String searchedText)
	{
		Query<Book> query = getSession().createQuery("select b from Book b where b.title like :theTitle order by b.id desc", Book.class);
		query.setParameter("theTitle", searchedText);
		List<Book> books = query.getResultList();
		return books;
	}

	public void saveOrUpdateBook(Book book)
	{
		getSession().saveOrUpdate(book);
	}

	public Book getBookById(long id)
	{
		Query<Book> query = getSession().createQuery("select b from Book b where b.id=:theId", Book.class);
		query.setParameter("theId", id);

		return query.getSingleResult();

	}

	public void deleteBook(long id)
	{
		getSession().delete(getBookById(id));
	}

	public long getBookCount()
	{
		Query<Long> query = getSession().createQuery("select count(1) from Book", Long.class);
		
		return query.getSingleResult().longValue();
	}

}
