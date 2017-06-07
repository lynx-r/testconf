/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Schedule;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByRoomId(Integer roomId);

    @Query("select schedule from Schedule schedule " +
            "left join schedule.presentation.presenters presenters " +
            "where presenters.id = :presenterId")
    List<Schedule> findAllByPresenterId(@Param("presenterId") Integer presenterId);

    void deleteByPresentationId(Integer presentationId);

    default List<Schedule> complete(String query, int maxResults) {
        Schedule probe = new Schedule();

        ExampleMatcher matcher = ExampleMatcher.matching() //
        ;

        Page<Schedule> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }

}