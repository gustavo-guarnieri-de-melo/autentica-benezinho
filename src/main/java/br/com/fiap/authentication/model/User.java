package br.com.fiap.authentication.model;

import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(
            name = "user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<Profile> profiles = new LinkedHashSet<>();

    public User() {
    }

    public User(Long id, String email, String password, Pessoa pessoa, Set<Profile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.pessoa = pessoa;
        this.profiles = profiles;
    }

    public User addPerfil(Profile profile) {
        this.profiles.add(profile);
        return this;
    }

    public User removePerfil(Profile profile) {
        this.profiles.remove(profile);
        return this;
    }

    public Set<Profile> getProfiles() {
        return Collections.unmodifiableSet(profiles);
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public User setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", pessoa=" + pessoa +
                ", profiles=" + profiles +
                '}';
    }
}
