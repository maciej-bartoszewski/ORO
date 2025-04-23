package com.zad3oro.service;

import com.zad3oro.entity.ConferenceRoom;
import com.zad3oro.entity.Participant;
import com.zad3oro.entity.Presentation;
import com.zad3oro.repository.PresentationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PresentationService {
    private PresentationRepository presentationRepository;

    public Presentation createPresentation(String title, Participant presenter, ConferenceRoom conferenceRoom, List<Participant> participants) {
        return presentationRepository.save(new Presentation().withPresenter(presenter).withConferenceRoom(conferenceRoom).withTitle(title).withParticipants(participants));
    }

    public List<Presentation> getAllPresentations() {
        return presentationRepository.findAll();
    }
}
