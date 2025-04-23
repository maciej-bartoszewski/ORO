package com.zad1oro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "motorcycles")
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "motorcycles_parts",
            joinColumns = @JoinColumn(name = "motorcycle_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private List<Part> parts;
}
