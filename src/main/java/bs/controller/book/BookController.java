package bs.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bs.entity.Book;
import bs.service.BookService;
import bs.service.UserService;

@RestController
@RequestMapping("/api")
public class BookController
{
	@Autowired
	BookService bookService;
	
		
	@GetMapping("/books") 
	public Page<Book> getActiveBooksPaginated(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
			@RequestParam(value = "pageSize",required = false, defaultValue = "5") int pageSize) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Book> page = bookService.listAllActiveBooks(pageable);
		return page;
	}
	
	
	
	@GetMapping("/books/{bookId}")
	public Book getBook(@PathVariable long bookId)
	{
		Book book = bookService.getBookById(bookId);

		return book;

	}

	@PostMapping("/books")
	public Book addBook(@RequestBody Book book)
	{
		book.setId(0);

		bookService.saveOrUpdateBook(book);

		return book;

	}

	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book)
	{
		bookService.saveOrUpdateBook(book);

		return book;

	}
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable long bookId)
	{

		bookService.deleteBook(bookId);
		
		return "Book ("+ bookId +") deleted!";

	}
	
}
