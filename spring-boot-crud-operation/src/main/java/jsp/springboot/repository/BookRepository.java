package jsp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.Book;

public interface BookRepository extends JpaRepository<Book , Integer>{
   
	//fetch all book based on title
	List<Book> findByTitle(String Title);
	
	//fetch all book based on Title and Author
	List<Book> findByTitleAndAuthor(String title,String Author);
	
	//fetch all book where price is greater than
	List<Book> findByPriceGreaterThan(Double price);
	
	// fetch all book where price is between a specified range
	List<Book> findByPriceBetween(Double startPrice, Double endPrice);
	
	@Query("Select b from Book b where b.availability= true")
	List<Book> getBookByAvailability();
	
	@Query("Select b from Book b where b.publishedYear = ?1")
	List<Book> getBookByPublishedYear(Integer year);
	
	@Query("Select b from Book b where b.genre = :genre")
	List<Book> getBookByGenre(String genre);
}
