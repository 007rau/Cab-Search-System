package org.codejudge.sb.response;

public class DriverResponse extends Response {
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String license_number;
    private String car_number;

    public DriverResponse() {
    }

    public DriverResponse(int id, String name, String email, String phone_number, String license_number, String car_number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.license_number = license_number;
        this.car_number = car_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }
}
