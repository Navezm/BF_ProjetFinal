package be.digitalcity.projetfinal.util;

import be.digitalcity.projetfinal.models.entity.*;
import be.digitalcity.projetfinal.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class DataBaseFiller implements InitializingBean {
    private final AddressRepository addressRepository;
    private final DisponibilityRepository disponibilityRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final GroupRepository groupRepository;
    private final PaintingRepository paintingRepository;
    private final PaintingQuotationRepository paintingQuotationRepository;
    private final PaintingPurchaseRepository paintingPurchaseRepository;
    private final PaintingTypeRepository paintingTypeRepository;
    private final PicturePurchaseRepository picturePurchaseRepository;
    private final PictureRepository pictureRepository;
    private final ReservationRepository reservationRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataBaseFiller(AddressRepository addressRepository, DisponibilityRepository disponibilityRepository, EventCategoryRepository eventCategoryRepository, GroupRepository groupRepository, PaintingRepository paintingRepository, PaintingQuotationRepository paintingQuotationRepository, PaintingPurchaseRepository paintingPurchaseRepository, PaintingTypeRepository paintingTypeRepository, PicturePurchaseRepository picturePurchaseRepository, PictureRepository pictureRepository, ReservationRepository reservationRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.disponibilityRepository = disponibilityRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.groupRepository = groupRepository;
        this.paintingRepository = paintingRepository;
        this.paintingQuotationRepository = paintingQuotationRepository;
        this.paintingPurchaseRepository = paintingPurchaseRepository;
        this.paintingTypeRepository = paintingTypeRepository;
        this.picturePurchaseRepository = picturePurchaseRepository;
        this.pictureRepository = pictureRepository;
        this.reservationRepository = reservationRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Address> addressList = List.of(
                new Address("rue de Haerne", "105", "1040", "Etterbeek"),
                new Address("rue du Chapiteau", "10", "1200", "Bruxelles"),
                new Address("Avenue Blizzard", "35", "6520", "Eternal")
        );

        List<Disponibility> disponibilityList = List.of(
                new Disponibility(LocalDate.now(), true),
                new Disponibility(LocalDate.of(2021, 10, 3), false),
                new Disponibility(LocalDate.of(2022, 8, 24), true),
                new Disponibility(LocalDate.of(2021, 12, 16), false)
        );

        List<EventCategory> eventCategoryList = List.of(
                new EventCategory("Mariage"),
                new EventCategory("Anniversaire"),
                new EventCategory("Nourriture"),
                new EventCategory("Landscape")
        );

        List<Role> roleList = List.of(
                new Role("reservation"),
                new Role("buying"),
                new Role("addPainting"),
                new Role("addPicture"),
                new Role("shopAccess")
        );

        List<Group> groupList = List.of(
                new Group("ADMIN", Set.of(roleList.get(1), roleList.get(2), roleList.get(0), roleList.get(3), roleList.get(4))),
                new Group("USER", Set.of(roleList.get(1), roleList.get(0), roleList.get(4)))
        );

        List<Painting> paintingList = List.of(
                new Painting(

                )
        );

        this.addressRepository.saveAll(addressList);
        this.disponibilityRepository.saveAll(disponibilityList);
        this.eventCategoryRepository.saveAll(eventCategoryList);
        this.roleRepository.saveAll(roleList);
        this.groupRepository.saveAll(groupList);
    }
}
