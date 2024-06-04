package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    List<Bus> findAllByBusOwner(User busOwner);
}
