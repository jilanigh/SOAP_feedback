package com.feedback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "averageRating"
})
@XmlRootElement(name = "getAverageRatingResponse", namespace = "http://feedback.com/service")
public class GetAverageRatingResponse {
    
    @XmlElement(name = "averageRating", namespace = "http://feedback.com/service", required = true)
    private Double averageRating;

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
} 