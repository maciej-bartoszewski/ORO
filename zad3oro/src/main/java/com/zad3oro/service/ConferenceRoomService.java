package com.zad3oro.service;

import com.zad3oro.entity.ConferenceRoom;
import com.zad3oro.repository.ConferenceRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ConferenceRoomService {
    private ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoom createConferenceRoom(String name) {
        return conferenceRoomRepository.save(new ConferenceRoom().withName(name));
    }

    public Map<ConferenceRoom, Long> getPresentationsCountInConferenceRooms() {
        return conferenceRoomRepository.getPresentationsCountInConferenceRooms().stream()
                .collect(Collectors.toMap(
                        object -> (ConferenceRoom) object[0],
                        object -> (Long) object[1]
                ));
    }

}
