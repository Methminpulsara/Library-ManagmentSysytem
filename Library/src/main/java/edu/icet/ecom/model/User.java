package edu.icet.ecom.model;

import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString

public class User {
    private String userid ;
    private String name ;
    private String  contactinformation ;
    private String membershipdate ;
    private Double fine;


}
