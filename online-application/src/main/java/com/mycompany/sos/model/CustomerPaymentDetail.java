/*
 * |-------------------------------------------------
 * | Copyright © 2015 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.sos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 * {@link CustomerPaymentDetail} class
 *
 * CustomerPaymentDetail entity
 *
 * @author colin
 */
@Entity
@Table(name = "customer_payment_details")
@Getter
@Setter
@ToString(exclude = "customer")
@EqualsAndHashCode(exclude = "customer")
@NoArgsConstructor
public class CustomerPaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_payment_details_id")
	private int paymentDetailsId;

	@NotNull(message = "{error.null.customerReference}")
	@NotBlank(message = "{error.blank.customerReference}")
	@NotEmpty(message = "{error.empty.customerReference}")
	@Size(min = 1, max = 20, message = "{error.size.customerReference}")
    @Column(name = "customer_reference", nullable = false, length = 255)
	private String customerReference;

	@NotNull(message = "{error.null.cardNo}")
	@NotBlank(message = "{error.blank.cardNo}")
	@NotEmpty(message = "{error.empty.cardNo}")
	@Size(max = 16, min = 16, message = "{error.invalid.size.cardNo}")
	@Pattern(regexp = "[0-9]*", message = "{error.invalid.cardNo}")
    @Column(name = "customer_card_number", nullable = false, length = 16)
	private String cardNo;

	@NotNull(message = "{error.null.expDate}")
	@Future(message = "{error.future.expDate}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "customer_card_expiry_date", nullable = false, length = 5)
	private Date cardExpiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

}
