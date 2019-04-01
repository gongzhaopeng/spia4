package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gongzhaopeng on 14/03/2018.
 */
@Configuration
@ComponentScan(basePackages = {"spittr"},
        excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "spittr\\.web\\..*")
        })
public class RootConfig {

    @Bean
    SpittleRepository spittleRepository() {
        return new SpittleRepository() {
            public List<Spittle> findSpittles(long max, int count) {
                if (max == Long.MAX_VALUE && count == 20) {
                    List<Spittle> spittles = new ArrayList<Spittle>();

                    for (int i = 0; i < count; ++i) {
                        spittles.add(new Spittle((long) i, "Spittle " + i, new Date(),
                                i / 10.0, i / 10.0));
                    }

                    return spittles;
                } else {
                    throw new InvalidParameterException();
                }
            }

            public Spittle findOne(long id) {
                if (id == 12345) {
                    return new Spittle("Hello", new Date());
                } else {
                    throw new InvalidParameterException();
                }
            }
        };
    }

    @Bean
    SpitterRepository spitterRepository() {
        return new SpitterRepository() {
            public Spitter save(Spitter spitter) {
                if (spitter.getUsername().equals("jbauer")) {
                    spitter.setId(24L);
                    return spitter;
                } else {
                    throw new InvalidParameterException();
                }
            }

            public Spitter findByUsername(String username) {
                if (username.equals("jbauer")) {
                    return new Spitter(24L, "jbauer", "24hours",
                            "Jack", "Bauer", "jbauer@ctu.gov");
                } else {
                    throw new InvalidParameterException();
                }
            }
        };
    }
}
