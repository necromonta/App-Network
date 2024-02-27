package network;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author anagy
 */
public class StaticConfig {

    String ip;
    String networkIp;
    String routerIp;
    String mask;
    ArrayList<String> deviceIps;
    int maskNumber;
    int devices;
    int excluded;
    int index;

    public StaticConfig(String networkIp, String devices, String excluded) {
        this.networkIp = networkIp;
        this.devices = Integer.valueOf(devices);
        this.excluded = Integer.valueOf(excluded);

        this.ip = networkIp.split("/")[0];
        this.maskNumber = Integer.valueOf(networkIp.split("/")[1]);

        this.index = calculatesIndex();
        this.routerIp = calculatesRouterIp();
        this.mask = calculatesMask();
        this.deviceIps = caculatesDevicesIp();
    }

    private int calculatesIndex() {
        if (maskNumber < 24) {
            return 2;

        } else {
            return 3;
        }
    }

    private String calculatesRouterIp() {

        String temp[] = ip.replace('.', ',').split(",");
        temp[index] = String.valueOf(Integer.valueOf(temp[index]) + 1);
        return temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3];

    }

    private String calculatesMask() {

        String mask = String.valueOf(maskNumber);
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

    private ArrayList<String> caculatesDevicesIp() {
        ArrayList<String> ips = new ArrayList();
        for (int i = 0; i < devices; i++) {
            String temp[] = ip.replace('.', ',').split(",");
            temp[index] = String.valueOf(Integer.valueOf(temp[index]) + 1 + excluded + i);
            ips.add(temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3]);
        }

        return ips;

    }

    public void results() {
        System.out.println("------------------");
        System.out.println("Router ip: " + routerIp.replace(',', '.'));
        System.out.println("Mask: " + mask);
        System.out.println("Devices: ");
        for (int i = 0; i < deviceIps.size(); i++) {
            System.out.println(" -pc" + i + ": " + deviceIps.get(i).replace(',', '.'));
        }
        System.out.println("------------------");
    }

}
