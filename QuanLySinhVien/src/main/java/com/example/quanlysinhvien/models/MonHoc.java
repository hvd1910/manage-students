package com.example.quanlysinhvien.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monhoc")
public class MonHoc {

    @Id
    private String maMH;

    private String tenMH;

    @ManyToOne
    @JoinColumn(name = "maTC")  // Assuming 'tinchi' is mapped with 'maTC'
    private TinChi tinchi;

    @ManyToOne
    @JoinColumn(name = "maTL")  // Assuming 'theloai' is mapped with 'maTL'
    private TheLoai theloai;

    @ManyToMany
    @JoinTable(
            name = "monhoc_giangvien",  // Tên bảng join
            joinColumns = @JoinColumn(name = "maMH"),  // Cột cho Monhoc
            inverseJoinColumns = @JoinColumn(name = "maGV")  // Cột cho Giangvien
    )
    private Set<GiangVien> giangviens;
}

