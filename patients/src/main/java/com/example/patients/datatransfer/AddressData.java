package com.example.patients.datatransfer;

import com.example.patients.models.Address;

public record AddressData(Long id, String logradouro, String numero, String uf, String cep, String cidade) {

	public AddressData(Address endereco) {
		this(endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getUF(), endereco.getCep(),
				endereco.getCidade());
	}

}
