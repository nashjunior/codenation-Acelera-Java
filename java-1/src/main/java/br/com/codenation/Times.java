package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Times {
	private Long id;
	private String nome;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private LocalDate dataCriacao;
	private Jogador jogador;
	private List <Jogador> jogadores= new ArrayList<Jogador>();
	
	public Jogador getJogador() {
		return this.jogador;
	}

	public List<Jogador> getJogadores(){
		return this.jogadores;
	}

	public void setJogador(Jogador jogador) {
		jogadores.add(jogador);
		this.jogador = jogador;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCorUniformePrincipal() {
		return this.corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return this.corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public LocalDate getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

  
  public Times(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
    this.setId(id);
    this.setNome(nome);
    this.setDataCriacao(dataCriacao);
    this.setCorUniformePrincipal(corUniformePrincipal);
    this.setCorUniformeSecundario(corUniformeSecundario);
	}
	
	public Jogador melhorJogador(){
		Jogador BestJogador=null;
		for(Jogador jogador:jogadores){
			if((jogador.getNivelHabilidade()>0 && BestJogador==null) || (BestJogador.getNivelHabilidade()<jogador.getNivelHabilidade())){
				BestJogador = jogador;
			}
		}
		return BestJogador;
	};

	public Jogador oldestPlayer(){
		List <Jogador> copy = new ArrayList<Jogador>(jogadores);
		copy.sort((Jogador jg1, Jogador jg2) -> jg2.getDataNascimento().compareTo(jg1.getDataNascimento()));
		return copy.get(copy.size()-1);
	};

	public Jogador bestSalary(){
		List <Jogador> copy = new ArrayList<Jogador>(jogadores);
		copy.sort((Jogador jg1, Jogador jg2) -> jg2.getSalario().compareTo(jg1.getSalario()));
		return copy.get(0);
	}

	public List <Jogador> topPlayers(int top){
		List <Jogador> copy = new ArrayList<Jogador>(jogadores);
		copy.sort((Jogador jg1, Jogador jg2)-> jg1.getId().compareTo(jg2.getId()));
		return copy;
	}
}

