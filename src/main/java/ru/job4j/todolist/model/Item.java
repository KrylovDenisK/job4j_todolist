package ru.job4j.todolist.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String name;
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now().withNano(0);
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "done")
    private boolean done;

    public Item() {
    }

    public Item(String description, User user) {
        this.name = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(created, item.created)
                && Objects.equals(done, item.done);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id
                + ", description='" + name + '\'' + ", created="
                + created + ", done=" + done + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, done);
    }
}
