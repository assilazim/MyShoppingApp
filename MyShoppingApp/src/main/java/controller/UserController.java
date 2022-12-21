package controller;


import CommonUtils.FileDatabaseManager;
import model.Item;
import model.Manager;
import model.User;
import view.AdminScreen;
import view.CustomerScreen;
import view.ManagerScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {
    private List<User> userArrayList;
    private List<Manager> managerArrayList;
    private StoreController storeController;

    public UserController() {
        this.initialize();
    }

    private void initialize(){
        this.storeController = new StoreController();
        this.userArrayList = FileDatabaseManager.readUsersFromFile();
        this.managerArrayList = FileDatabaseManager.readManagersFromFile();
        if(userArrayList.isEmpty()){
            //add initial admin user
            User admin = new User("Administrator","ADMIN-001",null,null,null,null,null,"admin","admin123", User.USER_ROLES.ADMIN);
            this.userArrayList.add(admin);
            FileDatabaseManager.writeUsersToFile(userArrayList);
        }
        if(managerArrayList.isEmpty()){
            //add initial Manager user and some managers
            Manager manager1 = new Manager("Manager_1","MANAGER-001",null,null,null,null,null,"manager1","manager123", User.USER_ROLES.MANAGER); 
            Manager manager2 = new Manager("Manager_2","MANAGER-002",null,null,null,null,null,"manager2","manager123", User.USER_ROLES.MANAGER);
            Manager manager3 = new Manager("Manager_3","MANAGER-003",null,null,null,null,null,"manager3","manager123", User.USER_ROLES.MANAGER);
            this.managerArrayList.add(manager1);
            this.managerArrayList.add(manager2);
            this.managerArrayList.add(manager3);
            FileDatabaseManager.writeManagersToFile(managerArrayList);
        }
    }

    public boolean login(String userName, String password){
        User loggedUser = this.userArrayList.stream().filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password)).findFirst().orElse(null);
        Manager manager = null;
        if(loggedUser == null){
            manager = this.managerArrayList.stream().filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password)).findFirst().orElse(null);
        }

        if(loggedUser != null ) {
            System.out.println("logged user name : " + loggedUser.getName());
            if (loggedUser.getUser_role().equals(User.USER_ROLES.ADMIN)) {
                AdminScreen adminScreen = new AdminScreen();
                adminScreen.getFrame().setVisible(true);
            } else if (loggedUser.getUser_role().equals(User.USER_ROLES.CUSTOMER)) {
                CustomerScreen customerScreen = new CustomerScreen(loggedUser.getUserId());
                customerScreen.getFrame().setVisible(true);

            }
            return true;
        }
        else if(manager != null){
                ManagerScreen managerScreen = new ManagerScreen(manager.getStoreCode());
                managerScreen.getFrame().setVisible(true);
                return true;
            }
        return false;
    }

    public boolean registerCustomer(String name, String streetName, String streetNo, String postalCode,
                                    String city, String country, String userName, String password, User user){
        if(!name.isEmpty() && !streetName.isEmpty() && !streetNo.isEmpty() && !postalCode.isEmpty()
                && !city.isEmpty() && !country.isEmpty() && !userName.isEmpty() && !password.isEmpty()){

            if(user != null){
                this.userArrayList.remove(user);
                User newCustomer = new User( name,  user.getUserId(),  streetName,  streetNo,  postalCode,  city,  country,  userName,  password, User.USER_ROLES.CUSTOMER);
                this.userArrayList.add(newCustomer);
            }
            else{
                String uniqueID = UUID.randomUUID().toString();
                User newCustomer = new User( name,  uniqueID,  streetName,  streetNo,  postalCode,  city,  country,  userName,  password, User.USER_ROLES.CUSTOMER);
                this.userArrayList.add(newCustomer);
            }
            return true;
        }
        return false;
    }

    public boolean registerManager(String name, String streetName, String streetNo, String postalCode,
                                    String city, String country, String userName, String password, Manager manager){
        if(!name.isEmpty() && !streetName.isEmpty() && !streetNo.isEmpty() && !postalCode.isEmpty()
                && !city.isEmpty() && !country.isEmpty() && !userName.isEmpty() && !password.isEmpty()){


            if(manager != null){
                managerArrayList.remove(manager);

                Manager newManager = new Manager( name, manager.getUserId(),  streetName,  streetNo,  postalCode,  city,  country,  userName,  password, User.USER_ROLES.CUSTOMER);
                this.managerArrayList.add(newManager);
            }
            else {
                String uniqueID = UUID.randomUUID().toString();
                Manager newManager = new Manager( name,  uniqueID,  streetName,  streetNo,  postalCode,  city,  country,  userName,  password, User.USER_ROLES.CUSTOMER);
                this.managerArrayList.add(newManager);
            }
            return true;
        }
        return false;
    }

    public boolean removeManager(int index){
        if(index <= managerArrayList.size()){
            this.managerArrayList.remove(index);
            return true;
        }
        else  {
            return false;
        }
    }

    public boolean addStoreToManager(int managerIndex,String storeCode){
        if(managerArrayList.size() > managerIndex && storeCode != null){
            managerArrayList.get(managerIndex).setStoreCode(storeCode);
            return true;
        }
        return false;
    }

    public boolean removeStoreFromManager(int managerIndex){
        if(managerArrayList.size() > managerIndex){
            if(managerArrayList.get(managerIndex).getStoreCode() != null) {
                managerArrayList.get(managerIndex).setStoreCode(null);
                return true;
            }
        }
        return false;
    }

    public boolean addItemToLoggedUserList(String userId,Item item){
        User user = userArrayList.stream().filter(user1 -> user1.getUserId().equals(userId)).findFirst().orElse(null);
        if(user != null){
            user.addPurchasedItem(item);
            userArrayList = userArrayList.stream().map(user1 -> {
                if(user1.getUserId().equals(userId)){
                    user1.setPurchasedItemList(user.getPurchasedItemList());
                    return user1;
                }
                else {
                    return user1;
                }
            }).toList();
            return true;
        }
        else {
            return false;
        }
    }

    public List<Manager> getManagerList(){
        return this.managerArrayList;
    }

    public User getUserById(String userId){
        return userArrayList.stream().filter(user1 -> user1.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public boolean deleteUserById(String userId){
        userArrayList = userArrayList.stream().filter(user1 -> !user1.getUserId().equals(userId)).toList();
        return true;
    }

    public boolean updateUserList(){
        FileDatabaseManager.writeUsersToFile(userArrayList);
        return true;
    }

    public boolean updateManagerList(){
        FileDatabaseManager.writeManagersToFile(managerArrayList);
        return true;
    }

    public List<User> getUserArrayList() {
        return userArrayList;
    }
}
