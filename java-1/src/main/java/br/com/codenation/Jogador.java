package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador{
  private Long id;
  private Long idTime;
  private String nome;
  private LocalDate dataNascimento;
  private Integer nivelHabilidade;
  private BigDecimal salario;
  private Boolean isCaptain;

  public Boolean getIsCaptain() {
    return this.isCaptain;
  }

  public void setIsCaptain(Boolean isCaptain) {
    this.isCaptain = isCaptain;
  }

  public Long getId() {
		return this.id;
	}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdTime() {
    return this.idTime;
  }

  public void setIdTime(Long idTime) {
    this.idTime = idTime;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDataNascimento() {
    return this.dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Integer getNivelHabilidade() {
    return this.nivelHabilidade;
  }

  public void setNivelHabilidade(Integer nivelHabilidade) {
    this.nivelHabilidade = nivelHabilidade;
  }

  public BigDecimal getSalario() {
    return this.salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }



  public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
      BigDecimal salario) {
    this.setId(id);
    this.setIdTime(idTime);
    this.setNome(nome);
    this.setDataNascimento(dataNascimento);
    this.setNivelHabilidade(nivelHabilidade);
    this.setSalario(salario);
    this.setIsCaptain(false);
  }


}