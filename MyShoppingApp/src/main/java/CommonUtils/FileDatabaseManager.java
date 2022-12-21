package CommonUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Manager;
import model.Store;
import model.User;

public class FileDatabaseManager {
    public static boolean writeUsersToFile(List<User> userArrayList){
        try (FileOutputStream fos = new FileOutputStream("Users.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // write object to file
            oos.writeObject(userArrayList);
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<User> readUsersFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("Users.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            List<User> userList = (List<User>)obj;

            System.out.println("The Object has been read from the Users.txt file");
            objectIn.close();
            
            ArrayList<User> userArrayList = new ArrayList<>();
            userList.forEach(user -> userArrayList.add(user));
            return userArrayList;

        } catch (Exception ex) {
            System.out.println("Users.txt is initiated");
            return new ArrayList<>();
        }
    }

    public static boolean writeStoresToFile(List<Store> storeArrayList){
        try (FileOutputStream fos = new FileOutputStream("Stores.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // write object to file
            oos.writeObject(storeArrayList);
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Store> readStoresFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("Stores.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            List<Store> storeList = (List<Store>)obj;

            System.out.println("The Object has been read from the Stores.txt file");
            objectIn.close();
            return storeList;

        } catch (Exception ex) {
            System.out.println("Stores.txt Read Error");
            return new ArrayList<>();
        }
    }

    public static boolean writeManagersToFile(List<Manager> userArrayList){
        try (FileOutputStream fos = new FileOutputStream("Managers.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // write object to file
            oos.writeObject(userArrayList);
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Manager> readManagersFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("Managers.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            List<Manager> userArrayList = (List<Manager>)obj;

            System.out.println("The Object has been read from the Managers.txt file");
            objectIn.close();
            return userArrayList;

        } catch (Exception ex) {
            System.out.println("Managers.txt is initiated");
            return new ArrayList<>();
        }
    }
}
