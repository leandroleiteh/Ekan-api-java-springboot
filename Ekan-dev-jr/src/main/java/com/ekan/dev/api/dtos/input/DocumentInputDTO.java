package com.ekan.dev.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DocumentInputDTO(

        @NotBlank(message = "O documento não pode ser nulo ou vazio")
        String typeDocument,

        @NotBlank(message = "A descrição não pode ser nulo ou vazio")
        String description,

        @NotEmpty(message = "Preencha um beneficiário")
        BeneficiaryInputDTO beneficiaryInputDTO

) {
}
