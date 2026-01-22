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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Order} class
 *
 * Order entity
 *
 * @author colin
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "items"})
@ToString(exclude = "customer")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
	private int orderId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "customer_id", nullable = false) private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_items",
        joinColumns = { @JoinColumn(name = "order_id", nullable = false, updatable = false)},
        inverseJoinColumns = { @JoinColumn(name = "item_id", nullable = false, updatable = false)}
    )
	private Set<Item> items = new HashSet<>(0);



}
