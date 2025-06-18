/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.antonio.F.F.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Junior
 */
public class ProblemaException {

    private Integer status;
    private LocalDateTime datahora;
    private String titulo;
    
    private List<CampoProblema> listaCamposProblema;
    
    public List<CampoProblema> getCampos() {
        return listaCamposProblema;
    }
    
    public void setCampos(List<CampoProblema> campos) {
        this.listaCamposProblema = campos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static class CampoProblema {

        private String nomeCampo;
        private String mensagemCampo;

        public CampoProblema(String nomeCampo, String mensagemCampo) {
            this.nomeCampo = nomeCampo;
            this.mensagemCampo = mensagemCampo;
        }

        public String getNomeCampo() {
            return nomeCampo;
        }

        public void setNomeCampo(String nomeCampo) {
            this.nomeCampo = nomeCampo;
        }

        public String getMensagemCampo() {
            return mensagemCampo;
        }

        public void setMensagemCampo(String mensagemCampo) {
            this.mensagemCampo = mensagemCampo;
        }

    }
}