package edu.icet.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Borrow {

    private int  orderID;
    private String UserID;
    private String BookID;
    private String date;
    private String returndate;
    private String avelability;

}
