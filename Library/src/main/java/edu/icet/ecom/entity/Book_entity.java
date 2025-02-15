package edu.icet.ecom.entity;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Book_entity {
    private String bookID;
    private String ISBN ;
    private String titel;
    private String author;
    private String avelebility;

}
