package persistence;

import org.json.JSONObject;
/*
 * Represents writable interface that allow class to parse class object to Json objects
 */
public interface  Writable {
    //EFFECTS: returns this as Json object
    JSONObject toJson();
}
