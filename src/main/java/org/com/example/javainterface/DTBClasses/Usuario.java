package org.com.example.javainterface.DTBClasses;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String token;

    // esse e o contrutor vazio apenas para exemplificar cono fica, eu to utilizando uma classe java usuario para armazenar os dados do usuario do banco
    public Usuario(){}

    public Usuario(int id, String nome, String email, String senha, String token) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.token = token;
    }
    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }

    public String getNome()                 { return nome; }
    public void setNome(String nome)        { this.nome = nome; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public String getSenha()                { return senha; }
    public void setSenha(String senha)      { this.senha = senha; }

    public String getToken()                { return token; }
    public void setToken(String token)      { this.token = token; }
}
