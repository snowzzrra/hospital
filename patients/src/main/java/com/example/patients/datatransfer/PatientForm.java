package com.example.patients.datatransfer;

import jakarta.annotation.Nonnull;

public record PatientForm(@Nonnull String nome, @Nonnull String email, @Nonnull String telefone, @Nonnull String cpf,
		@Nonnull AddressForm endereco) {

}
