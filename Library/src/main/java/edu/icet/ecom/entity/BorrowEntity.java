package edu.icet.ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BorrowEntity {

    private int orderID;
    private String UserID;
    private String BookID;
    private String date;
    private String returndate;
    private String avelability;

}
