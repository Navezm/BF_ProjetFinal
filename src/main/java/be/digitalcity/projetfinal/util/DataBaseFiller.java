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
                new EventCategory("Event"),
                new EventCategory("Travel"),
                new EventCategory("Food"),
                new EventCategory("Portraits")
        );

        List<Role> roleList = List.of(
                new Role("makingReservation"),
                new Role("buy"),
                new Role("shopAccess"),
                new Role("makingQuotation"),
                new Role("addPainting"),
                new Role("addPicture"),
                new Role("deletePainting"),
                new Role("deletePicture"),
                new Role("handleRole"),
                new Role("treatingQuotation"),
                new Role("handlePurchase"),
                new Role("handleReservation"),
                new Role("handleQuotation"),
                new Role("deleteUser"),
                new Role("modifyUser"),
                new Role("addUser"),
                new Role("addAvailability"),
                new Role("USER")
        );

        List<Group> groupList = List.of(
                new Group("ADMIN", Set.of(
                        roleList.get(0),
                        roleList.get(1),
                        roleList.get(2),
                        roleList.get(3),
                        roleList.get(4),
                        roleList.get(5),
                        roleList.get(6),
                        roleList.get(7),
                        roleList.get(8),
                        roleList.get(9),
                        roleList.get(10),
                        roleList.get(11),
                        roleList.get(12),
                        roleList.get(13),
                        roleList.get(14),
                        roleList.get(15),
                        roleList.get(16),
                        roleList.get(17))
                ),
                new Group("USER", Set.of(
                        roleList.get(0),
                        roleList.get(1),
                        roleList.get(2),
                        roleList.get(3))
                )
        );

        List<PaintingType> paintingTypeList = List.of(
                new PaintingType("Lines"),
                new PaintingType("Abstract")
        );

        List<Painting> paintingList = List.of(
                new Painting(paintingTypeList.get(1), "Almost white", "/img/painting/IMG_0452.JPG", "Beautiful painting", BigDecimal.valueOf(62), true),
                new Painting(paintingTypeList.get(0), "Rainbow Lines Background", "/img/painting/IMG_0836.JPG", "Lines with colors and stuff", BigDecimal.valueOf(49), true),
                new Painting(paintingTypeList.get(0), "Twins Rainbow Lines", "/img/painting/IMG_7588.jpg", "Twin painting lines", BigDecimal.valueOf(120), true),
                new Painting(paintingTypeList.get(1), "Mix Color", "/img/painting/IMG_8125.JPG", "Colors mix with white", BigDecimal.valueOf(82.3), true),
                new Painting(paintingTypeList.get(0), "Spaced Lines", "/img/painting/IMG_8134.JPG", "White background", BigDecimal.valueOf(46.5), true),
                new Painting(paintingTypeList.get(1), "Abstract Square", "/img/painting/IMG_8172.JPG", "Square with colors", BigDecimal.valueOf(58), true),
                new Painting(paintingTypeList.get(1), "Colors Spread", "/img/painting/IMG_8653.JPG", "White & Colors", BigDecimal.valueOf(81), true),
                new Painting(paintingTypeList.get(1), "Ocean Throuple", "/img/painting/IMG_8822.JPG", "Three paintings that are better together", BigDecimal.valueOf(46), true),
                new Painting(paintingTypeList.get(1), "White Dots", "/img/painting/IMG_9301.JPG", "White dots on colorful background", BigDecimal.valueOf(90), true),
                new Painting(paintingTypeList.get(1), "White dots overview", "/img/painting/IMG_9440crop.jpg", "White dots on colorful background (but the whole painting)", BigDecimal.valueOf(90), true)
        );


        List<User> userList = List.of(
                new User("martin", encoder.encode("test123456"), "m@gmail.com", List.of(), groupList.get(0), addressList.get(0), true, true, true, true),
                new User("laetitia", encoder.encode("test123456"), "l@gmail.com", List.of(roleList.get(17), roleList.get(4)), groupList.get(1), addressList.get(1), true, true, true, true),
                new User("arnaud", encoder.encode("test123456"), "a@gmail.com", List.of(), groupList.get(1), addressList.get(2), true, true, true, true)
        );

        List<PaintingQuotation> paintingQuotationList = List.of(
                new PaintingQuotation(FormatEnum.BIG, ColorEnum.MIX1, "message1", paintingTypeList.get(0), StatusEnum.DONE, userList.get(0)),
                new PaintingQuotation(FormatEnum.SMALL, ColorEnum.MIX2, "message2", paintingTypeList.get(1), StatusEnum.PENDING, userList.get(1)),
                new PaintingQuotation(FormatEnum.MEDIUM, ColorEnum.MIX3, "message3", paintingTypeList.get(0), StatusEnum.ONGOING, userList.get(2))
        );

        List<PaintingPurchase> paintingPurchaseList = List.of(
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(0), List.of(paintingList.get(0)), addressList.get(0)),
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(1), List.of(paintingList.get(1)), addressList.get(1)),
                new PaintingPurchase(StatusEnum.ONGOING, userList.get(0), List.of(paintingList.get(0), paintingList.get(2)), addressList.get(2))
        );

        List<Picture> pictureList = List.of(
                new Picture("Alhambra", "/img/picture/Alhambra1.jpg", "Picture of the Alhambra", BigDecimal.valueOf(25), true, eventCategoryList.get(1)),
                new Picture("CapeTown", "/img/picture/CapeTown1.jpg", "Beautiful place CapeTown", BigDecimal.valueOf(12.5), true, eventCategoryList.get(1)),
                new Picture("Corse", "/img/picture/Corse1.jpg", "Beautiful island", BigDecimal.valueOf(33), true, eventCategoryList.get(1)),
                new Picture("Beach", "/img/picture/IMG_0173.JPG", "Magnificent beach", BigDecimal.valueOf(45.12), true, eventCategoryList.get(0)),
                new Picture("Sea", "/img/picture/IMG_0255.JPG", "Magnificent sea", BigDecimal.valueOf(30), true, eventCategoryList.get(0)),
                new Picture("New-York Street-Art", "/img/picture/IMG_5591.JPG", "Street Art in NY City", BigDecimal.valueOf(39), true, eventCategoryList.get(1)),
                new Picture("Bridge", "/img/picture/IMG_52381.jpg", "Magnificent bridge", BigDecimal.valueOf(12), true, eventCategoryList.get(2)),
                new Picture("Sea & Mountains", "/img/picture/IMG_53911.jpg", "Nature and it's beauty", BigDecimal.valueOf(31), true, eventCategoryList.get(2)),
                new Picture("Boat", "/img/picture/IMG_54111.jpg", "What a boat!", BigDecimal.valueOf(10), true, eventCategoryList.get(2)),
                new Picture("Italie", "/img/picture/Italie1.jpg", "Pasta & stuff", BigDecimal.valueOf(21.5), true, eventCategoryList.get(0)),
                new Picture("New-York Bridge", "/img/picture/NY1.jpg", "The beautiful famous bridge", BigDecimal.valueOf(16.5), true, eventCategoryList.get(1)),
                new Picture("Paris", "/img/picture/Paris1.jpg", "Love & Light", BigDecimal.valueOf(13), true, eventCategoryList.get(3)),
                new Picture("San Francisco", "/img/picture/SF1.jpg", "The bridge but in color", BigDecimal.valueOf(12), true, eventCategoryList.get(3)),
                new Picture("Road in the USA", "/img/picture/USA1.jpg", "Passing by in the highway", BigDecimal.valueOf(21), true, eventCategoryList.get(3))
        );

        List<PicturePurchase> picturePurchaseList = List.of(
                new PicturePurchase(StatusEnum.ONGOING, userList.get(0), List.of(pictureList.get(5), pictureList.get(6), pictureList.get(7))),
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
