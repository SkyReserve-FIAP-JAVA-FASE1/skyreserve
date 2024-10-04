package org.skyreserve.domain.enums;

import lombok.Getter;

@Getter
public enum TipoVooEnum {

    IDA	(0, "Ida"),
    IDA_E_VOLTA	(1, "Ida e Volta");

    private final Integer codigo;
    private final String descricao;

    private TipoVooEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoVooEnum toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoVooEnum x : TipoVooEnum.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de vôo inválido");
    }
}