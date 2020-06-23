package com.unmsm.clinica.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.parametro.Parametro;
import com.unmsm.clinica.model.seguridad.TipoAuditoria;

public interface ITipoAuditoriaMapper extends IMantenibleMapper<TipoAuditoria> {
	@Select(value = { "{call MANT_TIPO_AUDITORIA ( " + "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idTipoAuditoria, jdbcType = INTEGER, mode = IN},"
			+ "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<TipoAuditoria> mantener(Parametro parametro);
}