package ccm.kx.server.service;

import java.util.List;

import javax.persistence.EntityManager;

import ccm.kx.server.ServerLauncher;
import ccm.kx.server.jpa.UserEntity;

/**
 * @author KX
 */
public class UserService {

    private EntityManager em = ServerLauncher.getEntityManager();

    public boolean checkUser(String login, String password) {
        UserEntity user = em.find(UserEntity.class, login);
        return user != null && user.getPassword().equals(password);
    }

    public void createUser(String login, String password) {
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setPassword(password);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updatePassword(String login, String password) {
        UserEntity user = em.find(UserEntity.class, login);
        if (user == null)
            return;
        user.setPassword(password);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public List<String> listAllUsers() {
        return em.createNamedQuery("findAllUsers", String.class).getResultList();
    }
}
