package com.zad3oro.repository;

import com.zad3oro.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {

    @Query("select cr, count(p) from ConferenceRoom cr join cr.presentations p group by cr")
    List<Object[]> getPresentationsCountInConferenceRooms();
}
