package ua.prom.roboticsdmc.validator;

import ua.prom.roboticsdmc.domain.EncryptingData;

public interface Validator {
    void validate(EncryptingData encryptingData);
}
