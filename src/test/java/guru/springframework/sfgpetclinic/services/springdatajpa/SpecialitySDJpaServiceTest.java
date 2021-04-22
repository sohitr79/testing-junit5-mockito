package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    /**
     * In this test case we want to test functionality of SpecialitySDJpaService Class but this class uses Objects of
     * other class SpecialtyRepository. So we need an object of SpecialitySDJpaService class (service) but since service
     * object is dependent on SpecialtyRepository object so we need SpecialtyRepository as well.
     * Thus we have mocked SpecialtyRepository, and after that we have said using @InjectMocks that please inject all
     * the mocked objects i.e specialtyRepository into service.
     */

    @Test
    void deleteById() {
        service.deleteById(12l);
    }

    /**
     * to check how many times particular function is called
     */
    @Test
    void deleteByIdVerify() {
        service.deleteById(12l);

        verify(specialtyRepository, times(1)).deleteById(12l);
    }

    @Test
    void testDelete() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findById() {

        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));
        Speciality foundSpecialty = service.findById(1l);

        assertThat(foundSpecialty).isNotNull();
        verify(specialtyRepository).findById(1l);

    }

    /**
     * its similar to above test but only syntax is different
     * this one is more readable
     */
    @Test
    void findByIdBdd() {

        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1l);

        //then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository, atLeast(1)).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository, atMost(10)).deleteById(12l);
    }

    @Test
    void deleteByIdAtNever() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository, never()).deleteById(1l);
    }

    @Test
    void deleteByObject() {
        service.delete(new Speciality());
    }

    @Test
    void testDoThrowsException() {
        doThrow(new RuntimeException()).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException());
        assertThrows(RuntimeException.class,()->service.findById(anyLong()));
        then(specialtyRepository).should(times(1)).findById(anyLong());
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class,()->service.findById(anyLong()));
        then(specialtyRepository).should(times(1)).findById(anyLong());
    }
}