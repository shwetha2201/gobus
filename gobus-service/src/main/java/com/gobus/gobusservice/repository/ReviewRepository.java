package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByBus(Bus bus);
}
