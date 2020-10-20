/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatos;

/**
 *
 * @author Denilson
 */
public class binario {
    
   public static double numeroBinario(double n){
       n=Math.abs(n);
       System.out.println(Double.parseDouble(fraccionBinario(n)[0]));
      return enteroBinario(n,2)+Double.parseDouble(fraccionBinario(n)[0]);
       
   }
    
   public static int enteroBinario(double numero,int mod){ // combiete a base binaria a decimal y visceversa; (mod es a la base que quieres convertir)
       
       int residuo=0,resultado=0,i=0,n=(int)(Math.abs(numero)),base=10;
       if (mod==10) {
           base=2;
       }
       while(n>=1){
           residuo=n%mod;
           resultado+=residuo*Math.pow(base, i);
           n=n/mod;
           i++;
         
       }
        
       return resultado;
   }
   
   public static String[] fraccionBinario(double numero){ //convertir de decimal a binario
        double fracccion=cortarFraccion(numero),resultado=0;
        String r="",este="";
        
       int i=1;
       while(fracccion!=0){
           r+=""+fracccion+"*2=";
       fracccion=fracccion*2;
       r+=""+fracccion+" ";
       este+=Double.toString(fracccion).substring(0, 1);
       resultado+=((int)fracccion)/Math.pow(10, i);
       
       fracccion=cortarFraccion(fracccion);
       
           if (i==12) {
               break;
           }
          i++;  
       }
       este="0."+este;
       String a[]={este,r};
       return a;
   }
   
   public static double cortarFraccion(double  numero){ //para cortar la parte fraccionaria
        numero=Math.abs(numero);
       String t=Double.toString(numero);
        return Double.parseDouble("0"+t.substring(t.indexOf(".")));
   }
   
   public double matisa_Decimal(double numero){ //convertir la matisa a decimal
       String n=Double.toString(numero);
       double resultado=0.5; int c=2; 
       while(n.length()>0){
          resultado+=Integer.parseInt( n.substring(0,1))/Math.pow(2, c);
          n=n.substring(c-1);
           c++;
       }
       return resultado;
         
         
      
   }
   
}
