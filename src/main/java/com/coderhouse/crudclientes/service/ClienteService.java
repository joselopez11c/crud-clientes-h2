package com.coderhouse.crudclientes.service;

import com.coderhouse.crudclientes.handle.ApiException;
import com.coderhouse.crudclientes.model.ClienteEntity;
import com.coderhouse.crudclientes.model.ClienteRequest;
import com.coderhouse.crudclientes.model.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse buscarPorDni(Long dni);
    List<ClienteResponse> buscarTodos();
    ClienteResponse crear(ClienteRequest cliente) throws ApiException;
    ClienteResponse actualizar(ClienteRequest cliente) throws ApiException;
    void eliminar(ClienteRequest cliente);
}
