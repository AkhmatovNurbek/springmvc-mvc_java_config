package uz.jl.springmvc.controllers;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jl.springmvc.domains.Book;
import uz.jl.springmvc.dto.BookCreateVO;
import uz.jl.springmvc.dto.BookPageVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


@Controller
public class HomeController {
    private final AtomicInteger counter = new AtomicInteger(1);
    private final List<Book> books = new ArrayList<>();


    {
        Faker faker = new Faker();
        com.github.javafaker.Book book = faker.book();
        for (int i = 0; i < 115; i++) {
            books.add(Book.builder()
                    .id(counter.getAndIncrement())
                    .title(book.title())
                    .author(book.author())
                    .page(new Random().nextInt(300, 500))
                    .build());
        }
    }

    /*@RequestParam(name = "size") Optional<Long> sizeOptional,
      @RequestParam(name = "page") Optional<Long> pageOptional,
     * @return
     * @RequestParam(name = "size", defaultValue = "10") long size,
     * @RequestParam(name = "page", defaultValue = "0") long page,
     */

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String homePage(@RequestParam(name = "size", defaultValue = "10") long size,
                           @RequestParam(name = "page", defaultValue = "0") long page,
                           Model model) {
        List<Book> paginatedBooks = books.stream()
                .skip(page * size)
                .limit(size)
                .toList();
        long pagesCount = books.size() / size;
        BookPageVO bookPage = BookPageVO.builder()
                .next(page + 1)
                .previous(page - 1)
                .hasNext(page != pagesCount)
                .hasPrevious(page != 0)
                .currentPage(page)
                .books(paginatedBooks)
                .totalPages(pagesCount)
                .build();
        model.addAttribute("page", bookPage);
        return "index";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addBookPage() {
        return "book/add";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addBook(@ModelAttribute BookCreateVO vo) {
        Book book = Book.builder()
                .id(counter.getAndIncrement())
                .title(vo.getTitle())
                .author(vo.getAuthor())
                .page(vo.getPage())
                .build();
        books.add(book);
        return "redirect:/";
    }

    @RequestMapping(value = {"/delete/{bookId}"}, method = RequestMethod.GET)
    public String deleteBookPage(@PathVariable("bookId") Integer id, Model model) {
        Optional<Book> optionalBook = books.stream().filter(book -> book.getId().equals(id)).findFirst();
        if (optionalBook.isEmpty()) {
            model.addAttribute("error", "Book not found");
            return "book/delete";
        }
        model.addAttribute("book", optionalBook.get());
        return "book/delete";
    }

    @RequestMapping(value = {"/delete/{bookId}"}, method = RequestMethod.POST)
    public String deleteBook(@PathVariable("bookId") Integer id) {
        books.removeIf(book -> book.getId().equals(id));
        return "redirect:/";
    }

    @RequestMapping(value = {"/update/{bookId}"}, method = RequestMethod.GET)
    public String updateBookPage(@PathVariable("bookId") Integer id, Model model) {
        Optional<Book> optionalBook = findBook.apply(books, id);
        if (optionalBook.isEmpty()) {
            model.addAttribute("error", "Book not found");
            return "book/update";
        }
        model.addAttribute("book", optionalBook.get());
        return "book/update";
    }

    @RequestMapping(value = {"/update/{bookId}"}, method = RequestMethod.POST)
    public String updateBook(@ModelAttribute BookCreateVO vo, @PathVariable Integer bookId, Model model) {
        findBook.apply(books, bookId)
                .ifPresent(book -> updateBook.accept(book, vo));
        return "redirect:/";
    }

    private final BiConsumer<Book, BookCreateVO> updateBook = Book::update;

    private final BiFunction<List<Book>, Integer, Optional<Book>> findBook = (books, id) ->
            (books.stream().filter(b -> b.getId().equals(id)).findFirst());


}
