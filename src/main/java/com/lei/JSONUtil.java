package com.lei;

import java.util.List;

/**
 * @author lei
 * @create 2022-07-06-6:24 PM
 */
public class JSONUtil {


    public static String messageToJSON(Library.Message message) {


        List<Library.SDKError> errors = message.errorMsgs;
        List<Library.LocationEvent> events = message.payload;


        String res = "[";
        res += "\n";
        res += "{";
        res += "\n";

        if (null != errors && errors.size() != 0) {

            res += '"' + "errorMsgs" + '"' + ": ";
            res += "[";

            for (int i = 0; i < errors.size(); i++) {

                if (i != errors.size() - 1) {
                    res += errorToJSON(errors.get(i));
                    res += ",";
                    res += "\n";
                } else {
                    res += errorToJSON(errors.get(i));
                }


            }

            res += "]";
            res += ",";
            res += "\n";
        }

        if (null != events && events.size() != 0) {
            res += '"' + "payload" + '"' + ": ";
            res += "[";

            for (int i = 0; i < events.size(); i++) {
                if (i != events.size() - 1) {
                    res += eventToJSON(events.get(i));
                    res += ",";
                    res += "\n";
                } else {
                    res += eventToJSON(events.get(i));
                }

            }

            res += "]";
            res += "\n";
        }


        res += "}";
        res += "\n";
        res += "]";

        return res;
    }


    private static String eventToJSON(Library.LocationEvent event) {

        String res = "{";
        res += "\n";
        res += '"' + "lat" + '"' + ": " + '"' + event.lat.toString() + '"' + ',';
        res += "\n";
        res += '"' + "lon" + '"' + ": " + '"' + event.lon.toString() + '"' + ',';
        res += "\n";
        res += '"' + "time" + '"' + ": " + '"' + event.time.toString() + '"' + ',';
        res += "\n";
        res += '"' + "ext" + '"' + ": " + '"' + event.ext + '"';
        res += "\n";

        res += "}";

        return res;

    }


    private static String errorToJSON(Library.SDKError error) {

        String res = "{";
        res += "\n";
        res += '"' + "type" + '"' + ": " + '"' + error.type + '"';
        res += "\n";
        res += "}";

        return res;

    }
}
