package network;

import java.util.HashMap;

/**
 *
 * @author anagy
 */
public class DynamicConfig {

    String networkIp;
    String dnsIp;
    String name;
    String ip;
    int excluded;
    int index;

    public DynamicConfig(String networkIp, String name, String dnsIp, String excluded) {
        this.networkIp = networkIp;
        this.dnsIp = dnsIp;
        this.name = name;
        this.excluded = Integer.valueOf(excluded);
        ip = networkIp.split("/")[0];
        this.index=calculatesIndex();
    }

    private String calculatesMask() {

        String mask = String.valueOf(networkIp.split("/")[1]);
        HashMap<String, String> masks = new HashMap<>();
        masks.put("16", "255.255.0.0");
        masks.put("17", "255.255.128.0");
        masks.put("18", "255.255.192.0");
        masks.put("19", "255.255.224.0");
        masks.put("20", "255.255.240.0");
        masks.put("21", "255.255.248.0");
        masks.put("22", "255.255.252.0");
        masks.put("23", "255.255.254.0");
        masks.put("24", "255.255.255.0");
        masks.put("25", "255.255.255.128");
        masks.put("26", "255.255.255.192");
        masks.put("27", "255.255.255.224");
        masks.put("28", "255.255.255.240");
        masks.put("29", "255.255.255.248");
        masks.put("30", "255.255.255.252");

        if (masks.get(mask) == null) {
            return "Wrong mask";
        }
        return masks.get(mask);

    }

    private int calculatesIndex() {
        if (Integer.valueOf(networkIp.split("/")[1]) < 24) {
            return 2;

        } else {
            return 3;
        }
    }

    private String ipCalculator(int number){
        String temp[] = ip.replace('.', ',').split(",");
        temp[index] = String.valueOf(Integer.valueOf(temp[index]) + number);
        return temp[0] + "." + temp[1] + "." + temp[2] + "." + temp[3];
    }

    public void results() {
        System.out.println("------------------");
        if (excluded > 0) {
            System.out.println("ip dhcp exlcuded-address " + ipCalculator(2) + " " + ipCalculator(1+excluded));
        }
        System.out.println("ip dhcp pool " + name);
        System.out.println("network " + ip + " " + calculatesMask());
        System.out.println("default-router " + ipCalculator(1));
        System.out.println("dns-server " + dnsIp);
        System.out.println("------------------");
    }
}
