package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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

        verify(specialtyRepository,times(1)).deleteById(12l);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository,atLeast(1)).deleteById(12l);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository,atMost(10)).deleteById(12l);
    }

    @Test
    void deleteByIdAtNever() {
        service.deleteById(12l);
        service.deleteById(12l);

        verify(specialtyRepository,never()).deleteById(1l);
    }

    @Test
    void deleteByObject() {
        service.delete(new Speciality());
    }
}