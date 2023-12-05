package com.example.sysDevLtd.entity.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sl;
    private String details;
    private String remark;

}
