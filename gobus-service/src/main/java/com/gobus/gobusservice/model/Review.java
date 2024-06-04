package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private int reviewId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bus_id", referencedColumnName = "bus_id")
    private Bus bus;
    @Column(name = "review_comment")
    private String comment;

    @Column(name = "num_stars")
    private int numberOfStars;
}
