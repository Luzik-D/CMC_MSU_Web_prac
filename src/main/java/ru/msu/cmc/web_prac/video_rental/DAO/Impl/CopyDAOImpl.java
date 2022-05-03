package ru.msu.cmc.web_prac.video_rental.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msu.cmc.web_prac.video_rental.tables.Copy;
import ru.msu.cmc.web_prac.video_rental.DAO.CopyDAO;

@Repository
@Transactional
public class CopyDAOImpl extends AbstractDAOImpl<Copy> implements CopyDAO {
    public CopyDAOImpl() {
        super(Copy.class);
    }
}
