package com.example.quanlysinhvien.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SinhVien {

    @Id
    private int maSV;

    private String tenSV;
    private String diaChi;
    private int sdt;
    private String email;

    @ManyToOne
    @JoinColumn(name = "maLop")  // Foreign key column for the Lop entity
    private Lop lop;
}
