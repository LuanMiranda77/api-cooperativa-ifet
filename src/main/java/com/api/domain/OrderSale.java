package com.api.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.api.domain.enuns.OrderStatus;
import com.api.utils.UtilsHorasData;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//@autor Jadson Feitosa #AE-36

@Entity
@Data
public class OrderSale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateClose;
	
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "id_payment")
	private Payment pagamento;
	
//	@NotNull
//	@ManyToOne
//	@JoinColumn(name="cliente_id")
//	private Cliente cliente;
	
	@NotNull
//	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderItem> products = new ArrayList<OrderItem>();
	
	private BigDecimal valorTotal = new BigDecimal(0);
	
	private BigDecimal valorDesconto = new BigDecimal(0);
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserAplication userAplication;
	
	@PrePersist
	public void setDateCreate() {
		this.dateCreate = UtilsHorasData.subtrair(new Date(), 3);
	}
	
	@PreUpdate
	public void setDateUpdate() {
		this.dateClose = UtilsHorasData.subtrair(new Date(), 3);
	}
		
}
