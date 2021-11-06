package be.digitalcity.projetfinal.util;

import be.digitalcity.projetfinal.models.entity.*;
import be.digitalcity.projetfinal.repository.*;
import be.digitalcity.projetfinal.util.enums.ColorEnum;
import be.digitalcity.projetfinal.util.enums.FormatEnum;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    private final PasswordEncoder encoder;

    @Autowired
    public DataBaseFiller(AddressRepository addressRepository, DisponibilityRepository disponibilityRepository, EventCategoryRepository eventCategoryRepository, GroupRepository groupRepository, PaintingRepository paintingRepository, PaintingQuotationRepository paintingQuotationRepository, PaintingPurchaseRepository paintingPurchaseRepository, PaintingTypeRepository paintingTypeRepository, PicturePurchaseRepository picturePurchaseRepository, PictureRepository pictureRepository, ReservationRepository reservationRepository, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
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
        this.encoder = encoder;
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

        List<PaintingType> paintingTypeList = List.of(
                new PaintingType("Stripes"),
                new PaintingType("Dots"),
                new PaintingType("Impressionism")
        );

        List<Painting> paintingList = List.of(
                new Painting(paintingTypeList.get(0), "Paint1", "src", "description", BigDecimal.valueOf(1.20), true),
                new Painting(paintingTypeList.get(1), "Paint2", "src1", "description1", BigDecimal.valueOf(253), true),
                new Painting(paintingTypeList.get(0), "Paint3", "src2", "description2", BigDecimal.valueOf(24.5), true),
                new Painting(paintingTypeList.get(2), "Paint4", "src3", "description3", BigDecimal.valueOf(82.3), true),
                new Painting(paintingTypeList.get(2), "Paint5", "src4", "description4", BigDecimal.valueOf(4.658), true)
        );


        List<User> userList = List.of(
                new User("martin", encoder.encode("test123456"), "m@gmail.com", List.of(roleList.get(0)), groupList.get(0), addressList.get(0), true, true, true, true),
                new User("laetitia", encoder.encode("test123456"), "l@gmail.com", List.of(roleList.get(3), roleList.get(4)), groupList.get(1), addressList.get(1), true, true, true, true),
                new User("arnaud", encoder.encode("test123456"), "a@gmail.com", List.of(), groupList.get(1), addressList.get(2), true, true, true, true)
        );

        List<PaintingQuotation> paintingQuotationList = List.of(
                new PaintingQuotation(FormatEnum.BIG, ColorEnum.MIX1, "message1", paintingTypeList.get(0), StatusEnum.DONE, userList.get(0)),
                new PaintingQuotation(FormatEnum.SMALL, ColorEnum.MIX2, "message2", paintingTypeList.get(1), StatusEnum.PENDING, userList.get(1)),
                new PaintingQuotation(FormatEnum.MEDIUM, ColorEnum.MIX3, "message3", paintingTypeList.get(2), StatusEnum.ONGOING, userList.get(2))
        );

        List<PaintingPurchase> paintingPurchaseList = List.of(
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(0), List.of(paintingList.get(0)), addressList.get(0)),
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(1), List.of(paintingList.get(1)), addressList.get(1)),
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(0), List.of(paintingList.get(0), paintingList.get(2)), addressList.get(2))
        );

        List<Picture> pictureList = List.of(
                new Picture("picture1", "src", "description", BigDecimal.valueOf(10), true, eventCategoryList.get(0)),
                new Picture("picture2", "src1", "description1", BigDecimal.valueOf(1.5), true, eventCategoryList.get(1)),
                new Picture("picture3", "src2", "description2", BigDecimal.valueOf(263), true, eventCategoryList.get(2)),
                new Picture("picture4", "src3", "description3", BigDecimal.valueOf(45.12), true, eventCategoryList.get(1))
        );

        List<PicturePurchase> picturePurchaseList = List.of(
                new PicturePurchase(StatusEnum.ONGOING, userList.get(0), pictureList),
                new PicturePurchase(StatusEnum.ONGOING, userList.get(1), List.of(pictureList.get(2), pictureList.get(3))),
                new PicturePurchase(StatusEnum.ONGOING, userList.get(2), List.of(pictureList.get(0)))
        );

        List<Reservation> reservationList = List.of(
                new Reservation(StatusEnum.PENDING, userList.get(0), LocalDate.of(2022,12,1), LocalDate.of(2022,12,2), eventCategoryList.get(0)),
                new Reservation(StatusEnum.DONE, userList.get(1), LocalDate.of(2021,10,1), LocalDate.of(2021,10,2), eventCategoryList.get(2)),
                new Reservation(StatusEnum.ONGOING, userList.get(2), LocalDate.of(2022,12,24), LocalDate.of(2022,12,25), eventCategoryList.get(1))
        );

//        this.addressRepository.saveAll(addressList);
//        this.disponibilityRepository.saveAll(disponibilityList);
//        this.eventCategoryRepository.saveAll(eventCategoryList);
//        this.roleRepository.saveAll(roleList);
//        this.groupRepository.saveAll(groupList);
//        this.userRepository.saveAll(userList);
//        this.paintingTypeRepository.saveAll(paintingTypeList);
//        this.paintingRepository.saveAll(paintingList);
//        this.paintingQuotationRepository.saveAll(paintingQuotationList);
//        this.paintingPurchaseRepository.saveAll(paintingPurchaseList);
//        this.pictureRepository.saveAll(pictureList);
//        this.picturePurchaseRepository.saveAll(picturePurchaseList);
//        this.reservationRepository.saveAll(reservationList);
    }
}
