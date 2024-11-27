package instalacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controlo.Funcionario;

public class Complexo extends InstalacaoDefault {
	
	/*-------------------------------------------------------------------------------------------- CONSTRUTOR --------------------------------------------------------------------------------------*/
	
	public Complexo(int id, String nome) {
		super(id, nome);
	}
	
	/*---------------------------------------------------------------------------------------------- MÉTODOS ---------------------------------------------------------------------------------------*/
	
	// PARTE DE SER INSTALAÇÃO
	// Método que verifica se o funcionário pode entrar no complexo
	@Override
	public boolean podeEntrar(Funcionario f) {
		
		// Se pode entrar na instalação, então pode entrar no complexo
		if (!super.podeEntrar(f))
			return false;
		
		// Se existe alguma instalação na qual se possa entrar, então o funcionário pode entrar no complexo
		return instalacoes.stream().anyMatch(i -> i.podeEntrar(f));
		
//		for (Instalacao i : instalacoes) {
//			if (i.podeEntrar(f))
//				return true;
//		}
//		return false;
		
	}
	
	// Método que verifica se o funcionário está presente no complexo
	@Override
	public boolean estaPresente(Funcionario f) {
		return super.getPresentes().contains(f) || instalacoes.stream().anyMatch(i -> i.estaPresente(f));
	}
	
	// Método que obtém todos os funcionários presentes no complexo 
	@Override
	public Collection<Funcionario> getPresentes() {
		
		// Usando as streams
//		List<Funcionario> total = instalacoes.stream()
//				.flatMap(i -> i.getPresentes().stream())
//				.collect(Collectors.toCollection(ArrayList::new));
//		
//		total.addAll(super.getPresentes());
//		return total;
		
		// Usando o .forEach
		ArrayList<Funcionario> total = new ArrayList<Funcionario>(super.getPresentes());
		instalacoes.forEach(i -> total.addAll(i.getPresentes()));
		return total;
		
		// Usando o for
//		for (Instalacao i : instalacoes) {
//			total.addAll(i.getPresentes());
//		}
//		return total;
	}
	
	/*------------------------------------------------------------------------------------------ GETTERS E SETTERS ---------------------------------------------------------------------------------*/
	// PARTE DE SER UM COMPOSTO
	private ArrayList<Instalacao> instalacoes = new ArrayList<>();
	
	public void addInstalacao(Instalacao inst) {
		instalacoes.add(inst);
	}
	
	public void removeInstalacao(Instalacao inst) {
		instalacoes.remove(inst);
	}
	
	public List<Instalacao> getInstalacao(Instalacao inst) {
		return Collections.unmodifiableList(instalacoes);
	}
}
