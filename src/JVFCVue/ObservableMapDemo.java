package JVFCVue;

import java.util.Map;
import java.util.HashMap;
import javafx.collections.ObservableMap;
import javafx.collections.MapChangeListener;
import javafx.collections.FXCollections;

public class ObservableMapDemo {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<String,String>();

        ObservableMap<String,String> observableMap = FXCollections.observableMap(map);
        observableMap.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                System.out.println("Detected a change! ");
            }
        });


// Changes to the observableMap WILL be reported.
        observableMap.put("key 1","value 1");
        System.out.println("Size: "+observableMap.size() + observableMap.toString());

// Changes to the underlying map will NOT be reported.
        map.put("key 2","value 2");
        System.out.println("Size: "+observableMap.size()+ observableMap.toString());
// Changes to the observableMap WILL be reported.
        observableMap.remove("key 1");
        System.out.println("Size: "+observableMap.size() + observableMap.toString());
    }
}
