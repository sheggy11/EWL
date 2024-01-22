package org.example.repositories;


import org.example.models.UrgencyNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrgencyNoteRepository extends JpaRepository<UrgencyNote, Long> {


}
