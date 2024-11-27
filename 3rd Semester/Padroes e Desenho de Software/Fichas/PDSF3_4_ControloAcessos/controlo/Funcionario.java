package controlo;

import instalacao.Instalacao;

/**
 * Esta classe representa um funcionário da empresa
 * Cada funcionário tem um código identificador único e um nível de acesso 
 */
public class Funcionario {
	
	public static final int NIVEL_ACESSO_MAX = 5;
	public static final int NIVEL_ACESSO_MIN = 1;

	private int id;
	private int nivel;
	private Instalacao inst = null;
	
	/**
	 * Para criar um funcionário deve-se indicar qual o seu id e o seu nível de acesso
	 * @param id identificador único do funcionário 
	 * @param nivel nível de acesso (entre NIVEL_ACESSO_MIN e NIVEL_ACESSO_MAX)
	 */
	public Funcionario(int id, int nivel) {
		this.id = id;
		setNivel( nivel );
	}

	/**
	 * retorna o nível de acesso atribuido ao funcionário
	 * @return o nível de acesso atribuido ao funcionário
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * altera o nível de acesso do funcionário, que
	 * terá de ser um valor entre NIVEL_ACESSO_MIN e NIVEL_ACESSO_MAX
	 * @param nivel novo nível de acesso
	 */
	public void setNivel(int nivel) {
		if( nivel < NIVEL_ACESSO_MIN || nivel > NIVEL_ACESSO_MAX )
			throw new IllegalArgumentException( "nivel acesso inválido" );

		this.nivel = nivel;
	}


	/**
	 * devolve o número identificador do funcionário
	 * @return o número identificador do funcionário
	 */
	public int getId() {
		return id;
	}


	/**
	 * indica se este funcionário é igual a outro funcionário
	 * o critério de igualdade é terem o mesmo id
	 *  útil para utilizar em listas e outras coleções
	 * @param f funcionário com o qual se vai comparar
	 * @return true se o funcionário f tiver o mesmo id
	 */
	public boolean equals( Funcionario f ){
		return id == f.id;
	}
		
	/**
	 * Define em que instalação o funcionário está presente
	 * @param i instalação onde vai ficar
	 */
	public void setInstalacao( Instalacao i ){
		inst = i;
	}

	/**
	 * Devolve a instalação onde está o funcionário
	 * @return a instalação onde está o funcionário, ou null se não está em nenhuma
	 */
	public Instalacao getInstalacao(){
		return inst;
	}	
}
