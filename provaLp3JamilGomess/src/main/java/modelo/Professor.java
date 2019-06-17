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
public class Professor {
	  
	    private Integer codigo;
	    private String nome;
	    

	    public Professor(Integer codigo, String nome){
	        this.codigo = codigo;
	        this.nome = nome;
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

	   

	

		@Override
		public String toString() {
			return codigo + " - " + nome;
		}

    
    
    
}
