package com.testpractice.socialnetwork.entities.mysql;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "friendship")
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @MapsId("userFirst")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_first", nullable = false)
    private User userFirst;

    @MapsId("userSecond")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_second", nullable = false)
    private User userSecond;


    public Friendship(User userFirst, User userSecond) {
        this.userFirst = userFirst;
        this.userSecond = userSecond;
    }

}