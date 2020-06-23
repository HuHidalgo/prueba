package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Facultad;
import com.unmsm.clinica.model.parametro.Parametro;

public interface IFacultadMapper extends IMantenibleMapper<Facultad> {
	@Select(value = { "{call MANT_FACULTADES ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.codigoFacultad, jdbcType = NUMERIC, mode = IN},"
			+ "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.correoAsistenta, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.correoFacultad, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<Facultad> mantener(Parametro parametro);
}