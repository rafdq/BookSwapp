package bs.dao;

import java.util.List;

import bs.entity.Book;

public interface BookDAO
{

	public List<Book> listAllBooks();

	public List<Book> findBooks(String searchedText);

	public void saveOrUpdateBook(Book book);

	public Book getBookById(int id);

	public void deleteBook(int id);

}
