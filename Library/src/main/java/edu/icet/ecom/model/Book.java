package edu.icet.ecom.model;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    private String bookID;
    private String ISBN ;
    private String titel;
    private String author;
    private String avelebility;

}
