/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approveReturn } from '../fn/book/approve-return';
import { ApproveReturn$Params } from '../fn/book/approve-return';
import { BookResponse } from '../models/book-response';
import { borrowBook } from '../fn/book/borrow-book';
import { BorrowBook$Params } from '../fn/book/borrow-book';
import { findAllBooks } from '../fn/book/find-all-books';
import { FindAllBooks$Params } from '../fn/book/find-all-books';
import { findAllBooksByOwner } from '../fn/book/find-all-books-by-owner';
import { FindAllBooksByOwner$Params } from '../fn/book/find-all-books-by-owner';
import { findAllBorrowedBooks } from '../fn/book/find-all-borrowed-books';
import { FindAllBorrowedBooks$Params } from '../fn/book/find-all-borrowed-books';
import { findAllReturnedBooks } from '../fn/book/find-all-returned-books';
import { FindAllReturnedBooks$Params } from '../fn/book/find-all-returned-books';
import { getBookById } from '../fn/book/get-book-by-id';
import { GetBookById$Params } from '../fn/book/get-book-by-id';
import { PageResponseBookResponse } from '../models/page-response-book-response';
import { PageResponseBorrowedBookResponse } from '../models/page-response-borrowed-book-response';
import { returnBook } from '../fn/book/return-book';
import { ReturnBook$Params } from '../fn/book/return-book';
import { saveBook } from '../fn/book/save-book';
import { SaveBook$Params } from '../fn/book/save-book';
import { updateArchived } from '../fn/book/update-archived';
import { UpdateArchived$Params } from '../fn/book/update-archived';
import { updateShareable } from '../fn/book/update-shareable';
import { UpdateShareable$Params } from '../fn/book/update-shareable';
import { uploadCoverPicture } from '../fn/book/upload-cover-picture';
import { UploadCoverPicture$Params } from '../fn/book/upload-cover-picture';

@Injectable({ providedIn: 'root' })
export class BookService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `uploadCoverPicture()` */
  static readonly UploadCoverPicturePath = '/books/uploadCover/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadCoverPicture()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadCoverPicture$Response(params: UploadCoverPicture$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadCoverPicture(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadCoverPicture$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadCoverPicture(params: UploadCoverPicture$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadCoverPicture$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `saveBook()` */
  static readonly SaveBookPath = '/books/saveBook';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveBook()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBook$Response(params: SaveBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveBook$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBook(params: SaveBook$Params, context?: HttpContext): Observable<number> {
    return this.saveBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnBook()` */
  static readonly ReturnBookPath = '/books/borrow/return/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBook$Response(params: ReturnBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBook(params: ReturnBook$Params, context?: HttpContext): Observable<number> {
    return this.returnBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `borrowBook()` */
  static readonly BorrowBookPath = '/books/borrow/borrow/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook$Response(params: BorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook(params: BorrowBook$Params, context?: HttpContext): Observable<number> {
    return this.borrowBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateShareable()` */
  static readonly UpdateShareablePath = '/books/shareable/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateShareable()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareable$Response(params: UpdateShareable$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateShareable(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateShareable$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareable(params: UpdateShareable$Params, context?: HttpContext): Observable<number> {
    return this.updateShareable$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approveReturn()` */
  static readonly ApproveReturnPath = '/books/borrow/approve/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveReturn()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturn$Response(params: ApproveReturn$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approveReturn(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveReturn$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturn(params: ApproveReturn$Params, context?: HttpContext): Observable<number> {
    return this.approveReturn$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateArchived()` */
  static readonly UpdateArchivedPath = '/books/archived/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateArchived()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchived$Response(params: UpdateArchived$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateArchived(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateArchived$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchived(params: UpdateArchived$Params, context?: HttpContext): Observable<number> {
    return this.updateArchived$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findAllReturnedBooks()` */
  static readonly FindAllReturnedBooksPath = '/books/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReturnedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBooks$Response(params?: FindAllReturnedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBookResponse>> {
    return findAllReturnedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReturnedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBooks(params?: FindAllReturnedBooks$Params, context?: HttpContext): Observable<PageResponseBorrowedBookResponse> {
    return this.findAllReturnedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBookResponse>): PageResponseBorrowedBookResponse => r.body)
    );
  }

  /** Path part for operation `findAllBooksByOwner()` */
  static readonly FindAllBooksByOwnerPath = '/books/byOwner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBooksByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooksByOwner$Response(params?: FindAllBooksByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return findAllBooksByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBooksByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooksByOwner(params?: FindAllBooksByOwner$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.findAllBooksByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

  /** Path part for operation `findAllBorrowedBooks()` */
  static readonly FindAllBorrowedBooksPath = '/books/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBorrowedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks$Response(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBookResponse>> {
    return findAllBorrowedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBorrowedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<PageResponseBorrowedBookResponse> {
    return this.findAllBorrowedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBookResponse>): PageResponseBorrowedBookResponse => r.body)
    );
  }

  /** Path part for operation `getBookById()` */
  static readonly GetBookByIdPath = '/books/book/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getBookById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBookById$Response(params: GetBookById$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return getBookById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getBookById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBookById(params: GetBookById$Params, context?: HttpContext): Observable<BookResponse> {
    return this.getBookById$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `findAllBooks()` */
  static readonly FindAllBooksPath = '/books/allBooks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooks$Response(params?: FindAllBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return findAllBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooks(params?: FindAllBooks$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.findAllBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

}
