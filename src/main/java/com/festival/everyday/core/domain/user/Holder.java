package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Festival;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Holder")
@Getter
public class Holder extends User {

    @Column(name = "holder_name")
    private String name;

    @OneToMany(mappedBy = "holder")
    private List<Festival> festivals = new ArrayList<>();

    public void addFestival(Festival festival) {
        festivals.add(festival);
        festival.uploadFestival(this);
    }

    @Override
    public UserType getUserType() {
        return UserType.HOLDER;
    }

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
