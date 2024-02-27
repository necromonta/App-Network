package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author anagy
 */
public class Network {

    static ArrayList<StaticConfig> networks1 = new ArrayList();
    static ArrayList<DynamicConfig> networks2 = new ArrayList();
    static ArrayList<Ssh> networks3 = new ArrayList();

    public static void main(String[] args) {

        userInput();

    }

    public static void userInput() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        do {
            menu();
            userInput = sc.nextLine();
            switch (userInput) {
                case "1":
                    StaticIp();
                    break;
                case "2":
                    DhcpIp();
                    break;
                case "3":
                    OSPFv2();
                    break;
                case "4":
                    ssh();
                    break;
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":
                    networks();
                    break;

                case "9":
                    System.out.println("Disconnecting...");
                    break;
                default:
                    throw new AssertionError();
            }
        } while (!userInput.equals("4"));

    }

    public static void menu() {
        System.out.println("1.Static");
        System.out.println("2.Dhcp");
        System.out.println("3.OSPFv2");
        System.out.println("4.Ssh");
        System.out.println("8.Networks");
        System.out.println("9.Exit");
    }

    public static void StaticIp()   {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
        System.out.println("1.Network ip (format: 111.111.111.111/24)");
        System.out.println("2.Number of devices");
        System.out.println("3.Number of excluded devices");
        networks1.add(new StaticConfig(sc.nextLine(), sc.nextLine(), sc.nextLine()));
        networks1.get(networks1.size() - 1).results();
    }

    public static void DhcpIp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
        System.out.println("1.Network ip: (format: 192.168.10.0/25)");
        System.out.println("2.Pool name: ");
        System.out.println("3.Dns Server ip: ");
        System.out.println("4.Number of excluded addresses: ");
        networks2.add(new DynamicConfig(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine()));
        networks2.get(networks2.size() - 1).results();
    }

    public static void telnet(){
        
    }
    
    public static void ftp(){
        
        
    }
    
    public static void dns(){
         
     }
     
    public static void ssh() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------");
         System.out.println("1.Hostname: ");
          System.out.println("2.Domain name: ");
        System.out.println("1.Modulus size: ");    
        System.out.println("4.Timeout: ");
        System.out.println("5.Retries: ");
        System.out.println("6.LinePrev: ");
        System.out.println("7.ssh2 (true/false)");
        System.out.println("8.Password: ");
        networks3.add(new Ssh(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine()));
        networks3.get(networks3.size() - 1).results();
        
    }

    public static void OSPFv2() {

    }

    public static void networks() {
        System.out.println("------------------");
        System.out.println("Static networks");
        for (StaticConfig temp : networks1) {
            System.out.println(temp);
        }
        System.out.println("Dynamic networks");
        for (DynamicConfig temp : networks2) {
            System.out.println(temp);
        }
        System.out.println("SSH networks");
        for (Ssh temp : networks3) {
            System.out.println(temp);
        }
    }

}
