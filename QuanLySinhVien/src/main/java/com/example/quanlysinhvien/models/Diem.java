package com.example.quanlysinhvien.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diem")
public class Diem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBD;

    private float heso1;
    private float heso3;
    private float heso6;
    private float tongDiem;

    @ManyToOne
    @JoinColumn(name = "maGV")  // Assuming 'giangvien' is mapped with 'maGV'
    private GiangVien giangvien;

    @ManyToOne
    @JoinColumn(name = "maSV")  // Assuming 'sinhvien' is mapped with 'maSV'
    private SinhVien sinhvien;

    @ManyToOne
    @JoinColumn(name = "maMH")  // Assuming 'monhoc' is mapped with 'maMH'
    private MonHoc monhoc;

    @ManyToOne
    @JoinColumn(name = "maTC")  // Assuming 'tinchi' is mapped with 'maTC'
    private TinChi tinchi;

    @ManyToOne
    @JoinColumn(name = "maTL")  // Assuming 'theloai' is mapped with 'maTL'
    private TheLoai theloai;

    @ManyToOne
    @JoinColumn(name = "maHK")  // Assuming 'hocky' is mapped with 'maHK'
    private HocKy hocky;

    @ManyToOne
    @JoinColumn(name = "maNH")  // Assuming 'namhoc' is mapped with 'maNK'
    private NamHoc namhoc;

    @ManyToOne
    @JoinColumn(name = "maLop")  // Assuming 'lop' is mapped with 'maLop'
    private Lop lop;
}
