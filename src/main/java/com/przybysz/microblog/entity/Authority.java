package com.przybysz.microblog.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

//    @Column(name = "user_id")
//    private int userId;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy="authorities",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private User user;

    public Authority() {
    }

    public Authority(User user) {
        this.user = user;
    }

    public Authority(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
//                ", userId=" + userId +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
