package com.example.assessment.finserv.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolutionRequest {
    private String finalQuery;

    public SolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

}
