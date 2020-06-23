package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.Diente;
import com.unmsm.clinica.model.parametro.Parametro;

public interface ITratamientoDientesMapper extends IMantenibleMapper<Diente> {
	@Select(value = { "{call MANT_DIENTES ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idDiente, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.numeroRegistro, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.anio, jdbcType = VARCHAR, mode = IN}," + "#{objeto.tratamiento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.numeroDiente, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<Diente> mantener(Parametro parametro);
}