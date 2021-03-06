package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.ExamenMedicoRadiologico;
import com.unmsm.clinica.model.parametro.Parametro;

public interface IExamenMedicoRadiologicoMapper extends IMantenibleMapper<ExamenMedicoRadiologico> {
	@Select(value = { "{call MANT_EXAMEN_MEDICO_RADIOLOGICO ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.numeroRegistro, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.anio, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.codigoAlumno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.tipoAlumno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idResultadoRadiologico, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.observacion, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idCampania, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.idEstadoExamenMedico, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<ExamenMedicoRadiologico> mantener(Parametro parametro);
}