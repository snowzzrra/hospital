package com.example.doctor.datatransfer;

import com.example.doctor.models.Expertise;

import jakarta.annotation.Nonnull;

public record DoctorForm(@Nonnull String nome, @Nonnull String email, @Nonnull String telefone, @Nonnull String crm,
		@Nonnull AdressForm endereco, @Nonnull Expertise especialidade) {
}
