package com.bookbouqet.book.feedback;


import com.bookbouqet.book.book.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
@Slf4j
public class FeedBackController {


    private final FeedBackService service;



    @PostMapping("save")
    public ResponseEntity<Integer> saveFeedBack(@Valid @RequestBody FeedBackRequest request,
                                                Authentication authentication){
        return ResponseEntity.ok(service.save(request ,authentication));
    }


    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedBacksByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(value = "page" , defaultValue = "0" , required = false) int size ,
            Authentication authentication){

        return  ResponseEntity.ok(service.findAllFeedBacksByBookId(bookId ,page ,size ,authentication));
    }
}
