package JVFCVue;

import java.util.List;
import java.util.ArrayList;

import Enumeration.Etat;
import Enumeration.SpecialZone;
import IleInterdite.Position;
import IleInterdite.Zone;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class ObservableListDemo {

    public static void main(String[] args) {

        List<Zone> list = new ArrayList<Zone>();


        ObservableList<Zone> observableList = FXCollections.observableList(list);

        observableList.addListener(new ListChangeListener() {


            @Override
            public void onChanged(Change change) {

                System.out.println("Detected a change! ");

                while (change.next()) {

                    System.out.println("Was added? " + change.wasAdded());

                    System.out.println("Was removed? " + change.wasRemoved());

                }

            }

        });
        observableList.add(new Zone(Etat.normale, new Position(0,0), SpecialZone.none));

        System.out.println("Size: " + observableList.size()+observableList.toString());



        list.add(new Zone(Etat.none, new Position(0,0), SpecialZone.none));

        System.out.println("Size: " + observableList.size()+observableList.toString());

        observableList.add(new Zone(Etat.normale, new Position(0,0), SpecialZone.none));

        System.out.println("Size: " + observableList.size()+observableList.toString());



        list.add(new Zone(Etat.submergee, new Position(0,0), SpecialZone.none));

        System.out.println("Size: " + observableList.size()+observableList.toString());



        observableList.remove(1);

        System.out.println("Size: " + observableList.size()+observableList.toString());



        //observableList.sort(null);

        System.out.println("Size: " + observableList.size()+observableList.toString());



        observableList.set(2, new Zone(Etat.submergee, new Position(0,0), SpecialZone.none));

        System.out.println("Size: " + observableList.size()+observableList.toString());
        System.out.println("FIN: \n\n\n");


       /* IntegerBinding age1 = observableList.get(0).age;
        age1.addListener(new ChangeListener() {
             @Override
             public void changed(ObservableValue observableValue, Object o, Object t1) {
                 System.out.println("oldValue:"+ o + ", newValue = " + t1);

             }


         });*/

        //observableList.get(0).age.bind(observableList);

        //observableList.get(0).age.set(10);

        //System.out.println("observableList:"+ observableList.get(0).age + ", age1 = " + age1);


    }

}