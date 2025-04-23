package com.zad3oro.repository;

import com.zad3oro.entity.Participant;
import com.zad3oro.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByRole(Role role);

    List<Participant> findByCountry(String country);

    @Query("select p from Participant p where size(p.presentations) = (select max(size(presentations)) from Participant)")
    List<Object[]> findParticipantsWithMostPresentations();
}
