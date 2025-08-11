package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Address;
import com.festival.everyday.core.domain.Festival;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Holder")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Holder extends User {


    /**
     * Holder 가 자신의 축제 목록을 조회해야 합니다.
     * 양방향 연관관계입니다.
     */
    @OneToMany(mappedBy = "holder")
    private List<Festival> festivals = new ArrayList<>();

    @Override
    public UserType getUserType() {
        return UserType.HOLDER;
    }

    /**
     * 외부에서 호출 불가능한 생성자입니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Holder(String account, String password, String name, String tel, String email, Address address) {
        super(account, password, name, tel, email, address);
    }

    /**
     * 단일 공통 진입점입니다.
     * 기획자를 생성합니다.
     */
    public static Holder create(String account, String password, String name, String tel, String email, Address address) {
        return new Holder(account, password, name, tel, email, address);
    }

    /**
     * 단일 공통 진입점입니다.
     * 개최자의 축제 목록에 축제를 추가합니다.
     * Festival 에서만 호출합니다.
     */
    public void addFestival(Festival festival) {
        festivals.add(festival);
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
