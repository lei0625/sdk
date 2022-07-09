package com.lei;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lei
 * @create 2022-07-06-12:35 PM
 */
public class Library {

    String testAPI;

    List<LocationEvent> payload = new ArrayList<LocationEvent>();

    LocationEvent locationEvent = new LocationEvent();

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public CloseableHttpResponse response;

    Integer placeScope;

    public Message message = new Message();

    Set<String> errorMsgs = new HashSet<>();

    public Library(String testAPI) {
        this.testAPI = testAPI;
        this.placeScope = -1;
    }


    public Library(String testAPI, Integer placeScope) {
        this.testAPI = testAPI;
        this.placeScope = placeScope;
    }

    public class LocationEvent {
        public Float lat;
        public Float lon;
        public Long time;
        public String ext;

        public LocationEvent(LocationEvent locationEvent) {
            this.lat = locationEvent.lat;
            this.lon = locationEvent.lon;
            this.time = locationEvent.time;
            this.ext = locationEvent.ext;
        }

        public LocationEvent() {
        }

        @Override
        public String toString() {
            return "LocationEvent{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    ", time=" + time +
                    ", ext='" + ext + '\'' +
                    '}';
        }
    }

    public class SDKError {
        public String type;

        public SDKError(String type) {
            this.type = type;
        }
    }

    public class Message {

        public List<SDKError> errorMsgs;

        public List<LocationEvent> payload;

        public List<SDKError> getErrorMsgs() {
            return errorMsgs;
        }

        public List<LocationEvent> getPayload() {
            return payload;
        }

    }


    public boolean setUp() {

        if (this.payload.size() == 0) throw new RuntimeException("No valid params appear");

//        if (this.payload.size() == 0 && this.errorMsgs.size() == 0) {
//
//            return false;
//        }

        for (LocationEvent locationEvent : this.payload) {

            Float curLat = locationEvent.lat;
            Float curLon = locationEvent.lon;
            Long curTime = locationEvent.time;
            String curExt = locationEvent.ext;
            if (null == curLat && null == curLon && null == curTime && null == curExt && this.errorMsgs.size() == 0)
                return false;
            if (null == curLat) {
                this.errorMsgs.add(ErrorType.LAT_ABSENT.toString());
                locationEvent.lat = -99999f;
            }
            if (null == curLon) {
                this.errorMsgs.add(ErrorType.LON_ABSENT.toString());
                locationEvent.lon = -99999f;
            }
            if (null == curTime) {
                this.errorMsgs.add(ErrorType.TIME_ABSENT.toString());
                locationEvent.time = System.currentTimeMillis();
            }
            if (null == curExt) {
//                this.errorMsgs.add(ErrorType.EXT_ABSENT.toString());
                locationEvent.ext = "PLACE_HOLDER_STR";
            }

        }


        return true;
    }


    public Library fillParameters(Object o) {

        if (null == o) {
            this.errorMsgs.add(ErrorType.NULL_INPUT.toString());
            return this;
        }

        if (!(o instanceof Float) && !(o instanceof String) && !(o instanceof Long)) {
            this.errorMsgs.add(ErrorType.WRONG_TYPE.toString());
            return this;
        }

        if (o instanceof Float) {
            if (this.locationEvent.lat == null) {
                this.locationEvent.lat = modPrecision((Float) o);
                return this;
            } else if (this.locationEvent.lon == null) {
                this.locationEvent.lon = modPrecision((Float) o);
                return this;
            } else {
                this.errorMsgs.add(ErrorType.WRONG_TYPE.toString());
                return this;
            }
        } else if (o instanceof Long) {

            this.locationEvent.time = (Long) o;

            return this;

        }

        this.locationEvent.ext = (String) o;
        this.payload.add(new LocationEvent(locationEvent));

        clean();
        return this;
    }


    public void log() {

        if (!setUp()) {
            throw new RuntimeException("You must invoke fillParamters method before calling this method !");
        }

        fillErrors();

        this.message.payload = this.payload;

        HttpPost httpPost = new HttpPost(testAPI);
        httpPost.addHeader("Content-Type", "application/json");

        String json = JSONUtil.messageToJSON(this.message);

        System.out.println(json);

        try {
            httpPost.setEntity(new StringEntity(json));
            response = httpClient.execute(httpPost);
            if (null != response && response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Server side error !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clean() {
        locationEvent.lon = null;
        locationEvent.lat = null;
        locationEvent.time = null;
        locationEvent.ext = null;
    }

    private Float modPrecision(Float f) {

        if (this.placeScope < 0) {
            return 0.0f;
        }

        BigDecimal bd = new BigDecimal(f);
        BigDecimal res = bd.setScale(this.placeScope, RoundingMode.HALF_UP);
        return res.floatValue();
    }

    private void fillErrors() {

        this.message.errorMsgs = new ArrayList<SDKError>();

        for (String errorMsg : this.errorMsgs) {
            this.message.errorMsgs.add(new SDKError(errorMsg));
        }


    }

    public enum ErrorType {
        NULL_INPUT,
        WRONG_TYPE,
        LAT_ABSENT,
        LON_ABSENT,
        TIME_ABSENT
    }
}
