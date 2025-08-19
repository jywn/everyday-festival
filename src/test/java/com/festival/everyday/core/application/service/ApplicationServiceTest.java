//// src/test/java/com/festival/everyday/core/application/service/ApplicationServiceTest.java
//package com.festival.everyday.core.application.service;
//
//import com.festival.everyday.core.application.domain.Application;
//import com.festival.everyday.core.application.domain.SELECTED;
//import com.festival.everyday.core.application.dto.response.UpdateApplicationStatusResponse;
//import com.festival.everyday.core.application.repository.ApplicationRepository;
//import com.festival.everyday.core.festival.repository.FestivalRepository;
//import com.festival.everyday.core.recruit.repository.RecruitRepository;
//import com.festival.everyday.core.user.repository.UserRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.access.AccessDeniedException;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ApplicationServiceTest {
//
//    @Mock ApplicationRepository applicationRepository;
//    @Mock FestivalRepository festivalRepository;
//    @Mock RecruitRepository recruitRepository;
//    @Mock UserRepository userRepository;
//
//    ApplicationService service;
//
//    @BeforeEach
//    void setUp() {
//        service = new ApplicationService(applicationRepository, festivalRepository, recruitRepository, userRepository);
//    }
//
//    @Test
//    void 회사_지원서_상태변경_성공() {
//        Long festivalId = 10L, holderId = 100L, companyId = 200L;
//        String userType = "HOLDER";
//        SELECTED requested = SELECTED.ACCEPTED;
//
//        when(festivalRepository.findByIdAndHolderId(festivalId, holderId))
//                .thenReturn(Optional.of(mock(com.festival.everyday.core.festival.domain.Festival.class)));
//
//        Application app = mock(Application.class);
//        when(app.getId()).thenReturn(999L);
//        when(app.getSelected()).thenReturn(SELECTED.ACCEPTED);
//        when(applicationRepository.findCompanyApplicationByFestivalAndCompany(festivalId, companyId))
//                .thenReturn(Optional.of(app));
//
//        UpdateApplicationStatusResponse res = service.updateCompanyApplicationStatus(
//                festivalId, holderId, userType, companyId, requested);
//
//        verify(app, times(1)).apply(requested);
//        assertThat(res.getApplicationId()).isEqualTo(999L);
//        assertThat(res.getSelected()).isEqualTo(SELECTED.ACCEPTED);
//    }
//
//    @Test
//    void 회사_지원서_selected_null이면_예외() {
//        assertThatThrownBy(() ->
//                service.updateCompanyApplicationStatus(10L, 100L, "HOLDER", 200L, null)
//        ).isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @Test
//    void 회사_지원서_USER_TYPE_HOLDER_아니면_403() {
//        assertThatThrownBy(() ->
//                service.updateCompanyApplicationStatus(10L, 100L, "COMPANY", 200L, SELECTED.ACCEPTED)
//        ).isInstanceOf(AccessDeniedException.class);
//    }
//
//    @Test
//    void 회사_지원서_내축제_아니면_404() {
//        when(festivalRepository.findByIdAndHolderId(10L, 100L)).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() ->
//                service.updateCompanyApplicationStatus(10L, 100L, "HOLDER", 200L, SELECTED.ACCEPTED)
//        ).isInstanceOf(EntityNotFoundException.class);
//    }
//
//    @Test
//    void 회사_지원서_단건없으면_404() {
//        when(festivalRepository.findByIdAndHolderId(10L, 100L))
//                .thenReturn(Optional.of(mock(com.festival.everyday.core.festival.domain.Festival.class)));
//        when(applicationRepository.findCompanyApplicationByFestivalAndCompany(10L, 200L))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() ->
//                service.updateCompanyApplicationStatus(10L, 100L, "HOLDER", 200L, SELECTED.ACCEPTED)
//        ).isInstanceOf(EntityNotFoundException.class);
//    }
//
//    @Test
//    void 근로자_지원서_상태변경_성공() {
//        Long festivalId = 11L, holderId = 101L, laborId = 201L;
//        String userType = "HOLDER";
//        SELECTED requested = SELECTED.DENIED;
//
//        when(festivalRepository.findByIdAndHolderId(festivalId, holderId))
//                .thenReturn(Optional.of(mock(com.festival.everyday.core.festival.domain.Festival.class)));
//
//        Application app = mock(Application.class);
//        when(app.getId()).thenReturn(1000L);
//        when(app.getSelected()).thenReturn(SELECTED.DENIED);
//        when(applicationRepository.findLaborApplicationByFestivalAndLabor(festivalId, laborId))
//                .thenReturn(Optional.of(app));
//
//        UpdateApplicationStatusResponse res = service.updateLaborApplicationStatus(
//                festivalId, holderId, userType, laborId, requested);
//
//        verify(app, times(1)).apply(requested);
//        assertThat(res.getApplicationId()).isEqualTo(1000L);
//        assertThat(res.getSelected()).isEqualTo(SELECTED.DENIED);
//    }
//
//    @Test
//    void 근로자_지원서_selected_null이면_예외() {
//        assertThatThrownBy(() ->
//                service.updateLaborApplicationStatus(11L, 101L, "HOLDER", 201L, null)
//        ).isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @Test
//    void 근로자_지원서_USER_TYPE_HOLDER_아니면_403() {
//        assertThatThrownBy(() ->
//                service.updateLaborApplicationStatus(11L, 101L, "LABOR", 201L, SELECTED.DENIED)
//        ).isInstanceOf(AccessDeniedException.class);
//    }
//
//    @Test
//    void 근로자_지원서_내축제_아니면_404() {
//        when(festivalRepository.findByIdAndHolderId(11L, 101L)).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() ->
//                service.updateLaborApplicationStatus(11L, 101L, "HOLDER", 201L, SELECTED.DENIED)
//        ).isInstanceOf(EntityNotFoundException.class);
//    }
//
//    @Test
//    void 근로자_지원서_단건없으면_404() {
//        when(festivalRepository.findByIdAndHolderId(11L, 101L))
//                .thenReturn(Optional.of(mock(com.festival.everyday.core.festival.domain.Festival.class)));
//        when(applicationRepository.findLaborApplicationByFestivalAndLabor(11L, 201L))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() ->
//                service.updateLaborApplicationStatus(11L, 101L, "HOLDER", 201L, SELECTED.DENIED)
//        ).isInstanceOf(EntityNotFoundException.class);
//    }
//}
