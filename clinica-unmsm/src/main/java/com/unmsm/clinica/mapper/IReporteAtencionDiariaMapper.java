package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;

public interface IReporteAtencionDiariaMapper {
	@Select(value = { "{call REPORT_ATENCION_DIARIA ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{fechaInicio, jdbcType = DATE, mode = IN}," + "#{fechaFin, jdbcType = DATE, mode = IN},"
			+ "#{idCampania, jdbcType = INTEGER, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<ReporteAtencionDiaria> buscarAtencionesDiarias(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria);

	public List<ReporteAtencionDiaria> buscarAtencionesDiariasLaboratorio(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria);

	public List<ReporteAtencionDiaria> buscarAtencionesDiariasRadiologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria);

	public List<ReporteAtencionDiaria> buscarAtencionesDiariasPsicologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria);
}