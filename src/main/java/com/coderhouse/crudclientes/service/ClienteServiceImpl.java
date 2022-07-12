package com.coderhouse.crudclientes.service;

import com.coderhouse.crudclientes.builder.ClienteBuilder;
import com.coderhouse.crudclientes.dao.ClienteRepository;
import com.coderhouse.crudclientes.handle.ApiException;
import com.coderhouse.crudclientes.model.ClienteEntity;
import com.coderhouse.crudclientes.model.ClienteRequest;
import com.coderhouse.crudclientes.model.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse buscarPorDni(Long dni) {
        ClienteEntity cliente = clienteRepository.findById(dni).orElse(null);
        return ClienteBuilder.entityToResponse(cliente);
    }

    @Override
    public List<ClienteResponse> buscarTodos() {
        return ClienteBuilder.entityToResponseList(clienteRepository.findAll());
    }

    @Override
    public ClienteResponse crear(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) == null) {
                ClienteEntity entity = clienteRepository.save(ClienteBuilder.requestToEntity(cliente));
                return ClienteBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Cliente ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ClienteResponse actualizar(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) != null) {
                ClienteEntity entity = clienteRepository.save(ClienteBuilder.requestToEntity(cliente));
                return ClienteBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Cliente no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ClienteRequest cliente) {
        clienteRepository.delete(ClienteBuilder.requestToEntity(cliente));
    }
}
