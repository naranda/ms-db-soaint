package com.soaint.rest.mapper;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Slf4j
public class BaseMapper {

	protected String getString(ResultSet rs, String nombre) throws SQLException {
		try {
			String str = rs.getString(nombre);
			return str == null ? "" : str;
		} catch (Exception e) {
			log.error(nombre);
			log.error("Error: No se encontro la columna de tipo texto[{}] : {}", nombre, e.getMessage());
			throw e;
		}
	}

	protected Integer getInteger(ResultSet rs, String nombre) throws SQLException {
		try {
			String str = rs.getString(nombre);
			return str == null ? 0 : Integer.valueOf(str);
		} catch (Exception e) {
			log.error(nombre);
			log.error("Error: No se encontro la columna de tipo entero [{}] : {}", nombre, e.getMessage());
			throw e;
		}
	}

	protected Date getDate(ResultSet rs, String nombre) throws SQLException {
		try {
			java.sql.Date date = rs.getDate(nombre);
			if (date == null) {
				return null;
			}
			return date;
		} catch (Exception e) {
			log.error("Error: No se encontro la columna de fecha [{}] : {}", nombre, e.getMessage());
			throw e;
		}
	}
}
