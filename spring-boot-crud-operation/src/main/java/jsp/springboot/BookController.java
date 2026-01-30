package jsp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for handling Book-related CRUD operations.
 * Base URL: http://localhost:8080/library/book
 */
@RestController
@RequestMapping("/library/book")
public class BookController {

    @Autowired
    private BookService bookService; // Dependency injection of the Service layer

    @PostMapping
    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseStructure<List<Book>>> saveMultiple(@RequestBody List<Book> books) {
        return bookService.saveMultipleBooks(books);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Book>>> getAll() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Book>> getById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }


    @PutMapping
    public ResponseEntity<ResponseStructure<Book>> update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
    
    @DeleteMapping("/all")
    public ResponseEntity<ResponseStructure<String>> deleteAllBooks() {
        return bookService.deleteAllBooks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> delete(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }
}