package controlo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import instalacao.Instalacao;

/**
 * Esta clase representa a central de controlo do sistema
 * Possui as listas de instalações e de funcionarios
 */
public class CentralControlo {

	private Map<Integer,Instalacao> instalacoes = new HashMap<Integer,Instalacao>();
	private Map<Integer,Funcionario> funcionarios = new HashMap<Integer,Funcionario>();
	
	public CentralControlo(){
	}

	/**
	 * adicionar um funcionàrio à central de comando
	 * @param f funcionário a adicionar
	 */
	public void addfuncionario(Funcionario f) {
		funcionarios.put( f.getId(), f );		
	}
	
	/**
	 * devolve o funcionário com o identificador especificado
	 * @param id identificador do funcionário pretendido
	 * @return funcionário com  o identificador especificado, ou null caso não exista
	 */
	public Funcionario getFuncionario(int id) {		
		return funcionarios.get(id);
	}

	/**
	 * remove o funcionário do sistema
	 * @param id identificador do funcionário a remover
	 */
	public void removeFuncionario( int id ){
		funcionarios.remove( id );
	}
	
	/**
	 * devolve uma Lista com todos os funcionários existentes no sistema
	 * @return uma Lista com todas os funcionários existentes no sistema
	 */
	public List<Funcionario> getFuncionarios(){
		return new ArrayList<Funcionario>( funcionarios.values() );
	}	

	/**
	 * adiciona uma instalação ao sistema
	 * @param inst instalação a adicionar
	 */
	public void addInstalacao(Instalacao inst) {
		instalacoes.put( inst.getId(), inst );		
	}
	
	/**
	 * devolve a instalação com o identificador especificado
	 * @param id identificador da instalação pretendida
	 * @return a instalação com o identificador especificado
	 */
	public Instalacao getInstalacao(int id) {
		return instalacoes.get( id );
	}
	
	
	/**
	 * remove uma instalação do sistema
	 * @param id identificador da instalção a remover
	 */
	public void removeInstalacao( int id ){
		instalacoes.remove( id );
	}

	/**
	 * devolve uma lista com todas as instalações existentes no sistema
	 * @return uma lista com todas as instalações existentes no sistema
	 */
	public List<Instalacao> getInstalacoes(){
		return new ArrayList<Instalacao>( instalacoes.values() );
	}		
}
