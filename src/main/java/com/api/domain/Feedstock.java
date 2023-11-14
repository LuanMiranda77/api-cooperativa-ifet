package com.api.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@autor Jadson Feitosa #40

@Entity
@Data
public class Feedstock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=11)
	private String codeBar;
	
	private String name;
	
	//saldo
	private Double balance;
	
	//medida
	private String measure;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="feedstock_id")
	private List<Product> products = new ArrayList<Product>();
	
	private Integer deleted = 0;
	
	@PrePersist
	public void setDateCreate() {
		this.dateCreate = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void setDateUpdate() {
		this.dateUpdate = UtilsHorasData.subtrair(new Date(), 3);
	}

}
