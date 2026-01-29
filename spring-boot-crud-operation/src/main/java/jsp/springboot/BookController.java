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

    /**
     * Endpoint to save a single Book record.
     * @param book JSON object from request body
     * @return ResponseEntity containing saved book details and HTTP status 201
     */
    @PostMapping
    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    /**
     * Endpoint to save multiple Book records at once (Bulk Insert).
     * @param books List of Book objects
     * @return ResponseEntity containing list of saved books and HTTP status 201
     */
    @PostMapping("/all")
    public ResponseEntity<ResponseStructure<List<Book>>> saveMultiple(@RequestBody List<Book> books) {
        return bookService.saveMultipleBooks(books);
    }

    /**
     * Endpoint to fetch all Book records present in the database.
     * @return ResponseEntity containing list of all books and HTTP status 200
     */
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Book>>> getAll() {
        return bookService.findAllBooks();
    }

    /**
     * Endpoint to fetch a specific Book record using its unique ID.
     * @param id The primary key (id) of the book
     * @return ResponseEntity containing the book if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Book>> getById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    /**
     * Endpoint to update an existing Book record.
     * Requires the ID to be present in the JSON request body.
     * @param book JSON object with updated details
     * @return ResponseEntity containing the updated book or 404 if ID doesn't exist
     */
    @PutMapping
    public ResponseEntity<ResponseStructure<Book>> update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    /**
     * Endpoint to remove a Book record from the database.
     * @param id The unique ID of the book to be deleted
     * @return ResponseEntity with a success message or 404 if ID is missing
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> delete(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }
}