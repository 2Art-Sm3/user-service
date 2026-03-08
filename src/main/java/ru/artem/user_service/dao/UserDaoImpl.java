package ru.artem.user_service.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.artem.user_service.config.HibernateUtil;
import ru.artem.user_service.entity.User;
import ru.artem.user_service.exception.DatabaseException;

import java.util.List;
import java.util.Optional;

@Log4j2
public class UserDaoImpl implements UserDao {

    @Override
    public User save(User user) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();

            log.info("User saved: {}", user.getEmail());

            return user;

        } catch (HibernateException e) {

            if (transaction != null) {
                transaction.rollback();
            }

            log.error("Error saving user", e);

            throw new DatabaseException("Ошибка сохранения пользователя", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            User user = session.find(User.class, id);

            return Optional.ofNullable(user);

        } catch (HibernateException e) {

            log.error("Error finding user", e);

            throw new DatabaseException("Ошибка поиска пользователя", e);
        }
    }

    @Override
    public List<User> findAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM User", User.class).list();

        } catch (HibernateException e) {

            log.error("Error getting users", e);

            throw new DatabaseException("Ошибка получения пользователей", e);
        }
    }

    @Override
    public User update(User user) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();

            log.info("User updated: {}", user.getEmail());

            return user;

        } catch (HibernateException  e) {

            if (transaction != null) {
                transaction.rollback();
            }

            log.error("Error updating user", e);

            throw new DatabaseException("Ошибка обновления пользователя", e);        }
    }

    @Override
    public void delete(Long id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.find(User.class, id);

            if (user != null) {
                session.remove(user);
            }

            transaction.commit();

            log.info("User deleted: {}", id);

        } catch (HibernateException  e) {

            if (transaction != null) {
                transaction.rollback();
            }

            log.error("Error deleting user", e);

            throw new DatabaseException("Ошибка удаления пользователя", e);
        }
    }
}
