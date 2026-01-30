package jsp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    //Insert Single Record
    public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
        Book b = bookRepository.save(book);
        ResponseStructure<Book> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Book Record Saved Successfully");
        response.setData(b);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    //Insert Multiple Record
    public ResponseEntity<ResponseStructure<List<Book>>> saveMultipleBooks(List<Book> books) {
        List<Book> b = bookRepository.saveAll(books);
        ResponseStructure<List<Book>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Multiple Book Records Saved Successfully");
        response.setData(b);
        return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
    }

    //Fetching Multiple Record 
    public ResponseEntity<ResponseStructure<List<Book>>> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()) {
        	ResponseStructure<List<Book>> response = new ResponseStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("All Books Fetched Successfully");
            response.setData(books);
            return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
        }else {
        	throw new NoRecordAvailableException("No Record Available in the DB");
        }
    }
    
    //Fetch Record By id
    public ResponseEntity<ResponseStructure<Book>> findBookById(Integer id) {
        Optional<Book> opt = bookRepository.findById(id);
        ResponseStructure<Book> response = new ResponseStructure<>();
        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Book Found");
            response.setData(opt.get());
            return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Book Id not Found In DB");
        }
    }

 // UPDATED: Using exact Company logic for Book Update
    public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
        ResponseStructure<Book> response = new ResponseStructure<>();

        // 1️⃣ Check if ID is passed
        if (book.getId() == null) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Id must be provided to update the record");
            response.setData(null);
            return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.BAD_REQUEST);
        }

        // 2️⃣ Check if record exists
        Optional<Book> opt = bookRepository.findById(book.getId());

        if (opt.isPresent()) {
            Book updatedBook = bookRepository.save(book);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Record updated successfully");
            response.setData(updatedBook);
            return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Id does not Exist in the Database");
        }
    }
    
    //Delete All Record 
    public ResponseEntity<ResponseStructure<String>> deleteAllBooks() {
        ResponseStructure<String> response = new ResponseStructure<>();

        List<Book> books = bookRepository.findAll();

        if (!books.isEmpty()) {
            bookRepository.deleteAll();

            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("All Records Deleted Successfully");
            response.setData("Total Deleted Records: " + books.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Records Available to Delete");
        }
    }

        
        //Delete Record By Id
        public ResponseEntity<ResponseStructure<String>> deleteBook(Integer id) {
        ResponseStructure<String> response = new ResponseStructure<>();

        Optional<Book> opt = bookRepository.findById(id);

        if (opt.isPresent()) {
            bookRepository.deleteById(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Record deleted successfully");
            response.setData("Deleted ID: " + id);
            return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);

        } else {
            throw new IdNotFoundException("Record with Id does Not Exist in the Database");
        }
    }
}