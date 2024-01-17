package com.api.domain.TO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import com.api.domain.UserAplication;
import com.api.domain.enuns.Roles;
import com.api.domain.enuns.StatusUsuario;

import lombok.Data;
@Data
public class UserTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String name;
	private String lastName;
	private Date dateCreate;
	private Date dateUpdate;
	private Date acesso;
	private StatusUsuario status;
	// private String celular;
	private String cargo;
	private String email;
	private String password;
	private String roles;
	private Long setor;
	private Date sincTemp;
	private String userName;
	
	public UserTO() {
		super();
	}

	public UserTO(Long id, String nome, String email,String password, Date dataCriacao, Date dataAtualizacao,
			StatusUsuario status, String roles) {
		super();
		this.id = id;
		this.name = nome;
		this.email = email;
		this.password = password;
		this.dateCreate = dataCriacao;
		this.dateUpdate = dataAtualizacao;
		this.status = status;
		this.roles = roles;
		
	}
	
	
	public UserAplication converteParaEntidade(UserTO to) {
		UserAplication usuario = new UserAplication();
		BeanUtils.copyProperties(to, usuario,"password");
		return usuario;
	}
	
	public UserAplication converteParaEntidadeSemEstabelecimento(UserTO to) {
		UserAplication usuario = new UserAplication();
		BeanUtils.copyProperties(to, usuario, "estabelecimento");
		return usuario;
	}

	public static UserTO converteParaTO(UserAplication usuario) {
		UserTO to = new UserTO();
		BeanUtils.copyProperties(usuario, to, "password");
		return to;
	}

	

}
