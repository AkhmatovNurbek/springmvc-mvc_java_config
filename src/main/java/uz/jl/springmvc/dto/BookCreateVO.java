package uz.jl.springmvc.dto;

import lombok.*;

/**
 * @author "Elmurodov Javohir"
 * @since 04/08/22/12:08 (Thursday)
 * springmvc/IntelliJ IDEA
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookCreateVO {
    private String title;
    private String author;
    private int page;
}
