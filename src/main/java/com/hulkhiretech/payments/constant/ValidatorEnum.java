package com.hulkhiretech.payments.constant;

import java.util.HashMap;
import java.util.Map;

import com.hulkhiretech.payments.service.impl.validator.Check1Validator;
import com.hulkhiretech.payments.service.impl.validator.Check2Validator;
import com.hulkhiretech.payments.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ValidatorEnum {

    CHECK1_VALIDATOR_RULE("CHECK1_VALIDATOR_RULE", Check1Validator.class),
    CHECK2_VALIDATOR_RULE("CHECK2_VALIDATOR_RULE", Check2Validator.class);

    private final String name;
    private final Class<? extends Validator> validatorClass;

    private static final Map<String, ValidatorEnum> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (ValidatorEnum type : values()) {
            NAME_TO_ENUM_MAP.put(type.name, type);
        }
    }

    ValidatorEnum(String name, Class<? extends Validator> validatorClass) {
        this.name = name;
        this.validatorClass = validatorClass;
    }

    public static Class<? extends Validator> getValidatorClassByName(String name) {
        ValidatorEnum type = NAME_TO_ENUM_MAP.get(name);
        if(type == null) {
        	log.error("No validator found for name: {}", name);
        	return null;
        }
        
        return type.validatorClass;
    }
}
