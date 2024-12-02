package aula04.tempo.copiadefensiva;

import java.util.Objects;

/**
 * Esta classe representa um per�odo de tempo

 */
public class Periodo {

	private Hora inicio;
	private Hora fim;
	
	/**
	 * Criar um período indicando a hora inicial e final
	 * garantindo que a inicial é menor que a final
	 * @param ini hora inicial do periodo
	 * @param fim hora final do periodo
	 */
	public Periodo(Hora ini, Hora fim) {
		// NOTA: não é preciso testar se ini e fim são null pois chamar o clone já testa isso
		if( ini.compareTo(fim) > 0 )
			throw new IllegalArgumentException("Hora inicial (" + ini + ") tem de ser menor que hora final (" + fim +")");

		this.inicio = ini.clone();
		this.fim = fim.clone();
	}	

	/**
	 * Criar um período indicando a hora inicial e a duração em segundos
	 * @param ini hora inicial do período
	 * @param duracao duracao do período em segundos
	 */
	public Periodo(Hora ini, int duracao ) {
		// NOTA: não é preciso testar se ini é null pois chamar o clone já testa isso
		inicio = ini.clone();
		if( duracao < 0 )
			throw new IllegalArgumentException("Duracao (" + duracao + ") tem de ser positiva!");
		// tem de ser um clone senão na linha seguinte altera também a hora inicial
		fim = inicio.clone();  
		fim.somaSegundos( duracao );
	}	


	/**
	 * devolve a hora final
	 * @return a hora final
	 */
	public Hora getFim() {
		return fim.clone();
	}


	/**
	 * altera a hora final
	 * @param fim a nova hora final
	 */
	public void setFim(Hora fim) {
		// não é preciso testar fim == null, pois vai testar na linha seguinte
		if( fim.compareTo(inicio) < 0 )
			throw new IllegalArgumentException("fim (" + fim + ") tem de maior que inicio");
		this.fim = fim.clone();
	}


	/**
	 * devole a hora inicial
	 * @return a hora inicial
	 */
	public Hora getInicio() {
		return inicio.clone();
	}


	/**
	 * altera a hora inicial
	 * @param ini a nova hora inicial
	 */
	public void setInicio(Hora ini) {
		// não é preciso testar ini == null, pois vai testar na linha seguinte
		if( ini.compareTo(fim) < 0 )
			throw new IllegalArgumentException("inicio (" + ini + " tem de menor que fim");
		this.inicio = ini.clone();
	}

	/**
	 * retorna o Período em forma de String
	 * @return o Período em forma de String
	 */
	public String toString(){
		return "[ " + inicio + " - " + fim + " ]";
	}
	
	/**
	 * retorna a duração, em segundos, do período
	 * @return a duração, em segundos, do período
	 */
	public int duracao() {
		return fim.getDiferencaSegs( inicio );
	}

	/**
	 * indica se uma dada hora está dentro deste período de tempo
	 * @param h a hora que se pretende verificar se está dentro do período de tempo
	 * @return true, se a hora está dentro
	 */
	public boolean estaDentro( Hora h ){
		return h.compareTo( inicio ) >= 0 && h.compareTo( fim ) <= 0;
	}
	
	
	/**
	 * Verifica se um, outro, período de tempo intersecta ou não este período
	 * @param outro período que se pretende verificar a interseção
	 * @return se outro período intersecta o nosso
	 */
	public boolean interseta( Periodo outro ){
		if( outro.fim.compareTo( inicio ) <= 0 )
			return false;
		if( outro.inicio.compareTo( fim ) >= 0 )
			return false;		
		return true;		
	}
	
	
	/**
	 * indica se o nosso periodo está englobado dentro do periodo p 
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p onde o nosso pode estar englobado 
	 * @return se o nosso período está englobado em p  
	 */
	public boolean estaEnglobado( Periodo p ){
		return p.inicio.compareTo( inicio ) < 0 && p.fim.compareTo( fim ) > 0;
	}
	
	
	/**
	 * indica se o periodo p está englobado neste período
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p período que pode estar englobado neste
	 * @return se p está englobado neste período
	 */
	public boolean engloba( Periodo p ){
		return p.estaEnglobado( this );
	}
	
	
	/**
	 * Junta o período p a este. A junção só é válida se os períodos se intersetarem,
	 * caso contrário, não há junção
	 * @param p período a juntar a este
	 */
	public void junta( Periodo p ){
		if( !interseta(p))
			return;
		
		if( p.inicio.compareTo(inicio) < 0 )
			inicio = p.inicio.clone();
		if( p.fim.compareTo(fim) > 0 )
			fim = p.fim.clone();		
	}
	
	
	/**
	 * devolve a união do período com o período p
	 * Se os períodos não se intersetarem não há união. Se se intersetarem
	 * a união indica o maior periodo de tempo possível juntando os dois
	 * @param p período a testar
	 * @return a união do período com o período p
	 */
	public Periodo uniao( Periodo p){
		if( !interseta( p ))
			return null;
		Hora i = p.inicio.compareTo(inicio) < 0? p.inicio: inicio;
		Hora f = p.fim.compareTo(fim) > 0? p.fim: fim;
		return new Periodo( i.clone(), f.clone() );
	}
	
	
	/**
	 * Indica a interseção do período com o período p, ou seja,
	 * qual o período de tempo que possuem em comum
	 * @param p perído a testar
	 * @return o período de tempo que possuem em comum
	 */
	public Periodo interseccao( Periodo p){
		if( !interseta(p) )
			return null;
		
		Hora i = p.inicio.compareTo(inicio) > 0? p.inicio: inicio;
		Hora f = p.fim.compareTo(fim) < 0? p.fim: fim;
		return new Periodo( i.clone(), f.clone() );
	}
}
