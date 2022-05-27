package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "field")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FieldModel {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "field")
    public String field;

    @Column(name = "paths")
    public String paths;
}
