package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Address;
import com.festival.everyday.core.domain.BaseCreatedAtEntity;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.favorite.Favorite;
import com.festival.everyday.core.domain.favorite.ReceiverType;
import com.festival.everyday.core.domain.image.Image;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="users")
public abstract class User extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(name = "user_account")
    private String account;

    @NotBlank
    @Column(name = "user_password")
    private String password;

    @NotBlank
    @Column(name = "user_name")
    private String name;

    @NotBlank
    @Column(name = "user_tel")
    private String tel;

    @Email
    @NotBlank
    @Column(name = "user_email")
    private String email;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    /**
     * 주소를 저장하는 타입입니다.
     * 한 엔티티에서 두 번 이상 사용되는 경우에만 컬럼 명을 지정합니다.
     */
    @Valid
    @Embedded
    private Address address;

    public abstract UserType getUserType();

    /**
     * 내가 작성한 지원서 목록을 조회해야 합니다.
     * 양방향 연관관계입니다.
     */
    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

    /**
     * 내가 좋아요를 누른 대상 목록을 조회해야 한다.
     * 양방향으로 설계한다.
     */
    @OneToMany(mappedBy = "sender")
    private List<Favorite> favorites = new ArrayList<>();

    /**
     * 자식 클래스에서 사용하는 생성자.
     */
    protected User(String account, String password, String name, String tel, String email, Address address) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    /**
     * 지원 사실을 저장하는 메서드.
     * Application 클래스에서만 호출합니다.
     */
    public void addApplication(Application application) {
        applications.add(application);
    }

    /**
     * 좋아요 누른 목록을 저장하는 메서드.
     * Favorite 클래스에서만 호출합니다.
     */
    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
    }

    /**
     * 좋아요 목록에서 좋아요를 제거하는 메서드.
     * Favorite 클래스에서만 호출합니다.
     */
    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
    }

    /**
     * ~ 연관 관계 설정 메서드
     * ======================================
     * 비즈니스 메서드 ~
     *
     * 주의 사항
     * 1. 연관관계 설정 메서드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 가능한 선언하지 않습니다.필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 사용 의도가 나타나도록 이름을 작성합니다.
     */

}
