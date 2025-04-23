package com.zad3oro;

import com.zad3oro.entity.ConferenceRoom;
import com.zad3oro.entity.Participant;
import com.zad3oro.entity.Presentation;
import com.zad3oro.entity.Role;
import com.zad3oro.service.ConferenceRoomService;
import com.zad3oro.service.ParticipantService;
import com.zad3oro.service.PresentationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class Zad3oroApplicationTests {
    @Autowired
    ParticipantService participantService;
    @Autowired
    ConferenceRoomService conferenceRoomService;
    @Autowired
    PresentationService presentationService;

    static Participant presenter1, presenter2;
    static Participant participant1, participant2, participant3, participant4, participant5;
    static ConferenceRoom conferenceRoom1, conferenceRoom2;
    static Presentation presentation1, presentation2, presentation3;

    @BeforeAll
    static void setUp(@Autowired ParticipantService participantService,
                      @Autowired ConferenceRoomService conferenceRoomService,
                      @Autowired PresentationService presentationService) {

        presenter1 = participantService.createParticipant("Jan Kowalski", "Polska", Role.ORGANIZER);
        presenter2 = participantService.createParticipant("Piotr Nowak", "Polska", Role.ORGANIZER);

        participant1 = participantService.createParticipant("Michał Woźniak", "Polska", Role.STUDENT);
        participant2 = participantService.createParticipant("Anna Jantar", "Polska", Role.STUDENT);
        participant3 = participantService.createParticipant("John Smith", "USA", Role.DOCTOR);
        participant4 = participantService.createParticipant("Maria Garcia", "Hiszpania", Role.DOCTOR);
        participant5 = participantService.createParticipant("Hans Müller", "Niemcy", Role.STUDENT);

        conferenceRoom1 = conferenceRoomService.createConferenceRoom("Sala 1");
        conferenceRoom2 = conferenceRoomService.createConferenceRoom("Sala 2");

        presentation1 = presentationService.createPresentation(
                "Prezentacja 1", presenter1, conferenceRoom1, List.of(participant1, participant2));
        presentation2 = presentationService.createPresentation(
                "Prezentacja 2", presenter2, conferenceRoom2, List.of(participant1, participant3, participant4, participant5));
        presentation3 = presentationService.createPresentation(
                "Prezentacja 3", presenter1, conferenceRoom1, List.of(participant1, participant2, participant3, participant4, participant5));
    }

    @Test
    void testListOfAllParticipants() {
        List<Participant> participants = participantService.getParticipants();
        assertThat(participants.size()).isEqualTo(7);
        log.info("Participants:");
        participants.forEach(participant -> log.info(participant.getName(), participant.getCountry(), participant.getRole()));
    }

    @Test
    void testListOfAllParticipantsByRole() {
        List<Participant> doctors = participantService.getParticipantsByRole(Role.DOCTOR);
        assertThat(doctors.size()).isEqualTo(2);
        log.info("Doctors:");
        doctors.forEach(doctor -> log.info(doctor.getName(), doctor.getCountry(), doctor.getRole()));

        List<Participant> students = participantService.getParticipantsByRole(Role.STUDENT);
        assertThat(students.size()).isEqualTo(3);
        log.info("Students:");
        students.forEach(student -> log.info(student.getName(), student.getCountry(), student.getRole()));

        List<Participant> organizers = participantService.getParticipantsByRole(Role.ORGANIZER);
        assertThat(organizers.size()).isEqualTo(2);
        log.info("Organizers:");
        organizers.forEach(organizer -> log.info(organizer.getName(), organizer.getCountry(), organizer.getRole()));
    }

    @Test
    void testListOfAllParticipantsByCountry() {
        List<Participant> polandParticipants = participantService.getParticipantsByCountry("Polska");
        assertThat(polandParticipants.size()).isEqualTo(4);
        log.info("Poland participants:");
        polandParticipants.forEach(polandParticipant -> log.info(polandParticipant.getName(), polandParticipant.getCountry(), polandParticipant.getRole()));

        List<Participant> usaParticipants = participantService.getParticipantsByCountry("USA");
        assertThat(usaParticipants.size()).isEqualTo(1);
        log.info("USA participants:");
        usaParticipants.forEach(usaParticipant -> log.info(usaParticipant.getName(), usaParticipant.getCountry(), usaParticipant.getRole()));

        List<Participant> spainParticipants = participantService.getParticipantsByCountry("Hiszpania");
        assertThat(spainParticipants.size()).isEqualTo(1);
        log.info("Spain participants:");
        spainParticipants.forEach(spainParticipant -> log.info(spainParticipant.getName(), spainParticipant.getCountry(), spainParticipant.getRole()));

        List<Participant> germanyParticipants = participantService.getParticipantsByCountry("Niemcy");
        assertThat(germanyParticipants.size()).isEqualTo(1);
        log.info("Germany participants:");
        germanyParticipants.forEach(germanyParticipant -> log.info(germanyParticipant.getName(), germanyParticipant.getCountry(), germanyParticipant.getRole()));
    }

    @Test
    void testListOfAllPresentationsTitles() {
        List<Presentation> presentationsTitles = presentationService.getAllPresentations();
        assertThat(presentationsTitles.size()).isEqualTo(3);
        log.info("Presentations titles:");
        presentationsTitles.forEach(presentation -> log.info(presentation.getTitle()));
    }

    @Test
    void testParticipantWithMostPresentations() {
        Participant participantWithMostPresentations = (Participant) participantService.getParticipantWithMostPresentations()[0];
        assertThat(participantWithMostPresentations.getName()).isEqualTo("Michał Woźniak");
        log.info("Participant with most presentations: {}", participantWithMostPresentations.getName());
    }

    @Test
    void testListOfAllPresentationsInConferenceRooms(){
        Map<ConferenceRoom, Long> presentationsCountInConferenceRoom = conferenceRoomService.getPresentationsCountInConferenceRooms();
        presentationsCountInConferenceRoom.forEach((conferenceRoom, presentationsCount) -> log.info("{}, number of presentations: {}", conferenceRoom.getName(), presentationsCount));
    }
}
