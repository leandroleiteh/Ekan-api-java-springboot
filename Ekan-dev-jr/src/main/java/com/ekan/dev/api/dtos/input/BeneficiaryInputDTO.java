package com.ekan.dev.api.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record BeneficiaryInputDTO(

        @NotBlank(message = "O nome não pode ser nulo ou vazio")
        String name,

        @NotBlank(message = "O telefone não pode ser nulo ou vazio")
        String telNumber,

        @NotNull(message = "Preecnha sua data de nascimento")
        Date birthDate,

        @NotEmpty(message = "Preencha com pelo menos 1 documento")
        List<DocumentoInputDTO> documentoInputDTOS

) {
}
