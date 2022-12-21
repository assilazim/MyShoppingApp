package controller;

import CommonUtils.FileDatabaseManager;
import model.Item;
import model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreController {
    private List<Store> storeArrayList;

    public StoreController() {
        this.initialize();
    }

    private void initialize(){
        this.storeArrayList = FileDatabaseManager.readStoresFromFile();
        if(storeArrayList.isEmpty()){
            //add initial Store
            Store store1 = new Store("ST-001","Store 1","Yokshare","12","78500","Melburn","Australia",true,"6.00 am - 6.00pm");
            Store store2 = new Store("ST-002","Store 2","Pennsylvania Avenue NW","1600","20500","Washington","USA",true,"6.00 am - 6.00pm\"");
            Store store3 = new Store("ST-003","Store 3","FortStreet","12","78500","Tokyo","Japan",true,"6.00 am - 6.00pm\"");
            Store store4 = new Store("ST-004","Store 4", "King Street","627", "M5V 1M5", "Toronto", "Canada", true, "Open 24 Hours\"");
            Store store5 = new Store("ST-004", "Store 5", "Rruga E Deshmoreve", "69", "2021", "Durres", "Albania", true, "7.00am - 12.00am\"");
            this.storeArrayList.add(store1);
            this.storeArrayList.add(store2);
            this.storeArrayList.add(store3);
            this.storeArrayList.add(store4);
            this.storeArrayList.add(store5);
            FileDatabaseManager.writeStoresToFile(this.storeArrayList);
        }
    }

    public boolean addStore(Store store,Integer editStoreScreenIndex){
        if(editStoreScreenIndex == null){
            this.storeArrayList.add(store);
            return true;
        }
        else if(storeArrayList.size() > editStoreScreenIndex){
            this.storeArrayList.remove(editStoreScreenIndex.intValue());
            this.storeArrayList.add(store);
            return true;
        }
       return false;
    }

    public boolean removeStore(int index){

        if(index <= storeArrayList.size()){
            this.storeArrayList.remove(index);
            return true;
        }
        else  {
            return false;
        }
    }

    public Store getStoreByStoreCode(String storeCode){
        return this.storeArrayList.stream().filter(store -> store.getStoreCode().equals(storeCode)).findFirst().orElse(null);
    }

    public boolean addItemToStore(String storeCode, Item item){
        this.storeArrayList = this.storeArrayList.stream().map(store -> {
            if(store.getStoreCode().equals(storeCode)){
                store.addItem(item);
                return store;
            }
            return store;
        }).toList();
        return true;
    }

    public boolean removeItemFromStore(String storeCode, int itemIndex){
        this.storeArrayList = this.storeArrayList.stream().map(store -> {
            if(store.getStoreCode().equals(storeCode)){
                store.removeItem(itemIndex);
                return store;
            }
            return store;
        }).toList();
        return true;
    }

    public List<Store> getStoreArrayList() {
        return storeArrayList;
    }

    public List<Store> filterStoresList(String city, String country){
        if(city != null && country != null && !city.isEmpty() && !country.isEmpty()){
            return this.storeArrayList.stream().filter(store -> store.getCity().equalsIgnoreCase(city) && store.getCountry().equalsIgnoreCase(country)).toList();
        }
        else if(country != null && !country.isEmpty()){
            return this.storeArrayList.stream().filter(store -> store.getCountry().equalsIgnoreCase(country)).toList();
        }
        else if(city != null && !city.isEmpty()){
            return this.storeArrayList.stream().filter(store -> store.getCity().equalsIgnoreCase(city)).toList();
        }
        else if(city != null && country != null && city.isEmpty() && country.isEmpty()){
            return this.storeArrayList;
        }
        else if(city == null && country == null){
            return this.storeArrayList;
        }
        return new ArrayList<>();
    }

    public List<Item> filterStoreItems(String storeCode,String itemName){
        Store store = getStoreByStoreCode(storeCode);
        if(store != null) {
        	return store.getItemArrayList().stream().filter(item -> item.getName().equalsIgnoreCase(itemName)).toList();
        }
        else {
        	return new ArrayList<>();
        }
    }

    public String getValidStoreCode(){
    	int number = this.storeArrayList.size()+1;
        return "STR-"+ number;
    }

    public boolean updateStoreList(){
        FileDatabaseManager.writeStoresToFile(storeArrayList);
        return true;
    }
}
