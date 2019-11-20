package Usuarios;

import java.io.*;
public class Cript {

    
    public static String  Cript(String T){
        String TC ="";
        String Alfa = "abcdefghijklmnñopqrstuvwxyz=% @";
        String pal = T;
        int []t = new int[T.length()];// se almacenan los datos numericos de la palabra a encriptar
        int [] pa = new int [T.length()]; // Se almacenan los datos numericos de la palabra clave
        int [] cri = new int [T.length()]; // se almacenan los datos numericos de la palabra encriptada
        for (int i = 0 ; i < t.length; i ++){
            t[i] = Alfa.indexOf(T.substring(i, i+1));
            pa[i] = Alfa.indexOf(pal.substring(i%pal.length(),(i%pal.length())+1));
            cri[i] = (t[i]+ pa[i]) % Alfa.length();
            TC = TC + Alfa.substring(cri[i], cri[i]+1);
        }
        return TC;
    }
    public static String deCript(String T, String pal){ //T texto a descrincrip, pal palabra clave pass
        String Dc = "";
        String Alfa = "abcdefghijklmnñopqrstuvwxyz=% @";
        int []t = new int[T.length()];// se almacenan los datos numericos de la palabra  encriptada
        int [] pa = new int [T.length()]; // Se almacenan los datos numericos de la palabra clave
        int [] cri = new int [T.length()]; // se almacenan los datos numericos de la palabra desencriptada
        int n;
        for (int i = 0 ; i < t.length; i ++){
            t[i] = Alfa.indexOf(T.substring(i, i+1));
            pa[i] = Alfa.indexOf(pal.substring(i%pal.length(),(i%pal.length())+1));
            n = (t[i]- pa[i]);
            if (n< 0 ){n = Alfa.length()+n;}
            cri[i] = n% Alfa.length();
            Dc = Dc + Alfa.substring(cri[i], cri[i]+1);
        }
        return Dc;
    }
}