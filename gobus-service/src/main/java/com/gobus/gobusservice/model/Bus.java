package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bus_id")
    private int busId;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User busOwner;
    @Column(name = "bus_name")
    private String busName;
    @Column(name = "bus_type")
    private BusType busType;
    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "num_seats")
    private int numberOfSeats;

    public enum BusType {
        AC_SLEEPER, NONAC_SLEEPER, AC_SEATER, NONAC_SEATER;
    }
}
