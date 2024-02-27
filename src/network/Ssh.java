/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anagy
 */
public class Ssh {
    int modulusSize;
    int timeout;
    int retries;
    int linePriv;
    ArrayList<Users> users=new ArrayList();
    String domainName;
    boolean ssh2;
    String pw;
    String hostname;

    public Ssh(String hostname,String domainName,String modulusSize, String timeout,String retries, String linePriv, String ssh2,String pw) {
        this.modulusSize = Integer.valueOf(modulusSize);
        this.timeout = Integer.valueOf(timeout);
        this.retries = Integer.valueOf(retries);
        this.linePriv = Integer.valueOf(linePriv);
        this.domainName = domainName;
        this.ssh2 = Boolean.valueOf(ssh2);
        this.hostname=hostname;
        this.pw=pw;
        users();
    }
    
    void users(){
        Scanner sc=new Scanner(System.in);
        String userInput;
       
        do {
            System.out.println("Do you wanna add users? (yes/no)");
           userInput=sc.nextLine();
            if (userInput.equals("yes")) {
                System.out.println("Username: ");
                System.out.println("Password: ");
                System.out.println("Level: ");
                users.add(new Users(sc.nextLine(),sc.nextLine(),sc.nextLine()));
            }
        } while (!userInput.equals("no"));
    } 
    
    void results(){
        
        System.out.println("------------------");
        System.out.println("hostname "+hostname);
        System.out.println("ip domain-name "+domainName);
        System.out.println("crypto key generate rsa general-keys modules "+modulusSize);
        System.out.println("enable password "+pw);
         if (ssh2) {
            System.out.println("ip ssh version 2"); 
        }
        System.out.println("ip ssh time-out "+timeout);
        System.out.println("ip authentication-retries "+retries);
        for (int i = 0; i < users.size(); i++) {
            System.out.println("username "+users.get(i).name+" privilege "+users.get(i).lvl+" password "+users.get(i).pw);
        }
        
       
        System.out.println("line vty 0 15");
        System.out.println("transport input ssh");
        System.out.println("login local");
    }
}
