package com.unmsm.clinica.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Alumno;
import com.unmsm.clinica.model.parametro.Parametro;
import com.unmsm.clinica.model.seguridad.Usuario;

public interface IUsuarioMapper extends IMantenibleMapper<Usuario> {
	
	@Select(value = { "{call MANT_USUARIOS ( " 
			+ "#{verbo, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idUsuario, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.contrasenia, jdbcType = VARCHAR, mode = IN}," 
			+ "#{objeto.activo, jdbcType = BIT, mode = IN},"
			+ "#{objeto.idTipoDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.numeroDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.idPerfil, jdbcType = INTEGER, mode = IN}," 
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
	@Options(statementType = StatementType.CALLABLE)
	public List<Usuario> mantener(Parametro parametro);
	
	public List<Usuario> buscarUsuario(Usuario usuario);
}