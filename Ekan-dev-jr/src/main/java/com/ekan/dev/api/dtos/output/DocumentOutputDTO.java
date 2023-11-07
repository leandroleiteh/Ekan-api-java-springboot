package com.ekan.dev.api.dtos.output;

import com.ekan.dev.api.dtos.input.BeneficiaryInputDTO;

import java.time.LocalDateTime;
import java.util.UUID;


public record DocumentOutputDTO(

        UUID id,

        String typeDocument,

        String description,

        LocalDateTime inclusionDate,

        LocalDateTime updateDate

) {
}
