package com.api.domain;



import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
	
	private String ean;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	@NotBlank
	@NotNull
	private String name;

	@NotNull
	private Double balance;
	
	@NotNull
	@Size(max=3)
	private String measure;
	
    @JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date dateCreate;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
	
	@NotNull
	private BigDecimal price;
	
	
	@Size(max=1)
	private String status="S"; //paused ou active
	
	
	private Integer deleted=0;
	
	private String descrepancies;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="produto_id")
//	private List<ImagemProduto> imagens = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orders = new ArrayList<OrderItem>();
	
	@PrePersist
	public void setDateCreate() {
		this.dateCreate = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void setDateUpdate() {
		this.dateUpdate = UtilsHorasData.subtrair(new Date(), 3);
	}

}
