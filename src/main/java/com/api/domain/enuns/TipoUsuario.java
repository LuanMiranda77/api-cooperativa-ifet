package com.api.domain.enuns;

//@autor Jadson Feitosa #42

public enum TipoUsuario {
	
	MASTER("M"), VENDEDOR("V"), CAPITADOR("C");
	
	private String descricao;

	TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
