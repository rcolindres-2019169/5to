/*
    Ricardo Adrián Colindres Franco
    IN5AM
    2019169
    Fecha Creación: 
        01-03-2023
    Fechas Modificaciones:
        02-03-2023
*/

package org.ricardocolindres.main;

//import java.util.HashSet;
//import java.util.Scanner;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Sumar;
import org.ricardocolindres.bean.Restar;
import org.ricardocolindres.bean.Multiplicar;
import org.ricardocolindres.bean.Dividir;
import org.ricardocolindres.bean.Salir;


public class Principal {
    //Método Main
    public static void main(String[] args) {
          
          int numero;

        Sumar suma = new Sumar(0,0);
        Restar restando = new Restar(0,0);
        Multiplicar multiplicando = new Multiplicar (0,0);
        Dividir dividiendo = new Dividir (0,0);
        //Salir salir = new Salir();
        
        
        JOptionPane.showMessageDialog(null, "Ricardo Adrián Colindres Franco");
        do{
        numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de opción\n"
                + " 1. Suma\n"
                + " 2. Resta\n"
                + " 3. Multiplicación\n"
                + " 4. División\n"
                + " 5. Salir\n"));
        
        
        
        switch (numero){
        case 1:
            
            suma.setValor1(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número")));
            suma.setValor2(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese otro número")));
        
            JOptionPane.showMessageDialog(null, "El resultado es: " + suma.sumatoria(suma.getValor1(), suma.getValor2()));
            
            break;
            
        case 2:
            restando.setValor1(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número")));
            restando.setValor2(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese otro número")));
        
            JOptionPane.showMessageDialog(null, "El resultado es: " + restando.resta(restando.getValor1(), restando.getValor2()));
            break;
            
        case 3:
            multiplicando.setValor1(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número")));
            multiplicando.setValor2(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese otro número")));
        
            JOptionPane.showMessageDialog(null, "El resultado es: " + multiplicando.multiplica(multiplicando.getValor1(), multiplicando.getValor2()));
            break;
            
        case 4 :
            dividiendo.setValor1(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número")));
            dividiendo.setValor2(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese otro número")));
        
            JOptionPane.showMessageDialog(null, "El resultado es: " + dividiendo.divide(dividiendo.getValor1(), dividiendo.getValor2()));  
                break;
        case 5:
            
            JOptionPane.showMessageDialog(null, "Saliendo...");
            Salir.salir();
            }
        
        }while(numero!=5);
    }
}









//        int a = 0,b=0,c = 0;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Ricardo Colindres");
//        System.out.println("Ingrese un número entero");
//        a = sc.nextInt();
//        System.out.println("Ingrese otro número");
//        b = sc.nextInt();
//        c = a + b;
//        System.out.println("El resultado de la suma es: " + c);

//        JOptionPane.showMessageDialog(null, "Ricardo Adrián Colindres Franco");
//       JOptionPane.showInputDialog(null, "Ingrese un valor númerico");
//        JOptionPane.showMessageDialog(null, "Qué deseas hacer?");
//       int num1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese un número"));
//       num2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese otro número"));
//       result = num1 + num2;
//       JOptionPane.showMessageDialog(null, "La suma de los valores es: " + result);
        