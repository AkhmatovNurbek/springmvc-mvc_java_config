package uz.jl.springmvc.dto;

import lombok.*;
import uz.jl.springmvc.domains.Book;

import java.util.List;

/**
 * @author "Elmurodov Javohir"
 * @since 05/08/22/10:51 (Friday)
 * springmvc/IntelliJ IDEA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookPageVO {
    private long currentPage;
    private long totalPages;
    private List<Book> books;
    private boolean hasPrevious;
    private boolean hasNext;
    private long previous;
    private long next;
}
