/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.mycompany.myapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Simple DTO for Room.
 */
public class RoomDTO {
    private static final Logger log = Logger.getLogger(RoomDTO.class.getName());
    public Integer id;
    public Integer roomNumber;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDTO roomDTO = (RoomDTO) o;
        return Objects.equals(id, roomDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RoomDTO roomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
}