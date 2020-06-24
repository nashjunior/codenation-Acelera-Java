package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	public List<Times> times = new ArrayList<Times>();

	public Boolean isJogador(Long idJogador, Long id){
		return idJogador == id;
	}

	public Boolean theresTeam(){
		return (times.size()>0?true:false);
	}

	public Boolean isTheTeam(long id, long idTime){
		return id==idTime;
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(theresTeam()){
			for(Times time: times){
				if(isTheTeam(time.getId(), id)){
					throw new IdentificadorUtilizadoException();
				}
			}
		}
		times.add(new Times(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if(theresTeam()){
			for(Times time: times){
				if(isTheTeam(time.getId(), idTime)){
					for(Jogador jogador:time.getJogadores()){
						if(isJogador(jogador.getId(), id)){
							throw new IdentificadorUtilizadoException();
						}
					}
					time.setJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
					return;
				}
			}
		
		}
		throw new TimeNaoEncontradoException();
	}


	public void definirCapitao(Long idJogador) {
		if(theresTeam()){
			for(Times time: times){
				for(Jogador jogador: time.getJogadores()) {
					if(isJogador(jogador.getId(), idJogador)){
						jogador.setIsCaptain(true);
						return ;
					}
				}
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		if(theresTeam()){
			for(Times time: times){
				if(isTheTeam(time.getId(), idTime)){
					for(Jogador jogador: time.getJogadores()) {
						if(jogador.getIsCaptain()==true){
							return jogador.getId();
						}
					}
					throw new CapitaoNaoInformadoException();
				}
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public String buscarNomeJogador(Long idJogador) {
		for(Times time: times){
			for(Jogador jogador:time.getJogadores()){
				if(jogador.getId()==idJogador){
					return jogador.getNome();
				}
			}
		}

		throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) {
		for(Times time:times){
			if(isTheTeam(time.getId(), idTime)){
				return time.getNome();
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadoresTime=new ArrayList<>();
		for(Times time:times){
			if(isTheTeam(time.getId(), idTime)){
				for(Jogador jogador:time.getJogadores()){
					jogadoresTime.add(jogador.getId());
				}
				return jogadoresTime;
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		for(Times time:times){
			if(isTheTeam(time.getId(), idTime)){
				return time.melhorJogador().getId();
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		for (Times time:times){
			if(isTheTeam(time.getId(), idTime)){
				return	time.oldestPlayer().getId();
			}
		}

		throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarTimes() {
		List<Long>teams = new ArrayList<>();
		for(Times time:times){
			teams.add(time.getId());
		}
		return teams;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		for(Times time:times){
			if(isTheTeam(time.getId(), idTime)){
				return time.bestSalary().getId();
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		for(Times time:times){
			for(Jogador jogador:time.getJogadores()){	
				if(isJogador(jogador.getId(), idJogador)){
					return jogador.getSalario();
				}
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		ArrayList <List<Jogador>> list = new ArrayList<List<Jogador>>();
		List <Jogador> bests = new ArrayList<Jogador>();
		List <Long> idsOftheBests = new ArrayList<Long>();
		if(top>0 && top!=null  && times.size()>0){
			for(Times time:times){
				if(time.getJogadores().size()>0)
				{list.add(time.topPlayers(top));}
			}
			for(List<Jogador> player: list){
				player.stream().forEach(bests::add);
			}
	
			bests.sort((Jogador jg1,Jogador jg2) -> jg2.getId().compareTo(jg1.getId()));
			for (Jogador jogueiro:bests.subList(0, top)){
				idsOftheBests.add(jogueiro.getId());
			}
			
		}
		
		return idsOftheBests;
	}
}
