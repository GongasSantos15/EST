package securest.recurso;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase representa a central de controlo do sistema
 * Possui as listas de instalações e de funcionários
 * Sugere-se que usem um mapa para melhorar a pesquisa por id de funcionários e de instalações
 */
public class CentralControlo {
	
	public static final int NIVEL_MIN = 1;
	public static final int NIVEL_MAX = 5;

	private Map<Integer,Object> instalacoes = new HashMap<Integer,Object>();
	private Map<Integer,Object> funcionarios = new HashMap<Integer,Object>();

	public CentralControlo(){			
	}		

	/**
	 * adicionar um funcionário à central de controlo
	 * @param f funcionário a adicionar
	 */
	public void addFuncionario(Object f) {
		
	}
	
	/**
	 * devolve o funcionário com o identificador especificado
	 * @param id identificador do Funcionario a obter
	 * @return Funcionário com o identificador especificado
	 */
	public Object getFuncionario(int id) {		
		return funcionarios.get(id);
	}

	/**
	 * remove o funcionário do sistema
	 * @param id identificador do Funcionario a remover
	 */
	public void removeFuncionario( int id ){
		funcionarios.remove( id );
	}
	
	/**
	 * devolve uma coleção com todos os funcionários existentes no sistema
	 * @return uma coleção com todas os funcionários existentes no sistema
	 */
	public Collection<Object> getFuncionarios(){
		return Collections.unmodifiableCollection( funcionarios.values() );
	}	

	/**
	 * adiciona uma instalação ao sistema
	 * @param inst instalação a adicionar
	 */
	public void addInstalacao(Object inst) {

	}
	
	/**
	 * devolve a instalação com o identificador especificado
	 * @param id identificador da instalação pretendida
	 * @return a instalação com o identificador especificado
	 */
	public Object getInstalacao(int id) {
		return instalacoes.get( id );		
	}
	
	/**
	 * remove uma instalação do sistema
	 * @param id identificador da instalação a remover
	 */
	public void removeInstalacao( int id ){
		instalacoes.remove( id );
	}

	/**
	 * devolve uma coleção com todas as instalações existentes no sistema
	 * @return uma coleção com todas as instalações existentes no sistema
	 */
	public Collection<Object> getInstalacoes(){
		return Collections.unmodifiableCollection( instalacoes.values() );
	}
}
