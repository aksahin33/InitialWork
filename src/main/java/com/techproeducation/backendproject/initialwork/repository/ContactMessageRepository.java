package com.techproeducation.backendproject.initialwork.repository;

import com.techproeducation.backendproject.initialwork.domain.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    List<ContactMessage> findByEmail(String email);


    @Query("SELECT c FROM ContactMessage c WHERE LOWER(c.subject) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<ContactMessage> findBySearchString(@Param("searchString") String searchString);

    @Query("SELECT c FROM ContactMessage c WHERE c.creationDateTime >= :startDate AND c.creationDateTime <= :endDate")
    List<ContactMessage> findMessagesBetweenDates(@Param("startDate") LocalDateTime date1, @Param("endDate") LocalDateTime date2);






}
