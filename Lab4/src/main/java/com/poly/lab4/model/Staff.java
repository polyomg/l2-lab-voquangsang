package com.poly.lab4.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    private String id; // treat as email

    @NotBlank(message = "Chưa nhập họ và tên")
    private String fullname;

    private String photo = "photo.jpg";

    @NotNull(message = "Chưa chọn giới tính")
    private Boolean gender;

    @NotNull(message = "Chưa nhập ngày sinh")
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    @NotNull(message = "Chưa nhập lương")
    @Min(value = 1000, message = "Lương tối thiểu phải là 1000")
    private Double salary;

    private Integer level = 0;
}

