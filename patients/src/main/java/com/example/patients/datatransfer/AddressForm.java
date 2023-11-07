package com.example.patients.datatransfer;

import jakarta.annotation.Nonnull;

public record AddressForm(@Nonnull String logradouro, String numero, String complemento, @Nonnull String bairro,
		@Nonnull String cidade, @Nonnull String UF, @Nonnull String cep) {
}
