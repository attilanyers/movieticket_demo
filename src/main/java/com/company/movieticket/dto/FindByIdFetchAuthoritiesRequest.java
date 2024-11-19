package com.company.movieticket.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class FindByIdFetchAuthoritiesRequest {

    private UUID id;
}
