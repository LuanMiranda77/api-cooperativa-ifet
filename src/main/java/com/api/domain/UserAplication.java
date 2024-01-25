package com.api.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.domain.enuns.StatusUsuario;
import com.api.domain.enuns.TipoUsuario;
import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//@autor Jadson Feitosa #AE-42	

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserAplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private String userName;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date acesso;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusUsuario status;
	
//	@JsonIgnore
	@NotBlank
	@Size(min = 6)
	private String password;
	
	// @Size(max=1) // M = master, V = vendedor, C = capitador 
	@Enumerated(EnumType.STRING)
	private TipoUsuario cargo;
	
	// @JsonBackReference
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	@PrePersist
	public void dataInicial() {
		this.dateCreate = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void dataAtualizacao() {
		this.dateUpdate = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	
}
