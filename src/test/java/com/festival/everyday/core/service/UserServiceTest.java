package com.festival.everyday.core.service;

import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.repository.UserRepository;
import com.festival.everyday.core.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("findById: 존재하면 User 반환")
    void findById_success() {
        // given
        Long id = 1L;
        User mockUser = mock(User.class);
        when(userRepository.findById(id)).thenReturn(Optional.of(mockUser));

        // when
        User result = userService.findById(id);

        // then
        assertNotNull(result);
        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("findById: 없으면 EntityNotFoundException('Unexpected User')")
    void findById_notFound() {
        // given
        Long id = 2L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class,
                () -> userService.findById(id)
        );
        assertEquals("Unexpected User", ex.getMessage());
        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);
    }
}
