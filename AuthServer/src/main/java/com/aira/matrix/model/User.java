package com.aira.matrix.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public User() {
    }

    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roles = user.getRoles();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;
    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    /**
  	 * @return the id
  	 */
  	Integer getId() {
  		return id;
  	}

  	/**
  	 * @param id the id to set
  	 */
  	void setId(Integer id) {
  		this.id = id;
  	}

  	/**
  	 * @return the username
  	 */
  	String getUsername() {
  		return username;
  	}

  	/**
  	 * @param username the username to set
  	 */
  	void setUsername(String username) {
  		this.username = username;
  	}

  	/**
  	 * @return the password
  	 */
  	String getPassword() {
  		return password;
  	}

  	/**
  	 * @param password the password to set
  	 */
  	void setPassword(String password) {
  		this.password = password;
  	}

  	/**
  	 * @return the email
  	 */
  	String getEmail() {
  		return email;
  	}

  	/**
  	 * @param email the email to set
  	 */
  	void setEmail(String email) {
  		this.email = email;
  	}

  	/**
  	 * @return the enabled
  	 */
  	boolean isEnabled() {
  		return enabled;
  	}

  	/**
  	 * @param enabled the enabled to set
  	 */
  	void setEnabled(boolean enabled) {
  		this.enabled = enabled;
  	}

  	/**
  	 * @return the accountNonExpired
  	 */
  	boolean isAccountNonExpired() {
  		return accountNonExpired;
  	}

  	/**
  	 * @param accountNonExpired the accountNonExpired to set
  	 */
  	void setAccountNonExpired(boolean accountNonExpired) {
  		this.accountNonExpired = accountNonExpired;
  	}

  	/**
  	 * @return the credentialsNonExpired
  	 */
  	boolean isCredentialsNonExpired() {
  		return credentialsNonExpired;
  	}

  	/**
  	 * @param credentialsNonExpired the credentialsNonExpired to set
  	 */
  	void setCredentialsNonExpired(boolean credentialsNonExpired) {
  		this.credentialsNonExpired = credentialsNonExpired;
  	}

  	/**
  	 * @return the accountNonLocked
  	 */
  	boolean isAccountNonLocked() {
  		return accountNonLocked;
  	}

  	/**
  	 * @param accountNonLocked the accountNonLocked to set
  	 */
  	void setAccountNonLocked(boolean accountNonLocked) {
  		this.accountNonLocked = accountNonLocked;
  	}

  	/**
  	 * @return the roles
  	 */
  	List<Role> getRoles() {
  		return roles;
  	}

  	/**
  	 * @param roles the roles to set
  	 */
  	void setRoles(List<Role> roles) {
  		this.roles = roles;
  	}

  	/**
  	 * @return the serialversionuid
  	 */
  	static long getSerialversionuid() {
  		return serialVersionUID;
  	}


}
