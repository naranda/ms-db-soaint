package com.soaint.rest.service;

import com.soaint.rest.dao.ClienteDAO;
import com.soaint.rest.dto.ClienteDTO;
import com.soaint.rest.dto.ClienteInfoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public ClienteInfoResponseDTO getInfoCliente(String rut, String canal) {
        log.debug("getInfoCliente: RUT: {} CANAL: {}",rut,canal);
        List<ClienteDTO> listaCliente = clienteDAO.getInfoCliente(rut);

        //return getInfoClienteDummy(rut, canal);


        ClienteInfoResponseDTO clienteInfoResponseDTO = new ClienteInfoResponseDTO();
        if(listaCliente != null && !listaCliente.isEmpty()){
            for(ClienteDTO cliente: listaCliente ){
                clienteInfoResponseDTO.setIdCliente(String.valueOf(cliente.getIdCliente()));
                clienteInfoResponseDTO.setNombre(cliente.getNombre());
                clienteInfoResponseDTO.setApellidoPaterno(cliente.getApPaterno());
                clienteInfoResponseDTO.setApellidoMaterno(cliente.getApMaterno());
                clienteInfoResponseDTO.setCorreoElectronico(cliente.getEmail());
                clienteInfoResponseDTO.setTelefonoCelular(cliente.getCelular());

                log.debug("Cliente desde BD: {}",clienteInfoResponseDTO.toString());
            }

        }
        return clienteInfoResponseDTO;

    }

    private ClienteInfoResponseDTO getInfoClienteDummy(String rut, String canal){

        ClienteInfoResponseDTO clienteInfoResponseDTO = new ClienteInfoResponseDTO();
        clienteInfoResponseDTO.setIdCliente("");
        clienteInfoResponseDTO.setNombre("Juan");
        clienteInfoResponseDTO.setApellidoPaterno("Perez");
        clienteInfoResponseDTO.setApellidoMaterno("Soto");
        clienteInfoResponseDTO.setCorreoElectronico("jperez@mail.cl");
        clienteInfoResponseDTO.setTelefonoCelular("+56987456321");

        log.debug("Informacion basica generada: {}",clienteInfoResponseDTO.toString());

        return clienteInfoResponseDTO;
    }
}
