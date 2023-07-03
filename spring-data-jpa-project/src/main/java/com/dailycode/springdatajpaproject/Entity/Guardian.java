package com.dailycode.springdatajpaproject.Entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.core.sym.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
		@AttributeOverride(name = "email", column = @Column(name = "guardian_email")),
		@AttributeOverride(name = "mobile", column = @Column(name = "guardian_mobile")),

})
public class Guardian {

	private String name;

	private String email;

	private Long mobile;

}
