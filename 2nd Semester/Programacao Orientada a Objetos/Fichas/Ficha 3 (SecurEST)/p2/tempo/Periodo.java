package p2.tempo;

/**
 * Esta classe representa um período de tempo
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
		if( ini.compareTo(fim) > 0 ){
			this.inicio = fim;
			this.fim = ini;			
		}
		else {
			this.inicio = ini;
			this.fim = fim;
		}
	}	

	/**
	 * Criar um período indicando a hora inicial e a duração em segundos
	 * @param ini hora inicial do período
	 * @param duracao duração do período em segundos
	 */
	public Periodo(Hora ini, int duracao ) {
		this.inicio = ini;
		// tem de ser um clone senão na linha seguinte altera também a hora inicial
		fim = ini.clone();  
		fim.somaSegundos( Math.abs(duracao) );
	}	

	/**
	 * indica se uma dada hora está dentro deste período de tempo
	 * @param h a hora que se pretende verificar se está dentro do período de tempo
	 * @return true, se a hora está dentro, false caso contrário
	 */
	public boolean estaDentro( Hora h ){
		return h.compareTo( inicio ) >= 0 && h.compareTo( fim ) <= 0;
	}
	
	/**
	 * Verifica se outro periodo de tempo interseta este período
	 * @param outro período com o qual se pretende verificar a interseção
	 * @return true, se os períodos se intersetam 
	 */
	public boolean interseta( Periodo outro ){
		if( outro.fim.compareTo( inicio ) < 0 )
			return false;
		if( outro.inicio.compareTo( fim ) > 0 )
			return false;		
		return true;		
	}
	
	/**
	 * indica se o período está englobado dentro do periodo p 
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p período a testar 
	 * @return true, se o período está englobado em p  
	 */
	public boolean estaContido( Periodo p ){
		return p.inicio.compareTo( inicio ) <= 0 && p.fim.compareTo( fim ) >= 0;
	}
	
	
	/**
	 * indica se o periodo p está englobado neste período
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p período a testar
	 * @return true, se p está englobado no nosso período
	 */
	public boolean contem( Periodo p ){
		return p.estaContido( this );
	}
	
	
	/**
	 * Junta o período p a este. A junção só é válida se os períodos se intersetarem,
	 * caso contrário, não há junção
	 * @param p período a juntar
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
	 * devolve a união deste período com o período p
	 * Se os períodos não se intersetarem não há união. Se se intersetarem
	 * a união indica o maior periodo de tempo possível juntando os dois
	 * @param p período a unir
	 * @return a união do período com o período p, null se não se intersetarem
	 */
	public Periodo getUniao( Periodo p){
		if( !interseta( p ))
			return null;
		Hora i = p.inicio.compareTo(inicio) < 0 ? p.inicio.clone(): inicio.clone();
		Hora f = p.fim.compareTo(fim) > 0 ? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}
	
	
	/**
	 * Devolve a interseção do período com o período p, ou seja,
	 * qual o período de tempo que possuem em comum
	 * @param p período a intersetar
	 * @return o período de tempo que possuem em comum, null se não se intersetarem
	 */
	public Periodo getInterseccao( Periodo p){
		if( !interseta(p) )
			return null;
		
		Hora i = p.inicio.compareTo(inicio) > 0 ? p.inicio.clone(): inicio.clone();
		Hora f = p.fim.compareTo(fim) < 0 ? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}

	/**
	 * devolve a hora final
	 * @return a hora final
	 */
	public Hora getFim() {
		return fim;
	}

	/**
	 * altera a hora final
	 * @param fim a nova hora final
	 */
	public void setFim(Hora fim) {
		if( fim.compareTo( inicio) >= 0)
			this.fim = fim;
	}

	/**
	 * devolve a hora inicial
	 * @return a hora inicial
	 */
	public Hora getInicio() {
		return inicio;
	}

	/**
	 * altera a hora inicial
	 * @param ini a nova hora inicial
	 */
	public void setInicio(Hora ini) {
		if( ini.compareTo( fim ) <= 0)
			this.inicio = ini;
	}

	/**
	 * retorna o Período em forma de String
	 * @return o Período em forma de String
	 */
	public String toString(){
		return "[ " + inicio + " - " + fim + " ]";
	}
}
