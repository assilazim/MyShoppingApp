package controller;

import controller.StoreController;
import model.Item;
import model.Store;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreControllerTest {

    private StoreController storeController;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.storeController = new StoreController();
    }

    @org.junit.jupiter.api.Test
    void addStore() {
        Store store4 = new Store("ST-004","Store 4","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
        this.storeController.addStore(store4,null);
        assertEquals(6,storeController.getStoreArrayList().size());

        Store store5 = new Store("ST-005","Store 5","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
        this.storeController.addStore(store5,3);
        assertEquals(6,storeController.getStoreArrayList().size());
        
        assertEquals(false,this.storeController.addStore(store5,25));
    }

    @org.junit.jupiter.api.Test
    void removeStore() {

        assertEquals(false,this.storeController.removeStore(50));
        assertEquals(true,this.storeController.removeStore(0));
    }

    @org.junit.jupiter.api.Test
    void filterStoresTest() {
        List<Store> filteredList = storeController.filterStoresList("Melburn",null);
        assertEquals(1,filteredList.size());

        List<Store> filteredList1 = storeController.filterStoresList("Melburn","Tokyo");
        assertEquals(0,filteredList1.size());
     
        List<Store> filteredList2 = storeController.filterStoresList(null,null);
        assertEquals(5,filteredList2.size());

        List<Store> filteredList3 = storeController.filterStoresList("Melburn","Australia");
        assertEquals(1,filteredList3.size());
        
        List<Store> filteredList5 = storeController.filterStoresList(null,"Japan");
        assertEquals(1,filteredList5.size());
        
        List<Store> filteredList6 = storeController.filterStoresList("","");
        assertEquals(5,filteredList6.size());
        
        List<Store> filteredList7 = storeController.filterStoresList(null,"");
        assertEquals(0,filteredList7.size());

        List<Store> filteredList8 = storeController.filterStoresList("",null);
        assertEquals(0,filteredList8.size());



    }
    
    @org.junit.jupiter.api.Test
    void getStoreByStoreCode() {
        assertEquals(null,this.storeController.getStoreByStoreCode("ST-009"));
        Store store = this.storeController.getStoreByStoreCode("ST-002");
        assertEquals(store.getStoreCode(),"ST-002");
    }
    
    @org.junit.jupiter.api.Test
    void addRemoveItemToStore() {
    	Item item = new Item("Soap",20,100,"1","Clean",true);
        this.storeController.addItemToStore("ST-001",item);
        Store store = this.storeController.getStoreByStoreCode("ST-001");
        assertEquals(store.getItemArrayList().size(),1);
        
        this.storeController.removeItemFromStore("ST-001", 0);
        assertEquals(store.getItemArrayList().size(),0);
    }
    
    @org.junit.jupiter.api.Test
    void filterStoreItemsTest() {
    	Item item = new Item("Soap",20,100,"1","Clean",true);
        this.storeController.addItemToStore("ST-001",item);
        
        Item item2 = new Item("Bun",20,100,"1","Food",true);
        this.storeController.addItemToStore("ST-001",item2);
        
        List<Item> filteredItemList = storeController.filterStoreItems("ST-001","Soap");
        assertEquals(1,filteredItemList.size());
        
        List<Item> filteredItemList2 = storeController.filterStoreItems("ST-0012","Soap");
        assertEquals(0,filteredItemList2.size());

    }
    
    @org.junit.jupiter.api.Test
    void getValidStoreCodeTest() {
    	assertEquals("STR-6",storeController.getValidStoreCode());

    }
    



}