package morben.springframework.sfgpetclinic.services.springdatajpa;

import morben.springframework.sfgpetclinic.model.Speciality;
import morben.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository,
                times(2)).deleteById(1l);
    }

    @Test
    void delete() {
        Speciality speciality = new Speciality();

        service.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository,
                atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository,
                atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository,
                never()).deleteById(5l);
    }

    @Test
    void findById() {
        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1L))
                .thenReturn(Optional.of(speciality));

        Speciality foundSpecialty = service.findById(1L);
        assertThat(foundSpecialty).isNotNull();
        verify(specialtyRepository, times(1))
                .findById(1l);

        verify(specialtyRepository).findById(any(Long.class));
        verify(specialtyRepository).findById(anyLong());
    }


}