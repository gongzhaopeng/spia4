package spittr.data;

import spittr.Spitter;

/**
 * Created by gongzhaopeng on 15/03/2018.
 */
public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
