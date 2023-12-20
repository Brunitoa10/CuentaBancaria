/*-------------------------------------------------------------*/
                 /* Paquetes a utilizar */
/*-------------------------------------------------------------*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

/**
 * Proyecto: Cuenta Bancaria.
 * 
 * @Autor1 (Parisi Bruno - 127278)
 * @Autor2 (Dome Melina - 130216)
 * @Date (13/07/2021)
 */
public class GUI_Cajero extends JFrame 
{
    /*-------------------------------------------------------------*/
                 /* Atributos de la aplicacion */
    /*-------------------------------------------------------------*/
    // PARA HACER: declare un atributo "cuenta" que permita mantener referencia a una cuenta bancaria --> OK
     
    private CuentaBancaria cuenta; 
    
    /*-------------------------------------------------------------*/
                 /* Atributos de la GUI */
    /*-------------------------------------------------------------*/

    private Container contenedor;
    private JPanel panelAcciones, panelSaldo;
    private BotonCuentaBancaria bConsultar,bExtraer, bDepositar;
    
    /*-------------------------------------------------------------*/
                 /* Constructor de la clase */
    /*-------------------------------------------------------------*/
    
    public GUI_Cajero(CuentaBancaria cb)
    {
        super("Cuenta #"+ cb.obtenerCodigo());
        cuenta = cb;     
        setSize(300, 175);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGUI();
    }
    
    /*-------------------------------------------------------------*/
      /* Inicializa todos los componentes gr�ficos de la GUI */
    /*-------------------------------------------------------------*/
    
    private void initGUI()
    {
        /*-------------------------------------------------------------*/
                 /* Constructor de la claseContenedor y Paneles*/
        /*-------------------------------------------------------------*/
        
        contenedor = getContentPane();
        panelAcciones = new JPanel();
        panelSaldo = new JPanel();
        
        /*-------------------------------------------------------------*/
                             /* Botones */
        /*-------------------------------------------------------------*/
        // PARA HACER: cree los tres botones con el texto "Depositar", "Extraer" y "Consultar" --> OK
       
        // Boton depositar --> OK
        bDepositar = new BotonCuentaBancaria("Dep�sitar");
        //Boton Extraer --> OK
        bExtraer = new BotonCuentaBancaria("Extraer");
        //Boton Consultar --> OK
        bConsultar = new BotonCuentaBancaria("Consultar");
        
        /*-------------------------------------------------------------*/
                             /* Oyentes */
        /*-------------------------------------------------------------*/
        // PARA HACER: declare, cree y asocie los tres oyentes a cada uno de los botones. --> OK
        
        OyenteDepositar oDeposito = new OyenteDepositar();//Crear 
        bDepositar.addActionListener(oDeposito); // Asociar 
        
        OyenteExtraer oExtraer = new OyenteExtraer(); 
        bExtraer.addActionListener(oExtraer); 
        
        OyenteConsultar oConsultar = new OyenteConsultar(); 
        bConsultar.addActionListener(oConsultar); 
        
        /*-------------------------------------------------------------*/
                             /* Layout y paneles */
        /*-------------------------------------------------------------*/
       
        contenedor.setLayout(new BorderLayout());
        panelAcciones.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        panelAcciones.setPreferredSize(new Dimension(140, 60));
        panelSaldo.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        panelSaldo.setPreferredSize(new Dimension(140, 60));
        
        // PARA HACER: Agregue los botones de depositar y extraer en el panelAcciones, y el bot�n consultar en el panelSaldo --> OK
        
        panelAcciones.add(bDepositar);
        panelAcciones.add(bExtraer);
        panelSaldo.add(bConsultar);
        
        contenedor.add(panelAcciones, BorderLayout.NORTH);
        contenedor.add(panelSaldo, BorderLayout.SOUTH);
    }
    
    
    /*-------------------------------------------------------------*/
                             /* Clases Oyentes */
    /*-------------------------------------------------------------*/
    
    // PARA HACER: implemente la clase denominada OyenteDepositar, respetando el comportamiento de dicho bot�n.
    /*
     * Utilice el m�todo auxiliar obtenerEntrada(mensaje, titulo, tipoDeMensaje) para obtener la cantidad de dinero a depositar.
     * Utilice el m�todo auxiliar mostrarMensaje(mensaje, titulo, tipoDeMensaje) para mostrar la informaci�n en respuesta a la acci�n del bot�n.
     * Utilice la opci�n JOptionPane.PLAIN_MESSAGE como tipo de mensaje para solicitar el monto a depositar.
     * Utilice la opci�n JOptionPane.INFORMATION_MESSAGE como tipo de mensaje para indicar un dep�sito correcto.
    */
     
    
    class OyenteDepositar implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
          int MontoDep;   
          
          MontoDep = (int) obtenerEntrada("Ingrese la cantidad a depositar","Dep�sito",JOptionPane.PLAIN_MESSAGE);
          if(MontoDep > 0)
          {
            cuenta.depositar(MontoDep);
            mostrarMensaje("Usted Deposit� $"+MontoDep,"Comprobante Dep�sito",JOptionPane.INFORMATION_MESSAGE);
          }else
              {
                mostrarMensaje("Entrada Inv�lida","Error Dep�sito",JOptionPane.ERROR_MESSAGE);
              }
        }
    }
    
    
    // PARA HACER: implemente la clase denominada OyenteExtraer, respetando el comportamiento de dicho bot�n.
   
    /*
     * Utilice el m�todo auxiliar obtenerEntrada(mensaje, titulo, tipoDeMensaje) para obtener la cantidad de dinero a extraer.
     * Utilice el m�todo auxiliar mostrarMensaje(mensaje, titulo, tipoDeMensaje) para mostrar la informaci�n en respuesta a la acci�n del bot�n.
     * Utilice la opci�n JOptionPane.PLAIN_MESSAGE como tipo de mensaje para solicitar el monto a depositar.
     * Utilice la opci�n JOptionPane.INFORMATION_MESSAGE como tipo de mensaje para indicar una extracci�n correcta.
     * Utilice la opci�n JOptionPane.ERROR_MESSAGE como tipo de mensaje para indicar una extracci�n incorrecta.
    */
   
    class OyenteExtraer implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           int MontoExt;
           
           MontoExt = (int) obtenerEntrada("Ingrese la cantidad a extraer","Extraci�n",JOptionPane.PLAIN_MESSAGE);
    
           if(MontoExt > 0 && cuenta.puedeExtraer(MontoExt))
           {
             cuenta.extraer(MontoExt);
             mostrarMensaje("Usted extrajo $"+MontoExt,"Comprobante Extracci�n",JOptionPane.INFORMATION_MESSAGE);   
           }else
               {
                 mostrarMensaje("Usted no puede extraer esa cantidad.","Error Extracci�n",JOptionPane.ERROR_MESSAGE);
               }
        }
    }
    
    // PARA HACER: implemente la clase denominada OyenteConsultar, respetando el comportamiento de dicho bot�n.
    /*
     * Utilice el m�todo auxiliar mostrarMensaje(mensaje, titulo, tipoDeMensaje) para mostrar la informaci�n.
     * Utilice la opci�n JOptionPane.INFORMATION_MESSAGE como tipo de mensaje para indicar un saldo positivo.
     * Utilice la opci�n JOptionPane.WARNING_MESSAGE como tipo de mensaje para indicar un saldo negativo.
    */ 
    
    class OyenteConsultar implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(cuenta.obtenerSaldo() >= 0)
            {
               mostrarMensaje("Usted tiene un saldo de $"+cuenta.obtenerSaldo(),"Comprobante saldo",JOptionPane.INFORMATION_MESSAGE);  
            }else
                {
                    mostrarMensaje("Usted esta en descubierto $"+cuenta.obtenerSaldo(),"Comprobante saldo",JOptionPane.WARNING_MESSAGE);
                }
        }
    }
    
    /*-------------------------------------------------------------*/
                             /* Metodos auxiliares */
    /*-------------------------------------------------------------*/
    /*
     * Muestra un mensaje con el texto y titulo indicado por pantalla, haciendo uso de un InputDialog.
     * Obtiene el valor ingresado por el usuario, lo convierte a float y retorna.
     * tipoDeMensaje indica el formato del mensaje a mostrar. Requiere tipoDeMensaje v�lido.
     * Si el usuario no ingresa un valor, retorna 0.
    */
    
    private float obtenerEntrada(String texto, String titulo, int tipoDeMensaje){
        JOptionPane dialogo;
        String entrada;
        float retorno;
        
        dialogo = new JOptionPane();
        entrada = dialogo.showInputDialog(null, texto, titulo, tipoDeMensaje);
        
        // Se valida que se haya ingresado alg�n valor: se asume que el valor ser� un n�mero
        if ((entrada != null) && (entrada.length() > 0))
        {
            retorno = Float.parseFloat(entrada);
        }else
            {
              retorno = 0;
            }
        
        return retorno;
    }
    
    /*
     * Muestra un mensaje con el texto y titulo indicado por pantalla, haciendo uso de un MessageDialog.
     * tipoDeMensaje indica el formato del mensaje a mostrar. Requiere tipoDeMensaje v�lido.
    */
     
    private void mostrarMensaje(String texto, String titulo, int tipoDeMensaje)
    {
      JOptionPane.showMessageDialog(null, texto, titulo, tipoDeMensaje); 
    }
}