package com.bookbouqet.controller;


import com.bookbouqet.book.BookMapper;
import com.bookbouqet.repository.BookRepository;
import com.bookbouqet.request.BookRequest;
import com.bookbouqet.response.BookResponse;
import com.bookbouqet.response.BorrowedBookResponse;
import com.bookbouqet.response.PageResponse;
import com.bookbouqet.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("books")
@Tag(name = "Book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final GenericResponseService responseBuilder;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @PostMapping("saveBook")
    public ResponseEntity<Integer> saveBook(@Valid @RequestBody BookRequest request,
                                            Authentication authentication) {
        return ResponseEntity.ok(bookService.saveBook(request, authentication));
    }


    @GetMapping("book/{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookMapper.toBookResponse(bookService.getBookById(bookId)));
    }


    @GetMapping("allBooks")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication user) {

        return ResponseEntity.ok(bookService.findAllBooks(page, size, user));
    }

    @GetMapping("byOwner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication user) {

        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, user));
    }

    @GetMapping("borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication user) {

        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, user));
    }

    @GetMapping("returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication user) {

        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, user));
    }


    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateShareable(
            @PathVariable("book-id") Integer bookId, Authentication authentication
    ){
        return  ResponseEntity.ok(bookService.updateShareableStatus(bookId ,authentication));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateArchived(
            @PathVariable("book-id") Integer bookId, Authentication authentication
    ){
        return  ResponseEntity.ok(bookService.updateArchivedStatus(bookId ,authentication));
    }

    @PostMapping("borrow/borrow/{book-id}")
    public ResponseEntity<Integer> borrowBook(
            @PathVariable("book-id") Integer bookId, Authentication authentication
    ){
        return  ResponseEntity.ok(bookService.borrowBook(bookId ,authentication));
    }



    @PostMapping("borrow/return/{book-id}")
    public ResponseEntity<Integer> returnBook(
            @PathVariable("book-id") Integer bookId, Authentication authentication
    ){
        return  ResponseEntity.ok(bookService.returnBook(bookId ,authentication));
    }

    @PatchMapping("borrow/approve/{book-id}")
    public ResponseEntity<Integer> approveReturn(
            @PathVariable("book-id") Integer bookId, Authentication authentication
    ){
        return  ResponseEntity.ok(bookService.approveReturn(bookId ,authentication));
    }

    @PostMapping("uploadCover/{book-id}")
    public ResponseEntity<?> uploadCoverPicture(
            @PathVariable("book-id") Integer bookId,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ){
        bookService.uploadCoverPicture(file ,authentication ,bookId);
        return  ResponseEntity.accepted().build();
    }

}
