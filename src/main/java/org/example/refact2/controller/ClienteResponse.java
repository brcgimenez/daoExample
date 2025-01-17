package org.example.refact2.controller;

import java.math.BigDecimal;

public record ClienteResponse(BigDecimal id, int status, String mensagem) {
    public ClienteResponse(int status, String mensagem) {
        this(null, status, mensagem);
    }

    public ClienteResponse(BigDecimal id) {
        this(id, 201, null);
    }
}
