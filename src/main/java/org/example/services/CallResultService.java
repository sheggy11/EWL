package org.example.services;

import org.example.models.CallResult;

import java.util.List;

public interface CallResultService {
    List<CallResult> getAllCallResults();

    CallResult findById(Long id);
}
