package edu.icet.ecom.model;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Borrow_detail {
    private String orderID;
    private String boolID;
    private Integer qty;
    private Double price;
    private String returndate;
}
