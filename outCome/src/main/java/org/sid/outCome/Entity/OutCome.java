package org.sid.outCome.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class OutCome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    @NotNull(message = "Le montant ne doit pas être null")
    @Column(name = "MONTANT")
    private double montant;
    private Date date;
    private int categoryId;
   @Transient
   @JsonProperty("categories")
    private  List<Category> categories=new ArrayList<>();

}