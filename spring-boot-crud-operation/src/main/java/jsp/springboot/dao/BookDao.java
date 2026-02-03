package jsp.springboot.dao;

import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

	@Autowired
    private final BookRepository bookRepository;

	//Constructor
    public BookDao(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Insert single record
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    //Insert Multiple Record
    public List<Book> saveMultipleBooks(List<Book> book){
    	return bookRepository.saveAll(book);
    }
    
    //fetch operation
    public List<Book> findAllBook(){
    	List<Book> books = bookRepository.findAll();
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NoRecordAvailableException("No Record Available in the DB");
        }
    }
    
    //fetch record by id
    public Book findBookByid(Integer id) {
    	 Optional<Book> opt = bookRepository.findById(id);
         
         if(opt.isPresent()) {
             return opt.get();
         } else {
             throw new IdNotFoundException("Book Id not Found In DB");
         }
    }
    
    //Update record 
    public Book updateBook(Book book ){
    	// 1️⃣ ID check logic
        if (book.getId() == null) {
            return null; 
        }

        // 2️⃣ Record existence check logic
        Optional<Book> opt = bookRepository.findById(book.getId());

        if (opt.isPresent()) {
            return bookRepository.save(book);
        } else {
            throw new IdNotFoundException("Id does not Exist in the Database");
        }
    }
    
    //delete all Record
    public int deleteAllBook() {
    	List<Book> books = bookRepository.findAll();
        if (!books.isEmpty()) {
            int count = books.size();
            bookRepository.deleteAll();
            return count; 
        } else {
            throw new NoRecordAvailableException("No Records Available to Delete");
        }
    }
    
    //delete record by id
    public Integer deleteReordById(Integer id) {
    	Optional<Book> opt = bookRepository.findById(id);
        if (opt.isPresent()) {
            bookRepository.deleteById(id);
            return id; 
        } else {
            throw new IdNotFoundException("Record with Id does Not Exist in the Database");
        }
    }
    
    //Fetch record by title
    public List<Book> findBookByTitle(String title) {
    	List<Book> books = bookRepository.findByTitle(title);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NoRecordAvailableException("No Books found with Title: " + title);
        }
    }
    
    //fetch record by title and author
    public List<Book> findByTitleAndAuthor(String title, String author){
    	List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NoRecordAvailableException("No Books found with Title: " + title + " and Author: " + author);
        }
    }
    
    //fetch record by price
    public List<Book> findByPriceGreaterThan(Double price) {
        List<Book> books = bookRepository.findByPriceGreaterThan(price);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NoRecordAvailableException("Books with price greater than " + price + " are not available");
        }
    }
    
    //fetch record in between range of price 
    public List<Book> findByPriceBetween(Double startPrice, Double endPrice) {
        List<Book> books = bookRepository.findByPriceBetween(startPrice, endPrice);
        
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NoRecordAvailableException("Books with price between " + startPrice + " and " + endPrice + " are not available");
        }
    }
    
    // Available Books
    public List<Book> getAvailableBooks() {
        List<Book> books = bookRepository.getBookByAvailability();
        if (!books.isEmpty()) return books;
        throw new NoRecordAvailableException("No available books found!");
    }
    
    // By Year
    public List<Book> getBooksByYear(Integer year) {
        List<Book> books = bookRepository.getBookByPublishedYear(year);
        if (!books.isEmpty()) return books;
        throw new NoRecordAvailableException("No books published in " + year);
    }

    // By Genre
    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = bookRepository.getBookByGenre(genre);
        if (!books.isEmpty()) return books;
        throw new NoRecordAvailableException("No books found for genre: " + genre);
    }
    
}