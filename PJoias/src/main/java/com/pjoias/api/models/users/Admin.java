package com.pjoias.api.models.users;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.pjoias.api.models.entities.Maleta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="admin")
public class Admin extends User{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "id_admin")
	@Cascade(CascadeType.ALL)
	private List<Vendedor> vendedores;
	
	@OneToMany
	@JoinColumn(name = "id_admin")
	@Cascade(CascadeType.ALL)
	private List<Maleta> maletas;
}