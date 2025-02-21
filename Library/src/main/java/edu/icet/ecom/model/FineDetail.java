package edu.icet.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDetail {

    private int fineID;
    private int returnID ;
    private String UserID ;
    private Double fine;
    private String status;

}
