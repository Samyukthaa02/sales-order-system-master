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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Customer} class
 *
 * Customer entity
 *
 * @author colin
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "orders")
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
	private int customerId;

	@NotNull(message = "{error.null.firstname}")
	@NotBlank(message = "{error.blank.firstname}")
	@NotEmpty(message = "{error.empty.firstname}")
	@Pattern(regexp = "[a-zA-Z]*", message = "{error.invalid.firstname}")
    @Column(name = "customer_firstname", nullable = false, length = 50)
	private String firstName;

	@NotNull(message = "{error.null.lastname}")
	@NotBlank(message = "{error.blank.lastname}")
	@NotEmpty(message = "{error.empty.lastname}")
	@Pattern(regexp = "[a-zA-Z]*", message = "{error.invalid.lastname}")
    @Column(name = "customer_lastname", nullable = false, length = 50)
	private String lastName;

	@NotNull(message = "{error.null.dob}")
	@Past(message = "{error.past.dob}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "customer_date_of_birth", nullable = false)
	private Date dateOfBirth;

	@NotNull(message = "{error.null.email}")
	@NotBlank(message = "{error.blank.email}")
	@NotEmpty(message = "{error.empty.email}")
	@Email(message = "{error.email}")
    @Lob
    @Column(name = "customer_email_address", nullable = false, columnDefinition = "TEXT")
	private String email;

	// M : 1
	@Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_address_id")
	private Address address;

	// 1 : 1
	@Valid
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.ALL})
	private CustomerPaymentDetail customerPaymentDetail;

	// 1 : M
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Order> orders = new HashSet<>(0);

}
