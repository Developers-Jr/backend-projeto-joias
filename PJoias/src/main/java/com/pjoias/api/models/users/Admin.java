package com.pjoias.api.models.users;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.pjoias.api.models.entities.Maleta;

import lombok.Data;

@Data
@Entity
@Table(name="admin")
public class Admin extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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