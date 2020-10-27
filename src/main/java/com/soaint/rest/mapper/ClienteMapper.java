package com.soaint.rest.mapper;

import com.soaint.rest.dto.ClienteDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper extends BaseMapper implements RowMapper<ClienteDTO> {
    private static final String COLUMNA_APPATERNO = "ap_paterno";
    private static final String COLUMNA_APMATERNO = "ap_materno";
    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_EMAIL = "email";
    private static final String COLUMNA_CELULAR = "celular";
    private static final String COLUMNA_IDCLIENTE = "id_cliente";

    @Override
    public ClienteDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setApPaterno(getString(resultSet,COLUMNA_APPATERNO));
        clienteDTO.setApMaterno(getString(resultSet,COLUMNA_APMATERNO));
        clienteDTO.setNombre(getString(resultSet,COLUMNA_NOMBRE));
        clienteDTO.setEmail(getString(resultSet,COLUMNA_EMAIL));
        clienteDTO.setCelular(getString(resultSet,COLUMNA_CELULAR));
        clienteDTO.setIdCliente(getInteger(resultSet,COLUMNA_IDCLIENTE));



        return clienteDTO;
    }
}
