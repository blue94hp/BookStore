package bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bookstore.entity.Book;
import bookstore.repository.BookRepository;

@RestController
@CrossOrigin(origins="http://localhost:9000")
@RequestMapping(value="/api")
public class BookController {
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookController(BookRepository bookRepository){
		this.bookRepository=bookRepository;
	}
	
	@RequestMapping(value="/books",method=RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBook(@RequestParam int page, @RequestParam int size){
		Page<Book> books = bookRepository.findAll(new PageRequest(page, size));
		return new ResponseEntity<List<Book>>(books.getContent(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/book/{bookId}",method=RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable String bookId){
		return new ResponseEntity<Book>(bookRepository.findOne(bookId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
		Book newBook = new Book(book.getTitle(), book.getType(),
				book.getAuthor(), book.getPublisher(),
				book.getCoverType(), book.getFormat(),
				book.getNumberOfPage(), book.getPrice(),
				book.getDescription(),book.getImageUrl());
		bookRepository.save(newBook);
		return new ResponseEntity<List<Book>>(bookRepository.findAll(),HttpStatus.OK);
	}

	@RequestMapping(value="/book/{bookId}",method=RequestMethod.PUT)
	public ResponseEntity<List<Book>> updateBook(@RequestBody Book book, @PathVariable String bookId){
		Book updatedBook = bookRepository.findOne(bookId);
		updatedBook.setTitle(book.getTitle());
		updatedBook.setType(book.getType());
		updatedBook.setAuthor(book.getAuthor());
		updatedBook.setPublisher(book.getPublisher());
		updatedBook.setCoverType(book.getCoverType());
		updatedBook.setFormat(book.getFormat());
		updatedBook.setNumberOfPage(book.getNumberOfPage());
		updatedBook.setPrice(book.getPrice());
		updatedBook.setDescription(book.getDescription());
		bookRepository.save(updatedBook);
		return new ResponseEntity<List<Book>>(bookRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/book/{bookId}",method=RequestMethod.DELETE)
	public ResponseEntity<List<Book>> deleteBook(@PathVariable String bookId){
		bookRepository.delete(bookRepository.findOne(bookId));
		return new ResponseEntity<List<Book>>(bookRepository.findAll(),HttpStatus.OK);
	}
	
}
