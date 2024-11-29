package com.testpractice.socialnetwork.entities.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FriendshipId implements java.io.Serializable {
    private static final long serialVersionUID = 743533289920735253L;
    @NotNull
    @Column(name = "user_first", nullable = false)
    private Integer userFirst;

    @NotNull
    @Column(name = "user_second", nullable = false)
    private Integer userSecond;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipId entity = (FriendshipId) o;
        return Objects.equals(this.userFirst, entity.userFirst) &&
                Objects.equals(this.userSecond, entity.userSecond);
    }


    @Override
    public int hashCode() {
        return Objects.hash(userFirst, userSecond);
    }

}