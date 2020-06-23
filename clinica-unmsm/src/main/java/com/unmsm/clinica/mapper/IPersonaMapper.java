package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Persona;
import com.unmsm.clinica.model.parametro.Parametro;

public interface IPersonaMapper extends IMantenibleMapper<Persona> {
	@Select(value = { "{call MANT_PERSONAS ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idTipoDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.numeroDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.nombres, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.apellidoPaterno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.apellidoMaterno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idSexo, jdbcType = VARCHAR, mode = IN}," + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<Persona> mantener(Parametro parametro);
}