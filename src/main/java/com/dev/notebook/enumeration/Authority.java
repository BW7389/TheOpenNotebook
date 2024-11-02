package com.dev.notebook.enumeration;

import static com.dev.notebook.constants.Constant.ADMIN_AUTHORITIES;
import static com.dev.notebook.constants.Constant.USER_AUTHORITIES;

public enum Authority {
    USER(USER_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES);

    private final String value;

    Authority(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
