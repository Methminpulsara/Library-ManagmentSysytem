package edu.icet.ecom.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Admin {

    private String email;
    private String password;
}
