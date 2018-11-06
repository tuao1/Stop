package com.example.tuao.stop.bean;

public class CarManagerBean {
    // 车牌号
    private String carId;
    // 是否支持 无感支付
    private boolean isSupported;

    public CarManagerBean(String carId, boolean isSupported) {
        this.carId = carId;
        this.isSupported = isSupported;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }
}
