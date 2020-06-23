package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Medico;
import com.unmsm.clinica.model.parametro.Parametro;

public interface IMedicoMapper extends IMantenibleMapper<Medico> {
	@Select(value = { "{call MANT_MEDICOS ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idTipoDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.numeroDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.colegioMedico, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.registroNacionalEspecialidad, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.director, jdbcType = BIT, mode = IN}," + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<Medico> mantener(Parametro parametro);
}