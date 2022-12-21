package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String userId;
    private String streetName;
    private String streetNo;
    private String postalCode;
    private String city;
    private String country;
    private String userName;
    private String password;
    private USER_ROLES user_role;
    private ArrayList<PurchasedItem> purchasedItemList;

    public enum USER_ROLES {CUSTOMER,ADMIN,MANAGER};

    public User(String name, String userId, String streetName, String streetNo, String postalCode, String city, String country, String userName, String password, USER_ROLES user_role) {
        this.name = name;
        this.userId = userId;
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.userName = userName;
        this.password = password;
        this.user_role = user_role;
        this.purchasedItemList = new ArrayList<PurchasedItem>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public USER_ROLES getUser_role() {
        return user_role;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public boolean addPurchasedItem(Item item){
        PurchasedItem purchasedItem = new PurchasedItem(item.getName(), item.getPrice(), 1);
        this.purchasedItemList.add(purchasedItem);
        return  true;
    }

    public ArrayList<PurchasedItem> getPurchasedItemList() {
        return purchasedItemList;
    }

    public String getUserId() {
        return userId;
    }

    public void setPurchasedItemList(ArrayList<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
    }
}
