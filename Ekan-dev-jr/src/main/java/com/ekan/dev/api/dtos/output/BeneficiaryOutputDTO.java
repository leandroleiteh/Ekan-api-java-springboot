package com.ekan.dev.api.dtos.output;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record BeneficiaryOutputDTO(

        UUID id,

        String name,

        String telNumber,

        Date birthDate,

        LocalDateTime inclusionDate,

        LocalDateTime updateDate,

        List<DocumentOutputDTO> documentOutputDTO

) {
}
