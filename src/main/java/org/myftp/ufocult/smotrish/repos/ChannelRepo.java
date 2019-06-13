package org.myftp.ufocult.smotrish.repos;

import org.myftp.ufocult.smotrish.domain.Channel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChannelRepo extends CrudRepository<Channel, Long>{
    List<Channel> findByName(String name);
}
