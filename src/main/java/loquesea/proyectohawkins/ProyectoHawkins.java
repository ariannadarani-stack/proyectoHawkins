
package loquesea.proyectohawkins;

import java.util.Scanner;
public class ProyectoHawkins {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        
     
        final double MAX_ENERGIA = 200.0;
        final double LIMITE_RIESGO = 150.0;
        String operadorActual; 
        double sensorMedido;
        int codigoRiesgo;         
        double porcentajeEstado;
        boolean portalAbierto = true;       
        int opcionMenu=-1 ;  
        
        System.out.println("introduce nombre de usuario");
        operadorActual =sc.nextLine();//Guarde el nombre de el operador en la variable operador actual
        mostrarEncabezado(operadorActual);
        
        do{
            System.out.printf("hola, " + operadorActual + "\n --- SISTEMA HAWKINS --- \n 1. Inspeccionar Sensor Individual \n 2. Iniciar Muestreo Continuo\n 0. Salir de la Consola \n");
           
            try{
            opcionMenu =sc.nextInt();//cree el menu de opciones y lo guarde en 
            
           switch(opcionMenu){
                case 1:
                    
                    
                    sensorMedido = leerLecturaSegura(sc, "Introduce lectura µT: ");//Solicita la lectura llamando a leerLecturaSegura(teclado, "Introduce lectura µT: ") y guarda el valor en sensorMedido.
                    
                    
                    codigoRiesgo =evaluarNivelRiesgo(sensorMedido); //  Pasa sensorMedido como argumento a evaluarNivelRiesgo(...) y guarda el código devuelto encodigoRiesgo. 
                    
                    
                    porcentajeEstado =  calcularPorcentajeTruncado (sensorMedido , MAX_ENERGIA );//. Pasa sensorMedido y MAX_ENERGIA a calcularPorcentajeTruncado(...) y guarda el entero en porcentajeEstado
                    
                    
                    portalAbierto =ejecutarProtocoloAccion(codigoRiesgo);// Pasa codigoRiesgo a ejecutarProtocoloAccion(...) y asigna el resultado booleano a portalAbierto
                        System.out.println("Porcentaje de capacidad: " + porcentajeEstado + "%");
                        System.out.println("¿Portal abierto?: " + portalAbierto);
                    
                    
                    
                    
                    
                    break;
                case 2:
                    System.out.println(" iniciar muestreo continuo");//falta
                    simularMuestreoContinuo(sc, LIMITE_RIESGO);
                    
                    
                    
                   break; 
                case 0:
                   System.out.println(" salir de la consola");//falta
                   break;
                default:
                   System.out.println("opcion no valida");
           }//cierra switch
            }catch (java.util.InputMismatchException e){
                sc.nextLine();
                System.out.println("error el tipo de dato no es correcto");
            }   
        
        }while(opcionMenu != 0);
        
        
    }//cierro metodo main
    public static void  mostrarEncabezado(String operador) {
    
        System.out.printf("\t----bienvendido "+ operador +" -------\n ");
        
    }//CIERRO METODO mostrarEncabezado
    public static double leerLecturaSegura(Scanner sc, String mensaje ){
        
        double decimal ;
       
        while(true){
            System.out.println(mensaje);
            try{
                 decimal=sc.nextDouble();//pido el decimal
                return decimal;//retorno el decimal 
                        
            }catch(java.util.InputMismatchException e){     
                sc.next();
                System.out.println("el valor que has introducido no es valido");

            }catch(Exception e){
            
            }//cierra catch
       
        }//termina while
        
    }//cierro metodo leerLecturaSegura
    
    public static int evaluarNivelRiesgo(double microTeslas ){
    
        System.out.println("nivel" + ((microTeslas <  50) ? "seguro" : (microTeslas <= 150) ? "Alerta" : "critico"));
        
        if(microTeslas <  50){
        return 1;
        }else if(microTeslas <= 150){
        return 2;
        }else {
        return 3;        
        }
    
    } //cierra metodo  evaluarNivelRiesgo
   
    public static int calcularPorcentajeTruncado(double lectura , double maxEnergia ){
        double resultado1 = (lectura / maxEnergia)* 100;       
        int resultado2 = (int) resultado1;      
        return (resultado2) ;   
    
    }//cierro metodo calcularPorcentajeTruncado
    
    public static boolean ejecutarProtocoloAccion(int codigoRiesgo){
        
        switch(codigoRiesgo){
            
            case 1:
                System.out.println("Modo Estable");
                
            break;
            
            case 2: 
                System.out.println("anomalia detectada");
               
                break;
            
            case 3: 
                System.out.println("¡ALERTA MÁXIMA!");
                break;
            default:
                System.out.println("Código no válido");    
                
        }//CIERRO switch
        if(codigoRiesgo == 1){
            return false;
        }else if(codigoRiesgo == 2){
            return false;
        }else if(codigoRiesgo == 3){
        return true;
        }else{
        return false;
        } 
        
    }//cierro ejecutarProtocoloAccion

    public static void simularMuestreoContinuo(Scanner sc , double limite){
        System.out.println("si la lectura es negatica fin");
        System.out.println("si la Lectura es nula - omitir");
        System.out.println("si la Lectura supera el  límite advertencia");

    double lectura;
    double suma = 0;
    int contador = 0;

    while (true) {

        lectura = leerLecturaSegura(sc,"introduce lectura µT:");

        if (lectura <0) {
            System.out.println("finaliza el muestreo continuo");
            break;
        
        }else if (lectura == 0) {
            System.out.println("omitir lectura");
            continue;    
        
        }else if(lectura > limite){
            System.out.println("advertencia, limite superado");
        
        }

        suma += lectura;
         ++contador;    
        
    }//aqui acaba el while
    
        System.out.println("fin del muestreo, resultado:");
        
       

        if (contador == 0) { 

            System.out.println("No se registraron muestras válidas. Inténtelo de nuevo.");

        } else {

            System.out.print("Número de muestras totales válidas: " + contador);
            System.out.println("Media de todas las muestras: " + suma/contador);
        }
       
       
       
       
       
        
    }//cierro simularMuestreoContinuo
    
    } //cierra public class  
    

