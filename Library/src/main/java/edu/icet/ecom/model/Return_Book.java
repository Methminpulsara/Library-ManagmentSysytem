package edu.icet.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Return_Book {

    private Integer returnID;
    private Integer recordID;
    private String userID;
    private String bookID;
    private String borrowDate;
    private String returnDate;
    private String actualDate;
    private Double fine;
    private String status;


}
