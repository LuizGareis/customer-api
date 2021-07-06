package com.luizgareis.enums;

public enum StateEnum {

    AC("Acre"),
    SP("São Paulo"),
    RJ("Rio de Janeiro"),
    BA("Bahia"),
    SC("Santa Catarina"),
    RS("Rio Grande do Sul"),
    TO("Tocantins");

    private String descricao;

    private StateEnum(String descricao) {
        this.descricao = descricao;
    }

    private String getDescricao() {
        return descricao;
    }
}
