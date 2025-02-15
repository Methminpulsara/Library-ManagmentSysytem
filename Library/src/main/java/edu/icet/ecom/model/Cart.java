package edu.icet.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private String orderid;
    private String bookid;
    private String date;
    private String returndate;
   // private String  avelability;
}
