package jsp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.service.BookService;

import java.util.List;

/**
 * RestController for handling Book-related CRUD operations.
 * Base URL: http://localhost:8080/library/book
 */
@RestController
@RequestMapping("/library/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService; // Dependency injection of the Service layer

    //Insert single record
    @PostMapping
    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    //Insert multiple record
    @PostMapping("/all")
    public ResponseEntity<ResponseStructure<List<Book>>> saveMultiple(@RequestBody List<Book> books) {
      return bookService.saveMultipleBooks(books);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Book>>getById(@PathVariable Integer id) {
    	return bookService.getById(id);  
    }


    @PutMapping
    public ResponseEntity<ResponseStructure<Book>> update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
    
    @DeleteMapping("/all")
    public ResponseEntity<ResponseStructure<String>> deleteAll() {
        return bookService.deleteAllBooks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> delete(@PathVariable Integer id) {
        return bookService.deleteAllBooks();
    }
    
    @GetMapping("/title/{title}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByTitle(@PathVariable String title) {
       return bookService.findBooksByTitle(title);
    }
    
    @GetMapping("/title/{title}/author/{author}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByTitleAndAuthor(
            @PathVariable String title, 
            @PathVariable String author) {
        return bookService.findByTitleAndAuthor(title, author);
    }
    
    @GetMapping("/price-greater/{price}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByPriceGreaterThan(@PathVariable Double price) {
        return bookService.findByPriceGreaterThan(price);
    }
    
    @GetMapping("/price-between/{startPrice}/{endPrice}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByPriceBetween(
            @PathVariable Double startPrice, 
            @PathVariable Double endPrice) {
           
    	return bookService.findByPriceBetween(startPrice, endPrice);
    }
    
    @GetMapping("/available")
    public ResponseEntity<ResponseStructure<List<Book>>> getAvailable() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByYear(@PathVariable Integer year) {
        return bookService.getBooksByYear(year);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<ResponseStructure<List<Book>>> getByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

}