package edu.icet.ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Return_Book_Entity {

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
