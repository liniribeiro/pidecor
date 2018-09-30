package com.aliniribeiro.admin.api.model.customer;

import com.aliniribeiro.admin.api.model.user.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "useraccount", nullable = false)
    private UUID user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "useraccount", referencedColumnName = "ID", insertable = false, updatable = false)
    private UserEntity userAccount;

    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "criationdate", nullable = false)
    private LocalDate creationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UserEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserEntity userAccount) {
        this.userAccount = userAccount;
    }
}
