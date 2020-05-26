package JVFCVue;

import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class ObservableListDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {

                System.out.println("Detected a change! ");

                while (change.next()) {

                    System.out.println("Was added? " + change.wasAdded());

                    System.out.println("Was removed? " + change.wasRemoved());

                }

            }

        });
        observableList.add("a : item one");

        System.out.println("Size: " + observableList.size()+observableList.toString());



        list.add("d : item two");

        System.out.println("Size: " + observableList.size()+observableList.toString());

        observableList.add("f : item Three");

        System.out.println("Size: " + observableList.size()+observableList.toString());



        list.add("b : item four");

        System.out.println("Size: " + observableList.size()+observableList.toString());



        observableList.remove(1);

        System.out.println("Size: " + observableList.size()+observableList.toString());



        observableList.sort(null);

        System.out.println("Size: " + observableList.size()+observableList.toString());



        observableList.set(2, "c : item five");

        System.out.println("Size: " + observableList.size()+observableList.toString());


    }

}