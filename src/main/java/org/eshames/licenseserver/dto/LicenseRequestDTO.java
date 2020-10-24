package org.eshames.licenseserver.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LicenseRequestDTO {
    private String customerName;
    private String productName;
    private UUID hardwareId;
}
