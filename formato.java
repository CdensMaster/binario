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
public class formato {
    public  String res="";
        
    public String [] formatoIEEE754(double numero, int exp, int m) {
       String n[]= correrComa(numero);
       int exponente= exponente(exp,Integer.parseInt( n[1]));
       int signo=signo(numero);
        System.out.println(n[0]);
       
       double matisa=(binario.cortarFraccion(Double.parseDouble( n[0]))*Math.pow(10, 5));
        String []r={Integer.toString(signo),Integer.toString(exponente), n[0].substring(2)+"00000.....0"};
        return r;
    }
    
    public String [] formatoGeneral(double numero, int exp, int m){
        int signo=signo(numero);
        
        numero=Math.abs(numero);
     double n[]=matisaGeneral(numero);
     int exponente=exponente(exp,(int) n[1]);
     String matisaPasoPaso[]=binario.fraccionBinario(n[0]);
     String matisa=matisaPasoPaso[0].substring(2);
        int di=(m-matisa.length());// para saber cuantos bits faltan agregagar a la matisa
        if (di>=0) {
          for (int i = 0; i <di; i++) {
            matisa+="0";
            System.out.println(i);
        }  
        }else{
            matisa=matisa.substring(-di);
        }
        
        System.out.println(matisa);
//     String matisa=Double.toString( Double.parseDouble( matisaPasoPaso[0]) *Math.pow(10,6));
     res+=matisaPasoPaso[1];
//     if (signo==1) {
//            matisa=complemento1(Double.parseDouble(matisa));
//        }
     String [] r={Integer.toString(signo),Integer.toString(exponente),matisa};
     return r;
    }
    
    private int signo(double n){
        int r=0;
        if (n<0) {
            r=1;
        } 
        return r;
    }
    
    private String[] correrComa(double n){  //para el exponente del formato ieee754
        
        String numeroBi=Double.toString( binario.numeroBinario(n));
        res+=""+numeroBi+" ";
        int c=0;
        res+=""+numeroBi+"*2^"+c+" ";
        while(Double.parseDouble(numeroBi)>2){
            
            int pos=numeroBi.indexOf(".");
            String numero=numeroBi.substring(pos-1, pos);
            numeroBi=numeroBi.substring(0, pos-1)+"."+numero+numeroBi.substring(pos+1, numeroBi.length());
            c++;
            res+=""+numeroBi+"*2^"+c+" ";
        }
        res+="?";
        String r[]={numeroBi,""+c};
        return r;
    }
    
    private  double [] matisaGeneral(double n){
        
        int c=0;
        String procedimiento="";
        while(((int)n)>=1){
            c++;
            procedimiento+=""+n+"/2=";
            n=n/2;
            procedimiento+=""+n+" ";
        }
        double r[]={n,c};
        procedimiento+="?";
        res=procedimiento;
        return r;
    }
    
    private int exponente(int exp,int e){
        res+=""+e+"+exceso "+e+"+2^("+exp+"-1) "+e+"+2^"+(exp-1)+"=";
        
       int numeroExpDeci=(int)(Math.pow(2,(exp-1))+e);
       res+=""+numeroExpDeci+" ?"; 
       return binario.enteroBinario(numeroExpDeci,2);
    }
    
    
    
    
   public String complemento1(String numero){
      
      String reserva=numero;
        for (int i = 0; i < numero.length(); i++) {
           
            if (numero.substring(i, i+1).equals("0")) {
                numero=numero.substring(0,i)+"1"+numero.substring(i+1,numero.length());
            } 
        }
        for (int i = 0; i < numero.length(); i++) {
          if (reserva.substring(i, i+1).equals("1")) {
                numero=numero.substring(0,i)+"0"+numero.substring(i+1,numero.length());
            } 
        }
        return numero;
   }
   public String complemento2(String n){
       String r=complemento1(n),respueta="";
       
       respueta=sumarBit1(r);
       return respueta;
   }
   public String sumarBit1(String r){
       String respuesta="";
   if (r.substring(r.length()-1).equals("0")) {
           respuesta=r.substring(0,r.length()-1)+"1";
       }else{
//           int cociente=1;
//           int i = r.length();
//           while(cociente>=1){
//               int dividendo=(Integer.parseInt(r.substring(i-1,i)))+cociente;
//               respueta=""+(dividendo%2)+respueta;
//               cociente=(int)(dividendo/2); i--;
//               
//           }
//           respueta=r.substring(0,i)+respueta;
           
           for (int i = r.length(); i >=0; i--) {
               if (r.substring(i-1,i).equals("0")) {
                   r=r.substring(0,i-1)+"1"+r.substring(i);
                   break;
               }else{
                   r=r.substring(0,i-1)+"0"+r.substring(i);
               }
           }
           respuesta=r;
       }
       return respuesta;
   }
   
   
   private static String[] procesoMatisa(String matisa, String expo){//devuelve la matisa con el bit incluido
   String proc="";
      matisa="0.1"+matisa;
    int exceso=(int) Math.pow(2,expo.length()-1);
    int e= binario.enteroBinario(Double.parseDouble(expo), 10);
    int pot=e-exceso;
    proc+="Exponente-exceso "+e+"-"+exceso+"="+pot+" "+matisa+"*2^"+pot+" ?";
    String [] r={matisa,""+pot,proc};
    return r;
   }
   
   public static String[] sumar(String matisa, String expo,String matisa2, String expo2){ //pasamos el valor de la matisa
      String [] r1= procesoMatisa(matisa, expo);
      String [] r2= procesoMatisa(matisa2, expo2);
      String proce=r1[2]+r2[2]+"";
      int diferencia=Math.abs( Integer.parseInt(r1[1])-Integer.parseInt(r2[1]));
      String bitGuarda1="",bitGuarda2="";
      if (r1[1].equals(r2[1])) {
          bitGuarda2=bitGuarda1="00";
       }else if (Integer.parseInt(r1[1])>Integer.parseInt(r2[1])) { //correremos la matisa del segundo numero
           proce+="Debemos-homogeneizar-el-segundo-numero ";
           r2[0]=r2[0].substring(2);
           for (int i = 0; i <diferencia; i++) {
              r2[0] ="0"+r2[0];
           }
           r2[0]="0."+r2[0];
           bitGuarda1="00";
           if (diferencia>=2) { //para sacar los bits de guarda
              bitGuarda2=r2[0].substring(matisa.length()+3,matisa.length()+5);
          }else{
              bitGuarda2=r2[0].substring(matisa.length()+3)+"0";
           }
           proce+=r2[0]+"*2^"+(diferencia+Integer.parseInt(r2[1]))+" ";
       }else {
           proce+="Debemos-homogeneizar-el-primer-numero ";
           r1[0]=r1[0].substring(2);
           for (int i = 0; i <diferencia; i++) {
              r1[0] ="0"+r1[0];
           }
           r1[0]="0."+r1[0];
           bitGuarda2="00";
           if (diferencia>=2) { //para sacar los bits de guarda
              bitGuarda1=r1[0].substring(matisa.length()+3,matisa.length()+5);
          }else{
              bitGuarda1=r1[0].substring(matisa.length()+3)+"0";
           }
           proce+=r1[0]+"*2^"+(diferencia+Integer.parseInt(r1[1]))+" ";
       }
      
//       proce+=+"--"++" "++"--"++" ";
      String sumaGuarda=sumaBit(bitGuarda1, bitGuarda2, 2);
      String sumaMatisa=sumaBit(r1[0], r2[0], matisa.length()+3);//mas 1 el bit y dos por el cero y el punto
      String [] var={proce,r1[0].substring(0,matisa.length()+1+2),bitGuarda1,r2[0].substring(0,matisa.length()+1+2),bitGuarda2,sumaMatisa,sumaGuarda};
       return var;
    }
   
   private static String sumaBit(String r1,String r2,int tamaño ){
   String resultado="";
           int lleva=0;
       for (int i = tamaño; i >0 ; i--) { 
           String l1=r1.substring(i-1,i),l2=r2.substring(i-1, i);
           if (l1.equals(".")) {
               resultado="."+resultado;
           }if (l1.equals("0") && l2.equals("0")) {
               resultado=lleva+resultado;
               lleva=0;
           }else if (l1.equals("0") && l2.equals("1")) {
               int s=1+lleva;
               resultado=(s%2)+resultado;
               lleva=(int)(s/2);
           }else if (l1.equals("1") && l2.equals("0")) {
               int s=1+lleva;
               resultado=(s%2)+resultado;
               lleva=(int)(s/2);
           }else if (l1.equals("1") && l2.equals("1")) {
               int s=2+lleva;
               resultado=(s%2)+resultado;
               lleva=(int)(s/2);
           }
       }
       return resultado;
   }

}
