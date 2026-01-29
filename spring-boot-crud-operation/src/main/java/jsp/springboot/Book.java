package jsp.springboot;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String title;
     private String author;
     private Double price;
     private String publishedYear;
     private Boolean availability;
     private String genre;

	 public Integer getId() {
		 return id;
	 }
	 public void setId(Integer id) {
		 this.id = id;
	 }
	 public String getTitle() {
		 return title;
	 }
	 public void setTitle(String title) {
		 this.title = title;
	 }
	 public String getAuthor() {
		 return author;
	 }
	 public void setAuthor(String author) {
		 this.author = author;
	 }
	 public String getPublishedYear() {
		 return publishedYear;
	 }
	 public void setPublishedYear(String publishedYear) {
		 this.publishedYear = publishedYear;
	 }
	 public Double getPrice() {
		 return price;
	 }
	 public void setPrice(Double price) {
		 this.price = price;
	 }
	 public String getGenre() {
		 return genre;
	 }
	 public void setGenre(String genre) {
		 this.genre = genre;
	 }
	 public Boolean getAvailability() {
		 return availability;
	 }
	 public void setAvailability(Boolean availability) {
		 this.availability = availability;
	 }
     
}
