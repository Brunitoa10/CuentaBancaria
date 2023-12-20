public class CuentaBancaria{
    //Atributos de clase, monto máximo para extraer en descubierto
    private static final int maxDescubierto = 1000;
    
    //Atributos de instancia
    private int codigo;
    private float saldo;
    
    // Constructores
    // El código se establece al crear la cuenta y no cambia
    public CuentaBancaria(int cod){
        codigo = cod; 
        saldo = 0;
    }
    
    public CuentaBancaria(int cod, float sal){
        codigo = cod;
        saldo = sal;
    }
    
    // Comandos
    public void depositar(float mto){
        saldo= saldo + mto;
    }
       
    public void extraer(float mto){
        if (puedeExtraer(mto)){
            saldo=saldo-mto;
        }
    }
       
    // Consultas
    public int obtenerCodigo(){
        return codigo;
    }
    
    public float obtenerSaldo(){
        return saldo;
    }
    
    public String toString(){
        return codigo + " "+ saldo;
    }
    
    public boolean equals(CuentaBancaria cta){
        boolean iguales;
        iguales = codigo == cta.obtenerCodigo();
        iguales = iguales && saldo == cta.obtenerSaldo();
        return iguales;
    }

    public boolean puedeExtraer(float mto){
        boolean puede = false;
        
        if ( (-1)*(saldo-mto) <= maxDescubierto){
            puede = true;
        }
        return puede;
    }

    public int mayorSaldo(CuentaBancaria cta){
        int retorno;
        
        if (saldo > cta.obtenerSaldo()){
            retorno = codigo;
        }else{
            retorno = cta.obtenerCodigo();
        }
        return retorno;
    }
    
    public CuentaBancaria cta_mayorSaldo(CuentaBancaria cta){
        CuentaBancaria retorno;
        
        if (saldo > cta.obtenerSaldo()){
            retorno = this;
        }else{
            retorno = cta;
        }
        return retorno;
    }
}