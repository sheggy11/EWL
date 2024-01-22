package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.models.UrgencyNote;
import org.example.repositories.UrgencyNoteRepository;
import org.example.services.UrgencyNoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Log4j2
@Service
public class UrgencyNoteServiceImpl implements UrgencyNoteService {

    private final UrgencyNoteRepository urgencyNoteRepository;

    @Override
    public List<UrgencyNote> getAllUrgencyNotes() {
        return urgencyNoteRepository.findAll();
    }

    public UrgencyNote findById(Long id) {
        return urgencyNoteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UrgencyNote с id " + id + " не найдена"));
    }
}
