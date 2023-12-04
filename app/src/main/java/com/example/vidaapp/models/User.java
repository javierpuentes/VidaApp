
package com.example.vidaapp.models;

public class User {
    private long id;
    private String user;
    private String password;

    private String question;

    private String answer;

    public User(long id, String user, String password, String question, String answer) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }
    public long Db_QueriesUsers() {
        return this.id;
    }
    public void loginUser (long id, String user) {
        this.id = id;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
