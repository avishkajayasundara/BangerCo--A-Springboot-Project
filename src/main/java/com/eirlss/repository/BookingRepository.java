package com.eirlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eirlss.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser_Userid(long userId);
}
