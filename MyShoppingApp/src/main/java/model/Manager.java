package model;

public class Manager extends User{
    private String storeCode;

    public Manager(String name, String userId, String streetName, String streetNo, String postalCode, String city, String country, String userName, String password, USER_ROLES user_role) {
        super(name, userId, streetName, streetNo, postalCode, city, country, userName, password, user_role);
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
