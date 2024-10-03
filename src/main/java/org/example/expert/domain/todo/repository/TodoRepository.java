package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.modifiedAt BETWEEN :startDate and :endDate and (t.weather =:weather) ")
    Page<Todo> findByWeatherAndStartDateBetweenEndDate(@Param("weather") String weather,
                                                       @Param("startDate")LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate,
                                                       Pageable pageable);

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    @Query("SELECT t From Todo t where(t.weather =: weather) ")
    Page<Todo> findByAllWeather(@Param("weather") String weather,Pageable pageable);
}
