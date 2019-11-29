/**
 * 
 */
package com.aira.services.authorization.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

/**
 * @author Jaikishan Gurav
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(name = "permission_roles", joinColumns = {
	    @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
		    @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private List<Permission> permissions;

    public Role() {

    }

    public Role(RoleName name) {
	this.name = name;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public RoleName getName() {
	return name;
    }

    public void setName(RoleName name) {
	this.name = name;
    }

    public List<Permission> getPermissions() {
	return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
	this.permissions = permissions;
    }
}
