package bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.dao.BookDAO;
import bs.entity.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService
{
	@Autowired
	BookDAO bookDAO;
	
	public List<Book> listAllBooks()
	{
		return bookDAO.listAllBooks();
	}

	public List<Book> findBooks(String searchedText)
	{
		return bookDAO.findBooks(searchedText);
	}

	public void saveOrUpdateBook(Book book)
	{
		bookDAO.saveOrUpdateBook(book);
	}

	public Book getBookById(int id)
	{
		return bookDAO.getBookById(id);
	}

	public void deleteBook(int id)
	{
		bookDAO.deleteBook(id);
	}

}
