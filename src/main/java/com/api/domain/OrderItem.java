package com.api.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderSale order;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	private String name_product;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSale;

	private int quantitySale;
	
	private BigDecimal priceSale;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	@PrePersist
	private void setDateSale() {
		this.dateSale = UtilsHorasData.subtrair(new Date(), 3);
	}
	


}
