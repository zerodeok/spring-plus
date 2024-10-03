package org.example.expert.domain.todo.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class TodoGetRequest {
    private String weather;
    private String startDate;
    private String endDate;
}
