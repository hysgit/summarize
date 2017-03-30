package com.wolsx.summarize.other.json.test;

import java.util.Map;

/**
 * Created by hy
 * Date: 16-4-25.
 */
public class ParamDesc {

    private String type;
    private boolean requested;
    private Map<String, ParamDesc> fields;


    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, ParamDesc> getFields() {
        return fields;
    }

    public void setFields(Map<String, ParamDesc> fields) {
        this.fields = fields;
    }
}
