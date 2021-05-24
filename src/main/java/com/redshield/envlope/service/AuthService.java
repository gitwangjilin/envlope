package com.redshield.envlope.service;

public interface AuthService {
    String getEntInfo(String idCardHash);

    String verifySn(String licenseSn);

    String getUnisidEntInfo(String unisid);

    String getIdCardHashAndAreaCodeEntInfo(String idCardHash, String areaCode);
}
