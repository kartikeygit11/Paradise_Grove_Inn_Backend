package com.pradise_grove_inn.Paradise_grove_inn.repository;
import com.pradise_grove_inn.Paradise_grove_inn.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
