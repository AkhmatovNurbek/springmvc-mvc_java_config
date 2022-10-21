package uz.tomcat.springmvc.dto;

import lombok.*;


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
