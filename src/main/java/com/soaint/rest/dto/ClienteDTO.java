package com.soaint.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private String apPaterno;
    private String apMaterno;
    private String nombre;
    private String email;
    private String celular;
    private Integer idCliente;
}
