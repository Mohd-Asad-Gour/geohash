package com.app.geohash.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrixResponse {

    @JsonProperty("rows")
    private Row[] rows;

    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {

        @JsonProperty("elements")
        private Element[] elements;

        public Element[] getElements() {
            return elements;
        }

        public void setElements(Element[] elements) {
            this.elements = elements;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Element {

        @JsonProperty("status")
        private String status;

        @JsonProperty("duration")
        private Duration duration;

        @JsonProperty("distance")
        private Distance distance;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Duration {
        @JsonProperty("text")
        private String text;

        @JsonProperty("value")
        private int value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Distance {
        @JsonProperty("text")
        private String text;

        @JsonProperty("value")
        private int value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

