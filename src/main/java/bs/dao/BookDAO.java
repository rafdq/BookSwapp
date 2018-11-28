package bs.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bs.entity.Book;

public interface BookDAO
{

	public Page<Book> listAllActiveBooks(Pageable pageable);
	
	public List<Book> listAllBooks();

	public List<Book> findBooks(String searchedText);

	public void saveOrUpdateBook(Book book);

	public Book getBookById(long id);

	public void deleteBook(long id);

}
