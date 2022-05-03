package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

import java.util.List;

@Repository
@Transactional
public class CopyDAOImpl extends AbstractDAOImpl<Copy> implements CopyDAO {
    public CopyDAOImpl() {
        super(Copy.class);
    }

    @Override
    public List<Copy> getByFilmId(Integer filmId) {
        return null;
    }

    @Override
    public List<Copy> getCopyByType(String type) {
        return null;
    }

    @Override
    public List<Copy> getCopyByStatus(String status) {
        return null;
    }

    @Override
    public List<Copy> getCopyByPrice(int price) {
        return null;
    }

    @Override
    public Film getFilm() {
        return null;
    }
}
