package br.com.adrianohardcore.model;

import static javax.persistence.CascadeType.PERSIST;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@SequenceGenerator(name = "INC_USUARIO", sequenceName = "GEN_USUARIO_ID")
@Table(name = "USUARIO")
public class Usuario {


    public Usuario() {
        // copy fields from user
    }


    private static final long serialVersionUID = -7060154441729348386L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_USUARIO")
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String nome;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 5, max = 50)
	private String nomeusuario;

	@Column(unique = true)
	@Size(min = 5, max = 50)
	@Email
	private String email;

	@NotNull	
	private String senha;

	@Transient
	private String senhaForm;

	@Transient
	private String confirmSenhaForm;
	
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permisao"))
	@ManyToMany(cascade = PERSIST)
	private List<Permissao> permissoes = new ArrayList<Permissao>();









    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNomeusuario() {
		return nomeusuario;
	}

	public void setNomeusuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaForm() {
		return senhaForm;
	}

	public void setSenhaForm(String senhaForm) {
		this.senhaForm = senhaForm;
	}

	public String getConfirmSenhaForm() {
		return confirmSenhaForm;
	}

	public void setConfirmSenhaForm(String confirmSenhaForm) {
		this.confirmSenhaForm = confirmSenhaForm;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", nomeusuario=" + nomeusuario + ", email=" + email
				+ ", permissoes=" + permissoes + "]";
	}
	
	



}
