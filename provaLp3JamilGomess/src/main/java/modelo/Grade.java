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
public class Grade {
    
	private String ano;
    private Curso curso;
    private Disciplina disciplina;

    public Grade() {
    }

    public Grade(String ano, Curso curso, Disciplina disciplina) {
        this.curso = curso;
        this.disciplina = disciplina;
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Grade [ano=" + ano + ", curso=" + curso + ", disciplina=" + disciplina + "]";
	}
    
    
    
    
}
