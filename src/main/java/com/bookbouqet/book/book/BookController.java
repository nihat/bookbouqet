package com.bookbouqet.book.book;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@Tag(name = "Book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    public ResponseEntity<Integer> saveBook(@Valid @RequestBody BookRequest request,
                                            Authentication authentication) {

        return ResponseEntity.ok(bookService.saveBook(request, authentication));
    }
}
