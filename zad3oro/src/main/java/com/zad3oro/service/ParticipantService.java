package com.zad3oro.service;

import com.zad3oro.entity.Participant;
import com.zad3oro.entity.Role;
import com.zad3oro.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParticipantService {
    private ParticipantRepository participantRepository;

    public Participant createParticipant(String name, String country, Role role) {
        return participantRepository.save(new Participant().withName(name).withCountry(country).withRole(role));
    }

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public List<Participant> getParticipantsByRole(Role role) {
        return participantRepository.findByRole(role);
    }

    public List<Participant> getParticipantsByCountry(String country) {
        return participantRepository.findByCountry(country);
    }

    public Object[] getParticipantWithMostPresentations() {
        return participantRepository.findParticipantsWithMostPresentations().getFirst();
    }
}
