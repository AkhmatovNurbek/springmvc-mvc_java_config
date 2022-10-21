package uz.tomcat.springmvc.domains;

import lombok.*;
import uz.tomcat.springmvc.dto.BookCreateVO;


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
