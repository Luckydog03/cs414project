package com.tco.requests;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private String serverName;
    private List<String> features;

    @Override
    public void buildResponse() {
        serverName = "t57 Dungeons and Debuggers";
        features = new ArrayList<>();
        features.add("config");
        features.add("login");
        features.add("register");
        features.add("sendinvite");
        features.add("acceptinvite");
        features.add("rejectinvite");
        features.add("readinvite");
        features.add("readmatch");
        features.add("readuser");
        log.trace("buildResponse -> {}", this);
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public ConfigRequest() {
        this.requestType = "config";
    }

    public String getServerName() {
        return serverName;
    }

    public boolean validFeature(String feature){
        return features.contains(feature);
    }
}
