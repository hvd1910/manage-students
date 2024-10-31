package com.example.quanlysinhvien.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lop")
public class Lop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLop;

    private String tenLop;

    @ManyToOne
    @JoinColumn(name = "maKH")  // This is the foreign key column in the 'Lop' table
    private Khoa khoa;
}
