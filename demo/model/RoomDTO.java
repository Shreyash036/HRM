package com.example.demo.model;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomDTO {
    private Long id;

    @NotBlank
    private String type;

    @NotBlank
    private String status;

    @NotNull
    private Long ownerId;

    @NotNull
    private Set<Long> customerIds;

    // Constructor
    public RoomDTO() {
        super();
    }

    public RoomDTO(Long id, @NotBlank String type, @NotBlank String status, @NotNull Long ownerId, @NotNull Set<Long> customerIds) {
        super();
        this.id = id;
        this.type = type;
        this.status = status;
        this.ownerId = ownerId;
        this.customerIds = customerIds;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(Set<Long> customerIds) {
        this.customerIds = customerIds;
    }

    @Override
    public String toString() {
        return "RoomDTO [id=" + id + ", type=" + type + ", status=" + status + ", ownerId=" + ownerId + ", customerIds=" + customerIds + "]";
    }
}
