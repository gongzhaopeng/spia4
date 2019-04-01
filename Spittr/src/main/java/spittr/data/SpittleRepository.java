package spittr.data;

import spittr.Spittle;

import java.util.List;

/**
 * Created by gongzhaopeng on 14/03/2018.
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);
}
