package net.automobile;

public enum ISP {
    FPT("fpt"),
    VNPT("vnpt");

    private String name;

    ISP(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUDID(){
        return DevicesManager.load("android-isp.json").getISPByKey(getName().toUpperCase()).get("udid");
    }

    public String getDeviceName() {
        return DevicesManager.load("android-isp.json").getISPByKey(getName().toUpperCase()).get("deviceName");
    }

    //DevicesManager.load("android-isp.json").getAllISP().keySet().forEach(a-> System.out.println(a));
    //DevicesManager.load("android-isp.json").getAllISP().get(ISP.FPT.name().toUpperCase()).get("actor")


}