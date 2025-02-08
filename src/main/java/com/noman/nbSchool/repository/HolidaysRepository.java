package com.noman.nbSchool.repository;

import com.noman.nbSchool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday,Long> {
    List<Holiday> findByType(Holiday.Type type);
    List<Holiday> findAllByOrderByDayAsc();
}
