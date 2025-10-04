package com.poly.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    private String id;
    private String fullname;
    @Builder.Default
    private String photo = "quangsang.jpg";
    @Builder.Default
    private Boolean gender = true;
    @Builder.Default
    private Date birthday = new Date();
    @Builder.Default
    private Double salary = 12345.6789;
    @Builder.Default
    private Integer level = 0;
}
