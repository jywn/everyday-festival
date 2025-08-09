package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Address;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.favorite.Favorite;
import com.festival.everyday.core.domain.image.Image;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Holder, Company, Labor 가 상속하는 엔티티.
 * 추상 클래스로 선언하여 실제 인스턴스가 테이블에 등록되지 않습니다.
 * 다만, 이를 상속한 엔티티들의 인스턴스가 테이블에 등록됩니다.
 * 자주 조회되는 엔티티이므로, 조인을 최소화하고자 '싱글 테이블' 전략을 선택했습니다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Table(name ="user")
public abstract class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_account")
    private String account;

    @Column(name = "user_password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    /**
     * 주소를 저장하는 타입입니다.
     * 한 엔티티에서 두 번 이상 사용되는 경우에만 컬럼 명을 지정합니다.
     */
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Favorite> favorites = new ArrayList<>();

    public void uploadImage(Image image) {
        this.image = image;
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
        favorite.setSender(this);
    }

    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
        favorite.setSender(null);
    }

    public abstract UserType getUserType();


    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
