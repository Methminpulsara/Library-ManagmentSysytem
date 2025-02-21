package edu.icet.ecom.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDetail_Entity {

    private int fineID;
    private int returnID ;
    private String UserID ;
    private Double fine;
    private String status;

}

