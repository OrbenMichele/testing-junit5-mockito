package morben.springframework.sfgpetclinic.controllers;

import morben.springframework.sfgpetclinic.ControllerTests;
import morben.springframework.sfgpetclinic.fauxspring.Model;
import morben.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import morben.springframework.sfgpetclinic.model.Vet;
import morben.springframework.sfgpetclinic.services.SpecialtyService;
import morben.springframework.sfgpetclinic.services.VetService;
import morben.springframework.sfgpetclinic.services.map.SpecialityMapService;
import morben.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    VetService vetService;
    SpecialtyService specialtyService;

    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "joe", "buck", null);
        Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapImpl();

        String view = vetController.listVets(model);

        assertThat("vets/index").isEqualTo(view);

        Set modelAttribute = (Set) ((ModelMapImpl) model).getMap().get("vets");

        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}