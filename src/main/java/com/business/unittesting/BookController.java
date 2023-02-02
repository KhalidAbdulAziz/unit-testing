package com.business.unittesting;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/book")
@AllArgsConstructor
public class BookController {

    private BookRepo bookRepo;

    @GetMapping
    public List<Book> getAllBooks() {
       return bookRepo.findAll();
    }

    @GetMapping(value = "/bookId")
    public Book getBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookRepo.findById(bookId).get();
    }

    @PostMapping()
    public Book createBook(@RequestBody @Valid Book book) {
        return bookRepo.save(book);
    }

    @PutMapping(value = "/bookId")
    public Book updateBookById(@RequestBody @Valid Book book) {

        Optional<Book> bookRecord = bookRepo.findById(book.getBookId());
        if (!bookRecord.isPresent()) {
            throw new RuntimeException("Book with ID " + book.getBookId() + " does not exist");
        }
        Book currentBookRecord = bookRecord.get();
        currentBookRecord.setName(book.getName());
        currentBookRecord.setSummary(book.getSummary());
        currentBookRecord.setRating(book.getRating());

        return bookRepo.save(currentBookRecord);
    }



}
