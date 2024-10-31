package com.example.quanlysinhvien.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "giangvien")
public class GiangVien {

    @Id
    private String maGV;

    private String tenGV;

    private String diaChi;

    private int sdt;

    private String email;

}
