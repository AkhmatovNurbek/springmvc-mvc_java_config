package uz.jl.springmvc.domains;

import lombok.*;
import uz.jl.springmvc.dto.BookCreateVO;

/**
 * @author "Elmurodov Javohir"
 * @since 04/08/22/12:07 (Thursday)
 * springmvc/IntelliJ IDEA
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Book {
    private Integer id;
    private String title;
    private String author;
    private int page;

    public static void update(Book book, BookCreateVO vo) {
        book.setTitle(vo.getTitle());
        book.setAuthor(vo.getAuthor());
        book.setPage(vo.getPage());
    }
}
