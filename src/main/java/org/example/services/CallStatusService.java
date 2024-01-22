package org.example.services;

import org.example.models.CallStatus;

import java.util.List;

public interface CallStatusService {

    List<CallStatus> getAllCallStatuses();

    CallStatus findById(Long id);

}
