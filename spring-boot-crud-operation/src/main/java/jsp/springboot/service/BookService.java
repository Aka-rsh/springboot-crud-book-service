package jsp.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import jsp.springboot.dao.BookDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import java.util.List;

@Service
   public class BookService {

        @Autowired
        private final BookDao bookDao;
        
        //Constructor
        public BookService(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        //Insert Single Operation
        public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
            ResponseStructure<Book> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Book Record Saved Successfully");
            response.setData(bookDao.saveBook(book));
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    
    //Insert Multiple Recored
    public ResponseEntity<ResponseStructure<List<Book>>> saveMultipleBooks(List<Book> books) {
    	  List<Book> savedBooks = bookDao.saveMultipleBooks(books);
          
          ResponseStructure<List<Book>> response = new ResponseStructure<>();
          response.setStatusCode(HttpStatus.CREATED.value());
          response.setMessage("Multiple Book Records Saved Successfully");
          response.setData(savedBooks);
          
          return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
    }

 // Fetching Multiple Records
    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
        List<Book> books = bookDao.findAllBook();
        
        ResponseStructure<List<Book>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All Books Fetched Successfully");
        response.setData(books);
        
        return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
    }
    
    //Fetch Record By id
    public ResponseEntity<ResponseStructure<Book>>getById(@PathVariable Integer id) {
        Book book = bookDao.findBookByid(id); 

        ResponseStructure<Book> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Book Found Successfully");
        response.setData(book);
        
        return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
    }
    

 // UPDATED: Using exact Company logic for Book Update
    public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
    	Book updatedBook = bookDao.updateBook(book);
        ResponseStructure<Book> response = new ResponseStructure<>();

        // Agar service ne null diya (matlab ID missing thi)
        if (updatedBook == null) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Id must be provided to update the record");
            response.setData(null);
            return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.BAD_REQUEST);
        }

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record updated successfully");
        response.setData(updatedBook);
        return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
    }
    
 // Delete All Records
    public ResponseEntity<ResponseStructure<String>> deleteAllBooks() {
    int deletedCount = bookDao.deleteAllBook();
        
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All Records Deleted Successfully");
        response.setData("Total Deleted Records: " + deletedCount);
        
        return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
    }

    // Delete Record By Id
    public ResponseEntity<ResponseStructure<String>> deleteReordById(Integer id) {
       Integer deletedId = bookDao.deleteReordById(id);
        
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Record deleted successfully");
        response.setData("Deleted ID: " + deletedId);
        
        return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
    }
        
    //Fetch Record By Title
        public ResponseEntity<ResponseStructure<List<Book>>> findBooksByTitle(String title) {
        	 List<Book> books = bookDao.findBookByTitle(title);
             
             ResponseStructure<List<Book>> response = new ResponseStructure<>();
             response.setStatusCode(HttpStatus.OK.value());
             response.setMessage("Books Found with title: " + title);
             response.setData(books);
             
             return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }
        
        //fetch record by title and author
        public ResponseEntity<ResponseStructure<List<Book>>> findByTitleAndAuthor(String title, String author) {

            List<Book> books = bookDao.findByTitleAndAuthor(title, author);

            ResponseStructure<List<Book>> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Books Found for the given Title and Author");
            response.setData(books);
            
            return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }
        
        //fetch record by price
        public ResponseEntity<ResponseStructure<List<Book>>> findByPriceGreaterThan(Double price) {
        	List<Book> books = bookDao.findByPriceGreaterThan(price);

            ResponseStructure<List<Book>> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Books found with price greater than " + price);
            response.setData(books);
            
            return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }
        
        //fetch record in between range of price 
        public ResponseEntity<ResponseStructure<List<Book>>> findByPriceBetween(Double startPrice, Double endPrice) {
        	List<Book> books = bookDao.findByPriceBetween(startPrice, endPrice);

            ResponseStructure<List<Book>> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Books found between " + startPrice + " and " + endPrice);
            response.setData(books);
            
            return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }
        
     // Available Books
        public ResponseEntity<ResponseStructure<List<Book>>> getAvailableBooks() {
        	List<Book> books = bookDao.getAvailableBooks();
            return createResponse(books, "Available books fetched");
        }

        // By Year
        public ResponseEntity<ResponseStructure<List<Book>>> getBooksByYear(Integer year) {
        	List<Book> books = bookDao.getBooksByYear(year);
            return createResponse(books, "Books for year " + year + " fetched");
        }

        // By Genre
        public ResponseEntity<ResponseStructure<List<Book>>>getBooksByGenre(String genre) {
        	List<Book> books = bookDao.getBooksByGenre(genre);
            return createResponse(books, "Books for genre " + genre + " fetched");
        }
        
        // Helper method code 
        private ResponseEntity<ResponseStructure<List<Book>>> createResponse(List<Book> books, String msg) {
            ResponseStructure<List<Book>> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage(msg);
            response.setData(books);
            return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }

}