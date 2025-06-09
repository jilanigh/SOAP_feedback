package com.feedback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "feedback", namespace = "http://feedback.com/service", propOrder = {
    "id",
    "userId",
    "productId",
    "rating",
    "comment",
    "createdAt"
})
public class Feedback {
    
    @XmlElement(name = "id", namespace = "http://feedback.com/service", required = true)
    private Long id;
    
    @XmlElement(name = "userId", namespace = "http://feedback.com/service", required = true)
    private Long userId;
    
    @XmlElement(name = "productId", namespace = "http://feedback.com/service", required = true)
    private Long productId;
    
    @XmlElement(name = "rating", namespace = "http://feedback.com/service", required = true)
    private Integer rating;
    
    @XmlElement(name = "comment", namespace = "http://feedback.com/service", required = false)
    private String comment;
    
    @XmlElement(name = "createdAt", namespace = "http://feedback.com/service", required = true)
    private Date createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
} 