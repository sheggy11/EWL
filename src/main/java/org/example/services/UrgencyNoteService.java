package org.example.services;

import org.example.models.UrgencyNote;

import java.util.List;

public interface UrgencyNoteService {

    List<UrgencyNote> getAllUrgencyNotes();

    UrgencyNote findById(Long id);
}
