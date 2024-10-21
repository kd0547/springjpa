package org.example.springweb1.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.springweb1.controller.BookForm;

@Entity
@Getter @Setter
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;

    public static Book CreateBook(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        return book;
    }

}
