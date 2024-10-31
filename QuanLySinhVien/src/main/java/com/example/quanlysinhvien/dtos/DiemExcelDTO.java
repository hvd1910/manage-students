package com.example.quanlysinhvien.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiemExcelDTO {
    private int maBD;
    private float heso1;
    private float heso3;
    private float heso6;
    private float tongDiem;
    private int maSV;

    private String hoTen;

    private String tenLop;
}
