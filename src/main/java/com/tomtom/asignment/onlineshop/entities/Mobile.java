package com.tomtom.asignment.onlineshop.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Mobile extends Product {
    private float screenSize;
    private boolean touchScreen;
    private boolean turbocharge;
    private double ram;
    private double rom;
    private float cameraPixels;

}
