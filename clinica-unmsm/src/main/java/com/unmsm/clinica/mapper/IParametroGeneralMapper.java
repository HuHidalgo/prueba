package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.ParametroGeneral;
import com.unmsm.clinica.model.parametro.Parametro;

public interface IParametroGeneralMapper extends IMantenibleMapper<ParametroGeneral> {
	@Select(value = { "{call MANT_PARAMETRO_GENERAL ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.tiempoReprogramacion, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.correoSUM, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.correoClinica, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.anioInicial, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<ParametroGeneral> mantener(Parametro parametro);
}