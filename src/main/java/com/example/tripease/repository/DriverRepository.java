package com.example.tripease.repository;

import com.example.tripease.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository <Driver,Integer>{
    List<Driver> findByAge(int age);

    @Query(value = " select * from driver where cab_id = :cabId",nativeQuery = true)
    Driver getDriverByCabId(@Param("cabId") int cabId);
}
