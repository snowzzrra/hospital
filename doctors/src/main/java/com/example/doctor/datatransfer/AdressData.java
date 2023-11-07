package com.example.doctor.datatransfer;

import com.example.doctor.models.Address;

public record AdressData(Long id, String logradouro, String numero, String uf, String cep, String cidade) {
	public AdressData(Address endereco) {
		this(endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getUF(), endereco.getCep(),
				endereco.getCidade());
	}
}
