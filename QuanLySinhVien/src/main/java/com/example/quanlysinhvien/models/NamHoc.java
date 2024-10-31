package com.example.quanlysinhvien.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "namhoc")
public class NamHoc {

    @Id
    private String maNH;

    private String tenNH;
}
