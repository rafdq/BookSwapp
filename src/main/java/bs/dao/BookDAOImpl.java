package bs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import bs.entity.Book;
import bs.entity.User;

public class BookDAOImpl implements BookDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();

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

	public Book getBookById(int id)
	{
		Query<Book> query = getSession().createQuery("select b from Book where b.id=:theId", Book.class);
		query.setParameter("theId", id);

		return query.getSingleResult();

	}

	public void deleteBook(int id)
	{
		getSession().delete(getBookById(id));
	}

}
