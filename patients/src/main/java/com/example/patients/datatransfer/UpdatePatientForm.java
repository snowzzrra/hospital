package com.example.patients.datatransfer;

import jakarta.annotation.Nonnull;

public record UpdatePatientForm(@Nonnull String nome, @Nonnull String telefone, @Nonnull AddressForm endereco) {

}
