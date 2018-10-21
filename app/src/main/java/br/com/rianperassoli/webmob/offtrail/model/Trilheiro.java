package br.com.rianperassoli.webmob.offtrail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Trilheiro {

    @DatabaseField(generatedId = true)
    private Integer codigo;

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false)
    private Integer ida;

    @DatabaseField(foreign = true, foreignColumnName = "codigo")
    private Moto moto;

    public Trilheiro(){}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIda() {
        return ida;
    }

    public void setIda(Integer ida) {
        this.ida = ida;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}
