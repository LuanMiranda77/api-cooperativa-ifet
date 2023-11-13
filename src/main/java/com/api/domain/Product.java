package com.api.domain;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@autor Jadson Feitosa #29

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long codigo;
	
	@NotBlank
	private String ean;
	
	@JsonIgnore
	@NotNull
	@ManyToOne
    private Setor setor;
	
	@NotBlank
	private String nome;

	@NotNull
	private Float saldo;
	
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dateCreate = new Date();
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate = UtilsHorasData.subtrair(new Date(), 3);
    
    @Size(max=3)
    private String unid="UN";
    
	
	@NotNull
	private BigDecimal price;
	
	
	@Size(max=1)
	private String status="S"; //paused ou active
	
	
	private Integer deleted=0;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="produto_id")
//	private List<ImagemProduto> imagens = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orders = new ArrayList<OrderItem>();
	

}
