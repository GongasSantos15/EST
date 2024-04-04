package securest.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import p2.tempo.Hora;
import p2.tempo.Horario;

public class Instalacao {
	private int id, nivelAcesso;
	private String descricao;
	private Horario horarioFuncionamento;

	// Criar listas de funcionarios presentes e dos autorizados
	private ArrayList<Funcionario> presentes = new ArrayList<Funcionario>();
	private ArrayList<Funcionario> autorizados = new ArrayList<Funcionario>();

	/* --------------------------------------------------- CONSTRUTOR ---------------------------------------------------------- */

	public Instalacao(int id, int nivelAcesso, String descricao, Horario horarioFuncionamento) {
		this.id = id;
		setNivelAcesso(nivelAcesso);
		setDescricao(descricao);
		setHorarioFuncionamento(horarioFuncionamento);
	}
	
	/* ------------------------------------------------------ MÉTODOS ---------------------------------------------------------- */
	
	// Método que indica se o funcionário pode entrar na instalação na hora h
	public boolean podeEntrar(Funcionario f, Hora h) {
		if (!horarioFuncionamento.estaDentro(h)) {
			return false;
		} else {
			return f.getNivelAcesso() >= getNivelAcesso() || autorizados.contains(f);
		}
	}
	
	// Método que processa a entrada (se for possível) do funcionário no momento
	public boolean entrar(Funcionario f) {
		if (!podeEntrar(f, new Hora())) {
			return false;
		} else {
			presentes.add(f);
			f.entrar(this);
			return true;
		}
	} 
	
	// Método que processa a saída do funcionário
	
	
	// Método que adiciona um utente à lista de funcionários autorizados
	private void addAutorizado (Funcionario f) {
		autorizados.add(f);
	}
	
	// Método que remove um utente da lista de funcionários autorizados
	private void removeAutorizado (Funcionario f) {
		autorizados.remove(f);
	}
	
	

	/* ------------------------------------------------ GETTERS E SETTERS ------------------------------------------------------ */
	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		if (nivelAcesso >= CentralControlo.NIVEL_MIN && nivelAcesso <= CentralControlo.NIVEL_MAX) {
			this.nivelAcesso = nivelAcesso;
		} else {
			this.nivelAcesso = CentralControlo.NIVEL_MAX;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Horario getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(Horario horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public int getId() {
		return id;
	}

	// Listas só podem ter get, cria-se métodos add() e remove() no lugar do set */
	public List<Funcionario> getPresentes() {
		return Collections.unmodifiableList(presentes);
	}

	public List<Funcionario> getAutorizados() {
		return Collections.unmodifiableList(autorizados);
	}

	 /* ------------------------------------------------ TOSTRING, HASHCODE e EQUALS ------------------------------------------ */
	@Override
	public String toString() {
		return "[ ID = " + id + "; NÍVEL = " + nivelAcesso + "; DESCRIÇÃO = " + descricao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizados, descricao, horarioFuncionamento, id, nivelAcesso, presentes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instalacao other = (Instalacao) obj;
		return Objects.equals(autorizados, other.autorizados) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(horarioFuncionamento, other.horarioFuncionamento) && id == other.id
				&& nivelAcesso == other.nivelAcesso && Objects.equals(presentes, other.presentes);
	}
	
	

}
