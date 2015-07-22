package com.goree.api.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Place {
    private int id;
    private String name;
    private String address;
    private BigDecimal xCoordinate;
    private BigDecimal yCoordinate;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Place other = (Place) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (xCoordinate == null) {
            if (other.xCoordinate != null)
                return false;
        } else if (xCoordinate.compareTo(other.xCoordinate) != 0)
            return false;
        if (yCoordinate == null) {
            if (other.yCoordinate != null)
                return false;
        } else if (yCoordinate.compareTo(other.yCoordinate) != 0)
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((xCoordinate == null) ? 0 : xCoordinate.hashCode());
        result = prime * result + ((yCoordinate == null) ? 0 : yCoordinate.hashCode());
        return result;
    }
}
