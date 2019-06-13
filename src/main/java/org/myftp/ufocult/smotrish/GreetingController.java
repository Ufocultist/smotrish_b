package org.myftp.ufocult.smotrish;

import org.myftp.ufocult.smotrish.domain.Channel;
import org.myftp.ufocult.smotrish.repos.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ChannelRepo channelRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object>model){
        Iterable<Channel> channels = channelRepo.findAll();

        model.put("channels", channels);
        return "main";
    }

    @PostMapping("/main")
    public String addChannel(
            @RequestParam String name,
            @RequestParam Integer number,
            Map<String, Object> model
            ){
        Channel channel = new Channel(name, number);
        channelRepo.save(channel);

        Iterable<Channel> channels = channelRepo.findAll();
        model.put("channels", channels);

        return "main";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
            ){
                Iterable<Channel> channels;
                if(filter != null && !filter.isEmpty()) {
                    channels = channelRepo.findByName(filter);
                }else {
                    channels = channelRepo.findAll();
                }
                model.put("channels", channels);
            return "main";
    }


}