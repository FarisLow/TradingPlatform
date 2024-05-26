package org.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Map;

public class CurrencyResponse implements Serializable {

    private Map<String, Currencies> data;

    public Map<String, Currencies> getData() {
        return data;
    }

    public void setData(Map<String, Currencies> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CurrencyResponse{" +
                "data=" + data +
                '}';
    }
}
