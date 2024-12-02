package aula04.tempo.immutable;

import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public final class Hora implements Cloneable {
	private final int horas;
	private final int minutos;
	private final int segundos;
	
	// constantes para vários cálculos
	public static final int SEGS_POR_MINUTO = 60;
	public static final int MINS_POR_HORA = 60;
	public static final int SEGS_POR_HORA = MINS_POR_HORA * SEGS_POR_MINUTO; // o compilador que faça a conta :-)
	public static final int HORAS_POR_DIA = 24;  
	public static final int SEGS_POR_DIA = HORAS_POR_DIA * SEGS_POR_HORA;  
	public static final int MINS_POR_DIA = HORAS_POR_DIA * MINS_POR_HORA;  
	
	
    public Hora( ){
		Calendar cal = Calendar.getInstance();
		horas = cal.get( Calendar.HOUR_OF_DAY );
		minutos = cal.get( Calendar.MINUTE );
		segundos = cal.get( Calendar.SECOND );    	
    }
    
    public Hora( int h, int m, int s){
    	horas = checkHoras(h);
     	minutos = checkMinutos(m);     	
      	segundos = checkSegundos(s);    	
    }

	private int checkHoras(int h) {
		if( h < 0 || h > HORAS_POR_DIA )
    		throw new IllegalArgumentException( "Horas ("+h+") tem de estar entre 0 e HORAS_POR_DIA");
		return h;
	}

	private int checkMinutos(int m) {
		if( m < 0 || m > MINS_POR_HORA )
    		throw new IllegalArgumentException( "minutos ("+m+") tem de estar entre 0 e MINS_POR_HORA");
		return m;
	}

	private int checkSegundos(int s) {
		if( s < 0 || s > SEGS_POR_MINUTO )
    		throw new IllegalArgumentException( "segundos ("+s+") tem de estar entre 0 e SEGS_POR_MINUTO");
		return s;
	}

    public Hora( long nSegsMeiaNoite ){
        // verificar se nº de segundos não é inválido
    	if( nSegsMeiaNoite < 0 || nSegsMeiaNoite >= SEGS_POR_DIA )
        	throw new IllegalArgumentException( "segundos (" + nSegsMeiaNoite + ") tem de ser entre 0 e " + SEGS_POR_DIA ); 
        
        horas = (int)(nSegsMeiaNoite / SEGS_POR_HORA);
        minutos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) / SEGS_POR_MINUTO);
        segundos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) % SEGS_POR_MINUTO);
    }
       
    public Hora( String hStr ){
    	// não é precio testar hStr null pois é testado ao chamar o split
 	    try {
			// ler a string no formato dia/mês/ano
			String str[] = hStr.split(":");	    
   
			// verificar se valores são válidos
			horas = checkHoras( Integer.parseInt( str[0] ) );
			minutos = checkMinutos( Integer.parseInt( str[1] ) );
			segundos = checkSegundos( Integer.parseInt( str[2] ) );
		} catch (Exception e) {
			throw new IllegalArgumentException(hStr + " não está no formato hh:mm:ss");
		}	        	
    }

    // métodos de acesso
    public int getHoras( ) {
    	return horas;
    }
    
    public int getMinutos( ) {
     	return minutos;
    }
    
    public int getSegundos( ) {
    	return segundos;
    }
    
     // métodos de implementação
    public Hora somaHoras( int numHoras ){
        return somar( this, numHoras, 0, 0);    	 	
    }
    
    public Hora somaMinutos( int numMinutos ){
        return somar( this, 0, numMinutos, 0);    	
    }
    
    
    public Hora somaSegundos( int numSegundos ){
        return somar( this, 0, 0, numSegundos );     	
    }

    private static Hora somar( Hora h, int horas, int mins, int segs ) {
    	long totalSegundos = (h.horas + horas) * SEGS_POR_HORA + 
    			             (h.minutos + mins) * MINS_POR_HORA + 
    			             (h.segundos + segs);
    	if( totalSegundos > SEGS_POR_DIA ) {
    		// em outras implementações isto podia ser importante, aqui nem por isso
    		long nDias = totalSegundos / SEGS_POR_DIA;
    		return new Hora( totalSegundos % SEGS_POR_DIA );
    	}
    	return new Hora( totalSegundos );
    }
    
    // método que retorna o nº de segundos desde a meia-noite desse dia
    public int toSegundos( ) {
    	return horas * SEGS_POR_HORA + minutos * SEGS_POR_MINUTO + segundos;
    }
    
    // método para comparar Horas    
    public int  compareTo( Hora outra){
        if( horas != outra.horas )
            return horas - outra.horas;
        if( minutos != outra.minutos )
            return minutos - outra.minutos;
        return segundos - outra.segundos;       	
    }
    
    public int getDiferencaSegs( Hora outra ){
    	return toSegundos() - outra.toSegundos();
    }
    
    // método para imprimir a hora em formato simples
    public String toString() {
    	return String.format("%02d:%02d:%02d", horas, minutos, segundos );
    }

	public Hora clone( ) {
		return new Hora( horas, minutos, segundos);
	}
}
