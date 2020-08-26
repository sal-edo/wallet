package com.saledo.wallet.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	protected Long id;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Type(type = "org.hibernate.type.InstantType")
	@Column(name = "CREATED", nullable = false)
	protected Instant created = Instant.now();

	@Version
	@Column(name = "VERSION", nullable = false, columnDefinition = "bigint default '0'")
	protected Long version;
}
