package bookstore.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import bookstore.entity.Cart;
import bookstore.repository.BookRepository;
import bookstore.repository.CartRepository;

@RestController
@CrossOrigin(origins="http://localhost:9000")
@RequestMapping(value="/api")
public class CartController {

	private CartRepository cartRepository;
	private BookRepository bookRepository;
	
	@Autowired
	public CartController(CartRepository cartRepository, BookRepository bookRepository){
		this.cartRepository = cartRepository;
		this.bookRepository = bookRepository;
	}
	
	@RequestMapping(value="/cart/{bookId}",method=RequestMethod.POST)
	public ResponseEntity<?> addCart(@PathVariable String bookId){
		if(cartRepository.count() > 0){
			List<Book> books = new ArrayList<Book>();
			List<Cart> carts = cartRepository.findAll();
			books = carts.get(0).getBooks();
			books.add(bookRepository.findOne(bookId));
			Cart cart = cartRepository.findOne(carts.get(0).getId());
			cart.setBooks(books);
			cartRepository.save(cart);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}
		List<Book> books = new ArrayList<Book>();
		books.add(bookRepository.findOne(bookId));
		Cart cart = new Cart(books);
		cartRepository.save(cart);
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public ResponseEntity<List<Book>> getCart(){
			List<Book> books = new ArrayList<Book>();
			List<Cart> carts = cartRepository.findAll();
			books = carts.get(0).getBooks();
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}
	
}
