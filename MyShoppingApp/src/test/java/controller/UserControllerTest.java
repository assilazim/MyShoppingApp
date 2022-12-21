package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Item;
import model.Manager;
import model.Store;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        this.userController = new UserController();
    }

    @Test
    void login() {
        assertEquals(false,userController.login("Administrator","admin123"));
        assertEquals(true,userController.login("admin","admin123"));
        assertEquals(true,userController.login("manager1","manager123"));
        
        assertEquals(true,userController.registerCustomer("Customer_1","Bakers","12","1500","London",
                "England","customer1","abc123",null));
        assertEquals(true,userController.login("customer1","abc123"));
    }

    @Test
    void registerCustomer() {
        assertEquals(true,userController.registerCustomer("Customer_1","Bakers","12","1500","London",
                "England","customer1","abc123",null));
        assertEquals(false,userController.registerCustomer("","","","","London",
                "England","customer1","abc123",null));
        User user = userController.getUserArrayList().get(1);
        assertEquals("Customer_1",user.getName());

        assertEquals(true,userController.registerCustomer("Customer_1_modified","Bakers","12","1500","London",
                "England","customer1","abc123",user));
        User user2 = userController.getUserArrayList().get(1);
        assertEquals("Customer_1_modified",user2.getName());
    }

    @Test
    void registerManager() {
        assertEquals(true,userController.registerManager("Manager_4","Bakers","12","1500","London",
                "England","customer1","abc123",null));
        assertEquals(false,userController.registerManager("","","","","London",
                "England","customer1","abc123",null));
        Manager manager = userController.getManagerList().get(3);
        assertEquals("Manager_4",manager.getName());

        assertEquals(true,userController.registerManager("Manager_4_modified","Bakers","12","1500","London",
                "England","customer1","abc123",manager));
        Manager manager2 = userController.getManagerList().get(3);
        assertEquals("Manager_4_modified",manager2.getName());
    }

    @Test
    void removeManager() {
        assertEquals(false,userController.removeManager(10));
        assertEquals(true,userController.removeManager(1));
    }

    @Test
    void addStoreToManager() {
        Store store = new Store("ST-005","Store 5","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
        assertEquals(true,userController.addStoreToManager(0,store.getStoreCode()));
        assertEquals(false,userController.addStoreToManager(10,store.getStoreCode()));
        assertEquals(false,userController.addStoreToManager(0,null));
    }

    @Test
    void removeStoreFromManager() {
        Store store = new Store("ST-005","Store 5","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
        assertEquals(true,userController.addStoreToManager(0,store.getStoreCode()));
        assertEquals(true,userController.removeStoreFromManager(0));
        assertEquals(null,userController.getManagerList().get(0).getStoreCode());
        assertEquals(false,userController.removeStoreFromManager(10));
    }

    @Test
    void addItemToLoggedUserList() {
    	  Store store = new Store("ST-005","Store 5","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
          assertEquals(true,userController.addStoreToManager(0,store.getStoreCode()));
          
          assertEquals(true,userController.registerCustomer("Customer_1","Bakers","12","1500","London",
                  "England","customer1","abc123",null));
          
          assertEquals(true,userController.login("customer1","abc123"));
          Item item = new Item("Soap",20,100,"1","Clean",true);
          
          User loggedUser = userController.getUserArrayList().get(0);
          assertEquals(true,userController.addItemToLoggedUserList(loggedUser.getUserId(),item));
                 
    }

    @Test
    void getManagerList() {
    	  assertEquals(3,userController.getManagerList().size());
    	  userController.getManagerList().clear();
    	  assertEquals(0,userController.getManagerList().size());
    }

    @Test
    void getUserById() {
    	 assertEquals(true,userController.registerCustomer("Customer_1","Bakers","12","1500","London",
                 "England","customer1","abc123",null));
    	User realUser = userController.getUserArrayList().get(0);
    	User testUser = userController.getUserById(realUser.getUserId());
    	assertEquals(testUser.getUserId(), testUser.getUserId());
    }

    @Test
    void deleteUserById() {
    	 assertEquals(true,userController.registerCustomer("Customer_1","Bakers","12","1500","London",
                 "England","customer1","abc123",null));
    	User addedUser = userController.getUserArrayList().get(0);
    	assertEquals(userController.getUserArrayList().size(), 2);
    	userController.deleteUserById(addedUser.getUserId());
    	assertEquals(userController.getUserArrayList().size(), 1);
    }
}