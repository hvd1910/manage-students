package com.example.quanlysinhvien.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xeploai")
public class XepLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maXL;

    private float canDuoi;
    private float canTren;
    private String tenXL;
}