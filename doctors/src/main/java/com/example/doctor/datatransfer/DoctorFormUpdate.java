package com.example.doctor.datatransfer;

import jakarta.annotation.Nonnull;

public record DoctorFormUpdate(@Nonnull String nome, @Nonnull String telefone, @Nonnull AdressForm endereco) {
}
