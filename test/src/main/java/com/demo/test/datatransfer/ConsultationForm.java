package com.demo.test.datatransfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConsultationForm(Long medico,
		@Min(value = 1, message = "ID INVALIDA! Precisa ser maior que 0.") @NotNull Long paciente,
		@Valid @NotNull ConsultationDateForm dataConsulta

) {

}
