package com.demo.test.datatransfer;

import com.demo.test.model.Dias;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConsultationDateForm(
		@NotNull(message = "ANO INVALIDO! Sem valores nulos") @Min(value = 2023, message = "ANO INVALIDO!: Deve ser maior ou igual a 2023") @Max(value = 2025, message = "ANO INVALIDO!: Deve ser menor ou igual a 2025") int ano,
		@NotNull(message = "MES INVALIDO! Sem valores nulos") @Min(value = 1, message = "MES INVALIDO! Deve ser maior ou igual a 1") @Max(value = 12, message = "MES INVALIDO! Deve ser menor ou igual a 12") int mes,
		@NotNull(message = "DIA INVALIDO! Sem valores nulos") int dia,
		@NotNull(message = "DIA SEMANAL INVALIDO! Sem valores nulos") Dias dia_da_semana,
		@NotNull(message = "HORA INVALIDA! Sem valores nulos") @Min(value = 7, message = "HORA INVALIDA! Deve ser maior ou igual a 7") @Max(value = 19, message = "HORA INVALIDA! Deve ser menor ou igual a 19") int hora,
		@NotNull(message = "MINUTO INVALIDO! Sem valores nulos") @Min(value = 1, message = "MINUTO INVALIDO! Deve ser maior ou igual a 1") @Max(value = 59, message = "MINUTO INVALIDO! Deve ser menor ou igual a 59") int minuto) {
}
