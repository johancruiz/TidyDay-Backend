package com.tidyday.TidyDay.Project.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     private String name;
     private String description;
     private String category;
     private Date date;
     private Time time;
     private String place;
     private String state;

     @ElementCollection
     private List<String> tags = new ArrayList<>();

     @JsonIgnore
     @OneToOne(mappedBy = "event", cascade = CascadeType.ALL,orphanRemoval = true)

    private Chat chat;

     @ManyToOne
     private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Issue> issues = new ArrayList<>();

    @ManyToMany
    private List<User>team = new ArrayList<>();

}
