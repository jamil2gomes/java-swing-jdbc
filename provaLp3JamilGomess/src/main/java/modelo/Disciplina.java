/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jamil
 */
public class Disciplina {
    
	
    private Integer codigo;
    private String nome;
    private String ementa;
    private String ch;
    private Professor prof;
   

    public Disciplina() {
    }

    public Disciplina(Integer codigo, String nome, String ementa, String ch) {
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
        this.ch = ch;
    }
    
    
  
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

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    
    
    


    public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}


	@Override
	public String toString() {
		return codigo + " - " + nome;
	}
    
    
    
}
