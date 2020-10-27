package com.soaint.rest.dao;

import com.soaint.rest.dto.ClienteDTO;
import com.soaint.rest.mapper.ClienteMapper;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SCHEMA = "NEWAFP";
    private static final String PACKAGE = "PINT_AFILIADO";
    private static final String FUNCTION = "consultaCliente";

    public List<ClienteDTO> getInfoCliente(String rut){

        log.debug("Antes de llamar");
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA)
                .withCatalogName(PACKAGE)
                .withProcedureName(FUNCTION)
                .declareParameters(
                        new SqlParameter("VID_PERSONA", OracleTypes.VARCHAR),
                        new SqlOutParameter("THISCURSOR", OracleTypes.CURSOR, new ClienteMapper())

                ).withoutProcedureColumnMetaDataAccess();

        HashMap<String, Object> params = new HashMap<>();
        params.put("VID_PERSONA", rut);

        List<ClienteDTO> lista = null;

        try {
            Map<String, Object> respuesta = simpleJdbcCall.execute(params);
            lista = (List<ClienteDTO>) respuesta.get("THISCURSOR");
            log.debug("respuesta = [{}]", respuesta);


        } catch (UncategorizedSQLException ex) {
            /*if (ex.getSQLException().getErrorCode() == 20002){
                return Collections.emptyList();
            }*/
            SQLException sqlException = ex.getSQLException();
            log.error("Error 1: {}", sqlException.getLocalizedMessage());

        } catch (Exception ex) {
            log.error("Error 2: {} {}",ex.getMessage(), ex.getLocalizedMessage());

        }

        return lista;
    }
}
