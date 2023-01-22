package ru.javarush.module4.projecthibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.module4.projecthibernate2.dao.ActorDAO;
import ru.javarush.module4.projecthibernate2.dao.AddressDAO;
import ru.javarush.module4.projecthibernate2.dao.CategoryDAO;
import ru.javarush.module4.projecthibernate2.dao.CityDAO;
import ru.javarush.module4.projecthibernate2.dao.CountryDAO;
import ru.javarush.module4.projecthibernate2.dao.CustomerDAO;
import ru.javarush.module4.projecthibernate2.dao.FilmDAO;
import ru.javarush.module4.projecthibernate2.dao.FilmTextDAO;
import ru.javarush.module4.projecthibernate2.dao.InventoryDAO;
import ru.javarush.module4.projecthibernate2.dao.LanguageDAO;
import ru.javarush.module4.projecthibernate2.dao.PaymentDAO;
import ru.javarush.module4.projecthibernate2.dao.RentalDAO;
import ru.javarush.module4.projecthibernate2.dao.StaffDAO;
import ru.javarush.module4.projecthibernate2.dao.StoreDAO;
import ru.javarush.module4.projecthibernate2.entity.Address;
import ru.javarush.module4.projecthibernate2.entity.City;
import ru.javarush.module4.projecthibernate2.entity.Customer;
import ru.javarush.module4.projecthibernate2.entity.Film;
import ru.javarush.module4.projecthibernate2.entity.Inventory;
import ru.javarush.module4.projecthibernate2.entity.Payment;
import ru.javarush.module4.projecthibernate2.entity.Rental;
import ru.javarush.module4.projecthibernate2.entity.Staff;
import ru.javarush.module4.projecthibernate2.entity.Store;
import ru.javarush.module4.projecthibernate2.utils.MovieDBSessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovieDBApp {
    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final PaymentDAO paymentDAO;
    private final LanguageDAO languageDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public MovieDBApp() {
        sessionFactory = MovieDBSessionFactory.getSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {
        MovieDBApp movieDBApp = new MovieDBApp();
        Customer customer = movieDBApp.createCustomer();
        //movieDBApp.customerReturnInventoryToStore();
        movieDBApp.customerRentInventory(customer);
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Film filmForRent = filmDAO.getFirstAvailableFilmForRent();
            Store store = storeDAO.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(filmForRent);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(25.59));
            payment.setRental(rental);
            payment.setStaff(staff);
            paymentDAO.save(payment);

            transaction.commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental unreturnedRental = rentalDAO.getAnyUnreturnedRental();
            unreturnedRental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(unreturnedRental);

            transaction.commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);
            City city = cityDAO.getByName("Jurez");

            Address address = new Address();
            address.setAddress("Lenina str, 43");
            address.setCity(city);
            address.setPhone("+9-876-5432100");
            address.setDistrict("Area 13");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setFirstName("Armand");
            customer.setLastName("Van Held");
            customer.setAddress(address);
            customer.setEmail("vanhelden@hotmail.com");
            customer.setStore(store);
            customer.setActive(true);
            customerDAO.save(customer);

            transaction.commit();
            return customer;
        }
    }
}
