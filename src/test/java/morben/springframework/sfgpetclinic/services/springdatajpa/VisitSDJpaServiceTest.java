package morben.springframework.sfgpetclinic.services.springdatajpa;

import morben.springframework.sfgpetclinic.model.Speciality;
import morben.springframework.sfgpetclinic.model.Visit;
import morben.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {

        Visit visit = new Visit();
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(visit);

        when(repository.findAll()).thenReturn(visitSet);

        Set<Visit> foundVisits = service.findAll();
        verify(repository).findAll();
        assertThat(foundVisits).hasSize(1);
    }

    @Test
    void findById() {
        Visit visit = new Visit();

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(visit));

        Visit found = service.findById(1L);
        assertThat(found).isNotNull();
        verify(repository, times(1))
                .findById(1l);
        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(repository.save(any(Visit.class)))
                .thenReturn(visit);
        Visit savedVisit = service.save(new Visit());
        verify(repository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(repository, times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(repository,
                never()).deleteById(3l);
    }
}